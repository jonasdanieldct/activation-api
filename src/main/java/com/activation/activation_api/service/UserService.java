package com.activation.activation_api.service;

import com.activation.activation_api.model.User;
import org.springframework.stereotype.Service;
 @Service public interface UserService {
    public User saveUser(User user);
    public User findById(String id);
}
