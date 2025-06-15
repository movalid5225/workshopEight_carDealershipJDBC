package com.ps.DataManagers;
import java.sql.*;
import javax.sql.DataSource;
import java.time.LocalDate;

public class LeaseContractDAO {
    private final DataSource dataSource;

    public LeaseContractDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveLeaseContract(String vin, LocalDate leaseStart, LocalDate leaseEnd, double monthlyPayment, String customerName) {
        String query = "INSERT INTO Lease_contracts (VIN, lease_start_date, lease_end_date, monthly_payment, customer_name) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)
        ) {
            preparedStatement.setString(1, vin);
            preparedStatement.setDate(2, Date.valueOf(leaseStart));
            preparedStatement.setDate(3, Date.valueOf(leaseEnd));
            preparedStatement.setDouble(4, monthlyPayment);
            preparedStatement.setString(5, customerName);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Lease contract saved successfully!");
            } else {
                System.out.println("Failed to save lease contract.");
            }

        } catch (SQLException e) {
            System.out.println("Error in LeaseDao.saveLeaseContract");
        }
    }
}
