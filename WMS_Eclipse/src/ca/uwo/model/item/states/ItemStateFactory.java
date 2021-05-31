package ca.uwo.model.item.states;

/**
 * Creates and returns different item states based on the given type.
 * @author Group 82
 */
public class ItemStateFactory {
	/**
	 * Returns an item state based on the given type.
	 * @param type type of state to create
	 * @return created state object
	 */
	public static ItemState create(String type) {
		switch (type) {
			case "lowStock": {
				return (ItemState) new LowStockState();
			}
			case "outOfStock": {
				return (ItemState) new OutOfStockState();
			}
			case "inStock": {
				return (ItemState) new InStockState();
			}
		}
		
		// default to in stock state
		return (ItemState) new InStockState();
	}
}
