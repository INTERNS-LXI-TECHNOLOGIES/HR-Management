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
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(191) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(10) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (6,'shabeen','$2a$10$MscU5EvUvBYcfIqtMb6RpuDicQcWxBfzrG9ALuhY3KO9oOiS.6Xy.','muhammed','shabeenn','shabeen@gmail.com',NULL,'',NULL,NULL,NULL,'anonymousUser','2020-04-14 02:17:40',NULL,'noorunnisa','2020-04-20 00:14:35'),(8,'ayisha','$2a$10$ww1jnsEr0tNmwPXiGCs.w.1UxnssiAApgLdkrmA1SGO3G.KHxad5a','ayisha','izzah','ayish@gmail.com',NULL,'',NULL,NULL,NULL,'admin','2020-04-17 00:41:04',NULL,'admin','2020-04-17 00:41:04'),(10,'mihrajunnisa','$2a$10$No4U1R0si1Z.IBPTvDRKl.8gbLeVZlEnxGVGDQUcU6tk.tjQnK83u','mihraj','nnisamk','mihraj@gmail.com',NULL,'',NULL,NULL,NULL,'admin','2020-04-19 22:53:22',NULL,'noorunnisa','2020-04-19 23:58:05'),(11,'meharunnisa','$2a$10$LuRNk1YOGj9Dj60yfu55vuYHvFzgrXJvFV6cj4EZcEv.1Hx2khPdO','meharu','nnisa','meharuhm@gmail.com',NULL,'',NULL,NULL,NULL,'admin','2020-04-19 23:15:20',NULL,'admin','2020-04-19 23:15:20'),(12,'noorunnisa','$2a$10$gGmTYh7Wf44CMLs2qoQEdOhQ35oLoB9frxIDcUzO5.KTYl4L.Bq.q','nooru','nnisa','noor@gmail.com',NULL,'',NULL,NULL,NULL,'meharunnisa','2020-04-19 23:18:40',NULL,'meharunnisa','2020-04-19 23:18:40'),(13,'jithumk','$2a$10$lfZaX3NejqG6V/lcDEoIVu.drIHjys.mNzdOrGAkSPmS7V9u3h6Mq','muhammed','jith','jith@gmail.com',NULL,'',NULL,NULL,NULL,'noorunnisa','2020-04-20 00:18:32',NULL,'noorunnisa','2020-04-20 00:18:32');
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
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
