/**
 * 
 */
package com.gourmet.database;

import org.dynamicschema.reification.Table;

import com.gourmet.model.interfaces.IEntityObject;

/**
 * Interface provide method for getting the instance of the application-logic associated to a table 
 * This interface should implemented by every class accessing the database using of DynamicSchema
 * 
 * @author esp
 *
 */
public interface IDatabaseEntityAdaptor {
	
	/**
	 * Retrieve the instance of Application-level object corresponding to a database table 
	 * @param table the table for which we need to retrieve an instance of an object
	 * @return An  instance of the application-logic object 
	 */
	IEntityObject getInstanceForEntityType(Table table);

}
