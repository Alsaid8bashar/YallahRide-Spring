package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Account;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);

    Account findAccountById(Long id);

    List<Account> findAllAccounts();

    Account updateAccount(Account account);

    boolean isEmailExist(String email);

    boolean isPhoneExist(String phoneNumber);

    void deleteAllAccounts();

    void deleteAccountById(Long id);

    long getNumberOfAccount();

    Account findAccountByEmail(String email);

    Account findAccountByPhoneNumber(String phoneNumber);
}
