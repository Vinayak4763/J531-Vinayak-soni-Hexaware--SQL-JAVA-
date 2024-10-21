/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Main Module 
 * 21/10/2024
 */

package com.hexaware.carconnect.presentation;

import com.hexaware.carconnect.entity.Admin;
import com.hexaware.carconnect.entity.Reservation;
import com.hexaware.carconnect.entity.Customer;
import com.hexaware.carconnect.service.IAdminService;
import com.hexaware.carconnect.service.AdminService;
import com.hexaware.carconnect.service.CustomerService;  
import com.hexaware.carconnect.service.ICustomerService; 
import com.hexaware.carconnect.service.VehicleService;
import com.hexaware.carconnect.service.IVehicleService;
import com.hexaware.carconnect.entity.Vehicle;
import com.hexaware.carconnect.exceptions.AdminNotFoundException;
import com.hexaware.carconnect.exceptions.InputMismatchCustomException;
import com.hexaware.carconnect.exceptions.InvalidInputException;
import com.hexaware.carconnect.exceptions.ReservationException;
import com.hexaware.carconnect.exceptions.VehicleNotFoundException;
import com.hexaware.carconnect.service.ReservationService;
import com.hexaware.carconnect.service.IReservationService;


import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;


import java.util.Scanner;

public class MainModule {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws VehicleNotFoundException, AdminNotFoundException, InvalidInputException, ReservationException,InputMismatchCustomException {
        boolean flag = true;

        while (flag) {
            // Main menu options
            System.out.println("\n***** Main Menu *****");
            System.out.println("1. Admin Options");
            System.out.println("2. Customer Options");
            System.out.println("3. Reservation Options");
            System.out.println("4. Vehicle Options");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminOptions(); 
                    break;

                case 2:
                    customerOptions(); 
                    break;

                case 3:
                    reservationOptions(); 
                    break;

                case 4:
                    vehicleOptions();
                    break;

                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    flag = false; 
                    break;

                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

 
    public  static void adminOptions() throws AdminNotFoundException, InvalidInputException,InputMismatchCustomException {
        boolean flag = true;
        IAdminService service = new AdminService(); 

        while (flag) {
            
            System.out.println("\n***** Admin Options *****");
            System.out.println("1. Get Admin By ID");
            System.out.println("2. Get Admin By Username");
            System.out.println("3. Register Admin");
            System.out.println("4. Update Admin");
            System.out.println("5. Delete Admin");
            System.out.println("6. Back to Main Menu");

            int choice = scanner.nextInt();

            switch (choice) {
       
            case 1:
                try {
                    System.out.println("Enter Admin ID:");
                    int adminId = getValidatedIntInput(); // Validating input
                    Admin foundAdminById = service.getAdminById(adminId);
                    if (foundAdminById != null) {
                        System.out.println(foundAdminById);
                    } else {
                        System.err.println("Admin not found.");
                    }
                } catch (InputMismatchCustomException e) {
                    System.err.println(e.getMessage() != null ? e.getMessage() : " ");  // Check if message is null
                }
                break;
                case 2:
                    System.out.println("Enter Username:");
                    String username = scanner.next();
                    Admin foundAdminByUsername = service.getAdminByUsername(username); 
                    if (foundAdminByUsername != null) {
                        System.out.println(foundAdminByUsername);
                    } else {
                        System.err.println("Admin not found.");
                    }
                    break;

                case 3:
                    Admin adminToRegister = readAdminData(); 
                    service.registerAdmin(adminToRegister); 
                   
                    break;

                case 4:
                    System.out.println("Enter Admin ID to update:");
                    int updateId = scanner.nextInt();
                    Admin adminToUpdate = readAdminData(); 
                    adminToUpdate.setAdminId(updateId);
                    service.updateAdmin(adminToUpdate);
                   
                    break;

                case 5:
                    System.out.println("Enter Admin ID to delete:");
                    int deleteId = scanner.nextInt();
                    service.deleteAdmin(deleteId); 
                   
                    break;

                case 6:
                    flag = false; 
                    break;

                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void customerOptions() {
        boolean flag = true;
        ICustomerService service = new CustomerService(); 

        while (flag) {
            System.out.println("\n***** Customer Options *****");
            System.out.println("1. Get Customer By ID");
            System.out.println("2. Register Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back to Main Menu");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Customer ID:");
                    int customerId = scanner.nextInt();
                    Customer foundCustomer = service.getCustomerById(customerId);
                    if (foundCustomer != null) {
                        System.out.println(foundCustomer);
                    } else {
                        System.err.println("Customer not found.");
                    }
                    break;
                case 2:
                    Customer customerToRegister = readCustomerData();
                    service.registerCustomer(customerToRegister); 
                   
                    break;
                case 3:
                    System.out.println("Enter Customer ID to update:");
                    int updateId = scanner.nextInt();
                    Customer customerToUpdate = readCustomerData(); 
                    customerToUpdate.setCustomerId(updateId); 
                    service.updateCustomer(customerToUpdate); 
                    
                    break;
                case 4:
                    System.out.println("Enter Customer ID to delete:");
                    int deleteId = scanner.nextInt();
                    service.deleteCustomer(deleteId); 
                   
                    break;
                case 5:
                    flag = false; 
                    break;
                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

  
   
    public static void reservationOptions() throws ReservationException, InvalidInputException {
        boolean flag = true;
        IReservationService service = new ReservationService(); 

        while (flag) {
            System.out.println("\n***** Reservation Options *****");
            System.out.println("1. Get Reservation By ID");
            System.out.println("2. Get Reservations By Customer ID");
            System.out.println("3. Create Reservation");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Back to Main Menu");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Reservation ID:");
                    int reservationId = scanner.nextInt();
                    Reservation reservation = service.getReservationById(reservationId);
                    if (reservation != null) {
                        System.out.println(reservation);
                    } else {
                        System.err.println("Reservation not found.");
                    }
                    break;

                case 2:
                    System.out.println("Enter Customer ID:");
                    int customerId = scanner.nextInt();
                    List<Reservation> reservations = service.getReservationsByCustomerId(customerId);
                    if (!reservations.isEmpty()) {
                        reservations.forEach(System.out::println);
                    } else {
                        System.err.println("No reservations found for this customer.");
                    }
                    break;

                case 3:
                    Reservation newReservation = readReservationData();
                    service.createReservation(newReservation);
                    System.out.println("Reservation created successfully.");
                    break;

                case 4:
                    System.out.println("Enter Reservation ID to update:");
                    int updateId = scanner.nextInt();
                    Reservation updatedReservation = readReservationData();
                    updatedReservation.setReservationId(updateId);
                    service.updateReservation(updatedReservation);
                    
                    break;

                case 5:
                    System.out.println("Enter Reservation ID to cancel:");
                    int cancelId = scanner.nextInt();
                    service.cancelReservation(cancelId);
                    
                    break;

                case 6:
                    flag = false;
                    break;

                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void vehicleOptions() throws VehicleNotFoundException {
        boolean flag = true;

        while (flag) {
            System.out.println("\n***** Vehicle Options *****");
            System.out.println("1. Get Vehicle By ID");
            System.out.println("2. Get Available Vehicles");
            System.out.println("3. Add Vehicle");
            System.out.println("4. Update Vehicle");
            System.out.println("5. Remove Vehicle");
            System.out.println("6. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            IVehicleService vehicleService = new VehicleService();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle ID: ");
                    int vehicleId = scanner.nextInt();
                    Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

                    if (vehicle != null) {
                        System.out.println(vehicle);
                    } else {
                        System.err.println("Vehicle not found.");
                    }
                    break;

                case 2:
                    List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();

                    if (!availableVehicles.isEmpty()) {
                        for (Vehicle v : availableVehicles) {
                            System.out.println(v);
                        }
                    } else {
                        System.err.println("No available vehicles found.");
                    }
                    break;

                case 3:
                    Vehicle newVehicle = readVehicleData();
                    vehicleService.addVehicle(newVehicle);
                   
                    break;

                case 4:
                    System.out.print("Enter Vehicle ID to update: ");
                    int updateId = scanner.nextInt();

                    Vehicle updatedVehicle = readVehicleData();
                    updatedVehicle.setVehicleId(updateId);
                    vehicleService.updateVehicle(updatedVehicle);

                  
                    break;

                case 5:
                    System.out.print("Enter Vehicle ID to remove: ");
                    int removeId = scanner.nextInt();
                    vehicleService.removeVehicle(removeId);
                    
                    break;

                case 6:
                    flag = false;
                    break;

                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    

    public static Admin readAdminData() {
        Admin admin = new Admin(); 
        System.out.println("Enter Admin ID:");
        admin.setAdminId(scanner.nextInt());
        System.out.println("Enter First Name:");
        admin.setFirstName(scanner.next());
        System.out.println("Enter Last Name:");
        admin.setLastName(scanner.next());
        System.out.println("Enter Email:");
        admin.setEmail(scanner.next());
        System.out.println("Enter Phone Number:");
        admin.setPhoneNumber(scanner.next());
        System.out.println("Enter Username:");
        admin.setUsername(scanner.next());
        System.out.println("Enter Password:");
        admin.setPassword(scanner.next());
        System.out.println("Enter Role:");
        admin.setRole(scanner.next());
        System.out.println("Enter Join Date (YYYY-MM-DD):");
        admin.setJoinDate(scanner.next());
        return admin; 
    }
    
    public static Customer readCustomerData() {
        Customer customer = new Customer(); 
        System.out.println("Enter Customer ID:");
        customer.setCustomerId(scanner.nextInt());
        System.out.println("Enter First Name:");
        customer.setFirstName(scanner.next());
        System.out.println("Enter Last Name:");
        customer.setLastName(scanner.next());
        System.out.println("Enter Email:");
        customer.setEmail(scanner.next());
        System.out.println("Enter Phone Number:");
        customer.setPhoneNumber(scanner.next());
        
        System.out.println("Enter Address:");
        scanner.nextLine(); 
        customer.setAddress(scanner.nextLine());

        System.out.println("Enter Username:");
        customer.setUsername(scanner.next());

        System.out.println("Enter Password:");
        customer.setPassword(scanner.next());
        
        String registrationDate = LocalDate.now().toString();
        customer.setRegistrationDate(registrationDate); 
        return customer; 
    }
    
    public static Vehicle readVehicleData() {
        System.out.println("Enter Vehicle ID:");
        int vehicleId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter Vehicle Model:");
        String model = scanner.nextLine(); 

        System.out.println("Enter Vehicle Make:");
        String make = scanner.nextLine(); 

        System.out.println("Enter Vehicle Year:");
        int year = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter Vehicle Color:");
        String color = scanner.nextLine(); 

        System.out.println("Enter Registration Number:");
        String registrationNumber = scanner.nextLine(); 

        System.out.println("Is the vehicle available (true/false)?");
        boolean availability = scanner.nextBoolean();
        scanner.nextLine(); 

        System.out.println("Enter Daily Rate:");
        double dailyRate = scanner.nextDouble();

        return new Vehicle(vehicleId, model, make, year, color, registrationNumber, availability, dailyRate);
    }
    
    public static Reservation readReservationData() {
        System.out.println("Enter Reservation ID:");
        int reservationId = scanner.nextInt();

        System.out.println("Enter Customer ID:");
        int customerId = scanner.nextInt();

        System.out.println("Enter Vehicle ID:");
        int vehicleId = scanner.nextInt();

        System.out.println("Enter Start Date (YYYY-MM-DD):");
        String startDate = scanner.next();

        System.out.println("Enter End Date (YYYY-MM-DD):");
        String endDate = scanner.next();

        System.out.println("Enter Total Cost:");
        double totalCost = scanner.nextDouble();

        System.out.println("Enter Status:");
        String status = scanner.next();

        return new Reservation(reservationId, customerId, vehicleId, startDate, endDate, totalCost, status);
    }
    private static int getValidatedIntInput() throws InputMismatchCustomException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); 
            throw new InputMismatchCustomException("Invalid input! Please enter a valid integer.");
        }
    }

}
