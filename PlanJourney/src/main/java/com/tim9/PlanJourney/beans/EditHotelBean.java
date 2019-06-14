package com.tim9.PlanJourney.beans;

public class EditHotelBean {
	private String oldName;
	private String newName;
	private String cityName;
	private String newDescription;

	public EditHotelBean(String oldName, String newName, String cityName, String newDescription) {
		this.oldName = oldName;
		this.newName = newName;
		this.cityName = cityName;
		this.newDescription = newDescription;
	}

	public EditHotelBean() {
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getNewDescription() {
		return newDescription;
	}

	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}

}
