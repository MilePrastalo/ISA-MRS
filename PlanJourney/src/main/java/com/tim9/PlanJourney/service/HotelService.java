package com.tim9.PlanJourney.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim9.PlanJourney.beans.HotelReservationBean;
import com.tim9.PlanJourney.hotel.AdditionalCharges;
import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.repository.HotelRepository;

@Service
@Transactional(readOnly = true)
public class HotelService {

	@Autowired
	RegisteredUserService userService;

	@Autowired
	HotelReservationService hotelReservationService;

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

	@Transactional(readOnly = false)
	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long id) {
		hotelRepository.deleteById(id);
	}

	public Hotel findByAddress(String address) {
		return hotelRepository.findByAddress(address);
	}

	public Hotel findByName(String name) {
		return hotelRepository.findByName(name);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Long addHotelReservation(HotelReservationBean reservationBean, String username) {
		// checks if hotel exists.
		Hotel hotel = this.findByName(reservationBean.getHotelName());
		if (hotel == null) {
			return null;
		}
		RegisteredUser user = userService.findByUsername(username);
		if (user == null) {
			return null;
		}
		HotelRoom room = null;
		for (HotelRoom r : hotel.getRooms()) {
			if (r.getRoomNumber() == reservationBean.getRoomNumber()) {
				room = r;
				break;
			}
		}
		if (room == null) {
			return null;
		}

		// Creating Date objects

		Date firstDay = new Date();
		Calendar c = Calendar.getInstance();
		System.out.println("Dates: " + reservationBean.getfYear() + " m: " + reservationBean.getfMonth() + " d: "
				+ reservationBean.getfDay());
		c.set(reservationBean.getfYear(), reservationBean.getfMonth() - 1, reservationBean.getfDay(), 0, 0);
		firstDay.setTime(c.getTimeInMillis());

		Date lastDay = new Date();
		c.set(reservationBean.getlYear(), reservationBean.getlMonth() - 1, reservationBean.getlDay(), 0, 0);
		lastDay.setTime(c.getTimeInMillis());

		if (!checkDate(firstDay, lastDay, hotel, reservationBean.getRoomNumber())) {
			return null;
		}

		// Calculating number of days.
		long time = lastDay.getTime() - firstDay.getTime();
		long days = TimeUnit.MILLISECONDS.toDays(time);

		HotelReservation reservation = new HotelReservation();
		reservation.setFirstDay(firstDay);
		reservation.setLastDay(lastDay);
		reservation.setHotel(hotel);
		reservation.setRoom(room);
		reservation.setDiscount(0);
		reservation.setPaidPrice(days * room.getPricePerDay());
		reservation.setUser(user);
		hotel.getReservations().add(reservation);
		user.getHotelReservations().add(reservation);

		// Adds choosen additional charges to reservation.
		for (String aCName : reservationBean.getAdditionalCharges()) {
			for (AdditionalCharges ac : room.getAdditionalCharges()) {
				if (ac.getName().equals(aCName)) {
					reservation.getAdditionalCharges().add(ac);
				}
			}
		}
		// service.save(hotel);
		HotelReservation newReservation = hotelReservationService.save(reservation);
		System.out.println(newReservation.getId());
		// userService.save(user);

		return newReservation.getId();
	}

	public boolean addQuickHotelReservation(HotelReservationBean reservationBean) {
		// Checks if hotel exists.
		Hotel hotel = this.findByName(reservationBean.getHotelName());
		if (hotel == null) {
			return false;
		}
		// Finds selected room and check if it exists.
		HotelRoom room = null;
		for (HotelRoom r : hotel.getRooms()) {
			if (r.getRoomNumber() == reservationBean.getRoomNumber()) {
				room = r;
				break;
			}
		}
		if (room == null) {
			return false;
		}
		// Splits date and checks if reservation date is valid.
		Date firstDay = new Date();
		Calendar c = Calendar.getInstance();
		c.set(reservationBean.getfYear(), reservationBean.getfMonth() - 1, reservationBean.getfDay(), 0, 0);
		firstDay.setTime(c.getTimeInMillis());

		Date lastDay = new Date();
		c.set(reservationBean.getlYear(), reservationBean.getlMonth() - 1, reservationBean.getlDay(), 0, 0);
		lastDay.setTime(c.getTimeInMillis());

		if (!checkDate(firstDay, lastDay, hotel, reservationBean.getRoomNumber())) {
			System.out.println(firstDay);
			System.out.println(lastDay);
			return false;
		}

		// Calculating number of days.
		long time = lastDay.getTime() - firstDay.getTime();
		long days = TimeUnit.MILLISECONDS.toDays(time);

		// Adds new reservation
		HotelReservation reservation = new HotelReservation();
		reservation.setFirstDay(firstDay);
		reservation.setLastDay(lastDay);
		reservation.setHotel(hotel);
		reservation.setRoom(room);
		reservation.setPaidPrice(days * room.getPricePerDay());
		reservation.setDiscount(reservationBean.getDiscount());
		hotel.getReservations().add(reservation);
		this.save(hotel);

		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Long buyQuickReservation(HotelReservationBean reservationBean, String username) {
		// checks if hotel exists..
		Hotel hotel = this.findByName(reservationBean.getHotelName());
		if (hotel == null) {
			return null;
		}

		// checks if registered user exists.
		RegisteredUser user = userService.findByUsername(username);
		if (user == null) {
			return null;
		}

		// Splits date and checks if reservation date is valid.
		Calendar c = Calendar.getInstance();
		HotelReservation foundReservation = new HotelReservation();
		for (HotelReservation hr : hotel.getReservations()) {
			if (hr.getRoom().getRoomNumber() == reservationBean.getRoomNumber()) {
				c.setTime(hr.getFirstDay());
				int fYear = c.get(Calendar.YEAR);
				int fMonth = c.get(Calendar.MONTH) + 1;
				int fDay = c.get(Calendar.DAY_OF_MONTH);
				c.setTime(hr.getLastDay());
				int lYear = c.get(Calendar.YEAR);
				int lMonth = c.get(Calendar.MONTH) + 1;
				int lDay = c.get(Calendar.DAY_OF_MONTH);
				if (fYear == reservationBean.getfYear() && fMonth == reservationBean.getfMonth()
						&& fDay == reservationBean.getfDay() && lYear == reservationBean.getlYear()
						&& lMonth == reservationBean.getlMonth() && lDay == reservationBean.getlDay()) {
					foundReservation = hr;
					break;
				}
			}
		}

		// service.save(hotel);
		// userService.save(user);
		foundReservation.setUser(user);
		hotelReservationService.save(foundReservation);

		return foundReservation.getId();
	}

	// Backend checks for adding new reservations.
	private boolean checkDate(Date firstDay, Date lastDay, Hotel hotel, int roomNumber) {

		if (firstDay.after(lastDay)) {
			return false;
		}

		for (HotelReservation hr : hotel.getReservations()) {
			if (roomNumber == hr.getRoom().getRoomNumber()) {
				if (lastDay.after(hr.getFirstDay()) && lastDay.before(hr.getLastDay())) {
					return false;
				}
				if (firstDay.after(hr.getFirstDay()) && firstDay.before(hr.getLastDay())) {
					return false;
				}
				if (firstDay.equals(hr.getFirstDay()) || lastDay.equals(hr.getLastDay())) {
					return false;
				}
			}
		}
		return true;
	}

}
