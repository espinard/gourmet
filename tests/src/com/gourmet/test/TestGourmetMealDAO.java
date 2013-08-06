package com.gourmet.test;

import java.util.List;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.gourmet.database.context.DBContextManager;
import com.gourmet.database.dao.GourmetMealDAO;
import com.gourmet.model.Meal;
import com.gourmet.session.UserSessionManager;

public class TestGourmetMealDAO extends AndroidTestCase {

	
	private GourmetMealDAO mealDAO;
	private UserSessionManager session;
	private DBContextManager dbContext;
	
	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		try {
			session = UserSessionManager.getInstance(getContext());
			session.destroySession();
			dbContext = DBContextManager.getInstance(getContext());
			
			mealDAO = GourmetMealDAO.getInstance(getContext());
			mealDAO.openDataSource();
	
		} catch (SQLException e) {
			fail(e.getMessage());
		}catch (Exception e1){
			fail(e1.getMessage());

		}
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		try {
			mealDAO.closeDataSource();
	
		} catch (SQLException e) {
			fail(e.getMessage());
			
		}catch (Exception e1){
			fail(e1.getMessage());

		}	
	
	}
	
	public final void testMealsWithSeasonalIngredients(){
		
		session.registerRawData(UserSessionManager.PROFILE_TYPE, UserSessionManager.RESTAURANT);
		session.registerNumeric(UserSessionManager.RESTO_ID_KEY, 1);
		session.registerNumeric(UserSessionManager.LANG_ID_KEY, 2);
		session.registerRawData(UserSessionManager.LOC_LATITUDE, String.valueOf(50.874011));
		session.registerRawData(UserSessionManager.LOC_LONGITUDE, String.valueOf(4.37854));
		dbContext.setUserProfile(session);

		List<Meal> meals = null;
		meals = mealDAO.getMealsWithSeasonalIngredients();
		assertTrue(meals != null);
		
		
		
		
	}
	
}
