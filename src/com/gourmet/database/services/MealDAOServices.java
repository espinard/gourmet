package com.gourmet.database.services;

import java.util.List;

import com.gourmet.model.Meal;

public interface MealDAOServices {
	/**
	 * 
	 * Get all meals of the current restaurant with seasonal ingredients
	 * @return
	 */
	////Validation Query 3 
	List<Meal> getMealsWithSeasonalIngredients();
	/**
	 * 
	 * Get all meals served by the current restaurant
	 * @return
	 */
	List<Meal> getAllMealsOfRestaurant();
	
}
