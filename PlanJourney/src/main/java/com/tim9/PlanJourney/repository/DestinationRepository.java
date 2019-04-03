package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.flight.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
	
	
	public Destination findByName(String name);

}
