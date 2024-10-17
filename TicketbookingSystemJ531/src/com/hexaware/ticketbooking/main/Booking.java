package com.hexaware.ticketbooking.main;

import com.hexaware.ticketbooking.entity.Concert;
import com.hexaware.ticketbooking.entity.Event;
import com.hexaware.ticketbooking.entity.Customer;
import com.hexaware.ticketbooking.entity.Venue;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Booking {
    private static int bookingCounter = 0; // To generate unique booking IDs
    private int bookingId;
    private Customer[] customers; // Array of customers
    private Event event; // Reference to the event booked
    private int numTickets; // Number of tickets booked
    private double totalCost; // Total cost of the booking
    private LocalDateTime bookingDate; // Timestamp of booking

    // Default constructor
    public Booking() {
        this.bookingId = ++bookingCounter;
        this.customers = new Customer[0]; // Initialize with zero customers
        this.event = null;
        this.numTickets = 0;
        this.totalCost = 0.0;
        this.bookingDate = LocalDateTime.now();
    }

    // Overloaded constructor with Customer attributes
    public Booking(Customer[] customers, Event event, int numTickets) {
        this.bookingId = ++bookingCounter;
        this.customers = customers;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = numTickets * event.getTicketPrice();
        this.bookingDate = LocalDateTime.now();
    }

    // Getter and setter methods
    
    public void setCustomerId(int customerId) {   //handline validations//
        // Assuming you want to associate the customer ID with the customer array
        if (customers != null && customerId >= 0 && customerId < customers.length) {
            // Implement logic to set the customer ID if necessary
        } else {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }
    
    
    
    public int getBookingId() {
        return bookingId;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    // Display booking details
    public void displayBookingDetails() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Event Details:");
        event.displayEventDetails(); // Call the event's display method
        System.out.println("Number of Tickets: " + numTickets);
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Customer Details:");
        for (Customer customer : customers) {
            System.out.println("Customer Name: " + customer.getCustomerName()); // Adjust according to Customer attributes
        }
    }

    // Calculate and display the total cost for the given number of tickets
    public void calculateBookingCost(Event event, int numTickets) {
        double totalCost = numTickets * event.getTicketPrice();
        System.out.println("Total cost for " + numTickets + " tickets: " + totalCost);
    }

    // Book the specified number of tickets for the event
    public void bookTickets(Event event, int numTickets) {
        if (event.getAvailableSeats() >= numTickets) {
            event.bookTickets(numTickets);
            System.out.println(numTickets + " tickets booked successfully!");
        } else {
            System.out.println("Not enough seats available.");
        }
    }

    // Cancel the specified number of tickets and update the available seats
    public void cancelBooking(Event event, int numTickets) {
        if (numTickets <= event.getBookedSeats()) {
            event.cancelBooking(numTickets);
            System.out.println(numTickets + " tickets canceled successfully.");
        } else {
            System.out.println("Invalid cancellation request. You can't cancel more than booked tickets.");
        }
    }

    // Return the total number of available tickets for the event
    public int getAvailableNoOfTickets(Event event) {
        return event.getAvailableSeats();
    }

    // Display the details of the event
    public void getEventDetails(Event event) {
        event.displayEventDetails();
    }

    // Main method to demonstrate the operations
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Venue venue = new Venue(1, "PVR Cinemas", "Vidisha");

        // Create an Event object
        Concert event = new Concert(
        	    "Interstellar", 
        	    LocalDate.of(2024, 11, 10), 
        	    LocalTime.of(18, 30), 
        	    venue, // Pass the Venue object here
        	    200, 
        	    250.00, 
        	    "Hans Zimmer", 
        	    "Movie Soundtracks"
        	);
        // Create a Booking object to access the methods
        Booking booking = new Booking();

        while (true) {
            System.out.println("\n--- Ticket Booking System ---");
            System.out.println("1. Book Tickets");
            System.out.println("2. Cancel Tickets");
            System.out.println("3. Check Available Tickets");
            System.out.println("4. View Event Details");
            System.out.println("5. View Booking Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the number of tickets to book: ");
                    int ticketsToBook = scanner.nextInt();
                    booking.bookTickets(event, ticketsToBook);
                    break;
                case 2:
                    System.out.print("Enter the number of tickets to cancel: ");
                    int ticketsToCancel = scanner.nextInt();
                    booking.cancelBooking(event, ticketsToCancel);
                    break;
                case 3:
                    System.out.println("Available Tickets: " + booking.getAvailableNoOfTickets(event));
                    break;
                case 4:
                    System.out.println("Event Details:");
                    booking.getEventDetails(event);
                    break;
                case 5:
                    booking.displayBookingDetails(); // Display booking details
                    break;
                case 6:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
