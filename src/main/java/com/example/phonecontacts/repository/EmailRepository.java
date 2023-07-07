package com.example.phonecontacts.repository;

import com.example.phonecontacts.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Long> {
}
