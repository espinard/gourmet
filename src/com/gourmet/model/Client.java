/**
 * 
 */
package com.gourmet.model;


import com.gourmet.database.gen.ClientTable.ClientColumns;
import com.gourmet.model.interfaces.IEntityObject;
import com.gourmet.model.interfaces.IUser;

/**
 * @author esp
 *
 */
public class Client implements IEntityObject, IUser {

	
	private int id;
	private String clName;
	private String clSurname;
	private int languageID;
	private int age;
	private String country;
	private double latitude;
	private double longitude;
	
	private Language objLang; //preferred language for this current client
	 
	 
	/**
	 * 
	 */
	public Client() {
		this.clName = null;
		this.clSurname = null;
	
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	@Override
	public void setAttribute(String attrName, Object value) {
		
		String val = (String) value;
		int numVal;
		double doubleVal;
		
		try {
			numVal = Integer.valueOf(val);
			doubleVal = Double.valueOf(val);
		} catch (NumberFormatException e) {
			numVal = -1;
			doubleVal = -1;
		}
		
		if(attrName.endsWith(ClientColumns._ID))
			setId(numVal);
		
		if(attrName.endsWith(ClientColumns.CLI_NAME))
			setClName(val);
		
		if(attrName.endsWith(ClientColumns.CLI_SURNAME))
			setClSurname(val);		
		
		if(attrName.endsWith(ClientColumns.ID_LANGUAGE))
			setLanguageID(numVal);	
		
		if(attrName.endsWith(ClientColumns.AGE))
			setAge(Integer.valueOf((String)value));	
		
		if(attrName.endsWith(ClientColumns.COUNTRY))
			setCountry((String)value);	
		
		if(attrName.endsWith(ClientColumns.LOC_LONGITUDE))
			setLongitude(doubleVal);
		
		if(attrName.endsWith(ClientColumns.LOC_LATITUDE))
			setLatitude(doubleVal);
			
			
	}

	/**
	 * @return the clName
	 */
	public String getClName() {
		return clName;
	}

	/**
	 * @param clName the clName to set
	 */
	public void setClName(String clName) {
		this.clName = clName;
	}

	/**
	 * @return the clSurname
	 */
	public String getClSurname() {
		return clSurname;
	}

	/**
	 * @param clSurname the clSurname to set
	 */
	public void setClSurname(String clSurname) {
		this.clSurname = clSurname;
	}

	/**
	 * @return the languageID
	 */
	@Override
	public int getLanguageID() {
		return languageID;
	}

	/**
	 * @param languageID the languageID to set
	 */
	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	@Override
	public int getUserID() {
		return this.id;
	}

	@Override
	public String getUserName() {
		return this.clName;
	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof Language){
			setObjLang((Language) relatedObj);
		}
		
	}

	/**
	 * @return the objLang
	 */
	public Language getObjLang() {
		return objLang;
	}

	/**
	 * @param objLang the objLang to set
	 */
	public void setObjLang(Language objLang) {
		this.objLang = objLang;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
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
