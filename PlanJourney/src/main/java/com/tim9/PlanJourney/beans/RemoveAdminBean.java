package com.tim9.PlanJourney.beans;

public class RemoveAdminBean {
	private String username;
	private String firstName;
	private String lastName;
	private String hotelName;

	public RemoveAdminBean(String username, String firstName, String lastName, String hotelName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hotelName = hotelName;
	}

	public RemoveAdminBean() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

}
