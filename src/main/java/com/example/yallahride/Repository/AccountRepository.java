package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Transactional
    @Modifying
    @Query("update Account a set a.isActive = false where a.user.id = ?1")
    void deactivateUserByUserId(long userId);

    Optional<Account> findByUser_Id(Long id);

    Optional<Account> findByPhoneNumberIgnoreCase(String phoneNumber);

    Optional<Account> findByEmailIgnoreCase(String email);

    Optional<Account> findByPhoneNumber(String phoneNumber);


    @Query(value = "SELECT COUNT(*) AS email_count FROM  YallahRide.Account WHERE email = :email"
            , nativeQuery = true)
    long isEmailExist(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) AS phone_count FROM YallahRide.Account WHERE phone_number = :phoneNumber"
            , nativeQuery = true)
    long isPhoneNumberExist(@Param("phoneNumber") String phoneNumber);


}
