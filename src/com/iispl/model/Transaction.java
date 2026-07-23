package com.iispl.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.iispl.enums.TransactionStatus;
import com.iispl.enums.TransactionType;

public class Transaction {
	/*-----member variables -----*/
	private String transactionId;
	private BigDecimal amount;
	private TransactionStatus transactionStatus;
	private TransactionType transactionType;
	private LocalDate transactionDate;
	private String description;
	
	/*-----getters and setters to set and get values  -----*/
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*-----constructor to initialise member variables-----*/
	public Transaction(String transactionId, BigDecimal amount, TransactionStatus transactionStatus,
			TransactionType transactionType, LocalDate transactionDate, String description) {
		
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionStatus = transactionStatus;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.description = description;
	}
	
	
	
}
