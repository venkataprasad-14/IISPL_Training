package com.iispl.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iispl.model.Account;
import com.iispl.model.Customer;
import com.iispl.service.AccountService;
import com.iispl.service.TransactionService;

public class BankApplication {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)
	{
		List<Customer> customersList = new ArrayList<>();
		
		String goToMainMenu;
		do {
			System.out.println("Are you a customer or Admin(c/a)");
			char person=sc.next().charAt(0);
			sc.nextLine();
			if(person=='a' || person=='A')
			{
			/*------------ADMIN MENU------------*/
				System.out.println("Enter your choice");
				System.out.println("1.Create Account "
						+ " 2.Display Accounts "
						+ " 3.Deactivate Account "
						+ " 4.Create Admin Transaction "
						+ " 5.View Transactions ");
				
				int adminChoice = sc.nextInt();
				
				switch (adminChoice) 
				{
					case 1:
						AccountService.createAccount(customersList);
						break;
					case 2:
						AccountService.displayAccountDetails(customersList);
						break;
					case 3:
						Account account1= AccountService.findAccount(customersList); 
						if(account1 !=null)
							{
								AccountService.deactivateAccount(account1);
							}
							else
								System.out.println("Account not found");
						
							break;
					case 4:
						TransactionService.createAdminTransaction(customersList);
						break;
					case 5:
						TransactionService.viewTransactions(customersList);
						break;
					default:
						System.out.println("Invalid choice");
				}	
			}
			else if(person=='c' || person=='C')
			{
				/*------------CUSTOMER MENU------------*/
				System.out.println("Enter your choice");
				System.out.println("1.Display Account Details "
								+" 2.Deposit Amount "
								+" 3.Withdraw Amount "
								+" 4.View Transactions ");
				
				int choice = sc.nextInt();
				switch (choice) {
					case 1:
						AccountService.displayAccountDetails(customersList);
						break;
					case 2:
						 TransactionService.deposit(customersList);
						break;
						
					case 3:
						TransactionService.withdraw(customersList);
						break;
					case 4:
	                    TransactionService.viewTransactions(customersList);
	                    break;

					default:
						System.out.println("Invalid choice");
				}
			}
			else {
				System.out.println("Invalid user");
			}
			System.out.println("Do you want to go main menu(y/n)");
			goToMainMenu = sc.next();
		}while(goToMainMenu.equalsIgnoreCase("y"));

	}

}
