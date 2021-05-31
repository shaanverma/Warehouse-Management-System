package ca.uwo.pricingStrategies.individual;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class collects all the concrete implementations of the {@link IndividualPricingStrategy} interface.
 */
public class IndividualPricingStrategyRepo {
	private static IndividualPricingStrategyRepo instance = null;
	
	private Map<String, IndividualPricingStrategy> strategies = new HashMap<String, IndividualPricingStrategy>();
	
	/**
	 * constructor for the IndividualPricingStrategyRepo class.
	 * Read the name of the item and corresponding strategy from the file and save them.
	 */
	private IndividualPricingStrategyRepo() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("indiv_pricing_strategy_file")));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineTokens = line.split("\t");
				strategies.put(lineTokens[0], IndividualPricingStrategyFactory.create(lineTokens[1]));
			}
			br.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	/**
	 * there should be only one instance of IndividualPricingStrategyRepo.
	 * @return the instance of IndividualPricingStrategyRepo.
	 */
	public static IndividualPricingStrategyRepo getInstance() {
		if (instance == null)
			instance = new IndividualPricingStrategyRepo();
		
		return instance;
	}
	
	/**
	 * retrieves one strategy from the repository.
	 * @param itemName the name of the item, which is attached to a strategy.
	 * @return one concrete implementation of IndividualPricingStrategy.
	 */
	public IndividualPricingStrategy getStrategy(String itemName) {
		return strategies.getOrDefault(itemName, IndividualPricingStrategyFactory.create("default"));
	}

}
