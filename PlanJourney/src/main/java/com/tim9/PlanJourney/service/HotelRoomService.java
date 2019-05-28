package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.repository.HotelRoomRepository;

@Service
@Transactional(readOnly = true)
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

	@Transactional(readOnly = false)
	public HotelRoom save(HotelRoom hotel) {
		return repository.save(hotel);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}

}
