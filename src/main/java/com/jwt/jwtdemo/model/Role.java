package com.jwt.jwtdemo.model;

import com.jwt.jwtdemo.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_role_roleName", columnNames = "role_name")
        }
)
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
