package com.it.controller;

/* REST API is a technology which allows to expose data over the network with the help of HTTP protocol.
 * We can use the following  standard methods to exchange data:
 * 		POST:	create new data/ record, 
 * 		GET:	fetch data
 * 		PUT:	update data
 * 		DELETE:	delete data
 * 		PATCH:	partial update of data
 * 		OPTION:	Test whether we are able to make request or not
*/


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.repository.SignupRepository;
import com.it.repository.entity.Signup;
import com.it.spring.security.JwtUtils;

@RestController
@RequestMapping("/api")	// defining prefix for REST API requests from browser using http://localhost:8080/api/signups
public class SignupRESTController 
{
	// creating a bean through DEPENDENCY INJECTION USING @Autowired annotation
	@Autowired				
	private SignupRepository signupRepository;

	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/cauth")
	public Map<String,Object> postLogin(@RequestBody SignupDTO signupRequest) {
		//authentication has two things - user-name and role
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signupRequest.getEmail(), signupRequest.getPassword()));
		//it will come here only when user-name and password are correct!!
		String jwt = jwtUtils.generateJwtToken(authentication);
		Map<String,Object> jwtReponse = new HashMap<>();
		jwtReponse.put("Authorization", jwt);
		jwtReponse.put("email", signupRequest.getEmail());
		jwtReponse.put("title", "Manager");
		return jwtReponse;
	}
	
	
	// HTTP: using http://localhost:8080/api/signups
	@GetMapping("signups")
	public List<Signup> showSignups() 
	{
		// Fetch remaining data from database and display back on home-page's screen
		List<Signup> signups = signupRepository.findAll();	
		return signups;
	}
	
	
	@DeleteMapping("signups/{username}")	// using http://localhost:8080/api/signups/username
	@PreAuthorize("hasAuthority('ADMIN')")
	public MessageDTO deleteSignups(@PathVariable String username) 
	{	// @PathVariable annotation is used to read data from URI
		signupRepository.deleteById(username);
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessage("Record deleted successfully!!!");
		messageDTO.setCode(233);
		return messageDTO;
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/signups")	// using http://localhost:8080/api/signups in Postman with POST request
	public MessageDTO postSignup(@RequestBody Signup signup) 
	{
		signup.setTimestamp(new Date());
		Optional<Signup> optional= signupRepository.findById(signup.getUsername());
		MessageDTO messageDTO = new MessageDTO();
		if(optional.isPresent())
		{
			messageDTO.setMessage("That username is not allowed");
			messageDTO.setCode(233);
		}
		else
		{
			signup.setPassword(passwordEncoder.encode(signup.getPassword()));
			signupRepository.save(signup);
			messageDTO.setMessage("you are all signed-up!");
			messageDTO.setCode(233);
		}
		return messageDTO;
	}
}