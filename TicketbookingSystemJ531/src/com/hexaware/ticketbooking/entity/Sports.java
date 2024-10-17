package com.hexaware.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sports extends Event {
    private String sportName;    // Name of the game
    private String teamsName;     // Teams playing (e.g., India vs Pakistan)

    // Default Constructor
    public Sports() {
        super();
        this.sportName = "Unknown Sport";
        this.teamsName = "Unknown Teams";
    }

    public Sports(int eventId, String eventName, Venue venue) {
        super(eventId, eventName, venue); // Call the superclass constructor
    }
    
    public Sports(int eventId, String eventName, Venue venue, double ticketPrice, int availableSeats) {
        super(eventId, eventName, venue, ticketPrice, availableSeats, "Sports");
        this.sportName = "Unknown Sport"; // Default value
        this.teamsName = "Unknown League"; // Default value
    }

    // Overloaded Constructor
    public Sports(String eventName, LocalDate eventDate, LocalTime eventTime, 
                  Venue venue, int totalSeats, double ticketPrice, 
                  String sportName, String teamsName) {
        super(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, "Sports");
        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    // Getter and Setter for sportName
    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    // Getter and Setter for teamsName
    public String getTeamsName() {
        return teamsName;
    }

    public void setTeamsName(String teamsName) {
        this.teamsName = teamsName;
    }

    // Method to display sport details
    public void displaySportDetails() {
        displayEventDetails(); // Call to display the inherited event details
        System.out.println("Sport Name: " + sportName);
        System.out.println("Teams: " + teamsName);
    }
}