package com.ps.DataManagers;
import com.ps.Models.Vehicle;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static com.ps.Main.scanner;

public class VehicleDAO {
    private final DataSource dataSource;

    public VehicleDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM Vehicles";

        try(
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                String vin = resultSet.getString("VIN");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                double price = resultSet.getDouble("price");
                String type = resultSet.getString("type");
                int mileage = resultSet.getInt("mileage");

                Vehicle vehicle = new Vehicle(vin, make, model, year, color, price,type, mileage);
                vehicles.add(vehicle);
            }
        }
        catch(Exception e){
            System.out.println("CHECK getAllVehicles() METHOD");
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByPriceRange(){
        List<Vehicle> vehicles = new ArrayList<>();

        System.out.print("Lowest Price: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Highest Price: ");
        double maxPrice = scanner.nextDouble();

        String query = "SELECT * FROM  Vehicles " +
                "WHERE price BETWEEN ? AND ? " +
                "ORDER BY price ASC";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
                preparedStatement.setDouble(1, minPrice);
                preparedStatement.setDouble(2, maxPrice);

                try(ResultSet resultSet = preparedStatement.executeQuery();)
                {
                    while (resultSet.next()) {
                        String vin = resultSet.getString("VIN");
                        String make = resultSet.getString("make");
                        String model = resultSet.getString("model");
                        int year = resultSet.getInt("year");
                        String color = resultSet.getString("color");
                        double price = resultSet.getDouble("price");
                        String type = resultSet.getString("type");
                        int mileage = resultSet.getInt("mileage");

                        Vehicle vehicle = new Vehicle(vin, make, model, year, color, price, type, mileage);
                        vehicles.add(vehicle);
                    }
                }
        }catch (Exception e){
            System.out.println("CHECK getVehiclesByRange() METHOD");
        }
        return vehicles;
    }



}
