package com.demo.spring.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public String Login() {
		return "Hello ";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {

		return "Logout ";
	}
	

}