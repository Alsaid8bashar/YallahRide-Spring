package com.example.yallahride;

import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YallahRideApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        userService.save(new User("Bashar","AlSaid","no image"));
    }

}
