package com.ecommerceproject.dto; // Cambia el paquete según la estructura de tu proyecto

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserDto {
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private RoleDto role;
}
