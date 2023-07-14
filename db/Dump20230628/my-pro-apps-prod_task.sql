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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'ROADMAP','Accrocher les projets ACT au priorités  S2M cf. notes','2023-05-30',_binary '\0','2023-05-25 16:15:44.715000','2023-05-31 11:47:08.222455','admin-louah','admin-louah','Les 3 priorités S2M pour 2023: 1) P1- Développement récurrence   2) Amélioration productivité et rentabilité   3) Modernisation du produit actuel et développement\nOKR est une bonne piste pour faire cet exercice\n\n2023-05-31 -Done  - voir fichier excel sur  gouvernance EVP',1,1,NULL,1),(2,'Select Refonte vs réécrire','Répondre à la question - Peut-on faire évoluer l’architecture  de SS en mode progressif ?',NULL,_binary '\0','2023-05-25 16:19:11.823000','2023-06-14 17:16:03.239151','admin-louah','admin-louah','Permettre du side by side dans un premier temps à l\'instar de APEX et FORMS. </BR> \nVoir email de chami du  12/06/2023 13:52  </BR> \n\n31/05/2023 - Pour la réunion du 01/06/23 - je vais me baser sur les slides d\'architecture.',3,1,5,1),(3,'CONF MANAGEMENT','Call yassine Mrani pour cadrer mission et faire un retour à Abou El Bal','2023-05-26',_binary '','2023-05-25 17:03:02.921000','2023-05-26 15:57:32.912398','admin-louah','admin-louah','Date fin 26/05/25',1,3,15,1),(4,'ACT/DRS','Organiser la collaboration avec DRS/Loubna','2023-05-28',_binary '\0','2023-05-25 17:28:26.759000','2023-06-21 21:34:26.851303','admin-louah','admin-louah','Closed on 21/06/23',1,2,12,1),(5,'ABAQUES APEX','Revoir les abaques APEX et traiter les manquements.',NULL,_binary '','2023-05-26 16:11:41.698726','2023-05-26 16:11:41.698726','admin-louah','admin-louah','Recouper avec la réalité du consommé réel pour valider les abaques.\n\nRevoir ce qui a été fait pour le chiffrage dans le cadre du projet \nObjectif : vérifier la qualité des abaques.\n\n/!\\ \n',1,4,13,1),(6,'BU ACT','Présenter le budget au codir pour décider de la suite  et Expliquer les bénéfices vs KPI','2023-06-16',_binary '','2023-05-26 16:13:20.316000','2023-06-21 13:33:56.995342','admin-louah','admin-louah','Action Traitée lors du one2one de juin avec le président. Sachant que j\'ai communiqué une approche pour les KPI\nClosed on 21/06/2023',1,4,13,1),(7,'BUDGET ','Estimer le RAF suite au Bilan VR fait par MLO',NULL,_binary '','2023-05-26 16:15:40.503000','2023-05-26 16:38:37.850682','admin-louah','admin-louah',NULL,1,4,13,1),(8,'BU-ACT','Mise à jour du support  et le partager avec CODIR et proposer une réunion à RAB',NULL,_binary '','2023-05-26 17:28:10.449000','2023-06-21 15:59:22.055687','admin-louah','admin-louah','<p>31/05/2023 - demander une date &agrave; Rachid Abou El Bal --&gt; Done!<br />16/06/2023 R&eacute;union avec RAVB --&gt; Done !<br />21/06/2023 Document mis &agrave; jour </p>\n\nhttps://www.riskinsight-wavestone.com/2011/10/grands-projets-de-transformation-un-manque-criant-de-kpi/',1,5,13,1),(10,'Objectifs-2023','Fixer les objectifs pour 2023 (Lié au OBEJCTIFS ACT)','2023-05-31',_binary '','2023-05-26 17:35:35.456000','2023-05-31 11:57:10.336424','admin-louah','admin-louah','Voir MoM MAR Objcctifs 2023 !\nCréer la BU et son RUN , productivité, qualité $\nse baser sur les KPI des autres BU pour fixer un taux d\'amélioration \n\n31-05-2023 - les objectifs sont crées et partagés avec président et VP. Je clos cette action. s\'ils a de nouveaux objectifs je les intégrerai.\n',1,6,NULL,1),(11,'BOARD MEMBER','Partager avec Rachid A. le mail de Benani relatif au directoire',NULL,_binary '','2023-05-26 17:45:10.002337','2023-05-26 17:45:10.002337','admin-louah','admin-louah','Done the same day !',1,6,NULL,1),(12,'MAINTEANCE','Partager avec CC et PS un doc qui résume les types de maintenance',NULL,_binary '\0','2023-05-26 18:06:54.133000','2023-05-31 17:38:51.998498','admin-louah','admin-louah','Lors de la réunion, nous n\'avons pas pu se mettre d\'accord sur la maintenance préventif !\n\nhttps://www.ibm.com/fr-fr/topics/what-is-preventive-maintenance#:~:text=La%20maintenance%20pr%C3%A9ventive%20consiste%20%C3%A0,elles%20ne%20tombent%20en%20panne.\n31/05/2023 - Close\n--------------------------\nLa maintenance préventive consiste à effectuer des activités de maintenance régulièrement programmées afin d’éviter des défaillances inattendues à l’avenir. En d’autres termes, il s’agit de réparer les choses avant qu’elles ne tombent en panne.',1,7,NULL,1),(13,'CONCEPTION ','Expliquer ce qui a été fait au niveau d’APEX',NULL,_binary '\0','2023-05-26 18:55:31.353000','2023-05-31 16:58:28.033034','admin-louah','admin-louah','31/05/2023  voilà ce qui a été \n├───Archictecture\n│       Architecture logicielle.docx\n│\n├───Mockup\n│       MOCKUP.rar\n│       mockup.rar.content.txt\n│\n├───UI\n└───UX\n    │   APEX_VS_V6.pptx\n    │\n    └───Parcours\n            parcours-apex.xlsx',1,9,NULL,1),(14,'RECRUTEMENT DSI','Préparer les entretiens DSI pour le 29/05/2023','2023-05-29',_binary '\0','2023-05-29 10:01:38.565000','2023-05-29 15:58:57.421553','admin-louah','admin-louah','1) Creat the meetings requests (OK)\n\n•	Mr Driss Taha Yassine        mardi 30 mai à 10H \n•	Mr Ahmed Essadki              mardi 30 mai à 16H \n•	Mr Mohamed Mouakat     jeudi 01 juin à 10H  (will be changed)\n\n2) Read the CV\'s\n\n3) Preparer somes questions',1,NULL,13,1),(15,'LIVRABLES','Finaliser le dossier livrable','2023-06-02',_binary '','2023-05-31 12:18:01.422000','2023-05-31 17:05:46.491937','admin-louah','admin-louah','31/05/2023 - urgent car départ de Rehhal et fin du projet de développement',1,NULL,2,1),(16,'DSI','CR entretiens DSI','2023-06-01',_binary '','2023-06-01 15:46:10.659000','2023-06-01 16:43:15.414196','admin-louah','admin-louah',NULL,1,NULL,13,1),(17,'Event- Money 2020 ','ORGNIZE THE TRAVEL !','2023-06-04',_binary '','2023-06-03 11:22:50.672000','2023-06-13 12:45:23.017716','admin-louah','admin-louah',NULL,1,NULL,NULL,1),(18,'Sharing knowledge',' Framework UML sera présenté par Marouane. ',NULL,_binary '\0','2023-06-05 14:00:54.597000','2023-06-16 14:40:47.885987','admin-louah','admin-louah',NULL,2,18,NULL,1),(19,'CANTABO vs CLOUDTECH',' Comparer budget  entre Cantabo et cloudtech en terme de volume pour SIF et PS ne pas oublier le problème des licences Oracle pour PS !','2023-06-13',_binary '','2023-06-13 10:50:36.333000','2023-06-28 15:02:03.691610','admin-louah','admin-louah','Ne pas oublier le problème des licences Oracle pour PS (demandeur Abou El Bal) et le support sur la partie sécurité /télécom\nVoir la possibilité de faire travailler Yassine Lamrani!\nEffectivement ça marche bien avec cloudtech\nYassir part de chez Medtech et il faut le souligner\n\n19/06/23 - email sent to chairman mer. 14/06/2023 16:55\n28/06/23 - Envoi de la récap  à Hassan et à Amarti',3,19,11,1),(20,'CONGE','Demande de congé à envoyer au président ETE + 26/06 pour HIBA','2023-06-19',_binary '','2023-06-13 11:55:54.828000','2023-06-22 15:30:37.421004','admin-louah','admin-louah','Congé annuel du 7 au 23 août.',1,NULL,NULL,1),(21,'DOC','Template documentation pour Rachid','2023-06-19',_binary '\0','2023-06-13 16:49:05.093000','2023-06-14 17:10:30.333330','admin-louah','admin-louah','Le projet est en cours de finalisation\nvoir l\'exemple de DGSN et CJU Enterprise architect',2,NULL,3,1),(22,'RH','S2M Situation  difficile PRO#2','2023-06-30',_binary '','2023-06-14 09:08:28.923000','2023-06-14 09:14:34.193922','admin-louah','admin-louah','Surtout document l\'état d\'esprit après la résolution du problème.',3,NULL,NULL,1),(23,'RH','Trouver et stocker situation difficile #1',NULL,_binary '\0','2023-06-14 09:15:57.985086','2023-06-14 09:15:57.985086','admin-louah','admin-louah',NULL,NULL,NULL,NULL,1),(24,'VR','Organiser une réunion Bilan VR','2023-06-22',_binary '','2023-06-16 13:43:28.655000','2023-06-28 14:18:11.290842','admin-louah','admin-louah','<p  style=\"color:red\">\nUn slide pour montrer :\na) ce qui est compris\nb) ce qu\'on voulait faire\nc) La réalité \n\n</p>\n\nOrganiser une réunion Bilan VR avec Mounir et Hassan et Chami à la demande du président qui veut y voir plus clair surtout après plusieurs projets réalisés: BNP Selegal, BNP Cote Ivoir, SCT, NIBL, ...\nPeut-être partager avec le président ma lecture \nTrouver le CR de la réunion avec Machtani et Rahhal \n\n<h6> Historiques  </h6>\n- 21/06/23 -Suite à l\'absence de Mounir, et suite au partage du bilan VR pour phase II  j\'ai relancé pour la lecture du doc afin d\'organiser une réunion <BR>\n- 22/06/23 - Relance Yassine Adnane pour la lecture \n- 27/06/2023 partager le doc avec les autres DM: Jamal + Houssam en plus de Fikhane.\n- 27/06/23 - Réunion prévue pour le 6/7/23\n- 28/06/2023 - Call avec Hassan -> ok pour être présent lors de la réunion\n\nAction: Trouver la photo prise lors de notre réunion avec Rahhal et Machtani ',1,25,6,1),(25,'QA','Travailler sur des KPI par BU ',NULL,_binary '\0','2023-06-16 13:53:13.024890','2023-06-16 13:53:13.024890','admin-louah','admin-louah','Voir notes KPI sur email envoyé au président',2,25,9,1),(26,'QA','Faire le point avec Amarti pour décider pour Kenza',NULL,_binary '\0','2023-06-16 13:54:05.413000','2023-06-22 14:40:45.314975','admin-louah','admin-louah','Check and Balance  <BR>\nSi des ressources externes sont nécessaires on peut les appeler  <BR>\nemail sent on jeu. 22/06/2023 15:39',1,25,9,1),(27,'QA','Faire le point avec Geneviève Denier ','2023-06-27',_binary '\0','2023-06-16 13:59:15.225000','2023-06-28 08:56:56.573780','admin-louah','admin-louah','L\'idée est d\'avoir l\'historique et prendre des propositions/idées et voir comment on pourrait travailler ensemble éventuellement <BR>\n2023-06-22 call <BR>\n2023-06-27 RDV pris pour ce jour <BR>\n23/06/27 Nous avons tenu notre réunion hier avec Madame Deunier. 	  Cette dernière est désormais plus spécialisée dans l’agilité que les processus d’ingénierie de type CMMI. 	Cette piste est donc désormais close.\n	\n\n',1,25,10,1),(28,'Projects internes','Lancer recrutements partie applicative CP Ouadia + DEV freelance + RAQ',NULL,_binary '','2023-06-16 14:01:14.421000','2023-06-23 09:43:02.269421','admin-louah','admin-louah','- DEV:  Jhipster Définir les compétences Jhipster OK\n- CP : CV Ouadie pour CP envoyé OK\n- RAQ: Tentative Kenza pour RAQ non concluante: voir Nawal\n- ARCHI à voir Mahmoud à réserver 10 jours par moi',1,25,9,1),(29,'RM-SEM','Proposition chantier/projet Gouvernance RM S2M',NULL,_binary '','2023-06-16 14:33:19.345000','2023-06-23 09:45:07.979310','admin-louah','admin-louah','Décidé lors du point du 16/6/2023  avec le président !\n\nACT pour animer et rendre compte de l\'avancement des projets \nCréer un nouveau chantier/projet Gouvernance RM S2M\nDéfinir la fréquence\nPour chaque Projet 1) définir le niveau de l\'instance qui assistera au instances de suivi: Entité elle-même, Directoire, Ad\'hoc   2) Monter le leader et les contributeurs.\nNe pas parler de pilotage mais plutôt de secrétariat !',2,25,9,1),(30,'Conf Management','Réunion d\'échange avec le gars de M2M via Abou Al Bal',NULL,_binary '\0','2023-06-16 14:37:07.673000','2023-06-16 14:37:54.616366','admin-louah','admin-louah','Objectif: échanges autour de  configuration management et l\'expérience Git Hub',2,25,10,1),(31,'PREZ','Mettre à jour le support de  présentation et le partager avec le président',NULL,_binary '','2023-06-16 14:39:21.700000','2023-06-21 16:51:08.308104','admin-louah','admin-louah','21/06/2023 -Done. cf. S2M-MLO-BU ACT v5.pdf + email sent to the chaiman',1,25,9,1),(32,'PERIMETRE','Simplifier le périmètre de ACT, le mettre à jour et montrer contributeur/Owner ',NULL,_binary '','2023-06-16 15:06:18.785000','2023-06-21 16:50:12.305647','admin-louah','admin-louah','21/06/23 - Done cf. S2M-MLO-BU ACT v5.pdf',1,25,9,1),(33,'Facturation ','Facturation Yassine Lamrani ','2023-06-22',_binary '','2023-06-22 10:42:46.741000','2023-06-27 12:07:40.179590','admin-louah','admin-louah','Se mettre d\'accord sur le mode de facturation <BR>\n\nLe plus simple est que S2M continu à facturer ) \nPlanning initial\nSemaine 10/07/2023 :  Mardi & Jeudi\nSemaine 17/07/2023 :  Mardi & Jeudi\nSemaine 24/07/2023 :  Mardi & Jeudi\n\n23/06/2023 - email sent to président on 23/06/23',1,NULL,10,1),(34,'SQL','NULL Values and the GROUP BY Clause',NULL,_binary '\0','2023-06-22 10:51:34.028000','2023-06-23 17:57:23.348924','admin-louah','admin-louah','Understand how \'Group By\' can be cause of non diplaying information \n<pre style=\"color:green\">\nSELECT \n    t.project_name_id \'Project ID\',\n    t.id,\n    t.subject,\n    t.task_title,\n    t.deal_line,\n    t.is_urgent,\n    t.notes\nFROM\n    `my-pro-apps-prod`.task t\nWHERE\n    t.task_status_id <> 1\n        AND t.task_status_id <> 4\nGROUP BY t.project_name_id\nORDER BY t.is_urgent\n</pre>\n\n\nNULL Values and the GROUP BY Clause <BR>\n\n<code>   https://learnsql.com/blog/null-values-group-clause/   </code>\n\nSolution: Group by ne doit pas être incluse dans cette requete car rien à regrouper !',1,NULL,NULL,1),(35,'Startup ','Startup the project','2023-06-22',_binary '','2023-06-22 10:56:04.010000','2023-06-23 17:31:29.069450','admin-louah','admin-louah','<p>- Equipe : qui travaille sur le projet <br />\n- Tableau de bord projet pour le suivi<br />\n- Document &agrave; envoyer &agrave; Yassine <br />\n- Note de cadrage</p>\n\nfiche d\'initialisation --> EC',3,NULL,10,1),(36,'PROJECT PORTFOLIO','S2M RoadMap 2023','2023-07-31',_binary '\0','2023-06-23 10:15:51.195000','2023-06-28 15:34:06.209721','admin-louah','admin-louah','Show dependencies between priorities projects (P1) \nDone on the excel !\n',1,NULL,9,1),(37,'ENV','Trouver la gestion des environnements ONDA R12',NULL,_binary '\0','2023-06-23 10:43:36.997000','2023-06-28 15:26:02.254330','admin-louah','admin-louah','C:\\Users\\Marouane LOUAH\\Documents\\DOC-ENTREPRISES\\HP CDG\\AO\\AO-BID\\EBS MIGR12\\2013-09-ONDA EBS Mig r12\\OT  <BR>\n<strong>  ONDA-MIG-R12-Mise à Niveau-OT-HP CDG-Environnements.docx      </strong> <BR>',1,NULL,10,1),(38,'BACK-UP ','Schedule My-pro-apps back each day ','2023-06-25',_binary '','2023-06-23 10:50:09.022000','2023-06-28 15:59:44.566697','admin-louah','admin-louah','Un script existe déjà  :   <BR> <code> C:\\Users\\Marouane LOUAH\\Documents\\DOC-ENTREPRISES\\S2M\\10-TOOLS\\MySQL\\MySQL-Save.cmd     </code>',3,NULL,15,1),(39,'CM','Onboarding Yassine Lamrani','2023-06-27',_binary '','2023-06-27 12:27:53.148000','2023-06-28 11:00:21.116737','admin-louah','admin-louah','Email de cadrage de la mission -> OK\nAlimenter Yassine Lamrani avec des documents à lire -> OK\nCréer un planning de présence. prévoir des ateliers -> OK\nRéunion de présentation de Yassine à l\'équipe   -->  OK\n\nidées :\n- voir les objectifs\n- voir les pains points \n- Les docs:  pre-requis pour démarrer \n',1,26,NULL,1),(40,'TEAM','initialiser la partie equipe',NULL,_binary '\0','2023-06-28 08:59:54.099000','2023-06-28 14:19:43.154868','admin-louah','admin-louah','Deunier  -->  non\nYassine EL Mrani --> OK\nAssas --> ?\nHicham Marouani --> \nMarouane Meliani --> \nLouha Marouane --> \nFikhane --> \nChami --> ',3,NULL,10,1),(41,NULL,'Partager avec président les items clés','2023-06-28',_binary '\0','2023-06-28 10:57:42.975484','2023-06-28 10:57:42.975484','admin-louah','admin-louah',NULL,1,29,13,1);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 18:21:45