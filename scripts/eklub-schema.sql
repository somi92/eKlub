CREATE DATABASE  IF NOT EXISTS `eKlub` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `eKlub`;
-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: eKlub
-- ------------------------------------------------------
-- Server version	5.5.50-0ubuntu0.14.04.1

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
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-28 14:09:36
