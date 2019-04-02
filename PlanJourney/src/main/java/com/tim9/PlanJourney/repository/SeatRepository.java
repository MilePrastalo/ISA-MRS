package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.flight.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
