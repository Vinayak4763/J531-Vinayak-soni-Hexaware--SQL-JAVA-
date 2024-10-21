/* Romi Kumar Singh and Vinayak Soni
 * CarConnect VehicleDao
 * 21/10/2024
 */

package com.hexaware.carconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.carconnect.entity.Vehicle;
import com.hexaware.carconnect.util.DBUtil;

public class VehicleDao  implements  IVehicleDao{

	  private Connection conn;

	    public VehicleDao() {
	        conn = DBUtil.getConnection(); // Get connection from utility class
	    }

	    private Vehicle mapResultSetToVehicle(ResultSet rs) throws SQLException {
	        int vehicleId = rs.getInt("vehicleId");
	        String model = rs.getString("model");
	        String make = rs.getString("make");
	        int year = rs.getInt("year");
	        String color = rs.getString("color");
	        String registrationNumber = rs.getString("registrationNumber");
	        boolean availability = rs.getBoolean("availability");
	        double dailyRate = rs.getDouble("dailyRate");

	        return new Vehicle(vehicleId, model, make, year, color, registrationNumber, availability, dailyRate);
	    }

	
	@Override
	public Vehicle getVehicleById(int vehicleId) {
		Vehicle vehicle = null;
        String selectById = "SELECT * FROM Vehicle WHERE vehicleId = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(selectById);
            pstmt.setInt(1, vehicleId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                vehicle = mapResultSetToVehicle(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
	}

	@Override
	public List<Vehicle> getAvailableVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();
        String selectAvailable = "SELECT * FROM Vehicle WHERE availability = true";

        try {
            PreparedStatement pstmt = conn.prepareStatement(selectAvailable);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Vehicle vehicle = mapResultSetToVehicle(rs);
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
	}

	@Override
	public void addVehicle(Vehicle vehicleData) {
		 String insertVehicle = "INSERT INTO Vehicle (model, make, year, color, registrationNumber, availability, dailyRate) VALUES (?, ?, ?, ?, ?, ?, ?)";

		 try {
			 PreparedStatement pstmt = conn.prepareStatement(insertVehicle);
			 	pstmt.setString(1, vehicleData.getModel());
				pstmt.setString(2, vehicleData.getMake());
				pstmt.setInt(3, vehicleData.getYear());
				pstmt.setString(4, vehicleData.getColor());
				pstmt.setString(5, vehicleData.getRegistrationNumber());
				pstmt.setBoolean(6, vehicleData.isAvailability());
				pstmt.setDouble(7, vehicleData.getDailyRate());

			 pstmt.executeUpdate();
			 System.out.println("Vehicle added successfully!");

		 	}
		 	catch (SQLException e) {
		 		e.printStackTrace();
		 		System.err.println("Error adding vehicle");
		 	}
	}

	@Override
	public void updateVehicle(Vehicle vehicleData) {
		String updateVehicle = "UPDATE Vehicle SET model = ?, make = ?, year = ?, color = ?, registrationNumber = ?, availability = ?, dailyRate = ? "
				+ "WHERE vehicleId = ?";

		try
		{
			PreparedStatement pstmt = conn.prepareStatement(updateVehicle);
				pstmt.setString(1, vehicleData.getModel());
				pstmt.setString(2, vehicleData.getMake());
				pstmt.setInt(3, vehicleData.getYear());
				pstmt.setString(4, vehicleData.getColor());
				pstmt.setString(5, vehicleData.getRegistrationNumber());
				pstmt.setBoolean(6, vehicleData.isAvailability());
				pstmt.setDouble(7, vehicleData.getDailyRate());
				pstmt.setInt(8, vehicleData.getVehicleId());

			pstmt.executeUpdate();
		System.out.println("Vehicle updated successfully!");

		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error updating vehicle");
		}
		
	}

	@Override
	public void removeVehicle(int vehicleId) {
	    String deleteVehicle = "DELETE FROM Vehicle WHERE vehicleId = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(deleteVehicle)) {
	        pstmt.setInt(1, vehicleId);
	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Vehicle removed successfully!");
	        } else {
	            System.out.println("Vehicle with ID " + vehicleId + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Error removing vehicle");
	    }
	
		
	}

}
