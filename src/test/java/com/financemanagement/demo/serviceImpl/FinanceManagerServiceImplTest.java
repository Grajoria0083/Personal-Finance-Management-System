package com.financemanagement.demo.serviceImpl;

import com.financemanagement.demo.exception.CustomerException;
import com.financemanagement.demo.exception.TransactionException;
import com.financemanagement.demo.model.User;
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
    void calculateTotalExpenses() throws CustomerException {

        String email = "aman@gmail";
        LocalDate startDate1 = LocalDate.of(2023, 9,01);
        LocalDate startDate2 = LocalDate.of(2023, 10,01);
        LocalDate endDate = LocalDate.now();



        assertEquals(3700,financeManagerService.calculateTotalExpenses(email, startDate1, endDate));
        assertEquals(1400,financeManagerService.calculateTotalExpenses(email, startDate2, endDate));

        Assertions.assertDoesNotThrow(
                () -> financeManagerService.calculateTotalExpenses(email, startDate1, endDate));

        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> {
            financeManagerService.calculateTotalExpenses("amit@gmail", startDate1, endDate);
        });
        Assertions.assertEquals("invalid id!", customerException.getMessage());

    }


    @Test
    void setBudget() throws CustomerException{

        String email = "aman@gmail";
        String category = "Shoping";
        double limit = 500;

        String email2 = "amit@gmail";
        String category2 = "Traveling";
        double limit2 = 700;

        assertEquals("Budget is getting over to income. Avalible Budget is : 0.0", financeManagerService.setBudget(email, category, limit));

        Assertions.assertDoesNotThrow(
                () -> financeManagerService.setBudget(email, category, limit))
        ;
                        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> {
            financeManagerService.setBudget(email2, category2, limit2);
        });

    }
}