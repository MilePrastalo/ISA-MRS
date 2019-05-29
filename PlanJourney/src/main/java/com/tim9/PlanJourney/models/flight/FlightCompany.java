package com.tim9.PlanJourney.models.flight;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.tim9.PlanJourney.models.Company;


@Entity
public class FlightCompany extends Company {
	
	
	@OneToMany(mappedBy = "flightCompany" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FlightAdmin> flightAdmins = new HashSet<FlightAdmin>();
	
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Destination> destinations = new HashSet<Destination>();
	
	
	@OneToMany(mappedBy = "flightCompany",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flight>  flights = new  HashSet<Flight>();
	
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FlightReservation>  flightReservation = new  HashSet<FlightReservation>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QuickFlightReservation>  quickFlightReservations = new  HashSet<QuickFlightReservation>();
	
	@Version
	private Long version;
	
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
			Set<FlightReservation> flightReservation, Set<QuickFlightReservation> quickFlightReservation) {
		this.setName(name);
		this.setAddress(address);
		this.setDescription(description);
		this.setRating(rating);
		this.flightAdmins = flightAdmins;
		this.destinations = destinations;
		this.flights = flights;
		this.flightReservation = flightReservation;
		this.quickFlightReservations = quickFlightReservation;
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

	public Set<FlightReservation> getFlightReservation() {
		return flightReservation;
	}

	public void setFlightReservation(Set<FlightReservation> flightReservation) {
		this.flightReservation = flightReservation;
	}

	public Set<QuickFlightReservation> getQuickFlightReservations() {
		return quickFlightReservations;
	}

	public void setQuickFlightReservations(Set<QuickFlightReservation> quickFlightReservations) {
		this.quickFlightReservations = quickFlightReservations;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	
	
	

}
