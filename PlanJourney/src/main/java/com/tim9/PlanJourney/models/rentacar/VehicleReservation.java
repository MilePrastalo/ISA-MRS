package com.tim9.PlanJourney.models.rentacar;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.Reservation;
import com.tim9.PlanJourney.models.flight.FlightReservation;
@Entity

public class VehicleReservation extends Reservation {

	@ManyToOne
	private Vehicle vehicle;
	
	@Column(name = "dateFrom", nullable = false)
	private Date dateFrom;
	
	@Column(name = "dateTo", nullable = false)
	private Date dateTo;
	
	@Column(name = "date", nullable = false)
	private Date date;
	
	@ManyToOne
	private BranchOffice officePick;
	
	@ManyToOne
	private BranchOffice officeReturn;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@ManyToOne
	private FlightReservation flightReservation;
	
	@Version
	private Long version;
	
	public VehicleReservation() {
		super();
	}

	public VehicleReservation(Vehicle vehicle, RegisteredUser user,Date date, Date dateFrom, Date dateTo, double cena) {
		super();
		this.date = date;
		this.vehicle = vehicle;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.cena = cena;
		setUser(user);
	}


	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public BranchOffice getOfficePick() {
		return officePick;
	}

	public void setOfficePick(BranchOffice officePick) {
		this.officePick = officePick;
	}

	public BranchOffice getOfficeReturn() {
		return officeReturn;
	}

	public void setOfficeReturn(BranchOffice officeReturn) {
		this.officeReturn = officeReturn;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public FlightReservation getFlightReservation() {
		return flightReservation;
	}

	public void setFlightReservation(FlightReservation flightReservation) {
		this.flightReservation = flightReservation;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	
	

	
	
	
	
}
