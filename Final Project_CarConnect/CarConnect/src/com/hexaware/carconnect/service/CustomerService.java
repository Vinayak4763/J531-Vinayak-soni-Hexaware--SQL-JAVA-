/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Customer Service
 * 21/10/2024
 */

package com.hexaware.carconnect.service;

import com.hexaware.carconnect.dao.CustomerDao;
import com.hexaware.carconnect.dao.ICustomerDao;
import com.hexaware.carconnect.entity.Customer;

public class CustomerService implements ICustomerService {

    private ICustomerDao customerDao; 

    
    public CustomerService() {
        this.customerDao = new CustomerDao(); //object instantiating//
    }

    @Override
    public Customer getCustomerById(int customerId) {
        
        return customerDao.getCustomerById(customerId);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        
        return customerDao.getCustomerByUsername(username);
    }

    @Override
    public void registerCustomer(Customer customerData) {
       
        customerDao.registerCustomer(customerData);
    }

    @Override
    public void updateCustomer(Customer customerData) {
        
        customerDao.updateCustomer(customerData);
    }

    @Override
    public void deleteCustomer(int customerId) {
        
        customerDao.deleteCustomer(customerId);
    }
}