package ca.uwo.client;

import java.util.Map;

import ca.uwo.proxies.WelcomeProxy;

/**
 * @author kkontog, ktsiouni, mgrigori
 * One concrete implementation of {@link Client} class, who restocks the supplies.
 */
public class Supplier extends Client {
	
	/**
	 * supplier restocks the supplies.
	 * @param restockDetails  the name and quantity of each restocked item.
	 */
	public void supply(Map<String, Integer> restockDetails) {
		WelcomeProxy proxy = WelcomeProxy.getInstance();
		proxy.restock(restockDetails,this);
	}
}
