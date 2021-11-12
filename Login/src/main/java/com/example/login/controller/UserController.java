package com.example.login.controller;

import com.example.login.dto.PasswordDTO;
import com.example.login.entity.AppUser;
import com.example.login.entity.OTP;
import com.example.login.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final AppUserService appUserService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(appUserService.getAllUsers());
    }

    @GetMapping("/users/info/get-otp")
    public ResponseEntity<String> getOTP(){
        try {
            AppUser user = appUserService.loadUserFromContext();
            OTP otp = appUserService.retrieveNewOTP(user);
            return ResponseEntity.ok().body(otp.getCode());
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
                return ResponseEntity.ok()
                        .header("new-password", passwordDTO.getNewPassword())
                        .body(otp.getCode());
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
