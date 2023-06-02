package com.jwt.jwtdemo.repo;

import com.jwt.jwtdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepo extends JpaRepository<Role, Long> {

    @Query(
            value = "INSERT into account_role(account_id, role_id) values (?1, ?2)",
            nativeQuery = true)
    Integer assignRoleToUser(Long roleId, Long userId);
}
