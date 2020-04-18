CREATE DATABASE  IF NOT EXISTS `personaldiet_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `personaldiet_db`;
-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: personaldiet_db
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `foodgroup`
--

DROP TABLE IF EXISTS `foodgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foodgroup` (
  `id_foodgroup` tinyint NOT NULL AUTO_INCREMENT,
  `fg_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id_foodgroup`),
  UNIQUE KEY `fg_name_UNIQUE` (`fg_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodgroup`
--

LOCK TABLES `foodgroup` WRITE;
/*!40000 ALTER TABLE `foodgroup` DISABLE KEYS */;
INSERT INTO `foodgroup` VALUES (1,'Fruits and Vegetables'),(2,'Grain Products'),(4,'Meat and Alternatives'),(3,'Milk and Alternatives');
/*!40000 ALTER TABLE `foodgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fooditem`
--

DROP TABLE IF EXISTS `fooditem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fooditem` (
  `id_fooditem` int NOT NULL AUTO_INCREMENT,
  `foodname` varchar(45) DEFAULT NULL,
  `calories` decimal(7,2) DEFAULT NULL,
  `carbs` decimal(7,2) DEFAULT NULL,
  `protein` decimal(7,2) DEFAULT NULL,
  `fat` decimal(7,2) DEFAULT NULL,
  `id_foodgroup` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_fooditem`),
  UNIQUE KEY `foodname_UNIQUE` (`foodname`),
  KEY `id_foodgroup_idx` (`id_foodgroup`),
  CONSTRAINT `id_foodgroup` FOREIGN KEY (`id_foodgroup`) REFERENCES `foodgroup` (`id_foodgroup`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fooditem`
--

LOCK TABLES `fooditem` WRITE;
/*!40000 ALTER TABLE `fooditem` DISABLE KEYS */;
INSERT INTO `fooditem` VALUES (1,'apple',100.00,3.40,0.00,45.00,1),(2,'milk',250.00,45.00,30.00,2.50,3),(3,'mango',100.00,3.00,2.00,4.00,1),(4,'potato',400.00,4.70,3.00,3.00,1),(7,'banana',40.00,46.00,2.00,35.00,1),(8,'beets',1.00,1.00,1.00,1.00,1),(13,'rice',100.00,77.00,2.00,98.00,2),(14,'depanneur',1.00,1.00,1.00,1.00,3),(16,'tim hortons',5.00,2.00,6.00,4.00,3);
/*!40000 ALTER TABLE `fooditem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Infoodentries`
--

DROP TABLE IF EXISTS `Infoodentries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Infoodentries` (
  `id_fooditem` int NOT NULL,
  `entry_time` time NOT NULL,
  `entry_date` varchar(45) NOT NULL,
  `serving` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_fooditem`,`entry_time`,`entry_date`),
  CONSTRAINT `id_fooditem` FOREIGN KEY (`id_fooditem`) REFERENCES `fooditem` (`id_fooditem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Infoodentries`
--

LOCK TABLES `Infoodentries` WRITE;
/*!40000 ALTER TABLE `Infoodentries` DISABLE KEYS */;
INSERT INTO `Infoodentries` VALUES (1,'13:30:00','april 15','1 item'),(1,'22:13:00','Thursday April 16','1 portion'),(4,'01:10:00','Wednesday April 15','90 g'),(7,'22:50:00','Thursday April 16','1 portion'),(8,'22:59:00','Thursday April 16','1 portion'),(13,'01:51:00','Thursday April 16','1 portion');
/*!40000 ALTER TABLE `Infoodentries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Outfoodentries`
--

DROP TABLE IF EXISTS `Outfoodentries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Outfoodentries` (
  `id_fooditem` int NOT NULL,
  `entry_time` time NOT NULL,
  `entry_date` varchar(45) NOT NULL,
  `meal` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_fooditem`,`entry_time`,`entry_date`),
  CONSTRAINT `id_fooditem2` FOREIGN KEY (`id_fooditem`) REFERENCES `fooditem` (`id_fooditem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Outfoodentries`
--

LOCK TABLES `Outfoodentries` WRITE;
/*!40000 ALTER TABLE `Outfoodentries` DISABLE KEYS */;
INSERT INTO `Outfoodentries` VALUES (14,'18:33:00','Friday April 17','snack'),(16,'18:33:00','Friday April 17','lunch');
/*!40000 ALTER TABLE `Outfoodentries` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-18 13:18:57
