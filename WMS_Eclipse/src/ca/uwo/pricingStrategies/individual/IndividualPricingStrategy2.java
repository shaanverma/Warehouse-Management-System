package ca.uwo.pricingStrategies.individual;

/**
 * The second individual pricing strategy.
 * Calculates the total cost as the amount times the item's price and applies a 10% discount.
 * @author Group 82
 */
public class IndividualPricingStrategy2 implements IndividualPricingStrategy {
	public double calculate(int quantity, double price) {
		return 0.9 * quantity * price;
	}
}
