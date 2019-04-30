package com.tim9.PlanJourney.beans;

import com.tim9.PlanJourney.models.RegisteredUser;

public class FriendBean {
	
	private RegisteredUser user;
	private String state;
	
	public FriendBean() {
		// TODO Auto-generated constructor stub
	}

	public FriendBean(RegisteredUser user, String status) {
		super();
		this.user = user;
		this.state = status;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public String getStatus() {
		return state;
	}

	public void setStatus(String status) {
		this.state = status;
	}
	
	

}
