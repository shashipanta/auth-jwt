package com.jwt.jwtdemo.service;

import com.jwt.jwtdemo.dto.UserAccountMessage;
import com.jwt.jwtdemo.dto.request.UserAccountRequest;
import com.jwt.jwtdemo.dto.response.UserAccountResponse;

import java.util.List;

public interface UserAccountService {

    UserAccountResponse registerUserAccount(UserAccountRequest userAccountRequest);

    List<UserAccountResponse> getRegisteredUserAccounts();

    UserAccountMessage deleteUserAccountById(Long userId);
}
