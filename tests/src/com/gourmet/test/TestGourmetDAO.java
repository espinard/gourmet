package com.gourmet.test;

import java.util.List;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.gourmet.database.GourmetDAO;
import com.gourmet.model.Meal;
import com.gourmet.model.Restaurant;

public class TestGourmetDAO extends AndroidTestCase {


	private GourmetDAO dao; 
	
	protected void setUp() throws Exception {
		super.setUp();
	
		try {
			dao = new GourmetDAO(getContext());
			dao.openDataSource();
	
		} catch (SQLException e) {
			fail(e.getMessage());
		}catch (Exception e1){
			fail(e1.getMessage());

		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		try {
			dao.closeDataSource();
	
		} catch (SQLException e) {
			fail(e.getMessage());
			
		}catch (Exception e1){
			fail(e1.getMessage());

		}
	}


	public final void testGourmetDAO() {
		
		assertTrue(dao != null);
		assertTrue(dao instanceof GourmetDAO);
	
	}
	
//	public final void testGetAllRestaurants() {
//		
//		try{
//			List<Restaurant> lRest = null;
//			lRest = dao.getAllRestaurants();
//			assertTrue(lRest != null);
//			assertTrue(lRest.size() >= 0);	
//			assertTrue(lRest.size() == 10);
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		} catch (Exception e1){
//			fail(e1.getMessage());
//
//		}
//	}
	
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
//			lMeal = dao.getAllMeals();
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
	

	public final void testGetNearbyRestos(){
		try{
			List<Restaurant> lRest = null;
			lRest = dao.getAllNearbyRestaurants();
			assertTrue(lRest != null);
			assertTrue(lRest.size() == 10);
		} catch (SQLException e) {
			fail(e.getMessage());
		} catch (Exception e1){
			fail(e1.getMessage());

		}
	}

}
