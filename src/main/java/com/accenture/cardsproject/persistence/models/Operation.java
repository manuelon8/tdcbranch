package com.accenture.cardsproject.persistence.models;

import javax.persistence.*;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long amount;
    private long cardNumber;
    private double operationRate;

    public Operation() {
    }

    public Operation(long id, long amount, long cardNumber, long operationRate) {
        this.id = id;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.operationRate = operationRate;
    }

    public Operation(long amount, long cardNumber) {
        this.amount = amount;
        this.cardNumber = cardNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getOperationRate() {
        return operationRate;
    }

    public void setOperationRate(double operationRate) {
        this.operationRate = operationRate;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", amount=" + amount  +
                ", cardNumber=" + cardNumber +
                ", operationRate=" + operationRate +
                '}';
    }
}
