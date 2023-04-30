package com.example.yallahride;


import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEntityTest {
    @Autowired
    UserService userService;

    @Test
    @Order(1)
    public void testSaveUser() {
        User user = new User("Bashar", "ahamd", "basharalsaid17@gmail.com", "newImage");
        userService.saveUser(user);
    }

    @Test
    @Order(2)
    public void testFindUserById() {
        Assertions.assertThat(userService.findUserById(7L)).isNotNull();
    }

    @Test
    @Order(3)
    public void testFindAllUsers() {
        Assertions.assertThat(userService.findAllUsers()).isNotNull();
    }

    @Test
    @Order(4)
    public void testDeleteUserById() {
        userService.deleteUserById(7L);
        Assertions.assertThat(userService.findUserById(7L)).isEmpty();
    }

    @Test
    @Order(5)
    public void testDeleteAllUsers() {
        userService.deleteAllUsers();
        Assertions.assertThat(userService.findAllUsers()).isEmpty();
    }
}
