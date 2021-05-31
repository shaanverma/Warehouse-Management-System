package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;
import ca.uwo.frontend.interfaces.FacadeCommands;

public class SupplierProxy extends Proxy {
	private static SupplierProxy instance = null;
		
	//Attribute that stores the next Proxy in the chain
	private LowQuantityProxy next = LowQuantityProxy.getInstance();
		
	//Constructor, private so that this class cannot be instantiated
	private SupplierProxy() {}
		
	//Get the only object available
	public static SupplierProxy getInstance() {
		if (instance == null)
			instance = new SupplierProxy();
		
		return instance;
	}
	
	//Chains to the next proxy
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer)
	{
		next.placeOrder(orderDetails, buyer);
	}

	//Sends restocking info to the facade
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) 
	{
		Facade facade = Facade.getInstance();
		facade.restock(restockDetails, supplier);
		
	}
}
