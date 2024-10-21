/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Reservation SERVICE
 * 21/10/2024
 */

package com.hexaware.carconnect.service;

import java.util.List;

import com.hexaware.carconnect.dao.IReservationDao;
import com.hexaware.carconnect.dao.ReservationDao;
import com.hexaware.carconnect.entity.Reservation;
import com.hexaware.carconnect.exceptions.InvalidInputException;
import com.hexaware.carconnect.exceptions.ReservationException;

public class ReservationService implements IReservationService{

	private IReservationDao reservationDao;

    public ReservationService() {
        this.reservationDao = new ReservationDao(); 
    }
	
	@Override
	public Reservation getReservationById(int reservationId) throws ReservationException {
		
		 return reservationDao.getReservationById(reservationId);
	}

	@Override
	public List<Reservation> getReservationsByCustomerId(int customerId) throws InvalidInputException{
		
		return reservationDao.getReservationsByCustomerId(customerId);
	}

	@Override
	public void createReservation(Reservation reservationData) throws ReservationException, InvalidInputException{
 
		
		 reservationDao.createReservation(reservationData);
		
	}

	@Override
	public void updateReservation(Reservation reservationData) throws ReservationException, InvalidInputException {
		
		 reservationDao.updateReservation(reservationData);
		
	}

	@Override
	public void cancelReservation(int reservationId)  throws ReservationException{
		
		   reservationDao.cancelReservation(reservationId);
		
	}

}