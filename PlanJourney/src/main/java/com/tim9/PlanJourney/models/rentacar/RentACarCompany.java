package com.tim9.PlanJourney.models.rentacar;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.Company;

@Entity
public class RentACarCompany extends Company {
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BranchOffice> offices = new HashSet<>();

	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RentACarAdmin> admins = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VehicleReservation> reservations = new HashSet<VehicleReservation>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<QuickVehicleReservation> quickReservations = new HashSet<QuickVehicleReservation>();

	public RentACarCompany() {
		super();
	}

	public RentACarCompany(Long id, Set<RentACarAdmin> admins, Set<BranchOffice> offices, Set<Vehicle> vehicles,
			Set<VehicleReservation> reservations) {
		super();
		/*
		 * this.admins = admins; this.offices = offices; this.vehicles = vehicles;
		 * this.reservations = reservations;
		 */
	}

	public RentACarCompany(String name, String address, String description, double rating) {
		super(0l, name, address, description, rating);
		offices = new HashSet<>();
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

	public Set<QuickVehicleReservation> getQuickReservations() {
		return quickReservations;
	}

	public void setQuickReservations(Set<QuickVehicleReservation> quickReservations) {
		this.quickReservations = quickReservations;
	}

}
