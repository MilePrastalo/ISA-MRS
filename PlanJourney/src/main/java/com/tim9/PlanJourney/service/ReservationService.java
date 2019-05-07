package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.Reservation;
import com.tim9.PlanJourney.repository.ReservationRepository;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository repository;
	
	public Reservation findOne(Long id) {
		return repository.getOne(id);
	}

	public List<Reservation> findAll() {
		return repository.findAll();
	}

	public Reservation save(Reservation reservation) {
		return repository.save(reservation);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}


}
