package com.accenture.cardsproject.rest.controller.impl;

import com.accenture.cardsproject.dto.OperationRateRequest;
import com.accenture.cardsproject.dto.OperationRateResponse;
import com.accenture.cardsproject.exceptions.InvalidDateException;
import com.accenture.cardsproject.exceptions.InvalidParameterException;
import com.accenture.cardsproject.persistence.models.Brand;
import com.accenture.cardsproject.persistence.models.Card;
import com.accenture.cardsproject.persistence.models.Operation;
import com.accenture.cardsproject.persistence.repository.OperationRepository;
import com.accenture.cardsproject.rest.controller.BrandController;
import com.accenture.cardsproject.rest.controller.CardController;
import com.accenture.cardsproject.rest.controller.OperationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/operation")
public class OperationControllerImpl implements OperationController {
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    CardController cardController;
    @Autowired
    BrandController brandController;


    @RequestMapping(method = RequestMethod.POST)
    @Override
    public void create(@RequestBody Operation operation) {
        Card card = cardController.getCard(operation.getCardNumber());
        if (card.validateCard()) {
            if (validateOperation(operation)){
                operation.setOperationRate(calculateOperationRateFromCard(card));
                operationRepository.save(operation);
            }
            else{
                throw new InvalidParameterException(String.valueOf(operation.getAmount()));
            }
        } else {
            throw new InvalidDateException(new SimpleDateFormat("dd/MM/yyyy").format(card.getExpirationDate()));
        }
    }

    private String getPriceFromBrand(Card card) {
        Brand brand = brandController.getBrand(card.getBrand());
        String price = brand.getServicePrice();
        price = price.replace("ñ", "ni");
        return price;
    }

    private double[] setPricingParameter(String[] s) {
        double[] longs= new double[2];
        Calendar now = new GregorianCalendar();
        int ind = 0;
        for (String s1 : s) {
            switch (s1){
                case "anio" : longs[ind] = now.get(Calendar.YEAR);
                    break;
                case "dia" : longs[ind] = now.get(Calendar.DAY_OF_MONTH);
                    break;
                case "mes" : longs[ind] = now.get(Calendar.MONTH) + 1;
                    break;
                default: longs[ind] = Double.parseDouble(s1);
            }
            ind++;
        }
        return longs;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Override
    public List<Operation> list() {
        return operationRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public Operation getOperation(@PathVariable long id) {
        return operationRepository.findById(id).orElse(null);
    }

    @Override
    @RequestMapping(value = "/calculaterate", method = RequestMethod.POST)
    public OperationRateResponse calculateOperationRateRest(@RequestBody OperationRateRequest operationRateRequest){
        Brand brand = brandController.getBrand(operationRateRequest.getBrand());
        String price = brand.getServicePrice();
        price = price.replace("ñ", "ni");
        String[] stringArray = price.split("[/|*]");
        double[] parameters = setPricingParameter(stringArray);
        double parameter1 = parameters[0];
        double parameter2 = parameters[1];
        OperationRateResponse operationRateResponse = new OperationRateResponse();
        operationRateResponse.setAmount(operationRateRequest.getAmount());
        operationRateResponse.setMarca(operationRateRequest.getBrand());
        if(price.contains("/")) operationRateResponse.setOperationrate(operationRateRequest.getAmount()*parameter1/parameter2);
        else operationRateResponse.setOperationrate(operationRateRequest.getAmount()*parameter1*parameter2);
        return operationRateResponse;
    }

    @Override
    public double calculateOperationRateFromCard(@RequestBody Card cd) {
        String price = getPriceFromBrand(cd);
        String[] stringArray = price.split("[/|*]");
        double[] parameters = setPricingParameter(stringArray);
        double parameter1 = parameters[0];
        double parameter2 = parameters[1];
        if(price.contains("/")) return parameter1/parameter2;
        else return parameter1*parameter2;
    }

    public boolean validateOperation(Operation operation) {
        return operation.getAmount() < 1000;
    }
}
