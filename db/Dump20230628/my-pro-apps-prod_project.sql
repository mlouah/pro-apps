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
  `date_creation` datetime(6),
  `date_modify` datetime(6),
  `last_modify_by` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `notes` longtext,
  `project_priority_id` bigint DEFAULT NULL,
  `project_category_id` bigint DEFAULT NULL,
  `client_code_id` bigint DEFAULT NULL,
  `internal_project_manager_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (2,'SELECT SYSTEM BO MODERNIZING','APEX','2022-07-01',NULL,'Modernize Select system UX/UI and user management','2023-05-31',NULL,NULL,'2023-05-25 15:40:34.134000','2023-05-25 15:46:50.036976','admin-louah','admin-louah','Momentum canada as a service provider',1,2,1,2,1),(3,'SELECT SYSTEM FO MODERNIZING PHASE-1','F2 Phase1',NULL,NULL,'Modernize Select system  architecture and Deploy the quick wins on the clients on 2023\nQuick win 1 - IPC\nQuick win 1 - HSM','2023-06-30',NULL,NULL,'2023-05-25 15:43:19.034000','2023-05-30 08:59:35.079668','user','admin-louah','F2 stands for Fast Front with Mihamed El Assass as tech leader',1,2,1,2,1),(4,'SS APEX VERSION CERTIF.','SPX-CERT','2023-05-28',NULL,'New Select system product version (apex based) certificationµ\nEND DATE DIFFICULT TO BE PLANIFIED',NULL,NULL,NULL,'2023-05-25 15:50:07.049000','2023-06-21 08:16:38.875800','admin-louah','admin-louah',NULL,1,3,1,3,1),(5,'Etude pour la refonte de SPX','SS-NEW GEN',NULL,NULL,'Select system product redesign study project\nOracle DB Lockout \nKeep APEX Low Cost powerfull TTM and UX with open source tools\nLower TCO for customers\nNew stack ',NULL,NULL,NULL,'2023-05-25 15:57:57.466000','2023-06-21 16:22:02.820321','admin-louah','admin-louah',NULL,2,1,1,1,1),(6,'VR Phase II ','VR-P-II ',NULL,NULL,'1) Deploy the new Process CM \n2) Enhance Extensibilty ',NULL,NULL,410,'2023-05-25 16:09:42.574000','2023-05-25 16:43:41.961340','admin-louah','admin-louah','mINUS CM cost !!!',1,2,1,1,1),(7,'PM DEFINE STRATEGY METHODO','PM-METHOD-STRAT',NULL,NULL,'Product Management- Méthodo product Strategy',NULL,NULL,NULL,'2023-05-25 16:30:22.285870','2023-05-25 16:30:22.285870','admin-louah','admin-louah',NULL,2,6,1,1,1),(8,'S2M DATA PROJECT','DATA',NULL,NULL,'DATA client -> traité au niveau de SPX (Mini BI APEX)',NULL,NULL,NULL,'2023-05-25 16:35:15.189000','2023-06-21 13:36:25.592713','admin-louah','admin-louah',NULL,2,6,1,1,1),(9,'BU ACT - STARTUP','ACT',NULL,NULL,'Start up as an official Business Unit ',NULL,NULL,NULL,'2023-05-25 16:38:42.627000','2023-05-25 16:41:09.733409','admin-louah','admin-louah',NULL,2,7,1,1,1),(10,'CONF MANAGEMENT DEPLOYEMENT','Conf. Mgt',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-25 16:43:14.050000','2023-06-22 10:56:33.089118','admin-louah','admin-louah',NULL,1,6,1,1,1),(11,'SERVERS OUTSOURCING','EXT-INFRA',NULL,NULL,'Externalisation des serveurs infra\nJe suis venu, j\'ai trouvé ce projet déjà en cours sans étude. En tout cas, je n\'ai pas trouvé d\'études !',NULL,NULL,NULL,'2023-05-25 16:45:20.761000','2023-06-21 08:11:07.011507','admin-louah','admin-louah',NULL,1,7,1,1,1),(12,'MANUEL DE PROCEDURE(FINANCE)','PROCEDURE',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-25 16:46:35.198000','2023-05-25 16:49:12.082108','admin-louah','admin-louah',NULL,2,8,1,4,1),(13,'SEPARATION INTEGRATION ET EDITION','REORG',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-25 16:50:29.529000','2023-05-29 09:55:21.687100','admin-louah','admin-louah','Le projet devait se terminer le : xx/XX/xx ',1,7,1,5,1),(14,'PRODUCTS LICENCE MANAGEMENT','KEY-MGT',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-25 16:53:06.714000','2023-05-25 17:14:58.889132','admin-louah','admin-louah',NULL,2,2,1,6,1),(15,'PMO TOOLS FOR ACT','ACT-PMO',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-25 16:58:25.845000','2023-05-25 17:00:31.214992','admin-louah','admin-louah',NULL,3,9,1,1,1),(16,'XXXXX','XXXX',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-25 17:01:15.656000','2023-06-23 09:44:21.285742','admin-louah','admin-louah','',1,6,1,1,2),(17,'SELECT SYSTEM ON THE CLOUD','SS-OCI',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-25 17:08:47.715000','2023-06-16 15:13:24.482016','admin-louah','admin-louah','SS v6 is already on AWS (Hum Group)',3,10,1,9,1),(18,'SALES OFFRE GENERATOR','OFFRE-GEN',NULL,NULL,'Génération d\'offre automatique via une application en VBA','2023-08-31',NULL,NULL,'2023-05-26 18:25:24.666000','2023-06-16 15:14:51.528179','admin-louah','admin-louah',NULL,3,9,1,7,1),(19,'AO CDG CAPITAL INFOGERANCE','CDG-K INFO','2013-09-01','2013-11-30','Réponse à l\'AO CDG Capital n° APPEL D’OFFRES INTERNATIONAL\nN° REFERENCE O382/DOSI/0813\nSEPTEMBRE 2013',NULL,NULL,NULL,'2023-05-31 17:15:40.914000','2023-05-31 17:35:53.265992','admin-louah','admin-louah',NULL,NULL,11,NULL,1,2),(20,'XXXXXXX','XXXX',NULL,NULL,'Project Description',NULL,NULL,NULL,'2023-06-01 14:04:38.426000','2023-06-02 11:16:40.750675','admin-louah','admin-louah',NULL,1,5,1,NULL,NULL),(21,'RSE','RSE',NULL,NULL,NULL,NULL,NULL,NULL,'2023-06-21 08:13:39.380189','2023-06-21 08:13:39.380189','admin-louah','admin-louah',NULL,NULL,NULL,NULL,NULL,1),(22,'SOC','SOC',NULL,NULL,NULL,NULL,NULL,NULL,'2023-06-21 13:37:45.377299','2023-06-21 13:37:45.377299','admin-louah','admin-louah',NULL,NULL,NULL,1,9,1),(23,'APPEL D’OFFRE RESTREINT POUR LA MISE EN PLACE D’UNE SOLUTION CIH@DOMICILE','CIH@DOMICILE',NULL,NULL,'Java JEE\nHP Bélgique a été impliquée',NULL,NULL,NULL,'2023-06-28 15:14:39.270000','2023-06-28 15:17:24.525728','admin-louah','admin-louah',NULL,NULL,11,3,1,2),(24,'MIGRATION DE LA SOLUTION ORACLE EBS DE LA VERSION 11 VERS LA VERSION R12 ET MISE A NIVEAU DES MODULES','EBS Migration vers R12',NULL,NULL,'MIGRATION DE LA SOLUTION ORACLE EBS DE LA VERSION 11 VERS LA VERSION R12 ET MISE A NIVEAU DES MODULES',NULL,NULL,NULL,'2023-06-28 15:19:26.478458','2023-06-28 15:19:26.478458','admin-louah','admin-louah',NULL,NULL,11,4,1,2),(25,'Simpl-IS','EDI Simpl-IS',NULL,NULL,'l’application EDI Simpl-IS pour la déclaration de l’impôt sur les sociétés (IS) à envoyer à la Direction Générale des Impôts (DGI) à travers le canal Echange de données informatisé (EDI) offert par l’application Simpl-IS.\nL’application Simpl-IS a pour finalité de générer les fichiers XML représentant les données des déclarations de l’IS.\n\nLes données concernées par la déclaration de l’IS sont citées comme suit :  <BR>\n\n•	La liasse Fiscale Modèle Etablissements financiers\n•	La déclaration des rémunérations versées à des tiers\n•	La déclaration des produits de placements à revenu fixe \n•	La déclaration des rémunérations versées à des personnes non résidentes \n•	La déclaration des produits des actions, parts sociales et revenus assimilés ',NULL,NULL,NULL,'2023-06-28 15:35:03.175000','2023-06-28 15:37:04.221618','admin-louah','admin-louah',NULL,NULL,2,NULL,NULL,2),(26,'  Mise à jour de la solution Front to Back CDG Capital ','K+ /MCC /KTP Upgrade','2012-07-28','2012-12-03','Migration des logiciels Kondor+  de la version 2.6 à la version 3.0 L5 et KTP de 5.0 à 6.1 .  ',NULL,NULL,NULL,'2023-06-28 15:41:47.049000','2023-06-28 15:44:46.515582','admin-louah','admin-louah','Samir Gourroum',NULL,NULL,5,NULL,2),(27,'Projet de mise à niveau de la solution Ms EPM 2007 vers EPM 2010','Project  Server 2010',NULL,NULL,'L’ONCF souhaite à travers ce projet migrer l’ancienne ferme Microsoft EPM 2007 vers la version EPM 2010, aussi d’intégrer les nouvelles fonctionnalités de Project Server 2010 et principalement la gestion Portfolio.  <BR>\n\n<strong>Jalal BLALI - IPM Consulting </strong>',NULL,NULL,NULL,'2023-06-28 15:51:18.476000','2023-06-28 15:56:34.220268','admin-louah','admin-louah',NULL,NULL,NULL,6,NULL,2);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 18:21:47
