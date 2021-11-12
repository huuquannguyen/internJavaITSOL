package com.example.login.service;

import com.example.login.dto.PasswordDTO;
import com.example.login.email.EmailServiceImpl;
import com.example.login.entity.AppUser;
import com.example.login.entity.OTP;
import com.example.login.entity.Role;
import com.example.login.repo.OTPRepo;
import com.example.login.repo.RoleRepo;
import com.example.login.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final OTPRepo otpRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;


    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public OTP saveOTP(OTP otp) {
        return otpRepo.save(otp);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public AppUser getUserById(Long id) throws RuntimeException {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("Cannot find this user"));
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public OTP generateOTP(AppUser user) {
        OTP otp = new OTP(user);
        return saveOTP(otp);
    }

    @Override
    public OTP getOTPByUser(AppUser user) {
        return otpRepo.findByUser(user).orElse(null);
    }


    @Override
    public OTP retrieveNewOTP(AppUser user) throws RuntimeException{
        OTP otp = getOTPByUser(user);
        if(otp == null){
            otp = generateOTP(user);
            return otp;
        }else{
            OTP newOTP = new OTP();
            otp.setCode(newOTP.getCode());
            otp.setIssueAt(newOTP.getIssueAt());
            return otp;
        }
    }

    @Override
    public void verifyOTP(OTP otp, String otpCode) throws RuntimeException {
        if(!otp.getCode().equals(otpCode)){
            throw new RuntimeException("Wrong opt code");
        }else if(otp.isExpired()){
            throw new RuntimeException("OTP is expired");
        }
    }

    @Override
    public void activeAccount(AppUser user) {
        user.setActive(true);
    }

    @Override
    public boolean verifyPassword(AppUser user, PasswordDTO passwordDTO) throws RuntimeException {
        if(passwordDTO.getNewPassword() == null ||
                passwordDTO.getVerifyNewPassword() == null ||
                passwordDTO.getCurrentPassword() == null ||
                !passwordDTO.getNewPassword().equals(passwordDTO.getVerifyNewPassword()) ||
                !passwordEncoder.matches(passwordDTO.getCurrentPassword(), user.getPassword())){
            return false;
        }
        return true;
    }

    @Override
    public void changePassword(String password, AppUser user) throws RuntimeException {
        user.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public AppUser loadUserFromContext(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUser(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Cannot find this user");
        }
        List<SimpleGrantedAuthority> authorities =new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(user.getUsername(), user.getPassword(), true, true, true, user.isActive(), authorities);
    }

}
