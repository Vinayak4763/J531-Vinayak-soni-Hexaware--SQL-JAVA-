/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Vehicle presentation
 * 21/10/2024
 */

package com.hexaware.carconnect.presentation;

import java.util.List;
import java.util.Scanner;

import com.hexaware.carconnect.entity.Vehicle;
import com.hexaware.carconnect.exceptions.VehicleNotFoundException;
import com.hexaware.carconnect.service.IVehicleService;
import com.hexaware.carconnect.service.VehicleService;

public class VehicleClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws VehicleNotFoundException {

        boolean flag = true;

        
        IVehicleService service = new VehicleService();

        while (flag) {
           
            System.out.println("\n***** Vehicle Options *****");
            System.out.println("1. Get Vehicle By ID");
            System.out.println("2. Get  Available Vehicles");
            System.out.println("3. Add Vehicle");
            System.out.println("4. Update Vehicle");
            System.out.println("5. Remove Vehicle");
          
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                  
                    System.out.println("Enter Vehicle ID:");
                    int vehicleId = scanner.nextInt();
                    Vehicle vehicle = service.getVehicleById(vehicleId);
                    
                    if (vehicle != null)
                    {
                        System.out.println(vehicle);
                    } 
                    else
                    {
                        System.err.println("Vehicle not found.");
                    }
                    break;

                case 2:
                   
                    List<Vehicle> availableVehicles = service.getAvailableVehicles();
                    
                    if (!availableVehicles.isEmpty()) 
                    {
                        for (Vehicle v : availableVehicles) 
                        {
                            System.out.println(v);
                        }
                    } 
                    else 
                    {
                        System.err.println("No available vehicles found.");
                    }
                    break;

                case 3:
                   
                    Vehicle newVehicle = readVehicleData();
                    service.addVehicle(newVehicle);
                    
                    System.out.println("Vehicle added successfully.");
                    break;

                case 4:
                   
                    System.out.println("Enter Vehicle ID to update:");
                    int updateId = scanner.nextInt();
                    
                    Vehicle updatedVehicle = readVehicleData();
                    updatedVehicle.setVehicleId(updateId);
                    service.updateVehicle(updatedVehicle);
                    
                    System.out.println("Vehicle updated successfully.");
                    break;

                case 5:
                    
                    System.out.println("Enter Vehicle ID to remove:");
                    
                    int removeId = scanner.nextInt();
                    service.removeVehicle(removeId);
                    
                    System.out.println("Vehicle removed successfully.");
                    break;

                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static Vehicle readVehicleData() {
        System.out.println("Enter Vehicle Model:");
        String model = scanner.next();

        System.out.println("Enter Vehicle Make:");
        String make = scanner.next();

        System.out.println("Enter Vehicle Year:");
        int year = scanner.nextInt();

        System.out.println("Enter Vehicle Color:");
        String color = scanner.next();

        System.out.println("Enter Registration Number:");
        String registrationNumber = scanner.next();

        System.out.println("Is the vehicle available (true/false)?");
        boolean availability = scanner.nextBoolean();

        System.out.println("Enter Daily Rate:");
        double dailyRate = scanner.nextDouble();

        // ID is set to 0 initially, assuming it will be auto-generated in the database
        return new Vehicle(0, model, make, year, color, registrationNumber, availability, dailyRate);
    }
}