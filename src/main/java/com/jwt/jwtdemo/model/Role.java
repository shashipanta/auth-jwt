package com.jwt.jwtdemo.model;

import com.jwt.jwtdemo.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private Long roleCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 50, nullable = false)
    private RoleName roleName;
}
