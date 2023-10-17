package com.fivancemanagement.demo.service;

import com.fivancemanagement.demo.exception.CustomerException;
import com.fivancemanagement.demo.exception.TransactionException;
import com.fivancemanagement.demo.model.Transaction;
import com.fivancemanagement.demo.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FinanceManagerService {

    String addUser(User user);

    String addTransaction(String email, String category, double amount, LocalDate localDate) throws CustomerException, TransactionException;

    public String setBudget(String email, String category, double limit) throws CustomerException;

    public double calculateTotalExpenses(String email, LocalDate startDate, LocalDate endDate) throws CustomerException;

    public void generateReport(String email, LocalDate startDate, LocalDate endDate) throws CustomerException ;

    public double getSavings(String email) throws CustomerException;

    public void getUsers();

    public void setGoal(String email, String des, int amount) throws CustomerException;

    public Map<String, List<Transaction>> getTransactions();
}
