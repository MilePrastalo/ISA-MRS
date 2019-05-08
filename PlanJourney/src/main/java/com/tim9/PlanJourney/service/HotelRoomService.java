package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.repository.HotelReservationRepository;
import com.tim9.PlanJourney.repository.HotelRoomRepository;

@Service
public class HotelRoomService {
	@Autowired
	private HotelRoomRepository repository;

	public HotelRoom findOne(Long id) {
		return repository.getOne(id);// repository.findOne();
	}

	public List<HotelRoom> findAll() {
		return repository.findAll();
	}

	public Page<HotelRoom> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public HotelRoom save(HotelRoom hotel) {
		return repository.save(hotel);
	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
