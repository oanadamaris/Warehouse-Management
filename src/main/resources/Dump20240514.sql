-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: warehouse
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `Bill`
--

DROP TABLE IF EXISTS `Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idOrder` int NOT NULL,
  `total` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idBill_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bill`
--

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;
INSERT INTO `Bill` VALUES (1,1,100),(3,3,750),(4,4,90);
/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Client` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idClient_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` VALUES (1,'Oana','oanar@yahoo.com','A Iancu, 410'),(2,'Dan','dandeac@gmail.com','Livezii, 11'),(4,'Simona','simou@gmail.com','Parang, 20'),(5,'Aurel','aurel@yahoo.com','Acasa, 5'),(6,'Ligia','ligiam@yardi.com','A Iancu, 410');
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders` (
  `id` int NOT NULL,
  `client` int NOT NULL,
  `product` int NOT NULL,
  `quantityO` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idOrder_UNIQUE` (`id`),
  KEY `client_idx` (`client`),
  KEY `product_idx` (`product`),
  CONSTRAINT `client` FOREIGN KEY (`client`) REFERENCES `Client` (`id`),
  CONSTRAINT `product` FOREIGN KEY (`product`) REFERENCES `Product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (1,1,1,10);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Product` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `quantity` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idProduct_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'Cozonac',0,10),(2,'Perna',180,20),(3,'Merisoare',300,30),(4,'Catel',400,40),(5,'Slapi',500,50);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-14 14:05:34
