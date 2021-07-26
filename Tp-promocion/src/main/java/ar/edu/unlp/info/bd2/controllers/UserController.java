package ar.edu.unlp.info.bd2.controllers;

import ar.edu.unlp.info.bd2.model.Category;
import ar.edu.unlp.info.bd2.model.User;
import ar.edu.unlp.info.bd2.services.*;
import ar.edu.unlp.info.bd2.repositories.MLException;
import ar.edu.unlp.info.bd2.repositories.UserRepository;

import java.util.Date;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	
	@Inject 
	private SpringDataMLService service;
	
    @Autowired
    public UserController(SpringDataMLService service) {
        this.service = service;
    }
    
    public SpringDataMLService getService () {
    	return this.service;
    }
    
    @PostMapping("user/_doc")
    public void createUser (@RequestBody String email, String password, String fullname, Date dayOfBirth ) throws MLException {
    	this.getService().createUser(email, fullname, password, dayOfBirth);
    }
    
    @GetMapping("user/{email}")
    public Optional<User> findByEmail(@PathVariable String email) throws MLException {
    	return this.getService().getUserByEmail(email);
    }

}
