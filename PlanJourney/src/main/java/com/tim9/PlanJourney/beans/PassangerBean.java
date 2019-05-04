package com.tim9.PlanJourney.beans;

public class PassangerBean {
	
	
	private Long seatId;
	private String firstName;
	private String lastName;
	private String passport;
	
	public PassangerBean() {
		// TODO Auto-generated constructor stub
	}

	public PassangerBean(Long seatId, String firstName, String lastName, String passport) {
		super();
		this.seatId = seatId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
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
	
	

}
