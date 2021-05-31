package ca.uwo.model;

import java.util.ArrayList;
import java.util.List;

import ca.uwo.model.item.states.ItemState;
import ca.uwo.model.item.states.ItemStateFactory;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;
import ca.uwo.viewer.Messenger;
import ca.uwo.viewer.StockManager;
import ca.uwo.viewer.Viewer;

/**
 * @author kkontog, ktsiouni, mgrigori This class represents the data objects
 *         (instances of Item class) in the database.
 */
public class Item {
	private int id;
	private String name;
	private int availableQuantity;
	private double price;
	// This is the attribute that references the object's state
	private ItemState state;
	private List<Viewer> viewers;

	/**
	 * constructor for the Item class.
	 * 
	 * @param id
	 *            identifier of the item.
	 * @param name
	 *            name of the item.
	 * @param quantity
	 *            quantity of the item.
	 * @param price
	 *            price of the item.
	 */
	public Item(int id, String name, int quantity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.availableQuantity = quantity;
		this.price = price;
		this.viewers = new ArrayList<Viewer>();
		
		// Adding viewers thus implementing part of the Observer design pattern
		this.viewers.add(StockManager.getInstance());
		this.viewers.add(Messenger.getInstance());

		// Initialize the item with the appropriate state for its quantity
		if (quantity == 0) {
			this.state = ItemStateFactory.create("outOfStock");
		} else if (quantity < 10) {
			this.state = ItemStateFactory.create("lowStock");
		} else {
			this.state = ItemStateFactory.create("inStock");
		}
	}

	/**
	 * @return id of the item.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return name of the item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return available quantity of the item.
	 */
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity
	 *            available quantity of the item in stock.
	 */
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	/**
	 * @return price of the item.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets the state of this item to the given state.
	 * @param state State to set.
	 */
	public void setState(ItemState state) {
		this.state = state;
	}

	/**
	 * deplete the item.
	 * 
	 * @param quantity
	 *            the quantity of item to deplete.
	 * @return execution result of the deplete action.
	 */
	public ItemResult deplete(int quantity) {
		// Deplete the item with quantity and return the execution result of deplete action
		// The current state of the item will handle the deplete action
		return this.state.deplete(this, quantity);
	}

	/**
	 * replenish the item.
	 * 
	 * @param quantity
	 *            the quantity of item to replenish.
	 * @return execution result of the replenish action.
	 */
	public ItemResult replenish(int quantity) {
		// Replenish the item with quantity and return the execution result of replenish action
		// The current state of the item will handle the replenish action
		return this.state.replenish(this, quantity);
	}

	/**
	 * Adds the given viewer to the item's list of viewers.
	 * @param viewer viewer to add
	 */
	public void addViewer(Viewer viewer) {
		this.viewers.add(viewer);
	}
	
	/**
	 * Removes the given viewer from the item's list of viewers.
	 * @param viewer
	 */
	public void removeViewer(Viewer viewer) {
		this.viewers.remove(viewer);
	}
	
	/**
	 * Informs the viewer's of the item.
	 */
	public void notifyViewers() {
		for (Viewer viewer : this.viewers) {
			viewer.inform(this);
		}
	}
}
