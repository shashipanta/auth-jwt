package com.jwt.jwtdemo.dto.request;

import com.jwt.jwtdemo.enums.RoleName;
import com.jwt.jwtdemo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RoleRequest {
    private Long roleCode;
    private Integer intRoleName;

//    public RoleRequest(Long roleCode, Integer intRoleName){
//        this.roleCode = roleCode;
//        this.intRoleName = intRoleName;
//    }

    public static Role toRole(RoleRequest roleRequest){
        Role role = new Role();

        role.setRoleCode(roleRequest.getRoleCode());
        role.setRoleName(RoleName.toRoleName(roleRequest.getIntRoleName()));

        return role;
    }
}
