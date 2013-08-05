package com.gourmet.database.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.reification.TableRelation;
import org.dynamicschema.visitor.context.QueryFilteringSpecifier;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gourmet.database.GourmetOpenHelper;
import com.gourmet.database.filterings.RestaurantFiltering;
import com.gourmet.database.filterings.SeasonFiltering;
import com.gourmet.database.gen.GourmetRelationModel.BelongingRelations;
import com.gourmet.database.gen.GourmetRelationModel.ContenanceRelations;
import com.gourmet.database.gen.GourmetRelationModel.MealRelations;
import com.gourmet.database.gen.LanguageTable;
import com.gourmet.database.gen.MealTable;
import com.gourmet.database.gen.RestaurantTable;
import com.gourmet.database.gen.RestaurantTable.RestaurantColumns;
import com.gourmet.database.gen.SeasonTable;
import com.gourmet.database.services.MealDAOServices;
import com.gourmet.model.Meal;
import com.gourmet.session.UserSessionManager;

public class GourmetMealDAO  implements MealDAOServices {
	private static GourmetMealDAO daoInstance = null;
	private Schema reifiedSchema;
	private SQLiteDatabase database;
	private GourmetOpenHelper dbGourmetHelper;
	private Context appContext;
	private UserSessionManager sessionsMgr; 
	
	
	private EntityLoaderManager loader; 

	
	public static GourmetMealDAO getInstance(Context context){
		if(daoInstance == null)
			daoInstance = new GourmetMealDAO(context);
		return daoInstance;
	}
	
	private GourmetMealDAO(Context context) {
		loader = new EntityLoaderManager();
		
		dbGourmetHelper = GourmetOpenHelper.getInstance(context);
		reifiedSchema = dbGourmetHelper.getReifiedSchema();
		this.appContext = context;
		this.sessionsMgr = UserSessionManager.getInstance(this.appContext);
	}

	private List<Meal> loadMealsFromCursor(Cursor cursor, RelationalContextManager ctx){

		List<Meal> meals = new ArrayList<Meal>();
		while(cursor.moveToNext()){

			Meal meal = (Meal) loader.loadEntityObjectFromCursor(cursor, ctx);
			meals.add(meal);
		}
		return meals;
	}
	
	public void openDataSource() throws SQLException{
		database = dbGourmetHelper.getWritableDatabase();
	}

	public void closeDataSource(){
		dbGourmetHelper.close();
	}

	
	
	/**
	 * Fetch all meals belonging to a given restaurant whose ID is explicitely given.
	 * @param restID
	 * @return
	 */
	
	public List<Meal> getMealsOfRestaurant(int restID){ 
		
		DBTable restTab = this.reifiedSchema.getTable(RestaurantTable.NAME);
		TableRelation relRestMeal = restTab.getTabRelation(MealRelations.RESTAURANT_MEAL, null);
		Map<String, Object> colBindings = new HashMap<String, Object>();
		colBindings.put(RestaurantColumns._ID, Integer.valueOf(restID));
		ContextedQueryBuilder qb = restTab.lazyRelationSelect(relRestMeal, colBindings);
		Cursor cursor  = database.rawQuery(qb.toString(), null);
		List<Meal> meals= loadMealsFromCursor(cursor, qb.getRelationalContext());
		return meals;
	}

	@Override
	public List<Meal> getMealsWithSeasonalIngredients() {
		
		DBTable mealTab = this.reifiedSchema.getTable(MealTable.NAME);
		DBTable seasonTab = this.reifiedSchema.getTable(SeasonTable.NAME);
		DBTable langTab = this.reifiedSchema.getTable(LanguageTable.NAME);
		int restID = sessionsMgr.getNumericValue(UserSessionManager.RESTO_ID_KEY);
		QueryFilteringSpecifier specifier = new QueryFilteringSpecifier();
		specifier.addQuerFiltering(BelongingRelations.rel_Season_Belonging, seasonTab, new SeasonFiltering());
		specifier.addQuerFiltering(ContenanceRelations.rel_Meal_Contenance, mealTab, new RestaurantFiltering(restID, true));
		ContextedQueryBuilder qb = mealTab.select(relationsForMealWithSeasonalIngr,specifier);
		Cursor cursor  = database.rawQuery(qb.toString(), null);
		List<Meal> meals= loadMealsFromCursor(cursor, qb.getRelationalContext());
		return meals;
	}

	@Override
	public List<Meal> getAllMealsMatchingDietConstraints() {
		DBTable mealTab = this.reifiedSchema.getTable(MealTable.NAME);
		
//		QueryFilteringSpecifier specifier = new QueryFilteringSpecifier();
//		ContextedQueryBuilder qb = mealTab.select(specifier);
//		Cursor cursor  = database.rawQuery(qb.toString(), null);
//		List<Meal> meals= loadMealsFromCursor(cursor, qb.getRelationalContext());
//		return meals;
		return null;
	}
	

	
}
