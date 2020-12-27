package com.userapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.userapp.service.IUserService;

@RestController
public class UnlockAccountRestController {
	
	@Autowired
	IUserService service;
	

}
