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
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `start_date` date DEFAULT NULL,
  `real_endt_date` date DEFAULT NULL,
  `project_description` longtext,
  `initial_end_date` date DEFAULT NULL,
  `initial_cost` double DEFAULT NULL,
  `initial_work_load` int DEFAULT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `date_modify` datetime(6) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `notes` longtext,
  `project_priority_id` bigint DEFAULT NULL,
  `project_category_id` bigint DEFAULT NULL,
  `client_code_id` bigint DEFAULT NULL,
  `internal_project_manager_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `objectives` longtext,
  `progress` float DEFAULT NULL,
  `todo` varchar(1000) DEFAULT NULL,
  `project_status_id` bigint DEFAULT NULL,
  `project_status_code_id` bigint DEFAULT NULL,
  `alertes` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_project__project_priority_id` (`project_priority_id`),
  KEY `fk_project__project_category_id` (`project_category_id`),
  KEY `fk_project__client_code_id` (`client_code_id`),
  KEY `fk_project__internal_project_manager_id` (`internal_project_manager_id`),
  KEY `fk_project__company_id` (`company_id`),
  CONSTRAINT `fk_project__client_code_id` FOREIGN KEY (`client_code_id`) REFERENCES `client` (`id`),
  CONSTRAINT `fk_project__company_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `fk_project__internal_project_manager_id` FOREIGN KEY (`internal_project_manager_id`) REFERENCES `person` (`id`),
  CONSTRAINT `fk_project__project_category_id` FOREIGN KEY (`project_category_id`) REFERENCES `project_category` (`id`),
  CONSTRAINT `fk_project__project_priority_id` FOREIGN KEY (`project_priority_id`) REFERENCES `project_priority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-20  1:20:15
