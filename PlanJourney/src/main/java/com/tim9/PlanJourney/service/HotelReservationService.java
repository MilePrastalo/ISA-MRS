package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.repository.HotelReservationRepository;


@Service
@Transactional(readOnly = true)
public class HotelReservationService {
	@Autowired
	private HotelReservationRepository repository;

	public HotelReservation findOne(Long id) {
		return repository.getOne(id);// repository.findOne();
	}

	public List<HotelReservation> findAll() {
		return repository.findAll();
	}

	public Page<HotelReservation> findAll(Pageable page) {
		return repository.findAll(page);
	}

	@Transactional(readOnly = false)
	public HotelReservation save(HotelReservation hotel) {
		return repository.save(hotel);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}
}
