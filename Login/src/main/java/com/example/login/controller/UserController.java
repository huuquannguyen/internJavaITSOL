package com.example.login.controller;

import com.example.login.dto.PasswordDTO;
import com.example.login.email.EmailServiceImpl;
import com.example.login.entity.AppUser;
import com.example.login.entity.OTP;
import com.example.login.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final AppUserService appUserService;
    private final EmailServiceImpl emailService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(appUserService.getAllUsers());
    }

    @GetMapping("/users/info/get-otp")
    public ResponseEntity<String> getOTP(){
        try {
            AppUser user = appUserService.loadUserFromContext();
            OTP otp = appUserService.retrieveNewOTP(user);
            emailService.sendSimpleMessage(user.getUsername(), "OTP", "OTP: " + otp.getCode());
            return ResponseEntity.ok().body("check email for OTP");
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/users/info/change-password")
    public ResponseEntity<String> changePasswordRequest(@RequestBody PasswordDTO passwordDTO){
        AppUser user = appUserService.loadUserFromContext();
        try {
            if(appUserService.verifyPassword(user, passwordDTO)){
                OTP otp = appUserService.retrieveNewOTP(user);
                emailService.sendSimpleMessage(user.getUsername(),
                        "Change password",
                        "OTP: " + otp.getCode() + "\nNew password: " + passwordDTO.getNewPassword());
                return ResponseEntity.ok().body("Check mail for OTP to commit changing");
            }else{
                return ResponseEntity.badRequest().body("Failed changing password");
            }
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/users/info/change-password")
    public ResponseEntity<String> changePassword(@RequestParam String otpCode, @RequestParam String password){
        try {
            AppUser user = appUserService.loadUserFromContext();
            OTP otp = appUserService.getOTPByUser(user);
            appUserService.verifyOTP(otp, otpCode);
            appUserService.changePassword(password, user);
            return ResponseEntity.ok().body("Password change successfull");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
