package com.gourmet.model;

public enum AppConstants {
	
	REQUEST("request"),
	RESTO_ID("restoID"),
	MEAL_ID("mealID"),
	MEAL_NAME("mealName"),
	REQUEST_CLIENT_SEASON_MEAL("seasonalMealsResto"),
	REQUEST_POTENTIAL_CLIENTS("potentialClients");
	
	private String val;
	
	
	AppConstants(String value){
		this.val = value;
	}


	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.val;
	}
	

}
