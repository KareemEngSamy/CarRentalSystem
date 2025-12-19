-- Schema bootstrap for Car Rental System

CREATE DATABASE IF NOT EXISTS carrentalsystem;
USE carrentalsystem;

-- Users
CREATE TABLE IF NOT EXISTS users (
  ID INT PRIMARY KEY,
  FirstName VARCHAR(50) NOT NULL,
  LastName  VARCHAR(50) NOT NULL,
  Email     VARCHAR(100) NOT NULL UNIQUE,
  PhoneNumber VARCHAR(30) NOT NULL,
  Password  VARCHAR(255) NOT NULL,
  Type      INT NOT NULL -- 0: Client, 1: Admin, 2/3: Deleted
);

-- Cars
CREATE TABLE IF NOT EXISTS cars (
  ID INT PRIMARY KEY,
  Brand  VARCHAR(50) NOT NULL,
  Model  VARCHAR(50) NOT NULL,
  Color  VARCHAR(30) NOT NULL,
  Year   INT NOT NULL,
  Price  DOUBLE NOT NULL,
  Available INT NOT NULL -- 0: available, 1: rented, 2: deleted
);

-- Rents
CREATE TABLE IF NOT EXISTS rents (
  ID INT PRIMARY KEY,
  `User` INT NOT NULL,
  `Car`  INT NOT NULL,
  DateTime VARCHAR(20) NOT NULL, -- example: dd-MM-yyyy  HH:mm
  Hours INT NOT NULL,
  Total DOUBLE NOT NULL,
  Status INT NOT NULL, -- 0: running, 1: returned
  CONSTRAINT fk_rents_user FOREIGN KEY (`User`) REFERENCES users(ID),
  CONSTRAINT fk_rents_car  FOREIGN KEY (`Car`) REFERENCES cars(ID)
);
