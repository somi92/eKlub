CREATE DATABASE  IF NOT EXISTS `eKlub` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `eKlub`;
-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: eKlub
-- ------------------------------------------------------
-- Server version	5.5.52-0ubuntu0.14.04.1

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
-- Table structure for table `Attendance`
--

DROP TABLE IF EXISTS `Attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Attendance` (
  `idAttendance` int(11) NOT NULL AUTO_INCREMENT,
  `idTraining` int(11) NOT NULL,
  `idMember` int(11) NOT NULL,
  `isAttendant` tinyint(1) DEFAULT NULL,
  `lateMin` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAttendance`),
  KEY `fk_Attendance_1_idx` (`idMember`),
  KEY `fk_Attendance_1_idx1` (`idTraining`),
  CONSTRAINT `fk_Attendance_1` FOREIGN KEY (`idTraining`) REFERENCES `Training` (`idTraining`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Attendance_2` FOREIGN KEY (`idMember`) REFERENCES `Member` (`idMember`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attendance`
--

LOCK TABLES `Attendance` WRITE;
/*!40000 ALTER TABLE `Attendance` DISABLE KEYS */;
INSERT INTO `Attendance` VALUES (1,1,26,1,0),(2,1,27,1,0),(3,1,28,1,0),(4,1,29,1,0),(5,1,30,1,0),(6,1,39,1,0),(7,1,40,1,0),(8,2,26,1,0),(9,2,27,1,0),(10,2,28,1,0),(11,2,29,1,0),(12,2,30,1,0),(13,2,39,1,0),(14,2,40,1,0),(15,3,31,1,0),(16,3,32,1,0),(17,3,33,1,0),(18,3,34,1,0),(19,3,35,1,0),(20,4,6,0,0),(21,4,7,1,0),(22,4,8,1,0),(23,4,9,1,10),(24,4,10,1,5),(25,4,38,1,0),(26,5,31,1,0),(27,5,32,1,0),(28,5,33,1,0),(29,5,34,0,0),(30,5,35,0,0),(31,6,21,1,0),(32,6,22,1,0),(33,6,23,1,0),(34,6,24,1,0),(35,6,25,1,0),(36,6,36,1,5),(37,6,37,0,0),(38,7,16,1,0),(39,7,17,1,0),(40,7,18,1,0),(41,7,19,1,0),(42,7,20,0,0),(43,8,16,1,0),(44,8,17,1,0),(45,8,18,1,0),(46,8,19,1,0),(47,8,20,1,0),(48,9,1,1,5),(49,9,2,1,0),(50,9,3,1,0),(51,9,4,1,0),(52,9,5,1,0),(53,10,6,1,0),(54,10,7,1,0),(55,10,8,1,0),(56,10,9,1,0),(57,10,10,0,0),(58,10,38,0,0),(59,12,26,1,23),(60,13,26,1,23),(61,14,26,1,23),(98,20,6,1,5),(99,20,7,0,0),(100,20,8,0,0),(101,20,9,1,0),(102,20,10,1,0),(103,20,38,1,0),(104,21,6,1,0),(105,21,7,0,0),(106,21,8,1,0),(107,21,9,1,5),(108,21,10,1,0),(109,21,38,1,0),(110,22,21,1,5),(111,22,22,1,0),(112,22,23,0,0),(113,22,24,1,0),(114,22,25,1,0),(115,22,36,1,0),(116,22,37,1,0),(117,23,21,1,5),(118,23,22,1,0),(119,23,23,0,0),(120,23,24,1,0),(121,23,25,1,0),(122,23,36,1,0),(123,23,37,1,0),(124,24,6,1,0),(125,24,7,1,0),(126,24,8,1,0),(127,24,9,0,0),(128,24,10,0,0),(129,24,38,1,5),(131,25,6,1,0),(132,25,7,1,5),(133,25,8,1,0),(134,25,9,1,0),(135,25,10,1,0),(136,25,38,0,0),(138,26,6,1,0),(139,26,7,1,10),(140,26,8,1,0),(141,26,9,1,0),(142,26,10,1,0),(143,26,38,0,0),(144,26,45,1,5);
/*!40000 ALTER TABLE `Attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Category` (
  `idCategory` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES (1,'Fitnes','test1'),(2,'Slobodni rekreativci','test2'),(3,'Krosfit',NULL),(4,'Kardio',NULL);
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClientDetails`
--

DROP TABLE IF EXISTS `ClientDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClientDetails` (
  `appId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `resourceIds` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `appSecret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grantTypes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `redirectUrl` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authorities` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `autoApproveScopes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientDetails`
--

LOCK TABLES `ClientDetails` WRITE;
/*!40000 ALTER TABLE `ClientDetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClientDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `idEmployee` int(11) NOT NULL AUTO_INCREMENT,
  `nameSurname` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idEmployee`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'Milo≈° Stojanoviƒá','milos92s@yahoo.com','064/912-18-31','Svetozara ƒÜoroviƒáa 11','milos','somi92',NULL,1),(2,'Marko Filipov','markofilipov@xmail.com','065/107-00-09','Milutina Milankoviƒáa 70','marko','marko',NULL,1);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmployeeAuthority`
--

DROP TABLE IF EXISTS `EmployeeAuthority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmployeeAuthority` (
  `idEmployeeAuthority` int(11) NOT NULL AUTO_INCREMENT,
  `idEmployee` int(11) NOT NULL,
  `idAuthority` int(11) NOT NULL,
  PRIMARY KEY (`idEmployeeAuthority`),
  KEY `fk_EmployeeAuthority_1` (`idEmployee`),
  KEY `fk_EmployeeAuthority_2` (`idAuthority`),
  CONSTRAINT `fk_EmployeeAuthority_1` FOREIGN KEY (`idEmployee`) REFERENCES `Employee` (`idEmployee`) ON UPDATE CASCADE,
  CONSTRAINT `fk_EmployeeAuthority_2` FOREIGN KEY (`idAuthority`) REFERENCES `oauth_eklub_authority` (`idAuthority`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeAuthority`
--

LOCK TABLES `EmployeeAuthority` WRITE;
/*!40000 ALTER TABLE `EmployeeAuthority` DISABLE KEYS */;
INSERT INTO `EmployeeAuthority` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `EmployeeAuthority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmployeeEngagement`
--

DROP TABLE IF EXISTS `EmployeeEngagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmployeeEngagement` (
  `idEmployeeEngagement` int(11) NOT NULL AUTO_INCREMENT,
  `idGroup` int(11) NOT NULL,
  `idEmployee` int(11) NOT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idEmployeeEngagement`),
  KEY `fk_EmployeeEngagement_2_idx` (`idEmployee`),
  KEY `fk_EmployeeEngagement_1_idx` (`idGroup`),
  CONSTRAINT `fk_EmployeeEngagement_1` FOREIGN KEY (`idGroup`) REFERENCES `Group` (`idGroup`) ON UPDATE CASCADE,
  CONSTRAINT `fk_EmployeeEngagement_2` FOREIGN KEY (`idEmployee`) REFERENCES `Employee` (`idEmployee`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeEngagement`
--

LOCK TABLES `EmployeeEngagement` WRITE;
/*!40000 ALTER TABLE `EmployeeEngagement` DISABLE KEYS */;
/*!40000 ALTER TABLE `EmployeeEngagement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Group`
--

DROP TABLE IF EXISTS `Group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Group` (
  `idGroup` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idCategory` int(11) NOT NULL,
  PRIMARY KEY (`idGroup`),
  KEY `fk_Group_1_idx` (`idCategory`),
  CONSTRAINT `fk_Group_1` FOREIGN KEY (`idCategory`) REFERENCES `Category` (`idCategory`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Group`
--

LOCK TABLES `Group` WRITE;
/*!40000 ALTER TABLE `Group` DISABLE KEYS */;
INSERT INTO `Group` VALUES (1,'Fitnes grupa 1',NULL,1),(2,'Fitnes grupa 2',NULL,1),(3,'Krosfit poƒçetna grupa',NULL,3),(4,'Krosfit napredna grupa',NULL,3),(5,'Kardio grupa 1',NULL,4),(6,'Rekreativci grupa 1',NULL,2),(7,'Rekreativci grupa 2',NULL,2),(8,'Kardio grupa 2','',4),(9,'Test MS',NULL,1),(12,'Test G1','test g',4),(14,'Fitnes grupa 3','program 1133',1),(15,'test auth','test',3);
/*!40000 ALTER TABLE `Group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Member`
--

DROP TABLE IF EXISTS `Member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Member` (
  `idMember` int(11) NOT NULL AUTO_INCREMENT,
  `idCard` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nameSurname` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `dateOfMembership` date DEFAULT NULL,
  `remark` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idGroup` int(11) NOT NULL,
  PRIMARY KEY (`idMember`),
  UNIQUE KEY `idCard_UNIQUE` (`idCard`),
  KEY `fk_Member_1_idx` (`idGroup`),
  CONSTRAINT `fk_Member_1` FOREIGN KEY (`idGroup`) REFERENCES `Group` (`idGroup`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
INSERT INTO `Member` VALUES (1,'343259403','Petar Markoviƒá','M','pera@xmail.com','Njego≈°eva 31','064/321-33-87','1991-03-14','2014-07-25','',3),(2,'544309658','Ilija Rakoviƒá','M','ilija@xmail.com','Svetozara Markoviƒáa 44','063/965-12-25','1990-05-12','2014-10-05','',3),(3,'554096121','Anja Petroviƒá','≈Ω','anjap@ymail.com','Milutina Milankoviƒáa 10','064/987-12-55','1989-07-23','2015-01-25','',3),(4,'975112436','Ranko Uro≈°eviƒá','M','rankou@xmail.com','Karnegijeva 5','065/311-43-22','1990-08-15','2015-02-17','',3),(5,'856773720','Tijana Vraniƒá','≈Ω','tijanav@zmail.com','Vladimira Roloviƒáa 12','064/339-12-71','1991-05-30','2014-09-27','',3),(6,'564423090','Marko Radanov','M','markor@ymail.com','Jove Iliƒáa 44','064/443-75-17','1986-04-17','2014-09-13','',4),(7,'981042465','Nikola Stanojeviƒá','M','nikolast@ymail.com','Petra Preradoviƒáa 74','063/543-91-22','1988-10-11','2015-06-04','',4),(8,'094338211','Ma≈°a Mitroviƒá','≈Ω','masa@xmail.com','Kosovska 21','065/312-45-77','1989-12-03','2014-11-22','',4),(9,'549881093','Andrija Stamenkov','M','andrija@zmail.com','Mo≈°e Pijade 31','063/425-32-11','1984-01-09','2015-03-14','',4),(10,'434908146','Nemanja Ne≈°iƒá','M','nemke@xmail.com','Gospodara Vuƒçiƒáa 73','064/431-55-31','1987-07-03','2015-04-13','',4),(11,'656732986','Uro≈° Trajkoviƒá','M','uros@zmail.com','Dalmatinska 27','064/120-43-89','1985-11-04','2014-07-01','',6),(12,'456343657','Aleksandar Rodiƒá','M','alek@xmail.com','Dositejeva 15','064/470-42-76','1989-06-23','2014-09-14','',6),(13,'905487833','Elena Milenoviƒá','≈Ω','elena@ymail.com','Mitropolita Petra Zimonjiƒáa 64','063/669-32-11','1991-02-20','2014-12-06','',6),(14,'343985673','Milo≈° Joroviƒá','M','milos@zmail.com','Koƒçe Kapetana 41','064/122-09-32','1992-10-18','2015-05-14','',6),(15,'237854092','Una Zgonjanin','≈Ω','unazg@xmail.com','Ilije Gara≈°anina 32','064/787-23-56','1990-08-24','2014-10-04','',6),(16,'856764322','Nemanja Tasovac','M','taske@xmail.com','Bulevar Arsenija ƒåarnojeviƒáa 87','064/909-32-88','1983-01-13','2014-06-08','',7),(17,'547867443','Sa≈°a Vukoviƒá','M','salevu@ymail.com','Ivankovaƒçka 6','063/743-31-67','1987-12-05','2015-04-27','',7),(18,'906843266','Vuka≈°in Mitroviƒá','M','vulem@xmail.com','Starine Novaka 19','064/213-31-82','1986-05-19','2015-03-11','',7),(19,'136678433','Tanja Grbiƒá','≈Ω','tanjag@zmail.com','Desanke Maksimoviƒá 43','064/341-06-56','1989-09-16','2014-10-02','',7),(20,'954003843','Boris ƒÜiriƒá','M','boris@xmail.com','Zmaj Jovina 66','063/893-11-23','1991-05-27','2014-07-12','',7),(21,'856331765','Jovana Arsiƒá','≈Ω','jovana@xmail.com','Ruzveltova 73','064/451-90-77','1992-02-05','2014-07-16','',5),(22,'665432843','Ana Tri≈°iƒá','≈Ω','anat@ymail.com','Jovana Cvijiƒáa 53','064/121-89-03','1993-03-20','2014-11-06','',5),(23,'902342775','Jovan Ulemek','M','jovanulemek@xmail.com','Partizanska 20','063/177-23-90','1988-10-02','2015-02-12','',5),(24,'213754889','Pavle Petroviƒá','M','pavle@zmail.com','Kraljice Marije 8','065/965-34-75','1990-05-03','2014-08-10','',5),(25,'764236678','Nikola Simiƒá','M','nikolas@xmail.com','Makedonska 58','064/566-76-89','1990-11-07','2014-12-12','',5),(26,'957422452','Aleksandra Ga≈°iƒá','≈Ω','saskag@ymail.com','Humska 46','064/654-11-82','1984-06-26','2014-09-14','',1),(27,'432256121','Tijana Elenkov','≈Ω','elenkovt@xmail.com','Ive Andriƒáa 68','063/787-43-56','1985-07-29','2015-04-10','',1),(28,'657442954','Olja Radonjiƒá','≈Ω','oljara@zmail.com','Dimitrija Tucoviƒáa 24','063/338-20-87','1990-04-14','2014-10-07','',1),(29,'223095812','Jelena Kovrlija','≈Ω','jecakov@xmail.com','Filipa Vi≈°njiƒáa 14','064/561-87-34','1991-03-05','2014-05-22','',1),(30,'438513884','Du≈°an To≈°iƒá','M','duletos@xmail.com','Studentski trg 34','064/707-66-81','1989-08-19','2014-10-01','',1),(31,'584091775','Ana Jovanoviƒá','≈Ω','anajov@zmail.com','Rudniƒçka 45','063/909-35-11','1991-02-23','2015-01-16','',2),(32,'353367094','Valentina Grbiƒá','≈Ω','valentina@xmail.com','Mile≈°evska 30','064/199-67-66','1987-12-27','2014-07-22','',2),(33,'675098451','Tomislav Nikoliƒá','M','toman@ymail.com','Me≈°e Selimoviƒáa 8','064/980-01-01','1983-06-24','2014-08-18','',2),(34,'129909653','Marija Markoviƒá','≈Ω','marijam@ymail.com','Kneza Lazara 33','063/987-22-21','1991-10-07','2014-11-04','',2),(35,'855322142','Dragana Milutinov','≈Ω','dacam@xmail.com','Mladena Stojanoviƒáa 61','064/197-44-88','1990-07-13','2015-03-17','',2),(36,'439000321','Maja Stojanoviƒá','≈Ω','maja.jankovic10@gmail.com','Svetozara ƒÜoroviƒáa 11','063/912-18-33','1994-09-03','2015-02-12','',5),(37,'332486120','CCCCCCCCC','≈Ω','test@test.com','CCCCC','333','1999-04-13','2015-04-20','CC test update',5),(38,'433210933','Milo≈° Stojanoviƒá','M','stojanovicmilos31@gmail.com','Svetozara ƒÜoroviƒáa 11','064/912-18-31','1992-03-31','2015-04-15','',4),(39,'126854900','LLLLLLLLLLLLL','≈Ω','','LLLLLLLLLLLLLLLLLL','123','1996-08-23','2015-05-20','',1),(40,'094392854','MMMMMMMMMMMM','M','mmm@mmm.com','MMMMMMMMMMM','2121','1997-07-25','2015-05-20','',1),(41,'332100439','Marko Stojanoviƒá','M','markos@xmail.com','Dalmatinska 17','064/112-00-00','1987-06-23','2015-05-14','',1),(42,'123456','Test3 Test3','M','d@d','test add','1234','2000-06-14','2015-01-28',NULL,8),(45,'045778344','Nikola Nikolic','M','nidze@xmail.com','Ulica 157','064/333-22-11','1992-06-18','2016-07-01',NULL,4);
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MembershipFee`
--

DROP TABLE IF EXISTS `MembershipFee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MembershipFee` (
  `idMembershipFee` int(11) NOT NULL AUTO_INCREMENT,
  `dateFrom` date NOT NULL,
  `dateTo` date NOT NULL,
  `remark` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idMembershipFee`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MembershipFee`
--

LOCK TABLES `MembershipFee` WRITE;
/*!40000 ALTER TABLE `MembershipFee` DISABLE KEYS */;
INSERT INTO `MembershipFee` VALUES (1,'2015-04-01','2015-04-30',NULL),(2,'2015-05-01','2015-05-31',NULL),(3,'2015-06-01','2015-06-30',NULL),(4,'2016-08-01','2016-08-31',NULL);
/*!40000 ALTER TABLE `MembershipFee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Payment` (
  `idPayment` int(11) NOT NULL AUTO_INCREMENT,
  `idMembershipFee` int(11) NOT NULL,
  `idMember` int(11) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`idPayment`),
  KEY `fk_Payment_1_idx` (`idMembershipFee`),
  KEY `fk_Payment_2_idx` (`idMember`),
  CONSTRAINT `fk_Payment_1` FOREIGN KEY (`idMembershipFee`) REFERENCES `MembershipFee` (`idMembershipFee`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Payment_2` FOREIGN KEY (`idMember`) REFERENCES `Member` (`idMember`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
INSERT INTO `Payment` VALUES (1,1,11,2000,'2015-04-14'),(2,1,12,2000,'2015-04-21'),(3,1,24,2000,'2015-04-10'),(4,1,30,2000,'2015-04-15'),(5,1,37,2000,'2015-04-14'),(6,1,38,2000,'2015-04-20'),(7,2,11,2000,'2015-05-11'),(8,2,12,2000,'2015-05-29'),(9,2,24,2000,'2015-05-19'),(10,2,30,2000,'2015-05-29'),(11,2,38,2470,'2015-05-23'),(12,2,41,2000,'2015-06-23'),(13,3,12,2000,'2015-06-16'),(14,3,30,2000,'2015-06-20'),(15,3,38,2000,'2015-06-09'),(16,3,36,2500,'2016-06-15'),(17,2,36,3000,'2016-05-20'),(18,4,45,2500,'2016-09-07');
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Training`
--

DROP TABLE IF EXISTS `Training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Training` (
  `idTraining` int(11) NOT NULL AUTO_INCREMENT,
  `dateTime` timestamp NULL DEFAULT NULL,
  `durationMinutes` int(11) DEFAULT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idGroup` int(11) NOT NULL,
  PRIMARY KEY (`idTraining`),
  KEY `fk_Training_1_idx` (`idGroup`),
  CONSTRAINT `fk_Training_1` FOREIGN KEY (`idGroup`) REFERENCES `Group` (`idGroup`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Training`
--

LOCK TABLES `Training` WRITE;
/*!40000 ALTER TABLE `Training` DISABLE KEYS */;
INSERT INTO `Training` VALUES (1,'2015-05-24 18:30:23',90,'Vezbe za noge i trbusne misice. Srednji intenzitet rada.',1),(2,'2015-05-24 20:07:29',120,'',1),(3,'2015-05-25 09:35:32',0,'',2),(4,'2015-06-04 18:00:38',90,'Trening snage i izdr≈æljivosti, program 1.',4),(5,'2015-06-09 10:30:46',90,'Lagani trening program 2.',2),(6,'2015-06-09 12:00:29',90,'Kardio trening program 1.',5),(7,'2015-06-09 13:00:21',60,'Slobodan trening.',7),(8,'2015-06-09 11:06:16',0,'',7),(9,'2015-06-22 16:00:48',60,'Trening snage po programu 1.',3),(10,'2015-07-06 08:39:11',0,'',4),(11,NULL,360,'Test MS',4),(12,'2016-06-16 08:30:23',60,'Vezbe za noge i trbusne misice. Srednji intenzitet rada.',1),(13,'2016-06-16 08:30:23',60,'TEST REF 2.',1),(14,'2016-06-16 08:30:23',60,'TEST REF 3.',1),(20,'2016-03-31 10:55:00',60,'Test Android app',4),(21,'2016-07-08 17:30:00',90,'Trening po programu A100',4),(22,'2016-08-28 10:00:00',60,'Auth test',5),(23,'2016-08-25 16:30:00',60,'test mobile auth',5),(24,'2016-09-07 16:30:00',90,'Trening po programu A50',4),(25,'2016-09-07 16:30:00',90,'Trening program A51',4),(26,'2016-09-07 16:30:00',90,'Trening program A53',4);
/*!40000 ALTER TABLE `Training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
INSERT INTO `oauth_access_token` VALUES ('a7093009ae7e6138490e3d27037f19fe','¨Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6ÖZ‹Á–\0\0xpsr\0java.util.DatehjÅKYt\0\0xpw\0\0WF\r(xpsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0globalxt\0bearert\0$5a4751e0-31a9-4538-8823-b89ffa0cc620','4251899b434f9e2dcf36223978495893','milos','eKlubWebClient4WQvUNlj7gBiu998yJtCO2kigYNZI4yJ','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0@\0L\0rolet\0Ljava/lang/String;xpt\0adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0.eKlubWebClient4WQvUNlj7gBiu998yJtCO2kigYNZI4yJsr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsq\0~\0sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rresponse_typet\0tokent\0redirect_urit\0http://localhost:8000/appt\0statet\0\0t\0	client_idq\0~\0t\0scopet\0globalx\0sr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0&xsq\0~\0*w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xq\0~\0!psq\0~\0*w\0\0\0?@\0\0\0\0\0\0xsq\0~\0*w\0\0\0?@\0\0\0\0\0q\0~\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\03sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0@\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0	127.0.0.1t\0 8C94531204C5EF790D1F309C1D0442EFpsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0@\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0\'sr\0java.util.TreeSet›òPìïÌá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0@\0\0xpw\0\0\0q\0~\0xpt\0milos',NULL),('6b1a882c67231304bd055bfbd1144834','¨Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6ÖZ‹Á–\0\0xpsr\0java.util.DatehjÅKYt\0\0xpw\0\0W,˛-xpsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0mobilext\0bearert\0$2fb90848-4f5f-44e8-b7f6-abc4d7b2e0e2','46546206cc92f882ffaa79b90e67597e','milos','eKlubMobileClient4vY6OMfvNC4HKtAb8Lr4eT7pKsyGI','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0@\0L\0rolet\0Ljava/lang/String;xpt\0adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0.eKlubMobileClient4vY6OMfvNC4HKtAb8Lr4eT7pKsyGIsr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsq\0~\0sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rresponse_typet\0tokent\0redirect_urit\0+https://mpowafin.co.za/assets/icon-tick.svgt\0	client_idq\0~\0t\0scopet\0mobilex\0sr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0$xsq\0~\0(w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xq\0~\0!psq\0~\0(w\0\0\0?@\0\0\0\0\0\0xsq\0~\0(w\0\0\0?@\0\0\0\0\0q\0~\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0@\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0192.168.1.4t\0 6624B39AAE786376502DD7CBB0EBE0EFpsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0@\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0%sr\0java.util.TreeSet›òPìïÌá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0@\0\0xpw\0\0\0q\0~\0xpt\0milos',NULL);
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_approvals`
--

DROP TABLE IF EXISTS `oauth_approvals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientId` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_approvals`
--

LOCK TABLES `oauth_approvals` WRITE;
/*!40000 ALTER TABLE `oauth_approvals` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_approvals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `resource_ids` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_secret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authorized_grant_types` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authorities` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `autoapprove` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('eKlubMobileClient4vY6OMfvNC4HKtAb8Lr4eT7pKsyGI','',NULL,'mobile','implicit','','',NULL,NULL,'{}','mobile'),('eKlubWebClient4WQvUNlj7gBiu998yJtCO2kigYNZI4yJ','',NULL,'global','implicit','','',NULL,NULL,'{}','global');
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_token`
--

DROP TABLE IF EXISTS `oauth_client_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_token`
--

LOCK TABLES `oauth_client_token` WRITE;
/*!40000 ALTER TABLE `oauth_client_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_code`
--

DROP TABLE IF EXISTS `oauth_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_code` (
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_code`
--

LOCK TABLES `oauth_code` WRITE;
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_eklub_authority`
--

DROP TABLE IF EXISTS `oauth_eklub_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_eklub_authority` (
  `idAuthority` int(11) NOT NULL AUTO_INCREMENT,
  `authorityName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idAuthority`),
  UNIQUE KEY `authorityName` (`authorityName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_eklub_authority`
--

LOCK TABLES `oauth_eklub_authority` WRITE;
/*!40000 ALTER TABLE `oauth_eklub_authority` DISABLE KEYS */;
INSERT INTO `oauth_eklub_authority` VALUES (1,'admin'),(2,'staff');
/*!40000 ALTER TABLE `oauth_eklub_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-16 22:39:35
