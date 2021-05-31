package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;

/**
 * Implements the In Stock state for items.
 * @author Group 82
 */
public class InStockState implements ItemState {
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
		// If less than 10 are available, the item is low on stock
		if (available == 0) {
			item.setState(ItemStateFactory.create("outOfStock"));
		} else if (available < 10) {
			item.setState(ItemStateFactory.create("lowStock"));
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
		item.setAvailableQuantity(available + quantity);
		
		// The stock was successfully replenished
		return new ItemResult("Restocked", ResponseCode.Completed);
	}
}
