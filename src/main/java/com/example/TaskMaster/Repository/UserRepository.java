package com.example.TaskMaster.Repository;

import com.example.TaskMaster.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findAllByUsername(String username);
   Optional<User> findUserByUserId(Long userId);
}
