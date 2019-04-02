package com.tim9.PlanJourney.models.rentacar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.Company;

@Entity
public class RentACarCompany extends Company {
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RentACarAdmin> admins = new HashSet<>();
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BranchOffice> offices = new HashSet<>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vehicle> vehicles  = new HashSet<Vehicle>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VehicleReservation> reservations = new HashSet<VehicleReservation>();
	public RentACarCompany() {
		super();
	}
	public RentACarCompany(Long id, Set<RentACarAdmin> admins, Set<BranchOffice> offices, Set<Vehicle> vehicles,
			Set<VehicleReservation> reservations) {
		super();
		this.id = id;
		this.admins = admins;
		this.offices = offices;
		this.vehicles = vehicles;
		this.reservations = reservations;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<RentACarAdmin> getAdmins() {
		return admins;
	}
	
	public void setAdmins(Set<RentACarAdmin> admins) {
		this.admins = admins;
	}
	public Set<BranchOffice> getOffices() {
		return offices;
	}
	public void setOffices(Set<BranchOffice> offices) {
		this.offices = offices;
	}
	public Set<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public Set<VehicleReservation> getReservations() {
		return reservations;
	}
	public void setReservations(Set<VehicleReservation> reservations) {
		this.reservations = reservations;
	}
	
	
	
	
}
