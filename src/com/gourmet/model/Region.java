package com.gourmet.model;

import java.util.List;

import com.gourmet.database.gen.RegionTable.RegionColumns;
import com.gourmet.model.interfaces.IEntityObject;

public class Region implements IEntityObject {

	private String description;
	private int id;
	private int idParent;  
	
	private Region region;
	
	private List<Restaurant> rest;
	
	public Region() {
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	@Override
	public void setAttribute(String attrName, Object value) {
			String val = (String) value;
		
		if(attrName.endsWith(RegionColumns.DESCRIPTION))
			setDescription((String) value);
	
		if(attrName.endsWith(RegionColumns._ID)){
			int numVal = Integer.valueOf(val);
			setId(numVal);
			return;
		}
		
		if(attrName.endsWith(RegionColumns.PARENT)){
			int numVal = Integer.valueOf(val);
			setIdParent(numVal);

		}
	
	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof Region){
			setRegion((Region) relatedObj);
		}

	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the idParent
	 */
	public int getIdParent() {
		return idParent;
	}

	/**
	 * @param idParent the idParent to set
	 */
	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the rest
	 */
	public List<Restaurant> getRest() {
		return rest;
	}

	/**
	 * @param rest the rest to set
	 */
	public void setRest(List<Restaurant> rest) {
		this.rest = rest;
	}

}
