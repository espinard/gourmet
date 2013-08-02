package com.gourmet.model;

import com.gourmet.database.gen.AddressTable.AddressColumns;
import com.gourmet.model.interfaces.IEntityObject;

public class Address implements IEntityObject {
	
	private int id;
	private double latitude;
	private double longitude;
	private int postalCode; 
	private int street_Number;
	private String locality;
	private String country;
	private int idRestaurant;
	
	private Restaurant restObj;
	private AddressDescription descriptionObj;

	public Address() {
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	@Override
	public void setAttribute(String name, Object value) {
		String val = (String) value;
		int numVal;
		double doubleVal;
		
		try {
			numVal = Integer.valueOf(val);
			doubleVal = Double.valueOf(val);
		} catch (NumberFormatException e) {
			numVal = - 1;
			doubleVal = -1;
		}
		
		
		if(name.endsWith(AddressColumns._ID))
			setId(numVal);
		if(name.endsWith(AddressColumns.ID_RESTAURANT))
			setIdRestaurant(numVal);
		if(name.endsWith(AddressColumns.LATITUDE))
			setLatitude(doubleVal);
		if(name.endsWith(AddressColumns.LONGITUDE))
			setLongitude(doubleVal);
		if(name.endsWith(AddressColumns.COUNTRY))
			setCountry(val);
		if(name.endsWith(AddressColumns.STREET_NUMBER))
			setStreet_Number(numVal);
		if(name.endsWith(AddressColumns.POSTAL_CODE))
			setPostalCode(numVal);
		
	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof Restaurant)
			setRestObj((Restaurant)relatedObj);
		if(relatedObj instanceof AddressDescription){
			setDescriptionObj((AddressDescription) relatedObj);
			setLocality(getDescriptionObj().getCityName());
		}

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

	/**
	 * @return the postalCode
	 */
	public int getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the street_Number
	 */
	public int getStreet_Number() {
		return street_Number;
	}

	/**
	 * @param street_Number the street_Number to set
	 */
	public void setStreet_Number(int street_Number) {
		this.street_Number = street_Number;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the idRestaurant
	 */
	public int getIdRestaurant() {
		return idRestaurant;
	}

	/**
	 * @param idRestaurant the idRestaurant to set
	 */
	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	/**
	 * @return the restObj
	 */
	public Restaurant getRestObj() {
		return restObj;
	}

	/**
	 * @param restObj the restObj to set
	 */
	public void setRestObj(Restaurant restObj) {
		this.restObj = restObj;
	}

	/**
	 * @return the descriptionObj
	 */
	public AddressDescription getDescriptionObj() {
		return descriptionObj;
	}

	/**
	 * @param descriptionObj the descriptionObj to set
	 */
	public void setDescriptionObj(AddressDescription descriptionObj) {
		this.descriptionObj = descriptionObj;
	}

	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

}
