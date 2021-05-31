package ca.uwo.controller;

import ca.uwo.model.ItemRepository;
import ca.uwo.utils.Order;
import ca.uwo.utils.OrderItem;

/**
 * @author kkontog, ktsiouni, mgrigori
 * One concrete implementation of {@link Operation} class, which is responsible for depleting the stock.
 */
public class DepleteStockOperation extends Operation {

	/* (non-Javadoc)
	 * @see ca.uwo.controller.OperationInterface#perform(ca.uwo.utils.Order)
	 */
	@Override
	public Order perform(Order anOrder) {
		itemRepo = ItemRepository.getInstance();
		
		for(OrderItem orderItem : anOrder.getOrderItems()) {
			itemRepo.depleteItemStock(orderItem);
		}
		
		return anOrder;
	}

}
