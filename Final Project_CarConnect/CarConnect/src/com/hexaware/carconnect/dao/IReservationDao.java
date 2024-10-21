/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Reservation Interface
 * 21/10/2024
 */

package com.hexaware.carconnect.dao;

import java.util.List;

import com.hexaware.carconnect.entity.Reservation;

public interface IReservationDao {
	 Reservation getReservationById(int reservationId);

	    // Fetch a list of reservations by a customer ID
	    List<Reservation> getReservationsByCustomerId(int customerId);

	    // Create a new reservation
	    void createReservation(Reservation reservationData);

	    // Update an existing reservation
	    void updateReservation(Reservation reservationData);

	    // Cancel a reservation by its unique ID
	    void cancelReservation(int reservationId);

}
