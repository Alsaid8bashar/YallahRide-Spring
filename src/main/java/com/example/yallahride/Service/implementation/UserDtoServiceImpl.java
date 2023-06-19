package com.example.yallahride.Service.implementation;

import com.example.yallahride.Dto.UserDto;
import com.example.yallahride.Service.Interface.AccountService;
import com.example.yallahride.Service.Interface.RateService;
import com.example.yallahride.Service.Interface.UserDtoService;
import com.example.yallahride.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDtoServiceImpl implements UserDtoService {
    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    RateService rateService;

    @Override
    public UserDto getUserDto(long userId) {
        UserDto dto = UserDto.builder()
                .user(userService.findUserById(userId))
                .account(accountService.findAccountByUserId(userId))
                .rates(rateService.findUserRates(userId))
                .userRate(rateService.getUserRateByUserId(userId))
                .build();
        return dto;
    }
}
