package com.backend.crud.controllers;

import com.backend.crud.model.Category;
import com.backend.crud.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
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

    @PostMapping("/categories/create")
    public Category createCategory(@RequestBody Category category) throws ParseException {
        Category creatingCategory = categoryRepository.save(category);
        return creatingCategory;
    }

    @PutMapping ("/categories/update")
    public Category updateCategory(@RequestBody Category category) throws Exception {
        Category categoryFromDb = categoryRepository.findById(category.getId()).get();
        if (categoryFromDb != null) {
            categoryFromDb = category;
            categoryRepository.save(categoryFromDb);
        } else  {
            throw new Exception("This category is not existed");
        }

        return categoryFromDb;
    }

    @DeleteMapping(value = "/categories/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category deletingCategory = categoryRepository.findById(id).get();
        try {
            categoryRepository.delete(deletingCategory);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("It is impossible to delete this category because it contains some posts");
        }
    }
}
