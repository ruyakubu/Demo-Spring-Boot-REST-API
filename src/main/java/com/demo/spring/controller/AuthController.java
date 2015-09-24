package com.demo.spring.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/auth")
public class AuthController {

	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public @ResponseBody
	void success(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("success");
		//request.getRequestDispatcher("/").forward(request, response);
	}

	@RequestMapping(value = "/failure", method = RequestMethod.POST)
	public @ResponseBody
	void failure(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("failure");
		//request.getRequestDispatcher("/login").forward(request, response);
	}
}