-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	5.7.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(30) NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Meat an Poultry'),(2,'Vegetable'),(3,'Fruits'),(4,'Fish and Seafood'),(5,'Grains, Beans and Nuts'),(11,'Chicken'),(12,'Fish');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupandcategory`
--

DROP TABLE IF EXISTS `groupandcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupandcategory` (
  `groupAndCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `groupCategoryId` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  PRIMARY KEY (`groupAndCategoryId`),
  UNIQUE KEY `groupAndCategory_unique_key` (`groupCategoryId`,`categoryId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `groupandcategory_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`),
  CONSTRAINT `groupandcategory_ibfk_2` FOREIGN KEY (`groupCategoryId`) REFERENCES `groupcategory` (`groupCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupandcategory`
--

LOCK TABLES `groupandcategory` WRITE;
/*!40000 ALTER TABLE `groupandcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupandcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupcategory`
--

DROP TABLE IF EXISTS `groupcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupcategory` (
  `groupCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `groupCategoryName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`groupCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupcategory`
--

LOCK TABLES `groupcategory` WRITE;
/*!40000 ALTER TABLE `groupcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `itemName` varchar(200) DEFAULT NULL,
  `categoryId` int(11) NOT NULL,
  `frontImg` varchar(45) DEFAULT NULL,
  `backImg` varchar(45) DEFAULT NULL,
  `status` enum('ACTIVE','DELETE') NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`itemId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Chicken',1,NULL,NULL,'ACTIVE'),(2,'Beef',1,'2020_05_27front.jpg',NULL,'ACTIVE'),(3,'Pork',1,NULL,NULL,'ACTIVE'),(4,'Lettuce',2,NULL,NULL,'ACTIVE'),(5,'Spinach',2,NULL,NULL,'ACTIVE'),(6,'Cabbage',2,NULL,NULL,'ACTIVE'),(7,'Cauliflower',2,NULL,NULL,'ACTIVE'),(8,'Brussels Sprout',2,NULL,NULL,'ACTIVE'),(9,'Broccoli',2,NULL,NULL,'ACTIVE'),(10,'Cucumber',2,NULL,NULL,'ACTIVE'),(11,'Patato',2,NULL,NULL,'ACTIVE'),(12,'Apples',3,NULL,NULL,'ACTIVE'),(13,'Pears',3,NULL,NULL,'ACTIVE'),(14,'Oranges',3,NULL,NULL,'ACTIVE'),(15,'Grapefruits',3,NULL,NULL,'ACTIVE'),(16,'Bananas',3,NULL,NULL,'ACTIVE'),(17,'Mangoes',3,NULL,NULL,'ACTIVE'),(18,'Watermelons',3,NULL,NULL,'ACTIVE'),(19,'Tomatoes',3,NULL,NULL,'ACTIVE'),(20,'Rohu',4,NULL,NULL,'ACTIVE'),(21,'Catla',4,NULL,NULL,'ACTIVE'),(22,'Common carp',4,NULL,NULL,'ACTIVE'),(23,'Grass carp',4,NULL,NULL,'ACTIVE'),(24,'Tliapia',4,NULL,NULL,'ACTIVE'),(25,'Glass fish',4,NULL,NULL,'ACTIVE'),(26,'Spiny eel',4,NULL,NULL,'ACTIVE'),(27,'Wheat',4,NULL,NULL,'ACTIVE'),(28,'Oat',4,NULL,NULL,'ACTIVE'),(29,'Rice',4,NULL,NULL,'ACTIVE'),(30,'Maize',4,NULL,NULL,'ACTIVE'),(31,'Barley',4,NULL,NULL,'ACTIVE'),(32,'Sorghum',4,NULL,NULL,'ACTIVE'),(36,'Free-Range Chicken Curry Cut With Skin & Bone 800g',11,'2020_05_29_05_58_000front.png',NULL,'ACTIVE'),(37,'Chicken Liver 300g',11,'2020_05_29_05_58_008front.png',NULL,'ACTIVE'),(38,'Chicken Gizzard 300g',11,'2020_05_29_06_00_031front.png',NULL,'ACTIVE'),(39,'Chicken Heart 300g',11,'2020_05_29_06_01_006front.png',NULL,'ACTIVE'),(40,'Whole Chicken Leg With Skin 2pcs',11,'2020_05_29_10_17_054front.png',NULL,'ACTIVE'),(41,'Whole Chicken Bone 2pcs',11,'2020_05_29_06_05_036front.png',NULL,'ACTIVE'),(42,'Whole Chicken Wing With Skin 700g',11,'2020_05_29_06_06_027front.png',NULL,'ACTIVE'),(43,'Striped Snake Head Skinless & Boneless Fillet',12,'2020_05_29_09_48_034front.png',NULL,'ACTIVE');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitem` (
  `orderItemId` int(11) NOT NULL AUTO_INCREMENT,
  `userAccountId` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `deliveryFee` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(45) DEFAULT NULL,
  `preferedTime` varchar(70) DEFAULT NULL,
  `townshipId` int(11) DEFAULT NULL,
  `status` enum('ORDER','ACCEPT','REJECT','COMPLETE') NOT NULL DEFAULT 'ORDER',
  `acceptDate` datetime DEFAULT NULL,
  `rejectDate` datetime DEFAULT NULL,
  `completeDate` datetime DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `orderitem_ibfk_2_idx` (`townshipId`),
  KEY `orderitem_ibfk_1` (`userAccountId`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`userAccountId`) REFERENCES `useraccount` (`userAccountId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`townshipId`) REFERENCES `township` (`townshipId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (11,1,200,1000,0,1200,'2020-03-16','Mdy','09999','06:AM : 07:AM',1,'ORDER',NULL,NULL,NULL,'2020-03-16 00:00:00'),(12,NULL,200,1000,0,1200,'2020-03-16','Mdy','09999','04:PM : 05:PM',1,'ACCEPT',NULL,NULL,NULL,'2020-03-16 00:00:00'),(13,2,160,1000,0,1160,'2020-03-16','Mandalay','099','06:AM : 07:AM',1,'REJECT',NULL,NULL,NULL,'2020-03-16 00:00:00'),(14,2,280,1000,0,1280,'2020-03-17','Mdy','0999','06:AM : 07:AM',1,'COMPLETE',NULL,NULL,NULL,'2020-03-16 00:00:00'),(15,2,240,1000,0,1240,'2020-03-23','Yangon','09999999','06:AM : 07:AM',1,'ORDER',NULL,NULL,NULL,'2020-03-16 00:00:00'),(16,2,80,1000,0,1080,'2020-05-28','mdy','09999999','06:AM : 07:AM',1,'ORDER',NULL,NULL,NULL,'2020-03-16 00:00:00');
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitemlist`
--

DROP TABLE IF EXISTS `orderitemlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitemlist` (
  `orderItemListId` int(11) NOT NULL AUTO_INCREMENT,
  `orderItemId` int(11) NOT NULL,
  `priceId` int(11) NOT NULL,
  `itemName` varchar(200) DEFAULT NULL,
  `unitPrice` int(11) DEFAULT NULL,
  `qty` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`orderItemListId`),
  KEY `orderItemId` (`orderItemId`),
  KEY `priceId` (`priceId`),
  CONSTRAINT `orderitemlist_ibfk_1` FOREIGN KEY (`orderItemId`) REFERENCES `orderitem` (`orderItemId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orderitemlist_ibfk_2` FOREIGN KEY (`priceId`) REFERENCES `sellingprice` (`priceId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitemlist`
--

LOCK TABLES `orderitemlist` WRITE;
/*!40000 ALTER TABLE `orderitemlist` DISABLE KEYS */;
INSERT INTO `orderitemlist` VALUES (2,11,6,'Lettuce',40,3,120),(3,11,4,'Pork',40,1,40),(4,11,5,'Beef',40,1,40),(5,12,6,'Lettuce',40,3,120),(6,12,4,'Pork',40,1,40),(7,12,5,'Beef',40,1,40),(8,13,6,'Lettuce',40,1,40),(9,13,4,'Pork',40,3,120),(10,14,6,'Lettuce',40,6,240),(11,14,3,'Chicken',40,1,40),(12,15,6,'Lettuce',40,4,160),(13,15,3,'Chicken',40,1,40),(14,15,4,'Pork',40,1,40),(15,16,4,'Beef',30,1,30),(16,16,6,'Beef',50,1,50);
/*!40000 ALTER TABLE `orderitemlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseitem`
--

DROP TABLE IF EXISTS `purchaseitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchaseitem` (
  `purchaseItemId` int(11) NOT NULL AUTO_INCREMENT,
  `itemId` int(11) NOT NULL,
  `unitId` int(11) NOT NULL,
  `unitPrice` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  PRIMARY KEY (`purchaseItemId`),
  KEY `itemId` (`itemId`),
  KEY `unitId` (`unitId`),
  CONSTRAINT `purchaseitem_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purchaseitem_ibfk_2` FOREIGN KEY (`unitId`) REFERENCES `unit` (`unitId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseitem`
--

LOCK TABLES `purchaseitem` WRITE;
/*!40000 ALTER TABLE `purchaseitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellingprice`
--

DROP TABLE IF EXISTS `sellingprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sellingprice` (
  `priceId` int(11) NOT NULL AUTO_INCREMENT,
  `itemId` int(11) NOT NULL,
  `unitId` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `unitPrice` int(11) NOT NULL,
  PRIMARY KEY (`priceId`),
  KEY `itemId` (`itemId`),
  KEY `unitId` (`unitId`),
  CONSTRAINT `sellingprice_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sellingprice_ibfk_2` FOREIGN KEY (`unitId`) REFERENCES `unit` (`unitId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellingprice`
--

LOCK TABLES `sellingprice` WRITE;
/*!40000 ALTER TABLE `sellingprice` DISABLE KEYS */;
INSERT INTO `sellingprice` VALUES (1,2,1,'2019-10-21 15:29:43',20),(3,2,2,'2019-10-22 15:29:43',10),(4,2,2,'2019-10-23 15:29:43',30),(5,4,1,'2019-10-24 15:29:43',45),(6,2,1,'2020-05-25 15:29:43',50),(7,41,1,'2020-05-29 18:16:57',1000),(8,40,1,'2020-05-29 18:17:23',1100),(9,41,4,'2020-05-29 20:24:11',1200),(10,43,5,'2020-05-29 21:50:44',5000),(11,43,5,'2020-05-29 21:51:49',4000);
/*!40000 ALTER TABLE `sellingprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `township`
--

DROP TABLE IF EXISTS `township`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `township` (
  `townshipId` int(11) NOT NULL AUTO_INCREMENT,
  `townshipName` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`townshipId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `township`
--

LOCK TABLES `township` WRITE;
/*!40000 ALTER TABLE `township` DISABLE KEYS */;
INSERT INTO `township` VALUES (1,'Hlaing');
/*!40000 ALTER TABLE `township` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `unitId` int(11) NOT NULL AUTO_INCREMENT,
  `unitName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`unitId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'Gram'),(2,'Pound'),(4,'2pcs'),(5,'500g');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useraccount` (
  `userAccountId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `userName` varchar(150) DEFAULT NULL,
  `password` text,
  `phoneNo` varchar(30) DEFAULT NULL,
  `userRole` enum('ADMIN','STAFF','USER') NOT NULL,
  `status` enum('ACTIVE','INACTIVE','WAIT') NOT NULL,
  `facebookId` varchar(70) DEFAULT NULL,
  `profileName` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`userAccountId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (1,'paingpyaesone.dev@gmail.com','admin','$2a$10$Tyepame6YxeIRWPO0Qq9PeRS0m4UAl7pLY.Ml0RwOiE4FBSsRV.KK','09970074560','ADMIN','ACTIVE',NULL,NULL),(2,'paingpyaesone.programgroup@gmail.com','Paing Pyae Sone',NULL,NULL,'USER','ACTIVE','1737153749767378','Paing Pyae Sone');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'shop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-03 22:39:46
