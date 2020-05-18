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
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2020-04-12 10:34:07',1,'EXECUTED','8:dbc3cbe43c494a58902289d23f01aeab','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; createTable tableName=jhi_persistent_token; addForeignKeyConstraint baseTableName=jhi_user_a...','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064600-1','jhipster','config/liquibase/changelog/20200327064600_added_entity_UserExtra.xml','2020-04-12 10:34:08',2,'EXECUTED','8:dca50c6c56603fba491e32ac70da3324','createTable tableName=user_extra','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064600-1-relations','jhipster','config/liquibase/changelog/20200327064600_added_entity_UserExtra.xml','2020-04-12 10:34:08',3,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064600-1-data','jhipster','config/liquibase/changelog/20200327064600_added_entity_UserExtra.xml','2020-04-12 10:34:08',4,'EXECUTED','8:7fc91c6c92c0ea329c1a7d2e6dd95880','loadData tableName=user_extra','',NULL,'3.8.7','faker',NULL,'6667842024'),('20200327064700-1','jhipster','config/liquibase/changelog/20200327064700_added_entity_ReportStatus.xml','2020-04-12 10:34:09',5,'EXECUTED','8:306b790dab9b127304befde23ed902ad','createTable tableName=report_status; dropDefaultValue columnName=reporting_time, tableName=report_status','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064700-1-relations','jhipster','config/liquibase/changelog/20200327064700_added_entity_ReportStatus.xml','2020-04-12 10:34:09',6,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064700-1-data','jhipster','config/liquibase/changelog/20200327064700_added_entity_ReportStatus.xml','2020-04-12 10:34:09',7,'EXECUTED','8:0f860609b1aaf2e9557b67651ef44443','loadData tableName=report_status','',NULL,'3.8.7','faker',NULL,'6667842024'),('20200327064800-1','jhipster','config/liquibase/changelog/20200327064800_added_entity_Leave.xml','2020-04-12 10:34:09',8,'EXECUTED','8:669bb3fabfebd7220a12222f4cf5e755','createTable tableName=jhi_leave','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064800-1-relations','jhipster','config/liquibase/changelog/20200327064800_added_entity_Leave.xml','2020-04-12 10:34:09',9,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064800-1-data','jhipster','config/liquibase/changelog/20200327064800_added_entity_Leave.xml','2020-04-12 10:34:10',10,'EXECUTED','8:857b93d43d57cce58400d3acdf76bd5b','loadData tableName=jhi_leave','',NULL,'3.8.7','faker',NULL,'6667842024'),('20200327064900-1','jhipster','config/liquibase/changelog/20200327064900_added_entity_LateArrival.xml','2020-04-12 10:34:10',11,'EXECUTED','8:2f7bfadabaa4bb69c88e090a46a2e0ae','createTable tableName=late_arrival; dropDefaultValue columnName=reached_time, tableName=late_arrival','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064900-1-relations','jhipster','config/liquibase/changelog/20200327064900_added_entity_LateArrival.xml','2020-04-12 10:34:10',12,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064900-1-data','jhipster','config/liquibase/changelog/20200327064900_added_entity_LateArrival.xml','2020-04-12 10:34:10',13,'EXECUTED','8:f8765d85192f0fddaefc8171e0f918c8','loadData tableName=late_arrival','',NULL,'3.8.7','faker',NULL,'6667842024'),('20200327065000-1','jhipster','config/liquibase/changelog/20200327065000_added_entity_Hackathon.xml','2020-04-12 10:34:11',14,'EXECUTED','8:c5c62fe71ff67aacaac3b05ecb00accb','createTable tableName=hackathon','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327065000-1-relations','jhipster','config/liquibase/changelog/20200327065000_added_entity_Hackathon.xml','2020-04-12 10:34:11',15,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327065000-1-data','jhipster','config/liquibase/changelog/20200327065000_added_entity_Hackathon.xml','2020-04-12 10:34:11',16,'EXECUTED','8:aefd219a3858e904c385e6ba39ca7afd','loadData tableName=hackathon','',NULL,'3.8.7','faker',NULL,'6667842024'),('20200327065100-1','jhipster','config/liquibase/changelog/20200327065100_added_entity_Git.xml','2020-04-12 10:34:11',17,'EXECUTED','8:5d3e6e3ebbb68661915e27a33b1e122a','createTable tableName=git','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327065100-1-relations','jhipster','config/liquibase/changelog/20200327065100_added_entity_Git.xml','2020-04-12 10:34:11',18,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327065100-1-data','jhipster','config/liquibase/changelog/20200327065100_added_entity_Git.xml','2020-04-12 10:34:11',19,'EXECUTED','8:b116236abb2a9c18c9414c38abfca488','loadData tableName=git','',NULL,'3.8.7','faker',NULL,'6667842024'),('20200409033827-1','jhipster','config/liquibase/changelog/20200409033827_added_entity_Appraisal.xml','2020-04-12 10:34:12',20,'EXECUTED','8:08074b9e6fe1ed9a7a27265770369e82','createTable tableName=appraisal','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200409033827-1-relations','jhipster','config/liquibase/changelog/20200409033827_added_entity_Appraisal.xml','2020-04-12 10:34:12',21,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200409033827-1-data','jhipster','config/liquibase/changelog/20200409033827_added_entity_Appraisal.xml','2020-04-12 10:34:12',22,'EXECUTED','8:4786c3ddf46ec27ea353a62344efe727','loadData tableName=appraisal','',NULL,'3.8.7','faker',NULL,'6667842024'),('20200327065100-2','jhipster','config/liquibase/changelog/20200327065100_added_entity_constraints_Git.xml','2020-04-12 10:34:12',23,'EXECUTED','8:8a481b51955471e711a88fcbd42ab867','addForeignKeyConstraint baseTableName=git, constraintName=fk_git_user_extra_id, referencedTableName=user_extra','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327065000-2','jhipster','config/liquibase/changelog/20200327065000_added_entity_constraints_Hackathon.xml','2020-04-12 10:34:13',24,'EXECUTED','8:f341c79068f974fae256c809c043e64f','addForeignKeyConstraint baseTableName=hackathon, constraintName=fk_hackathon_user_extra_id, referencedTableName=user_extra','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064900-2','jhipster','config/liquibase/changelog/20200327064900_added_entity_constraints_LateArrival.xml','2020-04-12 10:34:14',25,'EXECUTED','8:a06f8937830c1b4e963f2ed301d86f19','addForeignKeyConstraint baseTableName=late_arrival, constraintName=fk_late_arrival_user_extra_id, referencedTableName=user_extra','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064800-2','jhipster','config/liquibase/changelog/20200327064800_added_entity_constraints_Leave.xml','2020-04-12 10:34:15',26,'EXECUTED','8:4a15c76bef19c8d32b08ad0a64131efd','addForeignKeyConstraint baseTableName=jhi_leave, constraintName=fk_jhi_leave_user_extra_id, referencedTableName=user_extra','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064700-2','jhipster','config/liquibase/changelog/20200327064700_added_entity_constraints_ReportStatus.xml','2020-04-12 10:34:15',27,'EXECUTED','8:e1a221dc2780b2a7611c7f020f669e63','addForeignKeyConstraint baseTableName=report_status, constraintName=fk_report_status_user_extra_id, referencedTableName=user_extra','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200327064600-2','jhipster','config/liquibase/changelog/20200327064600_added_entity_constraints_UserExtra.xml','2020-04-12 10:34:15',28,'EXECUTED','8:35f08228e2896cfd971fe51f0ac2338b','addForeignKeyConstraint baseTableName=user_extra, constraintName=fk_user_extra_user_id, referencedTableName=jhi_user','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200409033827-2','jhipster','config/liquibase/changelog/20200409033827_added_entity_constraints_Appraisal.xml','2020-04-12 10:34:16',29,'EXECUTED','8:ffc830caf32bc1de4d93baac72956db8','addForeignKeyConstraint baseTableName=appraisal, constraintName=fk_appraisal_user_extra_id, referencedTableName=user_extra','',NULL,'3.8.7',NULL,NULL,'6667842024'),('20200412053500-1','jhipster','config/liquibase/changelog/20200412053500_added_entity_UserDataBean.xml','2020-04-13 11:56:17',30,'EXECUTED','8:c27eb25ee3e026232b05c3096c5fa093','createTable tableName=user_data_bean','',NULL,'3.8.7',NULL,NULL,'6759176073'),('20200412053500-1-relations','jhipster','config/liquibase/changelog/20200412053500_added_entity_UserDataBean.xml','2020-04-13 11:56:17',31,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'6759176073'),('20200412053500-1-data','jhipster','config/liquibase/changelog/20200412053500_added_entity_UserDataBean.xml','2020-04-13 11:56:17',32,'EXECUTED','8:1ee2f99273bb3e4b651710a0556eb5d9','loadData tableName=user_data_bean','',NULL,'3.8.7','faker',NULL,'6759176073');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 22:19:02
