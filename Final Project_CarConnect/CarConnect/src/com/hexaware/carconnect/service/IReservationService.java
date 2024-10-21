/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Reservation interface service
 * 21/10/2024
 */
package com.hexaware.carconnect.service;

import java.util.List;
import com.hexaware.carconnect.entity.Reservation;
import com.hexaware.carconnect.exceptions.ReservationException;
import com.hexaware.carconnect.exceptions.InvalidInputException;

public interface IReservationService {

	   // Fetch a reservation by its unique ID
    Reservation getReservationById(int reservationId) throws ReservationException;

    // Fetch a list of reservations by a customer ID
    List<Reservation> getReservationsByCustomerId(int customerId) throws InvalidInputException;

    // Create a new reservation
    void createReservation(Reservation reservationData) throws ReservationException, InvalidInputException;

    // Update an existing reservation
    void updateReservation(Reservation reservationData) throws ReservationException, InvalidInputException;

    // Cancel a reservation by its unique ID
    void cancelReservation(int reservationId) throws ReservationException;
}
