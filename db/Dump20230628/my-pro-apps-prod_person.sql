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
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `is_external` bit(1) DEFAULT NULL,
  `date_creation` datetime(6),
  `date_modify` datetime(6),
  `last_modify_by` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `notes` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'LOUAH M','MLO',_binary '\0','2023-05-25 15:35:45.590000','2023-05-25 15:36:03.232644','admin-louah','admin-louah',NULL),(2,'KORAICHI R','RKE',_binary '\0','2023-05-25 15:35:56.282761','2023-05-25 15:35:56.282761','admin-louah','admin-louah',NULL),(3,'H. EL KHAIR','HEK',_binary '\0','2023-05-25 15:50:48.629000','2023-05-25 15:51:29.683884','admin-louah','admin-louah','HANANE EL KHAIR'),(4,'LOUBNA BELHAJ','BELHAJ L.',_binary '\0','2023-05-25 16:48:47.712000','2023-05-25 17:15:20.929695','admin-louah','admin-louah',' <lbelhaj@s2m.ma>'),(5,'MOHAMED CHAMI','CHAMI M.',_binary '\0','2023-05-25 16:50:52.893170','2023-05-25 16:50:52.893170','admin-louah','admin-louah',NULL),(6,'AYA HASSANI','AYA H',_binary '\0','2023-05-25 17:14:37.460917','2023-05-25 17:14:37.460917','admin-louah','admin-louah',NULL),(7,'YOUSSEF BERRADA','BERRADA Y.',_binary '\0','2023-05-26 18:25:52.305834','2023-05-26 18:25:52.305834','admin-louah','admin-louah',NULL),(8,'AFAF. ','AFAF',_binary '\0','2023-06-01 14:05:28.228000','2023-06-16 15:12:56.175130','admin-louah','admin-louah','provioirs  '),(9,'BADR','BADR',_binary '\0','2023-06-16 15:13:09.854732','2023-06-16 15:13:09.854732','admin-louah','admin-louah',NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 18:21:46
