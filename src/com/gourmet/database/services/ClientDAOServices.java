/**
 * Interface of services offered to the Restaurant as application user
 */
package com.gourmet.database.services;

import static com.gourmet.database.gen.GourmetRelationModel.ContenanceRelations.rel_Ingredient_Contenance;
import static com.gourmet.database.gen.GourmetRelationModel.ContenanceRelations.rel_Meal_Contenance;
import static com.gourmet.database.gen.GourmetRelationModel.MealRelations.rel_Restaurant_Meal;
import static com.gourmet.database.gen.GourmetRelationModel.PreferenceRelations.rel_Client_Preference;
import static com.gourmet.database.gen.GourmetRelationModel.PreferenceRelations.rel_Ingredient_Preference;

import java.util.Arrays;
import java.util.List;

import org.dynamicschema.reification.Relation;

import com.gourmet.model.Client;

/**
 * @author esp
 *
 */
public interface ClientDAOServices {
	
	/**
	 * Clients that can be interested in a particular menu
	 * @param menu
	 * @return
	 */
	////Validation Query 4 
	public static List<Relation> relationsForClientInterestInMeal = Arrays.asList(rel_Restaurant_Meal, rel_Meal_Contenance, rel_Ingredient_Contenance, rel_Ingredient_Preference, rel_Client_Preference);
	public static final String CLIENT_INTERESTED_MEAL = "Show potential clients";
	List<Client> getClientsInterestedInMeal(int mealID);
	/**
	 * Meals of the current restaurant using the application. The restaurant is identified by its ID 
	 * @return meals of the current restaurant
	 */
	
	
	List<Client> getAllClients();
}
