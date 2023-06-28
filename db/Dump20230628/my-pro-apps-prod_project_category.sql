-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: my-pro-apps-prod
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
-- Table structure for table `project_category`
--

DROP TABLE IF EXISTS `project_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `date_creation` datetime(6),
  `date_modify` datetime(6),
  `last_modify_by` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_category`
--

LOCK TABLES `project_category` WRITE;
/*!40000 ALTER TABLE `project_category` DISABLE KEYS */;
INSERT INTO `project_category` VALUES (1,'RESEARCH','Research, studies project','2023-05-25 15:00:00.000000','2023-05-25 15:07:41.416726','admin-louah','admin-louah',NULL),(2,'PROD-DEV','Product Developement ','2023-05-25 15:01:17.877000','2023-05-25 15:06:53.564951','admin-louah','admin-louah',NULL),(3,'CERT','Product certification project','2023-05-25 15:02:36.485359','2023-05-25 15:02:36.485359','admin-louah','admin-louah',NULL),(4,'HW','Hardware project','2023-05-25 15:03:57.486243','2023-05-25 15:03:57.486243','admin-louah','admin-louah',NULL),(5,'HR','Humain ressources projet','2023-05-25 15:04:44.084486','2023-05-25 15:04:44.084486','admin-louah','admin-louah','Sourcing, ...'),(6,'METHODO','METHODOLOGY ','2023-05-25 16:06:37.983000','2023-05-25 16:30:46.411320','admin-louah','admin-louah',NULL),(7,'ORG','ORGANIZATIONAL ','2023-05-25 16:39:31.437845','2023-05-25 16:39:31.437845','admin-louah','admin-louah',NULL),(8,'QA','QUALITE, CONTROL INTERNE','2023-05-25 16:47:38.019910','2023-05-25 16:47:38.019910','admin-louah','admin-louah',NULL),(9,'TOOLS','TOOLS 4 PRODUCTIVITY ','2023-05-25 16:56:59.934992','2023-05-25 16:56:59.934992','admin-louah','admin-louah',NULL),(10,'POC','PROOF OF CONCEPT','2023-05-25 17:09:19.348199','2023-05-25 17:09:19.348199','admin-louah','admin-louah',NULL),(11,'PRE-SALES','FRP/RFI ','2023-05-31 17:35:28.840902','2023-05-31 17:35:28.840902','admin-louah','admin-louah',NULL),(12,'DATA','DATA','2023-06-21 13:36:57.302180','2023-06-21 13:36:57.302180','admin-louah','admin-louah',NULL),(13,'IT','IT','2023-06-21 13:38:07.469724','2023-06-21 13:38:07.469724','admin-louah','admin-louah','Security, ...');
/*!40000 ALTER TABLE `project_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 18:21:44
