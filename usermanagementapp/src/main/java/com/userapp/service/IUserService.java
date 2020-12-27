package com.userapp.service;

import java.util.Map;

import com.userapp.entity.User;

public interface IUserService {

	public Map<Integer,String> findCountries();
	
	public Map<Integer,String> findStates(Integer countryId);
	
	public Map<Integer, String> findCities(Integer stateId);
	
	public boolean isEmailUnique(String emailId);
	
	public boolean saveUser(User user);
	
	//Login page operations
	public String loginCheck(String email, String pwd);
	
	public String passwordGenerator();
	//unlock account operations
	public boolean isTempPwdValid(String email, String tempPwd);
	
	public boolean unlockAccount(String email, String newPwd);
	
	//forgot password operations
	public String forgotPassword(String email);

}