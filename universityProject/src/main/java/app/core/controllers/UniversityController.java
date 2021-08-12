package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.session.Session;
import app.core.session.SessionContaxt;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UniversityController{

	@Autowired
	private SessionContaxt sessionContaxt;
	
	@GetMapping("/greet")
	public String greet(@RequestHeader String token) {
		Session s = sessionContaxt.getSession(token);
		if(s != null) {
			return "Hello " + s.getParameters("userName");
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no session.");
		}
	}
	
}
