package com.tim9.PlanJourney.models;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
public abstract class User {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="username", unique=true, nullable=false)
	private String username;
	@Column(name="password", unique=false, nullable=false)
	private String password;
	@Column(name="firstName", unique=false, nullable=false)
	private String firstName;
	@Column(name="lastName", unique=false, nullable=false)
	private String lastName;
	@Column(name="email", unique=false, nullable=false)
	private String email;
	
	public User() {
	}
	
	public User(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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
	
	
	
	
	
	
	

}
