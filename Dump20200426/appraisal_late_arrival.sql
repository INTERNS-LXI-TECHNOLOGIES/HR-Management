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
-- Table structure for table `late_arrival`
--

DROP TABLE IF EXISTS `late_arrival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `late_arrival` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reached_time` datetime,
  `type` varchar(255) DEFAULT NULL,
  `user_extra_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_late_arrival_user_extra_id` (`user_extra_id`),
  CONSTRAINT `fk_late_arrival_user_extra_id` FOREIGN KEY (`user_extra_id`) REFERENCES `user_extra` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `late_arrival`
--

LOCK TABLES `late_arrival` WRITE;
/*!40000 ALTER TABLE `late_arrival` DISABLE KEYS */;
INSERT INTO `late_arrival` VALUES (1,'2020-03-26 09:24:28','Human',NULL),(2,'2020-03-26 03:14:49','withdrawal envisioneer empower',NULL),(3,'2020-03-26 04:31:51','Sleek Wooden Chair Quality',NULL),(4,'2020-03-26 02:58:31','encoding reboot Intelligent',NULL),(5,'2020-03-26 03:55:36','SMTP Orchestrator',NULL),(6,'2020-03-26 18:07:04','withdrawal synergize Dobra',NULL),(7,'2020-03-26 06:07:21','EXE Spur',NULL),(8,'2020-03-26 01:32:09','Granite Credit Card Account',NULL),(9,'2020-03-26 07:42:10','Gorgeous',NULL),(10,'2020-03-26 23:44:32','Iowa Brand',NULL),(13,'2020-04-19 10:29:00','NonAuthorized',6),(16,'2020-04-18 21:31:00','NonAuthorized',6),(17,'2020-04-18 20:31:00','NonAuthorized',8),(19,'2020-04-20 08:32:00','NonAuthorized',10);
/*!40000 ALTER TABLE `late_arrival` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 22:19:07
