package ca.uwo.pricingStrategies.aggregate;

import java.util.Map;

/**
 * @author kkontog, ktsiouni, mgrigori
 * The interface for the implementation of Strategy design pattern, which calculates the 
 * total price for an order using different strategies.  
 */
public interface AggregatePricingStrategy {

	/**
	 * this method is used to calculate the total price on the invoice of the order.
	 * @param priceBreakdown the price of each item in the order.
	 * @return total price on the invoice 
	 */
	public double calculateTotal(Map<String, Double> priceBreakdown);

}
