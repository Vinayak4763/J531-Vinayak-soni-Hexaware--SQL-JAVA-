package com.hexaware.ticketbooking.dao;

import com.hexaware.ticketbooking.entity.*;
import com.hexaware.ticketbooking.exception.EventNotFoundException;
import com.hexaware.ticketbooking.exception.InvalidBookingIDException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {
    private List<Event> eventList;

    @Override
    public boolean createEvent(Event event) {
        String query = "INSERT INTO event (event_name, venue_id, event_type, ticket_price, total_seats, available_seats) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, event.getEventName());
            pstmt.setInt(2, event.getVenue().getId());
            pstmt.setString(3, event.getClass().getSimpleName().toLowerCase());
            pstmt.setDouble(4, event.getTicketPrice());
            pstmt.setInt(5, event.getTotalSeats());
            pstmt.setInt(6, event.getAvailableSeats());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Event> getEventDetails() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT e.event_id, e.event_name, e.event_type, e.ticket_price, e.available_seats, " +
                "v.venue_id, v.venue_name, v.address FROM event e " +
                "JOIN venue v ON e.venue_id = v.venue_id";

        try (Connection conn = DBUtil.getDbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Venue venue = new Venue(
                        rs.getInt("venue_id"),
                        rs.getString("venue_name"),
                        rs.getString("address")
                );

                String eventType = rs.getString("event_type");

                Event event = createEvent(
                        eventType,
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        venue,
                        rs.getDouble("ticket_price"),
                        rs.getInt("available_seats")
                );

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    private Event createEvent(String eventType, int eventId, String eventName, Venue venue,
                              double ticketPrice, int availableSeats) {
        switch (eventType.toLowerCase()) {
            case "movie":
                return new Movie(eventId, eventName, venue, ticketPrice, availableSeats);
            case "sports":
                return new Sports(eventId, eventName, venue, ticketPrice, availableSeats);
            case "concert":
                return new Concert(eventId, eventName, venue, ticketPrice, availableSeats);
            default:
                throw new IllegalArgumentException("Unknown event type: " + eventType);
        }
    }

    @Override
    public boolean bookTicket(Booking booking) throws EventNotFoundException {
        // Ensure the event exists before booking
        Event event = getEventById(booking.getEvent().getEventId());
        if (event == null) {
            throw new EventNotFoundException("Event not found: " + booking.getEvent().getEventId());
        }

        // Your existing booking logic here...
        String query = "INSERT INTO booking (customer_id, event_id, num_tickets, total_cost, booking_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, booking.getCustomer().getCustomerId());
            pstmt.setInt(2, booking.getEvent().getEventId());
            pstmt.setInt(3, booking.getNumOfTickets());
            pstmt.setDouble(4, booking.getTotalCost());
            pstmt.setDate(5, Date.valueOf(booking.getBookingDate()));

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelTicket(int bookingId) throws InvalidBookingIDException {
        // Check if the booking ID is valid before canceling
        if (getBookingDetails(bookingId) == null) {
            throw new InvalidBookingIDException("Invalid booking ID: " + bookingId);
        }

        String query = "DELETE FROM booking WHERE booking_id = ?";
        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Booking getBookingDetails(int bookingId) {
        Booking booking = null;
        String query = "SELECT * FROM booking WHERE booking_id = ?";
        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setNumOfTickets(rs.getInt("num_tickets"));
                booking.setTotalCost(rs.getDouble("total_cost"));
                booking.setBookingDate(rs.getDate("booking_date").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking; // It can return null if booking is not found
    }

    @Override
    public Event getEventById(int eventId) {
        for (Event event : eventList) {
            if (event.getEventId() == eventId) {
                return event; // Return the event if found
            }
        }
        return null; // Return null if not found
    }

	@Override
	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double calculateBookingCost(int eventId, int numOfTicket) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean createVenue(Venue venue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAvailableTickets(Event event) {
		// TODO Auto-generated method stub
		return 0;
	}
}
