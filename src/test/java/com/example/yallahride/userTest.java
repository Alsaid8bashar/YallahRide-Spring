//package com.example.yallahride;
//
//import com.example.yallahride.Entity.User;
//import com.example.yallahride.Service.Interface.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@SpringBootTest
//public class userServiceTest {
//    @Autowired
//    UserService userService;
//
//    @Test
//    public void testCreateUser() {
//        User user = new User("Hassan", "Al-Shannag", "shnaqhassan@hotmail.com", "image1");
//        userService.saveUser(user);
////        Assertions.assertNotNull(tempUser);
//    }
//
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
//
//}