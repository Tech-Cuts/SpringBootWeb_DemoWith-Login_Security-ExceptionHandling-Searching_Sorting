package com.it.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.it.repository.SignupRepository;
import com.it.service.AuthService;

@Controller
public class LoginController {
	
	@Autowired							// DEPENDENCY INJECTION USING @Autowired annotation
	private AuthService authService;
	
	@Autowired				
	private SignupRepository signupRepository;
	
	@GetMapping("/greet")
	public String showPage() {
		return "welcome"; //  /welcome.jsp
	}

	@GetMapping("/auth")
	public String showLoginPage() {
		return "login"; //  /login.jsp
	}

	// <form action="auth" method="post">
	@PostMapping("/auth")
	public String postLogin(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (authService.validateUser(username, password))
		{
			req.setAttribute("message", "Welcome back, " + username + "!");
			
			// Fetch data from database
			List<com.it.repository.entity.Signup> signups = signupRepository.findAll();	
			req.setAttribute("signups", signups); // adding the list into the request
			return "home";
		}
		req.setAttribute("message", "information mismatch!");
		return "login"; //  /login.jsp
	}
}