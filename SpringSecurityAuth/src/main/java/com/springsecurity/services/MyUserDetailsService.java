package com.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.model.UserPrinicpal;
import com.springsecurity.model.Users;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user  = repo.findByUsername(username);
		
		if(user == null) {
			System.err.println("User Not found");
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new UserPrinicpal(user);
	}

}
