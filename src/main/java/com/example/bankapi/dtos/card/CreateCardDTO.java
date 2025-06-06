package com.example.bankapi.dtos.card;

public record CreateCardDTO(
        String cardNumber,
        String flag,
        String cardholder,
        String cardValidity
) {
}
