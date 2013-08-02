package com.gourmet.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gourmet.R;
import com.gourmet.model.Restaurant;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

	
	private Context context;
	private int layoutResId;
	Restaurant [] restaurants;
	
	public RestaurantAdapter(Context context, int layoutResourceId, Restaurant[] dataRest) {
		super(context, layoutResourceId, dataRest);
		this.context = context;
		this.layoutResId = layoutResourceId;
		this.restaurants = dataRest;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(this.layoutResId, parent, false);
		
		TextView nameView = (TextView) rowView.findViewById(R.id.rest_name);
		TextView descrView = (TextView) rowView.findViewById(R.id.rest_description);
		TextView localityView = (TextView) rowView.findViewById(R.id.rest_locality);
		
		nameView.setText(this.restaurants[position].getName());
		descrView.setText(this.restaurants[position].getFoodType());
		localityView.setText("Location: " + this.restaurants[position].getLocality());
		
		return rowView;
		
	}


}
