package com.financemanagement.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {


    private String category;
    private double amount;
    private LocalDate localDate;

    public Transaction(String category, double amount, LocalDate localDate) {
        this.category = category;
        this.amount = amount;
        this.localDate = localDate;
    }

    public Transaction() {

    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                ", localDate=" + localDate +
                '}';
    }
}
