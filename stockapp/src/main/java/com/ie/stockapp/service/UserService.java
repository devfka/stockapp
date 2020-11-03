package com.ie.stockapp.service;

import com.ie.stockapp.model.entity.User;
import com.ie.stockapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserName(String username) {
        return this.userRepository.findByUsername(username);
    }

    // TODO: 10/30/2020 - exception handling
}
