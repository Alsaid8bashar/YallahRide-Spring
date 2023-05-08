package com.example.yallahride;

import com.example.yallahride.Service.Interface.AccountService;
import com.example.yallahride.Service.implementation.AccountServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class YallahRideApplication {

    public static void main(String[] args) {
        SpringApplication.run(YallahRideApplication.class, args);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
