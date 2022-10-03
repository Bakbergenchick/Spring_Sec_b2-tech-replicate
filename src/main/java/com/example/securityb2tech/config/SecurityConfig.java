package com.example.securityb2tech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/home").authenticated()
                .antMatchers("/dashboard").permitAll()
                .anyRequest().denyAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user1 = User.withUsername("bak")
                .password("$2a$12$Fe/aCGSG89oNhoQeiD8aCePZx8vOtg0PMAM1.GqAAySyro2azAKAe")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withUsername("aza")
                .password("$2a$12$Fe/aCGSG89oNhoQeiD8aCePZx8vOtg0PMAM1.GqAAySyro2azAKAe")
                .roles("USER")
                .build();

        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(user2);
        auth.userDetailsService(userDetailsManager);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
