package com.gourmet.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.Schema;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gourmet.database.GourmetOpenHelper;
import com.gourmet.database.context.UserLocationManager;
import com.gourmet.database.gen.AddressTable;
import com.gourmet.database.gen.AddressTable.AddressColumns;
import com.gourmet.database.gen.RestaurantTable;
import com.gourmet.database.services.AddressDAOServices;
import com.gourmet.model.Address;
import com.gourmet.model.Meal;
import com.gourmet.model.Restaurant;
import com.gourmet.session.UserSessionManager;

public class GourmetAddressDAO implements AddressDAOServices {
	
	
	private static GourmetAddressDAO daoInstance = null;

	private EntityLoaderManager loader;
	private Schema reifiedSchema;
	private SQLiteDatabase database;
	private GourmetOpenHelper dbGourmetHelper;
	private Context appContext;
	private UserSessionManager sessionsMgr; 

	

	public static GourmetAddressDAO getInstance(Context context){
		if(daoInstance == null)
			daoInstance = new GourmetAddressDAO(context);
		return daoInstance;
	}
	
	
	
	public GourmetAddressDAO(Context context) {
		loader = new EntityLoaderManager();
		dbGourmetHelper = GourmetOpenHelper.getInstance(context);
		reifiedSchema = dbGourmetHelper.getReifiedSchema();
		this.appContext = context;
		this.sessionsMgr = UserSessionManager.getInstance(appContext);
		
	}
	
	public void openDataSource() throws SQLException{
		database = dbGourmetHelper.getWritableDatabase();
		System.out.println("opening DB");
	}

	public void closeDataSource(){
		dbGourmetHelper.close();
	}
	
	/*
	 * Fetch all restaurant clients from database 
	 */
	public List<Address> getAllRestaurantAddressesFramework(){
		ContextedQueryBuilder qb = this.reifiedSchema.getTable(AddressTable.NAME).select();
		Cursor cursor = database.rawQuery(qb.toString(), null);
		return loadAddressesFromCursor(cursor, qb.getRelationalContext());
	}	
	
	/*
	 * Fetch all restaurant clients from database without using the framework (for validation)
	 */
	public List<Address> getAllRestaurantAddressesClassic(UserSessionManager userSession){
		int langID = userSession.getNumericValue(UserSessionManager.LANG_ID_KEY); //id of the language spoken by user
		int age = userSession.getNumericValue(UserSessionManager.AGE_KEY);
		String query = " SELECT A1._id, A1.id_restaurant, A1.Postal_Code,";
		query+= " A1.Street_Number, AD1.Street_Name, AD1.City_Name, R1._id";
		query+= " FROM Address AS A1 ";
		query+= " LEFT JOIN Restaurant AS R1 ON R1._id = A1.id_restaurant  ";
		query+= " LEFT JOIN Add_Description AS AD1 ON A1._id = AD1.id_address ";
		query+= " AND AD1.id_language =  " + langID;
		query+= " WHERE R1.Age_min <= " + age;
		
	
		Cursor cursor = database.rawQuery(query, null);
		 List<Address> lAddr = load(cursor); //build a list of restaurants entities 
		cursor.close();
		return lAddr;
	}
	
	/*
	 * Not the same loader as the one used with the framework, Although I used reified table for getting size of columns
	 * whithout having to hard code it. 
	 * Only used for the validation so that's why there is less coding attention 
	 */
	private List<Address> load(Cursor cursor) {
		List<Address> restL = new ArrayList<Address>();
		Address addr = null;
		while(cursor.moveToNext()){
			addr = new Address();
			
			addr.setAttribute(AddressColumns._ID, cursor.getString(0));
			addr.setAttribute(AddressColumns.ID_RESTAURANT, cursor.getString(1));
			addr.setAttribute(AddressColumns.POSTAL_CODE, cursor.getString(2));
			addr.setAttribute(AddressColumns.STREET_NUMBER, cursor.getString(3));
			addr.setStreetName(cursor.getString(4));
			addr.setLocality(cursor.getString(5));
		
			restL.add(addr);
		}
		return restL;
	}

	
	
	
	private List<Address> loadAddressesFromCursor(Cursor cursor, RelationalContextManager ctx){

		List<Address> addList = new ArrayList<Address>();
		while(cursor.moveToNext()){
			
			Address add = (Address) loader.loadEntityObjectFromCursor(cursor, ctx);
			addList.add(add);
		}
		return addList;
	}
}
