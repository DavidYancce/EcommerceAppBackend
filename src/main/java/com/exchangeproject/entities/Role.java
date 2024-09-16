package com.exchangeproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "APP_ROLE")
@Data
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
