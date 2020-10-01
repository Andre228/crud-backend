package com.backend.crud.controllers;

import com.backend.crud.model.User;
import com.backend.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Андрей on 25.09.2020.
 */
@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/user/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody User user) throws Exception {

        User userWithEmail = null;
        User userWithName = null;
        if (user != null && !user.getEmail().equals("")) {
          userWithEmail = userRepository.findByEmail(user.getEmail());
          userWithName = userRepository.findByName(user.getName());
            if (userWithEmail != null) {
              throw new Exception("Пользователь с email адресом " + userWithEmail.getEmail() + " уже существует");
            } else if (userWithName != null) {
              throw new Exception("Пользователь с логином " + userWithName.getName() + " уже существует");
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
            userWithName = userRepository.findByName(user.getName());
            if (userWithName != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
                boolean isValidPassword = encoder.matches(user.getPassword(), userWithName.getPassword());

                if (userWithName.getName() != null && isValidPassword) {
                    return userWithName;
                } else if(userWithName == null) {
                    throw new Exception("Неверный логин");
                } else if(isValidPassword == false) {
                    throw new Exception("Неверный пароль");
                }

            } else {
                throw new Exception("Неверный логин");
            }
        }

        return userWithName;
    }
}
