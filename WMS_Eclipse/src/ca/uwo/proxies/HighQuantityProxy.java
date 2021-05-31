package ca.uwo.proxies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.controller.Controller;
import ca.uwo.frontend.Facade;

public class HighQuantityProxy extends Proxy {
	private static HighQuantityProxy instance = null;
	
	//Constructor, private so that this class cannot be instantiated
	private HighQuantityProxy() {}
		
	//Get the only object available
	public static HighQuantityProxy getInstance() {
		if (instance == null)
			instance = new HighQuantityProxy();
		
		return instance;
	}

	//Sends order to facade
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		if (this.authenticate(buyer)) {
			Facade facade = Facade.getInstance();
			facade.placeOrder(orderDetails, buyer);
		} else {
			System.out.println("Unauthorized User");
		};
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
