package com.tim9.PlanJourney.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.AdminBean;
import com.tim9.PlanJourney.beans.EditHotelBean;
import com.tim9.PlanJourney.beans.FlightCompanyReportBean;
import com.tim9.PlanJourney.beans.FlightReportRequestBean;
import com.tim9.PlanJourney.beans.HotelAdminBean;
import com.tim9.PlanJourney.beans.HotelBean;
import com.tim9.PlanJourney.beans.HotelDailyReportBean;
import com.tim9.PlanJourney.beans.HotelReportBean;
import com.tim9.PlanJourney.beans.HotelReservationBean;
import com.tim9.PlanJourney.beans.HotelRoomBean;
import com.tim9.PlanJourney.hotel.AdditionalCharges;
import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.hotel.HotelRoom;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.models.Review;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.EmailService;
import com.tim9.PlanJourney.service.HotelAdminService;
import com.tim9.PlanJourney.service.HotelReservationService;
import com.tim9.PlanJourney.service.HotelService;
import com.tim9.PlanJourney.service.UserService;

@RestController
public class HotelAdminController {

	@Autowired
	private HotelAdminService adminService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private HotelReservationService hotelReservationService;

	static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");

	@RequestMapping(value = "/api/getHotelAdmin/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public HotelAdminBean getHotelAdmin(@PathVariable("username") String username) {
		HotelAdmin admin = adminService.findByUsername(username);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Checks if admin exists.
			if (admin == null) {
				return null;
			}
			// HotelAdminBean
			HotelAdminBean hab = new HotelAdminBean();
			hab.setFirstName(admin.getFirstName());
			hab.setLastName(admin.getLastName());
			hab.setUsername(admin.getUsername());
			hab.setEmail(admin.getEmail());

			// Setting hotel
			Hotel hotel = admin.getHotel();
			HotelBean hb = new HotelBean();
			hb.setAddress(hotel.getAddress());
			hb.setDescription(hotel.getDescription());
			hb.setCityName(hotel.getCity().getName());
			hb.setName(hotel.getName());
			hb.setRating(hotel.getRating());
			ArrayList<HotelRoomBean> rb = new ArrayList<HotelRoomBean>();
			for (HotelRoom r : hotel.getRooms()) {
				HotelRoomBean roomBean = new HotelRoomBean();
				roomBean.setRoomNumber(r.getRoomNumber());
				roomBean.setNumberOfBeds(r.getNumberOfBeds());
				roomBean.setPricePerDay(r.getPricePerDay());
				ArrayList<AdditionalCharges> ac = new ArrayList<AdditionalCharges>(r.getAdditionalCharges());
				roomBean.setAdditionalCharges(ac);
				rb.add(roomBean);
			}
			hb.setRooms(rb);
			ArrayList<HotelReservationBean> reservationBeans = new ArrayList<HotelReservationBean>();
			// Creates hotel reservation beans from hotel reservations.
			for (HotelReservation hr : hotel.getReservations()) {
				Calendar c = Calendar.getInstance();
				c.setTime(hr.getFirstDay());
				HotelReservationBean reservationBean = new HotelReservationBean();
				reservationBean.setRoomNumber(hr.getRoom().getRoomNumber());
				if (hr.getUser() != null) {
					reservationBean.setUsername(hr.getUser().getUsername());
				}
				reservationBean.setHotelName(hr.getHotel().getName());
				reservationBean.setfYear(c.get(Calendar.YEAR));
				reservationBean.setfMonth(c.get(Calendar.MONTH) + 1);
				reservationBean.setfDay(c.get(Calendar.DAY_OF_MONTH));
				c.setTime(hr.getLastDay());
				reservationBean.setHotelName(hr.getHotel().getName());
				reservationBean.setlYear(c.get(Calendar.YEAR));
				reservationBean.setlMonth(c.get(Calendar.MONTH) + 1);
				reservationBean.setlDay(c.get(Calendar.DAY_OF_MONTH));
				reservationBean.setDiscount(hr.getDiscount());
				reservationBean.setNumberOfBeds(hr.getRoom().getNumberOfBeds());
				reservationBean.setPaidPrice(hr.getPaidPrice());
				reservationBean.setId(hr.getId());
				reservationBeans.add(reservationBean);
			}
			hb.setReservations(reservationBeans);

			return hab;
		}
		return null;
	}

	@RequestMapping(value = "/api/getHotelAdminProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	// Method returns hotel admin information
	public @ResponseBody HotelAdminBean getHotelAdminProfile() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String username = authentication.getName();
			HotelAdmin admin = (HotelAdmin) userService.findOneByUsername(username);

			// Checks if admin exists.
			if (admin == null) {
				return null;
			}
			// HotelAdminBean
			HotelAdminBean hab = new HotelAdminBean();
			hab.setFirstName(admin.getFirstName());
			hab.setLastName(admin.getLastName());
			hab.setUsername(admin.getUsername());
			hab.setEmail(admin.getEmail());

			// Setting hotel
			Hotel hotel = admin.getHotel();
			HotelBean hb = new HotelBean();

			hb.setAddress(hotel.getAddress());
			hb.setDescription(hotel.getDescription());
			hb.setCityName(hotel.getCity().getName());
			hb.setName(hotel.getName());
			hb.setRating(hotel.getRating());

			ArrayList<HotelRoomBean> rb = new ArrayList<HotelRoomBean>();
			for (HotelRoom r : hotel.getRooms()) {
				HotelRoomBean roomBean = new HotelRoomBean();
				roomBean.setRoomNumber(r.getRoomNumber());
				roomBean.setNumberOfBeds(r.getNumberOfBeds());
				roomBean.setPricePerDay(r.getPricePerDay());
				ArrayList<AdditionalCharges> ac = new ArrayList<AdditionalCharges>(r.getAdditionalCharges());
				roomBean.setAdditionalCharges(ac);
				rb.add(roomBean);
			}
			hb.setRooms(rb);
			ArrayList<HotelReservationBean> reservationBeans = new ArrayList<HotelReservationBean>();
			// Creates hotel reservation beans from hotel reservations.
			for (HotelReservation hr : hotel.getReservations()) {
				Calendar c = Calendar.getInstance();
				c.setTime(hr.getFirstDay());
				HotelReservationBean reservationBean = new HotelReservationBean();
				reservationBean.setRoomNumber(hr.getRoom().getRoomNumber());
				if (hr.getUser() != null) {
					reservationBean.setUsername(hr.getUser().getUsername());
				}
				reservationBean.setHotelName(hr.getHotel().getName());
				reservationBean.setfYear(c.get(Calendar.YEAR));
				reservationBean.setfMonth(c.get(Calendar.MONTH) + 1);
				reservationBean.setfDay(c.get(Calendar.DAY_OF_MONTH));
				c.setTime(hr.getLastDay());
				reservationBean.setHotelName(hr.getHotel().getName());
				reservationBean.setlYear(c.get(Calendar.YEAR));
				reservationBean.setlMonth(c.get(Calendar.MONTH) + 1);
				reservationBean.setlDay(c.get(Calendar.DAY_OF_MONTH));
				reservationBean.setDiscount(hr.getDiscount());
				reservationBean.setNumberOfBeds(hr.getRoom().getNumberOfBeds());
				reservationBean.setPaidPrice(hr.getPaidPrice());
				reservationBean.setId(hr.getId());
				reservationBeans.add(reservationBean);
			}
			hb.setReservations(reservationBeans);
			hab.setHotel(hb);

			return hab;

		}
		return null;
	}

	@RequestMapping(value = "/api/addHotelAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public @ResponseBody boolean addHotelAdmin(@RequestBody AdminBean admin) {
		// Finds if new admin's given hotel exists.
		Hotel h = hotelService.findByName(admin.getCompanyName());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (admin.getUsername() == null || admin.getUsername().equals("".trim())) {
				return false;
			}
			if (admin.getPassword() == null || admin.getPassword().equals("".trim())) {
				return false;
			}
			if (admin.getFirstName() == null || admin.getFirstName().equals("".trim())) {
				return false;
			}
			if (admin.getLastName() == null || admin.getLastName().equals("".trim())) {
				return false;
			}
			if (admin.getEmail() == null || admin.getEmail().equals("".trim())) {
				return false;
			}
			if (admin.getCompanyName() == null || admin.getCompanyName().equals("".trim())) {
				return false;
			}

			if (adminService.findByUsername(admin.getUsername()) == null && h != null) {
				Authority authority = authorityService.findByName("HOTEL_ADMIN");
				ArrayList<Authority> auth = new ArrayList<Authority>();
				auth.add(authority);
				HotelAdmin hotelAdmin = new HotelAdmin();
				hotelAdmin.setUsername(admin.getUsername());
				hotelAdmin.setFirstName(admin.getFirstName());
				hotelAdmin.setLastName(admin.getLastName());
				hotelAdmin.setEmail(admin.getEmail());
				hotelAdmin.setAuthorities(auth);
				hotelAdmin.setHotel(h);

				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				hotelAdmin.setPassword(encoder.encode(admin.getPassword()));
				hotelAdmin.setConfirmed(true);
				hotelAdmin.setLoggedBefore(false);
				adminService.save(hotelAdmin);

				try {
					emailService.sendRegistrationEmail(hotelAdmin); // sends email
				} catch (MailException | InterruptedException e) {
					e.printStackTrace();
				}

				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@RequestMapping(value = "/api/removeHotelAdmin/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('SYS_ADMIN')")
	public ResponseEntity<HotelAdmin> removeHotelAdmin(@PathVariable("username") String username) {

		HotelAdmin admin = adminService.findByUsername(username);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// Checks if admin exists.
			if (admin == null) {
				return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
			}

			adminService.remove(admin.getId());
			return new ResponseEntity<HotelAdmin>(admin, HttpStatus.OK);
		}
		return new ResponseEntity<HotelAdmin>(admin, HttpStatus.CONFLICT);
	}

	// Updates Hotel Admins information: Email, FistName, LastName.
	@RequestMapping(value = "/api/updateHotelAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean updateHotelAdmin(@RequestBody HotelAdminBean hotelAdmin) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = (HotelAdmin) userService.findOneByUsername(username);
			if (admin.getUsername().equals(hotelAdmin.getUsername())) {
				admin.setEmail(hotelAdmin.getEmail());
				admin.setFirstName(hotelAdmin.getFirstName());
				admin.setLastName(hotelAdmin.getLastName());

				adminService.save(admin);
				return true;
			}
		}
		return false;

	}

	@RequestMapping(value = "/api/getHotelReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody HotelReportBean getHotelReport(@RequestBody HotelReportBean hotelReportBean) {

		hotelReportBean.setDailyReports(new ArrayList<HotelDailyReportBean>());
		// Authenticate admin so we can get hotel.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = adminService.findByUsername(username);
			Hotel hotel = hotelService.findByName(admin.getHotel().getName());

			// Sets time period of report depending on reports name(week,month,year).
			int timePeriod = 0;
			switch (hotelReportBean.getReportName()) {
			case ("WEEK"):
				timePeriod = 7;
				break;
			case ("MONTH"):
				timePeriod = 30;
				break;
			case ("YEAR"):
				timePeriod = 365;
				break;
			}
			int numberOfReservations = 0;
			float totalEarnings = 0;
			// Calendar c is used to iterate days, cr is used for reservation first day.
			Calendar c = Calendar.getInstance();
			c.set(hotelReportBean.getfYear(), hotelReportBean.getfMonth() - 1, hotelReportBean.getfDay(), 0, 0);
			Calendar cr = Calendar.getInstance();
			// For every single day in time period we create dailyReport and add it to final
			// report.
			for (int i = 0; i < timePeriod; i++) {
				HotelDailyReportBean dailyReport = new HotelDailyReportBean();
				dailyReport.settYear(c.get(Calendar.YEAR));
				dailyReport.settMonth(c.get(Calendar.MONTH) + 1);
				dailyReport.settDay(c.get(Calendar.DAY_OF_MONTH));
				dailyReport.setNumberOfReservations(0);
				dailyReport.setEarnings(0);

				// Checks if there are any reservations for current day, if there are adds them
				// to dailyReport earnings.
				for (HotelReservation hr : hotel.getReservations()) {
					// Splits the first day of reservation
					cr.setTime(hr.getFirstDay());
					int fYear = cr.get(Calendar.YEAR);
					int fMonth = cr.get(Calendar.MONTH) + 1;
					int fDay = cr.get(Calendar.DAY_OF_MONTH);

					if (fDay == dailyReport.gettDay() && fMonth == dailyReport.gettMonth()
							&& fYear == dailyReport.gettYear()) {
						dailyReport.setNumberOfReservations(dailyReport.getNumberOfReservations() + 1);
						float paidPrice = hr.getPaidPrice() - hr.getPaidPrice() * hr.getDiscount() / 100;
						dailyReport.setEarnings(dailyReport.getEarnings() + paidPrice);
						numberOfReservations++;
						totalEarnings += paidPrice;
					}
				}
				hotelReportBean.setNumberOfReservations(numberOfReservations);
				hotelReportBean.setTotalEarnings(totalEarnings);
				hotelReportBean.getDailyReports().add(dailyReport);

				// Increases Calendar c to next day.
				c.add(Calendar.DATE, 1);
			}

		}

		return hotelReportBean;
	}

	// Difference between quick reservation and normal reservation is lack of user
	// and price reduction in quick reservation.
	@RequestMapping(value = "/api/addQuickHotelReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean addQuickHotelReservation(@RequestBody HotelReservationBean reservationBean) {

		boolean added = hotelService.addQuickHotelReservation(reservationBean);

		return added;
	}

	@RequestMapping(value = "/api/cancelQuickHotelReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean cancelQuickHotelReservation(@RequestBody HotelReservationBean reservationBean) {
		HotelReservation hr = hotelReservationService.findOne(reservationBean.getId());

		if (hr.getUser() != null) {
			return false;
		}

		hotelReservationService.remove(hr.getId());

		return true;
	}

	@RequestMapping(value = "/api/updateHotelProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody boolean updateHotelProfile(@RequestBody EditHotelBean editHotelBean) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (editHotelBean.getNewName().equals("".trim())) {
				return false;
			}

			ArrayList<Hotel> hotels = (ArrayList<Hotel>) hotelService.findAll();

			String username = authentication.getName();
			HotelAdmin ha = adminService.findByUsername(username);
			Hotel hotel = ha.getHotel();
			for (Hotel h : hotels) {
				if (h.getName().equals(editHotelBean.getNewName())
						&& h.getCity().getName().equals(editHotelBean.getCityName()) && h.getId() != hotel.getId()) {
					return false;
				}
			}
			if (editHotelBean.getOldName().equals(hotel.getName())
					&& editHotelBean.getCityName().equals(hotel.getCity().getName())) {
				hotel.setName(editHotelBean.getNewName());
				hotel.setDescription(editHotelBean.getNewDescription());

				hotelService.save(hotel);

				return true;
			}
		}
		return false;
	}

	// Returns average rate for hotel.
	@RequestMapping(value = "/api/getHotelAverageRate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public ArrayList<Integer> getHotelAverageRate() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = adminService.findByUsername(username);

			Hotel hotel = admin.getHotel();
			HashMap<Integer, Integer> ratingMap = new HashMap<>();
			ratingMap.put(1, 0);
			ratingMap.put(2, 0);
			ratingMap.put(3, 0);
			ratingMap.put(4, 0);
			ratingMap.put(5, 0);
			for (HotelReservation reservation : hotel.getReservations()) {
				for (Review review : reservation.getReservationReviews()) {
					int newNum = ratingMap.get(review.getRating()) + 1;
					ratingMap.put(review.getRating(), newNum);
				}
			}
			ArrayList<Integer> rates = new ArrayList<>();
			rates.add(ratingMap.get(1));
			rates.add(ratingMap.get(2));
			rates.add(ratingMap.get(3));
			rates.add(ratingMap.get(4));
			rates.add(ratingMap.get(5));
			return rates;
		}
		return null;
	}

	@RequestMapping(value = "/api/getReservedRooms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody FlightCompanyReportBean getReservedRooms(@RequestBody FlightReportRequestBean reqestData)
			throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = adminService.findByUsername(username);

			Hotel hotel = admin.getHotel();

			Date startDate = reqestData.getDateFrom();
			Date endDate = reqestData.getDateTo();
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(startDate);
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(endDate);

			ArrayList<String> labels = new ArrayList<>();
			ArrayList<Integer> cntTickets = new ArrayList<>();

			System.out.println("KIND = " + reqestData.getKind());
			long span = endDate.getTime() - startDate.getTime();
			long cols;
			if (reqestData.getKind().equals("daily")) {
				cols = TimeUnit.DAYS.convert(span, TimeUnit.MILLISECONDS);
				for (int i = 0; i <= cols; i++) {
					labels.add(sdf.format(calStart.getTime()));
					cntTickets.add(0);
					calStart.add(calStart.DATE, 1);
				}
			} else if (reqestData.getKind().equals("weekly")) {
				cols = TimeUnit.DAYS.convert(span, TimeUnit.MILLISECONDS);
				int weekStart = calStart.get(Calendar.WEEK_OF_YEAR);
				int weekEnd = calEnd.get(Calendar.WEEK_OF_YEAR);
				boolean ajusted = false;
				for (int i = 0; i <= weekEnd - weekStart; i++) {
					labels.add(sdf.format(calStart.getTime()));
					cntTickets.add(0);
					if (!ajusted) {
						calStart.add(calStart.DATE, 9 - calStart.get(Calendar.DAY_OF_WEEK));
						ajusted = true;
					} else {
						calStart.add(calStart.DATE, 7);
					}
				}
			} else if (reqestData.getKind().equals("monthly")) {
				boolean ajusted = false;
				int monthStart = calStart.get(Calendar.MONTH);
				int montEnd = calEnd.get(Calendar.MONTH);

				for (int i = 0; i <= montEnd - monthStart; i++) {
					labels.add(sdf.format(calStart.getTime()));
					cntTickets.add(0);
					int sub = calStart.get(Calendar.DAY_OF_MONTH);
					if (!ajusted) {
						calStart.add(calStart.DATE, -sub + 1);
						calStart.add(calStart.MONTH, 1);
						ajusted = true;
					} else {
						calStart.add(calStart.MONTH, 1);
					}
				}
			}
			for (HotelReservation hr : hotel.getReservations()) {
				for (int i = cntTickets.size() - 1; i >= 0; i--) {
					Date d = sdf.parse(labels.get(i));
					if (hr.getFirstDay().after(d) && hr.getUser() != null) {
						cntTickets.set(i, cntTickets.get(i) + 1);
						break;
					}
				}
			}
			return new FlightCompanyReportBean(labels, cntTickets);

		}
		return null;
	}

	@RequestMapping(value = "/api/getHotelEarningsReport", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('HOTEL_ADMIN')")
	public @ResponseBody String getHotelEarningsReport(@RequestBody FlightReportRequestBean reqestData) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			HotelAdmin admin = adminService.findByUsername(username);

			Hotel hotel = admin.getHotel();

			Date dateFrom = reqestData.getDateFrom();
			Date dateTo = reqestData.getDateTo();
			Date today = new Date();

			if (dateFrom.after(today)) {
				return "Start date is in the future.";
			}

			double total = 0;

			for (HotelReservation reservation : hotel.getReservations()) {

				if (reservation.getFirstDay().after(dateFrom) && reservation.getFirstDay().before(dateTo)
						&& reservation.getUser() != null) {
					total += reservation.getPaidPrice();
				}
			}

			return Double.toString(total);
		}
		return null;
	}
}
