package com.example.bankapi.dtos.customer;

public record CreateCustomerDTO(

        String cpf,
        String email,
        String name,
        String phoneNumber,
        String password
) {
}
