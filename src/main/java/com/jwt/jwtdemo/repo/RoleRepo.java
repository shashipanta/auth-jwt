package com.jwt.jwtdemo.repo;

import com.jwt.jwtdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {

    @Query(
            value = "INSERT into account_role(account_id, role_id) values (?1, ?2)",
            nativeQuery = true)
    Integer assignRoleToUser(Long roleId, Long userId);

//    @Query("select r.roleCode, r.id, r.roleName from Role r " +
//            "left join account_role a_r join UserAccount u_a on a_r.user_id = u_a.id" +
//            "on r.id = a_r.role_id"+
//            "where user.id = ?1")

    @Query(
            value = "select r.id, r.code, r.role_name from role r left join (user_tbl u join account_role ar on ar.account_id = u.id)" +
                    "on ar.role_id = r.id where u.id = ?1",
            nativeQuery = true
    )
    List<Role> getRolesAssociatedToUser(Long userId);
}
