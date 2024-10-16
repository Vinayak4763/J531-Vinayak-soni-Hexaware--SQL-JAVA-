package com.hexaware.ticketbooking.entity;

public class Customer {
    private String customerName;
    private String email;
    private String phoneNumber;

    // Default Constructor
    public Customer() {super();}

    // Parameterized Constructor
    public Customer(String customerName, String email, String phoneNumber) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter methods
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // Display method
    public void displayCustomerDetails() {
        System.out.println("Customer Name: " + customerName + ", Email: " + email +
                           ", Phone Number: " + phoneNumber);
    }
}