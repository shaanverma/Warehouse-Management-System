package ca.uwo.controller;

import ca.uwo.utils.Order;

/**
 * @author kkontog, ktsiouni, mgrigori
 * The interface for the {@link Operation}, it is exposed to the Facade class.
 */
public interface OperationInterface {
	/**
	 * this method behaves appropriately depending on different concrete implementations of 
	 * the {@link Operation} class.
	 * @param order the original order on which the operations are performed.
	 * @return the revised order after operations.
	 */
	public Order perform(Order order); 

}
