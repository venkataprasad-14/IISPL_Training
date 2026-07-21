package com.iispl.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iispl.enums.AccountStatus;
import com.iispl.model.Account;
import com.iispl.model.CurrentAccount;
import com.iispl.model.Customer;
import com.iispl.model.SavingsAccount;

public class AccountService {
	static Scanner sc = new Scanner(System.in);
	
	/*-------Method to create an account------*/
	public  static	void createAccount(List<Customer> customersList)
	{		
		Customer customer=getCustomer(customersList);
		System.out.println("Which account you want to create ?  1.Savings Account   2.Current Account");
		int choice=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter account number");
		String accountNumber=sc.nextLine();
		if(accountExists(customer, accountNumber))
		{
			System.out.println("Account already exists.");
		}
		else {
			boolean isActive=true;
			System.out.println("Enter the amount to open your account");
			BigDecimal amount=sc.nextBigDecimal();
			Account account = null;
			switch(choice)
			{
			
				case 1://creating savings account
						account=new SavingsAccount(accountNumber, 
									isActive, 
									amount, 
									LocalDate.now());
						break;
			
				case 2://creating current account
						System.out.println("Enter GST Number");
						String gst=sc.next();
						account=new CurrentAccount(accountNumber,
								isActive,
								amount,
								LocalDate.now(),
								gst	);
						break;
				default:
						System.out.println("Invalid choice !!");
							break;
			}
			customer.getAccountsList().add(account);
			System.out.println("Account created succesfully");
		}
	}
	/*---Method to check whether the account is already in the list---*/
	private static boolean accountExists(Customer customer,String accountNumber)
	{
	    for(Account account : customer.getAccountsList())
	    {
	        if(account.getAccountNumber().equalsIgnoreCase(accountNumber))
	        {
	            return true;
	        }
	    }
	    return false;
	}
	/*----method to get the customer------*/
	public static Customer getCustomer(List<Customer> customersList)
	{
		System.out.println("1.new customer  2.existing customer");
		int choice=sc.nextInt();
		sc.nextLine();
		Customer customer=null;
		switch(choice)
		{
				//code for new customer
			case 1:
				customer=CustomerService.createCustomer(customersList);
				customersList.add(customer);
				break;
				
				//code for existing customer 
			case 2:
				System.out.println("Enter customer code");
				String customerCode1=sc.next();
				customer=CustomerService.findCustomer(customersList, customerCode1);
				if(customer==null)//checking if customer is present in list or not
				{
					System.out.println("Customer not found !!!");
					System.out.println("Create customer first");
					customer=CustomerService.createCustomer(customersList);
					customersList.add(customer);
				}
				break;
			default:
	            System.out.println("Invalid Choice");
		}
	    return customer;
	}
	/*-------Method to find account details------*/
	public static Account findAccount(List <Customer> customersList)
	{
		Customer customer=null;
		sc.nextLine();
		System.out.println("Enter customer code ");
		String customerCode=sc.nextLine();
		
		System.out.println("Enter account number");
		String accountNumber=sc.nextLine();
		
		customer=CustomerService.findCustomer(customersList,customerCode);
		if(customer!=null)
		{
			for(Account account:customer.getAccountsList())
			{
				if(account.getAccountNumber().equalsIgnoreCase(accountNumber))
					return account;
			}
		}
		else{
			System.out.println("Please try again by entering correct customer code.");
			//findAccount(customersList);
		}
			
		return null;
	}

	/*-------Method to display account details------*/
	public static void displayAccountDetails(List<Customer> customersList) 
	{
		System.out.println("Enter the customer code ");
		String customerCode=sc.next();
		Customer customer = CustomerService.findCustomer(customersList, customerCode);
		if(customer==null)
			System.out.println("Customer not found");
		else {
				System.out.println("===============================================");
				System.out.println("Customer code: "+customer.getCustomerCode());
				System.out.println("Customer name: "+customer.getCustomerName());
				System.out.println("===============================================");
				System.out.println("                   Accounts                    ");
				/*--forEach() loop to iterate the accounts of customer */
				customer.getAccountsList().forEach(account-> 
				{
					AccountStatus status;
					if(account.isActive())
						status=AccountStatus.ACTIVE;
					else 
					status=AccountStatus.INACTIVE;
					System.out.println("----------------------------------------------");
					System.out.println("Account number : "+account.getAccountNumber()
									+"\nAccount Status : "+ status 
									+"\nBalance : "+account.getBalance());
					if(account instanceof SavingsAccount) {
						System.out.println("Account type: Savings");
						SavingsAccount savingsaccount=(SavingsAccount)account;		
						System.out.println("Rate of interest is :"+SavingsAccount.getRoi());
						System.out.println();
					}else if(account instanceof CurrentAccount)
					{
					System.out.println("Account type: Current");
					CurrentAccount currentaccount=(CurrentAccount)account;
					System.out.println("GST number: "+currentaccount.getGstNumber());
					System.out.println();
					}
				});
			}
	}
	
	/*-------Method to deactivate your account------*/
	public static void deactivateAccount(Account account) {
		account.setActive(false);
		System.out.println("Account Deactivated sucessfully.");
	}

}
