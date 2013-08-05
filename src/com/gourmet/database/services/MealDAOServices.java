package com.gourmet.database.services;

import static com.gourmet.database.gen.GourmetRelationModel.BelongingRelations.rel_Ingredient_Belonging;
import static com.gourmet.database.gen.GourmetRelationModel.BelongingRelations.rel_Season_Belonging;
import static com.gourmet.database.gen.GourmetRelationModel.ContenanceRelations.*;
import static com.gourmet.database.gen.GourmetRelationModel.MealRelations.rel_Restaurant_Meal;
import static com.gourmet.database.gen.GourmetRelationModel.PreferenceRelations.*;
import static com.gourmet.database.gen.GourmetRelationModel.RequirementRelations.*;
import static com.gourmet.database.gen.GourmetRelationModel.ProhibitionRelations.*;

import java.util.Arrays;
import java.util.List;

import org.dynamicschema.reification.Relation;

import com.gourmet.model.Meal;

public interface MealDAOServices {
	/**
	 * 
	 * Get all meals of the current restaurant with seasonal ingredients
	 * @return
	 */
	////Validation Query 3 
	public static List<Relation> relationsForMealWithSeasonalIngr = Arrays.asList(rel_Meal_Contenance, rel_Ingredient_Contenance,rel_Ingredient_Belonging, rel_Season_Belonging);
	public static final String REST_MEAL_SEASON = "Show seasonal meals";
	List<Meal> getMealsWithSeasonalIngredients();
	/**
	 * 
	 * Get all meals served by the current restaurant
	 * @return
	 */
	List<Meal> getMealsOfRestaurant(int restID);
	
	/**
	 * Fetch meals which respecting diet constraints
	 * @return meals
	 */
	List<Meal> getAllMealsMatchingDietConstraints();
	public static final String MEAL_DIET_CONSTR = "All Meals matching diet constraints";
	public static List<Relation> relationsForMealDietConstr = Arrays.asList(rel_Meal_Contenance, 
																			rel_Ingredient_Contenance, 
																			rel_Ingredient_Prohibition, 
																			rel_User_Constraint_Prohibition, 
																			rel_User_Constraint_Requirement);
}
