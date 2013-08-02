package com.gourmet.database.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.ContextedTable;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gourmet.database.GourmetOpenHelper;
import com.gourmet.database.context.DBContextManager;
import com.gourmet.database.gen.ClientTable;
import com.gourmet.database.services.ClientDAOServices;
import com.gourmet.model.Client;
import com.gourmet.model.Meal;
import com.gourmet.model.interfaces.IEntityObject;
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


	public GourmetClientDAO(Context context) {
		dbGourmetHelper = GourmetOpenHelper.getInstance(context);
		reifiedSchema = dbGourmetHelper.getReifiedSchema();
		this.appContext = context;
		this.sessionsMgr = new UserSessionManager(this.appContext);

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
	public List<Client> getClientsInterestedInMeal(Meal meal) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Client> getAllClients(){
		DBContextManager.getInstance(appContext).resetNeutralProfile();
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
