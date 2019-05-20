package com.tim9.PlanJourney.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.tim9.PlanJourney.models.flight.Passanger;


public class FlightReservationBean {
	
	
	private Long flightId;
	private ArrayList<PassangerBean> passangers;
	private ArrayList<Long> hotelReservations;
	private ArrayList<Long> rentReservations;
	private FlightBean flight;
	private Set<Passanger> passangers_;
	private double price;
	private String created;
	
	
	
	public FlightReservationBean() {
		// TODO Auto-generated constructor stub
	}


	public FlightReservationBean(Long flightId, ArrayList<PassangerBean> passangers, ArrayList<Long> hotelReservations, ArrayList<Long> rentReservations ) {
		super();
		this.flightId = flightId;
		this.passangers = passangers;
		this.hotelReservations = hotelReservations;
		this.rentReservations = rentReservations;
	}
	
	
	public FlightReservationBean(FlightBean flight, Set<Passanger> passangers_, double price, String created) {
		
		this.flight = flight;
		this.passangers_ = passangers_;
		this.price = price;
		this.created = created;
	}

	public Long getFlightId() {
		return flightId;
	}


	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}


	public ArrayList<PassangerBean> getPassangers() {
		return passangers;
	}


	public void setPassangers(ArrayList<PassangerBean> passangers) {
		this.passangers = passangers;
	}


	public ArrayList<Long> getHotelReservations() {
		return hotelReservations;
	}


	public void setHotelReservations(ArrayList<Long> hotelReservations) {
		this.hotelReservations = hotelReservations;
	}


	public ArrayList<Long> getRentReservations() {
		return rentReservations;
	}


	public void setRentReservations(ArrayList<Long> rentReservations) {
		this.rentReservations = rentReservations;
	}


	public FlightBean getFlight() {
		return flight;
	}


	public void setFlight(FlightBean flight) {
		this.flight = flight;
	}


	public Set<Passanger> getPassangers_() {
		return passangers_;
	}


	public void setPassangers_(Set<Passanger> passangers_) {
		this.passangers_ = passangers_;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}
	
	
	
	

}
