package com.fivancemanagement.demo.model;

public class Budget {

    private String category;
    private double limit;

    public Budget(String category, double limit) {
        this.category = category;
        this.limit = limit;
    }

    public Budget() {

    }


    public String getCategory() {
        return category;
    }

    public double getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "category='" + category + '\'' +
                ", limit=" + limit +
                '}';
    }
}
