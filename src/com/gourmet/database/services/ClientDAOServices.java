/**
 * Interface of services offered to the Restaurant as application user
 */
package com.gourmet.database.services;

import java.util.List;

import com.gourmet.model.Client;
import com.gourmet.model.Meal;

/**
 * @author esp
 *
 */
public interface ClientDAOServices {
	
	/**
	 * Clients that can be interested in a particular menu
	 * @param menu
	 * @return
	 */
	////Validation Query 4 
	List<Client> getClientsInterestedInMeal(Meal meal);
	/**
	 * Meals of the current restaurant using the application. The restaurant is identified by its ID 
	 * @return meals of the current restaurant
	 */
	
	
	List<Client> getAllClients();
}
