//package com.example.securityb2tech.security;
//
//import com.example.securityb2tech.service.CustomUserDetailsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationManager implements AuthenticationManager {
//
//    private final PasswordEncoder passwordEncoder;
//    private final CustomUserDetailsService userDetailsService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        final UserDetails userDetail = userDetailsService.loadUserByUsername(authentication.getName());
//        if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetail.getPassword())) {
//            throw new BadCredentialsException("Wrong password");
//        }
//        return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
//    }
//}
