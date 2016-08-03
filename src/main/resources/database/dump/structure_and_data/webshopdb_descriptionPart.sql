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
-- Table structure for table `descriptionPart`
--

DROP TABLE IF EXISTS `descriptionPart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `descriptionPart` (
  `descriptionPartId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Describes product by parts with headers and images (if needed)',
  `descriptionPartHeader` text NOT NULL,
  `descriptionPartBody` text NOT NULL COMMENT 'This table can describe product by parts with heads and illustrations',
  `hasImg` bit(1) NOT NULL,
  `product_productId` int(11) NOT NULL,
  PRIMARY KEY (`descriptionPartId`),
  KEY `fk_descriptionPart_product2_idx` (`product_productId`),
  CONSTRAINT `fk_descriptionPart_product2` FOREIGN KEY (`product_productId`) REFERENCES `product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descriptionPart`
--

LOCK TABLES `descriptionPart` WRITE;
/*!40000 ALTER TABLE `descriptionPart` DISABLE KEYS */;
INSERT INTO `descriptionPart` VALUES (1,' Climatic class - N, SN, ST','Stable operation of the refrigerator at ambient temperature between +10 ° C to +38 ° C.','\0',7),(2,'FRESH system','It is important to bear in mind that different products are stored at different temperatures. The system generates FRESH different climatic zones with temperature from - 18 ° C to + 10 ° C, due to natural circulation of air and moisture. It does not dry products than favorably with No Frost.','\0',7),(3,' Automatic defrost','The accumulated frost thaws periodically in a special container, where evaporates back into the cooling chamber, keeping it necessary humidity.','\0',7),(4,'Reversible door','The standard version is installed on the refrigerator door, which opens from left to right. At the same time for more convenient opening of the door can be perenavesit and thereby change the direction of its opening.','\0',9),(5,'System CoolPlus ','Thanks to CoolPlus temperature in the freezer compartment can be reduced within a few hours, which will provide better freezing of the products. Moreover, this feature allows you to maintain the desired temperature in the freezer compartment at ambient temperatures between +10 ° C to +16 ° C (winter mode).','\0',9),(6,'A place for everything','Everyone has their own requirements for the fridge - so storage flexibility is key. Universal EasyStore box is the perfect solution specifically designed for the protection and quick access to the herbs, berries, nuts, delicatessen, small tubes and bottles and other small items, without which it is difficult to do so.','\0',10),(7,'Handy fridge for those who rarely enjoys freezer','Not everyone needs a huge freezer. Nevertheless, the ability to freeze fresh foods, many find convenient. This refrigerator \"all-in-one\" with built-in four-star freezer gives you that opportunity. You can store frozen food up to 12 months!','\0',10),(8,'','You are looking for a laptop that will be your indispensable assistant? Think you\'ve found it. Meet - HP Stream. He will not fail you at the right moment and hit the pocket. It is light, beautiful and portable laptop that has a low cost and powerful features.','\0',11),(9,'Discover the possibilities of Windows 10','Be confident in their ability to - use an improved version of the familiar of Windows.','\0',11),(10,'Flash memory capacity of 32 GB','Flash memory eMMC 32GB gives you quick access to the files. Recently opened files are stored in flash memory, so you can quickly return to them if necessary.','\0',11),(11,'Amazing quality graphics','Intel adapter provides superior quality graphics for games, leisure and other purposes.','\0',11),(12,'High-resolution display','Take a look at the world of digital innovation in a new way. Rate the stunning image quality, viewing movies and photos on a high definition display.','\0',12),(13,'Continuous operation thanks to increased capacity battery','Thanks to the battery, calculated at 9.5 hours of continuous operation, you can work and play without having to worry about the battery level.','\0',12),(14,'Incredible laptop \"2-in-1\" with great autonomy','Agree that carry a tablet and a laptop is often inconvenient. Another thing Asus Transformer Book T100HA - ultraportable laptop with Windows, which, thanks to a detachable keyboard can be used as a Tablet PC Windows-!). This device \"2-in-1\" combines a laptop and a tablet. Really simple and elegant solution. We need to work with? At your disposal a powerful laptop. Need a break? Disconnect the keyboard and get a convenient tablet. Quickly and without fuss! Hours Asus Transformer Book without charge up to 12 hours!','\0',13),(15,'Attention to detail','ASUS EeeBook E202 has a simple, but at the same time elegant design. Despite its compact size, the notebook boasts a rich functionality and support for modern interfaces.','\0',14),(16,'Large speaker drivers','ASUS EeeBook E202 is equipped with two speakers with increased resonance chamber located under the work surface and aimed directly at the user. These high-quality speakers deliver incredible for such a compact mobile device sound quality.','\0',15),(17,'Backlit Keyboard','Ergonomic keyboard Zenbook UX303 is equipped with self-adjusting backlight brightness, so that the laptop can comfortably work in any lighting conditions and even in complete darkness.','\0',20),(18,'Outstanding display - this is only the beginning.','When the screen fits over 5 million - the result is simply amazing. The pixel density is so high that the eye is no longer able to distinguish between some of them. Images come to a new level of realism. With impressive screen resolution (2880x1800 pixels), you see even more detail in each picture - with pixel precision. The text displayed is so clear that the e-mails, Web pages and documents look like on the printed page.\n\nThe new Retina display reduces glare while providing excellent color and image quality. A high contrast makes shades of white and black deeper. And all the other colors are also distinguished by the richness and brightness. Due technology IPS screen viewing angle is 178  - you will feel the difference from almost any angle. And make no mistake - what you see, you\'ll like.','',24),(19,'The trackpad Force Touch. You press deeper - get more.','Multi-Touch technology on OS X allows you to work with your computer using simple movements, such as swipe or mixing and dilution of the fingers. You can switch between applications, search for the desired content and the use of screen space to the maximum.','',24),(20,'Advanced mobile processor Intel. Serious power.','You can perform the most difficult tasks in professional applications such as Final Cut Pro, thanks to the 4-core processor 4th Intel Core i7 generation with a total cache memory of the third 6MB and Turbo Boost technology which increases the clock speed, the processor ready virtually any task.','',24),(21,'High-performance graphics. Perfect show.','At the 15-inch MacBook Pro display is unique - and just as unique graphics capabilities. Intel Core i7 Processor fourth generation GPU is complemented Iris Pro Graphics with 128 MB of internal memory - it speeds up the processor and graphics performance of resource-intensive tasks, working as a super-fast cache. Scroll larger photo. Play games with stunning detail. You can connect to your notebook, one or two external monitors','',24);
/*!40000 ALTER TABLE `descriptionPart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-03  3:24:07
