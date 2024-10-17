package com.hexaware.ticketbooking.service;

import com.hexaware.ticketbooking.dao.IBookingSystemRepository;
import com.hexaware.ticketbooking.entity.Concert;
import com.hexaware.ticketbooking.entity.Event;
import com.hexaware.ticketbooking.entity.Movie;
import com.hexaware.ticketbooking.entity.Sports;
import com.hexaware.ticketbooking.entity.Venue;

import java.time.LocalDate;
import java.time.LocalTime;

import com.hexaware.ticketbooking.dao.BookingSystemRepositoryImpl;

public class EventServiceProviderImpl implements IEventServiceProvider {
	private final IBookingSystemRepository repository = new BookingSystemRepositoryImpl();

	@Override
	public Event createEvent(String eventName, String date, String time, int totalSeats, float ticketPrice,
			String eventType, Venue venue) {
		// Get the appropriate Event subclass instance
		Event event = getEventInstance(eventName, venue, totalSeats, ticketPrice, eventType, date, time);
		
		boolean isCreated = repository.createEvent(event); // Store the event in the repository
		if (!isCreated) {
			throw new RuntimeException("Failed to create event: " + eventName);
		}
		return event; // Return the created event
	}

	// Helper method to return the correct subclass instance using switch case
	private Event getEventInstance(String eventName, Venue venue, int totalSeats, float ticketPrice, String eventType,
			String date, String time) {
		switch (eventType.toLowerCase()) {
		case "movie":
			Movie movie = new Movie();
			movie.setEventName(eventName);
			movie.setVenue(venue); // Assign venue if required
			movie.setTotalSeats(totalSeats);
			movie.setTicketPrice(ticketPrice);
			movie.setEventDate(LocalDate.parse(date));
			movie.setEventTime(LocalTime.parse(time));
			return movie;

		case "concert":
			return new Concert(eventName, LocalDate.parse(date), LocalTime.parse(time), venue, totalSeats, ticketPrice,
					"Music", "Rock");

		case "sports":
			return new Sports(eventName, LocalDate.parse(date), LocalTime.parse(time), venue, totalSeats, ticketPrice,
					"Football", "League");

		default:
			throw new IllegalArgumentException("Invalid event type: " + eventType);
		}
	}

	@Override
	public Event[] getEventDetails() {
		return repository.getEventDetails().toArray(new Event[0]); // Convert List to Array
	}

	@Override
	public int getAvailableNoOfTickets(int eventId) {
		Event event = repository.getEventById(eventId); // Adjust method to retrieve event by ID
		return repository.getAvailableTickets(event); // Fetch available tickets for the event
	}
}