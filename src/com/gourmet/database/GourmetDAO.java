/**
 * Class representing the Data Source 
 */
package com.gourmet.database;

import java.util.ArrayList;
import java.util.List;

import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.sql.QueryBuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.dynamicschema.android.reification.gen.MealTable;
import com.github.dynamicschema.android.reification.gen.RestaurantTable;
import com.gourmet.database.filterings.LocationFiltering;
import com.gourmet.model.Meal;
import com.gourmet.model.Restaurant;

/**
 * @author esp
 *
 */
public class GourmetDAO implements GourmetService {

	
	private Schema reifiedSchema;
	private SQLiteDatabase database;
	private GourmetOpenHelper dbGourmetHelper;
	private Context appContext;
	
	
	/**
	 * Initializes the data access object which acts as the data source
	 */
	public GourmetDAO(Context context) {		
		dbGourmetHelper = new GourmetOpenHelper(context);
		reifiedSchema = dbGourmetHelper.getReifiedSchema();
		this.appContext = context;
	}
	
	public void openDataSource() throws SQLException{
		database = dbGourmetHelper.getWritableDatabase();
	}
	
	public void closeDataSource(){
		dbGourmetHelper.close();
	}

	@Override
	public List<Restaurant> getNearbyRestoServingSeasonalMeals() {
		// TODO Auto-generated method stub
	
		return null;
	}
	
	

	@Override
	public List<Meal> getMealsWithSeasonalIngredients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		
		DBTable table2Query  = getTable(RestaurantTable.NAME);
		QueryBuilder qb  = table2Query.select();
		Cursor cursor  = database.rawQuery(qb.toString(), null);
		cursor.moveToFirst();
		List<Restaurant> listRest = new ArrayList<Restaurant>();
		while(!cursor.isAfterLast()){
			Restaurant rest = loadRestaurant(cursor);
			listRest.add(rest);
			cursor.moveToNext();
		}
		cursor.close();
		return listRest;
		
	}
	
	@Override
	public List<Restaurant> getAllNearbyRestaurants() {
		
		DBTable resto =  getTable(RestaurantTable.NAME);
		
		//set the filterings : Shoudl be part of of the context of user context; context.getSharedPreference
	
		double latitude = 50.85711 ;
		double longitude = 4.351906; 
		double range = 0.5;
		
		LocationFiltering filter = new LocationFiltering(latitude, longitude, range);
		resto.setFiltering(filter);
		QueryBuilder qb = resto.select();
		
		Cursor cursor  = database.rawQuery(qb.toString(), null);
		cursor.moveToFirst();
		List<Restaurant> listRest = new ArrayList<Restaurant>();
		while(!cursor.isAfterLast()){
			Restaurant rest = loadRestaurant(cursor);
			listRest.add(rest);
			cursor.moveToNext();
		}
		cursor.close();
		return listRest;
		
	}

	public List<Meal> getMealsOfRestaurant(int restaurantID){
		//TODO
		return null;
		
	}
	
	@Override
	public List<Meal> getAllMeals() {
		
		DBTable table2Query  = getTable(MealTable.NAME);
		QueryBuilder qb  = table2Query.select();
		Cursor cursor  = database.rawQuery(qb.toString(), null);
		cursor.moveToFirst();
		List<Meal> listMeal= new ArrayList<Meal>();
		while(!cursor.isAfterLast()){
			Meal m = loadMeal(cursor);
			listMeal.add(m);
			cursor.moveToNext();
		}
		cursor.close();
		return listMeal;
	}
	
	

	
	/**
	 * 
	 * @param name Name 
	 * @return
	 */
	private DBTable getTable(String name){
		if(reifiedSchema == null)
			throw new RuntimeException("Unexpected Error: About to used schema that was not previously refied !!!");
		
		List<DBTable> tables = this.reifiedSchema.getTables();
		for (DBTable dbTable : tables) {
			if(dbTable.getName().equals(name))
				return dbTable;
		}
		return null;
	}

	

	/**
	 * 
	 * @param cursor
	 * @return
	 */
	private Restaurant loadRestaurant(Cursor cursor){
		//TODO change. should use RelationContext Manager 
		
		String restoName = cursor.getString(2);
		return new Restaurant(restoName);
	}

	
	private Meal loadMeal(Cursor cursor){
		//TODO change. should use RelationContext Manager 
		
		int idMeal = cursor.getInt(1);
		return new Meal(idMeal);
	}
	
	
}
