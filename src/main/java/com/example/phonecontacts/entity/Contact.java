package com.example.phonecontacts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String contact;
    private String imageURL1;

    @Valid
    @UniqueElements(message = "Duplicate email found")
    @Size(min = 1, message = "At least one email must be provided")
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Email> emails = new ArrayList<>();


    @Valid
    @UniqueElements(message = "Duplicate phone number found")
    @Size(min = 1, message = "At least one phone number must be provided")

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<@Valid @Pattern(regexp = "^\\+\\d{1,3}-\\d+$", message = "Invalid phone number format") PhoneNumber> phoneNumbers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}
