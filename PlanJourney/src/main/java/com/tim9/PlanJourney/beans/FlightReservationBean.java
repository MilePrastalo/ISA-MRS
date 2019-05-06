package com.tim9.PlanJourney.beans;

import java.util.ArrayList;


public class FlightReservationBean {
	
	
	private Long flightId;
	private ArrayList<PassangerBean> passangers;
	
	
	public FlightReservationBean() {
		// TODO Auto-generated constructor stub
	}


	public FlightReservationBean(Long flightId, ArrayList<PassangerBean> passangers) {
		super();
		this.flightId = flightId;
		this.passangers = passangers;
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
	
	
	
	

}
