package com.activation.activation.api.controller;

import com.activation.activation.api.service.UserService;
import com.activation.activation.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user") public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/{id}") public ResponseEntity<User> getUserById(@PathVariable String id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
    }
}
