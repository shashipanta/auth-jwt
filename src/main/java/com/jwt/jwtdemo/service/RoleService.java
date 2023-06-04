package com.jwt.jwtdemo.service;


import com.jwt.jwtdemo.dto.request.RoleRequest;
import com.jwt.jwtdemo.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest roleRequest);
    List<RoleResponse>  getRegisteredRoles();

    void assignRoleToUser(Long roleId, Long userId);

    RoleResponse getRoleById(Long roleId);

    List<RoleResponse> getRolesAssociatedToUser(Long userId);
}
