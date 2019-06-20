package com.tim9.PlanJourney.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.tim9.PlanJourney.beans.LoginBean;
import com.tim9.PlanJourney.beans.RegisterBean;
import com.tim9.PlanJourney.models.Authority;
import com.tim9.PlanJourney.models.RegisteredUser;
import com.tim9.PlanJourney.models.SystemAdmin;
import com.tim9.PlanJourney.models.User;
import com.tim9.PlanJourney.models.UserTokenState;
import com.tim9.PlanJourney.security.TokenUtils;
import com.tim9.PlanJourney.service.AuthorityService;
import com.tim9.PlanJourney.service.EmailService;
import com.tim9.PlanJourney.service.UserService;
import com.tim9.PlanJourney.service.impl.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService autService;

	@Autowired
	private EmailService emailService;
	

	@CrossOrigin()
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginBean authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {
		System.out.println(authenticationRequest.getUsername() + "    " + authenticationRequest.getPassword());
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Kreiraj token
		User user = (User) authentication.getPrincipal();
		System.out.println(user.getFirstName());

		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		response.setHeader("Authorization", "Bearer jwt_token");
		// Vrati token kao odgovor na uspesno autentifikaciju
		boolean notConfrirmed = false;
		if (user instanceof RegisteredUser) {
			RegisteredUser regUser = (RegisteredUser) user;
			if (!regUser.isConfirmed()) {
				notConfrirmed = true;
			}
		}
		if (notConfrirmed) {
			return ResponseEntity.ok("NOT CONFIRMED");
		}

		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	@CrossOrigin()
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public HttpStatus register(@RequestBody RegisterBean bean, HttpServletResponse response)
			throws AuthenticationException, IOException {
		System.out.println(bean.getUsername() + "    " + bean.getPassword());
		User u = userService.findOneByUsername(bean.getUsername());
		if (u != null) {
			return HttpStatus.CONFLICT;
		}
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		RegisteredUser user = new RegisteredUser(bean.getUsername(), bc.encode(bean.getPassword()), bean.getFirstName(),
				bean.getLastName(), bean.getEmail());
		Authority aut = autService.findOne(5l);
		ArrayList<Authority> authorities = new ArrayList<>();
		authorities.add(aut);
		user.setAuthorities(authorities);
		user.setConfirmed(false);
		user.setLoggedBefore(true);
		user.setCity(bean.getCity());
		user.setPhone(bean.getPhone());
		user.setConfirmed(true);
		userService.save(user);
		try {
			emailService.sendRegistrationEmail(user); // sends email
		} catch (MailException | InterruptedException e) {
			e.printStackTrace();
		}
		return HttpStatus.OK;
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	@CrossOrigin()
	@RequestMapping(value = "/registrationConfirmation/{encoded}", method = RequestMethod.GET)
	// Recieves enoceded username and switches variable confirmed to true so user
	// can log in
	public RedirectView Conform(@PathVariable("encoded") String encoded) throws AuthenticationException, IOException {
		byte[] decoded = Base64.getDecoder().decode(encoded);
		String username = new String(decoded, "UTF-8");
		System.out.println(username);
		User user = userService.findOneByUsername(username);
		user.setConfirmed(true);
		userService.save(user);
		RedirectView view = new RedirectView("https://planjourney.herokuapp.com/");
		return view;
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
	
	
	
	
	@CrossOrigin()
	@RequestMapping(value = "/logintest", method = RequestMethod.GET)
	public ArrayList<String> createAuthorityes() {
		ArrayList<String> autoriteti = new ArrayList<>();
		Authority a1 = new Authority();
		a1.setName("SYS_ADMIN");
		a1.setId(1l);
		autoriteti.add(a1.getName());
		autService.save(a1);
		
		
		Authority a2 = new Authority();
		a2.setName("FLIGHT_ADMIN");
		a2.setId(2l);
		autoriteti.add(a2.getName());
		autService.save(a2);
		
		Authority a3 = new Authority();
		a3.setName("HOTEL_ADMIN");
		a3.setId(3l);
		autoriteti.add(a3.getName());
		autService.save(a3);
		
		
		Authority a4 = new Authority();
		a4.setName("RENT_ADMIN");
		a4.setId(4l);
		autoriteti.add(a4.getName());
		autService.save(a4);
		
		Authority a5 = new Authority();
		a5.setName("REGISTERED");
		a5.setId(5l);
		autoriteti.add(a5.getName());
		autService.save(a5);
		
		return autoriteti;
	}
	@CrossOrigin()
	@RequestMapping(value = "/getA", method = RequestMethod.GET)
	public String displayAu() {
		Authority a = autService.findOne(1l);
		return a.getName();
	}

}
