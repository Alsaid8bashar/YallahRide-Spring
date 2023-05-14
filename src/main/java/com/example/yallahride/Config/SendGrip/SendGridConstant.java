package com.example.yallahride.Config.SendGrip;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@Configuration
public class SendGridConstant {
    public static final String WELCOME_EMAIL_ID = "d-c9c7066f3e614ffa912a5795600e6dbf";
    public static final String FROM_EMAIL = "yallahride@gmail.com";
}
