DROP DATABASE IF EXISTS CarDealershipDB;
CREATE DATABASE CarDealershipDB;

USE CarDealershipDB;

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
    `Role` ENUM('Sales', 'Admin', 'Disabled')
);

CREATE TABLE Make (
	MakeID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(20) NOT NULL,
    DateAdded DATE NOT NULL,
    UserEmail VARCHAR(40) NOT NULL,
    CONSTRAINT FK_UserEmail_Make FOREIGN KEY (UserEmail) REFERENCES `User`(Email)
);

CREATE TABLE Model (
	ModelID INT PRIMARY KEY AUTO_INCREMENT,
	`Name` VARCHAR(20) NOT NULL,
    `Year` YEAR NOT NULL,
    DateAdded DATE NOT NULL,
    UserEmail VARCHAR(40) NOT NULL,
    MakeID INT NOT NULL,
    CONSTRAINT FK_UserEmail_Model FOREIGN KEY (UserEmail) REFERENCES `User`(Email),
    CONSTRAINT FK_MakeID_Model FOREIGN KEY (MakeID) REFERENCES Make(MakeID)
);

CREATE TABLE VehicleCondition  (
	VehicleConditionID INT PRIMARY KEY AUTO_INCREMENT,
    Mileage INT,
    MileageUnit ENUM('Kilometers', 'Miles'),
    `Type` ENUM('New', 'Used')
);

CREATE TABLE `Trim` (
	TrimID INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(20) NOT NULL,
    InteriorColor VARCHAR(23) NOT NULL,
    ExteriorColor VARCHAR(23) NOT NULL,
    Transmission ENUM('Automatic', 'Manual')
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

INSERT INTO `User` VALUES("JG@gmail.com", "Jack", "Gram", "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd", "Admin");
INSERT INTO `User` VALUES("emilywiill@hotmail.com", "Emily", "Willes", "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd", "Sales");

INSERT INTO `Trim`(`Name`, InteriorColor, ExteriorColor, Transmission) VALUES ("Trim 1", "light golden rod yellow", "red", "Automatic");
INSERT INTO `Trim`(`Name`, InteriorColor, ExteriorColor, Transmission) VALUES ("Trim 2", "blue", "light golden rod yellow", "Manual");

INSERT INTO Make(`Name`, DateAdded, UserEmail) VALUES("Toyota", '2021-09-27', "JG@gmail.com");
INSERT INTO Make(`Name`, DateAdded, UserEmail) VALUES("Hyundai", '2020-05-18', "JG@gmail.com");

INSERT INTO Model(`Name`, `Year`, DateAdded, UserEmail, MakeID) VALUES("Camry", 2008, '2021-09-27', "JG@gmail.com", 1);
INSERT INTO Model(`Name`, `Year`, DateAdded, UserEmail, MakeID) VALUES("Accent", 2015, '2020-05-18', "JG@gmail.com", 2);

INSERT INTO VehicleCondition(Mileage, MileageUnit, `Type`) VALUES (50000, "Miles", "Used");
INSERT INTO VehicleCondition(Mileage, MileageUnit, `Type`) VALUES (1000, "Kilometers", "New");

INSERT INTO Vehicle VALUES ("123456789012AS567", 1, 1, "Sedan", null, null, 1, 24000.00, 36000.46, false);
INSERT INTO Vehicle VALUES ("123fw6789012AS567", 2, 2, "Subcompact", null, "Clean Reliable Fuel not efficient", 1, 24500.00, 27000.46, true);

INSERT INTO Contacts(FirstName, LastName, Phone, Email, MessageBox) VALUES ("Robert", "Jones", "1234567890", "rjoness@gmail.com", "Random message");
INSERT INTO Contacts(FirstName, LastName, Phone, Email, MessageBox) VALUES ("Eric", "Omar", "8756753780", "erioma@gmail.com", "Need car");
INSERT INTO Contacts(FirstName, LastName, Phone, Email, MessageBox) VALUES ("Quinn", "East", "4560753780", "qe@gmail.com", "Sell car");

INSERT INTO Specials(Title, `Description`) VALUES ("Monday Specials", "Nothing");
INSERT INTO Specials(Title, `Description`) VALUES ("Saturday Specials", "By 1 get 1 for a price of 2 cars.");

INSERT INTO Sales(UserEmail, VIN) VALUES ("emilywiill@hotmail.com", "123456789012AS567");
INSERT INTO Sales(UserEmail, VIN) VALUES ("emilywiill@hotmail.com", "123fw6789012AS567");