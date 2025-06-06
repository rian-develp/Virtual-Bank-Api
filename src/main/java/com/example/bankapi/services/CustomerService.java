package com.example.bankapi.services;

import com.example.bankapi.entities.CustomerEntity;
import com.example.bankapi.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public List<CustomerEntity> findAll(){
        return repository.findAll();
    }

    public CustomerEntity findByCpf(String cpf) throws EntityNotFoundException{
        return repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public void signUpCustomer(String cpf, String email, String name, String phoneNumber, String password) throws IllegalArgumentException {

        if (cpf.isBlank() || isNull(cpf) || email.isBlank() || isNull(email) || name.isBlank() || isNull(name)
                || phoneNumber.isBlank() || isNull(phoneNumber) || password.isBlank() || isNull(password)
        ) {
            throw new IllegalArgumentException("os campos precisam ser preenchido corretamente");
        }

        if (!email.matches("^([a-z]){1,}([a-z0-9._-]){1,}([@]){1}([a-z]){2,}([.]){1}([a-z]){2,}([.]?){1}([a-z]?){2,}$"))
            throw new IllegalArgumentException("Email inválido");

        CustomerEntity customerEntity = new CustomerEntity(cpf.trim(), email.trim(), name.trim(),
                phoneNumber.trim(), password.trim());

        repository.save(customerEntity);
    }

    public void deleteCustomer(String cpf){
        repository.deleteById(cpf);
    }
}
