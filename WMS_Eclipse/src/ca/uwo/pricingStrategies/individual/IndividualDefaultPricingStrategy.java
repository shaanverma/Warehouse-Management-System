package ca.uwo.pricingStrategies.individual;

/**
 * The default individual pricing strategy.
 * Simply calculates the total cost as the amount times the item's price.
 * @author Group 82
 */
public class IndividualDefaultPricingStrategy implements IndividualPricingStrategy {
	public double calculate(int quantity, double price) {
		return quantity * price;
	}
}
