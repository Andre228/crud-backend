package com.backend.crud.controllers;

import com.backend.crud.model.Category;
import com.backend.crud.model.Post;
import com.backend.crud.model.User;
import com.backend.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Андрей on 23.09.2020.
 */

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        List<User> users = (List<User>)userRepository.findAll();
        return users;
    }

    @GetMapping(value = "/user/get/{username}")
    public User getAllCategories(@PathVariable String username) {
        User userFromDB = userRepository.findByUsername(username);
        return userFromDB;
    }


}
