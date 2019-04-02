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
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "cena", nullable = false)
	private double cena;
	public VehicleReservation() {
		super();
	}
	public VehicleReservation(Long id, Vehicle vehicle, RegisteredUser user, Date date, double cena) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.user = user;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
}
