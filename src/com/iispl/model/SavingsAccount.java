package com.iispl.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SavingsAccount extends Account{
	/*-----member variables -----*/
	private static final String roi="6%";
	
	/*-----constructor to initialise member variables-----*/
	public SavingsAccount(String accountNumber, boolean isActive, BigDecimal balance, LocalDate accountCreatedOn) {
		super(accountNumber, isActive, balance, accountCreatedOn);	
	}

	/*-----getters to get value of roi -----*/
	public static String getRoi() {
		return roi;
	}
	
	
}
