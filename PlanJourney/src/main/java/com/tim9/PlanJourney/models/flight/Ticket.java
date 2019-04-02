package com.tim9.PlanJourney.models.flight;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tim9.PlanJourney.models.RegisteredUser;


@Entity
@Table(name = "ticket")
public class Ticket {
	

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="price", unique=false, nullable=false)
	private double price;
	
	@Column(name="discount", unique=false, nullable=true)
	private int discount;
	
	@Column(name="issueDate", unique=false, nullable=false)
	private Date issueDate;
	
	@Column(name="onDiscount", unique=false, nullable=false)
	private boolean onDiscount;
	
	@Column(name="rating", unique=false, nullable=false)
	private int rating;
	
	@ManyToOne
	private RegisteredUser user;
	
	@ManyToOne
	private Flight flight;
	
	@OneToOne()
	private Seat seat;
	
	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(Long id, double price, int discount, Date issueDate, Boolean onDiscounnt, int rating,
			RegisteredUser user, Flight flight, Seat seat) {
		/*
		super();
		this.id = id;
		this.price = price;
		this.discount = discount;
		this.issueDate = issueDate;
		this.onDiscount = onDiscounnt;
		this.rating = rating;
		this.user = user;
		this.flight = flight;
		this.seat = seat;
		*/
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Boolean getOnDiscount() {
		return onDiscount;
	}

	public void setOnDiscount(Boolean onDiscounnt) {
		this.onDiscount = onDiscounnt;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public void setOnDiscount(boolean onDiscount) {
		this.onDiscount = onDiscount;
	}
	
	
	

}
