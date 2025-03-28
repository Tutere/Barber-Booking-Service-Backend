package com.Tuts.controller;

import com.Tuts.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public User getUser() {
        User user = new User();
        user.setEmail("Tuts@gmail.com");
        user.setFullname("Tuts Test");
        user.setPhone("+64 12343545");
        user.setRole("Customer");
        return user;
    }

}
