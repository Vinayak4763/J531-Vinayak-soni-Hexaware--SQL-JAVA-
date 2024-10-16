package com.hexaware.ticketbooking.dao;

import com.hexaware.ticketbooking.entity.Booking;
import com.hexaware.ticketbooking.entity.Customer;
import com.hexaware.ticketbooking.entity.Event;
import com.hexaware.ticketbooking.entity.Venue;

import java.util.List;

public interface IBookingSystemRepository {
	
    Event createEvent(String eventName, String date, String time, int totalSeats, 
                      double ticketPrice, String eventType, Venue venue);
    
    List<Event> getEventDetails();
    
    int getAvailableNoOfTickets();
    
    double calculateBookingCost(int numTickets);
    
    Booking bookTickets(String eventName, int numTickets, List<Customer> customers);
    
    void cancelBooking(String bookingId);
    
    Booking getBookingDetails(String bookingId);
    
}