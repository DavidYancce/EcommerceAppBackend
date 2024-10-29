package com.ecommerceproject.config;

import com.ecommerceproject.modules.user.repositories.UserRepository;
import com.ecommerceproject.modules.user.services.MyUserDetailsService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration  {
    private final UserRepository userRepository;
    private final MyUserDetailsService myUserDetailsService;

    public ApplicationConfiguration (UserRepository userRepository, MyUserDetailsService myUserDetailsService) {
        this.userRepository = userRepository;
        this.myUserDetailsService = myUserDetailsService;
    }
}
