package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.flight.Ticket;
import com.tim9.PlanJourney.repository.FlightTicketRepository;

@Service
public class FlightTicketService {
	
	@Autowired
	FlightTicketRepository repository;
	
	
	public Ticket findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<Ticket> findAll() {
		return repository.findAll();
	}
	
	public Page<Ticket> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public Ticket save(Ticket ticket) {
		return repository.save(ticket);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
