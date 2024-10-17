package com.hexaware.ticketbooking.service;

import com.hexaware.ticketbooking.entity.Event;
import com.hexaware.ticketbooking.entity.Venue;

public interface IEventServiceProvider {
    Event createEvent(String eventName, String date, String time, int totalSeats, float ticketPrice, String eventType, Venue venue);
    Event[] getEventDetails(); // Return array of event details
    int getAvailableNoOfTickets(int eventId); // Return total available tickets for a specific event
}
