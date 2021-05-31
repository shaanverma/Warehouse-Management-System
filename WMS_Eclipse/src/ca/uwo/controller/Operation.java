package ca.uwo.controller;

import java.util.Map;

import ca.uwo.dataAccess.DataManager;
import ca.uwo.model.Item;
import ca.uwo.model.ItemRepository;

/**
 * @author kkontog, ktsiouni, mgrigori
 * The base class of Operation.
 */
public abstract class Operation implements OperationInterface {
	protected ItemRepository itemRepo;
	protected Map<String, Item> items;
	
	protected DataManager dataManager;

}
