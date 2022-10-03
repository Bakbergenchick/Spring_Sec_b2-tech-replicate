package com.example.securityb2tech.security;

import com.example.securityb2tech.model.Authority;
import com.example.securityb2tech.model.Users;
import com.example.securityb2tech.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User wit email:" + email + " not found"));

        if (encoder.matches(password, user.getPassword())){
            List<GrantedAuthority> authoritySet = new ArrayList<>();

            for (Authority auth:
                 user.getAuthoritySet()) {
                authoritySet.add(new SimpleGrantedAuthority(auth.getAuthority()));
            }

            return new UsernamePasswordAuthenticationToken(email, password, authoritySet);
        } else{
            throw new BadCredentialsException("Invalid credentials");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
