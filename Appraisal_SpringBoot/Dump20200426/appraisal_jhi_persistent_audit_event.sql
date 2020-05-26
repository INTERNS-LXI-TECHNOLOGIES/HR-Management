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
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2020-04-11 23:43:34','AUTHENTICATION_SUCCESS'),(2,'admin','2020-04-11 23:51:17','AUTHENTICATION_SUCCESS'),(3,'','2020-04-12 00:47:40','AUTHENTICATION_FAILURE'),(4,'','2020-04-12 00:47:53','AUTHENTICATION_FAILURE'),(5,'admin','2020-04-12 00:52:37','AUTHENTICATION_SUCCESS'),(6,'admin','2020-04-12 01:10:05','AUTHENTICATION_SUCCESS'),(7,'admin','2020-04-12 01:38:10','AUTHENTICATION_SUCCESS'),(8,'admin','2020-04-12 04:59:28','AUTHENTICATION_SUCCESS'),(9,'admin','2020-04-12 05:33:17','AUTHENTICATION_SUCCESS'),(10,'admin','2020-04-12 09:25:38','AUTHENTICATION_SUCCESS'),(11,'admin','2020-04-12 22:25:26','AUTHENTICATION_SUCCESS'),(12,'admin','2020-04-12 22:27:58','AUTHENTICATION_SUCCESS'),(13,'admin','2020-04-13 00:21:49','AUTHENTICATION_SUCCESS'),(14,'admin','2020-04-13 00:24:27','AUTHENTICATION_SUCCESS'),(15,'admin','2020-04-13 00:59:55','AUTHENTICATION_SUCCESS'),(16,'admin','2020-04-13 05:37:52','AUTHENTICATION_SUCCESS'),(17,'admin','2020-04-13 11:30:56','AUTHENTICATION_SUCCESS'),(18,'admin','2020-04-13 22:37:39','AUTHENTICATION_SUCCESS'),(19,'admin','2020-04-14 00:10:32','AUTHENTICATION_SUCCESS'),(20,'admin','2020-04-14 00:28:45','AUTHENTICATION_SUCCESS'),(21,'admin','2020-04-14 00:43:42','AUTHENTICATION_SUCCESS'),(22,'admin','2020-04-14 02:18:04','AUTHENTICATION_SUCCESS'),(23,'shabeen','2020-04-14 02:18:40','AUTHENTICATION_SUCCESS'),(24,'admin','2020-04-14 02:22:10','AUTHENTICATION_SUCCESS'),(25,'admin','2020-04-14 22:19:48','AUTHENTICATION_SUCCESS'),(26,'admin','2020-04-15 00:01:22','AUTHENTICATION_FAILURE'),(27,'admin','2020-04-15 00:01:29','AUTHENTICATION_SUCCESS'),(28,'admin','2020-04-15 00:22:23','AUTHENTICATION_SUCCESS'),(29,'admin','2020-04-15 08:56:17','AUTHENTICATION_SUCCESS'),(30,'admin','2020-04-15 22:49:36','AUTHENTICATION_SUCCESS'),(31,'admin','2020-04-15 23:07:52','AUTHENTICATION_SUCCESS'),(32,'admin','2020-04-16 00:52:26','AUTHENTICATION_SUCCESS'),(33,'admin','2020-04-16 03:08:55','AUTHENTICATION_SUCCESS'),(34,'admin','2020-04-16 11:33:45','AUTHENTICATION_SUCCESS'),(35,'admin','2020-04-16 23:20:19','AUTHENTICATION_SUCCESS'),(36,'admin','2020-04-16 23:37:19','AUTHENTICATION_SUCCESS'),(37,'admin','2020-04-16 23:58:23','AUTHENTICATION_SUCCESS'),(38,'admin','2020-04-17 00:39:19','AUTHENTICATION_SUCCESS'),(39,'admin','2020-04-17 04:45:09','AUTHENTICATION_SUCCESS'),(40,'admin','2020-04-19 02:25:48','AUTHENTICATION_SUCCESS'),(41,'admin','2020-04-19 02:28:48','AUTHENTICATION_SUCCESS'),(42,'admin','2020-04-19 04:46:16','AUTHENTICATION_SUCCESS'),(43,'admin','2020-04-19 11:58:29','AUTHENTICATION_SUCCESS'),(44,'admin','2020-04-19 12:00:39','AUTHENTICATION_SUCCESS'),(45,'admin','2020-04-19 22:46:16','AUTHENTICATION_SUCCESS'),(46,'meharunnisa','2020-04-19 23:15:39','AUTHENTICATION_SUCCESS'),(47,'admin','2020-04-19 23:16:03','AUTHENTICATION_SUCCESS'),(48,'meharunnisa','2020-04-19 23:17:31','AUTHENTICATION_SUCCESS'),(49,'noorunnisa','2020-04-19 23:19:01','AUTHENTICATION_SUCCESS'),(50,'mihrajunnisa','2020-04-19 23:19:22','AUTHENTICATION_SUCCESS'),(51,'noorunnisa','2020-04-19 23:19:40','AUTHENTICATION_SUCCESS'),(52,'noorunnisa','2020-04-20 00:08:06','AUTHENTICATION_FAILURE'),(53,'noorunnisa','2020-04-20 00:08:20','AUTHENTICATION_SUCCESS'),(54,'noorunnisa','2020-04-20 00:13:13','AUTHENTICATION_SUCCESS'),(55,'mihrajunnisa','2020-04-20 00:19:56','AUTHENTICATION_SUCCESS'),(56,'noorunnisa','2020-04-20 00:24:09','AUTHENTICATION_SUCCESS'),(57,'noorunnisa','2020-04-20 01:29:26','AUTHENTICATION_SUCCESS'),(58,'noorunnisa','2020-04-20 21:43:53','AUTHENTICATION_SUCCESS'),(59,'admin','2020-04-20 22:53:34','AUTHENTICATION_FAILURE'),(60,'admin','2020-04-20 22:53:45','AUTHENTICATION_FAILURE'),(61,'noorunnisa','2020-04-20 22:54:09','AUTHENTICATION_SUCCESS'),(62,'noorunnisa','2020-04-24 00:30:30','AUTHENTICATION_SUCCESS'),(63,'noorunnisa','2020-04-24 10:35:04','AUTHENTICATION_SUCCESS'),(64,'noorunnisa','2020-04-24 22:11:57','AUTHENTICATION_SUCCESS'),(65,'noorunnisa','2020-04-25 03:28:50','AUTHENTICATION_SUCCESS'),(66,'noorunnisa','2020-04-26 01:10:23','AUTHENTICATION_FAILURE'),(67,'noorunnisa','2020-04-26 01:10:28','AUTHENTICATION_FAILURE'),(68,'noorunnisa','2020-04-26 01:10:44','AUTHENTICATION_SUCCESS'),(69,'noorunnisa','2020-04-26 10:59:00','AUTHENTICATION_SUCCESS'),(70,'mihrajunnisa','2020-04-26 10:59:31','AUTHENTICATION_SUCCESS'),(71,'noorunnisa','2020-04-26 11:10:01','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
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