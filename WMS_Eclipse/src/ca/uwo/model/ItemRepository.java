package ca.uwo.model;

import java.util.HashMap;
import java.util.Map;

import ca.uwo.controller.Controller;
import ca.uwo.dataAccess.DataManager;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.OrderItem;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class represents a collection of {@link ca.uwo.model.Item} in the database.
 */
public class ItemRepository {
	private static ItemRepository instance = null;
	
	private Map<String, Item> savedItems;
	private DataManager dataManager;
	
	public static ItemRepository getInstance() {
		if (instance == null)
			instance = new ItemRepository();
		
		return instance;
	}

	/**
	 * update the quantity of item in the database for the deplete operation.
	 * @param orderItem one entry in the order.
	 */
	public void depleteItemStock(OrderItem orderItem) {
		//First try to get the item from savedItems using the name of the item, if not there,
		//get the item from the database. Then deplete the item with correct quantity and update the 
		//database. You should also update orderItem accordingly.
		System.out.println("ItemRepo: depleting " + orderItem.getItemName());
		String itemName = orderItem.getItemName();
		Item chosenItem = savedItems.get(itemName);
		
		if (chosenItem == null)
			chosenItem = dataManager.getItem(itemName);
			
		System.out.println("ItemRepo: Stock before Action: " + chosenItem.getAvailableQuantity());
		ItemResult itemResult = chosenItem.deplete(orderItem.getQuantity());
		savedItems.put(itemName, chosenItem);
		dataManager.updateItem(chosenItem);
		orderItem.setItemResult(itemResult);
		orderItem.setPrice(chosenItem.getPrice());
	}

	/**
	 * update the quantity of item in the database for the replenish operation.
	 * @param orderItem one entry in the order.
	 */
	public void replenishItemStock(OrderItem orderItem) {
		//First try to get the item from savedItems using the name of the item, if not there,
		//get the item from the database. Then replenish the item with correct quantity and update the 
		//database. You should also update orderItem accordingly.
		System.out.println("ItemRepo: replenishing " + orderItem.getItemName());
		String itemName = orderItem.getItemName();
		Item chosenItem = savedItems.get(itemName);
		
		if (chosenItem == null)
			chosenItem = dataManager.getItem(itemName);
		
		System.out.println("ItemRepo: Stock before Action: " + chosenItem.getAvailableQuantity());
		ItemResult itemResult = chosenItem.replenish(orderItem.getQuantity());
		savedItems.put(itemName, chosenItem);
		dataManager.updateItem(chosenItem);
		orderItem.setItemResult(itemResult);
	}

	/**
	 * constructor for ItemRepository class.
	 */
	private ItemRepository() {
		super();
		dataManager = DataManager.getInstance();
		savedItems = new HashMap<>();
	}
}
