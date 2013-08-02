package com.gourmet.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.gourmet.R;
import com.gourmet.adapters.MealAdapter;
import com.gourmet.database.dao.GourmetMealDAO;
import com.gourmet.model.AppConstants;
import com.gourmet.model.Meal;
import com.gourmet.session.UserSessionManager;

public class MealListActivity extends Activity {

	private UserSessionManager sessionMgr;
	private GourmetMealDAO datasourceMeal;
	private ListView listView;
	private Button btnLogout;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        
	        sessionMgr = new UserSessionManager(getApplicationContext());
	        sessionMgr.checkUserSession();  
	        datasourceMeal  = GourmetMealDAO.getInstance(getApplicationContext());
	        datasourceMeal.openDataSource();
	              
	        setContentView(R.layout.activity_meal);
	        
	        String req =  getIntent().getStringExtra(AppConstants.RESTO_ID.toString());
	        //Load meals 
	        
	        List<Meal> meals = handleMealLoadingRequest(req);
	        Meal [] mealsArr =  meals.toArray(new Meal [meals.size()]);

	        listView = (ListView) findViewById(R.id.lViewMeals);
	        btnLogout = (Button) findViewById(R.id.btnLogoutMeal);
	        
	        btnLogout.setText(sessionMgr.getUserName()+ " - " + btnLogout.getText());
	        
	        MealAdapter adapter = new MealAdapter(this, R.layout.list_meal_item, mealsArr);
	        listView.setAdapter(adapter);
	        
	        setListBehaviorOnItemClick();
	        setButtonBehaviorOnClick();
	        
	}
	
	


	private List<Meal> handleMealLoadingRequest(String request){
		List<Meal> meals = null;
		int restoID = Integer.valueOf(request);
		meals = datasourceMeal.getMealsOfCurrentRestaurant(restoID);		
		return meals;
	}
	private void setButtonBehaviorOnClick() {
		
		btnLogout.setOnClickListener(new OnClickListener() {

		    public void onClick(View v) {
		    		sessionMgr.destroySession();
		    }});
				
	}


	private void setListBehaviorOnItemClick() {
		//TODO
		
	}

	

}
