package ca.uwo.viewer.restock.strategies;

/**
 * Implements a restock strategy that always orders 500 new apples and for
 * other items calculates the amount based on the current quantity and price.
 * @author Group 82
 */
public class WeirdRestockStrategy implements RestockStrategy {
	public int calculateQuantity(String itemName, int quantity, double price) {
		//  Always restock apples with 500 new items
		if (itemName == "apples") {
			return 500;
		}
		
		// From the project outline: "with so many items as twice the product of
		// remaining quantity+1 multiplied by the unit price of the item"
		return (int) Math.round(2 * ((quantity + 1) * price));
	}
}
