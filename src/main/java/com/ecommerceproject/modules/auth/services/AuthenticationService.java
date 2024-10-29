package com.ecommerceproject.modules.auth.services;

import com.ecommerceproject.enums.RoleEnum;
import com.ecommerceproject.exceptions.EmailAlreadyExistsException;
import com.ecommerceproject.modules.auth.dto.LoginUserDto;
import com.ecommerceproject.modules.auth.dto.RegisterUserDto;
import com.ecommerceproject.modules.user.entity.Role;
import com.ecommerceproject.modules.user.entity.User;
import com.ecommerceproject.modules.user.repositories.RoleRepository;
import com.ecommerceproject.modules.user.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        Optional<User> existingUser = userRepository.findByEmail(input.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException(input.getEmail());
        }

        User user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        Role role = this.roleRepository
                .findById(RoleEnum.BUYER.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));

        user.setRole(role);
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        return userRepository.findByEmailWithPermissions(input.getEmail())
                .orElseThrow();
    }
}
