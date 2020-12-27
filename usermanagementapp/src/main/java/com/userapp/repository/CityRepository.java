package com.userapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userapp.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	
	List<City> findByStateId(Integer stateId);
}
