package com.example.phonecontacts.controller;

import com.example.phonecontacts.dto.ContactDto;
import com.example.phonecontacts.entity.Contact;
import com.example.phonecontacts.entity.Email;
import com.example.phonecontacts.entity.PhoneNumber;
import com.example.phonecontacts.entity.User;
import com.example.phonecontacts.repository.EmailRepository;
import com.example.phonecontacts.repository.PhoneNumberRepository;
import com.example.phonecontacts.repository.UserRepository;
import com.example.phonecontacts.service.ContactService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactService contactService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @Test
    public void testAddContact() {
        // Arrange
        Contact contact = new Contact();
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("username");
        User user = new User();
        Mockito.when(userRepository.findByUsername("username")).thenReturn(user);
        Contact createdContact = new Contact();
        Mockito.when(contactService.addContact(contact)).thenReturn(createdContact);

        // Act
        ResponseEntity<Contact> response = contactController.addContact(contact, principal);

        // Assert
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals(createdContact, response.getBody());
        Mockito.verify(contactService, Mockito.times(1)).addContact(contact);
        Mockito.verify(emailRepository, Mockito.times(contact.getEmails().size())).save(Mockito.any(Email.class));
        Mockito.verify(phoneNumberRepository, Mockito.times(contact.getPhoneNumbers().size())).save(Mockito.any(PhoneNumber.class));
    }



    @Test
    public void testDeleteContact() {
        // Arrange
        long contactId = 1;

        // Act
        contactController.deleteContact(contactId);

        // Assert
        Mockito.verify(contactService, Mockito.times(1)).deleteContact(contactId);
    }
    @Test
    public void testEditContact() {
        // Arrange
        long contactId = 1;
        Contact updatedContact = new Contact();
        updatedContact.setId(contactId);
        Contact expectedContact = new Contact();
        Mockito.when(contactService.editContact(updatedContact)).thenReturn(expectedContact);

        // Act
        Contact response = contactController.editContact(contactId, updatedContact);

        // Assert
        Assert.assertEquals(expectedContact, response);
        Mockito.verify(contactService, Mockito.times(1)).editContact(updatedContact);
    }

}




