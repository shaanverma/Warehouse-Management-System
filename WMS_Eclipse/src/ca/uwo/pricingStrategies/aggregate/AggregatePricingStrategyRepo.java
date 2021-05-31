package ca.uwo.pricingStrategies.aggregate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class collects all the concrete implementations of the {@link AggregatePricingStrategy} interface.
 */
public class AggregatePricingStrategyRepo {
private static AggregatePricingStrategyRepo instance = null;
	
	private Map<String, AggregatePricingStrategy> strategies = new HashMap<String, AggregatePricingStrategy>();
	
	/**
	 * constructor for the AggregatePricingStrategyRepo class.
	 * Read the name of the client and corresponding strategy from the file and save them.
	 */
	private AggregatePricingStrategyRepo() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("aggr_pricing_strategy_file")));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineTokens = line.split("\t");
				strategies.put(lineTokens[0], AggregatePricingStrategyFactory.create(lineTokens[1]));
			}
			br.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	/**
	 * there should be only one instance of the AggregatePricingStrategyRepo class.
	 * @return the instance of AggregatePricingStrategyRepo.
	 */
	public static AggregatePricingStrategyRepo getInstance() {
		if (instance == null)
			instance = new AggregatePricingStrategyRepo();
		
		return instance;
	}
	
	/**
	 * retrieves one strategy from the repository.
	 * @param client the person who is attached to the strategy.
	 * @return one concrete implementation of AggregatePricingStrategy.
	 */
	public AggregatePricingStrategy getStrategy(String client) {
		return strategies.getOrDefault(client, AggregatePricingStrategyFactory.create("default"));
	}

}
