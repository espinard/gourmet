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
import com.gourmet.adapters.AddressAdapter;
import com.gourmet.database.dao.GourmetAddressDAO;
import com.gourmet.model.Address;
import com.gourmet.model.AppConstants;
import com.gourmet.session.UserSessionManager;

public class AddressActivity extends Activity {

	private UserSessionManager sessionMgr;
	private GourmetAddressDAO dataSourceAddr;
	private ListView listView;
	private Button btnLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);

		sessionMgr = UserSessionManager.getInstance(getApplicationContext());
		sessionMgr.checkUserSession(); 

		dataSourceAddr = GourmetAddressDAO.getInstance(getApplicationContext());
		dataSourceAddr.openDataSource();	
		
		btnLogout = (Button) findViewById(R.id.bouton_logout_Addr);
		btnLogout.setText(sessionMgr.getUserName()+ " - " + btnLogout.getText());
		
		
        String loadingReq = getIntent().getStringExtra(AppConstants.REQUEST.toString());    
        List<Address> addr = handleAddrLoadingRequest(loadingReq);
        Address [] addrArr = addr.toArray(new Address [addr.size()]);
        
        AddressAdapter adapter = new AddressAdapter(getApplicationContext(), R.layout.list_address_item, addrArr);
		listView = (ListView) findViewById(R.id.listViewAddr);
		
		listView.setAdapter(adapter);
		
		setButtonBehaviorOnClick();

		
		
	}
	
	
	private List<Address> handleAddrLoadingRequest(String request){
		List<Address> addList = null;
		
		if(request.equals(GourmetAddressDAO.ALL_ADDR))
			addList = dataSourceAddr.getAllRestaurantAddressesFramework();
		else
			addList = dataSourceAddr.getAllRestaurantAddressesClassic(sessionMgr);
		return addList;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.address, menu);
		return true;
	}
	
	
private void setButtonBehaviorOnClick() {
		
		btnLogout.setOnClickListener(new OnClickListener() {

		    public void onClick(View v) {
		    		sessionMgr.destroySession();
		    }});
		
	}

}
