package com.accenture.cardsproject.rest.controller.impl;

import com.accenture.cardsproject.persistence.models.Card;
import com.accenture.cardsproject.persistence.repository.CardRepository;
import com.accenture.cardsproject.rest.controller.CardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardControllerImpl implements CardController {

    @Autowired
    CardRepository cardRepository;

    @RequestMapping(method = RequestMethod.POST)
    @Override
    public void create(@RequestBody Card card) {
        cardRepository.save(card);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public List<Card> list() {
        return cardRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public Card getCard(@PathVariable long id) {
        return cardRepository.findById(id).orElse(null);
    }
}
