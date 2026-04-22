package com.ansari.projects.airBnbApp.repository;

import com.ansari.projects.airBnbApp.dto.UserDto;
import com.ansari.projects.airBnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
