package com.ecommerceproject.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    BUYER(1L),
    TEAM_MEMBER(2L);

    private final Long id;
    RoleEnum(Long id) {
        this.id = id;
    }
}
