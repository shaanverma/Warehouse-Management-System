package ca.uwo.viewer.restock.strategies;

/**
 * The first restock strategy.
 * Simple in that it always orders 50 new items.
 * @author Group 82
 */
public class RestockStrategy1 implements RestockStrategy {
	public int calculateQuantity(String itemName, int quantity, double price) {
		return 50;
	}
}
