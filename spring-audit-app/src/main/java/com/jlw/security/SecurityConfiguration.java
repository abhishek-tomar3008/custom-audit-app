package com.jlw.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;



import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration {
	

	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.cors().disable()
		.authorizeHttpRequests()
	
		.anyRequest().authenticated().and()

		.formLogin()
		.and()
		.httpBasic();

		return http.build();
		
		

	}
	
	@Bean
	public UserDetailsService users() {
		// The builder will ensure the passwords are encoded before saving in memory
		UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails user = users
			.username("Abhishek")
			.password("1234")
			.roles("USER")
			.build();
		UserDetails admin = users
			.username("admin")
			.password("1234")
			.roles("ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
  
}
