package com.hexaware.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {
    private String eventName;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private Venue venue; // Reference to the Venue class
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private String eventType; // Movie, Sports, Concert

    // Default Constructor (No-Arg)
    public Event() {
        this.eventName = "";
        this.eventDate = LocalDate.now();
        this.eventTime = LocalTime.now();
        this.venue = new Venue();  // Initialize with an empty Venue object
        this.totalSeats = 0;
        this.availableSeats = 0;
        this.ticketPrice = 0.0;
        this.eventType = "Generic";
    }

    // Overloaded Constructor
    public Event(String eventName, LocalDate eventDate, LocalTime eventTime, 
            Venue venue, int totalSeats, double ticketPrice, String eventType) {
   this.eventName = eventName;
   this.eventDate = eventDate;
   this.eventTime = eventTime;
   this.venue = venue;
   this.totalSeats = totalSeats;
   this.availableSeats = totalSeats;  // Available seats initially same as total seats
   this.ticketPrice = ticketPrice;
   this.eventType = eventType;
}

    // Getters and Setters
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    // Method to get booked seats
    public int getBookedSeats() {
        return totalSeats - availableSeats;
    }

    // Book tickets for the event
    public void bookTickets(int numTickets) {
        if (numTickets <= availableSeats) {
            availableSeats -= numTickets;
            System.out.println(numTickets + " tickets booked successfully.");
        } else {
            System.out.println("Not enough seats available.");
        }
    }

    // Cancel booked tickets
    public void cancelBooking(int numTickets) {
        if (availableSeats + numTickets <= totalSeats) {
            availableSeats += numTickets;
            System.out.println(numTickets + " tickets canceled successfully.");
        } else {
            System.out.println("Invalid cancellation: Exceeds total seats.");
        }
    }

    // Calculate total revenue
    public double calculateTotalRevenue() {
        return getBookedSeats() * ticketPrice;
    }

    // Display event details
    public void displayEventDetails() {
        System.out.println("Event: " + eventName);
        System.out.println("Date: " + eventDate + ", Time: " + eventTime);
        venue.displayVenueDetails();  // Use Venue's display method
        System.out.println("Total Seats: " + totalSeats + ", Available Seats: " + availableSeats);
        System.out.println("Ticket Price: $" + ticketPrice);
        System.out.println("Event Type: " + eventType);
    }
}