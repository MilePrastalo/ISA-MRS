package com.tim9.PlanJourney.models.flight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "taken", unique = false, nullable = false)
	private Boolean taken;
	
	@Column(name = "row", unique = false, nullable = false)
	private int row;
	
	@Column(name = "column", unique = false, nullable = false)
	private int column;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "travelClass")
	private TravelClass travelClass;
	
	public Seat() {
		// TODO Auto-generated constructor stub
	}

	public Seat(Boolean taken, int row, int column, TravelClass travelClass) {
		super();
		this.taken = taken;
		this.row = row;
		this.column = column;
		this.travelClass = travelClass;
	}

	public Boolean getTaken() {
		return taken;
	}

	public void setTaken(Boolean taken) {
		this.taken = taken;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public TravelClass getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(TravelClass travelClass) {
		this.travelClass = travelClass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
