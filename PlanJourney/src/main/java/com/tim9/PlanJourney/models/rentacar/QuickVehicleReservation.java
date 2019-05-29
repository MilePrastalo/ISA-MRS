package com.tim9.PlanJourney.models.rentacar;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.tim9.PlanJourney.models.flight.Flight;
import com.tim9.PlanJourney.models.flight.Seat;

@Entity
public class QuickVehicleReservation {
	@Id
	@GeneratedValue
	private Long id;
		
	@ManyToOne
	private Vehicle vehicle;
	
	@Column(name = "originalPrice")
	private double originalPrice;
	
	@Column(name = "discount")
	private int discount;
	
	@Column(name = "dateFrom", nullable = false)
	private Date dateFrom;
	
	@Column(name = "dateTo", nullable = false)
	private Date dateTo;
	
	@ManyToOne
	private BranchOffice officePick;
	
	@ManyToOne
	private BranchOffice officeReturn;
	
	@Column(name = "taken")
	private boolean taken;
	
	@Version
	private Long version;

	public QuickVehicleReservation() {
		super();
	}

	public QuickVehicleReservation(Vehicle vehicle, double originalPrice, int discount, Date dateFrom, Date dateTo,
			BranchOffice officePick, BranchOffice officeReturn, boolean taken) {
		super();
		this.vehicle = vehicle;
		this.originalPrice = originalPrice;
		this.discount = discount;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.officePick = officePick;
		this.officeReturn = officeReturn;
		this.taken = taken;
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

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	

}
