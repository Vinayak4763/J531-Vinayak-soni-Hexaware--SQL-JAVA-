package com.hexaware.ticketbooking.dao;

import com.hexaware.ticketbooking.entity.Booking;
import com.hexaware.ticketbooking.entity.Customer;
import com.hexaware.ticketbooking.entity.Event;
import com.hexaware.ticketbooking.entity.Venue;
import com.hexaware.ticketbooking.exception.EventNotFoundException;
import com.hexaware.ticketbooking.exception.InvalidBookingIDException;

import java.util.List;

public interface IBookingSystemRepository {
    boolean createEvent(Event event);        // True if the event is created
    boolean bookTicket(Booking booking) throws EventNotFoundException;     // True if the booking succeeds
    boolean cancelTicket(int bookingId) throws InvalidBookingIDException;     // True if the ticket is canceled
    boolean addCustomer(Customer customer);  // True if the customer is added
    double calculateBookingCost(int eventId, int numOfTicket); 
    List<Event> getEventDetails();           // Fetch list of events
    boolean createVenue(Venue venue);        // True if venue is created
    int getAvailableTickets(Event event);    // Get available tickets count
    Booking getBookingDetails(int bookingId); 
    Event getEventById(int eventId);
}