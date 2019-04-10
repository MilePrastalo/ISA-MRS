package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.models.SystemAdmin;
import com.tim9.PlanJourney.repository.HotelRepository;
import com.tim9.PlanJourney.repository.SystemAdminRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	public Hotel findOne(Long id) {
		return hotelRepository.getOne(id);// repository.findOne();
	}

	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}

	public Page<Hotel> findAll(Pageable page) {
		return hotelRepository.findAll(page);
	}

	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public void remove(Long id) {
		hotelRepository.deleteById(id);
	}

	public Hotel findByAddress(String address) {
		return hotelRepository.findByAddress(address);
	}

	public Hotel findByName(String name) {
		return hotelRepository.findByName(name);
	}
}
