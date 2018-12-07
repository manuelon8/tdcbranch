package com.accenture.cardsproject.rest.controller;

import com.accenture.cardsproject.persistence.models.Card;

import java.util.List;

public interface CardController {
    void create(Card card);
    List<Card> list();
    Card getCard(long id);
}
