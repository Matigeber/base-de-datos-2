package ar.edu.unlp.info.bd2.controllers;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.*;
import ar.edu.unlp.info.bd2.repositories.*;

import java.util.Optional;

import org.elasticsearch.common.inject.Inject;
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

    @PostMapping("/api/category/{name}")
    public void createCategory(@PathVariable("name") String name) throws MLException{
        this.getService().createCategory(name);
    }


    /*
    @GetMapping("/{name}")
    public Optional<Category> findByName(@PathVariable final String name) throws MLException {
        return service.getCategoryByName(name);
    }
    */

}