package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Account;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.AccountRepository;
import com.example.yallahride.Service.Interface.AccountService;
import com.example.yallahride.Service.Interface.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleService roleService;

    static Account unwrapAccount(Optional<Account> account, Long id) {
        if (account.isPresent()) return account.get();
        else throw new EntityNotFoundException(id, Account.class);
    }

    @Override
    public Account saveAccount(Account account) {
        account.setPasswordHash(bCryptPasswordEncoder.encode(account.getPasswordHash()));
        account.getUser().addRole(roleService.findRoleById(1L));
        return accountRepository.save(account);
    }

    @Override
    public Account findAccountById(Long id) {
        return unwrapAccount(accountRepository.findById(id), id);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccountPassword(String newPassword, Long id) {
        Account account = findAccountById(id);
        account.setPasswordHash(bCryptPasswordEncoder.encode(newPassword));
        return updateAccount(account);
    }

    @Override
    public boolean isEmailExist(String email) {
        if (accountRepository.isEmailExist(email) > 0)
            return true;
        return false;
    }

    @Override
    public boolean isPhoneExist(String phoneNumber) {
        if (accountRepository.isPhoneNumberExist(phoneNumber) > 0)
            return true;
        return false;
    }

    @Override
    public boolean confirmPassword(String hashPassword, Long id) {
        Account account = findAccountById(id);
        if (bCryptPasswordEncoder.matches(hashPassword, account.getPasswordHash())) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public long getNumberOfAccount() {
        return accountRepository.count();
    }

    @Override
    public Account findAccountByEmail(String email) {
        return unwrapAccount(accountRepository.findByEmailIgnoreCase(email), 404L);
    }

    @Override
    public Account findAccountByPhoneNumber(String phoneNumber) {
        return unwrapAccount(accountRepository.findByPhoneNumber(phoneNumber), 404L);
    }

    @Override
    public Account findAccountByUserId(long userId) {
        return unwrapAccount(accountRepository.findByUser_Id(userId), 404L);
    }

    @Override
    public void deactivateUserById(long userId) {
        accountRepository.deactivateUserByUserId(userId);
    }

}
