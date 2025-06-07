package com.example.bankapi.controllers;

import com.example.bankapi.dtos.customer.CreateCustomerDTO;
import com.example.bankapi.dtos.customer.FindCustomerByCpfDTO;
import com.example.bankapi.entities.CustomerEntity;
import com.example.bankapi.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerEntity>> findAllCustomers(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/customer/{cpf}")
    public ResponseEntity<?> findCustomerByCpf(@RequestBody FindCustomerByCpfDTO dto){
        try{
            var entity = service.findByCpf(dto.cpf());
            return ResponseEntity.ok(entity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerDTO dto){
        try {
            service.signUpCustomer(dto.cpf(), dto.email(), dto.name(), dto.phoneNumber(), dto.password());
            return ResponseEntity.status(HttpStatus.CREATED).body("Sucesso no cadastro");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
