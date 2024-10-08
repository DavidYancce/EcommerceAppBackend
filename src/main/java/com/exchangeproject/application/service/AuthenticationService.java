package com.exchangeproject.application.service;

import com.exchangeproject.dto.LoginUserDto;
import com.exchangeproject.dto.RegisterUserDto;
import com.exchangeproject.domain.model.Role;
import com.exchangeproject.domain.model.User;
import com.exchangeproject.enums.RoleEnum;
import com.exchangeproject.exceptions.EmailAlreadyExistsException;
import com.exchangeproject.domain.port.RoleRepository;
import com.exchangeproject.domain.port.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            throw new EmailAlreadyExistsException("El email " + input.getEmail() + " ya está registrado");
        }

        User user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        Role role;
        if (input.getRole() != null && input.getRole().getId() != null) {
            if (input.getRole().getId() == RoleEnum.ADMIN.getId()) {
                throw new RuntimeException("No se cuenta con permisos para esta petición");
            }

            role = roleRepository.findById(input.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        } else {
            role = roleRepository.findById(RoleEnum.CUSTOMER.getId())
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
        }

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

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
