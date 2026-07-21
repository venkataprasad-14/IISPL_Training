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

    /*----------method to Deposit Amount --------------*/
    public static void deposit(List<Customer> customersList) {

        Account account = getActiveAccount(customersList);

        if (account == null)
            return;

        System.out.println("Enter Deposit Amount");
        BigDecimal amount = sc.nextBigDecimal();

        //to Update Balance
        account.setBalance(account.getBalance().add(amount));

        // Creating a Transaction
        Transaction transaction = createTransaction(
                amount,
                TransactionType.CREDIT,
                TransactionStatus.SUCCESS);

        // Save Transaction
        account.getTransactionList().add(transaction);

        System.out.println("\nAmount Deposited Successfully.");
        System.out.println("Updated Balance : " + account.getBalance());
    }

    /*---------Method to Withdraw Amount --------------*/
    public static void withdraw(List<Customer> customersList) {

        Account account = getActiveAccount(customersList);

        if (account == null)
            return;

        System.out.println("Enter Withdraw Amount");
        BigDecimal amount = sc.nextBigDecimal();

        Transaction transaction;

        if (account.getBalance().compareTo(amount) >= 0) {

            // Update Balance
            account.setBalance(account.getBalance().subtract(amount));

            transaction = createTransaction(
                    amount,
                    TransactionType.DEBIT,
                    TransactionStatus.SUCCESS);

            System.out.println("\nAmount Withdrawn Successfully.");
            System.out.println("Updated Balance : " + account.getBalance());

        } else {

            transaction = createTransaction(
                    amount,
                    TransactionType.DEBIT,
                    TransactionStatus.FAILLED);

            System.out.println("Insufficient Balance.");
        }

        // Save Transaction
        account.getTransactionList().add(transaction);
    }

    /*-------------method to  View list of Transactions ----------------*/
    public static void viewTransactions(List<Customer> customersList) {

        Account account = AccountService.findAccount(customersList);

        if (account == null) {
            System.out.println("Account Not Found.");
            return;
        }

        List<Transaction> transactions = account.getTransactionList();

        if (transactions.isEmpty()) {
            System.out.println("No Transactions Available.");
            return;
        }

        System.out.println("\n============== TRANSACTIONS ==============");

        for (Transaction transaction : transactions) {

            System.out.println("-----------------------------------------");
            System.out.println("Transaction ID     : " + transaction.getTransactionId());
            System.out.println("Amount             : " + transaction.getAmount());
            System.out.println("Type               : " + transaction.getTransactionType());
            System.out.println("Status             : " + transaction.getTransactionStatus());
            System.out.println("Date               : " + transaction.getTransactionDate());
        }

        System.out.println("-----------------------------------------");
    }

    /*-----------this is a helper Method ----------------*/
    private static Account getActiveAccount(List<Customer> customersList) {

        Account account = AccountService.findAccount(customersList);

        if (account == null) {
            System.out.println("Account Not Found.");
            return null;
        }

        if (!account.isActive()) {
            System.out.println("Account is Deactivated.");
            return null;
        }

        return account;
    }

    /*---------------- Create Transaction ----------------*/
    private static Transaction createTransaction(
            BigDecimal amount,
            TransactionType transactionType,
            TransactionStatus transactionStatus) {

        String transactionId = "TXN"
                + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        return new Transaction(
                transactionId,
                amount,
                transactionStatus,
                transactionType,
                LocalDate.now());
    }

}