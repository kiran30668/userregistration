package com.userapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "city")
public class City {
	@Id
	private Integer cityId;
	private String cityName;
	
}
