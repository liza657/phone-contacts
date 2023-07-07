package com.example.phonecontacts.mapper;

import com.example.phonecontacts.dto.ContactDto;
import com.example.phonecontacts.entity.Contact;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactMapper {
    @Autowired
    ModelMapper modelMapper;


    public ContactDto toDto(Contact contact) {
        return modelMapper.map(contact, ContactDto.class);
    }

    public Contact toModel(ContactDto animalDto) {
        return modelMapper.map(animalDto, Contact.class);
    }

    public List<ContactDto> toDtoList(List<Contact> animalList) {
        return animalList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Contact> toModelList(List<ContactDto> animalDtoList) {
        return animalDtoList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
