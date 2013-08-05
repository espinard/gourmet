package com.gourmet.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.gourmet.R;
import com.gourmet.adapters.ClientAdapter;
import com.gourmet.database.dao.GourmetClientDAO;
import com.gourmet.model.AppConstants;
import com.gourmet.model.Client;
import com.gourmet.session.UserSessionManager;

public class ClientListActivity extends Activity {

	private UserSessionManager sessionMgr;
	private GourmetClientDAO datasourceClient;
	private ListView listView;
	private Button btnLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_list);

		sessionMgr = UserSessionManager.getInstance(getApplicationContext());
		sessionMgr.checkUserSession();  

		listView = (ListView) findViewById(R.id.listViewClient);
		btnLogout = (Button) findViewById(R.id.bouton_logout_Client);
		btnLogout.setText(sessionMgr.getUserName()+ " - " + btnLogout.getText());

        int loadingReqMealID = getIntent().getIntExtra(AppConstants.MEAL_ID.toString(), -1);
        String mealName = getIntent().getStringExtra(AppConstants.MEAL_NAME.toString());
        
        setTitle("Potential Clients for Meal: " + mealName);
        
        datasourceClient = GourmetClientDAO.getInstance(getApplicationContext());
        datasourceClient.openDataSource();
        
        List<Client> clients = datasourceClient.getClientsInterestedInMeal(loadingReqMealID);
        Client [] clientArr = clients.toArray(new Client [clients.size()]);
        ClientAdapter adapter = new ClientAdapter(this, R.layout.list_client_item, clientArr);
        listView.setAdapter(adapter);
        
        setButtonBehaviorOnClick();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_list, menu);
		return true;
	}
	
	

	private void setButtonBehaviorOnClick() {
		
		btnLogout.setOnClickListener(new OnClickListener() {

		    public void onClick(View v) {
		    		sessionMgr.destroySession();
		    }});
		
	}

}
