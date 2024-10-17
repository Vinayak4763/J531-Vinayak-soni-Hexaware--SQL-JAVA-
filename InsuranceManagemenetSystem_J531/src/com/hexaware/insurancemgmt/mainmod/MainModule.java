
/*
 * Author: J531 Vinayak soni
 * Batch :java 5
 * Insurance management system(java evaluation) 
 */





package com.hexaware.insurancemgmt.mainmod;

import com.hexaware.insurancemgmt.service.PolicyServiceImpl;
import com.hexaware.insurancemgmt.dao.PolicyDaoImpl;
import com.hexaware.insurancemgmt.entity.Policy;
import com.hexaware.insurancemgmt.exception.PolicyNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) throws PolicyNotFoundException {
        Scanner scanner = new Scanner(System.in);
        PolicyServiceImpl policyService = new PolicyServiceImpl(new PolicyDaoImpl());

        while (true) {
            System.out.println("\n--- Insurance Management System ---");
            System.out.println("1. Add Policy");
            System.out.println("2. View Policy by ID");
            System.out.println("3. View All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	System.out.print("Enter Policy ID: "); // Prompting for policyId
                    int policyId = scanner.nextInt(); // Getting user input for policyId
                    System.out.print("Enter Policy Name: ");
                    String policyName = scanner.next();
                    System.out.print("Enter Premium Amount: ");
                    double premiumAmount = scanner.nextDouble();
                    System.out.print("Enter Coverage Amount: ");
                    double coverageAmount = scanner.nextDouble();

                    // Create the Policy object with the updated constructor
                    Policy policy = new Policy(policyId, policyName, premiumAmount, coverageAmount);  
                    if (policyService.addPolicy(policy)) {
                        System.out.println("Policy added successfully.");
                    } else {
                        System.out.println("Failed to add policy.");
                    }
                    break;
                case 2:
                	 System.out.print("Enter Policy ID: ");
                     int searchId = scanner.nextInt();
                     try {
                         Policy foundPolicy = policyService.getPolicyById(searchId);
                         System.out.println("Policy Details: " + foundPolicy);
                     } catch (PolicyNotFoundException e) {
                         System.out.println("Error: " + e.getMessage());
                     }
                     break;
                case 3:
                    List<Policy> policies = policyService.getAllPolicies();
                    if (policies.isEmpty()) {
                        System.out.println("No policies found.");
                    } else {
                        System.out.println("All Policies: ");
                        for (Policy p : policies) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter Policy ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter New Policy Name: ");
                    String newPolicyName = scanner.next();
                    System.out.print("Enter New Premium Amount: ");
                    double newPremium = scanner.nextDouble();
                    System.out.print("Enter New Coverage Amount: ");
                    double newCoverage = scanner.nextDouble();

                    Policy updatedPolicy = new Policy(updateId, newPolicyName, newPremium, newCoverage);
                    if (policyService.updatePolicy(updatedPolicy)) {
                        System.out.println("Policy updated successfully.");
                    } else {
                        System.out.println("Update failed.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Policy ID to delete: ");
                    int deleteId = scanner.nextInt();
                    if (policyService.removePolicy(deleteId)) {
                        System.out.println("Policy deleted successfully.");
                    } else {
                        System.out.println("Delete failed.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
