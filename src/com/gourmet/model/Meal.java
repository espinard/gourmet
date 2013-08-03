/**
 * 
 */
package com.gourmet.model;

import com.gourmet.database.gen.MealTable.MealColumns;
import com.gourmet.model.interfaces.IEntityObject;

/**
 * @author esp
 *
 */
public class Meal implements IEntityObject {


	private int id_meal;
	private String description; //depending on the language
	private double budget_min;
	private double budget_max;
	private int preparationTime;
	
	private int idRestaurant; 
	private Restaurant restaurant; //restaurant where this meal is served;
	private MealDescription objDescription;




	/**
	 * 
	 */
	public Meal() {
	}

	/**
	 * @param id_meal
	 */
	public Meal(int id_meal) {
		this.id_meal = id_meal;
	}




	/**
	 * @return the id_meal
	 */
	public int getId_meal() {
		return id_meal;
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
	 * @return the budget_min
	 */
	public double getBudget_min() {
		return budget_min;
	}



	/**
	 * @param budget_min the budget_min to set
	 */
	public void setBudget_min(double budget_min) {
		this.budget_min = budget_min;
	}



	/**
	 * @return the budget_max
	 */
	public double getBudget_max() {
		return budget_max;
	}



	/**
	 * @param budget_max the budget_max to set
	 */
	public void setBudget_max(double budget_max) {
		this.budget_max = budget_max;
	}



	/**
	 * @param id_meal the id_meal to set
	 */
	@Override
	public void setId(int id_meal) {
		this.id_meal = id_meal;
	}

	@Override
	public void setAttribute(String attrName, Object value) {
		
		String val = (String) value;
		int numVal; 
		double doubleVal;
		

		if(attrName.endsWith(MealColumns.BUD_MIN)){
			doubleVal = Double.valueOf(val);
			setBudget_min(doubleVal);
			return;
		}
		if(attrName.endsWith(MealColumns.BUD_MAX)){
			doubleVal = Double.valueOf(val);
			setBudget_max(doubleVal);
			return;
		}
		
		if(attrName.endsWith(MealColumns._ID)){
			numVal = Integer.valueOf(val);
			setId(numVal);
			return;
		}
		
		if(attrName.endsWith(MealColumns.ID_RESTAURANT)){
			
			if(value == null){
				setIdRestaurant(-1);
			}else {
				numVal = Integer.valueOf(val);
				setIdRestaurant(numVal);

			}

		}
		
		if(attrName.endsWith(MealColumns.PREPARATION_TIME)){
			numVal = Integer.valueOf(val);
			setPreparationTime(numVal);
		}

	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof MealDescription){
			setObjDescription((MealDescription) relatedObj);
			setDescription(getObjDescription().getDescription());
		}
		
		if(relatedObj instanceof Restaurant){
			setRestaurant((Restaurant) relatedObj);
		} 
		
	}

	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @return the preparationTime
	 */
	public int getPreparationTime() {
		return preparationTime;
	}

	/**
	 * @param preparationTime the preparationTime to set
	 */
	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	/**
	 * @return the objDescription
	 */
	public MealDescription getObjDescription() {
		return objDescription;
	}

	/**
	 * @param objDescription the objDescription to set
	 */
	public void setObjDescription(MealDescription objDescription) {
		this.objDescription = objDescription;
	}

	/**
	 * @return the idRestaurant
	 */
	public int getIdRestaurant() {
		return idRestaurant;
	}

	/**
	 * @param idRestaurant the idRestaurant to set
	 */
	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	/**
	 * @param id_meal the id_meal to set
	 */
	public void setId_meal(int id_meal) {
		this.id_meal = id_meal;
	}

}
