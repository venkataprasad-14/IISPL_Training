package com.iispl.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public abstract class Account {
	/*-----member variables -----*/
	private String accountNumber;
	private boolean isActive;
	private BigDecimal balance;
	private LocalDate accountCreatedOn;
	private List<Transaction> transactionList;
	
	/*-----constructor to initialise member variables-----*/
	public Account(String accountNumber, boolean isActive, BigDecimal balance, LocalDate accountCreatedOn) {
		this.accountNumber = accountNumber;
		this.isActive = isActive;
		this.balance = balance;
		this.accountCreatedOn = accountCreatedOn;
		transactionList=new ArrayList<>();
	}

	/*-----getters and setters to set and get values  -----*/
	public LocalDate getAccountCreatedOn() {
		return accountCreatedOn;
	}

	public void setAccountCreatedOn(LocalDate accountCreatedOn) {
		this.accountCreatedOn = accountCreatedOn;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public List<Transaction> getTransactionList() {
	    return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
	    this.transactionList = transactionList;
	}
	
}
