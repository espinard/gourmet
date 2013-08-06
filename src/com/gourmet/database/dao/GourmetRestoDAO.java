/**
 * Class representing the Data Source 
 */
package com.gourmet.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.visitor.context.QueryFilteringSpecifier;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gourmet.database.GourmetOpenHelper;
import com.gourmet.database.context.UserLocationManager;
import com.gourmet.database.filterings.LanguageFiltering;
import com.gourmet.database.filterings.LocationFiltering;
import com.gourmet.database.filterings.SeasonFiltering;
import com.gourmet.database.gen.AddressTable;
import com.gourmet.database.gen.GourmetRelationModel.AddressRelations;
import com.gourmet.database.gen.GourmetRelationModel.BelongingRelations;
import com.gourmet.database.gen.LanguageTable;
import com.gourmet.database.gen.RestaurantTable;
import com.gourmet.database.gen.SeasonTable;
import com.gourmet.database.services.RestaurantDAOServices;
import com.gourmet.model.Language;
import com.gourmet.model.Restaurant;
import com.gourmet.model.UserLocation;
import com.gourmet.session.UserSessionManager;

/**
 * @author esp
 *
 */
public class GourmetRestoDAO implements RestaurantDAOServices {

	private static GourmetRestoDAO daoInstance = null;

	private Schema reifiedSchema;
	private SQLiteDatabase database;
	private GourmetOpenHelper dbGourmetHelper;
	private Context appContext;
	private UserSessionManager sessionsMgr; 
	private EntityLoaderManager loader;



	public static GourmetRestoDAO getInstance(Context context){
		if(daoInstance == null)
			daoInstance = new GourmetRestoDAO(context);
		return daoInstance;
	}


	/**
	 * Initializes the data access object which acts as the data source
	 */
	public GourmetRestoDAO(Context context) {		
		dbGourmetHelper = GourmetOpenHelper.getInstance(context);
		reifiedSchema = dbGourmetHelper.getReifiedSchema();
		this.appContext = context;
		this.sessionsMgr = UserSessionManager.getInstance(this.appContext);

		loader = new EntityLoaderManager();
	}

	public void openDataSource() throws SQLException{
		database = dbGourmetHelper.getWritableDatabase();
		System.out.println("opening DB");
	}

	public void closeDataSource(){
		dbGourmetHelper.close();
	}







	/*
	 * (non-Javadoc)
	 * @see com.gourmet.database.GourmetClientService#getAllRestaurantsMatchingIngredientsPref()
	 */
	@Override
	public List<Restaurant> getAllRestaurantsMatchingIngredientsPref() {
		DBTable restoTab  = this.reifiedSchema.getTable(RestaurantTable.NAME);
		ContextedQueryBuilder qb = restoTab.select(relationsForRestoMatchingPref);
		Cursor cursor  = database.rawQuery(qb.toString(), null);
		List<Restaurant> lRest = loadRestaurantsFromCursor(cursor, qb.getRelationalContext());
		cursor.close();
		return lRest;
	}


	/*
	 * (non-Javadoc)
	 * @see com.gourmet.database.GourmetClientService#getAllNearbyRestaurants()
	 */

	public List<Restaurant> getAllNearbyRestaurants() {

		//Set location-related filterings 
		UserLocation loc = UserLocationManager.getCurrentLocation();
		double distanceRange =  UserLocationManager.getDefaultDistanceRange();
		LocationFiltering locFilter = new LocationFiltering(loc.getLatitude(), loc.getLongitude(), distanceRange, false);
		DBTable restoAddr = this.reifiedSchema.getTable(AddressTable.NAME);
		//To add  filterings on a table participating a given relation
		QueryFilteringSpecifier specifier = new QueryFilteringSpecifier();  
		specifier.addQuerFiltering(AddressRelations.rel_Restaurant_Address, restoAddr, locFilter);
		//Pass the query filtering specifier to select
		ContextedQueryBuilder qb =  this.reifiedSchema.getTable(RestaurantTable.NAME).select(specifier);
		Cursor cursor = database.rawQuery(qb.toString(), null);
		List<Restaurant> lRest = loadRestaurantsFromCursor(cursor, qb.getRelationalContext());
		cursor.close();
		return lRest;
	}

	private List<Restaurant> loadRestaurantsFromCursor(Cursor cursor, RelationalContextManager ctx){

		List<Restaurant> lRest = new ArrayList<Restaurant>();
		while(cursor.moveToNext()){
			Restaurant rest =(Restaurant) loader.loadEntityObjectFromCursor(cursor, ctx);
			lRest.add(rest);
		}

		return lRest;
	}



//	private SqlCondition getRelationCondition(String tab1Name, String tab2Name, String relationName, RelationalContextManager ctxMgr){
//		Table tab1, tab2;
//		tab1 = reifiedSchema.getTable(tab1Name);
//		tab2 = reifiedSchema.getTable(tab2Name);
//		Relation rel = this.reifiedSchema.getRelationModel().getRelation(tab1Name, tab2Name, relationName);
//		SqlCondition cond = rel.getCondition().eval(ctxMgr.getFirstContextedTable(tab1), ctxMgr.getFirstContextedTable(tab2));
//		return cond;
//	}




	@Override
	public List<Restaurant> getNearbyRestoServingSeasonalMealsWithPrefIngr() {
		DBTable restoAddr = this.reifiedSchema.getTable(AddressTable.NAME);
		DBTable restoTab  = this.reifiedSchema.getTable(RestaurantTable.NAME);
		DBTable seasonTable = this.reifiedSchema.getTable(SeasonTable.NAME);
		//Prepare filterings
		UserLocation loc = UserLocationManager.getCurrentLocation();
		double distanceRange =  UserLocationManager.getDefaultDistanceRange();
		LocationFiltering locFilter = new LocationFiltering(loc.getLatitude(), loc.getLongitude(), distanceRange, false);
		SeasonFiltering seasonFilter=  new SeasonFiltering();
		QueryFilteringSpecifier specifier = new QueryFilteringSpecifier();  
		specifier.addQuerFiltering(AddressRelations.rel_Restaurant_Address, restoAddr, locFilter);
		specifier.addQuerFiltering(BelongingRelations.rel_Season_Belonging, seasonTable, seasonFilter);
		//Build the query
		//Since we are want to query restaurant entities, we call select on restaurant table
		ContextedQueryBuilder qb = restoTab.select(relationsForNearbyRestoSeasonal, specifier);
		Cursor cursor = database.rawQuery(qb.toString(), null);
		List<Restaurant> lRest = loadRestaurantsFromCursor(cursor, qb.getRelationalContext());
		cursor.close();
		return lRest;
	}



	/*
	 * (non-Javadoc)
	 * @see com.gourmet.database.services.RestaurantDAOServices#getNearbyRestoServingMealofDayWithPrefIngredients()
	 */
	@Override
	public List<Restaurant> getNearbyRestoServingMealofDayWithPrefIngredients() {
		
//		DBTable restoTab = this.reifiedSchema.getTable(RestaurantTable.NAME);
//		//prepare filterings
//		QueryFilteringSpecifier specifier = new QueryFilteringSpecifier();
//		
//		ContextedQueryBuilder qb = restoTab.select(relationsForNearbyRestoSeasonal, specifier);
//		Cursor cursor = database.rawQuery(qb.toString(), null);
//		List<Restaurant> lRest = loadRestaurantsFromCursor(cursor, qb.getRelationalContext());
//		cursor.close();
//		return lRest;
		return null;
	}


	@Override
	public List<Restaurant> getAllNearbyRestaurantsMatchingPref() {

		DBTable restoTab  = this.reifiedSchema.getTable(RestaurantTable.NAME);
		DBTable restoAddr = this.reifiedSchema.getTable(AddressTable.NAME);
		//prepare filterings
		UserLocation loc = UserLocationManager.getCurrentLocation();
		double distanceRange = UserLocationManager.getDefaultDistanceRange();
		LocationFiltering locFilter = new LocationFiltering(loc.getLatitude(),loc.getLongitude(), distanceRange, false);
		QueryFilteringSpecifier specifier = new QueryFilteringSpecifier();
		specifier.addQuerFiltering(AddressRelations.rel_Restaurant_Address, restoAddr, locFilter);
		//Build the query 
		ContextedQueryBuilder qb = restoTab.select(relationsForRestoMatchingPref, specifier);
		Cursor cursor  = database.rawQuery(qb.toString(), null);
		List<Restaurant> lRest = loadRestaurantsFromCursor(cursor, qb.getRelationalContext());
		cursor.close();
		return lRest;
	}


	@Override
	public List<Restaurant> getAllRestaurants() {
		ContextedQueryBuilder qb =  reifiedSchema.getTable(RestaurantTable.NAME).select();
		Cursor cursor = database.rawQuery(qb.toString(), null);
		return  loadRestaurantsFromCursor(cursor, qb.getRelationalContext());
	}

	
	/*
	 * In order to use restaurant names (in english) as usernames
	 */
	public List<Restaurant> getAllRestaurantLogin(){
		DBTable langTab = this.reifiedSchema.getTable(LanguageTable.NAME); 
		LanguageFiltering filter = new LanguageFiltering(Language.ENGLISH);
		langTab.setFiltering(filter);
		return getAllRestaurants();
	}

	public List<Restaurant> getAllRestaurantsClassic(UserSessionManager userSession){

		int langID = userSession.getNumericValue(UserSessionManager.LANG_ID_KEY); //id of the language spoken by user
		int age = userSession.getNumericValue(UserSessionManager.AGE_KEY);
		//for efficiency when querying a restaurant, also query its address
		String query = "SELECT R1._id , R1.Food_Type, R1.Age_min, R1.id_region, RD1.Description, AD1.Street_Name, AD1.City_Name, A1._id, A1.id_restaurant, A1.Postal_Code, A1.Street_Number" ;

		query+= " FROM Restaurant AS R1 ";
		query+=" LEFT JOIN RestoDescription AS RD1 ON R1._id = RD1.id_restaurant ";
		query+=" AND RD1.id_language = " + langID;
		query+=" LEFT JOIN Address AS A1 ON R1._id = A1.id_restaurant ";
		query+=" LEFT JOIN Add_Description AS AD1 ON A1._id = AD1.id_address ";
		query+=" AND AD1.id_language = " + langID;
		query+=" WHERE R1.Age_min  <= " + age;
		//Result set containing result of the query
		Cursor cursor = database.rawQuery(query, null);
		List<Restaurant> lRest = load(cursor); //build a list of restaurants entities 
		cursor.close();
		return lRest;

	}


	/*
	 * Not the same loader as the one used with the framework, Although I used reified table for getting size of columns
	 * whithout having to hard code it. 
	 */
	private List<Restaurant> load(Cursor cursor) {
		List<Restaurant> restL = new ArrayList<Restaurant>();
		Restaurant rest = null;
		List<String> colVaList = this.reifiedSchema.getTable(RestaurantTable.NAME).getColumnValues();
		while(cursor.moveToNext()){

			rest = new Restaurant();
			for (int i = 0; i < colVaList.size() ; i++) {
				rest.setAttribute(colVaList.get(i), cursor.getString(i));
			}
			rest.setDescription(cursor.getString(colVaList.size()));
			rest.setLocality(cursor.getString(colVaList.size() + 2));

			restL.add(rest);
		}
		return restL;
	}



}
