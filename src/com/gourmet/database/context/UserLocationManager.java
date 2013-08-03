package com.gourmet.database.context;

import android.util.SparseArray;

import com.gourmet.model.UserLocation;
import com.gourmet.session.UserSessionManager;


/**
 * Very simple simulation of a geolocalisation service  
 * Did this to be sure that I will have some results from the query
 * @author esp
 *
 */
public class UserLocationManager {

	private static int  cpt = 1 ;

	//Approximative distance (expressed in decimal (minutes-seconds)). Approximatively represents distance between Grand-Place in Brussels and Galerie Toison d'or (Brussels)  
	//which is of~ 4 km
	private static String defaultLocationRange = "0.053724"; 


	/*
	 *  "1","Lourdo","Ben","50.843454","4.347188","20","Belgique","1"
		"2","Whity","Nico","50.879535","4.354162","30","Belgique","1"
		"3","Horizman","David","50.84342","4.34712","40","Belgique","2"
		"4","Code42","Luc","50.8432","4.347","25","Belgique","2"
		"5","Frely","Esp","48.862682","2.351017","20","France","3"
		"6","Megot","cli 5","41.920673","11.370743","20","Italie","3"
		"7","little","Chris","45.76896","4.347","8","France","1"
	 */

	private static UserSessionManager userSession;
	private static SparseArray<UserLocation> locations;

	static {
		locations= new SparseArray<UserLocation>();
		locations.put(1, new UserLocation(50.843454, 4.347188));
		locations.put(2, new UserLocation(50.879535, 4.354162));
		locations.put(3, new UserLocation(50.84342, 4.34712));
		locations.put(4, new UserLocation(50.8432,4.347 ));
		locations.put(5, new UserLocation(48.862682, 2.351017));
		locations.put(6, new UserLocation(41.920673, 11.370743));
		locations.put(7, new UserLocation(45.76896, 4.347));
	}


	public static void assignSession(UserSessionManager session){
		userSession = session;
	}

	/*
	 * Get the distance range to determine whether a restaurant is close or not
	 * A close restaurant is any restaurant which with that range
	 */
	public static double getDefaultDistanceRange(){
		return Double.valueOf(defaultLocationRange);
	}


	public static  UserLocation getCurrentLocation(){
		cpt++;

		
		UserLocation loc = locations.get(userSession.getNumericValue(UserSessionManager.CLIENT_ID_KEY));
		
		
		return loc;
		


	}

}
