package com.tim9.PlanJourney.models.flight;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.Company;


@Entity
public class FlightCompany extends Company {
	
	
	@OneToMany(mappedBy = "flightCompany" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FlightAdmin> flightAdmins = new HashSet<FlightAdmin>();
	
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Destination> destinations = new HashSet<Destination>();
	
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flight>  flights= new  HashSet<Flight>();
	
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FlightReservation>  flightReservations = new  HashSet<FlightReservation>();
	
	public FlightCompany() {
		super();
	}
	
	public FlightCompany( String name, String address, String description,double rating) {
		this.setName(name);
		this.setAddress(address);
		this.setDescription(description);
		this.setRating(rating);
	}
	
	

	public FlightCompany( String name, String address, String description,double rating, Set<FlightAdmin> flightAdmins, Set<Destination> destinations, Set<Flight> flights,
			Set<FlightReservation> flightReservations) {
		this.setName(name);
		this.setAddress(address);
		this.setDescription(description);
		this.setRating(rating);
		this.flightAdmins = flightAdmins;
		this.destinations = destinations;
		this.flights = flights;
		this.flightReservations = flightReservations;
	}

	public Set<FlightAdmin> getFlightAdmins() {
		return flightAdmins;
	}

	public void setFlightAdmins(Set<FlightAdmin> flightAdmins) {
		this.flightAdmins = flightAdmins;
	}

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
	}
	
	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public Set<FlightReservation> getFlightReservations() {
		return flightReservations;
	}

	public void setFlightReservations(Set<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}

	
	

}
