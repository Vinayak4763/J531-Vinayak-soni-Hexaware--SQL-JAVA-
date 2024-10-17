package com.hexaware.insurancemgmt.dao;

import com.hexaware.insurancemgmt.entity.Policy;
import com.hexaware.insurancemgmt.exception.PolicyNotFoundException;
import com.hexaware.insurancemgmt.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyDaoImpl implements IPolicyDao {

    @Override
    public boolean createPolicy(Policy policy) {
        String sql = "INSERT INTO Policy (policyName, premiumAmount, coverageAmount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, policy.getPolicyName());
            pstmt.setDouble(2, policy.getPremiumAmount());
            pstmt.setDouble(3, policy.getCoverageAmount());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        String sql = "SELECT * FROM Policy WHERE policyId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, policyId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Policy(rs.getInt("policyId"),
                                  rs.getString("policyName"),
                                  rs.getDouble("premiumAmount"),
                                  rs.getDouble("coverageAmount"));
            } else {
                
                throw new PolicyNotFoundException("Policy with ID " + policyId + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
        return null; 
    }
    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String sql = "SELECT * FROM Policy";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                policies.add(new Policy(rs.getInt("policyId"),
                                        rs.getString("policyName"),
                                        rs.getDouble("premiumAmount"),
                                        rs.getDouble("coverageAmount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        String sql = "UPDATE Policy SET policyName = ?, premiumAmount = ?, coverageAmount = ? WHERE policyId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, policy.getPolicyName());
            pstmt.setDouble(2, policy.getPremiumAmount());
            pstmt.setDouble(3, policy.getCoverageAmount());
            pstmt.setInt(4, policy.getPolicyId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        String sql = "DELETE FROM Policy WHERE policyId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, policyId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
