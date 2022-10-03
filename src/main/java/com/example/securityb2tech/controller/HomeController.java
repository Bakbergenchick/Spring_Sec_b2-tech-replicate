package com.example.securityb2tech.controller;

import com.example.securityb2tech.model.Users;
import com.example.securityb2tech.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @GetMapping("/home")
    public String home(){
        return "You're welcome to home page!";
    }

    @GetMapping("/register")
    public Users resgister(
            @RequestBody Users user
    ){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }


    @GetMapping("/dashboard")
    public String dashboard(){
        return "You're welcome to dashboard!";
    }
}
