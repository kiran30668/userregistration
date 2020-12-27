package com.userapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "countryMaster")
@Data
public class Country {
	@Id
	private Integer countryId;
	
	private String countryName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="STATEID_FK")
	private State state;
	
}
