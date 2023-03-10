SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS PurchaseItem;
DROP TABLE IF EXISTS OrderItemList;
DROP TABLE IF EXISTS SellingPrice;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS City;
DROP TABLE IF EXISTS OrderItem;
DROP TABLE IF EXISTS Township;
DROP TABLE IF EXISTS Unit;
DROP TABLE IF EXISTS UserAccount;




/* Create Tables */

CREATE TABLE Category
(
	categoryId int NOT NULL AUTO_INCREMENT,
	categoryName varchar(30) NOT NULL,
	PRIMARY KEY (categoryId)
);


CREATE TABLE City
(
	cityId int NOT NULL AUTO_INCREMENT,
	cityName varchar(80),
	PRIMARY KEY (cityId)
);


CREATE TABLE Item
(
	itemId int NOT NULL AUTO_INCREMENT,
	itemName varchar(200),
	categoryId int NOT NULL,
	frontImg varchar(20),
	backImg varchar(20),
	PRIMARY KEY (itemId)
);


CREATE TABLE OrderItem
(
	orderItemId int NOT NULL AUTO_INCREMENT,
	userAccountId int NOT NULL,
	amount int,
	deliveryFee int NOT NULL,
	discount int NOT NULL,
	total int NOT NULL,
	orderDate date NOT NULL,
	address varchar(255),
	townshipId int NOT NULL,
	phoneNo varchar(40),
	preferedTime varchar(70),
	date date,
	status enum('ORDER','ACCEPT','REJECT','COMPLETE') DEFAULT 'ORDER' NOT NULL,
	acceptDate datetime,
	rejectDate datetime,
	completeDate datetime,
	PRIMARY KEY (orderItemId)
);


CREATE TABLE OrderItemList
(
	orderItemListId int NOT NULL AUTO_INCREMENT,
	orderItemId int NOT NULL,
	priceId int NOT NULL,
	itemName varchar(50),
	unitPrice int,
	qty int NOT NULL,
	amount int NOT NULL,
	PRIMARY KEY (orderItemListId)
);


CREATE TABLE PurchaseItem
(
	purchaseItemId int NOT NULL AUTO_INCREMENT,
	itemId int NOT NULL,
	unitId int NOT NULL,
	unitPrice int NOT NULL,
	price int NOT NULL,
	qty int NOT NULL,
	PRIMARY KEY (purchaseItemId)
);


CREATE TABLE SellingPrice
(
	priceId int NOT NULL AUTO_INCREMENT,
	itemId int NOT NULL,
	unitId int NOT NULL,
	date datetime NOT NULL,
	unitPrice int NOT NULL,
	PRIMARY KEY (priceId),
	CONSTRAINT uniquekey_item_unit UNIQUE (itemId, unitId)
);


CREATE TABLE Township
(
	townshipId int NOT NULL AUTO_INCREMENT,
	townshipName varchar(60),
	PRIMARY KEY (townshipId)
);


CREATE TABLE Unit
(
	unitId int NOT NULL AUTO_INCREMENT,
	unitName varchar(30),
	PRIMARY KEY (unitId)
);


CREATE TABLE UserAccount
(
	userAccountId int NOT NULL AUTO_INCREMENT,
	facebookId varchar(100),
	email varchar(50) NOT NULL,
	profileName varchar(50),
	userName varchar(50),
	password text,
	phoneNo varchar(30),
	userRole enum('ADMIN','STAFF','USER') NOT NULL,
	status enum('ACTIVE','INACTIVE','WAIT') NOT NULL,
	PRIMARY KEY (userAccountId),
	UNIQUE (userName)
);



/* Create Foreign Keys */

ALTER TABLE Item
	ADD FOREIGN KEY (categoryId)
	REFERENCES Category (categoryId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PurchaseItem
	ADD FOREIGN KEY (itemId)
	REFERENCES Item (itemId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SellingPrice
	ADD FOREIGN KEY (itemId)
	REFERENCES Item (itemId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE OrderItemList
	ADD FOREIGN KEY (orderItemId)
	REFERENCES OrderItem (orderItemId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE OrderItemList
	ADD FOREIGN KEY (priceId)
	REFERENCES SellingPrice (priceId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE OrderItem
	ADD FOREIGN KEY (townshipId)
	REFERENCES Township (townshipId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PurchaseItem
	ADD FOREIGN KEY (unitId)
	REFERENCES Unit (unitId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SellingPrice
	ADD FOREIGN KEY (unitId)
	REFERENCES Unit (unitId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE OrderItem
	ADD FOREIGN KEY (userAccountId)
	REFERENCES UserAccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



