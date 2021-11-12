package com.example.login.repo;

import com.example.login.entity.AppUser;
import com.example.login.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OTPRepo extends JpaRepository<OTP, Long> {

    @Query("FROM OTP o WHERE o.user = :user")
    Optional<OTP> findByUser(@Param("user") AppUser user);

//    @Modifying
//    @Query("UPDATE OTP o SET o.code = :code, o.issueAt = :issueAt WHERE o.user = :user")
//    void updateOTP(@Param("code") String otpCode,
//                   @Param("issueAt") Long issueAt,
//                   @Param("user") AppUser user);
}
