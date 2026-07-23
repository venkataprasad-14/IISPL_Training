package com.iispl.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.iispl.enums.TransactionStatus;
import com.iispl.enums.TransactionType;
import com.iispl.model.Account;
import com.iispl.model.Customer;
import com.iispl.model.Transaction;

public class TransactionService {

    private static Scanner sc = new Scanner(System.in);

    /*---------------- CUSTOMER DEPOSIT ----------------*/

    public static void deposit(List<Customer> customersList) {

        Account account = getActiveAccount(customersList);

        if (account == null)
            System.out.println("Account not found !!!");
        else {
	        
	        System.out.println("Enter Deposit Amount : ");
	        BigDecimal amount = sc.nextBigDecimal();
	
	        account.setBalance(account.getBalance().add(amount));
	
	        Transaction transaction = createTransaction(
	                amount,
	                TransactionType.CREDIT,
	                TransactionStatus.SUCCESS,
	                "Amount deposited into your account.");
	
	        account.getTransactionList().add(transaction);
	        System.out.println("Amount Deposited Successfully.");
	        System.out.println("Updated Balance : " + account.getBalance());
        }
    }

    /*---------------- CUSTOMER WITHDRAW ----------------*/

    public static void withdraw(List<Customer> customersList) {

        Account account = getActiveAccount(customersList);

        if (account == null)
        	System.out.println("Account not found !!!");
        else {
	        System.out.println("Enter Withdraw Amount : ");
	        BigDecimal amount = sc.nextBigDecimal();
	
	        Transaction transaction;
	
	        if (account.getBalance().compareTo(amount) >= 0) {
	
	            account.setBalance(account.getBalance().subtract(amount));
	
	            transaction = createTransaction(
	                    amount,
	                    TransactionType.DEBIT,
	                    TransactionStatus.SUCCESS,
	                    "Amount withdrawn from your account.");
	
	            System.out.println("Amount Withdrawn Successfully.");
	            System.out.println("Updated Balance : " + account.getBalance());
	
	        } else {
	
	            transaction = createTransaction(
	                    amount,
	                    TransactionType.DEBIT,
	                    TransactionStatus.FAILLED,
	                    "Withdrawal failed due to insufficient balance.");
	
	            System.out.println("Insufficient Balance.");
	        }
	
	        account.getTransactionList().add(transaction);
       }
    }

    /*---------------- ADMIN TRANSACTION ----------------*/

    public static void createAdminTransaction(List<Customer> customersList) {

        Account account = getActiveAccount(customersList);

        if (account == null)
        	System.out.println("Account not found !!!");
        else {
	        System.out.println("1. Credit");
	        System.out.println("2. Debit");
	        System.out.print("Choose Transaction Type : ");
	        int choice = sc.nextInt();
	
	        System.out.print("Enter Amount : ");
	        BigDecimal amount = sc.nextBigDecimal();
	
	        sc.nextLine(); // Consume newline
	
	        System.out.print("Enter Description : ");
	        String userDescription = sc.nextLine();
	
	        Transaction transaction;
	
	        switch (choice) {
	
	        case 1:
	
	            account.setBalance(account.getBalance().add(amount));
	
	            transaction = createTransaction(
	                    amount,
	                    TransactionType.CREDIT,
	                    TransactionStatus.SUCCESS,
	                    userDescription + " credited to your account.");
	
	            account.getTransactionList().add(transaction);
	
	            System.out.println("Amount Credited Successfully.");
	            System.out.println("Updated Balance : " + account.getBalance());
	
	            break;
	
	        case 2:
	
	            if (account.getBalance().compareTo(amount) >= 0) {
	
	                account.setBalance(account.getBalance().subtract(amount));
	
	                transaction = createTransaction(
	                        amount,
	                        TransactionType.DEBIT,
	                        TransactionStatus.SUCCESS,
	                        "Money debited for " + userDescription + ".");
	
	                System.out.println("Amount Debited Successfully.");
	                System.out.println("Updated Balance : " + account.getBalance());
	
	            } else {
	
	                transaction = createTransaction(
	                        amount,
	                        TransactionType.DEBIT,
	                        TransactionStatus.FAILLED,
	                        "Failed to debit amount for " + userDescription + ".");
	
	                System.out.println("Insufficient Balance.");
	            }
	
	            account.getTransactionList().add(transaction);
	            break;
	        default:
	        	System.out.println("Invalid Choice.");
	        }
        }
    }

    /*---------------- VIEW TRANSACTIONS ----------------*/

    public static void viewTransactions(List<Customer> customersList) {

        Account account = AccountService.findAccount(customersList);

        if (account == null) {
            System.out.println("Account Not Found.");
        }

        List<Transaction> transactions = account.getTransactionList();

        if (transactions.isEmpty()) {
            System.out.println("No Transactions Found.");
        }

        System.out.println("\n============== TRANSACTION HISTORY ==============");

        for (Transaction transaction : transactions) {

            System.out.println("-------------------------------------------");
            System.out.println("Transaction ID : " + transaction.getTransactionId());
            System.out.println("Amount         : " + transaction.getAmount());
            System.out.println("Type           : " + transaction.getTransactionType());
            System.out.println("Status         : " + transaction.getTransactionStatus());
            System.out.println("Date           : " + transaction.getTransactionDate());
            System.out.println("Description    : " + transaction.getDescription());
        }

        System.out.println("-------------------------------------------");
    }

    /*--------------Helper method to get the active account ----------------*/

    private static Account getActiveAccount(List<Customer> customersList) {

        Account account = AccountService.findAccount(customersList);

        if (account == null) {
          
            return null;
        }

        if (!account.isActive()) {
            System.out.println("Account is Deactivated.");
            return null;
        }

        return account;
    }

    /*---------------- CREATE TRANSACTION ----------------*/

   public static Transaction createTransaction(
            BigDecimal amount,
            TransactionType transactionType,
            TransactionStatus transactionStatus,
            String description) {

        String transactionId = "TXN"
                + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        return new Transaction(
                transactionId,
                amount,
                transactionStatus,
                transactionType,
                LocalDate.now(),
                description);
    }

}