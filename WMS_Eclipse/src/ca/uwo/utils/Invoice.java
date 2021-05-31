package ca.uwo.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ca.uwo.pricingStrategies.aggregate.AggregatePricingStrategy;
import ca.uwo.pricingStrategies.aggregate.AggregatePricingStrategyRepo;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class represents the invoice for the {@link Order}.
 */
public class Invoice {
	private String client;
	private double amountToBePaid = 0.0;
	private Map<String, Double> priceBreakdown = new HashMap<String, Double>();
	private AggregatePricingStrategy totalPricingStrategy;
	
	
	/**
	 * constructor for the Invoice class.
	 * @param client the person who pays for the invoice.
	 */
	public Invoice(String client) {
		this.client = client;
		totalPricingStrategy = AggregatePricingStrategyRepo.getInstance().getStrategy(this.client);
	}

	/**
	 * calculate the total price of the Invoice.
	 */
	public void calculateInvoiceTotal() {
		amountToBePaid = totalPricingStrategy.calculateTotal(priceBreakdown);	
	}
	
	/**
	 * add an orderItem to the Invoice.
	 * @param orderItem one entry of the order.
	 */
	public void addItem(OrderItem orderItem) {
		priceBreakdown.put(orderItem.getItemName(), orderItem.calculateItemPrice());
	}

	/**
	 * retrieve the total price of the Invoice.
	 * @return the amount of the total price.
	 */
	public double getAmountToBePaid() {
		return amountToBePaid;
	}

	/**
	 * set AggregatePricingStrategy for the Invoice for calculating the total price.
	 * @param totalPricingStrategy the strategy for calculating the total price.
	 */
	public void setTotalPricingStrategy(AggregatePricingStrategy totalPricingStrategy) {
		this.totalPricingStrategy = totalPricingStrategy;
	}

	/**
	 * retrieve the client of the Invoice.
	 * @return the client.
	 */
	public String getClient() {
		return client;
	}

	/**
	 * set the client of the Invoice.
	 * @param client the person who pays for the invoice.
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder invoiceText = new StringBuilder("Invoice\n");
		invoiceText.append("------------------\n");
		for (Entry<String,Double> item : priceBreakdown.entrySet()) {
			invoiceText.append("\t"+item.getKey()+"\t"+item.getValue()+"\n");
		}
		invoiceText.append("Total amount after specials: "+amountToBePaid);
		return invoiceText.toString();
	}

	
}
