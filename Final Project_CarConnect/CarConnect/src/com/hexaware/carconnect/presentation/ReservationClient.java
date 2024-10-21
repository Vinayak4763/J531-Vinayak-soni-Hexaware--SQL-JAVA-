/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Reservation presentation
 * 21/10/2024
 */

package com.hexaware.carconnect.presentation;

import java.util.List;
import java.util.Scanner;

import com.hexaware.carconnect.entity.Reservation;
import com.hexaware.carconnect.exceptions.InvalidInputException;
import com.hexaware.carconnect.exceptions.ReservationException;
import com.hexaware.carconnect.service.IReservationService;
import com.hexaware.carconnect.service.ReservationService;

public class ReservationClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ReservationException, InvalidInputException {

        boolean flag = true;

      
        IReservationService service = new ReservationService();

        while (flag) {
            // Display menu options
            System.out.println("\n***** Reservation Options *****");
            System.out.println("1. Get Reservation By ID");
            System.out.println("2. Get Reservations By Customer ID");
            System.out.println("3. Create Reservation");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
           

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
                        for (Reservation r : reservations) {
                            System.out.println(r);
                        }
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
                    // Update reservation
                    System.out.println("Enter Reservation ID to update:");
                    int updateId = scanner.nextInt();
                    Reservation updatedReservation = readReservationData();
                    updatedReservation.setReservationId(updateId);
                    service.updateReservation(updatedReservation);
                    System.out.println("Reservation updated successfully.");
                    break;

                case 5:
                    // Cancel reservation
                    System.out.println("Enter Reservation ID to cancel:");
                    int cancelId = scanner.nextInt();
                    service.cancelReservation(cancelId);
                    System.out.println("Reservation cancelled successfully.");
                    break;


                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
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

        // Return a new Reservation object with the provided data
        return new Reservation(reservationId, customerId, vehicleId, startDate, endDate, totalCost, status);
    }
}
