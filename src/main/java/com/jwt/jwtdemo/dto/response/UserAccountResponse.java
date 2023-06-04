package com.jwt.jwtdemo.dto.response;


import com.jwt.jwtdemo.model.Role;
import com.jwt.jwtdemo.model.UserAccount;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserAccountResponse {

    private String userName;
    private String email;
    private String phoneNumber;
    private Set<Role> roles;

    public UserAccountResponse(UserAccount userAccount){
        this.userName = userAccount.getUsername();
        this.email = userAccount.getEmail();
        this.phoneNumber = userAccount.getPhoneNumber();
        this.roles = userAccount.getRoles();
    }
}
