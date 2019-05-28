package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.flight.Seat;
import com.tim9.PlanJourney.repository.SeatRepository;

@Service
@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = false)
	public Seat save(Seat seat) {
		return repository.save(seat);
	}
	
	@Transactional(readOnly = false)
	public List<Seat> saveAll (List<Seat> seats){
		return repository.saveAll(seats);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
