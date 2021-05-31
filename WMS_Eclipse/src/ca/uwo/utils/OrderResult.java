package ca.uwo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class represents the status of the order as well as each item in the order.
 */
public class OrderResult {
	private List<ItemResult> itemResults;
	private ResponseCode responseCode;

	/**
	 * constructor for the OrderResult class.
	 */
	public OrderResult() {
		itemResults = new ArrayList<ItemResult>();
	}
	
	/**
	 * add an itemResult to the order. 
	 * @param itemResult the itemResult to add.
	 */
	public void addItemResult(ItemResult itemResult) {
		itemResults.add(itemResult);
		
	}

	/**
	 * get the status of all items in the order.
	 * @return list of itemResults.
	 */
	public List<ItemResult> getMessages() {
		return itemResults;
	}

	/**
	 * retrieve the response code of the order.
	 * @return responseCode response code of the order.
	 */
	public ResponseCode getResponseCode() {
		return responseCode;
	}
	

}
