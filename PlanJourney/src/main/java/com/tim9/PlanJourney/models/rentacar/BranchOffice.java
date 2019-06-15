package com.tim9.PlanJourney.models.rentacar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.tim9.PlanJourney.models.City;
import com.tim9.PlanJourney.models.flight.Destination;

@Entity

public class BranchOffice {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "address", nullable = false)
	private String address;

	@ManyToOne(fetch = FetchType.EAGER)
	private RentACarCompany company;

	@ManyToOne(fetch = FetchType.EAGER)
	private City destination;
	
	@Column(name="longitude", nullable = true)
	private float longitude;
	
	@Column(name="latitude", nullable = true)
	private float latitude;

	@Version
	private Long version;

	public BranchOffice() {
		super();
	}

	public BranchOffice(String name, String address, RentACarCompany company, City destination) {
		super();
		this.name = name;
		this.address = address;
		this.company = company;
		this.destination = destination;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RentACarCompany getCompany() {
		return company;
	}

	public void setCompany(RentACarCompany company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getDestination() {
		return destination;
	}

	public void setDestination(City destination) {
		this.destination = destination;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	

}
