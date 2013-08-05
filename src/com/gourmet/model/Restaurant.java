/**
 * 
 */
package com.gourmet.model;

import java.util.List;

import android.content.Context;

import com.gourmet.database.dao.GourmetMealDAO;
import com.gourmet.database.gen.RestaurantTable.RestaurantColumns;
import com.gourmet.model.interfaces.IEntityObject;
import com.gourmet.model.interfaces.IUser;

/**
 * @author esp
 *
 */
public class Restaurant implements IEntityObject, IUser {



	private int id;
	private String description;
	private String locality;
	private String foodType; 
	private int minAge;
	private int idRegion;
	private List<Meal> meals; //meals served in this restaurant

	private Chef chef;
	private RestoDescription objDescription;
	private Region region;
	private Address addressObj;

	private Context appContext;




	/**
	 */
	public Restaurant() {

	}


	/**
	 * @param id
	 */
	public Restaurant(int id) {
		this.id = id;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */	
	@Override
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return getDescription();
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		setDescription(name);
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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


	/*
	 * Set the value of the corresponding attribute
	 * Remark: better use reflection 
	 */
	@Override
	public void setAttribute(String attrName, Object value) {

		String val = (String) value; 

		if(attrName.endsWith(RestaurantColumns._ID)){
			int numVal = Integer.valueOf(val);
			setId(numVal);
		}
		
		if(attrName.endsWith(RestaurantColumns.ID_REGION)){
			int numVal = Integer.valueOf(val);
			setIdRegion(numVal);
			return;
		}
		
		if(attrName.endsWith(RestaurantColumns.FOOD_TYPE))	
			setFoodType((String) value);

		if(attrName.endsWith(RestaurantColumns.AGE_MIN)){
			int numVal = Integer.valueOf(val);
			setMinAge(numVal);

		}
		
	}


	private void setAppContext(Context context){
		this.appContext = context;
	}

	/**
	 * @return the meals
	 */
	public List<Meal> getMeals() {
		if(this.meals == null){
			//Load meals from DB

			GourmetMealDAO dao = GourmetMealDAO.getInstance(this.appContext);
			this.meals = dao.getMealsOfRestaurant(this.id);

		}
		return this.meals;
	}


	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		if(relatedObj instanceof Chef){
			setChef((Chef)relatedObj);
		}
		if(relatedObj instanceof RestoDescription ){
			setObjDescription((RestoDescription) relatedObj);
			setDescription(getObjDescription().getDescription());
		}

		if(relatedObj instanceof Region){
			setRegion((Region)relatedObj);
		}
		
		if(relatedObj instanceof Address){
			setAddressObj((Address) relatedObj);
			setLocality(getAddressObj().getLocality());
		}

	}


	/**
	 * @return the chef
	 */
	public Chef getChef() {
		return chef;
	}


	/**
	 * @param chef the chef to set
	 */
	public void setChef(Chef chef) {
		this.chef = chef;
	}


	/**
	 * @return the objDescription
	 */
	public RestoDescription getObjDescription() {
		return objDescription;
	}


	/**
	 * @param objDescription the objDescription to set
	 */
	public void setObjDescription(RestoDescription objDescription) {
		this.objDescription = objDescription;
	}


	/**
	 * @return the foodType
	 */
	public String getFoodType() {
		return foodType;
	}


	/**
	 * @param foodType the foodType to set
	 */
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}


	/**
	 * @return the appContext
	 */
	public Context getAppContext() {
		return appContext;
	}


	/**
	 * @return the minAge
	 */
	public int getMinAge() {
		return minAge;
	}


	/**
	 * @param minAge the minAge to set
	 */
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}


	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}


	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}


	/**
	 * @return the idRegion
	 */
	public int getIdRegion() {
		return idRegion;
	}


	/**
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
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


	@Override
	public int getUserID() {
		return getId();
	}


	@Override
	public int getLanguageID() {
		// Default language is English 
		//For example (validation) purpose, on how to use framework
		return Language.ENGLISH;
	}


	@Override
	public String getUserName() {
		return getName();
	}


	@Override
	public int getAge() {
		return -1;
	}


	@Override
	public UserLocation getDefaultLocation() {
		double lat = getAddressObj().getLatitude();
		double longit = getAddressObj().getLongitude();
		return new UserLocation(lat, longit);
	}





}
