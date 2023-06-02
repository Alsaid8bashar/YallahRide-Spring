package com.example.yallahride.security.filter;

import com.example.yallahride.Entity.Account;
import com.example.yallahride.Service.Interface.AccountService;
import com.example.yallahride.security.SecurityConstants;
import com.example.yallahride.security.manager.CustomAuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private CustomAuthenticationManager authenticationManager;
    private AccountService accountService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            Account account = new ObjectMapper().readValue(request.getInputStream(),Account.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException();
        } 
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Account account = accountService.findAccountByPhoneNumber(authResult.getName());
        JwtUtils jwtUtils = new JwtUtils();
        String token = jwtUtils.generateToken(account);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600"); // Optional, specify max age in seconds
        response.addHeader("access-control-expose-headers", "Authorization");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
    }



}
