package com.ecommerceproject.domain.model;

import com.ecommerceproject.modules.user.entity.Role;
import jakarta.persistence.*;
import com.ecommerceproject.domain.model.Permission;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ROLE_PERMISSION", schema = "EXCHANGE_APP")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}