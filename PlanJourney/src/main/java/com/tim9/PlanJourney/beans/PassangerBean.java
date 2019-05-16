package com.tim9.PlanJourney.beans;

public class PassangerBean {
	
	
	private Long seatId;
	private String firstName;
	private String lastName;
	private String passport;
	private double price;
	private Long friendId;
	
	public PassangerBean() {
		// TODO Auto-generated constructor stub
	}

	public PassangerBean(Long seatId, String firstName, String lastName, String passport, double price) {
		super();
		this.seatId = seatId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.price = price;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
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

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	
	

}
