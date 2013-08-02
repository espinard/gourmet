package com.gourmet.model;

import com.gourmet.database.gen.DescriptionTable.DescriptionColumns;
import com.gourmet.model.interfaces.IEntityObject;

public class MealDescription implements IEntityObject{

	private int id_meal;
	private int id_lang;
	private String description;
	
	private Language langObj; 
	private Meal  mealObj;
	private int id;
	
	
	
	public MealDescription() {
	}

	@Override
	public void setId(int id) {
		this.id = id;
		
	}

	@Override
	public void setAttribute(String name, Object value) {
		if(name.endsWith(DescriptionColumns.DESCRIPTION))
				setDescription((String) value);
		
		if(name.endsWith(DescriptionColumns.ID_LANGUAGE))
			setId_lang(Integer.valueOf((String) value));
		
		if(name.endsWith(DescriptionColumns.ID_MEAL))
			setId_meal(Integer.valueOf((String) value));	

	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		
		if(relatedObj instanceof Language){
			setLangObj((Language) relatedObj);
		}
		if(relatedObj instanceof Meal) {
			setMealObj((Meal) relatedObj);
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
		return this.id;
	}

	/**
	 * @return the id_meal
	 */
	public int getId_meal() {
		return id_meal;
	}

	/**
	 * @param id_meal the id_meal to set
	 */
	public void setId_meal(int id_meal) {
		this.id_meal = id_meal;
	}

	/**
	 * @return the id_lang
	 */
	public int getId_lang() {
		return id_lang;
	}

	/**
	 * @param id_lang the id_lang to set
	 */
	public void setId_lang(int id_lang) {
		this.id_lang = id_lang;
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
	 * @return the mealObj
	 */
	public Meal getMealObj() {
		return mealObj;
	}

	/**
	 * @param mealObj the mealObj to set
	 */
	public void setMealObj(Meal mealObj) {
		this.mealObj = mealObj;
	}

}
