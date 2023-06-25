package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Account;

import java.util.List;

public interface AccountService {

    void deactivateUserById(long userId);

    Account saveAccount(Account account);

    Account findAccountById(Long id);

    List<Account> findAllAccounts();

    Account updateAccount(Account account);

    Account updateAccountPassword(String newPassword, Long id);

    Account findAccountByEmail(String email);

    Account findAccountByPhoneNumber(String phoneNumber);

    boolean isEmailExist(String email);

    boolean isPhoneExist(String phoneNumber);

    boolean confirmPassword(String hashPassword, Long id);

    void deleteAccountById(Long id);

    void deleteAllAccounts();

    long getNumberOfAccount();

    Account findAccountByUserId(long userId);

}
