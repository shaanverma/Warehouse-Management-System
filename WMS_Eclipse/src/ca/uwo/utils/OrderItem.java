package ca.uwo.utils;

import ca.uwo.pricingStrategies.individual.IndividualPricingStrategy;
import ca.uwo.pricingStrategies.individual.IndividualPricingStrategyRepo;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class represents one entry in the {@link Order}.
 */
public class OrderItem {
	private String itemName;
	private int quantity;
	private double price;
	private ItemResult itemResult;
	// This is the attribute to reference the strategy attached
	private IndividualPricingStrategy pricingStrategy;

	/**
	 * retrieve the name of the item.
	 * @return itemName the name of the item.
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * retrieve the quantity of the item.
	 * @return quantity the quantity of the item.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * retrieve the price of the item.
	 * @return price the price of the item.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * set the price of the item.
	 * @param price the price to be set.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * retrieve the execution result of actions for the item.
	 * @return itemResult execution result.
	 */
	public ItemResult getItemResult() {
		return itemResult;
	}

	/**
	 * set the execution result of actions for the item.
	 * @param itemResult execution result to be set.
	 */
	public void setItemResult(ItemResult itemResult) {
		this.itemResult = itemResult;
	}

	/**
	 * constructor for the OrderItem class.
	 * @param itemName the name of the item.
	 * @param quantity the quantity of the item.
	 */
	public OrderItem(String itemName, int quantity) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = -1.0;
		this.itemResult = null;
		this.pricingStrategy = IndividualPricingStrategyRepo.getInstance().getStrategy(itemName);
	}

	/**
	 * calculate the price for the item.
	 * @return itemPrice the price of the item.
	 */
	public double calculateItemPrice() {
		return this.pricingStrategy.calculate(this.quantity, this.price);
	}

}
