package com.hexaware.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event {
    private String genre;       // Genre of the movie (e.g., Action, Comedy, Horror)
    private String actorName;   // Name of the lead actor
    private String actressName; // Name of the lead actress

    // Default Constructor
    public Movie() {
        super();
        this.genre = "Unknown Genre";
        this.actorName = "Unknown Actor";
        this.actressName = "Unknown Actress";
    }
    
    public Movie(int eventId, String eventName, Venue venue) {
    	
		super(eventId, eventName, venue);
		
	}
    public Movie(int eventId, String eventName, Venue venue, double ticketPrice, int availableSeats) {
        super(eventId, eventName, venue, ticketPrice, availableSeats, "Movie");
        this.genre = "Unknown Genre";      // Default value
        this.actorName = "Unknown Actor";  // Default value
        this.actressName = "Unknown Actress"; // Default value
    }

	// Overloaded Constructor
    public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, 
                 Venue venue, int totalSeats, double ticketPrice, 
                 String genre, String actorName, String actressName) {
        super(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, "Movie");
        this.genre = genre;
        this.actorName = actorName;
        this.actressName = actressName;
    }

    // Getters and Setters for all attributes
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActressName() {
        return actressName;
    }

    public void setActressName(String actressName) {
        this.actressName = actressName;
    }

    // Overridden method to display event details including movie-specific attributes
    @Override
    public void displayEventDetails() {
        super.displayEventDetails();
        System.out.println("Genre: " + genre);
        System.out.println("Actor: " + actorName);
        System.out.println("Actress: " + actressName);
    }
}
