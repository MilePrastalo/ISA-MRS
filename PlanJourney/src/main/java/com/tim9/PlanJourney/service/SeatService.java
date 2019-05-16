package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.repository.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	SeatRepository repository;
	
	public Seat findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<Seat> findAll() {
		return repository.findAll();
	}
	
	public Page<Seat> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public Seat save(Seat seat) {
		return repository.save(seat);
	}
	
	public List<Seat> saveAll (List<Seat> seats){
		return repository.saveAll(seats);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
