package com.tim9.PlanJourney.beans;

public class FriendRequestBean {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private boolean accepted;
	
	
	public FriendRequestBean() {
		// TODO Auto-generated constructor stub
	}


	public FriendRequestBean(Long id, String firstName, String lastName, String username, boolean accepted) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.accepted = accepted;
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


	public boolean isAccepted() {
		return accepted;
	}


	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
