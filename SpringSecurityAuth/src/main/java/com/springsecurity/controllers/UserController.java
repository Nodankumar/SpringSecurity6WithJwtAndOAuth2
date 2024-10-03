package com.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.model.Users;
import com.springsecurity.services.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String verify(@RequestBody Users user) {

		return userService.verify(user);
	}

}
