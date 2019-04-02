package com.tim9.PlanJourney.models.rentacar;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.tim9.PlanJourney.models.Company;
@Entity
public class RentACarCompany extends Company {
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany
	private ArrayList<RentACarAdmin> admins;
	@OneToMany
	private ArrayList<BranchOffice> offices;
	@OneToMany
	private ArrayList<Vehicle> vehicles;
	@OneToMany
	private ArrayList<VehicleReservation> reservations;
	public RentACarCompany() {
		super();
	}
	public RentACarCompany(Long id, ArrayList<RentACarAdmin> admins, ArrayList<BranchOffice> offices,
			ArrayList<Vehicle> vehicles, ArrayList<VehicleReservation> reservations) {
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
	public ArrayList<RentACarAdmin> getAdmins() {
		return admins;
	}
	public void setAdmins(ArrayList<RentACarAdmin> admins) {
		this.admins = admins;
	}
	public ArrayList<BranchOffice> getOffices() {
		return offices;
	}
	public void setOffices(ArrayList<BranchOffice> offices) {
		this.offices = offices;
	}
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public ArrayList<VehicleReservation> getReservations() {
		return reservations;
	}
	public void setReservations(ArrayList<VehicleReservation> reservations) {
		this.reservations = reservations;
	}
	
}
