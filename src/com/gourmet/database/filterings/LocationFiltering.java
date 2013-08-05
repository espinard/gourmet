/**
 * 
 */
package com.gourmet.database.filterings;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.Sql;
import org.dynamicschema.sql.SqlCondition;

import com.gourmet.database.gen.AddressTable.AddressColumns;
import com.gourmet.database.gen.ClientTable.ClientColumns;


/**
 * @author esp
 *
 */
public class LocationFiltering extends RelationCondition {


	private double latitude;
	private double longitude;
	private double range;
	private boolean usedByResto;
	/**
	 * 
	 */
	public LocationFiltering(double latitude, double longitude, double range, boolean byResto) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.range = range;
		this.usedByResto = byResto;
	}


	/*
	 * Calculating Distance Between two coordinates using Taxicab geometry = Not the real geometry because SQLite do not support trigonometric functions 
	 * http://en.wikipedia.org/wiki/Taxicab_geometry
	 *  Distance = abs(latitude1 - latitude2) + abs(longitude1 - longitude2)
	 */


	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {

		SqlCondition finalCond = new SqlCondition();
		SqlCondition locCond = null;
		for (int i = 0; i < tables.length; i++) {
			String distanceCalc = null; 

			if(usedByResto){
				distanceCalc =  Sql.ABS+ "(" + tables[i].col(ClientColumns.LOC_LATITUDE)+  " - "+ this.latitude + ") + " + 		
						Sql.ABS+ "(" + tables[i].col(ClientColumns.LOC_LONGITUDE)+  " - "+ this.longitude+ ")";
			}else {
				distanceCalc = Sql.ABS+ "(" + tables[i].col(AddressColumns.LATITUDE)+  " - "+ this.latitude + ") + " + 

											Sql.ABS+ "(" + tables[i].col(AddressColumns.LONGITUDE)+  " - "+ this.longitude+ ")";

			}	

			locCond =new SqlCondition().lEq(distanceCalc, this.range);
			finalCond.and(locCond.toString());
		}

		return finalCond;
	}



}
