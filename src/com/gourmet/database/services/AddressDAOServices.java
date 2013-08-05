package com.gourmet.database.services;

import java.util.List;

import com.gourmet.model.Address;
import com.gourmet.session.UserSessionManager;

public interface AddressDAOServices {

	public static final String ALL_ADDR = "All Resto Addresses (Validation) ";
	List<Address> getAllRestaurantAddressesFramework();
	
	public static final String ALL_ADDR_CLASSIC = "All Resto Addresses Classic (Validation)";
	List<Address> getAllRestaurantAddressesClassic(UserSessionManager session);
	
}
