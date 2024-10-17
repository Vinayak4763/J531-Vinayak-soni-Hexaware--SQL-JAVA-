package com.hexaware.ticketbooking.entity;

public class Customer {
    private String customerName;
    private String email;
    private String phoneNumber;
    private int customerId;
    // Default Constructor
    public Customer() {super();}

    // Parameterized Constructor
    public Customer(String customerName, String email, String phoneNumber,int customerId) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerId=customerId;
    }

    // Getter and Setter methods
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

    // Display method
    public void displayCustomerDetails() {
        System.out.println("Customer Name: " + customerName + ", Email: " + email +
                           ", Phone Number: " + phoneNumber);
    }
}