package com.tim9.PlanJourney.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

import com.tim9.PlanJourney.hotel.HotelAdmin;
import com.tim9.PlanJourney.models.flight.FlightAdmin;
import com.tim9.PlanJourney.models.rentacar.RentACarAdmin;
@Entity
public class SystemAdmin extends User {

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	/*private ArrayList<HotelAdmin> hotelAdmins;
	private ArrayList<FlightAdmin> flightAdmins;
	private ArrayList<RentACarAdmin> rentACarAdmins;

	public SystemAdmin() {
		super();
	}

	public SystemAdmin( String username, String password, String firstName, String lastName, String email,
			ArrayList<HotelAdmin> hotelAdmins, ArrayList<FlightAdmin> flightAdmins,
			ArrayList<RentACarAdmin> rentACarAdmins) {
		super( username, password, firstName, lastName, email);
		this.hotelAdmins = hotelAdmins;
		this.flightAdmins = flightAdmins;
		this.rentACarAdmins = rentACarAdmins;
	}

	public ArrayList<HotelAdmin> getHotelAdmins() {
		return hotelAdmins;
	}

	public void setHotelAdmins(ArrayList<HotelAdmin> hotelAdmins) {
		this.hotelAdmins = hotelAdmins;
	}

	public ArrayList<FlightAdmin> getFlightAdmins() {
		return flightAdmins;
	}

	public void setFlightAdmins(ArrayList<FlightAdmin> flightAdmins) {
		this.flightAdmins = flightAdmins;
	}

	public ArrayList<RentACarAdmin> getRentACarAdmins() {
		return rentACarAdmins;
	}

	public void setRentACarAdmins(ArrayList<RentACarAdmin> rentACarAdmins) {
		this.rentACarAdmins = rentACarAdmins;
	}
*/
}
