/* Romi Kumar Singh and Vinayak Soni
 * CarConnect new vehicle test
 * 21/10/2024
 */

package com.hexaware.carconnect.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.hexaware.carconnect.entity.Vehicle;
import com.hexaware.carconnect.exceptions.VehicleNotFoundException;
import com.hexaware.carconnect.service.VehicleService;

class AddingNewVehicleTest {

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService();
    }

    @Test
    void testAddVehicle() throws VehicleNotFoundException {
        Vehicle newVehicle = new Vehicle(6, "XUV700", "Mahindra", 2022, "Red", "ABC123", true, 100.0);

        vehicleService.addVehicle(newVehicle);

        Vehicle result = vehicleService.getVehicleById(6);  

        assertEquals("XUV700", result.getModel());
    }
}

