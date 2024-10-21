/* Romi Kumar Singh and Vinayak Soni
 * CarConnect AdminDao
 * 21/10/2024
 */

package com.hexaware.carconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.hexaware.carconnect.entity.Admin;
import com.hexaware.carconnect.util.DBUtil;

import java.sql.SQLException;

public  class AdminDao implements IAdminDao {
	
	private Connection conn;

	public AdminDao(){

		conn = DBUtil.getConnection();

	}
	//reuse helper method for resultset//
	private Admin mapResultSetToAdmin(ResultSet rs) throws SQLException {
	    int adminId = rs.getInt("adminId");
	    String firstName = rs.getString("firstName");
	    String lastName = rs.getString("lastName");
	    String email = rs.getString("email");
	    String phoneNumber = rs.getString("phoneNumber");
	    String username = rs.getString("username");
	    String password = rs.getString("password");
	    String role = rs.getString("role");
	    String joinDate = rs.getString("joinDate");

	    return new Admin(adminId, firstName, lastName, email, phoneNumber, username, password, role, joinDate);
	}

	@Override
	public Admin getAdminById(int adminId) {
		
        Admin admin = null;
        String selectById = "SELECT * FROM Admin WHERE adminId = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(selectById);
            pstmt.setInt(1, adminId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                admin = mapResultSetToAdmin(rs); //calling the helper function//
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
	
	}

	@Override
	public Admin getAdminByUsername(String username) {
		Admin admin = null;
	    String selectByUsername = "SELECT * FROM Admin WHERE username = ?";

	    try {
	        PreparedStatement pstmt = conn.prepareStatement(selectByUsername);
	        pstmt.setString(1, username);  
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	           
	            admin = mapResultSetToAdmin(rs);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return admin;
	}

	@Override
	public void registerAdmin(Admin adminData) {
		  String insertAdmin = "INSERT INTO Admin (firstName, lastName, email, phoneNumber, username, password, role, joinDate) "
		            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		    try {
		        PreparedStatement pstmt = conn.prepareStatement(insertAdmin);
		        
		       
		        pstmt.setString(1, adminData.getFirstName());
		        pstmt.setString(2, adminData.getLastName());
		        pstmt.setString(3, adminData.getEmail());
		        pstmt.setString(4, adminData.getPhoneNumber());
		        pstmt.setString(5, adminData.getUsername());
		        pstmt.setString(6, adminData.getPassword());
		        pstmt.setString(7, adminData.getRole());
		        pstmt.setString(8, adminData.getJoinDate());

		        
		        pstmt.executeUpdate();

		        System.out.println("Admin registered successfully!");

		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.err.println("Error registering admin");
		    }
		
	}

	@Override
	public void updateAdmin(Admin adminData) {
		String query = "UPDATE Admin SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, username = ?, password = ?, role = ? WHERE adminId = ?";
        try {
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, adminData.getFirstName());
            stmt.setString(2, adminData.getLastName());
            stmt.setString(3, adminData.getEmail());
            stmt.setString(4, adminData.getPhoneNumber());
            stmt.setString(5, adminData.getUsername());
            stmt.setString(6, adminData.getPassword());
            stmt.setString(7, adminData.getRole());
            stmt.setInt(8, adminData.getAdminId());
            stmt.executeUpdate();
            System.out.println("Admin updated successfully: " + adminData.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	@Override
	public void deleteAdmin(int adminId) {
	    String query = "DELETE FROM Admin WHERE adminId = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, adminId);
	        stmt.executeUpdate();
	        System.out.println("Admin deleted successfully: " + adminId);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}
	
}
