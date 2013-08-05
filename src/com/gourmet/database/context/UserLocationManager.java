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


	private static UserSessionManager userSession;


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

		UserLocation loc = userSession.getUserLocation();
			
//		if(cpt % 3 == 0){
//			return new UserLocation(loc.getLatitude()+0.0005, loc.getLongitude()+0.0005);
//		}
//		
		return loc;
	
	}

}
