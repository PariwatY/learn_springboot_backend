package com.example.leraning.config.token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.leraning.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

public class TokenFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest serveletRequest, ServletResponse serveletResponse, FilterChain filterchain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) serveletRequest;

        String authorization = request.getHeader("Authorization");

        if (ObjectUtils.isEmpty(authorization)) {
            filterchain.doFilter(serveletRequest, serveletResponse);
            return;
        }

        if (!authorization.startsWith("Bearer ")) {
            filterchain.doFilter(serveletRequest, serveletResponse);
            return;
        }

        String token = authorization.substring(7);

        DecodedJWT decode = tokenService.verify(token);
        if (decode == null) {
            filterchain.doFilter(serveletRequest, serveletResponse);
            return;

        }

        // user id
        String principal = decode.getClaim("principal").asString();
        String role = decode.getClaim("role").asString();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal,
                "(protected)", authorities);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        filterchain.doFilter(serveletRequest, serveletResponse);
    }

}
