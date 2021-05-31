package ca.uwo.pricingStrategies.individual;

/**
 * @author kkontog, ktsiouni, mgrigori
 * The interface for the implementation of Strategy design pattern, which calculates the 
 * price for an item of the order.
 */
public interface IndividualPricingStrategy {
	
	/**
	 * this method is used to calculate the price for the item.
	 * @param quantity the quantity of the item.
	 * @param price the price of one unit of the item.
	 * @return the price of the item
	 */
	public double calculate(int quantity, double price);

}
