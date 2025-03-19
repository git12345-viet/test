--Create Database coffeeshop
--use coffeeshop

CREATE TABLE role (
    role_id int PRIMARY KEY IDENTITY(1,1),
    role_name VARCHAR(100) NOT NULL
)
INSERT INTO role (role_name) VALUES
( 'Admin'),
( 'Customer');


-- Users table
CREATE TABLE Users (
    ID INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(255),
    Password NVARCHAR(255),
    role_id INT,
	active bit,
	FOREIGN KEY (role_id) REFERENCES role(role_Id)
);

-- Insert sample data into Users table
INSERT INTO Users ( username, Password, role_id, active)
VALUES 
    ('admin', '12345', 1,1),
    ('customer1', '12345', 2,0),
	('customer2', '12345', 2,1);


-- Categories table
CREATE TABLE Categories (
    ID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(255)
);

-- Insert sample data into Categories table
INSERT INTO Categories (Name)
VALUES 
    (N'Cà phê Espresso'),
    (N'Cà phê Pha Sữa'),
    (N'Cà phê Đen'),
    (N'Thức uống Đặc Biệt');

-- Products table
CREATE TABLE Products (
    ID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(255),
    Description NVARCHAR(MAX),
    Price INT,
    Image NVARCHAR(MAX),
    CategoryID INT,
    FOREIGN KEY (CategoryID) REFERENCES Categories(ID),
);

-- Insert sample data into Products table
INSERT INTO Products (Name, Description, Price, Image, CategoryID)
VALUES 

    ('Espresso', 'Description of Espresso', 30000, 'img/product/1.jpg', 1),
	('Ristretto Bianco', 'Description of Ristretto Bianco', 30000, 'img/product/2.jpg', 1),
    ('Espresso Con Panna', 'Description of Espresso Con Panna', 55000, 'img/product/3.jpg', 1),
    ('Espresso Macchiato', 'Description of Espresso Macchiato', 40000, 'img/product/4.jpg',  1),
	('Caffe Latte', 'Description of Caffe Latte', 50000, 'img/product/5.jpg', 2),
	('Cappuccino', 'Description of Cappuccino', 45000, 'img/product/6.jpg', 2),
	('Flavored Latte', 'Description of Flavored Latte', 50000, 'img/product/7.jpg', 2),
	('Caramel Macchiato', 'Description of Caramel Macchiato', 35000, 'img/product/8.jpg', 2),
    ('Asian Dolce Latte', 'Description of Asian Dolce Latte', 45000, 'img/product/9.jpg', 2),
	('Skinny Latte', 'Description of Skinny Latte', 25000, 'img/product/10.jpg', 2),
    ('Hazelnut Macchiato', 'Description of Hazelnut Macchiato', 35000, 'img/product/11.jpg', 2),
	('Americano', 'Description of Americano', 45000, 'img/product/12.jpg', 3),
    ('Freshly Brewed Coffee', 'Description of Freshly Brewed Coffee', 40000, 'img/product/13.jpg', 3),
    ('Vietnamese Coffee ', 'Description of Vietnamese Coffee', 47000, 'img/product/14.jpg', 3),
	('Affogato', 'Description of Affogato', 55000, 'img/product/15.jpg',  4),
	('Caffe Mocha', 'Description of Caffe Mocha', 25000, 'img/product/16.jpg',  4),
    ('Irish Coffee', 'Description of Irish Coffee', 40000, 'img/product/17.jpg',  4),
    ('Flat White', 'Description of Flat White', 50000, 'img/product/19.jpg',  4);
	

Create table OrderStatus(
	orderstatus_id int PRIMARY KEY IDENTITY(1,1),
	status_name nvarchar(20)
);

insert into OrderStatus (status_name)
VALUES
	('wait'),
	('process'),
	('done');

-- Orders table
CREATE TABLE Orders (
    ID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT,
	Name NVARCHAR(255) ,
	phonenumber nvarchar(20),
    Address NVARCHAR(255),
    OrderDate DATE,
    TotalAmount INT DEFAULT 0,
	StatusID int
    FOREIGN KEY (UserID) REFERENCES Users(ID),
	FOREIGN KEY (StatusID) REFERENCES OrderStatus(orderstatus_id)
);


-- OrderItems table
CREATE TABLE OrderItems (
    ID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(ID),
    FOREIGN KEY (ProductID) REFERENCES Products(ID)
);

