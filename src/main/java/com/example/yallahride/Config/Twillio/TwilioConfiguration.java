package com.example.yallahride.Config.Twillio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class TwilioConfiguration {

    @Value( "${account_sid}" )
    private String accountSid;

    @Value( "${auth_token}" )
    private String authToken;
    @Value( "${service_sid}" )

    private String serviceSid;

    public TwilioConfiguration() {

    }

    public String getAccountSid() {
        return accountSid;
    }


    public String getAuthToken() {
        return authToken;
    }

}
