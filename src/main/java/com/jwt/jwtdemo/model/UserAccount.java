package com.jwt.jwtdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tbl")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 100, nullable = false)
    private String username;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_no", length = 10, nullable = false)
    private String phoneNumber;

    @ManyToMany(fetch = EAGER, cascade = {ALL})
       @JoinTable(
               name = "account_role",
               joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
               foreignKey = @ForeignKey(name = "fk_user_roleId"),
               inverseForeignKey = @ForeignKey(name = "fk_role_userId")
       )
    List<Role> roles;


}
