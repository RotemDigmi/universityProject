package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.University;
import app.core.repositories.UniversityRepository;
import app.core.services.UniversityService;
import app.core.session.Session;
import app.core.session.SessionContaxt;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private SessionContaxt sessionContaxt;
	@Autowired
	private UniversityRepository universityRepo;
	@Autowired
	private UniversityService universityService;
	
	@PostMapping("/login")
	private String login(@RequestParam String userEmail,@RequestParam String userPassword) throws Exception {
		University currUniversity = universityRepo.findByEmailAndPassword(userEmail, userPassword);
		if(currUniversity != null) {
			universityService.login(userEmail, userPassword);
			Session s = sessionContaxt.createSession();
			s.setParameters(userEmail, userPassword);
			return s.token;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "login failed capara!");
		}
	}
	
}
