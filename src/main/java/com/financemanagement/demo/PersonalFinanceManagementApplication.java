package com.financemanagement.demo;

import com.financemanagement.demo.exception.TransactionException;
import com.financemanagement.demo.exception.CustomerException;
import com.financemanagement.demo.model.User;
import com.financemanagement.demo.serviceImpl.FinanceManagerServiceImpl;
import com.financemanagement.demo.util.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class PersonalFinanceManagementApplication {

	public static void main(String[] args) {

		FinanceManagerServiceImpl financeManager = new FinanceManagerServiceImpl();
		Util util = new Util();
		Scanner scanner = new Scanner(System.in);



		while (true) {
			System.out.println("Select an option:");
			System.out.println("1. Resister User");
			System.out.println("2. Set Budget");
			System.out.println("3. Add Transaction");
			System.out.println("4. Generate Report");
			System.out.println("5. Calculate Total Expenses");
			System.out.println("6. Check Saving");
			System.out.println("7. Set Goal");
			System.out.println("8. Get User");
			System.out.println("9. Get Transaction");
			System.out.println("10. Get Budget");

			int choice = scanner.nextInt();

			String email;
			String category;
			LocalDate startDate;
			LocalDate endDate;
			List<LocalDate> localDates = new ArrayList<>();

			switch (choice) {

				case 1:
					try {
						System.out.print("Enter name: ");
						String name = scanner.next();
						System.out.print("Enter email: ");
						email = scanner.next();
						System.out.print("Enter mobileNo: ");
						String mobile = scanner.next();
						System.out.print("Enter income: ");
						var income = scanner.nextInt();
//						if (income instanceof Integer){
//
//						}
						String result = financeManager.addUser(new User(name, mobile, email, income));
						System.out.println(result);
					}catch (InputMismatchException exception){
						System.out.println(exception.getMessage());
					}
					catch (Exception exception){
						System.out.println(exception.getMessage());
					}

					break;
				case 2:
					System.out.print("Enter email: ");
					email = scanner.next();
					category = util.selectCatagory();
					System.out.print("Enter budget limit: ");
					double limit = scanner.nextDouble();
					try {
						System.out.println(financeManager.setBudget(email, category, limit));
					} catch (CustomerException e) {
						System.out.println(e);
					}
					break;
				case 3:
					System.out.print("Enter email: ");
					email = scanner.next();
					System.out.print("Enter category: ");
//					category = util.setCatagory(email);
					category = scanner.next();
					System.out.print("Enter amount: ");
					double amount = scanner.nextDouble();
					String transactionResult = null;
					try {
						transactionResult = financeManager.addTransaction(email, category, amount, LocalDate.now());
						System.out.println(transactionResult);
					} catch (CustomerException e) {
						System.out.println(e);
					} catch (TransactionException e) {
						System.out.println(e);
					}
					break;
				case 4:
					System.out.print("Enter email: ");
					email = scanner.next();
					System.out.print("1. Fixed Duration: ");
					System.out.print("2. Custume Duration : ");
					choice = scanner.nextInt();
					if (choice==1){
						System.out.print("1. Weekly Report : ");
						System.out.print("2. Monthly Report: ");
						System.out.print("3. Yearly Report : ");
						choice = scanner.nextInt();
						if (choice==1){
							localDates = util.getDateByvalue(7);
						}
						else if (choice==2){
							localDates = util.getDateByvalue(30);
						}
						else {
							localDates = util.getDateByvalue(365);
						}
						startDate = localDates.get(0);
						endDate = localDates.get(1);
					}else {
						System.out.print("Enter Starting Date: ");
						startDate = LocalDate.parse(scanner.next());
						System.out.print("Enter Ending Date: ");
						endDate = LocalDate.parse(scanner.next());
					}
					try {
						financeManager.generateReport(email, startDate, endDate);
					} catch (CustomerException e) {
						System.out.println(e);
					}
					break;
				case 5:
					System.out.print("Enter email: ");
					email = scanner.next();
					System.out.print("Enter Starting Date: ");
					startDate = LocalDate.parse(scanner.next());
					System.out.print("Enter Ending Date: ");
					endDate = LocalDate.parse(scanner.next());
					Double totalExpensesResult = null;
					try {
						totalExpensesResult = financeManager.calculateTotalExpenses(email, startDate, endDate);
					} catch (CustomerException e) {
						System.out.println(e);
					}
					System.out.println("Total Expenses : "+totalExpensesResult);
					break;
				case 6:
					System.out.print("Enter email: ");
					email = scanner.next();
					try {
						System.out.println("Saving of This Month : "+financeManager.getSavings(email));
					} catch (CustomerException e) {
						System.out.println(e);
					}
					break;
				case 7:
				System.out.print("Enter email: ");
				email = scanner.next();
				System.out.print("Enter Gaol: ");
				String goal = scanner.next();
				System.out.print("Enter amount: ");
				amount = scanner.nextInt();
					try {
						financeManager.setGoal(email, goal, (int) amount);
					} catch (CustomerException e) {
						System.out.println(e.toString());
					}
					break;
				case 8:
					financeManager.getUsers();
					break;
				case 9:
					System.out.println(financeManager.getTransactions());
					break;
				case 10:
					System.out.println(financeManager.getBudgets());
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
			}
		}
	}

}
