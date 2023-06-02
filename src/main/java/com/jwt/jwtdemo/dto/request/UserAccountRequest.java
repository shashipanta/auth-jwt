package com.jwt.jwtdemo.dto.request;

import com.jwt.jwtdemo.model.Role;
import com.jwt.jwtdemo.model.UserAccount;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountRequest {

    @NotNull(message = "Username cannot be blank")
    @Size(max = 100, min = 5, message = "username must be of length 5-100")
    private String userName;

    @Email(message = "Email field not valid")
    @Size(max = 150, message = "Max size : 150 exceeded")
    private String email;

    @NotNull(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "PhoneNumber must not be blank")
    @Size(min = 10, max = 13, message = "Invalid phone Number length")
    private String phoneNumber;

    private List<RoleRequest> roleRequest;

//    public UserAccountRequest(String userName, String email, String password, String phoneNumber){
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//        this.phoneNumber = phoneNumber;
//    }


    public static UserAccount toUserAccount(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(userAccountRequest.getEmail());
        userAccount.setUsername(userAccountRequest.getUserName());
        userAccount.setPassword(userAccountRequest.getPassword());
        userAccount.setPhoneNumber(userAccountRequest.getPhoneNumber());

        if (userAccountRequest.getRoleRequest() != null) {
            List<Role> roleList = userAccountRequest.getRoleRequest()
                    .stream()
                    .map(RoleRequest::toRole)
                    .toList();
            userAccount.setRoles(roleList);
        }
        return userAccount;
    }
}
