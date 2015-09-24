package com.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.demo.spring.model.User;
import com.demo.spring.service.UserService;
import com.demo.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<User> getAllUsers(Pageable pageable)
	{
		return this.userRepository.findAll(pageable);
	}
	   
	public Page<User> getUserByCountry(String country, Pageable pageable)
	{
		return this.userRepository.findByCountryAllIgnoringCase(country, pageable);
	}


}
	
	


