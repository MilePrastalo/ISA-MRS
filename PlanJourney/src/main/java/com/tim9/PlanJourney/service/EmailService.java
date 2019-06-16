package com.tim9.PlanJourney.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.hotel.HotelReservation;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.User;
import com.tim9.PlanJourney.models.flight.FlightReservation;
import com.tim9.PlanJourney.models.rentacar.VehicleReservation;

@Service
public class EmailService {
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	
	
	@Async
	public void sendReservationRequest(RegisteredUser friend, Long id) throws MailException, InterruptedException {
		Long id_ = id;
		System.out.println("Slanje emaila..." + friend.getEmail());
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(friend.getEmail());
		mail.setFrom("plan.journey.isa@gmail.com");
		mail.setSubject("PlanJourney: Flight Reservation Request");
		mail.setText("Go to: " + "http://localhost:8081/confirmationPage"+"/" + id_ + " to se details.");
		javaMailSender.send(mail);

		System.out.println("E-mail sent!");
	}
	//Sends email with encoded username after registration
	@Async
	public void sendRegistrationEmail(User user) throws MailException, InterruptedException {
		System.out.println("Slanje emaila..." + user.getEmail());
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("plan.journey.isa@gmail.com");
		mail.setSubject("PlanJourney: Flight Reservation Request");
		String url = "";
		try {
			url = Base64.getEncoder().encodeToString(user.getUsername().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mail.setText("Go to: " + "http://localhost:8080/auth/registrationConfirmation"+"/" + url + " to activate your account.");
		javaMailSender.send(mail);

		System.out.println("E-mail sent!");
	}
	
	@Async
	public void sendReservationMade(FlightReservation reservation) throws MailException, InterruptedException {

		System.out.println("Slanje emaila..." + reservation.getUser().getEmail());
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(reservation.getUser().getEmail());
		mail.setFrom("plan.journey.isa@gmail.com");
		mail.setSubject("PlanJourney: Flight Reservation");
		String emailText = "Flight is successfuly reserved!";
		emailText += "\nFrom: " + reservation.getFlight().getStartDestination().getName();
		emailText += "\nTo: " + reservation.getFlight().getEndDestination().getName();
		emailText += "\nStart date: " + sdf.format(reservation.getFlight().getStartDate());
		emailText += "\nEnd date: " + sdf.format(reservation.getFlight().getEndDate());
		emailText += "\nPrice: " + reservation.getPrice();
		emailText += "\nPassangers: " + reservation.getPassangers().size();
		emailText += "\nHotel reservations: ";
		for (HotelReservation hotel : reservation.getHoteReservations()) {
			emailText += "\n\t" +   hotel.getHotel().getName()  + " (" + sdf.format(hotel.getFirstDay())  + " : " + sdf.format(hotel.getLastDay()) + " )" + " => " +  hotel.getPaidPrice();
		}
		emailText += "\nRentACar reservations: ";
		for (VehicleReservation vehicle : reservation.getVehicleReservations()) {
			emailText += "\n\t" +   vehicle.getVehicle().getName() + " (" + sdf.format(vehicle.getDateFrom())  + " : " + sdf.format(vehicle.getDateTo()) + " )"+ " => " + vehicle.getCena();
		}
		mail.setText(emailText);
		javaMailSender.send(mail);
		System.out.println("E-mail sent!");
	}
	
	

}
