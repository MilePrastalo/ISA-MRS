package com.tim9.PlanJourney.models.flight;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tim9.PlanJourney.models.RegisteredUser;

@Entity
public class FlightReservation {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private RegisteredUser user;
	
	@OneToOne
	private Seat seat;
	
	//samo za neregistrovane putnike
	@OneToMany
	private Set<Passanger> passangers;
	
	@ManyToOne
	private Flight flight;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "confirmed")
	private boolean confirmed;
	
	@Column(name = "callerInfo")
	private String callerInfo;
	
	public FlightReservation() {
		// TODO Auto-generated constructor stub
	}

	public FlightReservation(RegisteredUser user, Seat seat, Set<Passanger> passangers, Flight flight,
			double price, Date date, boolean confirmed, String callerInfo) {
		super();
		this.user = user;
		this.seat = seat;
		this.passangers = passangers;
		this.flight = flight;
		this.price = price;
		this.date = date;
		this.confirmed = confirmed;
		this.callerInfo = callerInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Set<Passanger> getPassangers() {
		return passangers;
	}

	public void setPassangers(Set<Passanger> passangers) {
		this.passangers = passangers;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getCallerInfo() {
		return callerInfo;
	}

	public void setCallerInfo(String callerInfo) {
		this.callerInfo = callerInfo;
	}

	

}
