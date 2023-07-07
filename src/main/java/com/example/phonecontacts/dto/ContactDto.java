package com.example.phonecontacts.dto;

import com.example.phonecontacts.entity.Contact;
import com.example.phonecontacts.entity.Email;
import com.example.phonecontacts.entity.PhoneNumber;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ContactDto {
    private String contact;
    private String imageURL1;
    private List<String> emails;
    private List<String> phoneNumbers;



}
