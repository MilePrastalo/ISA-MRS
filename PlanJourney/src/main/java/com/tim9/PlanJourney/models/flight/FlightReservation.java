package com.tim9.PlanJourney.models.flight;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.Reservation;

@Entity
public class FlightReservation extends Reservation {
	
	
	@OneToOne
	private Seat seat;
	
	//samo za neregistrovane putnike
	@OneToMany
	private Set<Passanger> passangers = new HashSet<Passanger>();
	
	@ManyToOne
	private Flight flight;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "confirmed")
	private boolean confirmed;
	
	
	public FlightReservation() {
		// TODO Auto-generated constructor stub
	}

	public FlightReservation(RegisteredUser user, Seat seat, Set<Passanger> passangers, Flight flight,
			double price, Date date, boolean confirmed) {
		super();
		setUser(user);
		this.seat = seat;
		this.passangers = passangers;
		this.flight = flight;
		this.price = price;
		this.date = date;
		this.confirmed = confirmed;
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

	

}
