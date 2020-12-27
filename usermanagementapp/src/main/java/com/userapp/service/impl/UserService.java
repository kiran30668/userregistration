package com.userapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userapp.entity.City;
import com.userapp.entity.Country;
import com.userapp.entity.State;
import com.userapp.entity.User;
import com.userapp.repository.CityRepository;
import com.userapp.repository.CountryRepository;
import com.userapp.repository.StateRepository;
import com.userapp.repository.UserRepository;
import com.userapp.service.IUserService;

@Service
public class UserService implements IUserService { 

	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Map<Integer, String> findCountries() {
		List<Country> countriesList=countryRepo.findAll();
		Map<Integer,String> countries= new HashMap<>();
		countriesList.forEach(country -> {
			countries.put(country.getCountryId(), country.getCountryName());
		});

		return countries;
	}

	@Override
	public Map<Integer, String> findStates(Integer countryId) {
		List<State> statesList=stateRepo.findAll();
		Map<Integer,String> states=new HashMap<>();
		statesList.forEach(state -> {
			states.put(state.getStateId(), state.getStateName());
		});
		
		return states;
	}

	@Override
	public Map<Integer, String> findCities(Integer stateId) {
		
		List<City> cityList=cityRepo.findAll();
		Map<Integer, String> cities=new HashMap<>();
		cityList.forEach(city -> {
			cities.put(city.getCityId(), city.getCityName());
		});
		return cities;
	}

	@Override
	public boolean isEmailUnique(String emailId) {
		User userDetails=userRepo.findByEmailId(emailId);
		return userDetails.getUserId()==null;
	}

	
	public String passwordGenerator() {
		char[] password=new char[5];
		String alphaNumeric="ABCDEFGHabcdefgh1234567890";
		Random randomPwd=new Random();
		for(int i=0;i<5;i++) {
			password[i]=alphaNumeric.charAt(randomPwd.nextInt(alphaNumeric.length()));
		}
		System.out.println(password.toString());
		return password.toString();
		
	}
	
	
	@Override
	public boolean saveUser(User user) {
		
		user.setPassword(passwordGenerator());
		user.setAccountStatus("LOCKED");
		User userObj=userRepo.save(user);
		return userObj.getUserId()!=null;
	}

	//test1: invalid emailId and password ==> Invalid Credentials
	//test2 : valid email and password and account is locked ==> ACCOUNT_LOCKED
	//test3 : valid email and password and account is unlocked ==> LOGIN_SUCCESS
	@Override
	public String loginCheck(String email, String pwd) {
		User userDetails=userRepo.findByEmailIdAndPassword(email, pwd);
		if(userDetails!=null) {
			if(userDetails.getAccountStatus().equals("LOCKED")) {
				return "ACCOUNT_LOCKED";
			}else {
				return "LOGIN_SUCCESS";
			}
		}
		return "INVALID_CREDENTIALS";
	}

	
	//testcase1: User has given invalid temp-pwd ==>false
	//test case 2: User has given valid temp-pwd ==> true
	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {

		User userDetails=userRepo.findByEmailIdAndPassword(email, tempPwd);
		return userDetails.getUserId()!=null;
	}

	@Override
	public boolean unlockAccount(String email, String newPwd) {

		User userDetails= userRepo.findByEmailId(email);
		userDetails.setPassword(newPwd);
		userDetails.setAccountStatus("UNLOCKED");
		try {
			userRepo.save(userDetails);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//test case 1: ==> User entered valid email -> return existing pwd
	//test case 2: ==> User entered invalid email ->NPE
	@Override
	public String forgotPassword(String email) {
		User userDetails=userRepo.findByEmailId(email);
		if(userDetails!=null) {
			return userDetails.getPassword();
		}
		return null;
	}

}
