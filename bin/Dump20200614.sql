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
  `categoryName` varchar(200) NOT NULL,
  `categoryNameMm` varchar(200) NOT NULL,
  `specialId` int(11) DEFAULT '0',
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Beans, Corn & More','ပဲ၊ ပြောင်းဖူး နှင့် ပို၍',2),(2,'Broccoli, Cauliflower & Cabbage','ပန်းမုန်လာ၊ ပန်းဂေါ်ဖီ၊ ဂေါ်ဖီထုပ်',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `cityId` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`cityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
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
  CONSTRAINT `groupandcategory_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `groupandcategory_ibfk_2` FOREIGN KEY (`groupCategoryId`) REFERENCES `groupcategory` (`groupCategoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `groupCategoryNameMm` varchar(200) DEFAULT NULL,
  `frontImg` varchar(40) DEFAULT NULL,
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
  `itemNameMm` varchar(200) DEFAULT NULL,
  `categoryId` int(11) NOT NULL,
  `frontImg` varchar(40) DEFAULT NULL,
  `backImg` varchar(40) DEFAULT NULL,
  `status` enum('ACTIVE','DELETE') NOT NULL DEFAULT 'ACTIVE',
  `discountPrice` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Shwe Bean','ရွှေပဲ',1,'2020_06_09_04_36_022front.png',NULL,'ACTIVE',NULL),(2,'Sweet Corn','ပြောင်းဖူးအချို',1,'2020_06_09_04_40_044front.png',NULL,'ACTIVE',NULL),(3,'Baby Corn','ပြောင်းဖူးအသေး',1,'2020_06_09_04_44_029front.png',NULL,'ACTIVE',NULL),(4,'String Beans','ပဲတောင့်ရှည်',1,'2020_06_09_04_47_028front.png',NULL,'ACTIVE',NULL),(5,'Broccoli','ပန်းမုန်လာ',2,'2020_06_09_04_49_059front.png',NULL,'ACTIVE',NULL),(6,'Chinese Cabbage','တရုတ်ဂေါ်ဖီထုပ်',2,'2020_06_09_04_51_036front.png',NULL,'ACTIVE',NULL),(7,'Cauliflower','ပန်းဂေါ်ဖီ',1,'2020_06_09_04_52_047front.png',NULL,'ACTIVE',NULL),(8,'Cabbage','ဂေါ်ဖီထုပ်',2,'2020_06_09_04_54_009front.png',NULL,'ACTIVE',NULL),(9,'Cabbage','ဂေါ်ဖီထုပ်',2,'2020_06_09_04_55_051front.png',NULL,'ACTIVE',NULL);
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
  `userAccountId` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `deliveryFee` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `townshipId` int(11) NOT NULL,
  `phoneNo` varchar(40) DEFAULT NULL,
  `preferedTime` varchar(70) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` enum('ORDER','ACCEPT','REJECT','COMPLETE') NOT NULL DEFAULT 'ORDER',
  `acceptDate` datetime DEFAULT NULL,
  `rejectDate` datetime DEFAULT NULL,
  `completeDate` datetime DEFAULT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `townshipId` (`townshipId`),
  KEY `userAccountId` (`userAccountId`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`townshipId`) REFERENCES `township` (`townshipId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`userAccountId`) REFERENCES `useraccount` (`userAccountId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (8,4,800,1000,0,1800,'2020-06-14','test',1,'09999999','06:AM : 07:AM','2020-06-13','ORDER',NULL,NULL,NULL),(9,4,600,1000,0,1600,'2020-06-14','ထထ',1,'09999999','06:AM : 07:AM','2020-06-13','ORDER',NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitemlist`
--

LOCK TABLES `orderitemlist` WRITE;
/*!40000 ALTER TABLE `orderitemlist` DISABLE KEYS */;
INSERT INTO `orderitemlist` VALUES (1,8,4,'Chinese Cabbage',800,1,800),(2,9,1,'Cabbage',600,1,600);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  KEY `unitId` (`unitId`),
  KEY `itemId` (`itemId`),
  CONSTRAINT `sellingprice_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sellingprice_ibfk_2` FOREIGN KEY (`unitId`) REFERENCES `unit` (`unitId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellingprice`
--

LOCK TABLES `sellingprice` WRITE;
/*!40000 ALTER TABLE `sellingprice` DISABLE KEYS */;
INSERT INTO `sellingprice` VALUES (1,8,7,'2020-06-09 17:02:24',600),(2,9,8,'2020-06-09 17:02:37',300),(3,4,4,'2020-06-12 00:52:43',800),(4,6,7,'2020-06-12 14:49:57',800),(5,5,7,'2020-06-12 14:51:20',900),(7,8,7,'2020-06-13 14:18:06',270);
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
  `townshipNameMm` varchar(60) DEFAULT NULL,
  `deliveryCharge` int(11) DEFAULT '0',
  PRIMARY KEY (`townshipId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `township`
--

LOCK TABLES `township` WRITE;
/*!40000 ALTER TABLE `township` DISABLE KEYS */;
INSERT INTO `township` VALUES (1,'Hlaing','လှိုင်',0);
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
  `unitName` varchar(150) NOT NULL,
  `unitNameMm` varchar(150) NOT NULL,
  PRIMARY KEY (`unitId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'2Pcs','၂ခု'),(2,'160g','၁၆၀ဂရမ်'),(3,'1Pack','၁ထုပ်'),(4,'200g','၂၀၀ဂရမ်'),(5,'400g','၄၀၀ဂရမ်'),(6,'250g','၂၅၀ဂရမ်'),(7,'Whole','တစ်ခုလုံး'),(8,'Half','တဝက်');
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
  `facebookId` varchar(100) DEFAULT NULL,
  `firebaseId` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `profileName` varchar(50) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `password` text,
  `phoneNo` varchar(30) DEFAULT NULL,
  `userRole` enum('ADMIN','STAFF','USER') NOT NULL,
  `status` enum('ACTIVE','INACTIVE','WAIT') NOT NULL,
  PRIMARY KEY (`userAccountId`),
  UNIQUE KEY `firebaseId` (`firebaseId`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (1,NULL,NULL,'paingpyae.dev@gmail.com','Paing','admin','$2a$10$Tyepame6YxeIRWPO0Qq9PeRS0m4UAl7pLY.Ml0RwOiE4FBSsRV.KK',NULL,'ADMIN','ACTIVE'),(2,'11111111',NULL,NULL,'Paing',NULL,NULL,'092007657','USER','ACTIVE'),(3,NULL,'yaqKd2PnyZXLK7AtNqYW6VRffLP2',NULL,NULL,'+959970074560',NULL,'+959970074560','USER','ACTIVE'),(4,'1737153749767378',NULL,'paingpyaesone.programgroup@gmail.com',NULL,'Paing Pyae Sone',NULL,NULL,'USER','ACTIVE');
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

-- Dump completed on 2020-06-14 21:22:13
