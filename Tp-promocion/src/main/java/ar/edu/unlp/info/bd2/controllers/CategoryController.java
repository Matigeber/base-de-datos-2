package ar.edu.unlp.info.bd2.controllers;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.*;
import ar.edu.unlp.info.bd2.repositories.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private SpringDataMLService service;

    @Autowired
    public CategoryController(SpringDataMLService service) {
        this.service = service;
    }

    @PostMapping
    public void save(@RequestBody String category) throws MLException{
        service.createCategory(category);
    }


    @GetMapping("/{name}")
    public Optional<Category> findByName(@PathVariable final String name) throws MLException {
        return service.getCategoryByName(name);
    }

}