package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Account;
import com.example.yallahride.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;


    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.findAccountById(id), OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.saveAccount(account), CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.updateAccount(account), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find-by-email")
    public ResponseEntity<Account> findAccountByEmail(@RequestBody String email) {
        return new ResponseEntity<>(accountService.findAccountByEmail(email), OK);
    }

    @GetMapping("/find-by-phone")
    public ResponseEntity<Account> findAccountByPhoneNumber(@RequestBody String phone) {
        return new ResponseEntity<>(accountService.findAccountByPhoneNumber(phone), OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Account>> findAll() {
        return new ResponseEntity<>(accountService.findAllAccounts(), OK);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllAccount() {
        accountService.deleteAllAccounts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long> getNumberOfAccounts() {
        return new ResponseEntity<>(accountService.getNumberOfAccount(), HttpStatus.OK);
    }

}