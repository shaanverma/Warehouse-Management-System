package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;

/**
 * @author kkontog, ktsiouni, mgrigori
 * The interface for the implementation of the state of the item, which contributes to the 
 * State design pattern. Based on different states, the deplete and replenish actions behave 
 * differently. On the other hand, actions may also alter the states of the item.  
 */
public interface ItemState {

	/**
	 * this method behaves appropriately depending on different states of the Item.
	 * @param item the item to deplete.
	 * @param quantity the quantity of the item to deplete.
	 * @return execution result of the deplete action.
	 */
	ItemResult deplete(Item item, int quantity);

	/**
	 * this method behaves appropriately depending on different states of the Item.
	 * @param item the item to replenish.
	 * @param quantity the quantity of the item to replenish.
	 * @return execution result of the replenish action.
	 */
	ItemResult replenish(Item item, int quantity);

}
