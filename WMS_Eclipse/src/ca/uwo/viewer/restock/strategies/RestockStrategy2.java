package ca.uwo.viewer.restock.strategies;

/**
 * The second restock strategy.
 * Always restocks apples by 100, for all other items always restocks by 25.
 * @author Group 82
 */
public class RestockStrategy2 implements RestockStrategy {
	public int calculateQuantity(String itemName, int quantity, double price) {
		//  Always restock apples with 100 new items
		if (itemName == "apples") {
			return 100;
		}
		
		// For all other items, restock with 25 new items
		return 25;
	}
}
