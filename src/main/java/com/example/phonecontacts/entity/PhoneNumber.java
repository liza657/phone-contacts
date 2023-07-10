package com.example.phonecontacts.entity;

import com.example.phonecontacts.validation.PhoneNumberValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PhoneNumberValidation
    private String phone;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;
//
//    public PhoneNumber(String s) {
//    }
}
