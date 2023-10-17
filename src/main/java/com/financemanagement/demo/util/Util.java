package com.financemanagement.demo.util;

import com.financemanagement.demo.serviceImpl.FinanceManagerServiceImpl;
import com.financemanagement.demo.model.Budget;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Util {

    FinanceManagerServiceImpl financeManagerService = new FinanceManagerServiceImpl();

    Scanner scanner = new Scanner(System.in);


    public List<LocalDate> getDateByvalue(int day){

        LocalDate endDate  = LocalDate.now();
        LocalDate startDate = endDate.minusDays(day);
        return Arrays.asList(startDate, endDate);
    }

    public List<LocalDate> getLastWeek(){

        LocalDate currentDate = LocalDate.now();
        LocalDate lastWeekStartDate = currentDate.minusWeeks(7);
        LocalDate lastWeekEndDate = currentDate.minusWeeks(1);
        System.out.println("lastWeekEndDate "+lastWeekEndDate);
        return Arrays.asList(lastWeekStartDate, lastWeekEndDate);
    }

    public List<LocalDate> getLastMonth(){

        LocalDate currentDate = LocalDate.now();
        YearMonth lastMonth = YearMonth.from(currentDate).minusMonths(1);
        LocalDate lastMonthStartDate = lastMonth.atDay(1);
        LocalDate lastMonthEndDate = lastMonthStartDate.plusMonths(1).minusDays(1);
        return Arrays.asList(lastMonthStartDate, lastMonthEndDate);
    }


    public List<LocalDate> getLastYear(){

        LocalDate currentDate = LocalDate.now();
        Year lastYear = Year.from(currentDate).minusYears(1);
        LocalDate lastYearStartDate = LocalDate.of(lastYear.getValue(), 1, 1);
        LocalDate lastYearEndDate = LocalDate.of(lastYear.getValue(), 12, 31);
        return Arrays.asList(lastYearStartDate, lastYearEndDate);
    }


    public String selectCatagory(){

        System.out.print("Enter category: ");
        System.out.print("1. Shoping: ");
        System.out.print("2. Traveling: ");
        System.out.print("3. Eating: ");
        System.out.print("4. Rent: ");
        System.out.print("5. other_Expenses: ");

        int i = scanner.nextInt();

        switch (i){
            case 1:
                return "Shoping";
            case 2:
                return "Traveling";
            case 3:
                return "Eating";
            case 4:
                return "Rent";
            case 5:
                return "other_Expenses";
            default:
                return "invelid input";
        }

    }




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
