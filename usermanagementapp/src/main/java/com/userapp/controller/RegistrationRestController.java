package com.userapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userapp.entity.User;
import com.userapp.service.IUserService;

@RestController
public class RegistrationRestController {
	
	@Autowired
	private IUserService service;	
	@PutMapping("/user")
	public boolean saveUser(User user) {
		boolean b=false;
		if(user.getPassword()!=null) {
			b=service.saveUser(user);
		}else {
			String pwd=service.passwordGenerator();
			user.setPassword(pwd);
			b=service.saveUser(user);
		}
		
		return b;
	}
	
}
