package com.example.phonecontacts.service;

import com.example.phonecontacts.dto.ContactDto;
import com.example.phonecontacts.entity.Contact;
import com.example.phonecontacts.mapper.ContactMapper;
import com.example.phonecontacts.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ModelMapper modelMapper;
//    @Override
//    public Contact addContact(Contact contact) {
//        return contactRepository.save(contact);
//
//    }

    @Override
    public Contact addContact(Contact contact) {
        Contact newContact=new Contact();
        newContact.setContact(contact.getContact());
        newContact.setImageURL1(contact.getImageURL1());
        newContact.setEmails(contact.getEmails());
        newContact.setPhoneNumbers(contact.getPhoneNumbers());
        return contactRepository.save(contact);

    }

    @Override
    public List<Contact>  getAllContactsByUserId(long userId) {
        return contactRepository.findAllById(Collections.singleton(userId));
    }

    @Override
    public void deleteContact(long contactId) {
        contactRepository.deleteById(contactId);
    }

    @Override
    public Contact editContact(Contact updatedContact) {
        Contact existingContact = getContactById(updatedContact.getId());

        existingContact.setContact(updatedContact.getContact());
        existingContact.setEmails(updatedContact.getEmails());
        existingContact.setPhoneNumbers(updatedContact.getPhoneNumbers());

        return contactRepository.save(existingContact);

    }
    public Contact getContactById(Long contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));
    }
}
