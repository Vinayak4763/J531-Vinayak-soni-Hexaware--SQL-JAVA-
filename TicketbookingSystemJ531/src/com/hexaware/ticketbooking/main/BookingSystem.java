
package com.hexaware.ticketbooking.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import com.hexaware.ticketbooking.entity.*;

class BookingSystem {

    static Scanner scanner = new Scanner(System.in);

    // Method to create a new event object
    public Event createEvent(String eventName, String date, String time, int totalSeats, 
                             double ticketPrice, String eventType, String venueName, String venueAddress) {
        LocalDate eventDate = LocalDate.parse(date);
        LocalTime eventTime = LocalTime.parse(time);
        Venue venue = new Venue(totalSeats, venueName, venueAddress); // Include venue address

        switch (eventType.toLowerCase()) {
            case "movie":
                System.out.print("🎬 Enter Genre: ");
                String genre = scanner.nextLine();
                System.out.print("🎭 Enter Actor Name: ");
                String actorName = scanner.nextLine();
                System.out.print("🎭 Enter Actress Name: ");
                String actressName = scanner.nextLine();
                return new Movie(eventName, eventDate, eventTime, venue, totalSeats, 
                                 ticketPrice, genre, actorName, actressName);

            case "concert":
                System.out.print("🎤 Enter Artist Name: ");
                String artist = scanner.nextLine();
                System.out.print("🎼 Enter Genre: ");
                String concertGenre = scanner.nextLine();
                return new Concert(eventName, eventDate, eventTime, venue, totalSeats, 
                                   ticketPrice, artist, concertGenre);

            case "sport":
                System.out.print("⚽ Enter Teams: ");
                String teams = scanner.nextLine();
                System.out.print("🕵️ Enter Referee Name: ");
                String referee = scanner.nextLine();
                return new Sports(eventName, eventDate, eventTime, venue, totalSeats, 
                                  ticketPrice, teams, referee);

            default:
                System.out.println("❌ Invalid event type! Please try again.");
                return null;
        }
    }

    // Method to display event details using polymorphism
    public void displayEventDetails(Event event) {
        System.out.println("🌟 Event Details 🌟");
        System.out.println("=====================");
        event.displayEventDetails();
        System.out.println("=====================");
    }

    // Method to book tickets for an event
    public double bookTickets(Event event, int numTickets) {
        if (event.getAvailableSeats() >= numTickets) {
            event.setAvailableSeats(event.getAvailableSeats() - numTickets);
            double totalCost = numTickets * event.getTicketPrice();
            System.out.println("\n🎫 Booking Successful!");
            System.out.println("💰 Total Cost: " + totalCost);
            return totalCost;
        } else {
            System.out.println("❌ Booking Failed: Not enough seats available.");
            return 0;
        }
    }

    // Method to cancel tickets for an event
    public void cancelTickets(Event event, int numTickets) {
        event.setAvailableSeats(event.getAvailableSeats() + numTickets);
        System.out.println("✅ " + numTickets + " tickets cancelled successfully.");
    }

    // Main method to simulate the ticket booking system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystem system = new BookingSystem();
        Event event = null;
        boolean running = true;

        while (running) {
            // UI Header
            System.out.println("\n************************************************");
            System.out.println("⭐️ Welcome to the Ticket Booking System ⭐️");
            System.out.println("************************************************");
            System.out.println("1️⃣  Create Event");
            System.out.println("2️⃣  Display Event Details");
            System.out.println("3️⃣  Book Tickets");
            System.out.println("4️⃣  Cancel Tickets");
            System.out.println("5️⃣  Exit");
            System.out.print("👉 Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n🎟️ Create a New Event 🎟️");
                    System.out.print("📛 Enter Event Name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("📅 Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("⏰ Enter Time (HH:MM): ");
                    String time = scanner.nextLine();
                    System.out.print("💺 Enter Total Seats: ");
                    int totalSeats = scanner.nextInt();
                    System.out.print("💵 Enter Ticket Price: ");
                    double ticketPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("🎫 Enter Event Type (Movie/Sport/Concert): ");
                    String eventType = scanner.nextLine();
                    System.out.print("📍 Enter Venue Name: ");
                    String venueName = scanner.nextLine();
                    System.out.print("🏠 Enter Venue Address: ");
                    String venueAddress = scanner.nextLine(); // New input for address

                    event = system.createEvent(eventName, date, time, totalSeats, 
                                               ticketPrice, eventType, venueName, venueAddress);
                    if (event != null) {
                        System.out.println("✅ Event created successfully!");
                    }
                    break;

                case 2:
                    if (event != null) {
                        system.displayEventDetails(event);
                    } else {
                        System.out.println("⚠️ No event found. Please create an event first.");
                    }
                    break;

                case 3:
                    if (event != null) {
                        System.out.print("🎫 Enter number of tickets to book: ");
                        int numTickets = scanner.nextInt();
                        system.bookTickets(event, numTickets);
                    } else {
                        System.out.println("⚠️ No event found. Please create an event first.");
                    }
                    break;

                case 4:
                    if (event != null) {
                        System.out.print("🛑 Enter number of tickets to cancel: ");
                        int cancelTickets = scanner.nextInt();
                        system.cancelTickets(event, cancelTickets);
                    } else {
                        System.out.println("⚠️ No event found. Please create an event first.");
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("👋 Exiting Ticket Booking System. Thank you!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}

