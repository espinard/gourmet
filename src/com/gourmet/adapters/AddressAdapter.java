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
import com.gourmet.model.Address;

/**
 * @author esp
 *
 */
public class AddressAdapter extends ArrayAdapter<Address> {

	private Context context;
	private int layoutResId;
	Address [] addresses;
	
	
	/**
	 * @param context
	 * @param textViewResourceId
	 * @param objects
	 */
	public AddressAdapter(Context context, int textViewResourceId, Address[] dataAddr) {
		super(context, textViewResourceId, dataAddr);
		
		this.context = context;
		this.layoutResId = textViewResourceId;
		this.addresses = dataAddr;
	}


	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(this.layoutResId, parent, false);
		
		TextView cityNameView =  (TextView) rowView.findViewById(R.id.CityName);
		TextView streetNameView = (TextView) rowView.findViewById(R.id.streetName);
		TextView postalCodeView =  (TextView) rowView.findViewById(R.id.PostalCode);
		
		cityNameView.setText(this.addresses[position].getLocality());
		streetNameView.setText(this.addresses[position].getStreetName());
		postalCodeView.setText(String.valueOf(this.addresses[position].getPostalCode()));
		
		return rowView;
	}

	

}
