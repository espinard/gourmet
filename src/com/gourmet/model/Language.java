/**
 * 
 */
package com.gourmet.model;

import java.util.List;

import com.gourmet.database.gen.LanguageTable.LanguageColumns;
import com.gourmet.model.interfaces.IEntityObject;

/**
 * @author esps
 *
 */
public class Language implements IEntityObject {
	
	


	private String name;
	private int id;
	
	/**
	 * @param name
	 */
	public Language(int id) {
		this.id = id;
	}
	

	/**
	 * 
	 */
	public Language() {
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public void setAttribute(String name, Object value) {
		if(name.endsWith(LanguageColumns._ID))
			setId(Integer.valueOf((String)value));
		if(name.endsWith(LanguageColumns.DESCRIPTION))
			setName((String) value);
	}


	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		throw new RuntimeException("Not implemented	");
		
	}

}
