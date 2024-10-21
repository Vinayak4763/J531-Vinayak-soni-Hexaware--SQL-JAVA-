/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Customer Interface service
 * 21/10/2024
 */

package com.hexaware.carconnect.service;

import com.hexaware.carconnect.entity.Customer;

public interface ICustomerService {
	
    Customer getCustomerById(int customerId);

    
    Customer getCustomerByUsername(String username);

    
    void registerCustomer(Customer customerData);

   
    void updateCustomer(Customer customerData);

   
    void deleteCustomer(int customerId);

}
