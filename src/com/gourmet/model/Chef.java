package com.gourmet.model;

import com.gourmet.database.gen.ChefTable.ChefColumns;
import com.gourmet.model.interfaces.IEntityObject;

public class Chef implements IEntityObject{
	
	private int id;
	private String firstName;
	private String lastName;
	private int idRestaurant;
	
	private Restaurant restaurant; //restaurant for the current chef is in charge
	

	public Chef() {
	}

	/* (non-Javadoc)
	 * @see com.gourmet.model.IGourmetObject#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id ;
	}

	/* (non-Javadoc)
	 * @see com.gourmet.model.IGourmetObject#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String attrName, Object value) {
		
		String val = (String) value;
		int numVal; 
		try {
			numVal = Integer.valueOf(val);
		} catch (NumberFormatException e) {
			numVal = -1;
		}
		
		if(attrName.endsWith(ChefColumns.FIRSTNAME))
			setFirstName((String) value);
		
		if(attrName.endsWith(ChefColumns.LASTNAME))
			setLastName((String) value);
		
		if(attrName.endsWith(ChefColumns.ID_RESTAURANT))
			setIdRestaurant(numVal);
		
		
	}

	/* (non-Javadoc)
	 * @see com.gourmet.model.IGourmetObject#setAssociatedObject(com.gourmet.model.IGourmetObject)
	 */
	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof Restaurant){
			setRestaurant((Restaurant) relatedObj);
		}
		
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	

}
