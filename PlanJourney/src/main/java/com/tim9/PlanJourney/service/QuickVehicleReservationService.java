package com.tim9.PlanJourney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.models.rentacar.QuickVehicleReservation;
import com.tim9.PlanJourney.repository.QuickVehicleReservationRepository;

@Service
@Transactional(readOnly = true)
public class QuickVehicleReservationService {
	@Autowired
	private QuickVehicleReservationRepository repository;
	
	public QuickVehicleReservation findOne(Long id) {
		return repository.getOne(id);//repository.findOne();
	}

	public List<QuickVehicleReservation> findAll() {
		return repository.findAll();
	}
	
	public Page<QuickVehicleReservation> findAll(Pageable page) {
		return repository.findAll(page);
	}
	@Transactional(readOnly = false)
	public QuickVehicleReservation save(QuickVehicleReservation reservation) {
		return repository.save(reservation);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		repository.deleteById(id);
	}
}
