package com.iispl.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	/*-----member variables -----*/
	private String customerCode;
	private String customerName;
	private List<Account> accountsList;
	
	/*-----constructor to initialise member variables-----*/
	public Customer(String customerCode, String customerName) {
		this.customerCode = customerCode;
		this.customerName = customerName;
		accountsList=new ArrayList<>();
	}

	/*-----getters and setters to set and get values  -----*/
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Account> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(List<Account> accountsList) {
		this.accountsList = accountsList;
	}
	
	
}