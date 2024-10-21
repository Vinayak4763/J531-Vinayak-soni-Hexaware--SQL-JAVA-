
/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Vehicle Interface
 * 21/10/2024
 */

package com.hexaware.carconnect.dao;

import java.util.List;

import com.hexaware.carconnect.entity.Vehicle;

public interface IVehicleDao {
	  // Fetch a vehicle by its unique ID
    Vehicle getVehicleById(int vehicleId);

    // Fetch a list of available vehicles
    List<Vehicle> getAvailableVehicles();

    // Add a new vehicle to the system
    void addVehicle(Vehicle vehicleData);

    // Update the details of an existing vehicle
    void updateVehicle(Vehicle vehicleData);

    // Remove a vehicle by its unique ID
    void removeVehicle(int vehicleId);
}
