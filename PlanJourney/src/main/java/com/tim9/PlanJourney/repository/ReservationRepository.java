package com.tim9.PlanJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.PlanJourney.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
