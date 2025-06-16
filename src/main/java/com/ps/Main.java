package com.ps;
import com.ps.DataManagers.LeaseContractDAO;
import com.ps.DataManagers.SalesContractDAO;
import com.ps.DataManagers.VehicleDAO;
import com.ps.Models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the dealership\n");

        if(args.length < 2){
            System.out.println("Give me args!!!");
            System.exit(1);
        }

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/CarDealerShipDB");
        basicDataSource.setUsername(args[0]);
        basicDataSource.setPassword(args[1]);
        VehicleDAO vehicleDAO = new VehicleDAO(basicDataSource);
        SalesContractDAO salesDao = new SalesContractDAO(basicDataSource);
        LeaseContractDAO leaseDao = new LeaseContractDAO(basicDataSource);

        int command;
        do{
            displayHomeMenu();
            command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                    List<Vehicle> vehicles = vehicleDAO.getVehiclesByPriceRange();
                    for (Vehicle v : vehicles) {
                        System.out.println(v);
                    }
                    break;
                case 2:
                    List<Vehicle> makeModelVehicles = vehicleDAO.getVehiclesByMakeModel();
                    for (Vehicle v : makeModelVehicles) {
                        System.out.println(v);
                    }
                    break;
                case 3:
                    List<Vehicle> yearVehicles = vehicleDAO.getVehiclesByYearRange();
                    for (Vehicle v : yearVehicles) {
                        System.out.println(v);
                    }
                    break;
                case 4:
                    List<Vehicle> colorVehicles = vehicleDAO.getVehiclesByColor();
                    for (Vehicle v : colorVehicles) {
                        System.out.println(v);
                    }
                    break;
                case 5:
                    List<Vehicle> mileageVehicles = vehicleDAO.getVehiclesByMileageRange();
                    for (Vehicle v : mileageVehicles) {
                        System.out.println(v);
                    }
                    break;
                case 6:
                    List<Vehicle> typeVehicles = vehicleDAO.getVehiclesByType();
                    for (Vehicle v : typeVehicles) {
                        System.out.println(v);
                    }
                    break;
                case 7:
                    int rows = vehicleDAO.addVehicle();
                    System.out.println(rows + " rows changed");
                    break;
                case 8:
                    int removeRows = vehicleDAO.removeVehicle();
                    System.out.println(removeRows + "removed");
                    break;
                case 9:
                    salesDao.saveSalesContract();
                    break;
                case 10:
                    leaseDao.saveLeaseContract();
                    break;
                case 11:
                    List<Vehicle> allVehicles = vehicleDAO.getAllVehicles();
                    for(Vehicle v : allVehicles){
                        System.out.println(v);
                    }
                    break;
                case 0:
                    System.out.println("\nExiting ....\n");
                    break;
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        }while(command != 0);
    }

    public static void displayHomeMenu(){
        System.out.println("1) By price range");
        System.out.println("2) By make/model");
        System.out.println("3) By year range");
        System.out.println("4) By color");
        System.out.println("5) By mileage range");
        System.out.println("6) By type");
        System.out.println("7) Add vehicle");
        System.out.println("8) Remove Vehicle");
        System.out.println("9) Add Sales Contract");
        System.out.println("10) Add Lease Contract");
        System.out.println("11) See all vehicles");
        System.out.println("0) Exit");
        System.out.print("Enter command: ");
    }
}