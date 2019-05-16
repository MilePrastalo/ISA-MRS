package com.tim9.PlanJourney.beans;

import java.util.ArrayList;


public class FlightReservationBean {
	
	
	private Long flightId;
	private ArrayList<PassangerBean> passangers;
	private ArrayList<Long> hotelReservations;
	private ArrayList<Long> rentReservations;
	
	
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
	
	
	
	

}
