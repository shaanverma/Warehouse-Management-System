package ca.uwo.viewer.restock.strategies;

/**
 * @author kkontog, ktsiouni, mgrigori
 * The interface for the implementation of Strategy design pattern, which calculates the 
 * restock quantity of the item.
 */
public interface RestockStrategy {
	
	/**
	 * the calculateQuantity is used to determine the restock quantity for the item.
	 * @param itemName the name of the item.
	 * @param quantity the quantity of the item.
	 * @param price the price of the item.
	 * @return the restock quantity 
	 */
	public int calculateQuantity(String itemName, int quantity, double price);

}
