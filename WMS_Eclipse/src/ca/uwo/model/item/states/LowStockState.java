package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;

/**
 * Implements the Low Stock state for items.
 * @author Group 82
 */
public class LowStockState implements ItemState {
	@Override
	public ItemResult deplete(Item item, int quantity) {
		// Notify viewers of a deplete operation on the item
		item.notifyViewers();
		
		// Get the available quantity of the item
		int available = item.getAvailableQuantity();
		
		// If enough of the item is not available, the transaction cannot proceed
		if (available < quantity) {
			return new ItemResult("Not Enough In Stock", ResponseCode.Not_Completed);
		}
		
		// Decrease the available quantity by the order amount
		available -= quantity;
		
		// If no more items are available, it is now out of stock
		if (available == 0) {
			item.setState(ItemStateFactory.create("outOfStock"));
		}
		
		// Update the item's available quantity
		item.setAvailableQuantity(available);
		
		// The stock was successfully depleted
		return new ItemResult("Available", ResponseCode.Completed);
	}
	
	@Override
	public ItemResult replenish(Item item, int quantity) {
		// Update the item's available quantity
		int available = item.getAvailableQuantity();
		available += quantity;
		item.setAvailableQuantity(available);
		
		// Change the item's state to in stock if at least 10 are available now
		if (available >= 10) {
			item.setState(ItemStateFactory.create("inStock"));
		}
		
		// The stock was successfully replenished
		return new ItemResult("Restocked", ResponseCode.Completed);
	}
}
