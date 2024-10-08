package com.ecommerceproject.modules.user.services;

import com.ecommerceproject.modules.user.entity.User;
import com.ecommerceproject.modules.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
