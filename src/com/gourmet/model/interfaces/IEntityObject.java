package com.gourmet.model.interfaces;

public interface IEntityObject {
	/**
	 * Every main entity should have an id 
	 * Assign the id of the object 
	 * @param id the id to assign
	 */
	void setId(int id);
	/**
	 * Assign values to the attributes of the entity
	 * @param name name of attribute
	 * @param value its value
	 */
	void setAttribute(String name, Object value);
	
	/**
	 * Register entity objects association 
	 * @param relatedObj entity object related to the current one
	 */
	void setAssociatedObject(IEntityObject relatedObj);

}
