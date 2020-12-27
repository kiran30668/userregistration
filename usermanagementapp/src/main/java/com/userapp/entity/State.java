package com.userapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "stateMaster")
public class State {
	@Id
	private Integer stateId;
	
	
	private String stateName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CITYID_FK")
	private City city;
}
