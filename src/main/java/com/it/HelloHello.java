package com.it;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//http://localhost:8080/health

@RestController
public class HelloHello {
	
	@GetMapping("/health")
	public String display() {
		return "<h1 style=\"color:blue\">Application is running<h1>";
	}
	

}
// what is the different between @Controller and @RestController annotations?
