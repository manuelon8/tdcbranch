package com.accenture.cardsproject.dto;

public class OperationRateRequest {
    private String brand;
    private long amount;

    public OperationRateRequest() {
    }

    public OperationRateRequest(String brand, long amount) {
        this.brand = brand;
        this.amount = amount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
