package com.example.bankapi.controllers;

import com.example.bankapi.dtos.card.CreateCardDTO;
import com.example.bankapi.entities.CardEntity;
import com.example.bankapi.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping("card/{cpf}")
    public ResponseEntity<List<CardEntity>> findAllCards(@PathVariable("cpf") String cpf){
        return ResponseEntity.ok(service.getAllCards(cpf));
    }

    @PostMapping()
    public ResponseEntity<String> createCard(@RequestBody CreateCardDTO dto){
        try{
            service.createCard(dto.cardNumber(), dto.flag(), dto.cardholder(), dto.cardValidity());
            return ResponseEntity.status(HttpStatus.CREATED).body("Sucesso ao criar cartão");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/card/delete/{cardNumber}")
    public ResponseEntity<String> deleteCardByCardNumber(@PathVariable("cardNumber") String cardNumber){
        try{
            service.deleteCardByNumber(cardNumber);
            return ResponseEntity.ok("Sucesso na delação");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/card/delete")
    public ResponseEntity<String> deleteAllCards(){
        service.deleteAllCards();
        return ResponseEntity.ok("Sucesso na deleção");
    }
}
