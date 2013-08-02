/**
 * Class representing the Data Source 
 */
package com.gourmet.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Relation;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.SqlCondition;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gourmet.database.GourmetOpenHelper;
import com.gourmet.database.context.UserLocationManager;
import com.gourmet.database.gen.RestaurantTable;
import com.gourmet.database.services.RestaurantDAOServices;
import com.gourmet.model.Restaurant;
import com.gourmet.session.UserSessionManager;

/**
 * @author esp
 *
 */
public class GourmetRestoDAO implements RestaurantDAOServices {

	private static GourmetRestoDAO daoInstance = null;
	
//	public static final String PREF_FILE_NAME= UserSessionManager.PREF_FILE_NAME;

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
		this.sessionsMgr = new UserSessionManager(this.appContext);
		
		loader = new EntityLoaderManager();
	}

	public void openDataSource() throws SQLException{
		database = dbGourmetHelper.getWritableDatabase();
		System.out.println("opening DB");
	}

	public void closeDataSource(){
		dbGourmetHelper.close();
	}

	
	
	
	public List<Restaurant> getAllRestaurant(){
		ContextedQueryBuilder qb =  reifiedSchema.getTable(RestaurantTable.NAME).select();
		Cursor cursor = database.rawQuery(qb.toString(), null);
		return  loadRestaurantsFromCursor(cursor, qb.getRelationalContext());
	}

	


	/*
	 * (non-Javadoc)
	 * @see com.gourmet.database.GourmetClientService#getAllRestaurantsMatchingIngredientsPref()
	 */
	@Override
	public List<Restaurant> getAllRestaurantsMatchingIngredientsPref() {
		DBTable restoTab  = this.reifiedSchema.getTable(RestaurantTable.NAME);
		ContextedQueryBuilder qb = restoTab.select(relation2TraverseRestoMatchingPref);
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
//		reInitTableFilterings();
//		double  range;
//		DBTable resto =  getTable(RestaurantTable.NAME);
//		DBTable ingrPref = getTable(PreferenceTable.NAME);
//
//		UserLocation loc = getCurrentLocation();
//		//set the filterings : Should be part of of the context of user context; context.getSharedPreference
//		range = Double.valueOf((String)getUserContextValue(UserSessionManager.RANGE_KEY,false)); 
//		int ClientID = ((Integer)getUserContextValue(UserSessionManager.CLIENT_ID_KEY, true)).intValue();
//		
//		ingrPref.setFiltering(new PreferenceFiltering(true, ClientID));
//		LocationFiltering filter = new LocationFiltering(loc.getLatitude(), loc.getLongitude(), range);
//		resto.setFiltering(filter);
//
//		ContextedQueryBuilder qb = resto.select();
//		RelationalContextManager ctxMgr = qb.getRelationalContext();
//
//		SqlCondition joinCondition = getRelationCondition(IngredientTable.NAME, PreferenceTable.NAME, PreferenceRelations.INGREDIENT_PREFERENCE_14, ctxMgr);
//		qb.addWhere(joinCondition);
//
//		Cursor cursor  = database.rawQuery(qb.toString(), null);
//		List<Restaurant> listRest = loadRestaurantsFromCursor(cursor, ctxMgr);
//		cursor.close();
//		return listRest;
		
		
		DBTable restoTable = this.reifiedSchema.getTable(RestaurantTable.NAME);
		ContextedQueryBuilder qb = restoTable.select();
		Cursor cursor  = database.rawQuery(qb.toString(), null);
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
	


	private SqlCondition getRelationCondition(String tab1Name, String tab2Name, String relationName, RelationalContextManager ctxMgr){
		Table tab1, tab2;
		tab1 = reifiedSchema.getTable(tab1Name);
		tab2 = reifiedSchema.getTable(tab2Name);
		Relation rel = this.reifiedSchema.getRelationModel().getRelation(tab1Name, tab2Name, relationName);
		SqlCondition cond = rel.getCondition().eval(ctxMgr.getFirstContextedTable(tab1), ctxMgr.getFirstContextedTable(tab2));
		return cond;
	}

	


	@Override
	public List<Restaurant> getNearbyRestoServingSeasonalMealsWithPrefIngr() {
		// TODO Auto-generated method stub
		return null;
	}


	


//	private Object getUserContextValue(String key, boolean isID){
//		
//		if(isID)
//				return Integer.valueOf(this.sessionsMgr.getIDValue(key));
//		else
//			return this.sessionsMgr.getDataValue(key);
//	}



	@Override
	public List<Restaurant> getNearbyRestoServingMealofDayWithPrefIngredients() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Restaurant> getAllNearbyRestaurantsMatchingPref() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Restaurant> getAllRestaurantsFramework() {
		// TODO Auto-generated method stub
		return null;
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
		 query+=" AND R1.Age_min  <= " + age;
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
