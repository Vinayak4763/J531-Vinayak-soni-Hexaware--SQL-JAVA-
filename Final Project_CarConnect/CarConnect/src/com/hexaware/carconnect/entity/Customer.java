/*
 * Author: J531 Vinayak Soni 
 * Date: 10/11/24
 * Description: Customer model
 */

package com.hexaware.carconnect.entity;


public class Customer {
	private int customerId;
	private  String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String username;
	private String password;
	private String registrationDate;
	
	
	public Customer() {
		super();
		
	}

	
	
	public Customer(int customerId, String firstName, String lastName, String email, String phoneNumber, String address,
			String username, String password, String registrationDate) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
	}



	

	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRegistrationDate() {
		return registrationDate;
	}



	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}



	@Override
	public String toString() {
		return String.format("Customer [firstName=%s, lastName=%s, email=%s]", firstName, lastName, email);
	}
	// Method to authenticate the user
	public boolean authenticate(String inputPassword) {
	    return this.password.equals(inputPassword);
	}
	
}
