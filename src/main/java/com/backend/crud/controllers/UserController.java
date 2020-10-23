package com.backend.crud.controllers;

import com.backend.crud.model.User;
import com.backend.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Андрей on 23.09.2020.
 */

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;


}
