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
-- Table structure for table `appraisal`
--

DROP TABLE IF EXISTS `appraisal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appraisal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attendance` bigint(20) DEFAULT NULL,
  `punctuality` bigint(20) DEFAULT NULL,
  `meeting_targets` bigint(20) DEFAULT NULL,
  `company_policy` bigint(20) DEFAULT NULL,
  `code_quality` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `user_extra_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_appraisal_user_extra_id` (`user_extra_id`),
  CONSTRAINT `fk_appraisal_user_extra_id` FOREIGN KEY (`user_extra_id`) REFERENCES `user_extra` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appraisal`
--

LOCK TABLES `appraisal` WRITE;
/*!40000 ALTER TABLE `appraisal` DISABLE KEYS */;
INSERT INTO `appraisal` VALUES (5,22960,2320,63476,82907,18409,'2020-04-07',NULL),(6,4,4,4,5,0,'2020-04-25',6),(7,4735,1932,93121,677,12731,'2020-04-08',NULL),(8,5,4,4,5,2,'2020-04-24',8),(9,8834,70797,595,81839,814,'2020-04-07',NULL),(10,5,5,5,5,3,'2020-04-25',10),(21,4,4,4,5,0,'2020-04-14',6),(41,4,4,4,5,0,'2020-04-15',6),(44,4,4,4,5,0,'2020-04-15',6),(45,4,4,4,5,0,'2020-04-15',6),(46,4,4,4,5,0,'2020-04-15',6),(47,4,4,4,5,0,'2020-04-15',6),(48,4,4,4,5,0,'2020-04-15',6),(49,4,4,4,5,0,'2020-04-15',6),(54,4,4,4,5,0,'2020-04-15',6),(60,4,4,4,5,0,'2020-04-15',6),(61,4,4,4,5,0,'2020-04-15',6),(62,4,4,4,5,0,'2020-04-15',6),(65,4,4,4,5,0,'2020-04-15',6),(66,4,4,4,5,0,'2020-04-15',6),(67,4,4,4,5,0,'2020-04-15',6),(68,4,4,4,5,0,'2020-04-15',6),(69,4,4,4,5,0,'2020-04-15',6),(70,4,4,4,5,0,'2020-04-15',6),(71,4,4,4,5,0,'2020-04-15',6),(76,4,4,4,5,0,'2020-04-15',6),(81,4,4,4,5,0,'2020-04-15',6),(82,5,5,5,5,0,'2020-04-19',11),(83,5,5,5,5,0,'2020-04-19',12),(84,5,5,5,5,0,'2020-04-19',11),(85,5,5,5,5,0,'2020-04-19',12),(86,5,5,5,5,0,'2020-04-19',11),(87,5,5,5,5,0,'2020-04-19',12),(88,5,5,5,5,0,'2020-04-19',13),(89,5,5,5,5,0,'2020-04-19',11),(90,5,5,5,5,0,'2020-04-19',12),(91,5,5,5,5,0,'2020-04-19',13),(92,5,5,5,5,0,'2020-04-19',13),(93,5,5,5,5,0,'2020-04-20',13),(94,5,5,5,5,0,'2020-04-20',11),(95,5,5,5,5,0,'2020-04-20',12),(96,5,5,5,5,0,'2020-04-20',13),(97,5,5,5,5,0,'2020-04-20',11),(98,5,5,5,5,0,'2020-04-20',12),(99,5,5,5,5,0,'2020-04-20',13),(100,5,5,5,5,0,'2020-04-24',11),(101,5,5,5,5,0,'2020-04-24',12),(102,5,5,5,5,0,'2020-04-24',13),(103,5,5,5,5,0,'2020-04-25',13),(104,5,5,5,5,0,'2020-04-25',13),(105,5,5,5,5,0,'2020-04-25',13);
/*!40000 ALTER TABLE `appraisal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 22:19:05