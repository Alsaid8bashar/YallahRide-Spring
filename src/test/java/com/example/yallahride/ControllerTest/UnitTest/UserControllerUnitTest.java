package com.example.yallahride.ControllerTest.UnitTest;

import com.example.yallahride.Controller.UserController;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest
{
    @InjectMocks
    UserController userController;
    @Mock
    UserService userService;

    @Test
    public void createUserTest()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user = new User("Hasan", "Al-Shannag", "hassanshannag@gmail.com", "imagePath1");

        ResponseEntity<HttpStatus> responseEntity = userController.createUser(user);
        Assertions.assertTrue((responseEntity.getStatusCode()).is2xxSuccessful());
    }
}
