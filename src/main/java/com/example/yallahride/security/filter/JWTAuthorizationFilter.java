package com.example.yallahride.security.filter;

import com.example.yallahride.Service.Interface.AccountService;
import com.example.yallahride.security.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {


    AccountService accountService;

    JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(SecurityConstants.AUTHORIZATION);
        final String phoneNumber;
        final String jwtToken;

        if (header == null || !header.startsWith(SecurityConstants.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = header.substring(14);
        phoneNumber = jwtUtils.extractPhoneNumber(jwtToken);

        if (phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = accountService.findAccountByPhoneNumber(phoneNumber);
            final boolean isTokenValid = jwtUtils.isTokenValid(jwtToken, userDetails);

            if (isTokenValid) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
            }
        }

        filterChain.doFilter(request, response);
    }
}
