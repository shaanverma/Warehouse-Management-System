package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;
import java.io.FileReader;
import java.io.FileReader;
import java.io.BufferedReader;

public class LowQuantityProxy extends Proxy {
	private static LowQuantityProxy instance = null;
		
	//Attribute that stores the next proxy in the chain
	private HighQuantityProxy next = HighQuantityProxy.getInstance();
		
	//Constructor, private so that this class cannot be instantiated
	private LowQuantityProxy() {}
		
	//Get the only object available
	public static LowQuantityProxy getInstance() {
		if (instance == null)
			instance = new LowQuantityProxy();
		
		return instance;
	}
	
	//Chains to the next proxy if more than 10 items. Otherwise sends to facade
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		// Chain to high quantity proxy
		if (orderDetails.size() > 10) {
			next.placeOrder(orderDetails, buyer);
		} else {
			if (this.authenticate(buyer)) {
				Facade facade = Facade.getInstance();
				facade.placeOrder(orderDetails, buyer);
			} else {
				System.out.println("Unauthorized User");
			};
		}
	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		// TODO Auto-generated method stub
	}
	
	// Authenticates the given buyer against the user input
	private boolean authenticate(Buyer buyer) {
		try (BufferedReader reader = new BufferedReader(new FileReader("user_input_file"))) {
			String line;
			String userName = buyer.getUserName();
			String password = buyer.getPassword();
			
			while ((line = reader.readLine()) != null) {
				String[] splitted = line.split(" ");
				
				if (userName.equals(splitted[0]) && password.equals(splitted[1])) {
					return true;
				}
			}
			
			return false;
		} catch (Exception e) {
			System.out.println("Failed to read user input.");
			return false;
		}
	}
}