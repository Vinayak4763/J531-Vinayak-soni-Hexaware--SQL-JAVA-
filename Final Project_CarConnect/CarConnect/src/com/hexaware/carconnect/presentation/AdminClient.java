/* Romi Kumar Singh and Vinayak Soni
 * CarConnect admin presentation
 * 21/10/2024
 */

package com.hexaware.carconnect.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.hexaware.carconnect.entity.Admin;
import com.hexaware.carconnect.exceptions.AdminNotFoundException;
import com.hexaware.carconnect.exceptions.InputMismatchCustomException;
import com.hexaware.carconnect.exceptions.InvalidInputException;
import com.hexaware.carconnect.service.AdminService;
import com.hexaware.carconnect.service.IAdminService;

public class AdminClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        IAdminService service = new AdminService(); 

        while (flag) {
            try {
                
                System.out.println("\n***** Admin Options *****");
                System.out.println("1. Get Admin By ID");
                System.out.println("2. Get Admin By Username");
                System.out.println("3. Register Admin");
                System.out.println("4. Update Admin");
                System.out.println("5. Delete Admin");
                System.out.println("6. Exit");

                int choice = getValidatedIntInput(); 

                switch (choice) {
                    case 1:
                        System.out.println("Enter Admin ID:");
                        int adminId = getValidatedIntInput(); 
                        Admin foundAdminById = service.getAdminById(adminId);
                        if (foundAdminById != null) {
                            System.out.println(foundAdminById);
                        } else {
                            System.err.println("Admin not found.");
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
                        System.out.println("Admin registered successfully.");
                        break;

                    case 4:
                        System.out.println("Enter Admin ID to update:");
                        int updateId = getValidatedIntInput();
                        Admin adminToUpdate = readAdminData();
                        adminToUpdate.setAdminId(updateId);
                        service.updateAdmin(adminToUpdate);
                        System.out.println("Admin updated successfully.");
                        break;

                    case 5:
                        System.out.println("Enter Admin ID to delete:");
                        int deleteId = getValidatedIntInput();
                        service.deleteAdmin(deleteId);
                        System.out.println("Admin deleted successfully.");
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        flag = false;
                        break;

                    default:
                        System.err.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchCustomException e) {
                
            } catch (AdminNotFoundException | InvalidInputException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static int getValidatedIntInput() throws InputMismatchCustomException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); 
            throw new InputMismatchCustomException("Invalid input! Please enter a valid integer.");
        }
    }

    public static Admin readAdminData() throws InputMismatchCustomException {
        Admin admin = new Admin(); // Create a new Admin object
        System.out.println("Enter Admin ID:");
        admin.setAdminId(getValidatedIntInput()); // Validated input for ID
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
}
