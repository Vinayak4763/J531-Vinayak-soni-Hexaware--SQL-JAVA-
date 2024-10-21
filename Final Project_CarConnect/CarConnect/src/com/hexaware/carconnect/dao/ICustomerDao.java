/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Customer Interface
 * 21/10/2024
 */

package com.hexaware.carconnect.dao;

import com.hexaware.carconnect.entity.Customer;

public interface ICustomerDao {
	 // Fetch a customer by their unique ID
    Customer getCustomerById(int customerId);

    // Fetch a customer by their unique username//
    Customer getCustomerByUsername(String username);

    // Register a new customer with their details
    void registerCustomer(Customer customerData);

    // Update the details of an existing customer
    void updateCustomer(Customer customerData);

    // Delete a customer by their ID
    void deleteCustomer(int customerId);
}
