package com.example.yallahride.Controller;


import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.Role;
import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTests {
    @MockBean
    UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void findUserByIDTest() throws Exception {
        long id = 1L;
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi i am Hasan");
        user.setId(id);

        when(userService.findUserById(id)).thenReturn(user);

        mockMvc.perform(get("/user/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.about").value(user.getAbout()))
                .andExpect(jsonPath("$.imagePath").value(user.getImagePath()))
                .andExpect(jsonPath("$.active").value(user.isActive()))
                .andDo(print());
    }

    @Test
    void createUserTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");

        mockMvc.perform(post("/user/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void deleteUserById() throws Exception {
        long id = 1L;

        doNothing().when(userService).deleteUserById(id);
        mockMvc.perform(delete("/user/delete/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteAllUsersTest() throws Exception {
        doNothing().when(userService).deleteAllUsers();
        mockMvc.perform(delete("/user/delete-all"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void getNumberOfUsersTest() throws Exception {
        when(userService.getNumberOfUser()).thenReturn(3L);
        MvcResult result = mockMvc.perform(get("/user/statistics"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertEquals("3", responseBody);
    }


    @Test
    void shouldReturnListOfTutorials() throws Exception {

        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User("Hassan", "Al-Shannag", "shnaqhassan@hotmail.com", "image1"),
                        new User("Bashar", "Al-Masri", "basaharmasari@gmail.com", "image1"),
                        new User("Luay", "Al-Alawneh", "luay@yahoo.com", "image1")
                ));

        when(userService.findAllUsers()).thenReturn(users);
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andDo(print());
    }

    @Test
    void updateUserByIdTest() throws Exception {
        long id = 1L;

        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(id);

        User updatedUser = new User("Hassan", "Al-Shannag", "email@hotmail.com", "image1");
        updatedUser.setId(id);

        when(userService.findUserById(id)).thenReturn(user);
        when(userService.updateUser(updatedUser)).thenReturn(updatedUser);

        mockMvc.perform(put("/user/update/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void activateUserByIdTest() throws Exception {
        Long id = 1L;
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setActive(true);
        when(userService.activateUserById(id)).thenReturn(user);
        mockMvc.perform(post("/user/activate/{id}", id))
                .andExpect(jsonPath("$.active").value(user.isActive()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deactivateUserByIdTest() throws Exception {
        Long id = 1L;
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setActive(false);
        when(userService.deactivateUserById(id)).thenReturn(user);
        mockMvc.perform(post("/user/deactivate/{id}", id))
                .andExpect(jsonPath("$.active").value(user.isActive()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addTravelPreferenceByUserIdTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        TravelPreference travelPreference = new TravelPreference("TEST");
        travelPreference.setId(1L);

        user.addTravelPreference(travelPreference);
        when(userService.addTravelPreference(user.getId(), travelPreference)).thenReturn(user);
        mockMvc.perform(post("/user/travel_preferences/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(travelPreference)))
                .andExpect(jsonPath("$.travelPreferences[0]").value(user.getTravelPreferences().iterator().next()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTravelPreferencesTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        List<TravelPreference> travelPreferences = new ArrayList<>(
                Arrays.asList(
                        new TravelPreference("TEST1"),
                        new TravelPreference("TEST2"),
                        new TravelPreference("TEST3")
                ));

        for (var element : travelPreferences) {
            user.addTravelPreference(element);
        }

        when(userService.getUserTravelPreferences(user.getId())).thenReturn(user.getTravelPreferences());
        mockMvc.perform(get("/user/{id}/travel-preferences", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }

    @Test
    void deleteTravelPreferenceTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        TravelPreference travelPreference = new TravelPreference("TEST");
        travelPreference.setId(1L);
        user.addTravelPreference(travelPreference);
        user.deleteTravelPreference(travelPreference);

        when(userService.deleteTravelPreference(user.getId(), travelPreference)).thenReturn(user);
        mockMvc.perform(delete("/user/{id}/delete/travel-preferences", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(travelPreference)))
                .andExpect(jsonPath("$.travelPreferences.size()").value(0))
                .andExpect(status().isNoContent())
                .andDo(print());

    }

    @Test
    void addRoleToUserTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        Role role = new Role("ADMIN");
        role.setId(1L);
        user.addRole(role);
        when(userService.addRole(user.getId(), role)).thenReturn(user);

        mockMvc.perform(post("/user/{id}/add/role", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(role)))
                .andExpect(jsonPath("$.roles.size()").value(1))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void getRolesTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        List<Role> roles = new ArrayList<>(
                Arrays.asList(
                        new Role("ADMIN"),
                        new Role("USER"),
                        new Role("MENTOR")
                ));
        for (int x = 0; x < roles.size(); x++) {
            roles.get(x).setId((long) (x + 1));
            user.addRole(roles.get(x));
        }

        when(userService.getUserRoles(user.getId())).thenReturn(roles);

        mockMvc.perform(get("/user/{id}/roles", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }

    @Test
    void deleteRoleTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        List<Role> roles = new ArrayList<>(
                Arrays.asList(
                        new Role("ADMIN"),
                        new Role("USER"),
                        new Role("MENTOR")
                ));
        for (int x = 0; x < roles.size(); x++) {
            roles.get(x).setId((long) (x + 1));
            user.addRole(roles.get(x));
        }
        user.deleteRole(roles.get(2));

        when(userService.deleteRole(user.getId(), roles.get(2))).thenReturn(user);

        mockMvc.perform(delete("/user/{id}/delete/role", user.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roles.get(2))))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.roles", hasSize(2)))
                .andDo(print());


    }

    @Test
    void addRideTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        Ride ride = new Ride("Irbid", "Amman", 3, user);
        ride.setId(1L);
        user.addRide(ride);

        when(userService.addRide(user.getId(), ride)).thenReturn(user);
        mockMvc.perform(post("/user/{id}/add/ride", user.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ride)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rides", hasSize(1)))
                .andDo(print());
    }

    @Test
    void getRidesTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        List<Ride> rides = new ArrayList<>(
                Arrays.asList(
                        new Ride("Irbid", "Amman", 3, user),
                        new Ride("Jarash", "Aqaba", 2, user),
                        new Ride("Zarqa", "Mafraq", 1, user)
                ));
        for (int x = 0; x < rides.size(); x++) {
            rides.get(x).setId((long) (x + 1));
            user.addRide(rides.get(x));
        }

        when(userService.getUserRides(user.getId())).thenReturn(rides);

        mockMvc.perform(get("/user/{id}/rides", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());

    }

    @Test
    void deleteRideTest() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        user.setId(1L);
        List<Ride> rides = new ArrayList<>(
                Arrays.asList(
                        new Ride("Irbid", "Amman", 3, user),
                        new Ride("Jarash", "Aqaba", 2, user),
                        new Ride("Zarqa", "Mafraq", 1, user)
                ));
        for (int x = 0; x < rides.size(); x++) {
            rides.get(x).setId((long) (x + 1));
            user.addRide(rides.get(x));
        }
        user.deleteRide(rides.get(0));

        when(userService.deleteRide(user.getId(), rides.get(0))).thenReturn(user);

        mockMvc.perform(delete("/user/{id}/delete/ride", user.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rides.get(0))))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.rides", hasSize(2)))
                .andDo(print());
    }
}