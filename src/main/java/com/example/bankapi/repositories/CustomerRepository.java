package com.example.bankapi.repositories;

import com.example.bankapi.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

     @Query(value = "SELECT * FROM customers WHERE cpf := cpf", nativeQuery = true)
     CustomerEntity findByCPF(@Param("cpf") String cpf);
}
