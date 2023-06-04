package com.jwt.jwtdemo.service.serviceImpl;

import com.jwt.jwtdemo.dto.UserAccountMessage;
import com.jwt.jwtdemo.dto.request.RoleRequest;
import com.jwt.jwtdemo.dto.request.UserAccountRequest;
import com.jwt.jwtdemo.dto.response.RoleResponse;
import com.jwt.jwtdemo.dto.response.UserAccountResponse;
import com.jwt.jwtdemo.model.Role;
import com.jwt.jwtdemo.model.UserAccount;
import com.jwt.jwtdemo.repo.UserAccountRepo;
import com.jwt.jwtdemo.service.RoleService;
import com.jwt.jwtdemo.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepo userAccountRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccountResponse registerUserAccount(UserAccountRequest userAccountRequest) {
        if (userAccountRequest.getRoleRequest() != null) {
            List<RoleRequest> roleRequestToSave = filterRoleRequest(userAccountRequest);
            userAccountRequest.setRoleRequest(roleRequestToSave);
        }


        UserAccount userAccount = UserAccountRequest.toUserAccount(userAccountRequest);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));

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

    @Override
    public UserAccountResponse updateUserAccountById(UserAccountRequest userAccountRequest, Long userId) {
        UserAccount foundAccount = userAccountRepo.findById(userId).orElseThrow();
        foundAccount = prepareToUpdate(userAccountRequest, foundAccount);
        foundAccount = userAccountRepo.save(foundAccount);

        return new UserAccountResponse(foundAccount);
    }

    @Override
    public UserAccountResponse assignRoleToUser(Long userId, Long roleId) {
        List<RoleResponse> userRoles = roleService.getRolesAssociatedToUser(userId);
        List<RoleRequest> roleRequests = userRoles.stream()
                .map(RoleResponse::toRoleRequest)
                .collect(Collectors.toList());
        UserAccount userAccount = userAccountRepo.findById(userId).orElseThrow();

        RoleResponse requestedRole = roleService.getRoleById(roleId);
        roleRequests.add(RoleResponse.toRoleRequest(requestedRole));

        UserAccountRequest userAccountRequest = UserAccountRequest.toUserAccountRequest(userAccount, roleRequests);

        userAccountRequest.setRoleRequest(roleRequests);
        UserAccountResponse response = this.updateUserAccountById(userAccountRequest, userId);

        return response;
    }


    private List<RoleRequest> filterRoleRequest(UserAccountRequest userAccountRequest) {
        List<RoleRequest> roleRequestToSave = new ArrayList<>();

        List<RoleRequest> request = userAccountRequest.getRoleRequest();

        List<RoleRequest> saved = roleService.getRegisteredRoles()
                .stream()
                .map(RoleResponse::toRoleRequest)
                .collect(Collectors.toList());

        if (saved.size() == 0) {
            return new ArrayList<>(request);
        }

        boolean requestFound = false;

        for (int i = 0; i < request.size(); i++) {
            RoleRequest requestedRole = request.get(i);
            for (int j = 0; j < saved.size(); j++) {
                RoleRequest savedRole = saved.get(j);
                if (requestedRole.getIntRoleName().equals(savedRole.getIntRoleName())) {
                    requestedRole.setId(savedRole.getId());
                    roleRequestToSave.add(requestedRole);
                    requestFound = true;
                    break;
                }
            }
            if (!requestFound) {
                roleRequestToSave.add(requestedRole);
            }
        }

        return roleRequestToSave;
    }


    public static UserAccount prepareToUpdate(UserAccountRequest userRequest, UserAccount foundUserAccount) {
        if (userRequest.getUserName() != null) foundUserAccount.setUsername(userRequest.getUserName());
        if (userRequest.getEmail() != null) foundUserAccount.setEmail(userRequest.getEmail());
        if (userRequest.getPhoneNumber() != null) foundUserAccount.setPhoneNumber(userRequest.getPhoneNumber());
//        if (userRequest.getPassword() != null) foundUserAccount.setPassword(pa);
        if (userRequest.getRoleRequest() != null) {
            Set<Role> roleSet =
                    userRequest.getRoleRequest().stream()
                            .map(RoleRequest::toRole)
                            .collect(Collectors.toSet());
            foundUserAccount.setRoles(roleSet);
        }

        return foundUserAccount;
    }

}
