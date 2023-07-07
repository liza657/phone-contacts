package com.example.phonecontacts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;
}
