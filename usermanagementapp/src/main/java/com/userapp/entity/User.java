package com.userapp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name="user_master")
@Data
public class User {
	
	@Id
	private Integer userId;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	private Integer phoneNumber;
	
	@NotNull
	@Past
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private Date dob;
	
	@NotNull
	private Gender gender;
	
	@NotNull
	private List<Country> country;
	
	
	@NotNull
	private List<State> state;
	
	@NotNull
	private List<City> city;
	
	
	private String password;
	
	
	private String accountStatus;
	

}
