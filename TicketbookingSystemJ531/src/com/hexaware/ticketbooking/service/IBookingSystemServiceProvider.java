package com.hexaware.ticketbooking.service;

import com.hexaware.ticketbooking.entity.Booking;
import com.hexaware.ticketbooking.entity.Customer;

public interface IBookingSystemServiceProvider {
    double calculateBookingCost(int numTickets, int eventId); // Calculate and set the total cost of the booking
    Booking bookTickets(String eventName, int numTickets, Customer[] arrayOfCustomer); // Book tickets
    boolean cancelBooking(int bookingId); // Cancel the booking
    Booking getBookingDetails(int bookingId); // Get booking details
}