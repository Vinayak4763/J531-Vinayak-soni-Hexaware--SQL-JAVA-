package com.hexaware.ticketbooking.main;

import com.hexaware.ticketbooking.entity.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class TicketBookingSystem {
    static Scanner scanner = new Scanner(System.in);

    // Method to create a new event
    public Event createEvent(String eventType) {
        System.out.print("Enter Event Name: ");
        String eventName = scanner.next();

        System.out.print("Enter Event Date (YYYY-MM-DD): ");
        LocalDate eventDate = LocalDate.parse(scanner.next());

        System.out.print("Enter Event Time (HH:MM): ");
        LocalTime eventTime = LocalTime.parse(scanner.next());

        System.out.print("Enter Venue Name: ");
        String venueName = scanner.next();

        System.out.print("Enter Total Seats: ");
        int totalSeats = scanner.nextInt();

        System.out.print("Enter Ticket Price: ");
        double ticketPrice = scanner.nextDouble();

        switch (eventType.toLowerCase()) {
            case "movie":
                System.out.print("Enter Genre: ");
                String genre = scanner.next();

                System.out.print("Enter Actor Name: ");
                String actor = scanner.next();

                System.out.print("Enter Actress Name: ");
                String actress = scanner.next();

                return new Movie(eventName, eventDate, eventTime, new Venue(venueName), 
                                 totalSeats, ticketPrice, genre, actor, actress);

            case "concert":
                System.out.print("Enter Artist Name: ");
                String artist = scanner.next();

                System.out.print("Enter Genre: ");
                String concertGenre = scanner.next();

                return new Concert(eventName, eventDate, eventTime, new Venue(venueName), 
                                   totalSeats, ticketPrice, artist, concertGenre);

            case "sports":
                System.out.print("Enter Sport Name: ");
                String sportName = scanner.next();

                System.out.print("Enter Teams (e.g., India vs Australia): ");
                String teams = scanner.next();

                return new Sports(eventName, eventDate, eventTime, new Venue(venueName), 
                                  totalSeats, ticketPrice, sportName, teams);

            default:
                System.err.println("Invalid event type. Returning null.");
                return null;
        }
    }

    // Method to display event details
    public void displayEventDetails(Event event) {
        if (event != null) {
            event.displayEventDetails();
        } else {
            System.err.println("No event to display.");
        }
    }

    // Method to book tickets
    public double bookTickets(Event event, int numTickets) {
        if (numTickets <= event.getAvailableSeats()) {
            int updatedSeats = event.getAvailableSeats() - numTickets;
            event.setAvailableSeats(updatedSeats);
            double totalCost = numTickets * event.getTicketPrice();
            System.out.println(numTickets + " tickets booked successfully!");
            return totalCost;
        } else {
            System.err.println("Insufficient seats! Event might be sold out.");
            return 0.0;
        }
    }

    // Method to cancel tickets
    public void cancelTickets(Event event, int numTickets) {
        int updatedSeats = event.getAvailableSeats() + numTickets;
        event.setAvailableSeats(updatedSeats);
        System.out.println(numTickets + " tickets cancelled successfully!");
    }

    // Main method to simulate the system
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        boolean flag = true;
        Event event = null; // Event initially set to null

        while (flag) {
            System.out.println("\n***** Ticket Booking Menu *****");
            System.out.println("1. Create Event");
            System.out.println("2. View Event Details");
            System.out.println("3. Book Tickets");
            System.out.println("4. Cancel Tickets");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter event type (Movie, Concert, Sports): ");
                    String eventType = scanner.next();
                    event = system.createEvent(eventType); // Create the event
                    if (event != null) {
                        System.out.println("Event created successfully!");
                    }
                    break;

                case 2:
                    system.displayEventDetails(event); // View event details
                    break;

                case 3:
                    if (event == null) {
                        System.err.println("No event available. Please create an event first.");
                    } else {
                        System.out.print("Enter the number of tickets to book: ");
                        int numTickets = scanner.nextInt();
                        double totalCost = system.bookTickets(event, numTickets);
                        if (totalCost > 0) {
                            System.out.println("Total cost: $" + totalCost);
                        }
                    }
                    break;

                case 4:
                    if (event == null) {
                        System.err.println("No event available. Please create an event first.");
                    } else {
                        System.out.print("Enter the number of tickets to cancel: ");
                        int numTickets = scanner.nextInt();
                        system.cancelTickets(event, numTickets);
                    }
                    break;

                case 5:
                    flag = false;
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.err.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
