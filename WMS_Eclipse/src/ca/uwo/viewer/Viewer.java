package ca.uwo.viewer;

import ca.uwo.model.Item;

/**
 * @author kkontog, ktsiouni, mgrigori
 * The base class of Viewer for the implementation of Observer design pattern. The observers are notified 
 * when the item is out of stock or low stock.
 */
public abstract class Viewer {

	public abstract void inform(Item item);

}
