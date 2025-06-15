DROP DATABASE IF EXISTS CarDealershipDB;
CREATE DATABASE CarDealershipDB;
USE CarDealershipDB;


-- 1. Vehicles table
CREATE TABLE Vehicles (
    VIN VARCHAR(17) NOT NULL,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    color VARCHAR(20),
    price DECIMAL(10,2),
    type VARCHAR(50),
    mileage INT,
    sold BIT NOT NULL,
    PRIMARY KEY(`VIN`)
);


-- 2. Dealership table
CREATE TABLE Dealerships (
    dealership_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    phone VARCHAR(12),
    PRIMARY KEY(`dealership_id`)
);

-- 3. Inventory table
CREATE TABLE Inventory (
    dealership_id INT NOT NULL,
    VIN VARCHAR(17) NOT NULL,
    PRIMARY KEY (`dealership_id`, `VIN`)
);

-- 4. SalesContracts table
CREATE TABLE Sales_contracts (
    sales_id INT NOT NULL AUTO_INCREMENT,
    VIN VARCHAR(17) NOT NULL,
    sale_date DATE NOT NULL,
    sale_price DECIMAL(10,2) NOT NULL,
    customer_name VARCHAR(100),
    PRIMARY KEY (sales_id),
    FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

-- 5. LeaseContracts table
CREATE TABLE Lease_contracts (
    lease_id INT NOT NULL AUTO_INCREMENT,
    VIN VARCHAR(17) NOT NULL,
    lease_start_date DATE NOT NULL,
    lease_end_date DATE NOT NULL,
    monthly_payment DECIMAL(10,2) NOT NULL,
    customer_name VARCHAR(100),
    PRIMARY KEY (lease_id),
    FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);



-- Vehicle inserts
INSERT INTO Vehicles (VIN, make, model, year, color, price, type, mileage, sold) VALUES
('1HGCM82633A004352', 'Honda', 'Accord', 2020, 'Red', 22000.00, 'Sedan' ,15000, 0),
('1FAFP404X1F192837', 'Ford', 'Mustang', 2019, 'Blue', 27000.00, 'Truck',12000, 1),
('2T1BURHE6FC123456', 'Toyota', 'Corolla', 2021, 'White', 18000.00, 'Sedan' ,8000, 0);

-- Dealership inserts
INSERT INTO Dealerships (name, address, phone) VALUES
('City Motors', '123 Main St', '555-123-4567'),
('AutoHub', '456 Elm St', '555-987-6543'),
('DriveTime', '789 Oak St', '555-555-5555');

-- Inventory inserts
INSERT INTO Inventory (dealership_id, VIN) VALUES
(1, '1HGCM82633A004352'),
(2, '1FAFP404X1F192837'),
(3, '2T1BURHE6FC123456');

-- Sales Contracts inserts
INSERT INTO Sales_contracts (VIN, sale_date, sale_price, customer_name) VALUES
('1FAFP404X1F192837', '2024-05-01', 25000.00, 'John Doe');

-- Lease Contracts inserts
INSERT INTO Lease_contracts (VIN, lease_start_date, lease_end_date, monthly_payment, customer_name) VALUES
('1HGCM82633A004352', '2024-01-01', '2025-01-01', 300.00, 'Jane Smith');



