package ca.uwo.pricingStrategies.aggregate;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class is responsible for creating different strategies (using Factory design pattern) 
 * which implement {@link AggregatePricingStrategy} interface based on different contexts.
 */
public class AggregatePricingStrategyFactory {

	/**
	 * create strategy for the total price calculation.
	 * @param type each type is attached to one strategy.
	 * @return one concrete implementation of {@link AggregatePricingStrategy}.
	 */
	public static AggregatePricingStrategy create(String type) {
		switch(type) {
		case "test":
			return new TestAggregatePricingStrategy();
		default:
			return new AggregateDefaultPricingStrategy();
		}
		
	}
	
}
