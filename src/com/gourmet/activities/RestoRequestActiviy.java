package com.gourmet.activities;

import com.gourmet.R;
import com.gourmet.R.layout;
import com.gourmet.R.menu;
import com.gourmet.database.dao.GourmetAddressDAO;
import com.gourmet.database.services.ClientDAOServices;
import com.gourmet.database.services.MealDAOServices;
import com.gourmet.database.services.RestaurantDAOServices;
import com.gourmet.model.AppConstants;
import com.gourmet.session.UserSessionManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class RestoRequestActiviy extends Activity {

	
	private UserSessionManager sessionMgr;
	private String [] options;
	private Button btnLogout ;
	private ListView lViewOptions; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resto_request_activiy);
		
		sessionMgr = UserSessionManager.getInstance(getApplicationContext());  
		sessionMgr.checkUserSession();    
		
		btnLogout = (Button) findViewById(R.id.btnLogoutRestoReq);
		lViewOptions=  (ListView) findViewById(R.id.listViewRestoReq);
		setTitle("Restaurant User options");
        btnLogout.setText(sessionMgr.getUserName()+ " - " + btnLogout.getText());

        options = new String [] {
				MealDAOServices.REST_MEAL_SEASON,
				ClientDAOServices.CLIENT_INTERESTED_MEAL
		};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);

		lViewOptions.setAdapter(adapter);
		setListBehaviorOnItemClick();
		setButtonBehaviorOnClick();
        
	}
	
	

	private void setButtonBehaviorOnClick() {
		btnLogout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				sessionMgr.destroySession();
			}});

	}

	private void setListBehaviorOnItemClick() {
		lViewOptions.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
				String selectedOption  = (String) parent.getItemAtPosition(position);
				Intent i = new  Intent(getApplicationContext(), MealListActivity.class);
				
				int currentRestoID = sessionMgr.getNumericValue(UserSessionManager.RESTO_ID_KEY);
				i.putExtra(AppConstants.RESTO_ID.toString(), String.valueOf(currentRestoID));
				
				if(selectedOption.equals(MealDAOServices.REST_MEAL_SEASON))
					i.putExtra(AppConstants.REQUEST_CLIENT_SEASON_MEAL.toString(), AppConstants.REQUEST_CLIENT_SEASON_MEAL.toString());
				else
					i.putExtra(AppConstants.REQUEST_POTENTIAL_CLIENTS.toString(), AppConstants.REQUEST_POTENTIAL_CLIENTS.toString());
				
				startActivity(i);
			}

		});	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resto_request_activiy, menu);
		return true;
	}

}
