package com.example.bankapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "cards")
@Table(name = "cards")
@AllArgsConstructor
@Getter
@Setter
public class CardEntity {
    @Id
    @Column(name = "card_number", nullable = false)
    private String cardNumber;
    @Column(nullable = false, unique = true)
    private String flag;
    @Column(nullable = false)
    private String cardholder;
    @Column(length = 7, nullable = false)
    private LocalDate cardValidity;
}
