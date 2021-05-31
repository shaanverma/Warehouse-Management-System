package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;

/**
 * Implements the Out Of Stock state for items.
 * @author Group 82
 */
public class OutOfStockState implements ItemState {
	@Override
	public ItemResult deplete(Item item, int quantity) {
		// Notify viewers of a deplete operation on the item
		item.notifyViewers();
		
		// No transaction an proceed if the item is out of stock
		return new ItemResult("Out Of Stock", ResponseCode.Not_Completed);
	}
	
	@Override
	public ItemResult replenish(Item item, int quantity) {
		// Update the item's available quantity
		int available = item.getAvailableQuantity();
		available += quantity;
		item.setAvailableQuantity(available);
		
		// Change the item's state to in stock if at least 10 are available now
		// Otherwise, change to low in stock for less than 10
		if (available >= 10) {
			item.setState(ItemStateFactory.create("inStock"));
		} else {
			item.setState(ItemStateFactory.create("lowStock"));
		}
		
		// The stock was successfully replenished
		return new ItemResult("Restocked", ResponseCode.Completed);
	}
}
