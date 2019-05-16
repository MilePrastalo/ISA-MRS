package com.tim9.PlanJourney.models.flight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.tim9.PlanJourney.models.RegisteredUser;

@Entity
public class Passanger {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "firstName", unique = false, nullable = false)
	private String firstName;

	@Column(name = "lastName", unique = false, nullable = false)
	private String lastName;

	@Column(name = "passport", unique = false, nullable = false)
	private String passport;
	
	@Column(name = "price", unique = false, nullable = false)
	private double price;
	
	@OneToOne
	private RegisteredUser friend;
	
	@OneToOne
	private Seat seat;

	public Passanger() {
		// TODO Auto-generated constructor stub
	}

	public Passanger( String firstName, String lastName, String passport, Seat seat, RegisteredUser friend, double price) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.seat = seat;
		this.friend = friend;
		this.price = price;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Seat getSeat() {
		return seat;
	}


	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public RegisteredUser getFriend() {
		return friend;
	}

	public void setFriend(RegisteredUser friend) {
		this.friend = friend;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
