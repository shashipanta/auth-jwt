package com.jwt.jwtdemo.service.serviceImpl;

import com.jwt.jwtdemo.dto.UserAccountMessage;
import com.jwt.jwtdemo.dto.request.UserAccountRequest;
import com.jwt.jwtdemo.dto.response.UserAccountResponse;
import com.jwt.jwtdemo.model.UserAccount;
import com.jwt.jwtdemo.repo.UserAccountRepo;
import com.jwt.jwtdemo.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepo userAccountRepo;

    @Override
    public UserAccountResponse registerUserAccount(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = UserAccountRequest.toUserAccount(userAccountRequest);
        return new UserAccountResponse(userAccountRepo.save(userAccount));
    }

    @Override
    public List<UserAccountResponse> getRegisteredUserAccounts() {
        List<UserAccount> userAccountList = userAccountRepo.findAll();
        return userAccountList.stream()
                .map(UserAccountResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserAccountMessage deleteUserAccountById(Long userId) {
        userAccountRepo.deleteById(userId);
        UserAccount userAccount = userAccountRepo.findById(userId).orElseThrow();
        return new UserAccountMessage("UserAccount deleted", "Del-code");
    }
}
