package ca.uwo.viewer;

import java.util.HashMap;
import java.util.Map;

import ca.uwo.client.Supplier;
import ca.uwo.model.Item;
import ca.uwo.viewer.restock.strategies.RestockStrategy;
import ca.uwo.viewer.restock.strategies.RestockStrategyFactory;

/**
 * @author kkontog, ktsiouni, mgrigori
 * One concrete implementation of the {@link Viewer} class, who is responsible for restocking the supplies 
 * when receiving the notification.
 */
public class StockManager extends Viewer implements Runnable {
	private static StockManager instance = null;
	
	// This is the attribute to reference the strategy attached
	private RestockStrategy restockStrategy = RestockStrategyFactory.create("default");
	private Map<String, Integer> restockDetails = new HashMap<String, Integer>();

	public static StockManager getInstance() {
		if (instance == null)
			instance = new StockManager();
		
		return instance;
	}
	
	/**
	 * constructor for the StockManager class.
	 */
	private StockManager() {
		super();
		// restockDetails.put("apple", 50);
		// restockDetails.put("pear", 50);
		// restockDetails.put("mango", 50);
		// restockDetails.put("onions", 50);
		Thread t = new Thread(this);
		t.start();
	}
	
	/* (non-Javadoc)
	 * @see ca.uwo.viewer.Viewer#inform(ca.uwo.model.Item)
	 */
	@Override
	public void inform(Item item) {
		String itemName = item.getName();
		int quantity = item.getAvailableQuantity();
		double price = item.getPrice();
		
		// Use the attached restock strategy to calculate the quantity to restock by
		int restockQuantity = this.restockStrategy.calculateQuantity(itemName, quantity, price);
		
		// Update the restock details map with the calculated restock quantity
		this.restockDetails.put(itemName, restockQuantity);
	}

	// TODO make concurrent
	/**
	 * restock the Item from the Supplier.
	 */
	public void order() {
		System.out.println("restocked with " + restockDetails);
		Supplier supplier = new Supplier();
		supplier.supply(restockDetails);
		restockDetails.clear();
	}

	/**
	 * set restock strategy for the Item.
	 * @param restockStrategy the restock strategy to be set.
	 */
	public void setRestockStrategy(RestockStrategy restockStrategy) {
		System.out.println("Restock strategy changed to: " + restockStrategy.toString());
		this.restockStrategy = restockStrategy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			System.out.println("Stockmanager looking for potential orders...");
			if (!restockDetails.isEmpty())
				order();

			System.out.println("Wait for orders to accumulate...");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
