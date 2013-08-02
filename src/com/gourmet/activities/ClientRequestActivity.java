package com.gourmet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.gourmet.R;
import com.gourmet.database.services.RestaurantDAOServices;
import com.gourmet.model.AppConstants;
import com.gourmet.session.UserSessionManager;

public class ClientRequestActivity extends Activity {


	private UserSessionManager sessionMgr;
	private String [] options;
	private Button btnLogout ;
	private ListView lViewOptions; 


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sessionMgr = new UserSessionManager(getApplicationContext());  
		sessionMgr.checkUserSession();    

		setContentView(R.layout.activity_cli_req);

		btnLogout = (Button) findViewById(R.id.btnLogoutCliReq);
		lViewOptions=  (ListView) findViewById(R.id.listViewCliReq);

        btnLogout.setText(sessionMgr.getUserName()+ " - " + btnLogout.getText());

		
		options = new String [] {
				RestaurantDAOServices.NEARBY_REST_KEY,
				RestaurantDAOServices.REST_PREF_KEY,
				RestaurantDAOServices.REST_SEASON
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
				Intent i = new Intent(getApplicationContext(), RestoListActivity.class);
				i.putExtra(AppConstants.REQUEST.toString(), selectedOption);
				startActivity(i);
			}

		});		

	}



}
