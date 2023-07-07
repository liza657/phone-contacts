package com.example.phonecontacts.service;

import com.example.phonecontacts.dto.ContactDto;
import com.example.phonecontacts.entity.Contact;

import java.util.List;

public interface ContactService {
    Contact addContact(Contact contact);

    List<Contact> getAllContactsByUserId(long userId);

    void deleteContact(long contactId);

    Contact editContact(Contact contact);


}
