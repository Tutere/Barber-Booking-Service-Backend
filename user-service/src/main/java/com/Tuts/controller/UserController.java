package com.Tuts.controller;

import com.Tuts.model.User;
import com.Tuts.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/users")
    public User creatUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/api/users/{userId}")
    // can optionally avoid brackets if the variable name is same as the path
    // variable i.e. Long userId
    public User getUserById(@PathVariable("userId") Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not found");
    }

    @PutMapping("/api/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable("userId") Long id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new Exception("User not found with id: " + id);
        }
        User existingUser = userOptional.get();

        existingUser.setFullname(user.getFullname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/api/users/{userId}")
    public String deleteUserById(@PathVariable("userId") Long id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new Exception("User not found with id: " + id);
        }
        userRepository.deleteById(userOptional.get().getId());
        return "User deleted successfully";
    }

}
