package ca.uwo.frontend.interfaces;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This interface is exposed to the {@link ca.uwo.proxies.Proxy}. 
 */
public interface FacadeCommands {
	
	/** 
	 * this method is invoked by the appropriate proxy, it encapsulates authentication and 
	 * implementation details.
	 * @param orderDetails the name and quantity of each item in the order. 
	 * @param buyer the person who places the order.
	 */
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer); 
	
	/**
	 * this method is invoked by appropriate proxy, the implementation is encapsulated in the 
	 * Facade class. 
	 * @param restockDetails the name and quantity of each item for the restocking.
	 * @param supplier the person who restocks the supplies.
	 */
	public void restock(Map<String, Integer> restockDetails, Supplier supplier);
	
}
