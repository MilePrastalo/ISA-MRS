package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.repository.HotelReservationRepository;


@Service
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

	public HotelReservation save(HotelReservation hotel) {
		return repository.save(hotel);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}
}
