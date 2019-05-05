package com.tim9.PlanJourney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.flight.FlightReservation;

@Service
public class EmailService {
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Async
	public void sendReservationRequest(FlightReservation reservation) throws MailException, InterruptedException {


		System.out.println("Slanje emaila..." + reservation.getUser().getEmail());
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(reservation.getUser().getEmail());
		mail.setFrom("plan.journey.isa@gmail.com");
		mail.setSubject("PlanJourney: Flight Reservation Request");
		mail.setText("Go to: " + "http://localhost:8081/confirmationPage"+"/" + reservation.getId() + " to se details.");
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
		mail.setText("Flight is successfuly reserved!");
		javaMailSender.send(mail);

		System.out.println("E-mail sent!");
	}
	
	

}
