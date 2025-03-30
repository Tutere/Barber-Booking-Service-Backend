package com.Tuts.controller;

import com.Tuts.exception.UserException;
import com.Tuts.model.User;
import com.Tuts.repository.UserRepository;
import com.Tuts.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/api/users/{userId}")
    // can optionally avoid brackets if the variable name is same as the path
    // variable i.e. Long userId
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) throws Exception {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userId") Long id) throws Exception {
        User updatedUser = userService.updateUser(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") Long id) throws Exception {
        userService.deleteUse(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
    }

}
