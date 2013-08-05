package com.gourmet.database.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.visitor.context.QueryFilteringSpecifier;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.dynamicschema.android.sql.EmptyFilteringCondition;
import com.gourmet.database.GourmetOpenHelper;
import com.gourmet.database.context.DBContextManager;
import com.gourmet.database.context.UserLocationManager;
import com.gourmet.database.filterings.LocationFiltering;
import com.gourmet.database.filterings.MealFiltering;
import com.gourmet.database.filterings.NestedFiltering;
import com.gourmet.database.filterings.PreferenceFiltering;
import com.gourmet.database.filterings.RestaurantFiltering;
import com.gourmet.database.gen.ClientTable;
import com.gourmet.database.gen.GourmetRelationModel.ContenanceRelations;
import com.gourmet.database.gen.GourmetRelationModel.PreferenceRelations;
import com.gourmet.database.gen.LanguageTable;
import com.gourmet.database.gen.MealTable;
import com.gourmet.database.gen.PreferenceTable;
import com.gourmet.database.services.ClientDAOServices;
import com.gourmet.model.Client;
import com.gourmet.model.UserLocation;
import com.gourmet.session.UserSessionManager;

public class GourmetClientDAO implements ClientDAOServices {

	private static GourmetClientDAO daoInstance = null;


	public static final String PREF_FILE_NAME= UserSessionManager.PREF_FILE_NAME;
	private EntityLoaderManager entityLoader;

	private Schema reifiedSchema;
	private SQLiteDatabase database;
	private GourmetOpenHelper dbGourmetHelper;
	private Context appContext;
	private UserSessionManager sessionsMgr; 

	public static GourmetClientDAO getInstance(Context context){
		if(daoInstance == null)
			daoInstance = new GourmetClientDAO(context);
		return daoInstance;
	}


	private GourmetClientDAO(Context context) {
		dbGourmetHelper = GourmetOpenHelper.getInstance(context);
		reifiedSchema = dbGourmetHelper.getReifiedSchema();
		this.appContext = context;
		this.sessionsMgr = UserSessionManager.getInstance(this.appContext);

		//init entities loader 
		entityLoader = new EntityLoaderManager();
		
	}
	

	public void openDataSource() throws SQLException{
		database = dbGourmetHelper.getWritableDatabase();
	}

	public void closeDataSource(){
		dbGourmetHelper.close();
	}


	@Override
	public List<Client> getClientsInterestedInMeal(int mealID) {
		DBTable clientTab= this.reifiedSchema.getTable(ClientTable.NAME);
		DBTable mealTab = this.reifiedSchema.getTable(MealTable.NAME);
		DBTable prefTable = this.reifiedSchema.getTable(PreferenceTable.NAME);
		DBTable langTab = this.reifiedSchema.getTable(LanguageTable.NAME);
		UserLocation restoLoc = sessionsMgr.getUserLocation();
		int restoID = sessionsMgr.getNumericValue(UserSessionManager.RESTO_ID_KEY);	
		double distanceRange = UserLocationManager.getDefaultDistanceRange();
		
		//Since select client entities, language entity is going to be selected and global contextual filtering
		//on language would be applied. We temporary remove filtering on language because we want to find clients
		// no matter language they speak. 
		RelationCondition langFilter = langTab.getFiltering(); 
		langTab.setFiltering(new EmptyFilteringCondition());
		
		QueryFilteringSpecifier specifier = new QueryFilteringSpecifier();
		LocationFiltering clientLocFilter = new LocationFiltering(restoLoc.getLatitude(), restoLoc.getLongitude(), distanceRange, true);
		//Filtering that apply on same table in the same relation
		MealFiltering mealFilter = new MealFiltering(mealID);
		RestaurantFiltering restoFilteronMeal = new RestaurantFiltering(restoID, true);
		PreferenceFiltering prefFilter = new PreferenceFiltering(true, PreferenceFiltering.NO_SPECIFIC_CLIENT);
		NestedFiltering nestedFilter = new  NestedFiltering(Arrays.asList(mealFilter,restoFilteronMeal));
		//Add them to query specifier
		specifier.addQuerFiltering(PreferenceRelations.rel_Client_Preference, clientTab, clientLocFilter);
		specifier.addQuerFiltering(ContenanceRelations.rel_Meal_Contenance, mealTab, nestedFilter);	
		specifier.addQuerFiltering(PreferenceRelations.rel_Client_Preference, prefTable, prefFilter);
		//Build the query
		ContextedQueryBuilder qb =  clientTab.select(relationsForClientInterestInMeal, specifier);
		//Once query has been build, we can set the contextual language filtering back
		langTab.setFiltering(langFilter);
		
		Cursor cursor = database.rawQuery(qb.toString(), null);
		return loadClientsFromCursor(cursor, qb.getRelationalContext());
	}


	public List<Client> getAllClients(){
//		DBContextManager.getInstance(appContext).resetNeutralProfile();
		DBTable clientTab= this.reifiedSchema.getTable(ClientTable.NAME);
		ContextedQueryBuilder qb =  clientTab.select();
		Cursor cursor = database.rawQuery(qb.toString(), null);
		return loadClientsFromCursor(cursor, qb.getRelationalContext());
	}


	private List<Client> loadClientsFromCursor(Cursor cursor, RelationalContextManager ctx){

		List<Client> clients = new ArrayList<Client>();
	
		while(cursor.moveToNext()){
			Client cl = (Client) entityLoader.loadEntityObjectFromCursor(cursor, ctx);
			clients.add(cl);
		}
		return clients;
	}
	
}
