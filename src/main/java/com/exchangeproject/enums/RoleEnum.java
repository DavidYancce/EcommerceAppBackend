package com.exchangeproject.enums;

public enum RoleEnum {
    ADMIN(1),
    CUSTOMER(2),
    ENTERPRISE(3);

    private final int id;

    RoleEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
