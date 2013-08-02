package com.gourmet.model;

public enum AppConstants {
	
	REQUEST("request"),
	RESTO_ID("restoID");
	
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
