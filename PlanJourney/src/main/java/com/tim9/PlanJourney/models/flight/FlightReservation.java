package com.tim9.PlanJourney.models.flight;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.Reservation;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;

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
	
	@OneToMany
	private Set<HotelReservation> hoteReservations = new HashSet<>();
	
	@OneToMany
	private Set<VehicleReservation> vehicleReservations = new HashSet<>();
	
	public FlightReservation() {
		// TODO Auto-generated constructor stub
	}

	public FlightReservation(RegisteredUser user, Seat seat, Set<Passanger> passangers, Flight flight,
			double price, Date date, boolean confirmed,Set<HotelReservation> hotelReservations, Set<VehicleReservation> vehicleReservations  ) {
		super();
		setUser(user);
		this.seat = seat;
		this.passangers = passangers;
		this.flight = flight;
		this.price = price;
		this.date = date;
		this.confirmed = confirmed;
		this.hoteReservations = hotelReservations;
		this.vehicleReservations = vehicleReservations;
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

	public Set<HotelReservation> getHoteReservations() {
		return hoteReservations;
	}

	public void setHoteReservations(Set<HotelReservation> hoteReservations) {
		this.hoteReservations = hoteReservations;
	}

	public Set<VehicleReservation> getVehicleReservations() {
		return vehicleReservations;
	}

	public void setVehicleReservations(Set<VehicleReservation> vehicleReservations) {
		this.vehicleReservations = vehicleReservations;
	}

	

}
