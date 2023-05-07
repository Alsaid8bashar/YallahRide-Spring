package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Account;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.AccountRepository;
import com.example.yallahride.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    User user;
    Account account;


    @BeforeAll()
    public void setup() {
        user = userRepository.save(new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!"));
        account = accountRepository.save(new Account("shnaqhassan@hotmail.com",
                "+962798084013", "$2a$12$URyEGDnS0up5B8mmANGkqu5i4yYCbE7p4B4lL8csL8cw8p1kTNFp2", user));
    }

    @Test
    public void contextLoadTest() {
        Assertions.assertNotNull(accountRepository);
        Assertions.assertNotNull(userRepository);
    }

    @Test
    @Order(1)
    public void createAccountTest() {
        Account account1 = accountRepository.save(new Account("shnaqhassan@hotmail.com",
                "+962798084013", "$2a$12$URyEGDnS0up5B8mmANGkqu5i4yYCbE7p4B4lL8csL8cw8p1kTNFp2", user));
        System.out.println("account1 = " + account1);
        Assertions.assertTrue(account1.getId() > 0);
    }

    @Test
    @Order(2)
    public void findAccountByIdTest(){
        Assertions.assertNotNull(accountRepository.findById(account.getId()));
    }

    @Test
    @Order(3)
    public void findAllAccountsTest(){
        List<Account> accountList = accountRepository.findAll();
        Assertions.assertTrue(accountList.size() > 0);
    }

    @Test
    @Order(4)
    public void updateAccountTest(){
        account.setEmail("shnaqhassan@gmail.com");
        Account account1 = accountRepository.save(account);
        Assertions.assertTrue(account1.getEmail().equals("shnaqhassan@gmail.com"));
    }

    @Test
    @Order(5)
    public void getNumberOfAccountTest(){
        Long numberOfAccount = accountRepository.count();
        Assertions.assertTrue(numberOfAccount > 0);
    }

    @Test
    @Order(6)
    public void deleteAllAccountsTest(){
        accountRepository.deleteAll();
        Long numberOfAccount = accountRepository.count();
        Assertions.assertTrue(numberOfAccount == 0);
    }

    @Test
    @Order(7)
    public void deleteAccountByIdTest(){
        accountRepository.deleteById(account.getId());
        Long numberOfAccount = accountRepository.count();
        Assertions.assertTrue(numberOfAccount == 0);
    }

}