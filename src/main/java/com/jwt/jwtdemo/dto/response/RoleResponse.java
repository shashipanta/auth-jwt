package com.jwt.jwtdemo.dto.response;


import com.jwt.jwtdemo.model.Role;
import lombok.Data;

@Data
public class RoleResponse {

    private String roleName;
    private Long roleCode;

    public RoleResponse(Role role) {
        this.roleName = role.getRoleName().name();
        this.roleCode = role.getRoleCode();
    }
}
