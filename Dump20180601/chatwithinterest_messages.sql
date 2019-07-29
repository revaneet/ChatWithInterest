-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: chatwithinterest
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `msgfrom` varchar(100) NOT NULL,
  `msgto` varchar(100) NOT NULL,
  `message` varchar(1024) NOT NULL,
  `msgtype` varchar(45) NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`),
  KEY `msgfrom_idx` (`msgfrom`),
  KEY `msgto_idx` (`msgto`),
  CONSTRAINT `msgfrom` FOREIGN KEY (`msgfrom`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `msgto` FOREIGN KEY (`msgto`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'def@gmail.com','abc@gmail.com','hello','text','2018-05-25 07:28:25'),(2,'def@gmail.com','abc@gmail.com','27.png','smiley','2018-05-25 07:28:27'),(3,'abc@gmail.com','def@gmail.com','buzz','text','2018-05-25 07:29:24'),(4,'def@gmail.com','abc@gmail.com','hello','text','2018-05-25 08:30:23'),(5,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:30:29'),(6,'def@gmail.com','abc@gmail.com','hello','text','2018-05-25 08:30:32'),(7,'def@gmail.com','abc@gmail.com','27.png','smiley','2018-05-25 08:30:37'),(8,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:01'),(9,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:02'),(10,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:02'),(11,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:02'),(12,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:02'),(13,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:03'),(14,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:04'),(15,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:04'),(16,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:04'),(17,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:04'),(18,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:05'),(19,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:05'),(20,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:05'),(21,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:05'),(22,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:05'),(23,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:06'),(24,'def@gmail.com','abc@gmail.com','18.png','smiley','2018-05-25 08:31:06'),(25,'abc@gmail.com','def@gmail.com','10.png','smiley','2018-05-25 08:35:33'),(26,'def@gmail.com','abc@gmail.com','hello','text','2018-05-25 10:25:02'),(27,'abc@gmail.com','def@gmail.com','hello','text','2018-05-25 10:25:14'),(28,'def@gmail.com','abc@gmail.com','hello','text','2018-05-25 10:41:13'),(29,'def@gmail.com','abc@gmail.com','hello','text','2018-05-25 10:46:19'),(30,'abc@gmail.com','def@gmail.com','fl.png','smiley','2018-05-25 10:46:27'),(31,'abc@gmail.com','def@gmail.com','hello','text','2018-05-25 10:46:29'),(32,'def@gmail.com','abc@gmail.com','30.png','smiley','2018-05-25 10:46:37'),(33,'abc@gmail.com','def@gmail.com','7.png','smiley','2018-05-25 10:46:53'),(34,'def@gmail.com','abc@gmail.com','13.png','smiley','2018-05-25 10:46:58'),(35,'def@gmail.com','abc@gmail.com','hello','text','2018-05-25 10:47:04'),(36,'def@gmail.com','abc@gmail.com','hello','text','2018-06-01 05:33:28'),(37,'abc@gmail.com','def@gmail.com','hi','text','2018-06-01 05:33:40');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-01 11:29:41
