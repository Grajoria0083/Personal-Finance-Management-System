package com.fivancemanagement.demo;

import com.fivancemanagement.demo.exception.CustomerException;
import com.fivancemanagement.demo.exception.TransactionException;
import com.fivancemanagement.demo.model.User;
import com.fivancemanagement.demo.serviceImpl.FinanceManagerServiceImpl;
import com.fivancemanagement.demo.util.Util;

import java.time.LocalDate;
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
			System.out.println("6. Get Saving");
			System.out.println("7. Set Goal");
			System.out.println("8. Get User");
			System.out.println("9. Get Transaction");

			int choice = scanner.nextInt();

			String email;
			String category;

			switch (choice) {

				case 1:
					System.out.print("Enter name: ");
					String name = scanner.next();
					System.out.print("Enter email: ");
					email = scanner.next();
					System.out.print("Enter mobileNo: ");
					String mobile = scanner.next();
					System.out.print("Enter income: ");
					int income = scanner.nextInt();
					String result = financeManager.addUser(new User(name, mobile, email, income));
					System.out.println(result);
					break;
				case 2:
					System.out.print("Enter email: ");
					email = scanner.next();
					System.out.print("Enter category: ");
					category = scanner.next();
					System.out.print("Enter budget limit: ");
					double limit = scanner.nextDouble();
					try {
						financeManager.setBudget(email, category, limit);
					} catch (CustomerException e) {
						System.out.println(e);
					}
					break;
				case 3:
					System.out.print("Enter email: ");
					email = scanner.next();
//					category = util.setCatagory(email);
					System.out.print("Enter category: ");
					category = scanner.next();
					System.out.print("Enter amount: ");
					double amount = scanner.nextDouble();
					String transactionResult = null;
					try {
						transactionResult = financeManager.addTransaction(email, category, amount, LocalDate.now());
					} catch (CustomerException e) {
						System.out.println(e);
					} catch (TransactionException e) {
						System.out.println(e);
					}
					System.out.println(transactionResult);
					break;
				case 4:
					System.out.print("Enter email: ");
					email = scanner.next();
					System.out.print("Enter Starting Date: ");
					LocalDate startDate = LocalDate.parse(scanner.next());
					System.out.print("Enter Ending Date: ");
					LocalDate endDate = LocalDate.parse(scanner.next());
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
						System.out.println("Saving of Last Month : "+financeManager.getSavings(email));
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
				financeManager.setGoal(email, goal, (int) amount);
				case 8:
					financeManager.getUsers();
					break;
				case 9:
					System.out.println(financeManager.getTransactions());
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
			}
		}
	}

}
