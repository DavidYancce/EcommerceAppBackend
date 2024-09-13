package com.exchangeproject.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;

    @Setter(AccessLevel.NONE)
    private String password;

    private String fullName;
    public User() {}

    public void setPassword(String password) {
        //TODO: encrypt password
        this.password = password;
    }
}
