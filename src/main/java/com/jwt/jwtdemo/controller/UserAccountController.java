package com.jwt.jwtdemo.controller;

import com.jwt.jwtdemo.dto.request.UserAccountRequest;
import com.jwt.jwtdemo.dto.response.UserAccountResponse;
import com.jwt.jwtdemo.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping(value = "/")
    public ResponseEntity<UserAccountResponse> registerNewUser(@RequestBody UserAccountRequest userAccountRequest){
        UserAccountResponse response = userAccountService.registerUserAccount(userAccountRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<UserAccountResponse>> getAllRegisteredUsers(){
        List<UserAccountResponse> responseList = userAccountService.getRegisteredUserAccounts();
        return ResponseEntity.ok(responseList);
    }
}
