package com.gourmet.test;

import java.util.List;

import android.content.SharedPreferences;
import android.database.SQLException;
import android.test.AndroidTestCase;

import com.gourmet.database.dao.GourmetClientDAO;
import com.gourmet.database.dao.GourmetRestoDAO;
import com.gourmet.model.Client;
import com.gourmet.model.Meal;
import com.gourmet.model.Restaurant;
import com.gourmet.session.UserSessionManager;

public class TestGourmetDAO extends AndroidTestCase {


	private GourmetClientDAO daoCl; 
	private static final String PREF_FILE_NAME= UserSessionManager.PREF_FILE_NAME;

	
	protected void setUp() throws Exception {
		super.setUp();
	
		try {
			daoCl = GourmetClientDAO.getInstance(getContext());
			daoCl.openDataSource();
	
		} catch (SQLException e) {
			fail(e.getMessage());
		}catch (Exception e1){
			fail(e1.getMessage());

		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		try {
			daoCl.closeDataSource();
	
		} catch (SQLException e) {
			fail(e.getMessage());
			
		}catch (Exception e1){
			fail(e1.getMessage());

		}
	}


	public final void testGourmetDAO() {
		
//		assertTrue(daoCl != null);
//		assertTrue(daoCl instanceof GourmetRestoDAO);
//	
	}
	
	
	//Query 1: Requester Client 
	public final void testGetAllNearbyRestaurantsMatchingIngrPref() {
		
//		try{
//			List<Restaurant> lRest = null;
//			lRest = daoCl.getAllRestaurantsMatchingIngredientsPref();
//			assertTrue(lRest != null);
//			assertTrue(lRest.size() >= 0);	
//			assertTrue(lRest.size() == 10);
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		} catch (Exception e1){
//			fail(e1.getMessage());
//
//		}
	}
	
	
	//Query 2: Requester:  client
	public final void testGetAllRestoServingMealWithPreferredSeasonalIngr()
	{
		fail("TODO implement");
	}
	
	
	//Query 3: Requester: Restaurant
	public final void testGetMealsWithSeasonalIngredients(){
		fail("TODO implement");
	}
	
	
	//Query 4: Requester: Restaurant
	public final void testGetClientsCoudlBeInterestedInMeal(){
		fail("TODO implement");
	}
	
	//Query 5: Requester: Client
	public final void testGetAllNearbyRestaurantsServingMealOfDayMatchingPref(){
		fail("TODO implement");
	}
	
	
	//Query 6: Requester: Client
	public final void testGetAllRestaurantAddresses(){
		fail("TODO implement");
	}
	
	
	//Query 7: Requester: Client
	public final void testGetAllRestaurants(){
		fail("TODO implement");
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public final void testGetMealsOfCurrentRestaurant() {
		
		try{
//			SharedPreferences prefs = getContext().getSharedPreferences(PREF_FILE_NAME, 0);
//			SharedPreferences.Editor edit = prefs.edit();
//		
//			edit.putString("language", "2");
//			edit.commit();
//			
//			int idRest = 1;
//			List<Meal> meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() ==  2);
//			
//			idRest = 2;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() ==  4);
//
//			idRest = 3;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() == 0);
//	
//			idRest = 4;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() == 0);
//
//			idRest = 5;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() == 1);
//			
//			idRest = 6;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() == 1);
//			
//			idRest = 7;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() == 0);
//			
//			idRest = 8;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() == 1);
//			
//			idRest = 9;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() == 1);
//			
//			idRest = 10;
//			meals = null;
//			meals = daoCl.getMealsOfCurrentRestaurant(idRest);
//			assert(meals.size() == 1);
			
		} catch (SQLException e) {
			fail(e.getMessage());
		} catch (Exception e1){
			fail(e1.getMessage());

		}
	}
	
//	private final void printRestaurants(String testName, List<Restaurant> rest){
//		System.out.println(testName + ": ");
//		System.out.println("Restaurants List empty: "+  (rest.size() == 0));
//		for (Restaurant restaurant : rest) {
//			printResto(restaurant);
//			System.out.println();
//		}
//	
//	}
//	private final void printResto(Restaurant rest){
//		System.out.println(rest.getName());
//	}
	

//	public final void testGetAllMeals() {
//		
//		try{
//			List<Meal> lMeal = null;
//			lMeal = daoCl.getAllMeals();
//			assertTrue(lMeal != null);	
//			assertTrue(lMeal.size() == 12);
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		} catch (Exception e1){
//			fail(e1.getMessage());
//
//		}
//	}

//	public final void testGetNearbyRestoServingSeasonalMeals() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	public final void testGetMealsWithSeasonalIngredients() {
//		fail("Not yet implemented"); // TODO
//	}
//
	public final void testGetAllClients(){
		List<Client> clList = null;
		clList = daoCl.getAllClients();
		assertTrue(clList !=null);
		
	}
	

	public final void testGetNearbyRestos(){
		try{
			//Set Preferences 

			/*
			 *  Double.valueOf(0.2); //longitude
			 */
			
			SharedPreferences prefs = getContext().getSharedPreferences(PREF_FILE_NAME, 0);
			SharedPreferences.Editor edit = prefs.edit();
			edit.putString("range", "0.2");
//			edit.putString("clientID", "1");
//			edit.commit();
			List<Restaurant> lRest = null;
//			lRest = daoCl.getAllNearbyRestaurants();
//			assertTrue(lRest != null);
//			assertTrue(lRest.size() == 3);
			
//			edit.putString("clientID", "2");
//			edit.commit();
//			lRest = null;
//			lRest = daoCl.getAllNearbyRestaurants();
//			assertTrue(lRest != null);
//			assertTrue(lRest.size() == 3);
		
//			edit.putString("clientID", "3");
//			edit.commit();
//			 lRest = null;
//			lRest = daoCl.getAllNearbyRestaurants();
//			assertTrue(lRest != null);
//			assertTrue(lRest.size() == 2);
			
//			edit.putString("clientID", "4");
//			edit.commit();
//			 lRest = null;
//			lRest = daoCl.getAllNearbyRestaurants();
//			assertTrue(lRest != null);
//			assertTrue(lRest.size() == 5);
//			
//			edit.putString("clientID", "5");
//			edit.commit();
//			 lRest = null;
//			lRest = daoCl.getAllNearbyRestaurants();
//			assertTrue(lRest != null);
//			assertTrue(lRest.size() == 5);
//			
//			edit.putString("clientID", "6");
//			edit.commit();
//			 lRest = null;
//			lRest = daoCl.getAllNearbyRestaurants();
//			assertTrue(lRest != null);
//			assertTrue(lRest.size() == 0);
			

			
			
		} catch (SQLException e) {
			fail(e.getMessage());
		} catch (Exception e1){
			e1.printStackTrace();
			fail("Message "+ e1.getMessage());
			

		}
	}
	
	

	

}
