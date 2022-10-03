package com.example.securityb2tech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "You're welcome to home page!";
    }


    @GetMapping("/dashboard")
    public String dashboard(){
        return "You're welcome to dashboard!";
    }
}
