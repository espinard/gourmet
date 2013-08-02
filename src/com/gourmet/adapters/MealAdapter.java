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
import com.gourmet.model.Meal;

/**
 * @author esp
 *
 */
public class MealAdapter extends ArrayAdapter<Meal> {

	private Context context;
	private int layoutResId;
	Meal [] meals;
	
	
	/**
	 * @param context
	 * @param textViewResourceId
	 * @param objects
	 */
	public MealAdapter(Context context, int textViewResourceId, Meal[] dataMeals) {
		super(context, textViewResourceId, dataMeals);
		
		this.context = context;
		this.layoutResId = textViewResourceId;
		this.meals = dataMeals;
	}


	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(this.layoutResId, parent, false);
		
		TextView descrView =  (TextView) rowView.findViewById(R.id.meal_description);
		TextView budgetView = (TextView) rowView.findViewById(R.id.meal_budget);
		
		descrView.setText(this.meals[position].getDescription());
		String budget = this.meals[position].getBudget_min() + " - " + this.meals[position].getBudget_max(); 
		budgetView.setText(budget);
		
		return rowView;
	}

	

}
