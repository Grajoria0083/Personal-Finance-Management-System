package com.fivance_management.demo.serviceImpl;

import com.fivance_management.demo.exception.CustomerException;
import com.fivance_management.demo.exception.TransactionException;
import com.fivance_management.demo.model.*;
import com.fivance_management.demo.service.FinanceManagerService;

import java.time.LocalDate;
import java.util.*;

public class FinanceManagerServiceImpl implements FinanceManagerService {


    Map<String, List<Transaction>> userTransactionMap = new HashMap<>();
    Map<String, List<Budget>> userBudgetMap = new HashMap<>();

    Map<String, Goal> userGoalMap = new HashMap<>();
    List<User> users = new ArrayList<>();
    List<Report> reports = new ArrayList<>();
    List<Budget> budgets = new ArrayList<>();


    public FinanceManagerServiceImpl() {

        users.addAll(Arrays.asList(new User("Aman","7859749786","aman@gmail",45000),
                new User("Gaurav","7017233221","gaurav@gmail",18000),
                new User("Rahul","8596743567","rahul@gmail",20000)
                ));

        List<Budget> buget = new ArrayList<>();
        buget.add(new Budget("Shoping",2000));
        buget.add(new Budget("Eating",700));
        buget.add(new Budget("Traveling",500));
        userBudgetMap.put("aman@gmail", buget);

        List<Transaction> transaction = new ArrayList<>();
        transaction.add(new Transaction("Shoping",1200, LocalDate.of(2023, 10, 10)));
        transaction.add(new Transaction("Travel",1500, LocalDate.of(2023, 10, 12)));
        transaction.add(new Transaction("Shoping",200, LocalDate.of(2023, 10, 11)));
        transaction.add(new Transaction("Eating",800, LocalDate.of(2023, 10, 15)));

        userTransactionMap.put("aman@gmail",transaction);

    }

    public Map<String, List<Budget>> getUserBudgetMap() {
        return userBudgetMap;
    }

    @Override
    public String addUser(User user) {

        users.add(user);

        userTransactionMap.put(user.getEmail(), new ArrayList<Transaction>());
        userBudgetMap.put(user.getEmail(), new ArrayList<Budget>());

        return "user has registerd successfully!";
    }

    @Override
    public String addTransaction(String email, String category, double amount, LocalDate localDate) throws CustomerException, TransactionException {

        if (userTransactionMap.containsKey(email)){

            List<Transaction> transactions = userTransactionMap.get(email);
            List<Budget> budgets = userBudgetMap.get(email);

            int totalExpenses = 0;
            for (Transaction transaction:transactions){
                if (transaction.getCategory().equals(category))
                    totalExpenses += transaction.getAmount();
            }

            for (Budget budget:budgets){
                if (budget.getCategory().equals(category)){
                    totalExpenses += amount;
                    if (totalExpenses>budget.getLimit()){
                        throw new TransactionException("buget is crossing it limit for catagory : "+category);
                    }
                }
            }
            transactions.add(new Transaction(category, amount, localDate));

            userTransactionMap.put(email, transactions);

            return "Transaction add successfully!";
        }
        else {
            throw new CustomerException("invalid id!");
        }
    }

    @Override
    public void setBudget(String email, String category, double limit) throws CustomerException {

        if (userBudgetMap.containsKey(email)){

            List<Budget> budgets = userBudgetMap.get(email);

            budgets.add(new Budget(category, limit));

            userBudgetMap.put(email, budgets);
        }
        else {
            throw new CustomerException("invalid id!");
        }
    }

    @Override
    public double calculateTotalExpenses(String email, LocalDate startDate, LocalDate endDate) throws CustomerException {

        if (userTransactionMap.containsKey(email)) {

            List<Transaction> transactions = userTransactionMap.get(email);

            int totalExpenses = 0;
            for (Transaction transaction : transactions) {
                if (transaction.getLocalDate().compareTo(startDate) >= 0 && transaction.getLocalDate().compareTo(endDate) <= 0) {
                    totalExpenses += transaction.getAmount();
                }
            }
            return totalExpenses;
        }
        throw new CustomerException("invalid id!");
    }


    @Override
    public void generateReport(String email, LocalDate startDate, LocalDate endDate) throws CustomerException {

        if (userTransactionMap.containsKey(email)) {

            List<Transaction> transactions = userTransactionMap.get(email);

            for (Transaction transaction : transactions) {
                if (transaction.getLocalDate().compareTo(startDate) >= 0 && transaction.getLocalDate().compareTo(endDate) <= 0) {
                    System.out.println(transaction);
                }
            }
        }
    }

        @Override
        public double getSavings (String email) throws CustomerException {

            LocalDate startDate = LocalDate.of(2023, 10, 01);
            LocalDate endTate = LocalDate.of(2023, 10, 30);

            double totalExpenses;

            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    totalExpenses = calculateTotalExpenses(email, startDate, endTate);
                    return user.getIncome() - totalExpenses;
                }
            }
            throw new CustomerException("invalid id!");
        }

        @Override
        public void getUsers () {

            for (User u : users)
                System.out.println(u);
        }

    @Override
    public void setGoal(String email, String des, int amount) {

        userGoalMap.put(email, new Goal(des, amount));

    }

    @Override
        public Map<String, List<Transaction>> getTransactions () {
            return userTransactionMap;
        }
}
