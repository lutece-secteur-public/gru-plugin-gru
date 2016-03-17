-- MySQL dump 10.15  Distrib 10.0.21-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: guichet_gru_rec
-- ------------------------------------------------------
-- Server version	10.0.21-MariaDB

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
-- Table structure for table `crm_demand_type`
--

DROP TABLE IF EXISTS `crm_demand_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_demand_type` (
  `id_demand_type` int(11) NOT NULL DEFAULT '0',
  `label` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `url_resource` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `url_info` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `url_contact` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `demand_type_order` int(11) NOT NULL DEFAULT '1',
  `id_category` int(11) NOT NULL DEFAULT '0',
  `date_begin` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `workgroup_key` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `role_key` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `target` smallint(6) NOT NULL DEFAULT '0',
  `url_delete` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `is_include_id_user` smallint(6) NOT NULL DEFAULT '0',
  `is_need_authentication` smallint(6) NOT NULL DEFAULT '0',
  `is_need_validation` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_demand_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_demand_type`
--

LOCK TABLES `crm_demand_type` WRITE;
/*!40000 ALTER TABLE `crm_demand_type` DISABLE KEYS */;
INSERT INTO `crm_demand_type` VALUES (102,'Réclamation','Réclamation','','',2,0,NULL,NULL,'all','none',0,'',1,1,1),(101,'Sollicitation','Sollicitation','','',3,0,NULL,NULL,'all','none',0,'',1,1,1),(103,'Rendez-vous','Rendez-vous','','',1,0,NULL,NULL,'all','none',0,'',1,1,1);
/*!40000 ALTER TABLE `crm_demand_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-17 11:32:17
