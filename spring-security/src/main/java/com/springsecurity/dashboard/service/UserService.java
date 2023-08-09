package com.springsecurity.dashboard.service;

import com.springsecurity.dashboard.model.User;
import com.springsecurity.dashboard.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}