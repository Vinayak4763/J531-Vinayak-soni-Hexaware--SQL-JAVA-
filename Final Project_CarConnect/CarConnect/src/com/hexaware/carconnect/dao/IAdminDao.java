/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Admin Interface
 * 21/10/2024
 */

package com.hexaware.carconnect.dao;

import com.hexaware.carconnect.entity.Admin;

public interface IAdminDao {
	// Fetch admin details by their unique ID
    Admin getAdminById(int adminId);

    // Fetch admin details by their username
    Admin getAdminByUsername(String username);

    // Register a new admin
    void registerAdmin(Admin adminData);

    // Update an existing admin's details
    void updateAdmin(Admin adminData);

    // Delete an admin by their unique ID
    void deleteAdmin(int adminId);

}
