package ca.uwo.viewer;

import ca.uwo.model.Item;

/**
 * @author kkontog, ktsiouni, mgrigori
 * One concrete implementation of the {@link Viewer} class. 
 */
public class Messenger extends Viewer {
	private static Messenger instance = null;
	
	public static Messenger getInstance() {
		if (instance == null)
			instance = new Messenger();
		
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see ca.uwo.viewer.Viewer#inform(ca.uwo.model.Item)
	 */
	@Override
	public void inform(Item item) {
		System.out.println("Notify department that item run out of stock: " + item.getName());
	}

}
