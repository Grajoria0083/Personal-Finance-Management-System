package com.fivancemanagement.demo.model;

public class Goal {

    String description;
    int savings;

    public Goal(String description, int savings) {
        this.description = description;
        this.savings = savings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }
}
