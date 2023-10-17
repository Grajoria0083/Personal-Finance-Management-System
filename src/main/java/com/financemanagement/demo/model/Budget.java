package com.financemanagement.demo.model;

public class Budget {

    private String category;
    private double limit;

    private double totalBdgetLimit;

    private double curentBudget;

    public Budget(String category, double limit) {
        this.category = category;
        this.limit = limit;
    }

    public Budget() {

    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getTotalBdgetLimit() {
        return totalBdgetLimit;
    }

    public void setTotalBdgetLimit(double totalBdgetLimit) {
        this.totalBdgetLimit = totalBdgetLimit;
    }

    public double getCurentBudget() {
        return curentBudget;
    }

    public void setCurentBudget(double curentBudget) {
        this.curentBudget = curentBudget;
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
