package com.fivancemanagement.demo.util;

import com.fivancemanagement.demo.model.Budget;
import com.fivancemanagement.demo.serviceImpl.FinanceManagerServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Util {

    FinanceManagerServiceImpl financeManagerService = new FinanceManagerServiceImpl();



    Scanner scanner = new Scanner(System.in);

    public String setCatagory(String email){

        Map<String, List<Budget>> budgetMap = financeManagerService.getUserBudgetMap();

        if (budgetMap.containsKey(email)) {

            List<Budget> budgets = budgetMap.get(email);

            int i = 0;
            for (Budget b:budgets) {
                i++;
                System.out.println(i+". "+b.getCategory());
            }
            int n = scanner.nextInt();
            return budgets.get(n-1).getCategory();
        }
        return "invalid email id";
    }
}
