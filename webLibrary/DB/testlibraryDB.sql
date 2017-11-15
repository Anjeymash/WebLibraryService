-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: testlibrarydb
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(150) COLLATE utf8_bin NOT NULL,
  `b_author` varchar(255) COLLATE utf8_bin NOT NULL,
  `b_genre` varchar(150) COLLATE utf8_bin NOT NULL,
  `b_year` varchar(4) COLLATE utf8_bin NOT NULL,
  `b_quantity` smallint(5) NOT NULL,
  `b_status` tinyint(1) NOT NULL DEFAULT '0',
  `b_context` text COLLATE utf8_bin,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Angel','Sidney','children','2018',9,1,'Context'),(3,'Забыть всё','Хоаг_Тэми','Триллер','2012',3,0,NULL),(4,'Золушка','Ульсон_Кристина','Триллер','2013',24,0,NULL),(6,'Железный волк','Бульга_Сергей','scifi','2006',4,0,NULL),(7,'Аэрофил','Архангельский_Александер','advantures','1926',1,0,NULL),(8,'World_of_warcraft_Иллидан','Кинг_Уильям','advantures','2016',46,0,NULL),(9,'Star_Wars_Aftermath','Чак_Чэндинг','scifi','2015',61,0,NULL),(10,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(11,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(12,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(13,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(14,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(15,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(16,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(17,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(18,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(19,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(20,'TestBook','TestBook','TestBook','2017',1,0,'TestContext'),(21,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(22,'TestBook','TestBook','TestBook','2017',1,1,'TestContext'),(23,'TestBook','TestBook','TestBook','2017',1,0,'TestContext'),(24,'TestBook','TestBook','TestBook','2017',1,1,'TestContext');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rent` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `r_start` date NOT NULL,
  `r_end` date NOT NULL,
  `r_status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`r_id`),
  KEY `fk_rent_user1_idx` (`u_id`),
  KEY `fk_rent_book1_idx` (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_login` varchar(150) COLLATE utf8_bin NOT NULL,
  `u_password` varchar(255) COLLATE utf8_bin NOT NULL,
  `u_name` text COLLATE utf8_bin,
  `u_surname` text COLLATE utf8_bin,
  `u_location` text COLLATE utf8_bin,
  `u_tel` bigint(20) DEFAULT NULL,
  `u_status` text COLLATE utf8_bin,
  `u_email` text COLLATE utf8_bin,
  `u_flat` int(11) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Anjey','123','Andrey','Mash','Minsk',777777777,'admin','sss.@ss.ru',123),(4,'Попов_Максим','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Васильев_Дмитрий','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'Петров_Даниил','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'Соколов_Тимофей','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'TestUser','TestUser','TestUser','TestUser','TestUser',111111111,'user','test@user.ru',NULL),(9,'TestUser','TestUser','TestUser','TestUser','TestUser',111111111,'user','test@user.ru',NULL),(10,'TestUser','TestUser','TestUser','TestUser','TestUser',111111111,'user','test@user.ru',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-11 21:14:01
