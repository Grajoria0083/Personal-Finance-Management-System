package com.financemanagement.demo.model;

public class User {

    String name;
    String phoneNo;
    String email;
    int income;

    public User(String name, String phoneNo, String email, int income) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.income = income;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", income=" + income +
                '}';
    }
}
