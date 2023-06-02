package com.jwt.jwtdemo.init;

import com.jwt.jwtdemo.dto.request.RoleRequest;
import com.jwt.jwtdemo.model.Role;
import com.jwt.jwtdemo.repo.RoleRepo;
import com.jwt.jwtdemo.repo.UserAccountRepo;
import com.jwt.jwtdemo.service.RoleService;
import com.jwt.jwtdemo.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleService roleService;
    private final UserAccountService userAccountService;

    @Override
    public void run(String... args) throws Exception {

        RoleRequest adminRole = new RoleRequest(123l, 0);
        RoleRequest userRole = new RoleRequest(456l, 1);
        RoleRequest editorRole = new RoleRequest(789l, 2);

        log.info("Adding default roles : {}");
        log.info("ROLE_ADMIN : {}",adminRole );
        log.info("ROLE_USER : {}", userRole );
        log.info("ROLE_EDITOR : {}", editorRole );

        roleService.createRole(adminRole);
        roleService.createRole(userRole);
        roleService.createRole(editorRole);

    }
}
