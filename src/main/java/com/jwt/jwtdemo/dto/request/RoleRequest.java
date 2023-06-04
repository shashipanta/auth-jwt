package com.jwt.jwtdemo.dto.request;

import com.jwt.jwtdemo.enums.RoleName;
import com.jwt.jwtdemo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class RoleRequest {

    private Long id;
    private Long roleCode;
    private Integer intRoleName;

//    public RoleRequest(Long roleCode, Integer intRoleName){
//        this.roleCode = roleCode;
//        this.intRoleName = intRoleName;
//    }

    public RoleRequest(Long roleCode, Integer intRoleName){
        this.roleCode = roleCode;
        this.intRoleName = intRoleName;
    }

    public RoleRequest(Role role){
        this.id = role.getId();
        this.roleCode = role.getRoleCode();
        this.intRoleName = role.getRoleName().ordinal();
    }

    public static Role toRole(RoleRequest roleRequest){
        Role role = new Role();

        role.setId(roleRequest.getId());
        role.setRoleCode(roleRequest.getRoleCode());
        role.setRoleName(RoleName.toRoleName(roleRequest.getIntRoleName()));

        return role;
    }
}
