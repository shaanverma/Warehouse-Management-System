package ca.uwo.banking;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.utils.Invoice;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class simulates payments after transactions. 
 */
public class BankingTransactions {
	
	/**
	 * receive payment from the buyer after placing the order.
	 * @param invoice  the invoice to pay.
	 * @param buyer  the buyer makes the payment.
	 */
	public void receivePayment(Invoice invoice, Buyer buyer) {
		System.out.println("Waiting for payment from the buyer...");
		buyer.pay(invoice);
	}
	
	/**
	 * pay the supplier after restocking the supplies.
	 * @param supplier  the supplier gets paid.
	 */
	public void paySupplier(Supplier supplier) {
		System.out.println("Supplier paid.");
	}

}
