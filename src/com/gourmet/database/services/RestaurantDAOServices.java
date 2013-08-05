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

import static com.gourmet.database.gen.GourmetRelationModel.BelongingRelations.*;
import static com.gourmet.database.gen.GourmetRelationModel.MealOfDayRelations.*;

import com.gourmet.model.Restaurant;
import com.gourmet.session.UserSessionManager;

/**
 * @author esp
 *
 */
public interface RestaurantDAOServices {

	
	/**
	 * Get all restaurants that match user preferences
	 * @return
	 */
	public static final String REST_PREF = "All Restaurants matching my preferences";
	public static List<Relation> relationsForRestoMatchingPref = Arrays.asList(rel_Restaurant_Meal, rel_Meal_Contenance, rel_Ingredient_Contenance, rel_Ingredient_Preference);
	//Validation Query 1 
	List<Restaurant> getAllRestaurantsMatchingIngredientsPref();
	

	/**
	 * Nearby restaurants serving meals with preferred ingredients
	 * 
	 * @return all restaurants that are near a certain location provided by context
	 */
	//Context Info: Language, Age, Ingredients preference, Location
	public static final String NEARBY_REST_PREF = "Nearby Restos matching my preferences";
	//Possible validation query 
	List<Restaurant> getAllNearbyRestaurantsMatchingPref();
	
	
	
	
	//Context Info: Language, Age, Location
		public static final String ALL_NEAREST_NO_PREF = "Nearby Restos (regardless my prefs)";
		List<Restaurant> getAllNearbyRestaurants();
		
	
	/**
	 * 
	 * @return
	 */
	public static final String NEARBY_REST_SEASON = "Nearby Restos (with seasonal meals)";
	public static List<Relation> relationsForNearbyRestoSeasonal = Arrays.asList(rel_Restaurant_Meal, rel_Meal_Contenance, rel_Ingredient_Contenance,rel_Ingredient_Belonging, rel_Season_Belonging);
	//Validation Query 2
	List<Restaurant> getNearbyRestoServingSeasonalMealsWithPrefIngr();
	
	/**
	 * 
	 * @return
	 */
	public static final String REST_MEAL_DAY_PREF = "All Restos with meal of day matching my prefs. Budget [15-20]";
	//Validation Query 5 
	public static List<Relation> relationsForNearbyRestoMealOfDay = Arrays.asList(rel_Restaurant_MealOfDay, rel_Meal_MealOfDay ,rel_Meal_Contenance, rel_Ingredient_Contenance, rel_Ingredient_Preference);
	List<Restaurant> getNearbyRestoServingMealofDayWithPrefIngredients();

	
	
	

	public static final String ALL_REST = "All Known Restos (Validation)";
	List<Restaurant> getAllRestaurants();
	
	
	//Context Info: Language, Age
	public static final String ALL_REST_CLASSIC = "All Known Restos Classic (Validation)";
	 List<Restaurant> getAllRestaurantsClassic(UserSessionManager userSession);


	
	
}
