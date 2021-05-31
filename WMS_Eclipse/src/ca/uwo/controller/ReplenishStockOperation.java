package ca.uwo.controller;

import ca.uwo.model.ItemRepository;
import ca.uwo.utils.Order;
import ca.uwo.utils.OrderItem;

/**
 * @author kkontog, ktsiouni, mgrigori
 * One concrete implementation of {@link Operation} base class, which is responsible for replenishing the stock.
 */
public class ReplenishStockOperation extends Operation {

	/* (non-Javadoc)
	 * @see ca.uwo.controller.OperationInterface#perform(ca.uwo.utils.Order)
	 */
	@Override
	public Order perform(Order anOrder) {
		itemRepo = ItemRepository.getInstance();
		
		for(OrderItem orderItem : anOrder.getOrderItems()) {
			itemRepo.replenishItemStock(orderItem);
		}
		
		return anOrder;
	}

}
