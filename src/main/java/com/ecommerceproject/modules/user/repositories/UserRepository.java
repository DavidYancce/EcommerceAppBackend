package com.ecommerceproject.modules.user.repositories;

import com.ecommerceproject.modules.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    User save(User user);
}
