package ca.uwo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class represents an order which is placed by the {@link ca.uwo.client.Client}.
 */
public class Order {
	private String client;
	private Invoice invoice = null;
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	/**
	 * retrieve all items in the order.
	 * @return list of orderItems.
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * retrieve the invoice for the order.
	 * @return invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}



	/**
	 * set the invoice for the order.
	 * @param invoice
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * add an item to the order.
	 * @param anOrderItem
	 */
	public void addOrderItem(OrderItem anOrderItem) {
		orderItems.add(anOrderItem);
	}

}
