package com.tim9.PlanJourney.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.PlanJourney.beans.ReviewBean;
import com.tim9.PlanJourney.beans.VehicleReservationBean;
import com.tim9.PlanJourney.hotel.Hotel;
import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.Reservation;
import com.tim9.PlanJourney.models.Review;
import com.tim9.PlanJourney.models.rentacar.RentACarCompany;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;
import com.tim9.PlanJourney.service.HotelReservationService;
import com.tim9.PlanJourney.service.HotelRoomService;
import com.tim9.PlanJourney.service.HotelService;
import com.tim9.PlanJourney.service.RegisteredUserService;
import com.tim9.PlanJourney.service.RentACarCompanyService;
import com.tim9.PlanJourney.service.ReservationService;
import com.tim9.PlanJourney.service.ReviewService;
import com.tim9.PlanJourney.service.UserService;
import com.tim9.PlanJourney.service.VehicleReservationService;
import com.tim9.PlanJourney.service.VehicleService;


@RestController
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private RentACarCompanyService rentACarCompanyService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private VehicleReservationService vehicleReservationService;
	
	@Autowired
	private RegisteredUserService userService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private HotelReservationService hotelReservationService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelRoomService roomService;
	
	
	@RequestMapping(value = "/api/reviewVehicle", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	// Recieves parameters for search and returns list of found vehicles
	// Visible to everyone
	public void reviewVehicle(@RequestBody ReviewBean bean){
		System.out.println("Reviewed");
		VehicleReservation reservation = vehicleReservationService.findOne(bean.getReservationId());
		Long exist = checkIfExistsVR(reservation);
		RentACarCompany company = (RentACarCompany) reservation.getCompany();
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		RegisteredUser user = (RegisteredUser) userService.findByUsername(username);
		Review review;
		if(exist != -1l) {
			review = reviewService.findOne(exist);
			review.setRating(bean.getRating());
		}
		else {
			review = new Review(user, reservation,bean.getRating());
			reservation.getReservationReviews().add(review);
		}

		reviewService.save(review);
		reservationService.save(reservation);
		System.out.println("sacuvano");
		
		calculateNewVehicleRating(reservation);
		calculateNewRentACarRating(company);
	}
	@RequestMapping(value = "/api/reviewHotel", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	public void reviewHotelRoom(@RequestBody ReviewBean bean){
		System.out.println("Reviewed");
		HotelReservation reservation = hotelReservationService.findOne(bean.getReservationId());
		Long exist = checkIfExistsHR(reservation);
		Hotel company = (Hotel) reservation.getHotel();
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		RegisteredUser user = (RegisteredUser) userService.findByUsername(username);
		Review review;
		if(exist != -1l) {
			review = reviewService.findOne(exist);
			review.setRating(bean.getRating());
		}
		else {
			review = new Review(user, reservation,bean.getRating());
			reservation.getReservationReviews().add(review);
		}

		reviewService.save(review);
		reservationService.save(reservation);
		System.out.println("sacuvano");
		
		calculateNewRoomRating(reservation);
		calculateNewHotelRating(company);
	}
	private Long checkIfExistsVR(VehicleReservation reservation) {
		List<VehicleReservation> vr =  vehicleReservationService.findAll();
		ArrayList<VehicleReservation> vehicleReservations = new ArrayList<>();
		vehicleReservations.addAll(vr);
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		RegisteredUser user = (RegisteredUser) userService.findByUsername(username);
		
		ArrayList<Review> reviews = new ArrayList<>();
		reviews.addAll(reservation.getReservationReviews());
		for (Review review : reviews) {
			if(review.getUser().getUsername().equals(username)) {
				return review.getId();
			}
		}
		return -1l;
	
	}
	private void calculateNewRentACarRating(RentACarCompany company) {
		ArrayList<Reservation> reservations = new ArrayList<>();
		reservations.addAll(company.getReservations());
		double sum = 0;
		double num = 0;
		for (Reservation re : reservations) {
			for (Review rev : re.getReservationReviews()) {
				sum+=rev.getRating();
				num++;
			}
		}
		try {
			double a = sum/num;
			company.setRating(a);
		}catch (Exception e) {
			System.out.println("kec");
		}
		
		rentACarCompanyService.save(company);
	}
	private void calculateNewVehicleRating(VehicleReservation reservation) {
		ArrayList<VehicleReservation> vehicleReservations = new ArrayList<>();
		vehicleReservations.addAll(reservation.getVehicle().getReservations());
		double sum = 0;
		double num = 0;
		for (Reservation re : vehicleReservations) {
			for (Review rev : re.getReservationReviews()) {
				sum+=rev.getRating();
				num++;
			}
		}
		reservation.getVehicle().setRating(sum/num);
		vehicleService.save(reservation.getVehicle());
	}
	
	private Long checkIfExistsHR(HotelReservation reservation) {
		
		List<HotelReservation> vr =  hotelReservationService.findAll();
		ArrayList<HotelReservation> roomReservations = new ArrayList<>();
		roomReservations.addAll(vr);
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		RegisteredUser user = (RegisteredUser) userService.findByUsername(username);
		
		ArrayList<Review> reviews = new ArrayList<>();
		reviews.addAll(reservation.getReservationReviews());
		for (Review review : reviews) {
			if(review.getUser().getUsername().equals(username)) {
				return review.getId();
			}
		}
		return -1l;
		
	
	}
	private void calculateNewHotelRating(Hotel company) {
		ArrayList<Reservation> reservations = new ArrayList<>();
		reservations.addAll(company.getReservations());
		double sum = 0;
		double num = 0;
		for (Reservation re : reservations) {
			for (Review rev : re.getReservationReviews()) {
				sum+=rev.getRating();
				num++;
			}
		}
		try {
			double a = sum/num;
			company.setRating(a);
		}catch (Exception e) {
			System.out.println("kec");
		}
		
		hotelService.save(company);
	}
	private void calculateNewRoomRating(HotelReservation reservation) {
		ArrayList<HotelReservation> roomReservations = new ArrayList<>();
		
		for (HotelReservation hotelReservation : reservation.getHotel().getReservations()) {
			if(hotelReservation.getRoom().getId() == reservation.getRoom().getId()) {
				roomReservations.add(hotelReservation);
			}
		}
		double sum = 0;
		double num = 0;
		for (Reservation re : roomReservations) {
			for (Review rev : re.getReservationReviews()) {
				sum+=rev.getRating();
				num++;
			}
		}
		reservation.getRoom().setRating(sum/num);
		roomService.save(reservation.getRoom());
	}

}
