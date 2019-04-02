package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.flight.FlightAdmin;

public interface FlightAdminRepository extends JpaRepository<FlightAdmin, Long> {

}
