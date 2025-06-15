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

    public List<Vehicle> getVehiclesByMakeModel(){
        List<Vehicle> vehicles = new ArrayList<>();

        System.out.print("Enter make: ");
        String userMake = scanner.nextLine();

        System.out.print("Enter model: ");
        String userModel = scanner.nextLine();

        String query = "SELECT * FROM  Vehicles " +
                "WHERE make = ? AND model = ? " +
                "ORDER BY price ASC";


        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userMake);
            preparedStatement.setString(2, userModel);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
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
            System.out.println("CHECK getVehiclesByMakeModel() METHOD");
        }

        return vehicles;
    }

    public List<Vehicle> getVehiclesByYearRange(){
        List<Vehicle> vehicles = new ArrayList<>();

        System.out.print("Enter lowest year: ");
        int minYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter highest year: ");
        int maxYear = Integer.parseInt(scanner.nextLine());

        String query = "SELECT * FROM  Vehicles " +
                "WHERE year BETWEEN ? AND ? " +
                "ORDER BY price ASC";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1,minYear);
            preparedStatement.setInt(2,maxYear);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
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
            System.out.println("CHECK getByYearRange() METHOD");
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByColor() {
        List<Vehicle> vehicles = new ArrayList<>();

        System.out.print("Enter color: ");
        String userColor = scanner.nextLine();

        String query = "SELECT * FROM Vehicles WHERE color = ? ORDER BY price ASC";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, userColor);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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

        } catch (Exception e) {
            System.out.println("CHECK getVehiclesByColor() METHOD");
        }

        return vehicles;
    }

    public List<Vehicle> getVehiclesByMileageRange() {
        List<Vehicle> vehicles = new ArrayList<>();

        System.out.print("Enter minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();
        scanner.nextLine();

        String query = "SELECT * FROM Vehicles WHERE mileage BETWEEN ? AND ? ORDER BY mileage ASC";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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

        } catch (Exception e) {
            System.out.println("CHECK getVehiclesByMileageRange() METHOD");
        }

        return vehicles;
    }

    public List<Vehicle> getVehiclesByType() {
        List<Vehicle> vehicles = new ArrayList<>();

        System.out.print("Enter vehicle type: ");
        String userType = scanner.nextLine();

        String query = "SELECT * FROM Vehicles WHERE type = ? ORDER BY price ASC";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, userType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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

        } catch (Exception e) {
            System.out.println("CHECK getVehiclesByType() METHOD");
        }

        return vehicles;
    }

    public int addVehicle(){

    }


}
