package com.accenture.cardsproject.rest.controller;

import com.accenture.cardsproject.dto.OperationRateRequest;
import com.accenture.cardsproject.dto.OperationRateResponse;
import com.accenture.cardsproject.persistence.models.Card;
import com.accenture.cardsproject.persistence.models.Operation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationController {
    void create(Operation operation);
    List<Operation> list();
    Operation getOperation(long id);
    double  calculateOperationRateFromCard(Card cd);
    OperationRateResponse calculateOperationRateRest(OperationRateRequest operationRateRequest);
}
