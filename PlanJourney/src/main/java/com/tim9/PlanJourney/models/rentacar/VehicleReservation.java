package com.tim9.PlanJourney.models.rentacar;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.tim9.PlanJourney.models.RegisteredUser;
@Entity

public class VehicleReservation {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Vehicle vehicle;
	
	@ManyToOne
	private RegisteredUser user;
	
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
	
	public VehicleReservation() {
		super();
	}

	public VehicleReservation(Vehicle vehicle, RegisteredUser user,Date date, Date dateFrom, Date dateTo, double cena) {
		super();
		this.date = date;
		this.vehicle = vehicle;
		this.user = user;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.cena = cena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
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
	
	

	
	
	
	
}
