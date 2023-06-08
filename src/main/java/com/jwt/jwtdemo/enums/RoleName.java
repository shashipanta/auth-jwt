package com.jwt.jwtdemo.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RoleName {

    ROLE_ADMIN(1),
    ROLE_USER(2),
    ROLE_TESTER(3),
    ROLE_EDITOR(4);

    public final Integer roleIntVal;

    RoleName(Integer roleIntVal){
        this.roleIntVal = roleIntVal;
    }

    private static List<RoleName> roles = Arrays.asList(RoleName.values());

    // convert given integer number into roleName enum
    public static RoleName toRoleName(Integer value){

        return roles.stream()
                .filter(roleName -> roleName.ordinal() == value)
                .findFirst()
                .orElseThrow();
    }

    public static String roleNameToString(RoleName roleName){
        return roleName.name();
    }

    public static RoleName stringToRoleName(String roleName){
        return  RoleName.valueOf(roleName);
    }
}
