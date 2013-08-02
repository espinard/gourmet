package com.gourmet.model;

import com.gourmet.database.gen.IngredientTable.IngredientColumns;
import com.gourmet.model.interfaces.IEntityObject;

public class Ingredient implements IEntityObject{
	
	private int id;
	private String unit;
	private String origin;
	private String expirationDate;
	
	private String description;
	private IngredientDescription objDescription;

	public Ingredient()  {
	}

	@Override
	public void setId(int id) {
		this.id = id;
		
	}

	@Override
	public void setAttribute(String attrName, Object value) {
		
		String val = (String) value;
		int numVal;
		
		try {
			numVal = Integer.valueOf(val);
		} catch (NumberFormatException e) {
			numVal = -1;
		}
		
		
		if(attrName.endsWith(IngredientColumns._ID))
			setId(numVal);
		
		
		if(attrName.endsWith(IngredientColumns.UNIT))
			setUnit((String) value);
		
		if(attrName.endsWith(IngredientColumns.ORIGIN))
			setOrigin((String) value);
		
		if(attrName.endsWith(IngredientColumns.EXPIRATION_DATE))
			setExpirationDate((String) value);
		
	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof IngredientDescription){
			setObjDescription((IngredientDescription) relatedObj);
			setDescription(getObjDescription().getDescription());
		}
		
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * @return the objDescription
	 */
	public IngredientDescription getObjDescription() {
		return objDescription;
	}

	/**
	 * @param objDescription the objDescription to set
	 */
	public void setObjDescription(IngredientDescription objDescription) {
		this.objDescription = objDescription;
	}

}
