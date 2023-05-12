package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Account;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.AccountService;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    User user;
    Account account;

    @BeforeAll
    public void setup() {
        user = userService.saveUser(new User("Hassan", "Al-Shannag",  "Hi, I'm Hasan al Shannag!"));
        account = accountService.saveAccount(new Account("$12$URyEGDnS0@$12$URyEGDnS0.com", "+$12$URyEGDnS0", "$2a$12$URyEGDnS0up5B8mmANGkqu5i4yYCbE7p4B4lL8csL8cw8p1kTNFp2", user));
    }

    @Test
    @Order(1)
    public void contextLoadTest() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(accountService);
    }

    @Test
    @Order(2)
    public void findAccountByEmail() {
        Account account1 = accountService.findAccountByEmail(account.getEmail());
        Assertions.assertTrue(account1.getId() > 0);
    }

    @Test
    @Order(3)
    public void isEmailExistTest() {
        Assertions.assertTrue(accountService.isEmailExist("$12$URyEGDnS0@$12$URyEGDnS0.com"));
    }

    @Test
    @Order(3)
    public void isPhoneNumberExistTest() {
        Assertions.assertTrue(accountService.isPhoneExist(account.getPhoneNumber()));
    }


    @Test
    @Order(4)
    public void findAccountByPhoneNumber() {
        Account account1 = accountService.findAccountByPhoneNumber(account.getPhoneNumber());
        Assertions.assertTrue(account1.getId() > 0);
    }

    @AfterAll
    public void cleanup() {
        userService.deleteUserById(user.getId());
        accountService.deleteAccountById(account.getId());
    }

}
