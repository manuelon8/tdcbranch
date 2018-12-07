package com.accenture.cardsproject.dto;

public class OperationRateResponse {
    private String marca;
    private long amount;
    private double operationrate;

    public OperationRateResponse() {
    }

    public OperationRateResponse(String marca, long amount, double operationrate) {
        this.marca = marca;
        this.amount = amount;
        this.operationrate = operationrate;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public double getOperationrate() {
        return operationrate;
    }

    public void setOperationrate(double operationrate) {
        this.operationrate = operationrate;
    }
}
