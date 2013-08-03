package com.gourmet.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.Schema;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gourmet.database.GourmetOpenHelper;
import com.gourmet.model.Meal;
import com.gourmet.session.UserSessionManager;

public class GourmetMealDAO {
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
	
	public GourmetMealDAO(Context context) {
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
	 * 
	 * @param restaurantID
	 * @return
	 */
	
	List<Meal> getAllMealsOfRestaurant(){
		return null;
	}
	
	
	/**
	 * Fetch all meals belonging to a given restaurant whose ID is explicitely given.
	 * @param restID
	 * @return
	 */
	public List<Meal> getMealsOfCurrentRestaurant(int restID){
//		reInitTableFilterings();
		
//		DBTable restTab =  getTable(RestaurantTable.NAME);
//		TableRelation relRestMeal = restTab.getTabRelation(MealRelations.RESTAURANT_MEAL_10, null);
//		Map<String, Object> colBindings = new HashMap<String, Object>();
//
//		colBindings.put(RestaurantColumns._ID, Integer.valueOf(restID));
//		//Additional Tables whose columns should be selected
//		List<Table> addTables = new ArrayList<Table>();
//		addTables.add(new DescriptionTable());
//		//Add filterings 
//		int userLangID= ((Integer)getUserContextValue(UserSessionManager.LANG_ID_KEY, true)).intValue();
//		DBTable langTab = getTable(LanguageTable.NAME);
//		langTab.setFiltering(new LanguageFiltering(userLangID));
//		ContextedQueryBuilder qb = restTab.lazyRelationSelect(relRestMeal, colBindings, addTables);
//		RelationalContextManager ctxMgr = qb.getRelationalContext();
//		SqlCondition addCond = getRelationCondition(MealTable.NAME, DescriptionTable.NAME, DescriptionRelations.MEAL_DESCRIPTION_7, ctxMgr);
//		qb.addWhere(addCond);
//		
//		Cursor cursor  = database.rawQuery(qb.toString(), null);
//		List<Meal> meals= loadMealsFromCursor(cursor, ctxMgr);
//		return meals;
		return null;
	}

	
}
