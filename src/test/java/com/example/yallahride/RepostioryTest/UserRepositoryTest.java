package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    User user;
    @BeforeAll
    public void setup(){
        user = userRepository.save(new User("Ahmad", "Mouhsn", "ahmadmouhsn@gmail.com", "imagePath1"));
    }
    @Test
    @Order(1)
    @Rollback(value = false)
    public void testCreateUser() {
        User user = userRepository.save(new User("Hassan", "Al-Shannag", "shnaqhassan@hotmail.com", "image1"));
        Assertions.assertTrue(user.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindUserById(){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        User tempUser = optionalUser.get();
        Assertions.assertEquals(tempUser.getId(), user.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdateUser() {
        Optional<User>optionalUser = userRepository.findById(user.getId());
        user.setFirstName("Bashar");
        User userUpdated = userRepository.save(user);
        Assertions.assertEquals(user.getFirstName(),userUpdated.getFirstName());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllUsers() {
        List<User> userList = userRepository.findAll();
        Assertions.assertTrue(userList.size() > 0);
    }
    @Test
    @Order(5)
    public void testDeleteUserByID() {
        userRepository.deleteById(user.getId());
        Optional<User> optionalUser = userRepository.findById(user.getId());
        Assertions.assertFalse(optionalUser.isPresent());
    }

    @Test
    @Order(6)
    public void testDeleteAllUsers(){
        userRepository.deleteAll();
        Assertions.assertEquals(0, userRepository.count());
    }

}