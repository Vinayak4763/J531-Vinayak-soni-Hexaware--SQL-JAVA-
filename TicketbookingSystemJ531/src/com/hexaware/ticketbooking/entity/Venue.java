package com.hexaware.ticketbooking.entity;

public class Venue {
    private int id; // Unique identifier for the venue
    private String venueName;
    private String address;

    // Default Constructor
    public Venue() { }

    // Constructor with only venue name
    public Venue(String venueName) {
        this.venueName = venueName;
    }

    // Parameterized Constructor with id, name, and address
    public Venue(int id, String venueName, String address) {
        this.id = id;
        this.venueName = venueName;
        this.address = address;
    }

    // Getter and Setter methods
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Display method
    public void displayVenueDetails() {
        System.out.println("Venue ID: " + id + 
                           ", Venue Name: " + venueName + 
                           ", Address: " + address);
    }
}