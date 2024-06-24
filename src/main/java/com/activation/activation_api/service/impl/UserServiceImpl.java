package com.activation.activation_api.service.impl;

import com.activation.activation_api.model.User;
import com.activation.activation_api.repository.UserRepository;
import com.activation.activation_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class UserServiceImpl implements UserService {
        @Autowired UserRepository userRepository;

        public User saveUser(User user){
            return userRepository.save(user);
        }
        public User findById(String id){
            return userRepository.findById(id).orElse(null);
        }
}
