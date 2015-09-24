package com.demo.spring.repository;

import java.util.List;

import com.demo.spring.model.User;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	
	public Page<User> findAll(Pageable pageable);
	
	public Page<User> findByCountryAllIgnoringCase(String country, Pageable pageable);
	
	public Page<User> findByRegionAndCountryAllIgnoringCase(String region, String country, Pageable pageable);
	
	public User findByUserEmail(@Param("username") String username);
	
	public User findById(@Param("username") String username);
	
	@Transactional
	void deleteByUserEmail(@Param("username") String username);

}