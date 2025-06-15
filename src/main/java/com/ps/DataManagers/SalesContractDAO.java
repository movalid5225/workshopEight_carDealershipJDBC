package com.ps.DataManagers;
import java.sql.*;
import javax.sql.DataSource;
import java.time.LocalDate;

public class SalesContractDAO {
    private final DataSource dataSource;

    public SalesContractDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveSalesContract(String vin, LocalDate saleDate, double salePrice, String customerName) {
        String query = "INSERT INTO Sales_contracts (VIN, sale_date, sale_price, customer_name) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)
        ) {
            preparedStatement.setString(1, vin);
            preparedStatement.setDate(2, Date.valueOf(saleDate));
            preparedStatement.setDouble(3, salePrice);
            preparedStatement.setString(4, customerName);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Sales contract saved successfully!");
            } else {
                System.out.println("Failed to save sales contract.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR IN SalesDao.saveSalesContract");
        }
    }
}
