package com.jwt.jwtdemo.dto.response;


import com.jwt.jwtdemo.dto.request.RoleRequest;
import com.jwt.jwtdemo.enums.RoleName;
import com.jwt.jwtdemo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleResponse {

    private Long roleId;
    private String roleName;
    private Long roleCode;

    public RoleResponse(Role role) {
        this.roleName = role.getRoleName().name();
        this.roleCode = role.getRoleCode();
        this.roleId = role.getId();
    }

    // roleResponse to RoleRequest for adding role along with user if role already exists
    public static RoleRequest toRoleRequest(RoleResponse roleResponse){
        RoleName roleName = RoleName.valueOf(roleResponse.getRoleName());
        Integer intRoleName = roleName.ordinal();

        return RoleRequest.builder()
                .id(roleResponse.getRoleId())
                .intRoleName(intRoleName)
                .roleCode(roleResponse.getRoleCode())
                .build();

//        Role role =  new Role();
//        role.setId(roleResponse.getRoleId());
//        role.setRoleName(roleName);
//        role.setRoleCode(roleResponse.getRoleCode());
//        return role;
    }
}
