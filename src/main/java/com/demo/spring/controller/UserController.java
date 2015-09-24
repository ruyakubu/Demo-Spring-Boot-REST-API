package com.demo.spring.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.UserService;
import com.demo.spring.model.User;

@RestController
@RequestMapping("/api")
public class UserController {
	
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List getUsers(HttpServletRequest request) {
		
		int page = 0;
		int pageSize = 100;
		
		if(request.getParameter("page") != null)
		{
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("pageSize") != null) 
		{
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}

		Pageable pageable = new PageRequest(page, pageSize);
		Page<User> user = userService.getAllUsers(pageable);
		
		return user.getContent();

	}
	

	@RequestMapping(value = "/users/{country}/**", method = RequestMethod.GET)
	public @ResponseBody List<User> getUserCountry(@PathVariable String country) {
		
		Pageable pageable = new PageRequest(0, 100);
		Page<User> user = userService.getUserByCountry(country, pageable);
		return user.getContent();
		
	}


}