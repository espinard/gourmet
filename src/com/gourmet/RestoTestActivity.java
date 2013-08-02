package com.gourmet;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.gourmet.adapters.RestaurantAdapter;
import com.gourmet.database.dao.GourmetRestoDAO;
import com.gourmet.model.Restaurant;

public class RestoTestActivity extends ListActivity {

	
	private GourmetRestoDAO datasource;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_resto_test);
		datasource = GourmetRestoDAO.getInstance(getApplicationContext());
		
		List<Restaurant> restos = datasource.getAllRestaurant();
		Restaurant [] restoArr = restos.toArray(new Restaurant[restos.size()]);
		
		RestaurantAdapter adapter = new RestaurantAdapter(getApplicationContext(), R.layout.list_rest_item, restoArr);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resto_test, menu);
		return true;
	}



	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		datasource.openDataSource();
	}

}
