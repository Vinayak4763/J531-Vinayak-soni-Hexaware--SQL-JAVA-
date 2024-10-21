/* Romi Kumar Singh and Vinayak Soni
 * CarConnect ReservationDao
 * 21/10/2024
 */

package com.hexaware.carconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.carconnect.entity.Reservation;
import com.hexaware.carconnect.util.DBUtil;

public class ReservationDao implements IReservationDao {

    private Connection conn;

    // Constructor to initialize DB connection
    public ReservationDao() {
        conn = DBUtil.getConnection();
    }

    // Helper method to map ResultSet to Reservation object
    private Reservation mapResultSetToReservation(ResultSet rs) throws SQLException {
        int reservationId = rs.getInt("reservationId");
        int customerId = rs.getInt("customerId");
        int vehicleId = rs.getInt("vehicleId");
        String startDate = rs.getString("startDate");
        String endDate = rs.getString("endDate");
        double totalCost = rs.getDouble("totalCost");
        String status = rs.getString("status");
        return new Reservation(reservationId, customerId, vehicleId, startDate, endDate, totalCost, status);
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        Reservation reservation = null;
        String query = "SELECT * FROM Reservation WHERE reservationId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, reservationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                reservation = mapResultSetToReservation(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }

    @Override
    public List<Reservation> getReservationsByCustomerId(int customerId) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation WHERE customerId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void createReservation(Reservation reservationData) {
        String query = "INSERT INTO Reservation (customerId, vehicleId, startDate, endDate, totalCost, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, reservationData.getCustomerId());
            pstmt.setInt(2, reservationData.getVehicleId());
            pstmt.setString(3, reservationData.getStartDate());
            pstmt.setString(4, reservationData.getEndDate());
            pstmt.setDouble(5, reservationData.getTotalCost());
            pstmt.setString(6, reservationData.getStatus());

            pstmt.executeUpdate();
            System.out.println("Reservation created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateReservation(Reservation reservationData) {
        String query = "UPDATE Reservation SET customerId = ?, vehicleId = ?, startDate = ?, endDate = ?, "
                + "totalCost = ?, status = ? WHERE reservationId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, reservationData.getCustomerId());
            pstmt.setInt(2, reservationData.getVehicleId());
            pstmt.setString(3, reservationData.getStartDate());
            pstmt.setString(4, reservationData.getEndDate());
            pstmt.setDouble(5, reservationData.getTotalCost());
            pstmt.setString(6, reservationData.getStatus());
            pstmt.setInt(7, reservationData.getReservationId());

            pstmt.executeUpdate();
            System.out.println("Reservation updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelReservation(int reservationId) {
        String query = "DELETE FROM Reservation WHERE reservationId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, reservationId);

            pstmt.executeUpdate();
            System.out.println("Reservation canceled successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}