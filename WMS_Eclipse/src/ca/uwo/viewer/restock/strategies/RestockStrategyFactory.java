package ca.uwo.viewer.restock.strategies;

/**
 * Creates and returns different restock strategies based on the given type.
 * @author Group 82
 */
public class RestockStrategyFactory {
	/**
	 * Returns a restock strategy based on the given type.
	 * @param type type of restock strategy to create
	 * @return created restock strategy object
	 */
	public static RestockStrategy create(String type) {
		switch (type) {
			case "strategy1": {
				return (RestockStrategy) new RestockStrategy1();
			}
			case "strategy2": {
				return (RestockStrategy) new RestockStrategy2();
			}
			case "50units": {
				return (RestockStrategy) new Units50RestockStrategy();
			}
			case "weird": {
				return (RestockStrategy) new WeirdRestockStrategy();
			}
		}
		
		// default to 50 units restock strategy
		return (RestockStrategy) new Units50RestockStrategy();
	}
}
