package com.example.securityb2tech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        auth.inMemoryAuthentication()
                .withUser("bak").password("$2a$12$Fe/aCGSG89oNhoQeiD8aCePZx8vOtg0PMAM1.GqAAySyro2azAKAe").roles("ADMIN")
                .and()
                .withUser("aza").password("$2a$12$Fe/aCGSG89oNhoQeiD8aCePZx8vOtg0PMAM1.GqAAySyro2azAKAe").roles("USER")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
