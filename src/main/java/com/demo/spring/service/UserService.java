package com.demo.spring.service;

import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 

 
import com.demo.spring.model.User;

  
public interface UserService { 
 
 
   Page<User> getAllUsers(Pageable pageable); 
 
 
   Page<User> getUserByCountry(String country, Pageable pageable); 

} 
