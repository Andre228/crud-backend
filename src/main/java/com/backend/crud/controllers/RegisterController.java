package com.backend.crud.controllers;

import com.backend.crud.model.User;
import com.backend.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Андрей on 25.09.2020.
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/api/v1/basicauth")
    public String helloWorldBean() {
        System.out.println("basicauth");
        return new String("You are authenticated");
    }


    @PostMapping("/user/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody User user) throws Exception {

        User userWithEmail = null;
        User userWithName = null;
        if (user != null && !user.getEmail().equals("")) {
          userWithEmail = userRepository.findByEmail(user.getEmail());
          userWithName = userRepository.findByUsername(user.getName());
            if (userWithEmail != null) {
              throw new Exception("User with email " + userWithEmail.getEmail() + " is already exist");
            } else if (userWithName != null) {
              throw new Exception("User with login " + userWithName.getName() + " is already exist");
            }
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16); // Strength set as 16
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public User login(@RequestBody User user) throws Exception {
        User userWithName = null;
        if (user != null && !user.getName().equals("")) {
            userWithName = userRepository.findByUsername(user.getName());
            if (userWithName != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
                boolean isValidPassword = encoder.matches(user.getPassword(), userWithName.getPassword());

                if (userWithName.getName() != null && isValidPassword) {
                    return userWithName;
                } else if(userWithName == null) {
                    throw new UsernameNotFoundException("Username " + user.getName() + " not found in database.");
                } else if(isValidPassword == false) {
                    throw new UsernameNotFoundException("Bad password");
                }
            } else {
                throw new UsernameNotFoundException("Username " + user.getName() + " not found in database.");
            }
        }

        return userWithName;
    }
}
