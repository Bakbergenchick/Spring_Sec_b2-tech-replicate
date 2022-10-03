package com.example.securityb2tech.controller;

import com.example.securityb2tech.model.Authority;
import com.example.securityb2tech.model.Users;
import com.example.securityb2tech.repo.AuthorityRepo;
import com.example.securityb2tech.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Transactional
public class HomeController {

    private final UserRepository userRepository;
    private final AuthorityRepo authorityRepo;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String home(){
        return "You're welcome to home page!";
    }

    @PostMapping("/register")
    public Users resgister(
            @RequestBody Users user
    ){
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("Sefsef");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Users saved_user = userRepository.save(user);

        authorityRepo.save(new Authority(1L, "ROLE_USER", saved_user));

        return saved_user;
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(
            @RequestBody Users user
    ){
        Authentication authentication;
        try{
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (Exception e){
            throw new BadCredentialsException("Invalid credentials");
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "You're welcome to dashboard!";
    }
}
