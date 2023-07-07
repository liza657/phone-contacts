package com.example.phonecontacts.repository;

import com.example.phonecontacts.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Long> {

}
