package com.example.yallahride.Controller;

import com.example.yallahride.Dto.UserDto;
import com.example.yallahride.Service.Interface.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-dto")
public class UserDtoController {

    @Autowired
    UserDtoService userDtoService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserDtoById(@PathVariable Long userId) {
        return new ResponseEntity<>(userDtoService.getUserDto(userId), HttpStatus.OK);
    }
}
