package com.tim9.PlanJourney.models.flight;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.Company;


@Entity
public class FlightCompany extends Company {
	
	
	@OneToMany(mappedBy = "flightCompany" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<FlightAdmin> flightAdmins = new HashSet<FlightAdmin>();
	
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Destination> destinations = new HashSet<Destination>();
	
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flight>  flights = new  HashSet<Flight>();
	
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Ticket>  tickets = new  HashSet<Ticket>();
	
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
			Set<Ticket> tickets) {
		this.setName(name);
		this.setAddress(address);
		this.setDescription(description);
		this.setRating(rating);
		this.flightAdmins = flightAdmins;
		this.destinations = destinations;
		this.flights = flights;
		this.tickets = tickets;
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

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	

}
