/* Romi Kumar Singh and Vinayak Soni
 * CarConnect customer presentation
 * 21/10/2024
 */

package com.hexaware.carconnect.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.hexaware.carconnect.entity.Customer;
import com.hexaware.carconnect.service.CustomerService;
import com.hexaware.carconnect.service.ICustomerService;

public class CustomerClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ICustomerService service = new CustomerService();
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("\n***** Customer Options *****");
                System.out.println("1. Get Customer By ID");
                System.out.println("2. Get Customer By Username");
                System.out.println("3. Register Customer");
                System.out.println("4. Update Customer");
                System.out.println("5. Delete Customer");
                System.out.println("6. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter Customer ID:");
                        int customerId = scanner.nextInt();
                        Customer customer = service.getCustomerById(customerId);
                        System.out.println(customer != null ? customer : "Customer not found.");
                        break;
                    case 2:
                        System.out.println("Enter Username:");
                        String username = scanner.next();
                        customer = service.getCustomerByUsername(username);
                        System.out.println(customer != null ? customer : "Customer not found.");
                        break;
                    case 3:
                        Customer newCustomer = readCustomerData();
                        service.registerCustomer(newCustomer);
                        System.out.println("Customer registered successfully.");
                        break;
                    case 4:
                        System.out.println("Enter Customer ID to update:");
                        int updateId = scanner.nextInt();
                        Customer updatedCustomer = readCustomerData();
                        updatedCustomer.setCustomerId(updateId);
                        service.updateCustomer(updatedCustomer);
                        System.out.println("Customer updated successfully.");
                        break;
                    case 5:
                        System.out.println("Enter Customer ID to delete:");
                        int deleteId = scanner.nextInt();
                        service.deleteCustomer(deleteId);
                        System.out.println("Customer deleted successfully.");
                        break;
                    case 6:
                        flag = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.err.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close();
    }

    public static Customer readCustomerData() {
        Customer customer = new Customer();
        try {
            System.out.println("Enter First Name:");
            customer.setFirstName(scanner.next());
            System.out.println("Enter Last Name:");
            customer.setLastName(scanner.next());
            System.out.println("Enter Email:");
            customer.setEmail(scanner.next());
            System.out.println("Enter Phone Number:");
            customer.setPhoneNumber(scanner.next());
            System.out.println("Enter Username:");
            customer.setUsername(scanner.next());
            System.out.println("Enter Password:");
            customer.setPassword(scanner.next());
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please try again.");
            scanner.nextLine(); // Clear input buffer
        }
        return customer;
    }
}
