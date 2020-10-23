package com.backend.crud.services;

import com.backend.crud.model.User;
import com.backend.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * Created by Андрей on 19.10.2020.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //private final UserDataEncoder userDataEncoder;


//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, UserDataEncoder userDataEncoder) {
//        this.userRepository = userRepository;
//        this.userDataEncoder = userDataEncoder;
//    }


//    @Override
//    public User getCurrentUser() {
//        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Username " + username + " not found in database.");
        return user;
    }



}
