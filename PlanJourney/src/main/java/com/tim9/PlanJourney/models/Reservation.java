package com.tim9.PlanJourney.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JsonIgnore

	private RegisteredUser user;
	
	@ManyToOne
	private Company company;
	
	@OneToMany
	private Set<Review> reservationReviews;

	@Version
	private Long version;
	
	public Reservation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Review> getReservationReviews() {
		return reservationReviews;
	}

	public void setReservationReviews(Set<Review> reservationReviews) {
		this.reservationReviews = reservationReviews;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	

}
