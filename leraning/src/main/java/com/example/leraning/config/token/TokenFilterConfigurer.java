package com.example.leraning.config.token;

import com.example.leraning.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    
    private final TokenService service;

    public TokenFilterConfigurer(TokenService service) {
        this.service = service;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        TokenFilter filter = new TokenFilter(service);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

}
