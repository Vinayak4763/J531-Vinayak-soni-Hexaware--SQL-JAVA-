
package com.hexaware.ticketbooking.dao;

import com.hexaware.ticketbooking.entity.Booking;
import com.hexaware.ticketbooking.entity.Customer;
import com.hexaware.ticketbooking.entity.Event;
import com.hexaware.ticketbooking.entity.Venue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

    @Override
    public Event createEvent(String eventName, String date, String time, int totalSeats, 
                             double ticketPrice, String eventType, Venue venue) {
        String insertEventSQL = "INSERT INTO events (event_name, event_date, event_time, total_seats, "
                               + "available_seats, ticket_price, event_type, venue_id) "
                               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertEventSQL)) {

            pstmt.setString(1, eventName);
            pstmt.setString(2, date);
            pstmt.setString(3, time);
            pstmt.setInt(4, totalSeats);
            pstmt.setInt(5, totalSeats); // Initially available seats = total seats
            pstmt.setDouble(6, ticketPrice);
            pstmt.setString(7, eventType);
            pstmt.setInt(8, venue.getId()); // Assuming Venue has an ID attribute

            pstmt.executeUpdate();
            return new Event(eventName, date, time, venue, totalSeats, totalSeats, ticketPrice, eventType);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Event> getEventDetails() {
        List<Event> eventList = new ArrayList<>();
        String query = "SELECT * FROM events";

        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                        rs.getString("event_name"),
                        rs.getString("event_date"),
                        rs.getString("event_time"),
                        // Assuming Venue object needs to be created or fetched based on venue_id
                        new Venue(rs.getInt("venue_id")), 
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats"),
                        rs.getDouble("ticket_price"),
                        rs.getString("event_type")
                );
                eventList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    @Override
    public int getAvailableNoOfTickets() {
        String query = "SELECT SUM(available_seats) FROM events";
        int availableSeats = 0;

        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                availableSeats = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableSeats;
    }

    @Override
    public double calculateBookingCost(int numTickets) {
        // Assuming you fetch the ticket price from the event before this call
        double ticketPrice = 100; // Replace with actual logic to get ticket price
        return numTickets * ticketPrice;
    }

    @Override
    public Booking bookTickets(String eventName, int numTickets, List<Customer> customers) {
        Event event = getEventByName(eventName); // Assume this method exists to fetch event details
        if (event != null && event.getAvailableSeats() >= numTickets) {
            String insertBookingSQL = "INSERT INTO bookings (event_name, num_tickets, customer_ids) VALUES (?, ?, ?)";
            StringBuilder customerIds = new StringBuilder();
            for (Customer customer : customers) {
                customerIds.append(customer.getId()).append(","); // Assuming Customer has an ID
            }
            // Remove last comma
            if (customerIds.length() > 0) {
                customerIds.setLength(customerIds.length() - 1);
            }

            try (Connection conn = DBUtil.getDbConnection();
                 PreparedStatement pstmt = conn.prepareStatement(insertBookingSQL)) {
                pstmt.setString(1, eventName);
                pstmt.setInt(2, numTickets);
                pstmt.setString(3, customerIds.toString());
                pstmt.executeUpdate();

                // Update available seats
                event.setAvailableSeats(event.getAvailableSeats() - numTickets);

                return new Booking(eventName, customers, calculateBookingCost(numTickets)); // Create Booking object with details
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; // Return null if booking fails
    }

    @Override
    public void cancelBooking(String bookingId) {
        String deleteBookingSQL = "DELETE FROM bookings WHERE booking_id = ?";

        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteBookingSQL)) {

            pstmt.setString(1, bookingId);
            pstmt.executeUpdate();
            System.out.println("Booking canceled successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Booking getBookingDetails(String bookingId) {
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        Booking booking = null;

        try (Connection conn = DBUtil.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, bookingId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Retrieve booking details and create Booking object
                // Assuming Booking has appropriate constructor
                booking = new Booking(rs.getString("event_name"), /* retrieve customer objects here */);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    private Event getEventByName(String eventName) {
        // Implement logic to fetch event details from the database based on event name
        return null; // Replace with actual implementation
    }
}
