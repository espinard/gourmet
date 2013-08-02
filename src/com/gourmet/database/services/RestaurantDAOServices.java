/**
 * Interface of services where the User of the App is the Customer
 */
package com.gourmet.database.services;

import java.util.Arrays;
import java.util.List;

import org.dynamicschema.reification.Relation;

import static com.gourmet.database.gen.GourmetRelationModel.MealRelations.*;
import static com.gourmet.database.gen.GourmetRelationModel.ContenanceRelations.*;
import static com.gourmet.database.gen.GourmetRelationModel.PreferenceRelations.*;
import com.gourmet.model.Restaurant;

/**
 * @author esp
 *
 */
public interface RestaurantDAOServices {

	
	/**
	 * Get all restaurants that match user preferences
	 * @return
	 */
	public static final String REST_PREF_KEY = "Restaurants matching preferences regardless Location";
	public static List<Relation> relation2TraverseRestoMatchingPref = Arrays.asList(rel_Restaurant_Meal, rel_Meal_Contenance, rel_Ingredient_Contenance, rel_Ingredient_Preference);
	//Validation Query 1 
	List<Restaurant> getAllRestaurantsMatchingIngredientsPref();
	

	/**
	 * 
	 * @return
	 */
	public static final String REST_SEASON = "Restaurants serving dishes with seasonal Ingredients";
	//Validation Query 2
	List<Restaurant> getNearbyRestoServingSeasonalMealsWithPrefIngr();

	
	/**
	 * 
	 * @return
	 */
	public static final String REST_MEAL_DAY_PREF = "Restaurants serving meal of day matching preferences. Budget [15-20]";
	//Validation Query 5 
	List<Restaurant> getNearbyRestoServingMealofDayWithPrefIngredients();

	
	/**
	 * Nearby restaurants serving meals with preferred ingredients
	 * 
	 * @return all restaurants that are near a certain location provided by context
	 */
	public static final String NEARBY_REST_KEY = "Nearby restaurants Matching prefs";
	//Possible validation query 
	List<Restaurant> getAllNearbyRestaurantsMatchingPref();
	
	

	List<Restaurant> getAllRestaurants();
	

	
	List<Restaurant> getAllRestaurantsFramework();
	


	
	
}
