package com.gourmet.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
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

	private boolean reqPotentialClient;
	private boolean reqSeasonMeal; 

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sessionMgr = UserSessionManager.getInstance(getApplicationContext());
		sessionMgr.checkUserSession();  
		datasourceMeal  = GourmetMealDAO.getInstance(getApplicationContext());
		datasourceMeal.openDataSource();

		setContentView(R.layout.activity_meal);

		String req =  getIntent().getStringExtra(AppConstants.RESTO_ID.toString());
		String reqRestoSeasonal = getIntent().getStringExtra(AppConstants.REQUEST_CLIENT_SEASON_MEAL.toString());
		String reqRestoPotentialClient =  getIntent().getStringExtra(AppConstants.REQUEST_POTENTIAL_CLIENTS.toString());

		//Load meals 
		if(reqRestoSeasonal == null && reqRestoPotentialClient == null ){
			setTitle("Meals Served by Restaurant "+ req);
		}else {

			if(reqRestoSeasonal != null){
				setTitle("Season Meals In Restaurant " + req);
				reqSeasonMeal = true;
			}

			if(reqRestoPotentialClient != null){
				setTitle("Select a meal to search for potential clients");	   
				reqPotentialClient = true;

			}
		}

		List<Meal> meals = null;

		if(!reqSeasonMeal ){
			meals =  handleMealLoadingRequest(req);
		}else{	        	
			meals = datasourceMeal.getMealsWithSeasonalIngredients();
		}

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
		meals = datasourceMeal.getMealsOfRestaurant(restoID);		
		return meals;
	}
	private void setButtonBehaviorOnClick() {

		btnLogout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				sessionMgr.destroySession();
			}});

	}


	private void setListBehaviorOnItemClick() { 
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> p, View arg1, int position,long arg3) {
				Meal selectedMeal = (Meal) p.getItemAtPosition(position);
				int mealID = selectedMeal.getId_meal();

				if(reqPotentialClient){
						Intent i = new Intent(getApplicationContext(),ClientListActivity.class);
						i.putExtra(AppConstants.MEAL_ID.toString(), mealID);
						i.putExtra(AppConstants.MEAL_NAME.toString(), selectedMeal.getDescription());
						startActivity(i);
				}
			}

		 });		

	}


}
