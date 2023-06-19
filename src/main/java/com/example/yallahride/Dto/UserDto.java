package com.example.yallahride.Dto;

import com.example.yallahride.Entity.Account;
import com.example.yallahride.Entity.Rate;
import com.example.yallahride.Entity.User;
import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Account account;
    private User user;

    private Collection<Rate> rates;

    private double userRate;


}
