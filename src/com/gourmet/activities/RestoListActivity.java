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
import com.gourmet.adapters.RestaurantAdapter;
import com.gourmet.database.dao.GourmetRestoDAO;
import com.gourmet.database.services.RestaurantDAOServices;
import com.gourmet.model.AppConstants;
import com.gourmet.model.Restaurant;
import com.gourmet.session.UserSessionManager;

public class RestoListActivity extends Activity {

	private UserSessionManager sessionMgr;
	private GourmetRestoDAO datasourceResto;
	private ListView listView;
	private Button btnLogout;
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        sessionMgr = UserSessionManager.getInstance(getApplicationContext());
        sessionMgr.checkUserSession();  
        datasourceResto  = GourmetRestoDAO.getInstance(getApplicationContext());
		datasourceResto.openDataSource();

        setContentView(R.layout.activity_resto);
    
        listView = (ListView) findViewById(R.id.listViewResto);
        btnLogout = (Button) findViewById(R.id.bouton_logout);
        
        btnLogout.setText(sessionMgr.getUserName()+ " - " + btnLogout.getText());
        

        
        //Get the restaurants list to display (from DB)       
        String loadingReq = getIntent().getStringExtra(AppConstants.REQUEST.toString());   
        setTitle(loadingReq);

        List<Restaurant> restList= handleRestaurantsLoadingRequest(loadingReq);      
        
        Restaurant [] restArr = restList.toArray( new Restaurant [restList.size()]);
        //Setting the adapter
        RestaurantAdapter adapter = new RestaurantAdapter(this, R.layout.list_rest_item, restArr);
        listView.setAdapter(adapter);     
        setListBehaviorOnItemClick();
        setButtonBehaviorOnClick();
        

        
    }


	private void setButtonBehaviorOnClick() {
		
		btnLogout.setOnClickListener(new OnClickListener() {

		    public void onClick(View v) {
		    		sessionMgr.destroySession();
		    }});
		
	}

	
	private List<Restaurant> handleRestaurantsLoadingRequest(String request){
		List<Restaurant> restList = null;
		
		if(request.equals(RestaurantDAOServices.NEARBY_REST_PREF))
			restList = datasourceResto.getAllNearbyRestaurantsMatchingPref();
		
		else if (request.equals(RestaurantDAOServices.REST_PREF))
			restList = datasourceResto.getAllRestaurantsMatchingIngredientsPref();
		
		else if (request.equals(RestaurantDAOServices.ALL_REST))
				restList  = datasourceResto.getAllRestaurants();
		
		else if(request.equals(RestaurantDAOServices.ALL_REST_CLASSIC))
				restList = datasourceResto.getAllRestaurantsClassic(sessionMgr);
		
		else if(request.equals(RestaurantDAOServices.ALL_NEAREST_NO_PREF))
			restList =  datasourceResto.getAllNearbyRestaurants();
		else if(request.equals(RestaurantDAOServices.NEARBY_REST_SEASON))
			restList =datasourceResto.getNearbyRestoServingSeasonalMealsWithPrefIngr();
		


		
		return restList;
	}

	private void setListBehaviorOnItemClick() {
		
	      listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p, View arg1, int position,long arg3) {
					Restaurant selectedResto = (Restaurant) p.getItemAtPosition(position);
					Intent i = new Intent(getApplicationContext(),MealListActivity.class);
					i.putExtra(AppConstants.RESTO_ID.toString(), String.valueOf(selectedResto.getId()));
					startActivity(i);
				}
	        	
	        	}
			);		
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
	}

//
//	@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_resto, menu);
//        return true;
//    }
    
}
