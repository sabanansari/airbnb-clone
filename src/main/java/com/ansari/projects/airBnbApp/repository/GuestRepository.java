package com.ansari.projects.airBnbApp.repository;

import com.ansari.projects.airBnbApp.entity.Guest;
import com.ansari.projects.airBnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUser(User user);
}