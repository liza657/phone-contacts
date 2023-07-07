package com.example.phonecontacts.repository;

import com.example.phonecontacts.dto.ContactDto;
import com.example.phonecontacts.entity.Contact;
import com.example.phonecontacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findAllByUserId(long userId);
    Contact getContactById(long contactId);


}
