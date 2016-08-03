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
-- Table structure for table `propertyProduct`
--

DROP TABLE IF EXISTS `propertyProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propertyProduct` (
  `property_propertyId` int(11) NOT NULL,
  `product_productId` int(11) NOT NULL,
  PRIMARY KEY (`property_propertyId`,`product_productId`),
  KEY `fk_property_has_product_product3_idx` (`product_productId`),
  KEY `fk_property_has_product_property3_idx` (`property_propertyId`),
  CONSTRAINT `fk_property_has_concreteProduct_concreteProduct3` FOREIGN KEY (`product_productId`) REFERENCES `product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_property_has_concreteProduct_property3` FOREIGN KEY (`property_propertyId`) REFERENCES `property` (`propertyId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propertyProduct`
--

LOCK TABLES `propertyProduct` WRITE;
/*!40000 ALTER TABLE `propertyProduct` DISABLE KEYS */;
INSERT INTO `propertyProduct` VALUES (2,2),(3,2),(9,4),(2,8),(3,8),(5,8),(9,8),(11,8),(2,9),(3,9),(4,9),(9,9),(3,10),(9,10),(11,10),(12,10),(13,11),(14,11),(15,11),(16,11),(18,11),(14,12),(15,12),(16,12),(17,12),(19,12),(20,12),(21,13),(22,13),(23,13),(24,13),(25,13),(26,13),(13,14),(14,14),(15,14),(16,14),(18,14),(27,14),(28,14),(13,15),(14,15),(16,15),(24,15),(26,15),(27,15),(28,15),(29,15),(14,16),(16,16),(24,16),(26,16),(27,16),(28,16),(30,16),(23,17),(31,17),(32,17),(33,17),(35,17),(36,17),(16,18),(34,18),(37,18),(38,18),(40,18),(41,18),(14,19),(19,19),(23,19),(32,19),(35,19),(39,19),(14,20),(28,20),(33,20),(38,20),(42,20),(43,20),(23,21),(28,21),(32,21),(34,21),(43,21),(44,21),(13,22),(17,22),(28,22),(33,22),(44,22),(45,22),(46,22),(16,23),(32,23),(34,23),(38,23),(39,23),(40,23),(35,24),(40,24),(41,24),(45,24),(47,24),(48,24),(49,24),(50,24),(51,24),(13,26),(16,26),(28,26),(33,26),(35,26),(52,26),(22,27),(23,27),(28,27),(31,27),(32,27),(34,27),(13,28),(16,28),(27,28),(39,28),(40,28),(41,28);
/*!40000 ALTER TABLE `propertyProduct` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-03  3:24:05
