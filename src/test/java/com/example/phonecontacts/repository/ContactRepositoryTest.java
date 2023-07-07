package com.example.phonecontacts.repository;

import com.example.phonecontacts.entity.Contact;
import com.example.phonecontacts.entity.Email;
import com.example.phonecontacts.entity.PhoneNumber;
import org.assertj.core.api.Assertions;
import org.hibernate.dialect.MySQLDialect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;

    private Contact createValidContact() {
        Contact contact = new Contact();
        contact.setContact("John Doe");
        contact.setImageURL1("https://example.com/image.jpg");
        contact.getEmails().add(Email.builder().email("john.doe@example.com").contact(contact).build());
        contact.getPhoneNumbers().add(PhoneNumber.builder().phone("+1234567890").contact(contact).build());
        return contact;
    }

    @Test
    void saveContact_ValidContact_SuccessfullySaved() {
        Contact contact = createValidContact();

        Contact savedContact = contactRepository.save(contact);
        Assertions.assertThat(savedContact).isNotNull();
        Assertions.assertThat(savedContact.getId()).isGreaterThan(0);
    }
}
