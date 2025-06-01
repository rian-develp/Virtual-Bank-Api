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
@RequiredArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @Column(length = 11, nullable = false)
    @NonNull
    private String cpf;
    @Column(length = 33, nullable = false, unique = true)
    @NonNull
    private String email;
    @Column(length = 44, nullable = false)
    @NonNull
    private String name;
    @Column(name = "phone_number", length = 11, nullable = false, unique = true)
    @NonNull
    private String phoneNumber;
    @Column(length = 12, nullable = false)
    @NonNull
    private String password;
}
