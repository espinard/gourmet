package com.gourmet.model.interfaces;

import com.gourmet.model.UserLocation;

public interface IUser {
	
		int getUserID();
		int getLanguageID();
		String getUserName();
		int getAge();
		UserLocation getDefaultLocation();

}
