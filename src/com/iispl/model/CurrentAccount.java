package com.iispl.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrentAccount extends Account{
	/*-----member variables -----*/
	private String gstNumber;
	
	/*-----constructor to initialise member variables-----*/
	public CurrentAccount(String accountNumber, boolean isActive, BigDecimal balance, LocalDate accountCreatedOn,
			String gstNumber) {
		super(accountNumber, isActive, balance, accountCreatedOn);
		this.gstNumber = gstNumber;
	}

	/*-----getters and setters to set and get values  -----*/
	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	
}
