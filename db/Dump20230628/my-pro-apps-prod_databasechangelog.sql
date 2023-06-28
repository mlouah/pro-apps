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
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2023-05-22 20:04:07',1,'EXECUTED','8:dec494a89ae55e5f511eacfb3e88bae4','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182310-1','jhipster','config/liquibase/changelog/20230522182310_added_entity_MoM.xml','2023-05-22 20:04:07',2,'EXECUTED','8:c02411c4860ab07869168b807a0579c1','createTable tableName=mo_m; dropDefaultValue columnName=date_creation, tableName=mo_m; dropDefaultValue columnName=date_modify, tableName=mo_m','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182311-1','jhipster','config/liquibase/changelog/20230522182311_added_entity_Task.xml','2023-05-22 20:04:07',3,'EXECUTED','8:0bec7e964d8ccb7e97e5d45dd088df51','createTable tableName=task; dropDefaultValue columnName=date_creation, tableName=task; dropDefaultValue columnName=date_modify, tableName=task','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182312-1','jhipster','config/liquibase/changelog/20230522182312_added_entity_Person.xml','2023-05-22 20:04:07',4,'EXECUTED','8:39d5d7b0770440c492da78f111a0402f','createTable tableName=person; dropDefaultValue columnName=date_creation, tableName=person; dropDefaultValue columnName=date_modify, tableName=person','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182313-1','jhipster','config/liquibase/changelog/20230522182313_added_entity_Client.xml','2023-05-22 20:04:07',5,'EXECUTED','8:630571318e5b700f453dff3064ed1e50','createTable tableName=client; dropDefaultValue columnName=date_creation, tableName=client; dropDefaultValue columnName=date_modify, tableName=client','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182314-1','jhipster','config/liquibase/changelog/20230522182314_added_entity_Project.xml','2023-05-22 20:04:07',6,'EXECUTED','8:bf688df2c61301a634c1959ba18227c3','createTable tableName=project; dropDefaultValue columnName=date_creation, tableName=project; dropDefaultValue columnName=date_modify, tableName=project','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182315-1','jhipster','config/liquibase/changelog/20230522182315_added_entity_TaskStatus.xml','2023-05-22 20:04:07',7,'EXECUTED','8:ba8533c3563456b2d77fcaa87f77fdf8','createTable tableName=task_status; dropDefaultValue columnName=date_creation, tableName=task_status; dropDefaultValue columnName=date_modify, tableName=task_status','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182316-1','jhipster','config/liquibase/changelog/20230522182316_added_entity_Company.xml','2023-05-22 20:04:07',8,'EXECUTED','8:2171006416e6c5eb9c18f09cfb977b82','createTable tableName=company; dropDefaultValue columnName=date_creation, tableName=company; dropDefaultValue columnName=date_modify, tableName=company','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182317-1','jhipster','config/liquibase/changelog/20230522182317_added_entity_ProjectStatus.xml','2023-05-22 20:04:07',9,'EXECUTED','8:cde3764b304b92ebe0ee0dc3004c9a94','createTable tableName=project_status; dropDefaultValue columnName=date_creation, tableName=project_status; dropDefaultValue columnName=date_modify, tableName=project_status','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182318-1','jhipster','config/liquibase/changelog/20230522182318_added_entity_ProjectStatusCode.xml','2023-05-22 20:04:07',10,'EXECUTED','8:2bb8e13aeb2a7e236d970f9971372fce','createTable tableName=project_status_code; dropDefaultValue columnName=date_creation, tableName=project_status_code; dropDefaultValue columnName=date_modify, tableName=project_status_code','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182319-1','jhipster','config/liquibase/changelog/20230522182319_added_entity_ProjectPriority.xml','2023-05-22 20:04:07',11,'EXECUTED','8:bad9b9067e45b3b11b3c3cb5113ce959','createTable tableName=project_priority; dropDefaultValue columnName=date_creation, tableName=project_priority; dropDefaultValue columnName=date_modify, tableName=project_priority','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182320-1','jhipster','config/liquibase/changelog/20230522182320_added_entity_ProjectCategory.xml','2023-05-22 20:04:07',12,'EXECUTED','8:fce67c677757c6b369f4687e81fa95ab','createTable tableName=project_category; dropDefaultValue columnName=date_creation, tableName=project_category; dropDefaultValue columnName=date_modify, tableName=project_category','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182310-2','jhipster','config/liquibase/changelog/20230522182310_added_entity_constraints_MoM.xml','2023-05-22 20:04:08',13,'EXECUTED','8:f98cd0b91b8093b86fdc5065e0e9c50a','addForeignKeyConstraint baseTableName=mo_m, constraintName=fk_mo_m__projet_id, referencedTableName=project','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182311-2','jhipster','config/liquibase/changelog/20230522182311_added_entity_constraints_Task.xml','2023-05-22 20:04:08',14,'EXECUTED','8:89805aef9d1d0150740a84037f746a78','addForeignKeyConstraint baseTableName=task, constraintName=fk_task__task_status_id, referencedTableName=task_status; addForeignKeyConstraint baseTableName=task, constraintName=fk_task__task_mom_id, referencedTableName=mo_m; addForeignKeyConstraint...','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182314-2','jhipster','config/liquibase/changelog/20230522182314_added_entity_constraints_Project.xml','2023-05-22 20:04:08',15,'EXECUTED','8:7462e450cec63e0db21e21ce8fcfcca2','addForeignKeyConstraint baseTableName=project, constraintName=fk_project__project_priority_id, referencedTableName=project_priority; addForeignKeyConstraint baseTableName=project, constraintName=fk_project__project_category_id, referencedTableName...','',NULL,'4.15.0',NULL,NULL,'4782246247'),('20230522182317-2','jhipster','config/liquibase/changelog/20230522182317_added_entity_constraints_ProjectStatus.xml','2023-05-22 20:04:09',16,'EXECUTED','8:99be7bb0f75365fe0415d1639e6b19bb','addForeignKeyConstraint baseTableName=project_status, constraintName=fk_project_status__project_name_id, referencedTableName=project; addForeignKeyConstraint baseTableName=project_status, constraintName=fk_project_status__project_status_code_id, r...','',NULL,'4.15.0',NULL,NULL,'4782246247');
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

-- Dump completed on 2023-06-28 18:21:42
