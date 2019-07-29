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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `securityques` varchar(500) NOT NULL,
  `securityans` varchar(100) NOT NULL,
  `contact` varchar(45) NOT NULL,
  `interests` varchar(500) NOT NULL,
  `profile` varchar(500) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('abc@gmail.com','abc','12345','your favourite color','red','9346543975','movies~~science~~art~~literature~~','F:\\profile pics\\tsandhu@gmailcom_IMG20171028204327.jpg'),('abhichawla@gmail.com','abhinav','yousuck','your favourite phone','motorola','9385682946','movies~~tv series~~tattoos~~automobiles~~','F:\\profile pics\\abhichawla@gmail.com_IMG20171029153935.jpg'),('ayusirohi91997@gmail.com','ayush','realmadrid','your favourite color','balck','9593687345','movies~~sports~~tv series~~tattoos~~automobiles~~art~~','F:\\profile pics\\ayusirohi91997@gmail.com_c950f6be-0528-4e0e-b111-4725b5d6bca8_20170311_120933.jpg'),('bangabro@gmail.com','karishma','library','your favourite holiday destination','los vegas','7563597425','movies~~tv series~~tattoos~~makeup~~art~~literature~~','F:\\profile pics\\bangabro@gmail.com_IMG20171029130717.jpg'),('ddhawan100@gmail.com','disha','minion','your favourite car','bmw','8663477923','movies~~tv series~~makeup~~art~~commerce~~','F:\\profile pics\\ddhawan100@gmail.com_IMG-20171017-WA0017.jpg'),('def@gmail.com','def','12345','your favourite color','red','9346543975','movies~~science~~art~~literature~~','F:\\profile pics\\tsandhu@gmailcom_IMG20171028204327.jpg'),('mohitsh30898@gamil.com','mohit','nitrogen','your favourite color','red','8675640012','sports~~tv series~~movies~~health~~science~~history~~literature~~','F:\\profile pics\\mohitsh30898@gamil.com_IMG20171029134423.jpg'),('psaini456@gmail.com','puneet','sodium','your favourite car','audi','7235699420','movies~~tv series~~science~~history~~tattoos~~literature~~automobiles~~art~~','F:\\profile pics\\psaini456@gmail.com_IMG20171029141030.jpg'),('rachitkamal@gmail.com','rachit','gymnfood','your favourite color','pink','8535569487','makeup~~health~~art~~movies~~','F:\\profile pics\\rachitkamal@gmail.com_IMG_20171030_115004.jpg'),('ravneetkaur91997@gmail.com','ravneet','adv88100','your favourite color','black','8288970996','movies~~tv series~~makeup~~art~~literature~~tattoos~~','F:\\profile pics\\ravneetkaur91997@gmail.com_IMG20171029130055.jpg'),('rhythmsparks@gmail.com','misha','sumotata','your favourite phone','iphone','8674627412','movies~~tv series~~tattoos~~makeup~~automobiles~~','F:\\profile pics\\rhythmsparks@gmail.com_IMG_20171029_142254.jpg'),('ridhi123@gmail.com','ridhima','abc123','your favourite holiday destination','paris','9673896031','movies~~tv series~~makeup~~health~~art~~tattoos~~','F:\\profile pics\\ridhi123@gmail.com_IMG20171029134854.jpg'),('tsandhu@gmailcom','tanveer','12345','your favourite color','red','9346543975','movies~~science~~art~~literature~~','F:\\profile pics\\tsandhu@gmailcom_IMG20171028204327.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
