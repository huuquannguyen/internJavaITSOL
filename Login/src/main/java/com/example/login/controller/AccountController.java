package com.example.login.controller;

import com.example.login.entity.OTP;
import com.example.login.dto.UserSignupDTO;
import com.example.login.entity.AppUser;
import com.example.login.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AppUserService appUserService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupDTO userSignupDTO){
        AppUser user = new AppUser();
        user.setUsername(userSignupDTO.getUsername());
        user.setPassword(userSignupDTO.getPassword());
        user.setRoles(userSignupDTO.getRoles());
        user.setActive(false);
        appUserService.saveUser(user);
        OTP otp = appUserService.generateOTP(user);
        return ResponseEntity.ok().body(otp.getCode());
    }

    @PostMapping("/active/{id}")
    public ResponseEntity<String> activeAccount(@PathVariable Long id, @RequestParam String otpCode){
        try {
            AppUser user = appUserService.getUserById(id);
            if(user.isActive()){
                return ResponseEntity.ok().body("Your account is already activated");
            }
            OTP otp = appUserService.getOTPByUser(user);
            appUserService.verifyOTP(otp, otpCode);
            appUserService.activeAccount(user);
            return ResponseEntity.ok().body("Active successfull");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
