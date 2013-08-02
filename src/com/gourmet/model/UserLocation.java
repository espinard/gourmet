/**
 * 
 */
package com.gourmet.model;

/**
 * @author esp
 *
 */
public class UserLocation {

	private double latitude;
	private double longitude;
	/**
	 * @param latitude
	 * @param longitude
	 */
	public UserLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}


	
	/**
	 * 
	 */
	public UserLocation() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}



	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}



	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
