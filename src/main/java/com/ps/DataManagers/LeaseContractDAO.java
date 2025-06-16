package com.ps.DataManagers;
import java.sql.*;
import javax.sql.DataSource;
import java.time.LocalDate;
import static com.ps.Main.scanner;

public class LeaseContractDAO {
    private final DataSource dataSource;

    public LeaseContractDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveLeaseContract() {

        System.out.print("Enter VIN: ");
        String vinLease = scanner.nextLine();

        System.out.print("Enter customer name: ");
        String nameLease = scanner.nextLine();

        System.out.print("Enter monthly payment: ");
        double monthlyPayment = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter lease start date (YYYY-MM-DD): ");
        LocalDate leaseStart = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter lease end date (YYYY-MM-DD): ");
        LocalDate leaseEnd = LocalDate.parse(scanner.nextLine());

        String query = "INSERT INTO Lease_contracts (VIN, lease_start_date, lease_end_date, monthly_payment, customer_name) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)
        ) {
            preparedStatement.setString(1, vinLease);
            preparedStatement.setDate(2, Date.valueOf(leaseStart));
            preparedStatement.setDate(3, Date.valueOf(leaseEnd));
            preparedStatement.setDouble(4, monthlyPayment);
            preparedStatement.setString(5, nameLease);

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
