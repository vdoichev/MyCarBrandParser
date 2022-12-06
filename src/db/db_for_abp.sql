-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: localhost    Database: db_for_abp
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `Equipment`
--

DROP TABLE IF EXISTS `Equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Equipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `model_id` int DEFAULT NULL,
  `code` varchar(45) NOT NULL COMMENT 'Код комлектації',
  `dateRange` varchar(45) DEFAULT NULL,
  `engine1` varchar(45) DEFAULT NULL,
  `body` varchar(45) DEFAULT NULL,
  `grade` varchar(45) DEFAULT NULL,
  `atmMtm` varchar(45) DEFAULT NULL,
  `gearShiftType` varchar(45) DEFAULT NULL,
  `cab` varchar(45) DEFAULT NULL,
  `transmissionModel` varchar(45) DEFAULT NULL,
  `loadingCapacity` varchar(45) DEFAULT NULL,
  `rearTire` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `fuelInduction` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `model_id_fk_idx` (`model_id`),
  CONSTRAINT `model_id_fk` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Комплектація авто';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mark` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Ідентифікатор марки авто',
  `name` varchar(45) NOT NULL COMMENT 'Назва марки авто',
  `catalogGroup` varchar(45) DEFAULT NULL COMMENT 'Група каталогів',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Марка авто';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `market`
--

DROP TABLE IF EXISTS `market`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `market` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Ідентифікатор ринку збуту',
  `code` varchar(2) NOT NULL COMMENT 'Код країни',
  `name` varchar(45) DEFAULT NULL COMMENT 'Назва країни',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Ринок збуту';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Ідентифікатор моделі авто',
  `name` varchar(45) NOT NULL COMMENT 'Назва моделі авто',
  `code` varchar(45) NOT NULL COMMENT 'Код моделі',
  `date_from` datetime DEFAULT NULL COMMENT 'Дата виробництва',
  `mark_id` int NOT NULL COMMENT 'Посилання на марку авто',
  `market_id` int NOT NULL COMMENT 'Посилання на ринок збуту авто',
  PRIMARY KEY (`id`),
  UNIQUE KEY `model_code_UNIQUE` (`code`),
  KEY `mark_id_fk_idx` (`mark_id`),
  KEY `market_id_fk_idx` (`market_id`),
  CONSTRAINT `mark_id_fk` FOREIGN KEY (`mark_id`) REFERENCES `mark` (`id`),
  CONSTRAINT `market_id_fk` FOREIGN KEY (`market_id`) REFERENCES `market` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Модель автомобіля';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-06 15:45:13
