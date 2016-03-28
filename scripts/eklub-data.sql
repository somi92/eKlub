CREATE DATABASE  IF NOT EXISTS `eKlub` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `eKlub`;
-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: eKlub
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

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
-- Dumping data for table `Attendance`
--

LOCK TABLES `Attendance` WRITE;
/*!40000 ALTER TABLE `Attendance` DISABLE KEYS */;
INSERT INTO `Attendance` VALUES (1,1,26,1,0),(2,1,27,1,0),(3,1,28,1,0),(4,1,29,1,0),(5,1,30,1,0),(6,1,39,1,0),(7,1,40,1,0),(8,2,26,1,0),(9,2,27,1,0),(10,2,28,1,0),(11,2,29,1,0),(12,2,30,1,0),(13,2,39,1,0),(14,2,40,1,0),(15,3,31,1,0),(16,3,32,1,0),(17,3,33,1,0),(18,3,34,1,0),(19,3,35,1,0),(20,4,6,0,0),(21,4,7,1,0),(22,4,8,1,0),(23,4,9,1,10),(24,4,10,1,5),(25,4,38,1,0),(26,5,31,1,0),(27,5,32,1,0),(28,5,33,1,0),(29,5,34,0,0),(30,5,35,0,0),(31,6,21,1,0),(32,6,22,1,0),(33,6,23,1,0),(34,6,24,1,0),(35,6,25,1,0),(36,6,36,1,5),(37,6,37,0,0),(38,7,16,1,0),(39,7,17,1,0),(40,7,18,1,0),(41,7,19,1,0),(42,7,20,0,0),(43,8,16,1,0),(44,8,17,1,0),(45,8,18,1,0),(46,8,19,1,0),(47,8,20,1,0),(48,9,1,1,5),(49,9,2,1,0),(50,9,3,1,0),(51,9,4,1,0),(52,9,5,1,0),(53,10,6,1,0),(54,10,7,1,0),(55,10,8,1,0),(56,10,9,1,0),(57,10,10,0,0),(58,10,38,0,0);
/*!40000 ALTER TABLE `Attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES (1,'Fitnes','test1'),(2,'Slobodni rekreativci','test2'),(3,'Krosfit',NULL),(4,'Kardio',NULL);
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'Miloš Stojanović','milos92s@yahoo.com','064/912-18-31','Svetozara Ćorovića 11','milos','somi92',NULL),(2,'Marko Filipov','markofilipov@xmail.com','065/107-00-09','Milutina Milankovića 70','marko','marko',NULL);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `EmployeeEngagement`
--

LOCK TABLES `EmployeeEngagement` WRITE;
/*!40000 ALTER TABLE `EmployeeEngagement` DISABLE KEYS */;
/*!40000 ALTER TABLE `EmployeeEngagement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Group`
--

LOCK TABLES `Group` WRITE;
/*!40000 ALTER TABLE `Group` DISABLE KEYS */;
INSERT INTO `Group` VALUES (1,'Fitnes grupa 1',NULL,1),(2,'Fitnes grupa 2',NULL,1),(3,'Krosfit početna grupa',NULL,3),(4,'Krosfit napredna grupa',NULL,3),(5,'Kardio grupa 1',NULL,4),(6,'Rekreativci grupa 1',NULL,2),(7,'Rekreativci grupa 2',NULL,2),(8,'Kardio grupa 2','',4),(9,'Test MS',NULL,1);
/*!40000 ALTER TABLE `Group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
INSERT INTO `Member` VALUES (1,'343259403','Petar Marković','M','pera@xmail.com','Njegoševa 31','064/321-33-87','1991-03-14','2014-07-25','',3),(2,'544309658','Ilija Raković','M','ilija@xmail.com','Svetozara Markovića 44','063/965-12-25','1990-05-12','2014-10-05','',3),(3,'554096121','Anja Petrović','Ž','anjap@ymail.com','Milutina Milankovića 10','064/987-12-55','1989-07-23','2015-01-25','',3),(4,'975112436','Ranko Urošević','M','rankou@xmail.com','Karnegijeva 5','065/311-43-22','1990-08-15','2015-02-17','',3),(5,'856773720','Tijana Vranić','Ž','tijanav@zmail.com','Vladimira Rolovića 12','064/339-12-71','1991-05-30','2014-09-27','',3),(6,'564423090','Marko Radanov','M','markor@ymail.com','Jove Ilića 44','064/443-75-17','1986-04-17','2014-09-13','',4),(7,'981042465','Nikola Stanojević','M','nikolast@ymail.com','Petra Preradovića 74','063/543-91-22','1988-10-11','2015-06-04','',4),(8,'094338211','Maša Mitrović','Ž','masa@xmail.com','Kosovska 21','065/312-45-77','1989-12-03','2014-11-22','',4),(9,'549881093','Andrija Stamenkov','M','andrija@zmail.com','Moše Pijade 31','063/425-32-11','1984-01-09','2015-03-14','',4),(10,'434908146','Nemanja Nešić','M','nemke@xmail.com','Gospodara Vučića 73','064/431-55-31','1987-07-03','2015-04-13','',4),(11,'656732986','Uroš Trajković','M','uros@zmail.com','Dalmatinska 27','064/120-43-89','1985-11-04','2014-07-01','',6),(12,'456343657','Aleksandar Rodić','M','alek@xmail.com','Dositejeva 15','064/470-42-76','1989-06-23','2014-09-14','',6),(13,'905487833','Elena Milenović','Ž','elena@ymail.com','Mitropolita Petra Zimonjića 64','063/669-32-11','1991-02-20','2014-12-06','',6),(14,'343985673','Miloš Jorović','M','milos@zmail.com','Koče Kapetana 41','064/122-09-32','1992-10-18','2015-05-14','',6),(15,'237854092','Una Zgonjanin','Ž','unazg@xmail.com','Ilije Garašanina 32','064/787-23-56','1990-08-24','2014-10-04','',6),(16,'856764322','Nemanja Tasovac','M','taske@xmail.com','Bulevar Arsenija Čarnojevića 87','064/909-32-88','1983-01-13','2014-06-08','',7),(17,'547867443','Saša Vuković','M','salevu@ymail.com','Ivankovačka 6','063/743-31-67','1987-12-05','2015-04-27','',7),(18,'906843266','Vukašin Mitrović','M','vulem@xmail.com','Starine Novaka 19','064/213-31-82','1986-05-19','2015-03-11','',7),(19,'136678433','Tanja Grbić','Ž','tanjag@zmail.com','Desanke Maksimović 43','064/341-06-56','1989-09-16','2014-10-02','',7),(20,'954003843','Boris Ćirić','M','boris@xmail.com','Zmaj Jovina 66','063/893-11-23','1991-05-27','2014-07-12','',7),(21,'856331765','Jovana Arsić','Ž','jovana@xmail.com','Ruzveltova 73','064/451-90-77','1992-02-05','2014-07-16','',5),(22,'665432843','Ana Trišić','Ž','anat@ymail.com','Jovana Cvijića 53','064/121-89-03','1993-03-20','2014-11-06','',5),(23,'902342775','Jovan Ulemek','M','jovanulemek@xmail.com','Partizanska 20','063/177-23-90','1988-10-02','2015-02-12','',5),(24,'213754889','Pavle Petrović','M','pavle@zmail.com','Kraljice Marije 8','065/965-34-75','1990-05-03','2014-08-10','',5),(25,'764236678','Nikola Simić','M','nikolas@xmail.com','Makedonska 58','064/566-76-89','1990-11-07','2014-12-12','',5),(26,'957422452','Aleksandra Gašić','Ž','saskag@ymail.com','Humska 46','064/654-11-82','1984-06-26','2014-09-14','',1),(27,'432256121','Tijana Elenkov','Ž','elenkovt@xmail.com','Ive Andrića 68','063/787-43-56','1985-07-29','2015-04-10','',1),(28,'657442954','Olja Radonjić','Ž','oljara@zmail.com','Dimitrija Tucovića 24','063/338-20-87','1990-04-14','2014-10-07','',1),(29,'223095812','Jelena Kovrlija','Ž','jecakov@xmail.com','Filipa Višnjića 14','064/561-87-34','1991-03-05','2014-05-22','',1),(30,'438513884','Dušan Tošić','M','duletos@xmail.com','Studentski trg 34','064/707-66-81','1989-08-19','2014-10-01','',1),(31,'584091775','Ana Jovanović','Ž','anajov@zmail.com','Rudnička 45','063/909-35-11','1991-02-23','2015-01-16','',2),(32,'353367094','Valentina Grbić','Ž','valentina@xmail.com','Mileševska 30','064/199-67-66','1987-12-27','2014-07-22','',2),(33,'675098451','Tomislav Nikolić','M','toman@ymail.com','Meše Selimovića 8','064/980-01-01','1983-06-24','2014-08-18','',2),(34,'129909653','Marija Marković','Ž','marijam@ymail.com','Kneza Lazara 33','063/987-22-21','1991-10-07','2014-11-04','',2),(35,'855322142','Dragana Milutinov','Ž','dacam@xmail.com','Mladena Stojanovića 61','064/197-44-88','1990-07-13','2015-03-17','',2),(36,'439000321','Maja Stojanović','Ž','maja.jankovic10@gmail.com','Svetozara Ćorovića 11','063/912-18-33','1994-09-03','2015-02-12','',5),(37,'332486120','CCCCCCCCC','Ž','test@test.com','CCCCC','333','1999-04-13','2015-04-20','CC test update',5),(38,'433210933','Miloš Stojanović','M','stojanovicmilos31@gmail.com','Svetozara Ćorovića 11','064/912-18-31','1992-03-31','2015-04-15','',4),(39,'126854900','LLLLLLLLLLLLL','Ž','','LLLLLLLLLLLLLLLLLL','123','1996-08-23','2015-05-20','',1),(40,'094392854','MMMMMMMMMMMM','M','mmm@mmm.com','MMMMMMMMMMM','2121','1997-07-25','2015-05-20','',1),(41,'332100439','Marko Stojanović','M','markos@xmail.com','Dalmatinska 17','064/112-00-00','1987-06-23','2015-05-14','',1);
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `MembershipFee`
--

LOCK TABLES `MembershipFee` WRITE;
/*!40000 ALTER TABLE `MembershipFee` DISABLE KEYS */;
INSERT INTO `MembershipFee` VALUES (1,'2015-04-01','2015-04-30',NULL),(2,'2015-05-01','2015-05-31',NULL),(3,'2015-06-01','2015-06-30',NULL);
/*!40000 ALTER TABLE `MembershipFee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
INSERT INTO `Payment` VALUES (1,1,11,2000,'2015-04-14'),(2,1,12,2000,'2015-04-21'),(3,1,24,2000,'2015-04-10'),(4,1,30,2000,'2015-04-15'),(5,1,37,2000,'2015-04-14'),(6,1,38,2000,'2015-04-20'),(7,2,11,2000,'2015-05-11'),(8,2,12,2000,'2015-05-29'),(9,2,24,2000,'2015-05-19'),(10,2,30,2000,'2015-05-29'),(11,2,38,2000,'2015-05-20'),(12,2,41,2000,'2015-06-23'),(13,3,12,2000,'2015-06-16'),(14,3,30,2000,'2015-06-20'),(15,3,38,2000,'2015-06-09');
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Training`
--

LOCK TABLES `Training` WRITE;
/*!40000 ALTER TABLE `Training` DISABLE KEYS */;
INSERT INTO `Training` VALUES (1,'2015-05-24 18:30:23',90,'Vezbe za noge i trbusne misice. Srednji intenzitet rada.',1),(2,'2015-05-24 20:07:29',120,'',1),(3,'2015-05-25 09:35:32',0,'',2),(4,'2015-06-04 18:00:38',90,'Trening snage i izdržljivosti, program 1.',4),(5,'2015-06-09 10:30:46',90,'Lagani trening program 2.',2),(6,'2015-06-09 12:00:29',90,'Kardio trening program 1.',5),(7,'2015-06-09 13:00:21',60,'Slobodan trening.',7),(8,'2015-06-09 11:06:16',0,'',7),(9,'2015-06-22 16:00:48',60,'Trening snage po programu 1.',3),(10,'2015-07-06 08:39:11',0,'',4),(11,NULL,360,'Test MS',4);
/*!40000 ALTER TABLE `Training` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-28 21:32:05
