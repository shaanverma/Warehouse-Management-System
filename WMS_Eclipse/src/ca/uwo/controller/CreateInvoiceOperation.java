package ca.uwo.controller;

import ca.uwo.utils.Invoice;
import ca.uwo.utils.Order;
import ca.uwo.utils.OrderItem;
import ca.uwo.utils.ResponseCode;

/**
 * @author kkontog, ktsiouni, mgrigori
 * One concrete implementation of {@link Operation} class, which is responsible for issuing the invoice for the Order.
 */
public class CreateInvoiceOperation extends Operation {

	/* (non-Javadoc)
	 * @see ca.uwo.controller.OperationInterface#perform(ca.uwo.utils.Order)
	 */
	@Override
	public Order perform(Order order) {
		Invoice invoice = new Invoice(order.getClient());
		
		for (OrderItem orderItem : order.getOrderItems()) {
			ResponseCode itemResponseCode = orderItem.getItemResult().getResponseCode();
			if (itemResponseCode.equals(ResponseCode.Completed))
				invoice.addItem(orderItem);
		}
		
		invoice.calculateInvoiceTotal();
		order.setInvoice(invoice);
		return order;
	}


}
