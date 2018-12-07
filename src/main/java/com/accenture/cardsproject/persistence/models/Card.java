package com.accenture.cardsproject.persistence.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "card")
public class Card {
    @Id
    private long cardNumber;
    private String brand;
    private String cardHolder;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;

    public Card() {
    }

    public Card(long cardNumber, String brand, String cardHolder, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.brand = brand;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean validateCard() {
        Date cardDate = getExpirationDate();
        Date now = new Date();
        if (cardDate.compareTo(now) == 0 || cardDate.compareTo(now) > 0) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber=" + cardNumber +
                ", brand='" + brand + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expirationDate='" + new SimpleDateFormat("dd/MM/yyyy").format(getExpirationDate()) + '\'' +
                '}';
    }

    public int compareTo(Card card) {
        return Comparator.comparing(Card::getCardNumber).thenComparing(Card::getBrand).thenComparing(Card::getCardHolder).thenComparing(Card::getExpirationDate).compare(this, card);
    }
}
