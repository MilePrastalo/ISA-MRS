package com.tim9.PlanJourney.beans;

public class EditCityBean {
	private String oldName;
	private String newName;

	public EditCityBean(String oldName, String newName) {
		this.oldName = oldName;
		this.newName = newName;
	}

	public EditCityBean() {
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

}
