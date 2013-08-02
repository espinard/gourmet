package com.gourmet.database.context;

import static com.gourmet.session.UserSessionManager.AGE_KEY;
import static com.gourmet.session.UserSessionManager.CLIENT;
import static com.gourmet.session.UserSessionManager.CLIENT_ID_KEY;
import static com.gourmet.session.UserSessionManager.LANG_ID_KEY;
import static com.gourmet.session.UserSessionManager.PROFILE_TYPE;
import static com.gourmet.session.UserSessionManager.RESTO_ID_KEY;

import java.util.List;

import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;

import android.content.Context;

import com.github.dynamicschema.android.sql.EmptyFilteringCondition;
import com.gourmet.database.GourmetOpenHelper;
import com.gourmet.database.filterings.AgeFiltering;
import com.gourmet.database.filterings.LanguageFiltering;
import com.gourmet.database.filterings.PreferenceFiltering;
import com.gourmet.database.gen.LanguageTable;
import com.gourmet.database.gen.PreferenceTable;
import com.gourmet.database.gen.RestaurantTable;
import com.gourmet.session.UserSessionManager;

public class DBContextManager {
	
	private static  DBContextManager instance = null;
	private boolean profileActive;
	
	
	public static DBContextManager getInstance(Context context){
		if(instance == null)
			instance = new DBContextManager(context);
		return instance;
	}
	
	private GourmetOpenHelper dbGourmetHelper;

	public DBContextManager(Context context) {
		dbGourmetHelper = GourmetOpenHelper.getInstance(context);
		
	}
	
	public void setProfileActive(boolean active){
		this.profileActive = active;
	}

	
	/*
	 * There are two types of profiles=  Client and Customer. 
	 */
	public void setUserProfile(UserSessionManager userSession){

		int userID, age, userLangID;
		Schema sch =dbGourmetHelper.getReifiedSchema();
		DBTable rest = sch.getTable(RestaurantTable.NAME); 	
		DBTable language = sch.getTable(LanguageTable.NAME);
		DBTable preference = sch.getTable(PreferenceTable.NAME);
		
		userLangID = userSession.getNumericValue(LANG_ID_KEY);
		if(userSession.getDataValue(PROFILE_TYPE).equals(CLIENT)){
			age  = userSession.getNumericValue(AGE_KEY);
			userID = userSession.getNumericValue(CLIENT_ID_KEY);
			rest.setFiltering(new AgeFiltering(age));
			preference.setFiltering(new PreferenceFiltering(true, userID));
		}else {
			userID = userSession.getNumericValue(RESTO_ID_KEY);
			rest.setFiltering(new EmptyFilteringCondition());
			preference.setFiltering(new EmptyFilteringCondition());
		}
		language.setFiltering( new LanguageFiltering(userLangID));

	}
	
	public void resetNeutralProfile(){
		
		if(profileActive ){
			List<DBTable> tables = dbGourmetHelper.getReifiedSchema().getTables();
			for (DBTable dbTable : tables) {
				dbTable.setFiltering(new EmptyFilteringCondition());
			}
		}
		
	}
}
