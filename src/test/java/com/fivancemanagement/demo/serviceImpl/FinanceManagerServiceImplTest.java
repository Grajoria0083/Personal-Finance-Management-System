package com.fivancemanagement.demo.serviceImpl;

import com.fivancemanagement.demo.exception.CustomerException;
import com.fivancemanagement.demo.exception.TransactionException;
import com.fivancemanagement.demo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FinanceManagerServiceImplTest {

    FinanceManagerServiceImpl financeManagerService;

    @BeforeEach
    void setUp() {
        financeManagerService = new FinanceManagerServiceImpl();

    }


    @Test
    void addUser() {

    String name = "aman";
    String phoneNo = "7859749786";
    String email = "aman@gmail";
    int income = 45000;

    User user = new User(name, phoneNo, email, income);

        String result = financeManagerService.addUser(user);

        assertEquals(result, "user has registerd successfully!");
    }




    @Test
    void addTransaction() throws CustomerException, TransactionException {

        String email = "aman@gmail";
        String catagory = "Shoping";
        double amount = 40;


        assertEquals("Transaction add successfully!",financeManagerService.addTransaction(email, catagory, amount, LocalDate.now()));

        Assertions.assertDoesNotThrow(
                () -> financeManagerService.addTransaction(email, catagory, amount, LocalDate.now()));

        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> {
            financeManagerService.addTransaction("amit@gmail", catagory, amount, LocalDate.now());
        });
        Assertions.assertEquals("invalid id!", customerException.getMessage());

        TransactionException transactionException = Assertions.assertThrows(TransactionException.class, () -> {
            financeManagerService.addTransaction(email, catagory, 2000, LocalDate.now());
        });
        Assertions.assertEquals("buget is crossing it limit for catagory : "+catagory, transactionException.getMessage());


    }

    @Test
    void setBudget() {
    }

    @Test
    void calculateTotalExpenses() {
    }

    @Test
    void generateReport() {
    }

    @Test
    void getSavings() {
    }
}