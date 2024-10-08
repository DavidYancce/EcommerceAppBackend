package com.ecommerceproject.domain.port;

import com.ecommerceproject.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    User save(User user);
}
