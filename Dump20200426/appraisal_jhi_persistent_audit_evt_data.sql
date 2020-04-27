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
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'remoteAddress','0:0:0:0:0:0:0:1'),(2,'remoteAddress','0:0:0:0:0:0:0:1'),(2,'sessionId','MQ8g6S1cvCJWkbgkttpJ39yRdR6l8nOz9W5Dhgja'),(3,'message','Bad credentials'),(3,'remoteAddress','0:0:0:0:0:0:0:1'),(3,'type','org.springframework.security.authentication.BadCredentialsException'),(4,'message','Bad credentials'),(4,'remoteAddress','0:0:0:0:0:0:0:1'),(4,'sessionId','_bMUrooBL4ApS6NkoJrYwcfF0B2ByA-mXEL3KyIP'),(4,'type','org.springframework.security.authentication.BadCredentialsException'),(5,'remoteAddress','0:0:0:0:0:0:0:1'),(5,'sessionId','_bMUrooBL4ApS6NkoJrYwcfF0B2ByA-mXEL3KyIP'),(6,'remoteAddress','0:0:0:0:0:0:0:1'),(6,'sessionId','8rv_GI6yqWwiUxeZ2penWo8r6DDAXXt4cEOYwwpu'),(7,'remoteAddress','0:0:0:0:0:0:0:1'),(8,'remoteAddress','0:0:0:0:0:0:0:1'),(9,'remoteAddress','0:0:0:0:0:0:0:1'),(10,'remoteAddress','0:0:0:0:0:0:0:1'),(11,'remoteAddress','0:0:0:0:0:0:0:1'),(12,'remoteAddress','0:0:0:0:0:0:0:1'),(13,'remoteAddress','0:0:0:0:0:0:0:1'),(14,'remoteAddress','0:0:0:0:0:0:0:1'),(15,'remoteAddress','0:0:0:0:0:0:0:1'),(16,'remoteAddress','0:0:0:0:0:0:0:1'),(17,'remoteAddress','0:0:0:0:0:0:0:1'),(18,'remoteAddress','0:0:0:0:0:0:0:1'),(19,'remoteAddress','0:0:0:0:0:0:0:1'),(20,'remoteAddress','0:0:0:0:0:0:0:1'),(21,'remoteAddress','0:0:0:0:0:0:0:1'),(22,'remoteAddress','0:0:0:0:0:0:0:1'),(23,'remoteAddress','0:0:0:0:0:0:0:1'),(24,'remoteAddress','0:0:0:0:0:0:0:1'),(25,'remoteAddress','0:0:0:0:0:0:0:1'),(26,'message','Bad credentials'),(26,'remoteAddress','0:0:0:0:0:0:0:1'),(26,'type','org.springframework.security.authentication.BadCredentialsException'),(27,'remoteAddress','0:0:0:0:0:0:0:1'),(27,'sessionId','GbzRptM_2RRi_xbhy54XBcVWOwlTSfaFmPaTJagW'),(28,'remoteAddress','0:0:0:0:0:0:0:1'),(29,'remoteAddress','0:0:0:0:0:0:0:1'),(30,'remoteAddress','0:0:0:0:0:0:0:1'),(31,'remoteAddress','0:0:0:0:0:0:0:1'),(32,'remoteAddress','0:0:0:0:0:0:0:1'),(33,'remoteAddress','0:0:0:0:0:0:0:1'),(34,'remoteAddress','0:0:0:0:0:0:0:1'),(35,'remoteAddress','0:0:0:0:0:0:0:1'),(36,'remoteAddress','0:0:0:0:0:0:0:1'),(37,'remoteAddress','0:0:0:0:0:0:0:1'),(38,'remoteAddress','0:0:0:0:0:0:0:1'),(39,'remoteAddress','0:0:0:0:0:0:0:1'),(40,'remoteAddress','0:0:0:0:0:0:0:1'),(41,'remoteAddress','0:0:0:0:0:0:0:1'),(42,'remoteAddress','0:0:0:0:0:0:0:1'),(43,'remoteAddress','0:0:0:0:0:0:0:1'),(44,'remoteAddress','0:0:0:0:0:0:0:1'),(45,'remoteAddress','0:0:0:0:0:0:0:1'),(46,'remoteAddress','0:0:0:0:0:0:0:1'),(47,'remoteAddress','0:0:0:0:0:0:0:1'),(48,'remoteAddress','0:0:0:0:0:0:0:1'),(49,'remoteAddress','0:0:0:0:0:0:0:1'),(50,'remoteAddress','0:0:0:0:0:0:0:1'),(51,'remoteAddress','0:0:0:0:0:0:0:1'),(52,'message','Bad credentials'),(52,'remoteAddress','0:0:0:0:0:0:0:1'),(52,'type','org.springframework.security.authentication.BadCredentialsException'),(53,'remoteAddress','0:0:0:0:0:0:0:1'),(53,'sessionId','-b1Yzh20zUiSFdOek9jdRujGCl9J_-hY1W6nrQVv'),(54,'remoteAddress','0:0:0:0:0:0:0:1'),(55,'remoteAddress','0:0:0:0:0:0:0:1'),(56,'remoteAddress','0:0:0:0:0:0:0:1'),(57,'remoteAddress','0:0:0:0:0:0:0:1'),(58,'remoteAddress','0:0:0:0:0:0:0:1'),(59,'message','Bad credentials'),(59,'remoteAddress','0:0:0:0:0:0:0:1'),(59,'type','org.springframework.security.authentication.BadCredentialsException'),(60,'message','Bad credentials'),(60,'remoteAddress','0:0:0:0:0:0:0:1'),(60,'sessionId','vyfIxvangmrIjSRx7ak8rUvZY1d_Empz92GQhNlw'),(60,'type','org.springframework.security.authentication.BadCredentialsException'),(61,'remoteAddress','0:0:0:0:0:0:0:1'),(61,'sessionId','vyfIxvangmrIjSRx7ak8rUvZY1d_Empz92GQhNlw'),(62,'remoteAddress','0:0:0:0:0:0:0:1'),(63,'remoteAddress','0:0:0:0:0:0:0:1'),(64,'remoteAddress','0:0:0:0:0:0:0:1'),(65,'remoteAddress','0:0:0:0:0:0:0:1'),(66,'message','Bad credentials'),(66,'remoteAddress','0:0:0:0:0:0:0:1'),(66,'type','org.springframework.security.authentication.BadCredentialsException'),(67,'message','Bad credentials'),(67,'remoteAddress','0:0:0:0:0:0:0:1'),(67,'sessionId','17n_um7D6tD0vdGaW-_XohHw0UaYnTmd10nVchtH'),(67,'type','org.springframework.security.authentication.BadCredentialsException'),(68,'remoteAddress','0:0:0:0:0:0:0:1'),(68,'sessionId','17n_um7D6tD0vdGaW-_XohHw0UaYnTmd10nVchtH'),(69,'remoteAddress','0:0:0:0:0:0:0:1'),(70,'remoteAddress','0:0:0:0:0:0:0:1'),(71,'remoteAddress','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 22:19:03
