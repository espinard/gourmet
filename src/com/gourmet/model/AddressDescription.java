package com.gourmet.model;

import com.gourmet.database.gen.Add_DescriptionTable.Add_DescriptionColumns;
import com.gourmet.model.interfaces.IEntityObject;

public class AddressDescription  implements IEntityObject {
	
	private int id;
	private int idAddress;
	private int idLang;
	private String street_name;
	private String cityName;
	private Language langObj;
	private Address addressObj;
	

	public AddressDescription() {
		
	}

	@Override
	public void setId(int id) {
		this.id = id;
		
	}

	@Override
	public void setAttribute(String name, Object value) {
		String val = (String) value;
		int numVal; 
		try {
			numVal = Integer.valueOf(val);
		} catch (NumberFormatException e) {
			numVal = -1;
		}
		
		if(name.endsWith(Add_DescriptionColumns.ID_ADDRESS))
			setIdAddress(numVal);
		if(name.endsWith(Add_DescriptionColumns.ID_LANGUAGE))
			setIdLang(numVal);
		
		if(name.endsWith(Add_DescriptionColumns.STREET_NAME))
			setStreetName(val);
		
		if(name.endsWith(Add_DescriptionColumns.CITY_NAME))
			setCityName(val);
	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof Address)
			setAddressObj((Address) relatedObj);
		
		if(relatedObj instanceof Language){	
			setLangObj((Language) relatedObj);
		}
	}

	/**
	 * @return the idAddress
	 */
	public int getIdAddress() {
		return idAddress;
	}

	/**
	 * @param idAddress the idAddress to set
	 */
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	/**
	 * @return the idLang
	 */
	public int getIdLang() {
		return idLang;
	}

	/**
	 * @param idLang the idLang to set
	 */
	public void setIdLang(int idLang) {
		this.idLang = idLang;
	}

	/**
	 * @return the street_name
	 */
	public String getStreetName() {
		return street_name;
	}

	/**
	 * @param street_name the street_name to set
	 */
	public void setStreetName(String description) {
		this.street_name = description;
	}

	/**
	 * @return the langObj
	 */
	public Language getLangObj() {
		return langObj;
	}

	/**
	 * @param langObj the langObj to set
	 */
	public void setLangObj(Language langObj) {
		this.langObj = langObj;
	}

	/**
	 * @return the addressObj
	 */
	public Address getAddressObj() {
		return addressObj;
	}

	/**
	 * @param addressObj the addressObj to set
	 */
	public void setAddressObj(Address addressObj) {
		this.addressObj = addressObj;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
