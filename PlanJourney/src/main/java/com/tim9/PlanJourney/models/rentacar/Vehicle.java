package com.tim9.PlanJourney.models.rentacar;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "maker", nullable = false)
	private String maker;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "year", nullable = false)
	private int year;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "onDiscount", nullable = true)
	private Boolean onDiscount;

	@Column(name = "discount", nullable = true)
	private Integer discount;

	@Column(name = "avaiableFrom", nullable = true)
	private Date avaiableFrom;

	@Column(name = "avaiableTo", nullable = true)
	private Date avaiableTo;

	@OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
	private Set<VehicleReservation> reservations;

	@OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
	private Set<QuickVehicleReservation> quickReservations;

	@Column(name = "rating", nullable = true)
	private double rating;

	@ManyToOne
	private RentACarCompany company;

	@Version
	private Long version;

	public Vehicle() {
		super();
	}

	public Vehicle(String name, String maker, String type, int year, double price) {
		super();
		this.name = name;
		this.maker = maker;
		this.type = type;
		this.year = year;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getOnDiscount() {
		return onDiscount;
	}

	public void setOnDiscount(Boolean onDiscount) {
		this.onDiscount = onDiscount;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Date getAvaiableFrom() {
		return avaiableFrom;
	}

	public void setAvaiableFrom(Date avaiableFrom) {
		this.avaiableFrom = avaiableFrom;
	}

	public Date getAvaiableTo() {
		return avaiableTo;
	}

	public void setAvaiableTo(Date avaiableTo) {
		this.avaiableTo = avaiableTo;
	}

	public Set<VehicleReservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<VehicleReservation> reservations) {
		this.reservations = reservations;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public RentACarCompany getCompany() {
		return company;
	}

	public void setCompany(RentACarCompany company) {
		this.company = company;
	}

	public Set<QuickVehicleReservation> getQuickReservations() {
		return quickReservations;
	}

	public void setQuickReservations(Set<QuickVehicleReservation> quickReservations) {
		this.quickReservations = quickReservations;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
