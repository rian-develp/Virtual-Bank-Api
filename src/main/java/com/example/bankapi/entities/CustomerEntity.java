package com.example.bankapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "customers")
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @Column(length = 11, nullable = false)
    private String cpf;
    @Column(length = 33, nullable = false, unique = true)
    private String email;
    @Column(length = 44, nullable = false)
    private String name;
    @Column(name = "phone_number", length = 11, nullable = false, unique = true)
    private String phoneNumber;
    @Column(length = 12, nullable = false)
    private String password;
}
