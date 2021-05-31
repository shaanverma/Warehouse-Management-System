package ca.uwo.client;

import java.util.Map;

import ca.uwo.proxies.WelcomeProxy;
import ca.uwo.utils.Invoice;

/**
 * @author kkontog, ktsiouni, mgrigori
 * One concrete implementation of {@link Client} class, who places orders on the stock.
 */
public class Buyer extends Client {
	
	private String userName;

	private String password;
	
	/**
	 * Constructor for Buyer class.
	 * 
	 * @param userName	the buyer's user name
	 * @param password	the buyer's password
	 */
	public Buyer(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Buyer places the order.
	 * @param orderDetails  the name and quantity of each item in the order.
	 */
	public void buy(Map<String, Integer> orderDetails) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("My name is :" + this.userName + " and I'm buying : ");
		System.out.println(orderDetails);
		WelcomeProxy proxy = WelcomeProxy.getInstance();
		proxy.placeOrder(orderDetails, this);
	}
	
	/**
	 * Buyer pays the order according to the invoice.
	 * @param invoice the invoice to pay.
	 */
	public void pay(Invoice invoice) {
		System.out.println(invoice.toString());
	}
	
	/**
	 * @return the user name of the Buyer.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password of the Buyer.
	 */
	public String getPassword() {
		return password;
	}

}
