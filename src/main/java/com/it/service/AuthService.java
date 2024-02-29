package com.it.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.repository.SignupRepository;
import com.it.repository.entity.Signup;

@Service
public class AuthService 
{
	@Autowired
	private SignupRepository signupRepository;
	
	public boolean validateUser(String username, String password)
	{
		Optional<Signup> optional= signupRepository.findByUsernameAndPassword(username, password);
		return optional.isPresent();
	}
}
