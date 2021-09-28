DROP DATABASE IF EXISTS CarDealershipDBTest;
CREATE DATABASE CarDealershipDBTest;

USE CarDealershipDBTest;

CREATE TABLE Contacts (
	ContactID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(40),
    LastName VARCHAR(40),
    Phone VARCHAR(15),
    Email VARCHAR(40),
    MessageBox TEXT
);

CREATE TABLE `User` (
	Email VARCHAR(40) PRIMARY KEY,
    FirstName VARCHAR(40) NOT NULL,
    LastName VARCHAR(40) NOT NULL,
    PasswordHash CHAR(64) NOT NULL,
    `Role` ENUM('Sales', 'Admin')
);

CREATE TABLE Make (
	MakeID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(20) NOT NULL,
    DateAdded DATE NOT NULL,
    UserEmail VARCHAR(40) NOT NULL,
    CONSTRAINT FK_UserEmail_Make FOREIGN KEY (UserEmail) REFERENCES `User`(Email)
);

CREATE TABLE ModelYear (
    `Year` YEAR PRIMARY KEY
);

CREATE TABLE Model (
	ModelID INT PRIMARY KEY AUTO_INCREMENT,
	`Name` VARCHAR(20) NOT NULL,
    `Year` YEAR NOT NULL,
    DateAdded DATE NOT NULL,
    UserEmail VARCHAR(40) NOT NULL,
    MakeID INT NOT NULL,
    CONSTRAINT FK_UserEmail_Model FOREIGN KEY (UserEmail) REFERENCES `User`(Email),
    CONSTRAINT FK_MakeID_Model FOREIGN KEY (MakeID) REFERENCES Make(MakeID),
    CONSTRAINT FK_Year_Model FOREIGN KEY (`Year`) REFERENCES ModelYear(`Year`)
);

CREATE TABLE VehicleCondition  (
	VehicleConditionID INT PRIMARY KEY AUTO_INCREMENT,
    Mileage INT,
    MileageUnit ENUM('Kilometers', 'Miles'),
    `Type` ENUM('New', 'Used')
);

CREATE TABLE Color (
	ColorID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(23) NOT NULL
);

CREATE TABLE `Trim` (
	TrimID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(20) NOT NULL,
    InteriorColorID INT NOT NULL,
    ExteriorColorID INT NOT NULL,
    Transmission ENUM('Automatic', 'Manual'),
    CONSTRAINT FK_InteriorColorID FOREIGN KEY (InteriorColorID) REFERENCES Color(ColorID),
	CONSTRAINT FK_ExteriorColorID FOREIGN KEY (ExteriorColorID) REFERENCES Color(ColorID)
);

CREATE TABLE Specials (
	SpecialsID INT PRIMARY KEY auto_increment,
    Title VARCHAR(40) NOT NULL,
    `Description` TEXT NOT NULL
);

CREATE TABLE Vehicle (
	VIN CHAR(17) PRIMARY KEY,
    ModelID INT NOT NULL,
    VehicleConditionID INT NOT NULL,
    BodyStyle VARCHAR(32),
    Picture BLOB,
    `Description` TEXT,
    TrimID INT NOT NULL,
    SalesPrice DECIMAL(10, 2),
    MSRP DECIMAL(10, 2) NOT NULL,
    Featured Boolean NOT NULL,
    CONSTRAINT FK_ModelID FOREIGN KEY (ModelID) REFERENCES Model(ModelID),
    CONSTRAINT FK_VehicleConditionID FOREIGN KEY (VehicleConditionID) REFERENCES VehicleCondition(VehicleConditionID),
    CONSTRAINT FK_TrimID FOREIGN KEY (TrimID) REFERENCES `Trim`(TrimID)
);

CREATE TABLE SALES (
    UserEmail VARCHAR(40) NOT NULL,
    VIN CHAR(17) NOT NULL,
	PRIMARY KEY(UserEmail, VIN),
    CONSTRAINT FK_UserEmail FOREIGN KEY (UserEmail) REFERENCES `User`(Email),
    CONSTRAINT FK_VIN FOREIGN KEY (VIN) REFERENCES Vehicle(VIN)
);