/**
 * 
 */
package com.gourmet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gourmet.R;
import com.gourmet.model.Client;

/**
 * @author esp
 *
 */
public class ClientAdapter extends ArrayAdapter<Client> {

	private Context context;
	private int layoutResId;
	Client [] clients;
	
	
	/**
	 * @param context
	 * @param textViewResourceId
	 * @param objects
	 */
	public ClientAdapter(Context context, int textViewResourceId, Client[] dataCli) {
		super(context, textViewResourceId, dataCli);
		
		this.context = context;
		this.layoutResId = textViewResourceId;
		this.clients = dataCli;
	}


	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(this.layoutResId, parent, false);
		
		TextView clientNameView =  (TextView) rowView.findViewById(R.id.cli_name);
		TextView cliSurnNameView = (TextView) rowView.findViewById(R.id.cli_surname);
		
		clientNameView.setText(this.clients[position].getClName());
		cliSurnNameView.setText(this.clients[position].getClSurname());
		
		return rowView;
	}

	

}
