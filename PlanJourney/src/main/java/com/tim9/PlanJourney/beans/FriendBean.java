package com.tim9.PlanJourney.beans;

import com.tim9.PlanJourney.models.RegisteredUser;

public class FriendBean {
	
	private UserBean user;
	private String state;
	
	public FriendBean() {
		// TODO Auto-generated constructor stub
	}

	public FriendBean(UserBean user, String status) {
		super();
		this.user = user;
		this.state = status;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String getStatus() {
		return state;
	}

	public void setStatus(String status) {
		this.state = status;
	}
	
	

}
