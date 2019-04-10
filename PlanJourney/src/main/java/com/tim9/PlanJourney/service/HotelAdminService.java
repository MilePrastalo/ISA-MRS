package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.repository.HotelAdminRepository;

@Service
public class HotelAdminService {

	@Autowired
	private HotelAdminRepository hotelAdminRepository;

	public HotelAdmin findOne(Long id) {
		return hotelAdminRepository.getOne(id);// repository.findOne();
	}

	public List<HotelAdmin> findAll() {
		return hotelAdminRepository.findAll();
	}

	public Page<HotelAdmin> findAll(Pageable page) {
		return hotelAdminRepository.findAll(page);
	}

	public HotelAdmin save(HotelAdmin hotelAdmin) {
		return hotelAdminRepository.save(hotelAdmin);
	}

	public void remove(Long id) {
		hotelAdminRepository.deleteById(id);
	}
	
	public HotelAdmin findByUsername(String username) {
		return hotelAdminRepository.findByUsername(username);
	}
}
