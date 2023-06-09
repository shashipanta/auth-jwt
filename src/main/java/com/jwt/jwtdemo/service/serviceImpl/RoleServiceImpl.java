package com.jwt.jwtdemo.service.serviceImpl;

import com.jwt.jwtdemo.dto.request.RoleRequest;
import com.jwt.jwtdemo.dto.response.RoleResponse;
import com.jwt.jwtdemo.model.Role;
import com.jwt.jwtdemo.repo.RoleRepo;
import com.jwt.jwtdemo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = RoleRequest.toRole(roleRequest);
        return new RoleResponse(roleRepo.save(role));
    }

    @Override
    public List<RoleResponse> getRegisteredRoles() {
        List<Role> roles = roleRepo.findAll();
        return roles.stream()
                .map(RoleResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public void assignRoleToUser(Long roleId, Long userId) {

        roleRepo.assignRoleToUser(roleId, userId);

    }

    @Override
    public RoleResponse getRoleById(Long roleId) {
        Role role =  roleRepo.findById(roleId).orElseThrow();
        return new RoleResponse(role);
    }

    public List<RoleResponse> getRolesAssociatedToUser(Long userId){
        List<Role> roles = roleRepo.getRolesAssociatedToUser(userId);

        return roles.stream()
                .map(RoleResponse::new)
                .collect(Collectors.toList());

    }
}
