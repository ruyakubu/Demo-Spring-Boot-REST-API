package com.demo.spring.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring.model.User;
import com.demo.spring.repository.UserRepository;
import com.demo.spring.security.UserContext;

@Service
@Transactional
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
    	
    	com.demo.spring.model.User user = new User();
		user = userRepository.findByUserEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		

		return new UserContext(user);
    }
    
}
