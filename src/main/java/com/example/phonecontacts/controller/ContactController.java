package com.example.phonecontacts.controller;

import com.example.phonecontacts.dto.ContactDto;
import com.example.phonecontacts.entity.Contact;
import com.example.phonecontacts.entity.Email;
import com.example.phonecontacts.entity.PhoneNumber;
import com.example.phonecontacts.entity.User;
import com.example.phonecontacts.repository.ContactRepository;
import com.example.phonecontacts.repository.EmailRepository;
import com.example.phonecontacts.repository.PhoneNumberRepository;
import com.example.phonecontacts.repository.UserRepository;
import com.example.phonecontacts.service.ContactService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    PhoneNumberRepository phoneNumberRepository;
@Transactional
    @PostMapping("addContact")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        contact.setUser(user);

        Contact createdContact = contactService.addContact(contact);

        for (Email email : contact.getEmails()) {
            email.setContact(createdContact);
            emailRepository.save(email);
        }

        for (PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
            phoneNumber.setContact(createdContact);
            phoneNumberRepository.save(phoneNumber);
        }

        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @GetMapping("getContacts/{userId}")
    public List<ContactDto> getContactsByUser(@PathVariable("userId") long userId) {
        User user = userRepository.findUserById(userId);
        List<Contact> contacts = user.getContacts();
        List<ContactDto> contactDtos = new ArrayList<>();
        for (Contact contact : contacts) {
            ContactDto contactDto = new ContactDto();
            contactDto.setContact(contact.getContact());
            contactDto.setImageURL1(contact.getImageURL1());
            contactDto.setEmails(contact.getEmails().stream().map(Email::getEmail).collect(Collectors.toList()));
            contactDto.setPhoneNumbers(contact.getPhoneNumbers().stream().map(PhoneNumber::getPhone).collect(Collectors.toList()));
            contactDtos.add(contactDto);
        }
        return contactDtos;
    }


    @DeleteMapping("deleteById/{id}")
    public void deleteContact(@PathVariable("id") long contactId) {
        contactService.deleteContact(contactId);
    }

    @PutMapping("editById/{id}")
    public Contact editContact(@Valid @PathVariable("id") long contactId, @RequestBody Contact updatedContact) {
        updatedContact.setId(contactId);
        return contactService.editContact(updatedContact);
    }
}
