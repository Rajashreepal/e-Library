package com.g.library.e_Library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.g.library.e_Library.enums.SubscriptionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.*;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@With
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String mobileNumber;

    private String role;

    @Email
    @Column(unique = true)
    private String email;

    @Builder.Default
    private SubscriptionStatus subscriptionStatus = SubscriptionStatus.INACTIVE;
}