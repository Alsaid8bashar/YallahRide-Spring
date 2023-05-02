package com.example.yallahride;

import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.UserRepository;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class userServiceTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User("Hassan", "Al-Shannag", "shnaqhassan@hotmail.com", "image1");
        user.setActive(true);
        userRepository.save(user);
        User userTemp = userRepository.findById(user.getId()).get();
        Assertions.assertNotNull(userTemp);
    }

//    @Test
//    public void testFindUserById() {
//        Optional<User> optionalUser = userService.findUserById(65L);
//        User user = optionalUser.get();
////        Assertions.assertEquals(user.getId(), 1L);
//    }
//
//    @Test
//    public void testUpdateUser() {
//        Optional<User> optionalUser = userService.findUserById(1L);
//        User user = optionalUser.get();
//        user.setFirstName("Bashar");
//        user.setLastName("Egypt");
//        userService.saveUser(user);
////        Assertions.assertEquals(user.getFirstName(),tempUser.getFirstName());
//    }
//
//    @Test
//    public void testFindAllUsers() {
//        List<User> userList = userService.findAllUsers();
////        Assertions.assertTrue(userList.size() > 0);
//    }

}