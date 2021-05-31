package ca.uwo.utils;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class represents the execution result of the deplete and replenish actions for an item.
 */
public class ItemResult {
	
	private String itemName;
	private double price;
	private String message;
	private ResponseCode responseCode;
	
	/**
	 * constructor for the ItemResult class.
	 * @param message  status of the Item.
	 * @param responseCode  execution result of the deplete or replenish actions.
	 */
	public ItemResult(String message, ResponseCode responseCode) {
		super();
		this.message = message;
		this.responseCode = responseCode;
	}

	/**
	 * retrieve the status of the item.
	 * @return message status of the item.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * retrieve the execution result of actions.
	 * @return responseCode execution result of the deplete or replenish actions.
	 */
	public ResponseCode getResponseCode() {
		return responseCode;
	}

	/**
	 * retrieve the itemName.
	 * @return itemName the name of the item.
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * retrieve the price of the item.
	 * @return price the price of the item.
	 */
	public double getPrice() {
		return price;
	}

}
