package com.gourmet.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.gourmet.LoginActivity;

/**
 * Encapusalte user session management 
 * @author esp
 *
 */
public class UserSessionManager {

	public static final String PREF_FILE_NAME= "gourmetPrefs";
	
	public static final  String PROFILE_TYPE="userType";
	public static final  String RESTAURANT="restaurant";
	public static final  String CLIENT="client";
	
	public static final  String CLIENT_ID_KEY="clientID";
	public static final  String RESTO_ID_KEY="restoID";
	public static final  String LANG_ID_KEY = "language";
	public static final  String AGE_KEY = "Age";
//	public static final String RANGE_KEY ="range";
	private static final String LOGGED_IN = "loggedIN";
	private static final String USER_NAME_KEY = "username";
	/**
	 * 
	 */

	private SharedPreferences prefs; //Preferences to store user session data	
	private SharedPreferences.Editor editPref; //Editor for modifying user session data
	private Context currContext; //context of use
	
	
	public UserSessionManager(Context context) {
		currContext = context;
		prefs = currContext.getSharedPreferences(PREF_FILE_NAME,0	);
		editPref = prefs.edit();
//		initDefaultLocationRange();
		
	}
	
//	private void initDefaultLocationRange(){
//		editPref.putString(RANGE_KEY, defaultLocationRange);
//		editPref.commit();
//	}


	

	public void destroySession(){
		editPref.clear();
		editPref.commit();
		redirectToLogIn();
	
	}
	
	public void checkUserSession(){
		if(!isConnected()){
			redirectToLogIn();
		}
	}
	
	private void redirectToLogIn(){
		
		Intent logInIntent = new Intent(currContext, LoginActivity.class);
		logInIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  	logInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  	currContext.startActivity(logInIntent);
	}
	
	
	public void registerNumeric(String key , int value){
		editPref.putInt(key, value);
		editPref.commit();
	}
	
	public void registerRawData(String key, String value){
		editPref.putString(key, value);
		editPref.commit();
	}
	public void registerUser(String userName){
		editPref.putString(USER_NAME_KEY, userName);
		setLoggedIn();
	}
	
	private void setLoggedIn(){
		editPref.putBoolean(LOGGED_IN, true);
		editPref.commit();
	}
	
	public boolean isConnected(){
		return prefs.getBoolean(LOGGED_IN, false);
	}

	public String getDataValue(String key) {
		return this.prefs.getString(key, "N/A");
	
	}
	public int getNumericValue(String key){
		return this.prefs.getInt(key, -1);
	}
	
	public String getUserName(){
		return this.prefs.getString(USER_NAME_KEY, "N/A username");
	}
	
	

}
