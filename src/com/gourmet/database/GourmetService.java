/**
 * 
 */
package com.gourmet.database;

import java.util.List;

import android.app.Application.ActivityLifecycleCallbacks;

import com.gourmet.model.Meal;
import com.gourmet.model.Restaurant;

/**
 * @author esp
 *
 */
public interface GourmetService {
	
	
		List<Restaurant> getNearbyRestoServingSeasonalMeals();
		List<Meal> getMealsWithSeasonalIngredients();
		List<Restaurant> getAllRestaurants();
		List<Restaurant> getAllNearbyRestaurants();
		List<Meal> getAllMeals();
		

}
