package com.jwt.jwtdemo.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RoleName {

    ROLE_ADMIN,
    ROLE_USER,
    ROLE_EDITOR;

    private static List<RoleName> roles = Arrays.asList(RoleName.values());

    // convert given integer number into roleName enum
    public static RoleName toRoleName(Integer value){

        return roles.stream()
                .filter(roleName -> roleName.ordinal() == value)
                .findFirst()
                .orElseThrow();
    }
}
