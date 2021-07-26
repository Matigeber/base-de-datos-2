package ar.edu.unlp.info.bd2.controllers;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.*;
import ar.edu.unlp.info.bd2.repositories.*;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
	
	@Inject
    private SpringDataMLService service;

    @Autowired
    public CategoryController(SpringDataMLService service) {
        this.service = service;
    }
    
    public SpringDataMLService getService () {
    	return this.service;
    }

    @PostMapping("category/_doc")
    public void createCategory(@RequestBody String name) throws MLException{
        this.getService().createCategory(name);
    }


    
    @GetMapping("category/{name}")
    public Optional<Category> findByName(@PathVariable("name")final String aName) throws MLException {
    	Optional<Category> cat = this.getService().getCategoryByName(aName);
    	System.out.println(cat);
    	return cat;
    }
    

}