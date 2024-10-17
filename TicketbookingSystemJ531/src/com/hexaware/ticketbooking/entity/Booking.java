package com.hexaware.ticketbooking.entity;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private Customer customer; // Changed from customerID to customer
    private Event event; // Changed from eventID to event
    private int numOfTickets;
    private double totalCost;
    private LocalDate bookingDate;

    // Default constructor
    public Booking() {
        super();
    }

    // Parameterized constructor
    public Booking(int bookingId, Customer customer, Event event, int numOfTickets, double totalCost, LocalDate bookingDate) {
        super();
        this.bookingId = bookingId;
        this.customer = customer; // Assigning the Customer object
        this.event = event; // Assigning the Event object
        this.numOfTickets = numOfTickets;
        this.totalCost = totalCost;
        this.bookingDate = bookingDate;
    }

    // Getters and setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() { // Updated getter
        return customer;
    }

    public void setCustomer(Customer customer) { // Updated setter
        this.customer = customer;
    }

    public Event getEvent() { // Updated getter
        return event;
    }

    public void setEvent(Event event) { // Updated setter
        this.event = event;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}

