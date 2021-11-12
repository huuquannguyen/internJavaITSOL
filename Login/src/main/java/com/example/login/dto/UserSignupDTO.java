package com.example.login.dto;

import com.example.login.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDTO {
    private String username;
    private String password;
    private Set<Role> roles;
}
