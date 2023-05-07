package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmailIgnoreCase(String email);

    Account findByPhoneNumber(String phoneNumber);


}
