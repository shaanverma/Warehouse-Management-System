package ca.uwo.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.viewer.StockManager;
import ca.uwo.viewer.restock.strategies.RestockStrategy;
import ca.uwo.viewer.restock.strategies.RestockStrategyFactory;

/**
 * @author kkontog, ktsiouni, mgrigori This class provides the main method to
 *         test and run the warehouse system.
 */
public class Driver {

	public static void main(String[] args) {
		Map<Integer, Buyer> buyers = new HashMap<>();
		//Read all the buyers from the file and save them. Each line consists of the ID, name and password of the buyer.
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("buyer_file")));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineTokens = line.split("\t");
				buyers.put(Integer.parseInt(lineTokens[0]), new Buyer(lineTokens[1], lineTokens[2]));
			}
			br.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
		//Read the buyer and ordering details from the file. Each line consists of the ID of the buyer, name and quantity of 
		//the item. 
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("driver_file")));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineTokens = line.split("\t");
				if (lineTokens[0].equals("StrategyChange")) {
					RestockStrategy strategy = RestockStrategyFactory.create(lineTokens[1]);
					StockManager.getInstance().setRestockStrategy(strategy);
				} else {
					Integer buyerId = Integer.parseInt(lineTokens[0]);
					Map<String, Integer> orderItems = new HashMap<String, Integer>();
					for (int i = 1; i < lineTokens.length - 1; i += 2) {
						orderItems.put(lineTokens[i], Integer.parseInt(lineTokens[i + 1]));
					}
					buyers.get(buyerId).buy(orderItems);
				}

			}
			br.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}

}
