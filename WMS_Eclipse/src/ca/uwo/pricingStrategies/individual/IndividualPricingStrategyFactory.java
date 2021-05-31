package ca.uwo.pricingStrategies.individual;

/**
 * Creates and returns different individual pricing strategies based on the given type.
 * @author Group 82
 */
public class IndividualPricingStrategyFactory {
	/**
	 * Returns an individual pricing strategy based on the given type.
	 * @param type type of strategy to create
	 * @return created strategy object
	 */
	public static IndividualPricingStrategy create(String type) {
		switch (type) {
			case "strategy1": {
				return (IndividualPricingStrategy) new IndividualPricingStrategy1();
			}
			case "strategy2": {
				return (IndividualPricingStrategy) new IndividualPricingStrategy2();
			}
			default:
				return (IndividualPricingStrategy) new IndividualDefaultPricingStrategy();
		}
	}
}
