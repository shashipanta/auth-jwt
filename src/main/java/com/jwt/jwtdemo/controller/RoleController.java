package com.jwt.jwtdemo.controller;

import com.jwt.jwtdemo.dto.request.RoleRequest;
import com.jwt.jwtdemo.dto.response.RoleResponse;
import com.jwt.jwtdemo.repo.RoleRepo;
import com.jwt.jwtdemo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/admin/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping(value = "/")
    public RoleResponse registerNewRole(@RequestBody RoleRequest roleRequest){
        return roleService.createRole(roleRequest);
    }

    @GetMapping(value = "/")
    public List<RoleResponse> getAllRegisteredRoles(){
        return roleService.getRegisteredRoles();
    }


}
