package ca.uwo.viewer.restock.strategies;

/**
 * Implements a simple restock strategy of always ordering 50 new items.
 * @author Group 82
 */
public class Units50RestockStrategy implements RestockStrategy {
	public int calculateQuantity(String itemName, int quantity, double price) {
		return 50;
	}
}
