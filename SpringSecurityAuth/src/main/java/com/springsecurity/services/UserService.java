package com.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.springsecurity.model.Users;

@Repository
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(12);

	public Users register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}

	public String verify(Users user) {
		Authentication authentication = authManager
										.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		String jwtToken = null;
		if(authentication.isAuthenticated()) {
			jwtToken = jwtService.generateToken(user.getUsername());
			return jwtToken;
		}
		return jwtToken;
	}

}
