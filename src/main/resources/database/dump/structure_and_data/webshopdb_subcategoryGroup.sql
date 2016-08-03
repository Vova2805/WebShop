-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: localhost    Database: webshopdb
-- ------------------------------------------------------
-- Server version	5.7.13

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
-- Table structure for table `subcategoryGroup`
--

DROP TABLE IF EXISTS `subcategoryGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcategoryGroup` (
  `subcategoryGroupId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Subcategory for subcategory. Funny.\n\nFor example - for subcategory Laptop:\n	*All laptops\n	*Accumulators for laptops\n	*External HDD',
  `subcategoryId` int(11) NOT NULL,
  `subcategoryGroupTitle` text NOT NULL,
  PRIMARY KEY (`subcategoryGroupId`),
  KEY `fk_subcategoryGroup_subcategory1_idx` (`subcategoryId`),
  CONSTRAINT `fk_subcategoryGroup_subcategory1` FOREIGN KEY (`subcategoryId`) REFERENCES `subcategory` (`subcategoryId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoryGroup`
--

LOCK TABLES `subcategoryGroup` WRITE;
/*!40000 ALTER TABLE `subcategoryGroup` DISABLE KEYS */;
INSERT INTO `subcategoryGroup` VALUES (1,1,'Netbooks'),(2,1,'Transformers \\ 2 1'),(3,1,'Lightweight and thin'),(4,1,'Multimedia centers'),(5,1,'Gaming notebook'),(6,1,'For business'),(7,2,'Budgetary'),(9,2,'Tablets on Windows'),(10,5,'Antivirus'),(11,5,'Games'),(12,5,'OS'),(13,11,'Washing machines'),(14,10,'Single Door');
/*!40000 ALTER TABLE `subcategoryGroup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-03  3:24:06
