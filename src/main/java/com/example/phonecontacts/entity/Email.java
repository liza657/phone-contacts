package com.example.phonecontacts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;


}
