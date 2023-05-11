package com.example.yallahride.security;


import com.example.yallahride.Service.Interface.AccountService;
import com.example.yallahride.security.filter.AuthenticationFilter;
import com.example.yallahride.security.filter.ExceptionHandlerFilter;
import com.example.yallahride.security.filter.JWTAuthorizationFilter;
import com.example.yallahride.security.filter.JwtUtils;
import com.example.yallahride.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final AccountService accountService;
    private final JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager, accountService);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
//                .requestMatchers("/account/**").permitAll()
//                .requestMatchers("/storage/**").permitAll()
//                .requestMatchers("/role/").hasAuthority("ADMIN")
//                .requestMatchers("/ride/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
//                .addFilter(authenticationFilter)
//                .addFilterAfter(new JWTAuthorizationFilter(accountService, jwtUtils), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}