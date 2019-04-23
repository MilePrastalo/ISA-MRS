package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.flight.Flight;

public interface FllightRepository extends JpaRepository<Flight, Long> {
	

}
