package com.example.bankapi.services;

import com.example.bankapi.entities.CardEntity;
import com.example.bankapi.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    public void getAllCards(String cpf){
        repository.findAll();
    }

    public void deleteAllCards(){
        repository.deleteAll();
    }

    public void deleteCardByNumber(String cardNumber){

        if (cardNumber.isBlank() || isNull(cardNumber))
            throw new IllegalArgumentException("Inserir o número do cartão corretamente");

        repository.deleteById(cardNumber);
    }

    public void createCard(String cardNumber, String flag, String cardHolder, String cardValidity){
        if (cardNumber.isBlank() || isNull(cardNumber) || flag.isBlank() || isNull(flag) ||
                cardHolder.isBlank() || isNull(cardHolder) || cardValidity.isBlank() || isNull(cardValidity)
        ) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos corretamente");
        }

        var optional = validateCardValidity(cardValidity);

        if (optional.isEmpty())
            throw new IllegalArgumentException("data inválida");

        CardEntity cardEntity = new CardEntity(cardNumber.trim(), flag.trim(), cardHolder.trim(), optional.get());
        repository.save(cardEntity);
    }

    private Optional<LocalDate> validateCardValidity(String date){

        if (date.length() < 10)
            return Optional.empty();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date.trim(), dateTimeFormatter);

        if (localDate.isAfter(LocalDate.now()))
            return Optional.empty();

        return Optional.of(localDate);
    }

}
