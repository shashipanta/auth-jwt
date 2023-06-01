package com.jwt.jwtdemo.dto.request;

import com.jwt.jwtdemo.enums.RoleName;
import com.jwt.jwtdemo.model.Role;
import lombok.Data;


@Data
public class RoleRequest {
    private Long roleCode;
    private Integer intRoleName;

    public static Role toRole(RoleRequest roleRequest){
        Role role = new Role();

        role.setRoleCode(roleRequest.getRoleCode());
        role.setRoleName(RoleName.toRoleName(roleRequest.getIntRoleName()));

        return role;
    }
}
