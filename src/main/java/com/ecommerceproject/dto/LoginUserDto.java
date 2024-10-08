package com.ecommerceproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserDto {
    private String email;

    private String password;
}