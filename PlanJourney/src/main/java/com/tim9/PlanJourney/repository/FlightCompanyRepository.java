package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.flight.FlightCompany;

public interface FlightCompanyRepository extends JpaRepository<FlightCompany, Long> {

	FlightCompany findByAddress(String address);

	FlightCompany findByName(String name);
}
