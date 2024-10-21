/*
 * Author: J531 Vinayak Soni
 * Date: 10/11/24
 * Description: Reservation model
 */

package com.hexaware.carconnect.entity;

public class Reservation {

	private int reservationId;
    private int customerId;
    private int vehicleId;
    private String startDate;
    private String endDate;
    private double totalCost;
    private String status;
    
    
    
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reservation(int reservationId, int customerId, int vehicleId, String startDate, String endDate,
			double totalCost, String status) {
		super();
		this.reservationId = reservationId;
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.status = status;
	}


	public int getReservationId() {
		return reservationId;
	}


	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public int getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return String.format("Reservation [customerId=%s, vehicleId=%s, startDate=%s, endDate=%s]", customerId,
				vehicleId, startDate, endDate);
	}

	
	// Simplified CalculateTotalCost method
	public void calculateTotalCost(double dailyRate, int rentalDays) {
	    this.totalCost = dailyRate * rentalDays;
	}
    
}
