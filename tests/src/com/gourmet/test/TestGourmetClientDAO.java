package com.gourmet.test;

import java.util.List;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.gourmet.database.context.DBContextManager;
import com.gourmet.database.dao.GourmetClientDAO;
import com.gourmet.model.Client;
import com.gourmet.session.UserSessionManager;

public class TestGourmetClientDAO extends AndroidTestCase {

	
	
	private GourmetClientDAO daoCl;
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
			daoCl = GourmetClientDAO.getInstance(getContext());
			daoCl.openDataSource();
	
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
			daoCl.closeDataSource();
	
		} catch (SQLException e) {
			fail(e.getMessage());
			
		}catch (Exception e1){
			fail(e1.getMessage());

		}
	}
	
	
	public final void testClientsInterestedInMeal() {
		
		session.registerRawData(UserSessionManager.PROFILE_TYPE, UserSessionManager.RESTAURANT);
		session.registerNumeric(UserSessionManager.RESTO_ID_KEY, 1);
		session.registerNumeric(UserSessionManager.LANG_ID_KEY, 2);
		session.registerRawData(UserSessionManager.LOC_LATITUDE, String.valueOf(50.874011));
		session.registerRawData(UserSessionManager.LOC_LONGITUDE, String.valueOf(4.37854));
		dbContext.setUserProfile(session);

		
		List<Client> clients = null;
		int testMealID = 1;
		clients = daoCl.getClientsInterestedInMeal(testMealID);
		assertTrue(clients != null);
		
	}


	


}
