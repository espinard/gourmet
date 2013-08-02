package com.gourmet.model;

import com.gourmet.database.gen.RestoDescriptionTable.RestoDescriptionColumns;
import com.gourmet.model.interfaces.IEntityObject;

public class RestoDescription implements IEntityObject {

	private int id;
	private String description;
	private int idLang;
	private int idRestaurant;
	private Language langOj;
	
	public RestoDescription() {
		
	}

	@Override
	public void setId(int id) {
		this.id =id;
	}

	@Override
	public void setAttribute(String name, Object value) {
		String val = (String) value;
		
		int numVal;
		try {
			numVal = Integer.valueOf(val);
		} catch (NumberFormatException e) {
			numVal = -1; //current va
		}
		
		if(name.endsWith(RestoDescriptionColumns.ID_LANGUAGE)){
			setIdLang(numVal);
			return;
		}
		
		if(name.endsWith(RestoDescriptionColumns.DESCRIPTION)){
			setDescription(val);
			return;
		}
		
		if(name.endsWith(RestoDescriptionColumns.ID_RESTAURANT))
			setIdRestaurant(numVal);
	

	}

	@Override
	public void setAssociatedObject(IEntityObject relatedObj) {
		
		if(relatedObj instanceof Language){
			setLangOj((Language) relatedObj);
		}

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
	 * @return the langOj
	 */
	public Language getLangOj() {
		return langOj;
	}

	/**
	 * @param langOj the langOj to set
	 */
	public void setLangOj(Language langOj) {
		this.langOj = langOj;
	}

}
