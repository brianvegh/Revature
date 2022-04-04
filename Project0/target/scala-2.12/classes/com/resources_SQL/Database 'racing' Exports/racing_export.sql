CREATE DATABASE  IF NOT EXISTS `racing` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `racing`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: racing
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `ID` int NOT NULL,
  `Name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CarNumber` int DEFAULT NULL,
  `CarMake` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CarModel` varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CarYear` int DEFAULT NULL,
  `CarCountry` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'Brian Vegh',256,'BMW','M3',1999,'Germany'),(2,'Mark Vegh',23,'Oldsmobile','Cutlass',1983,'America'),(3,'Crazy Kenny Thomas',51,'Pontiac','Gran Prix',1982,'America'),(4,'Dale Earnheart',3,'Chevrolet','Monte Carlo',1980,'America'),(5,'Ricky Bobby',26,'Chevrolet','Malibu',1969,'America'),(6,'Jerry Seinfeld',9,'Porsche','959',1983,'Germany'),(7,'The Bandit',0,'Pontiac','Friebird Trans Am',1977,'America'),(8,'Ken Block',43,'Subaru','WRX STI',2008,'Japan'),(9,'Paul Newman',82,'Nissan','280ZX',1979,'Japan');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `points`
--

DROP TABLE IF EXISTS `points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `points` (
  `Position` int NOT NULL,
  `PointValue` int DEFAULT NULL,
  PRIMARY KEY (`Position`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `points`
--

LOCK TABLES `points` WRITE;
/*!40000 ALTER TABLE `points` DISABLE KEYS */;
INSERT INTO `points` VALUES (1,20),(2,16),(3,13),(4,11),(5,9),(6,7),(7,6),(8,5),(9,4),(10,3),(11,2),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1);
/*!40000 ALTER TABLE `points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `resultID_Key` int NOT NULL AUTO_INCREMENT,
  `RaceNumber` int DEFAULT NULL,
  `DriverID` int DEFAULT NULL,
  `Position` int DEFAULT NULL,
  `Finished` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`resultID_Key`),
  KEY `RaceNumber` (`RaceNumber`),
  KEY `DriverID` (`DriverID`),
  KEY `Position` (`Position`),
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`RaceNumber`) REFERENCES `schedule` (`RaceNumber`),
  CONSTRAINT `result_ibfk_2` FOREIGN KEY (`DriverID`) REFERENCES `driver` (`ID`),
  CONSTRAINT `result_ibfk_3` FOREIGN KEY (`Position`) REFERENCES `points` (`Position`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,1,1,1,1),(2,1,2,2,1),(3,1,3,9,1),(4,1,4,8,1),(5,1,5,7,1),(6,1,6,6,1),(7,1,7,5,1),(8,1,8,4,1),(9,1,9,3,1),(10,2,1,6,1),(11,2,2,5,1),(12,2,3,4,1),(13,2,4,7,1),(14,2,5,8,0),(15,2,6,9,0),(16,2,7,3,1),(17,2,8,2,1),(18,2,9,1,1),(19,3,1,4,1),(20,3,2,3,1),(21,3,3,7,1),(22,3,4,6,1),(23,3,5,1,1),(24,3,6,7,1),(25,3,7,2,1),(26,3,8,9,0),(27,3,9,5,1),(28,4,1,3,1),(29,4,2,1,1),(30,4,3,5,1),(31,4,4,2,1),(32,4,5,4,1),(33,4,6,9,1),(34,4,7,8,1),(35,4,8,7,1),(36,4,9,6,1),(37,5,1,2,1),(38,5,2,3,1),(39,5,3,6,1),(40,5,4,7,1),(41,5,5,8,1),(42,5,6,9,0),(43,5,7,5,1),(44,5,8,4,1),(45,5,9,1,1);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `RaceNumber` int NOT NULL AUTO_INCREMENT,
  `TrackID` int DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  PRIMARY KEY (`RaceNumber`),
  KEY `TrackID` (`TrackID`),
  CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`TrackID`) REFERENCES `track` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,1,'2021-04-02 00:00:00'),(2,2,'2021-04-16 00:00:00'),(3,3,'2021-04-30 00:00:00'),(4,4,'2021-05-07 00:00:00'),(5,5,'2021-05-21 00:00:00');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track` (
  `ID` int NOT NULL,
  `Name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `City` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `State` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Length` decimal(3,2) DEFAULT NULL,
  `Turns` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,'Summit Point Motorsports Park','Summit Point','WV',2.00,10),(2,'Watkins Glenn International','Watkins Glen','NY',3.45,11),(3,'Road Atlanta','Braselton','GA',2.54,12),(4,'Lime Rock Park','Lakeville','CT',1.53,7),(5,'Brainerd International Raceway','Braselton','MN',2.50,13);
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-14 11:26:52
