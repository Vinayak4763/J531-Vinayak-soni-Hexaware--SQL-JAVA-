/* Romi Kumar Singh and Vinayak Soni
 * CarConnect CustomerDao
 * 21/10/2024
 */

package com.hexaware.carconnect.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hexaware.carconnect.entity.Customer;
import com.hexaware.carconnect.util.*;

public class CustomerDao implements ICustomerDao {

    private Connection conn;

    public CustomerDao() {
        conn = DBUtil.getConnection(); // Get connection from utility class
    }

    // Helper method to map ResultSet to Customer object
    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        int customerId = rs.getInt("customerId");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phoneNumber");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String address = rs.getString("address");
        String registrationDate = rs.getString("registrationDate");

        return new Customer(customerId, firstName, lastName, email, phoneNumber, username, password, address, registrationDate);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        String selectById = "SELECT * FROM Customer WHERE customerId = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(selectById);
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = mapResultSetToCustomer(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = null;
        String selectByUsername = "SELECT * FROM Customer WHERE username = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(selectByUsername);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = mapResultSetToCustomer(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public void registerCustomer(Customer customerData) {
        String insertCustomer = "INSERT INTO Customer (firstName, lastName, email, phoneNumber, username, password, address, registrationDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(insertCustomer);

            pstmt.setString(1, customerData.getFirstName());
            pstmt.setString(2, customerData.getLastName());
            pstmt.setString(3, customerData.getEmail());
            pstmt.setString(4, customerData.getPhoneNumber());
            pstmt.setString(5, customerData.getUsername());
            pstmt.setString(6, customerData.getPassword());
            pstmt.setString(7, customerData.getAddress());
            pstmt.setString(8, customerData.getRegistrationDate());

            pstmt.executeUpdate();

            System.out.println("Customer registered successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error registering customer");
        }
    }

    @Override
    public void updateCustomer(Customer customerData) {
        String query = "UPDATE Customer SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, username = ?, password = ?, address = ? WHERE customerId = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, customerData.getFirstName());
            pstmt.setString(2, customerData.getLastName());
            pstmt.setString(3, customerData.getEmail());
            pstmt.setString(4, customerData.getPhoneNumber());
            pstmt.setString(5, customerData.getUsername());
            pstmt.setString(6, customerData.getPassword());
            pstmt.setString(7, customerData.getAddress());
            pstmt.setInt(8, customerData.getCustomerId());
            pstmt.executeUpdate();

            System.out.println("Customer updated successfully: " + customerData.getUsername());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void deleteCustomer(int customerId) {
        String query = "DELETE FROM Customer WHERE customerId = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query); // Prepare the SQL statement
            stmt.setInt(1, customerId); // Set the customerId parameter
            stmt.executeUpdate(); // Execute the delete operation

            System.out.println("Customer deleted successfully: " + customerId);

        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace if an exception occurs
        }
    }
}