package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmailIgnoreCase(String email);

    Account findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT COUNT(*) AS email_count FROM yallah_Ride.Account WHERE email = :email"
            , nativeQuery = true)
    long isEmailExist(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) AS phone_count FROM yallah_Ride.Account WHERE phone_number = :phoneNumber"
            , nativeQuery = true)
    long isPhoneNumberExist(@Param("phoneNumber") String phoneNumber);
}
