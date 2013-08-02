package com.gourmet.model;

import com.gourmet.database.gen.Description2Table.Description2Columns;
import com.gourmet.model.interfaces.IEntityObject;

public class IngredientDescription implements IEntityObject {

	private int id;
	private String description;
	private int idIngredient;
	private int idLang;
	
	private Language langObj;
	private Ingredient ingrObj;
	
	public IngredientDescription() {

	}

	@Override
	public void setId(int id) {
		this.id = id;
		
	}

	@Override
	public void setAttribute(String name, Object value) {
		String val = (String) value;
		int numVal;
		
		try {
			numVal =  Integer.valueOf(val);
		} catch (NumberFormatException e) {
			numVal = - 1;
		}
		
		
		if(name.endsWith(Description2Columns.ID_INGREDIENT))
			setIdIngredient(numVal);
		if(name.endsWith(Description2Columns.ID_LANGUAGE))
			setIdLang(numVal);
		if(name.endsWith(Description2Columns.DESCRIPTION))
			setDescription(val);
		
	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof Ingredient){
			setIngrObj((Ingredient) relatedObj);
		}
		if(relatedObj instanceof Language){
			setLangObj((Language) relatedObj);
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
	 * @return the idIngredient
	 */
	public int getIdIngredient() {
		return idIngredient;
	}

	/**
	 * @param idIngredient the idIngredient to set
	 */
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	/**
	 * @return the idLang
	 */
	public int getIdLang() {
		return idLang;
	}

	/**
	 * @param idLang the idLang to set
	 */
	public void setIdLang(int idLang) {
		this.idLang = idLang;
	}

	/**
	 * @return the langObj
	 */
	public Language getLangObj() {
		return langObj;
	}

	/**
	 * @param langObj the langObj to set
	 */
	public void setLangObj(Language langObj) {
		this.langObj = langObj;
	}

	/**
	 * @return the ingrObj
	 */
	public Ingredient getIngrObj() {
		return ingrObj;
	}

	/**
	 * @param ingrObj the ingrObj to set
	 */
	public void setIngrObj(Ingredient ingrObj) {
		this.ingrObj = ingrObj;
	}

}
