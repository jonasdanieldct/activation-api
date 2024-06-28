package com.activation.activation.api.service;

import com.activation.activation.api.model.User;
import org.springframework.stereotype.Service;
 @Service public interface UserService {
    public User saveUser(User user);
    public User findById(String id);
}
