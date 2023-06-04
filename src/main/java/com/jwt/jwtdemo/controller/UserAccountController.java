package com.jwt.jwtdemo.controller;

import com.jwt.jwtdemo.dto.request.RoleRequest;
import com.jwt.jwtdemo.dto.request.UserAccountRequest;
import com.jwt.jwtdemo.dto.response.RoleResponse;
import com.jwt.jwtdemo.dto.response.UserAccountResponse;
import com.jwt.jwtdemo.model.Role;
import com.jwt.jwtdemo.service.RoleService;
import com.jwt.jwtdemo.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final RoleService roleService;

    @PostMapping(value = "/")
    public ResponseEntity<UserAccountResponse> registerNewUser(@Valid @RequestBody UserAccountRequest userAccountRequest) {
        UserAccountResponse response = userAccountService.registerUserAccount(userAccountRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<UserAccountResponse>> getAllRegisteredUsers() {
        List<UserAccountResponse> responseList = userAccountService.getRegisteredUserAccounts();
        return ResponseEntity.ok(responseList);
    }

    @PutMapping(value = "/{user-id}")
    public ResponseEntity<UserAccountResponse> updateUserAccount(@RequestBody UserAccountRequest userAccountRequest,
                                                                 @PathVariable("user-id") Long userId
    ) {
        UserAccountResponse response = userAccountService.updateUserAccountById(userAccountRequest, userId);
        return ResponseEntity.ok(response);

    }

    @PutMapping(value = "/{user-id}/assign-role/{role-id}")
    public ResponseEntity<UserAccountResponse> assignRoleToUserAccount(
            @RequestBody UserAccountRequest userAccountRequest,
            @PathVariable("user-id") Long userId,
            @PathVariable("role-id") Long roleId) {

        return ResponseEntity.ok(userAccountService.assignRoleToUser(userId, roleId));
    }
}
