package com.tim9.PlanJourney.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.tim9.PlanJourney.security.TokenUtils;
import com.tim9.PlanJourney.security.auth.RestAuthenticationEntryPoint;
import com.tim9.PlanJourney.security.auth.TokenAuthenticationFilter;
import com.tim9.PlanJourney.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

	// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
		// BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Autowired
		private CustomUserDetailsService jwtUserDetailsService;

		// Neautorizovani pristup zastcenim resursima
		@Autowired
		private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		// Definisemo nacin autentifikacije
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
		}

		@Autowired
		TokenUtils tokenUtils;

		// Definisemo prava pristupa odredjenim URL-ovima
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				// komunikacija izmedju klijenta i servera je stateless
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				
				// za neautorizovane zahteve posalji 401 gresku
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				
				// svim korisnicima dopusti da pristupe putanjama /auth/** i /h2-console/**
				.authorizeRequests()
				.antMatchers("/auth/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS,"/api/**").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				//.antMatchers("/api/**").permitAll()
				.antMatchers(HttpMethod.POST,"/api/searchHotels/**").permitAll()
				.antMatchers(HttpMethod.GET,"/api/getHotel/**").permitAll()
				.antMatchers(HttpMethod.GET,"/front/**").permitAll()
				
				// svaki zahtev mora biti autorizovan
				.anyRequest().authenticated().and()
				
				// presretni svaki zahtev filterom
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

			http.csrf().disable();
		}

		// Generalna bezbednost aplikacije
		@Override
		public void configure(WebSecurity web) throws Exception {
			
			// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
			web.ignoring().antMatchers(HttpMethod.POST,"/","/api/flightSearch","/api/vehicleSearch","/api/getRentACarCompanies");

			web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js","/api/vehicleSearch","/api/getProducers","/api/getTypes","/api/addRC","/api/testFlightData","/api/getFlight/{id}","/api/searchFlightCompanies/{companyName}","/api/getRentACarCompanyInfo/{id}");
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**","/","/api/*");
		}

}
