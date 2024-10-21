/* Romi Kumar Singh and Vinayak Soni
 * CarConnect updating vehicle details test
 * 21/10/2024
 */

package com.hexaware.carconnect.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.hexaware.carconnect.entity.Vehicle;
import com.hexaware.carconnect.exceptions.VehicleNotFoundException;
import com.hexaware.carconnect.service.VehicleService;

class UpdatingVehicleDetails {

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService();
    }

    @Test
    void testUpdateVehicle() throws VehicleNotFoundException {
        Vehicle initialVehicle = new Vehicle(1, "Model S", "Tesla", 2024, "blue", "ABCD123", true, 100.0);
        vehicleService.addVehicle(initialVehicle); // Assuming this method adds the vehicle

        
        Vehicle updatedVehicle = new Vehicle(1, "Model S", "Tesla", 2022, "green", "ABCD321", true, 120.0);
        vehicleService.updateVehicle(updatedVehicle); 

        
        Vehicle result = vehicleService.getVehicleById(8); 
        
        assertEquals("green", result.getColor());
    }
}
