package com.hexaware.ticketbooking.entity;

public  class Venue {
    private String venueName;
    private String address;

    // Default Constructor
    public Venue() {
    	
            
    }

    public Venue(String venueName) {
        this.venueName = venueName;
    }
    // Parameterized Constructor
    public Venue(String venueName, String address) {
        this.venueName = venueName;
        this.address = address;
    }

    // Getter and Setter methods
    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Display method
    public void displayVenueDetails() {
        System.out.println("Venue Name: " + venueName + ", Address: " + address);
    }
}