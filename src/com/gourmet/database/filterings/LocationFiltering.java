/**
 * 
 */
package com.gourmet.database.filterings;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.Sql;
import org.dynamicschema.sql.SqlCondition;

import com.github.dynamicschema.android.reification.gen.RestaurantTable;


/**
 * @author esp
 *
 */
public class LocationFiltering extends RelationCondition {

	
	private double latitude;
	private double longitude;
	private double range;
	/**
	 * 
	 */
	public LocationFiltering(double latitude, double longitude, double range) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	

	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {
		
		SqlCondition finalCond = new SqlCondition();
		SqlCondition locCond = null;
		String cond ="";		
		for (int i = 0; i < tables.length; i++) {
			locCond = new SqlCondition();
			cond+=Sql.ABS+ "(" + tables[0].col(RestaurantTable.RestaurantColumns.LOC_LATITUDE)+  " - "+ this.latitude +  " ) <= " + this.range; 
			locCond.and(cond);
			cond+=Sql.ABS+ "(" + tables[0].col(RestaurantTable.RestaurantColumns.LOC_LONGITUDE)+  " - "+ this.longitude +  " ) <= " + this.range; 
			
			finalCond.and(locCond.toString());
		}
		
		return finalCond;
	}



}
