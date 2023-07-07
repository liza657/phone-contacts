package com.example.phonecontacts.repository;

import com.example.phonecontacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    User findUserById(long userId);
}
