package com.hexaware.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {
    private String artist; // Name of the performing artist
    private String genre;  // Genre of the concert

    // Constructor
    
    public Concert(int eventId, String eventName, Venue venue) {
        super(eventId, eventName, venue); // Call the superclass constructor
    }
    
    
    public Concert(int eventId, String eventName, Venue venue, double ticketPrice, int availableSeats) {
        super(eventId, eventName, venue, ticketPrice, availableSeats, "Concert");
        this.genre = "Unknown Genre"; // Default value
        this.artist = "Unknown Artist"; // Default value
    }

    
    public Concert(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue, int totalSeats,
			double ticketPrice, String eventType) {
		super(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, eventType);
		// TODO Auto-generated constructor stub
	}




	public Concert(String eventName, LocalDate eventDate, LocalTime eventTime, 
            Venue venue, int totalSeats, double ticketPrice, 
            String artist, String genre) {
 super(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, "Concert");
 this.artist = artist;
 this.genre = genre;
}

    // Override the displayEventDetails method to include artist and genre
    @Override
    public void displayEventDetails() {
        super.displayEventDetails();
        System.out.println("Artist: " + artist + ", Genre: " + genre);
    }
}
