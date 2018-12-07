package com.accenture.cardsproject;

import com.accenture.cardsproject.exceptions.InvalidDateException;
import com.accenture.cardsproject.exceptions.InvalidParameterException;
import com.accenture.cardsproject.persistence.models.Brand;
import com.accenture.cardsproject.persistence.models.Card;
import com.accenture.cardsproject.persistence.models.Operation;
import com.accenture.cardsproject.rest.controller.BrandController;
import com.accenture.cardsproject.rest.controller.CardController;
import com.accenture.cardsproject.rest.controller.OperationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CardsprojectApplication {

    @Autowired
    BrandController brandController;

    @Autowired
    CardController cardController;

    @Autowired
    OperationController operationController;

    public static void main(String[] args) {
        SpringApplication.run(CardsprojectApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            printBrandList();
            long cardNumber1 = 1234;
            processRequest(cardNumber1);
            long cardNumber2 = 4444;
            processRequest(cardNumber2);
            compareCards(cardNumber1, cardNumber2);
            compareCards(cardNumber1, cardNumber1);
            long opNumber = 4;
            System.out.println(informOperationData(opNumber));
        };
    }

    private String informOperationData(long operationNumber) {
        Operation op = operationController.getOperation(operationNumber);
        Card card = cardController.getCard(op.getCardNumber());
        StringBuilder sb = new StringBuilder();
        sb.append("*******************************")
                .append("\nObteniendo información sobre la operación nro.: ")
                .append(operationNumber)
                .append("\nMarca de tarjeta : ")
                .append(card.getBrand())
                .append("\nImporte : $")
                .append(op.getAmount())
                .append("\nTasa : ")
                .append(op.getOperationRate())
                .append("\n*******************************");
        return sb.toString();
    }

    private void compareCards(long cardNumber1, long cardNumber2) {
        System.out.println("***************************");
        System.out.println(String.format("Comparando tarjeta nro: %d con tarjeta nro: %d", cardNumber1, cardNumber2));
        Card card1 = cardController.getCard(cardNumber1);
        Card card2 = cardController.getCard(cardNumber2);
        if (card1.compareTo(card2) == 0) {
            System.out.println("Las tarjetas son iguales.");
            System.out.println("***************************");
        } else {
            System.out.println("Las tarjetas son distintas.");
            System.out.println("***************************");
        }
    }

    private void printBrandList() {
        System.out.println("**********************************");
        List<Brand> brandList = brandController.list();
        for (Brand brand : brandList) {
            System.out.println(brand.toString());
        }
        System.out.println("**********************************");
    }

    private void processRequest(long cardNumber) {
        enterCard(cardNumber);
        System.out.println("Ingresando operación con monto=1000 y número de tarjeta " + cardNumber);
        enterOperation(1000, cardNumber);
        System.out.println("Ingresando operación con monto=500 y número de tarjeta " + cardNumber);
        enterOperation(500, cardNumber);
    }

    private void enterCard(long cardNumber) {
        System.out.println("**********************************");
        System.out.println("Realizando consulta para la tarjeta = " + cardNumber);
        System.out.println(cardController.getCard(cardNumber));
        System.out.println("**********************************");
    }

    private void enterOperation(long amount, long cardNumber) {
        Operation op = new Operation(amount, cardNumber);
        try {
            operationController.create(op);
            System.out.println("Operación exitosa!");
        } catch (InvalidParameterException | InvalidDateException e) {
            System.out.println(e.getMessage());
        }
    }
}
