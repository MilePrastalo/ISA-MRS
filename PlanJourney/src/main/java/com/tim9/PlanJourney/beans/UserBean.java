package com.tim9.PlanJourney.beans;

public class UserBean {
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String repeatedPass;
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserBean(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getRepeatedPass() {
		return repeatedPass;
	}


	public void setRepeatedPass(String repeatedPass) {
		this.repeatedPass = repeatedPass;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	

}
