package com.iispl.service;

import java.util.List;
import java.util.Scanner;

import com.iispl.model.Customer;

public class CustomerService {
	
	 private static Scanner sc = new Scanner(System.in);
	private static Customer customer;

	/*-------method to find the customer---------*/
	public static Customer findCustomer(List<Customer> customersList, String customerCode) {
		for (Customer customer : customersList) {
			if (customer.getCustomerCode().equalsIgnoreCase(customerCode)) {
				return customer;
			}
		}
		return null;
	}
	/*-------method to create the customer---------*/
	public static Customer createCustomer(List<Customer> customersList) {
		
		System.out.println("Enter customer code");
		String customerCode = sc.nextLine();
		Customer customer = findCustomer(customersList, customerCode);

		if (customer != null) {
			System.out.println("Customer already exists.");
			return customer;
		}

		System.out.println("Enter Customer Name");
		String customerName = sc.nextLine();

		customer = new Customer(customerCode, customerName);

		System.out.println("Customer created successfully.");

		return customer;
	}
	
}
