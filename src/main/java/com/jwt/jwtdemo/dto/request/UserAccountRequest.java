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
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountRequest {

    private Long id;

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

    public UserAccountRequest(String userName, String email, String password, String phoneNumber, List<RoleRequest> roleRequest){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roleRequest = roleRequest;
    }


    public static UserAccount toUserAccount(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(userAccountRequest.getEmail());
        userAccount.setUsername(userAccountRequest.getUserName());
        userAccount.setPassword(userAccountRequest.getPassword());
        userAccount.setPhoneNumber(userAccountRequest.getPhoneNumber());

        if (userAccountRequest.getRoleRequest() != null) {
            Set<Role> roleList = userAccountRequest.getRoleRequest()
                    .stream()
                    .map(RoleRequest::toRole)
                    .collect(Collectors.toSet());
            userAccount.setRoles(roleList);
        }
        return userAccount;
    }

    public static UserAccountRequest toUserAccountRequest(UserAccount userAccount, List<RoleRequest> roleRequestList) {
        UserAccountRequest userAccountRequest = new UserAccountRequest();
        userAccountRequest.setId(userAccount.getId());
        userAccountRequest.setUserName(userAccount.getUsername());
        userAccountRequest.setEmail(userAccount.getEmail());
        userAccountRequest.setPassword(userAccount.getPassword());
        userAccountRequest.setPhoneNumber(userAccount.getPhoneNumber());

        return userAccountRequest;

    }
}
