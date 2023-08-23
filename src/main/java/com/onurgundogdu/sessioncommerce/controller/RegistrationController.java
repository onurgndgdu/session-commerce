package com.onurgundogdu.sessioncommerce.controller;

import com.onurgundogdu.sessioncommerce.model.User;
import com.onurgundogdu.sessioncommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User registered successfully";

    }




}
