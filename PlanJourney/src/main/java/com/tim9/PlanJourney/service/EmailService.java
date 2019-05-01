package com.tim9.PlanJourney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tim9.PlanJourney.models.RegisteredUser;

@Service
public class EmailService {
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Async
	public void sendReservationRequest(RegisteredUser user) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila..." + user.getEmail());

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("plan.journey.isa@gmail.com");
		mail.setSubject("PlanJourney: Flight Reservation Request");
		mail.setText("Go to: " + "http://localhost:8081/confirmationPage" + " to se details.");
		javaMailSender.send(mail);

		System.out.println("E-mail sent!");
	}
	
	

}
