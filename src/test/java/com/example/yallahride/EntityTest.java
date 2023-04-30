package com.example.yallahride;


import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityTest {
    @Autowired
    UserService userService;


    @Test
    public void testSaveUser() {
        User user = new User("Bashar", "ahamd", "basharalsaid16@gmail.com", "newImage");
        userService.save(user);
    }

    @Test
    public void testFindUserById() {
        System.out.println("userService.findById(7L) = " + userService.findById(15L));
    }

    @Test
    public void testFindAllUsers() {
        System.out.println("userService.findAll() = " + userService.findAll());
    }

    @Test
    public void testDeleteUserById() {
        userService.deleteById(2L);
    }

    @Test
    public void testDeleteAllUsers() {
        userService.deleteAll();
    }
}
