package com.ps;
import com.ps.DataManagers.LeaseContractDAO;
import com.ps.DataManagers.SalesContractDAO;
import com.ps.DataManagers.VehicleDAO;
import com.ps.Models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
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
        System.out.print("Enter command: ");
        int command = Integer.parseInt(scanner.nextLine());

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

        switch (command){
            case 1:
                List<Vehicle> vehicles = vehicleDAO.getVehiclesByPriceRange();
                for(Vehicle v : vehicles){
                    System.out.println(v);
                }
                break;
            case 2:
                List<Vehicle> makeModelVehicles = vehicleDAO.getVehiclesByMakeModel();
                for(Vehicle v : makeModelVehicles){
                    System.out.println(v);
                }
                break;
            case 3:
                List<Vehicle> yearVehicles = vehicleDAO.getVehiclesByYearRange();
                for(Vehicle v : yearVehicles){
                    System.out.println(v);
                }
                break;
            case 4:
                List<Vehicle> colorVehicles = vehicleDAO.getVehiclesByColor();
                for(Vehicle v : colorVehicles){
                    System.out.println(v);
                }
                break;
            case 5:
                List<Vehicle> mileageVehicles = vehicleDAO.getVehiclesByMileageRange();
                for(Vehicle v : mileageVehicles){
                    System.out.println(v);
                }
                break;
            case 6:
                List<Vehicle> typeVehicles = vehicleDAO.getVehiclesByType();
                for(Vehicle v : typeVehicles){
                    System.out.println(v);
                }
                break;
            case 7:
                int rows = vehicleDAO.addVehicle();
                System.out.println(rows+ " rows changed");
                break;
            case 8:
                int removeRows = vehicleDAO.removeVehicle();
                System.out.println(removeRows+ "removed");
                break;
            case 9:
                System.out.print("Enter VIN: ");
                String vinSale = scanner.nextLine();

                System.out.print("Enter customer name: ");
                String nameSale = scanner.nextLine();

                System.out.print("Enter sale price: ");
                double salePrice = Double.parseDouble(scanner.nextLine());

                LocalDate saleDate = LocalDate.now();

                salesDao.saveSalesContract(vinSale, saleDate, salePrice, nameSale);
                break;
            case 10:
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

                leaseDao.saveLeaseContract(vinLease, leaseStart, leaseEnd, monthlyPayment, nameLease);
                break;
            default:
                System.out.println("Enter a valid choice");
        }
    }
}