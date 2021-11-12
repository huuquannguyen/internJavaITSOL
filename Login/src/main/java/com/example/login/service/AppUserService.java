package com.example.login.service;

import com.example.login.dto.PasswordDTO;
import com.example.login.entity.AppUser;
import com.example.login.entity.OTP;
import com.example.login.entity.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService extends UserDetailsService {

    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    OTP saveOTP(OTP otp);

    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    AppUser getUserById(Long id);

    List<AppUser> getAllUsers();

    OTP generateOTP(AppUser user);

    OTP getOTPByUser(AppUser user);

    void verifyOTP(OTP otp, String otpCode);

    OTP retrieveNewOTP(AppUser user);

    void activeAccount(AppUser user);

    boolean verifyPassword(AppUser user, PasswordDTO passwordDTO);

    void changePassword(String password, AppUser user);

    AppUser loadUserFromContext();

}
