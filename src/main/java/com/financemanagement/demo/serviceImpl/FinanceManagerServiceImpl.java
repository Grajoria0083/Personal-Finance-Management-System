package com.financemanagement.demo.serviceImpl;

import com.financemanagement.demo.exception.TransactionException;
import com.financemanagement.demo.model.Budget;
import com.financemanagement.demo.model.Goal;
import com.financemanagement.demo.model.Transaction;
import com.financemanagement.demo.model.User;
import com.financemanagement.demo.service.FinanceManagerService;
import com.financemanagement.demo.exception.CustomerException;
import com.financemanagement.demo.util.Util;


import java.time.LocalDate;
import java.util.*;

public class FinanceManagerServiceImpl implements FinanceManagerService {


    Map<String, List<Transaction>> userTransactionMap = new HashMap<>();
    Map<String, List<Budget>> userBudgetMap = new HashMap<>();
    Map<String, Goal> userGoalMap = new HashMap<>();
    List<User> users = new ArrayList<>();
    Budget budgetObj = new Budget();


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
        transaction.add(new Transaction("Shoping",1200, LocalDate.of(2023, 10, 07)));
        transaction.add(new Transaction("Travel",1500, LocalDate.of(2023, 9, 12)));
        transaction.add(new Transaction("Shoping",200, LocalDate.of(2023, 10, 6)));
        transaction.add(new Transaction("Eating",800, LocalDate.of(2023, 9, 15)));

        userTransactionMap.put("aman@gmail",transaction);

    }

    public Map<String, List<Budget>> getUserBudgetMap() {
        return userBudgetMap;
    }

    @Override
    public String addUser(User user) throws InputMismatchException {

        try {
            users.add(user);
            userTransactionMap.put(user.getEmail(), new ArrayList<Transaction>());
            userBudgetMap.put(user.getEmail(), new ArrayList<Budget>());
            budgetObj.setTotalBdgetLimit(user.getIncome());

            return "user has registerd successfully!";
        }
        catch (InputMismatchException exception){
            throw new InputMismatchException("Please enter value correctly!");
        }
    }

    @Override
    public String addTransaction(String email, String category, double amount, LocalDate localDate) throws CustomerException, TransactionException {

        if (!userTransactionMap.isEmpty() && userTransactionMap.containsKey(email)){

            List<Transaction> transactions = userTransactionMap.get(email);
            List<Budget> budgetList = userBudgetMap.get(email);

            boolean flag = false;
            for (Budget budget:budgetList){
                if (budget.getCategory().equals(category)){
                    flag = true;
                    if (amount>budget.getLimit()){
                        throw new TransactionException("buget is crossing it limit for catagory : "+category);
                    }
                    else {
                        if (!userGoalMap.isEmpty() && userGoalMap.get(email).getDescription().equals(category)){
                            System.out.println("you have set a Goal for : "+category);
                            System.out.println("Ener 1 for continou, and 2 for cancel the Transaction");
                            Scanner scanner = new Scanner(System.in);
                            int choice = scanner.nextInt();
                            if (choice==2){
                                return "Thanks for saving money for your goal";
                            }
                        }
                        budget.setLimit(budget.getLimit()-amount);
                    }
                }
            }
            if (flag){
                transactions.add(new Transaction(category, amount, localDate));
                userTransactionMap.put(email, transactions);
                return "Transaction add successfully!";
            }
            else {
                throw new CustomerException("invalid Budget Catagory!");
            }
        }
        else {
            throw new CustomerException("invalid id!");
        }
    }

    @Override
    public String setBudget(String email, String category, double limit) throws CustomerException {

        if (!userBudgetMap.isEmpty() && userBudgetMap.containsKey(email)){

            List<Budget> budget = userBudgetMap.get(email);

            if (budgetObj.getCurentBudget()+limit<=budgetObj.getTotalBdgetLimit()){
                budgetObj.setCurentBudget(budgetObj.getCurentBudget()+limit);
                budget.add(new Budget(category, limit));
                userBudgetMap.put(email, budget);
                return "Budget set successfully!";
            }
            else {
                return "Budget is getting over to income. Avalible Budget is : "+(budgetObj.getTotalBdgetLimit()- budgetObj.getCurentBudget());
            }
        }
        else {
            throw new CustomerException("invalid id!");
        }
    }

    @Override
    public double calculateTotalExpenses(String email, LocalDate startDate, LocalDate endDate) throws CustomerException {

        if ( userTransactionMap.containsKey(email)) {

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
        else {
            throw new CustomerException("invalid id!");
        }
    }

    @Override
    public double getSavings (String email) throws CustomerException {

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.withDayOfMonth(1);

            double totalExpenses;

            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    totalExpenses = calculateTotalExpenses(email, startDate, endDate);
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
    public void setGoal(String email, String des, int amount) throws CustomerException {

        if (userTransactionMap.containsKey(email)){
            if (userGoalMap.containsKey(email)){
                if (userGoalMap.get(email).getDescription().equals(des))
                    userGoalMap.put(email, new Goal(des, userGoalMap.get(email).getSavings()+amount));
            }
            else
                userGoalMap.put(email, new Goal(des, amount));
            System.out.println(userGoalMap.get(email).toString());
                System.out.println("Goal add Successfully!");
        }
        else
            throw new CustomerException("invalid Id!");
    }

    public List<Transaction> getTransactionList (String email) throws CustomerException {

        if (userTransactionMap.containsKey(email)) {

            return userTransactionMap.get(email);
        }

        throw new CustomerException("invalid Id!");
    }

    public List<Budget> getBudgetList(String email) throws CustomerException {

            if (userBudgetMap.containsKey(email)) {

                return userBudgetMap.get(email);
            }

            throw new CustomerException("Invalid id");
        }



    @Override
    public Map<String, List<Transaction>> getTransactions () {
            return userTransactionMap;
        }


    public Map<String, List<Budget>> getBudgets () {
        return userBudgetMap;
    }
}
