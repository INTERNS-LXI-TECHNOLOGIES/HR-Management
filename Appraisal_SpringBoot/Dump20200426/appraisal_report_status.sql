CREATE DATABASE  IF NOT EXISTS `appraisal` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `appraisal`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: appraisal
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `report_status`
--

DROP TABLE IF EXISTS `report_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reporting_time` datetime,
  `type` varchar(255) DEFAULT NULL,
  `user_extra_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_report_status_user_extra_id` (`user_extra_id`),
  CONSTRAINT `fk_report_status_user_extra_id` FOREIGN KEY (`user_extra_id`) REFERENCES `user_extra` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_status`
--

LOCK TABLES `report_status` WRITE;
/*!40000 ALTER TABLE `report_status` DISABLE KEYS */;
INSERT INTO `report_status` VALUES (1,'2020-03-26 17:03:51','North Dakota users Sleek',NULL),(2,'2020-03-26 04:53:09','violet Metal',NULL),(3,'2020-03-26 18:56:56','calculating compress withdrawal',NULL),(4,'2020-03-26 21:31:24','Seychelles Rupee customer loyalty payment',NULL),(5,'2020-03-26 20:32:09','Granite',NULL),(6,'2020-03-26 15:26:01','Sleek Chips',NULL),(7,'2020-03-26 12:46:23','cyan',NULL),(8,'2020-03-26 01:58:01','Sports 1080p',NULL),(9,'2020-03-26 12:33:05','innovative',NULL),(10,'2020-03-26 11:42:25','overriding Intranet',NULL),(11,'2020-04-16 19:31:00','NonAuthorized',6),(14,'2020-04-20 08:31:00','NonAuthorized',11);
/*!40000 ALTER TABLE `report_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 22:19:06
