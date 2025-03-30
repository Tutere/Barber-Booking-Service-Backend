package com.Tuts.service;

import java.util.List;

import com.Tuts.exception.UserException;
import com.Tuts.model.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id) throws UserException;

    List<User> getAllUsers();

    void deleteUse(Long id) throws UserException;

    User updateUser(User user, Long id) throws UserException;
}
