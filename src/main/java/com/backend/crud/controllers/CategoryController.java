package com.backend.crud.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Андрей on 22.09.2020.
 */
@RestController
@CrossOrigin
public class CategoryController implements BaseController {


    @GetMapping("/categories")
    @ResponseBody
    @Override
    public ResponseEntity<?> index(Long id) {
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void store() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {

    }
}
