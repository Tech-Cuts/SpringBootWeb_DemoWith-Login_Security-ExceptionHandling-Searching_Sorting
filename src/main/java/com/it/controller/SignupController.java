package com.it.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.it.repository.SignupRepository;
import com.it.repository.entity.Signup;


//to sort according to user-name in ascending order
class SortUser_Ascending implements Comparator<Signup>	
{
	@Override
	public int compare(Signup o1, Signup o2) 
	{
		int sorted_ascending=o1.getUsername().compareToIgnoreCase(o2.getUsername());
		return sorted_ascending;
	}
}


//to sort according to user-name in descending order
class SortUser_Descending implements Comparator<Signup>	
{
	@Override
	public int compare(Signup o1, Signup o2) 
	{
		int sorted_descending =o2.getUsername().compareToIgnoreCase(o1.getUsername());
		return sorted_descending;
	}
}


//to sort according to e-mail in ascending order
class SortEmail_Ascending implements Comparator<Signup>	
{
	@Override
	public int compare(Signup o1, Signup o2) 
	{
		int sorted_ascending=o1.getEmail().compareToIgnoreCase(o2.getEmail());
		return sorted_ascending;
	}
}


//to sort according to e-mail in descending order
class SortEmail_Descending implements Comparator<Signup>	
{
	@Override
	public int compare(Signup o1, Signup o2) 
	{
		int sorted_descending =o2.getEmail().compareToIgnoreCase(o1.getEmail());
		return sorted_descending;
	}
}


@Controller
public class SignupController {
	
	// creating a bean through DEPENDENCY INJECTION USING @Autowired annotation
	@Autowired				
	private SignupRepository signupRepository;

	
	// from "deleteSignup?uname=hacker420" in home.jsp
	@GetMapping("deleteSignup")
	// now need a method to request deletion of sign-up based on variable uname from home.jsp
	public String deleteSignupMethod(@RequestParam String uname, Model model) {
		signupRepository.deleteById(uname);		// uname is the primary key in db-table
		// Fetch remaining data from database and display back on home-page's screen
		List<Signup> signups = signupRepository.findAll();	
		model.addAttribute("signups", signups); // adding the list into the request
		// model is a hash-map which is used to carry data from controller to JSP
		return "home"; //  /home.jsp
	}
	
	
	// from "editSignup?uname=hacker420" in home.jsp
	@GetMapping("editSignup")
	// now need a method to request update of sign-up based on variable uname from home.jsp
	public String editSignupdata(@RequestParam String uname, Model model) {
		// now find a single sign-up data by uname; uname is the primary key in db-table
		Optional<Signup> optional = signupRepository.findById(uname);		
		// then obtain the data and display it back on sing-up page's screen for updating
		model.addAttribute("one_signup", optional.get()); // adding into the request
		// model is a hash-map which is used to carry data from controller to JSP
		return "edit_signup"; //  making edit_signup.jsp a separate page... not a good practice
	}
	
	
	// from "sortByEmail" in home.jsp (BY DEFINITION OF CUSTOM CLASSES)
	/* 
	@GetMapping("/sortByEmail")
	public String sortByEmail(@RequestParam String sortbyE, Model model) 
	{
		List<Signup> signups=signupRepository.findAll();
		if(sortbyE.equalsIgnoreCase("asc")) {
			model.addAttribute("sortbyE", "desc");
			model.addAttribute("message","Sorted by Email in descending order!");
			Collections.sort(signups, new SortEmail_Descending());
		}else 
		{
			model.addAttribute("sortbyE", "asc");
			model.addAttribute("message","Sorted by Email in ascending order!");
			Collections.sort(signups, new SortEmail_Ascending());
		}
		model.addAttribute("signups", signups);		
		return "home";
	}
	*/
	
	
	// from "sortByEmail" in home.jsp  (WITHOUT CUSTOM CLASSES; BY NAGENDRA KUMAR)
	/*
	@GetMapping("/sortByEmail")
	public String sortByEmail(@RequestParam String sortbyE, Model model) 
	{
		List<Signup> signups=signupRepository.findAll();
		if(sortbyE.equalsIgnoreCase("asc")) 
		{
			model.addAttribute("sortbyE", "desc");
			Collections.sort(signups, new Comparator<Signup>()
			{
				@Override
				public int compare(Signup o1, Signup o2)
				{
					return o1.getEmail().compareTo(o2.getEmail());
				}
			});
		}else 
		{
			model.addAttribute("sortbyE", "asc");
			Collections.sort(signups, new Comparator<Signup>()
			{
				@Override
				public int compare(Signup o1, Signup o2)
				{
					return o2.getEmail().compareTo(o1.getEmail());
				}
			});
		}
		model.addAttribute("signups", signups);		
		return "home";
	}
	*/
	
	
	// from "sortByEmail" in home.jsp  (SAME AS ABOVE, USING LAMBDA EXP; BY NAGENDRA KUMAR)
	@GetMapping("/sortByEmail")
	public String sortByEmail(@RequestParam String sortbyE, Model model) 
	{
		List<Signup> signups=signupRepository.findAll();
		if(sortbyE.equalsIgnoreCase("asc")) 
		{
			model.addAttribute("sortbyE", "desc");
			Collections.sort(signups, (o1,o2)->o1.getEmail().compareTo(o2.getEmail()));
		}else 
		{
			model.addAttribute("sortbyE", "asc");
			Collections.sort(signups, (o1,o2)->o2.getEmail().compareTo(o1.getEmail()));
		}
		model.addAttribute("signups", signups);		
		return "home";
	}
	

	// from "sortByUsername" in home.jsp
	@GetMapping("/sortByUsername")
	public String sortbyUsername(@RequestParam String sortbyU, Model model) 
	{
		List<Signup> signups=signupRepository.findAll();
		if(sortbyU.equalsIgnoreCase("asc")) {
			model.addAttribute("sortbyU", "desc");
			model.addAttribute("message","Sorted by Username in descending order!");
			Collections.sort(signups, new SortUser_Descending());
		}else 
		{
			model.addAttribute("sortbyU", "asc");
			model.addAttribute("message","Sorted by Username in ascending order!");
			Collections.sort(signups, new SortUser_Ascending());
		}
		model.addAttribute("signups", signups);		
		return "home";
	}
	
	
	
	// from "searchSignup" in home.jsp
	// METHOD #1: SEARCHING SINGLE FIELD
	/*
	@GetMapping("/searchSignup")
	@PostMapping("/searchSignup")
	public String searchSignupData(HttpServletRequest request) {
		String searchText = request.getParameter("username");
		System.out.println(searchText);
		
		// Fetch data from database
		List<Signup> crosscheck = signupRepository.findByUsername(searchText);
		request.setAttribute("signups", crosscheck);	// adding the list into the request
		return "home";	 //  /home.jsp
	}
	*/
	
	
	// from "searchSignup" in home.jsp
	// METHOD #2: SEARCHING FROM EITHER OF TWO FIELDS USING LIKE; BY NAGENDRA KUMAR
	@GetMapping("/searchSignup")
	public String searchSignupData(@RequestParam String searchText, Model model) {
		// Fetch data from database
		List<Signup> signups = signupRepository.findByUsernameOrEmailLike(searchText, searchText);
		model.addAttribute("signups", signups);	// adding the list into the request
		return "home";	 //  /home.jsp
	}
	
	
	// get or return the sign-up page
	@GetMapping({"/signup", "/"})		
	public String showLoginPage() {
		return "signup"; //  /signup.jsp
	}

	
	// update sing-up information into DB
	@PostMapping("/update_signup")		
	public String updateSignup(@ModelAttribute Signup signup, Model model) {
		Signup dbsignup = signupRepository.findById(signup.getUsername()).get();
		dbsignup.setEmail(signup.getEmail());
		dbsignup.setGender(signup.getGender());
		dbsignup.setPassword(signup.getPassword());
		signupRepository.save(dbsignup);
		model.addAttribute("message", "you are all updated!");
		// Fetch remaining data from database and display back on home-page's screen
		List<Signup> signups = signupRepository.findAll();	
		model.addAttribute("signups", signups); // adding the list into the request
		// model is a hash-map which is used to carry data from controller to JSP
		return "home"; //  /home.jsp
	}	
		
	
	// create sing-up information in DB
	// <form action="sign-up" method="post">
	@PostMapping("/signup")				// create sing-up information in DB
	
	// method #1 (using @ModelAttribute annotation):
	public String postSignup(@ModelAttribute Signup signup, Model model) {
		signup.setTimestamp(new Date());
		Optional<Signup> optional= signupRepository.findById(signup.getUsername());
		if(optional.isPresent())
		{
			model.addAttribute("message", "That username is not allowed");
			return "signup"; //  /signup.jsp
		}
		else
		{
			signupRepository.save(signup);
			model.addAttribute("message", "you are all signed-up!");
		}
		return "login"; //  /login.jsp
		
	// method #2 (using HttpServletRequest):
	/*
	public String postSignup(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		
		//Create a Sign-up entity object for 
		Signup signup= new Signup();
		signup.setEmail(email);
		signup.setGender(gender);
		signup.setPassword(password);
		signup.setUsername(username);
		signup.setTimestamp(new Date());
		
		signupRepository.save(signup);
		req.setAttribute("message", "you are all signed-up!");
		return " login"; //  /login.jsp
		*/
	}
}
