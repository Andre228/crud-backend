package com.backend.crud.controllers;

import com.backend.crud.model.User;
import com.backend.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Андрей on 25.09.2020.
 */
@RestController
@CrossOrigin
public class AuthContoller {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user/auth")
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(required = false) Long id) {
        User user = userRepository.findById(id).get();
        System.out.println();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
