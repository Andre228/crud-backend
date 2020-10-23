package com.backend.crud.controllers;

import com.backend.crud.model.Category;
import com.backend.crud.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Андрей on 21.10.2020.
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories/all")
    public List<Category> getAllCategories() {
        List<Category> categories = (List<Category>)categoryRepository.findAll();
        return categories;
    }
}
