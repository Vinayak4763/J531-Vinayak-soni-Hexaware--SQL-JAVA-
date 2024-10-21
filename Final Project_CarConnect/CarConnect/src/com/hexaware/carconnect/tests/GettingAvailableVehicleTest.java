/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Get available vehicle test
 * 21/10/2024
 */

package com.hexaware.carconnect.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexaware.carconnect.entity.Vehicle;
import com.hexaware.carconnect.service.VehicleService;

class GettingAvailableVehicleTest {

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService();
    }

    @Test
    void test() {
        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();

        assertEquals(true, availableVehicles.get(0).isAvailability());
    }
}
