package com.example.yallahride.ServiceTest;


import com.example.yallahride.Entity.Role;
import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.RideService;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    RideService rideService;
    User user;

    @BeforeAll
    public void setUp() {
        user = new User("Bashar", "ahamd", "basharalsaid17@gmail.com", "male");
        user.setMultipartFile(new MockMultipartFile("userImage.png", "userImage.png".getBytes()));
        userService.saveUser(user);
    }

    @Test
    @Order(1)
    public void contextLoadTest() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(rideService);
    }


    @Test
    @Transactional
    @Order(1)
    public void addTravelPreferences() {
        TravelPreference travelPreference = new TravelPreference("test");
        user = userService.addTravelPreference(user.getId(), travelPreference);

        User tempUser = userService.findUserById(user.getId());
        Assertions.assertFalse(tempUser.getTravelPreferences().isEmpty());
    }

    @Test
    @Order(2)
    public void deleteTravelPreferences() {
        TravelPreference travelPreference = user.getTravelPreferences().iterator().next();
        user = userService.deleteTravelPreference(user.getId(), travelPreference);

        User tempUser = userService.findUserById(user.getId());
        Assertions.assertFalse(tempUser.getRoles().contains(travelPreference));
    }

    @Test
    @Order(3)
    public void addRole() {
        Role role = new Role("Admin");
        user = userService.addRole(user.getId(), role);


        User tempUser = userService.findUserById(user.getId());
        Assertions.assertFalse(tempUser.getRoles().isEmpty());
    }

    @Test
    @Order(4)
    public void deleteRole() {
        Role role = user.getRoles().iterator().next();
        user = userService.deleteRole(user.getId(), role);

        User tempUser = userService.findUserById(user.getId());
        Assertions.assertFalse(tempUser.getRoles().contains(role));
    }


    @Test
    @Order(7)
    public void deactivateUser() {
        userService.deactivateUserById(user.getId());
        User tempUser = userService.findUserById(user.getId());
        Assertions.assertSame(false, tempUser.isActive());
    }


    @Test
    @Order(7)
    public void activateUser() {
        userService.activateUserById(user.getId());
        User tempUser = userService.findUserById(user.getId());
        Assertions.assertSame(true, tempUser.isActive());
    }

    @Test
    @Order(8)
    public void should_not_remove_user_when_child_removed() {
        user = userService.findUserById(user.getId());
        Assertions.assertTrue(user.getId() > 0);
    }

    @AfterAll
    public void cleanup() {
        userService.deleteUserById(user.getId());
    }

}
