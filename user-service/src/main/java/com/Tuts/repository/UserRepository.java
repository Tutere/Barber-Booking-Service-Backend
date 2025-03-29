package com.Tuts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tuts.model.User;

// Need to state which entity the repository is for and the type of the primary key/unique identifier
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
