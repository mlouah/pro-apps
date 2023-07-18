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
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) DEFAULT NULL,
  `task_title` varchar(255) NOT NULL,
  `deal_line` date DEFAULT NULL,
  `is_urgent` bit(1) DEFAULT NULL,
  `date_creation` datetime(6),
  `date_modify` datetime(6),
  `last_modify_by` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `notes` longtext,
  `task_status_id` bigint DEFAULT NULL,
  `task_mom_id` bigint DEFAULT NULL,
  `project_name_id` bigint DEFAULT NULL,
  `task_owner_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_task__task_status_id` (`task_status_id`),
  KEY `fk_task__task_mom_id` (`task_mom_id`),
  KEY `fk_task__project_name_id` (`project_name_id`),
  KEY `fk_task__task_owner_id` (`task_owner_id`),
  CONSTRAINT `fk_task__project_name_id` FOREIGN KEY (`project_name_id`) REFERENCES `project` (`id`),
  CONSTRAINT `fk_task__task_mom_id` FOREIGN KEY (`task_mom_id`) REFERENCES `mo_m` (`id`),
  CONSTRAINT `fk_task__task_owner_id` FOREIGN KEY (`task_owner_id`) REFERENCES `person` (`id`),
  CONSTRAINT `fk_task__task_status_id` FOREIGN KEY (`task_status_id`) REFERENCES `task_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-18 18:13:32
