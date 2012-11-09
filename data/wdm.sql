-- MySQL dump 10.13  Distrib 5.5.19, for osx10.6 (i386)
--
-- Host: localhost    Database: wdm
-- ------------------------------------------------------
-- Server version	5.5.19

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
-- Table structure for table `allergen`
--

DROP TABLE IF EXISTS `allergen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allergen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergen`
--

LOCK TABLES `allergen` WRITE;
/*!40000 ALTER TABLE `allergen` DISABLE KEYS */;
INSERT INTO `allergen` VALUES (1,'Codeine Camsyl'),(2,'Asprin'),(3,'Ethromyacin'),(4,'Pizza'),(5,'Latex'),(6,'Penicillin');
/*!40000 ALTER TABLE `allergen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinician`
--

DROP TABLE IF EXISTS `clinician`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinician` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `ecommons_id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `pager` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `primary_phone` varchar(255) DEFAULT NULL,
  `purged` bit(1) DEFAULT NULL,
  `salt` varchar(255) NOT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  `credential` int(11) DEFAULT NULL,
  `division` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9D8F786EB98F6C22` (`department`),
  KEY `FK9D8F786EE9C48E58` (`division`),
  KEY `FK9D8F786E2FAB62C` (`credential`),
  CONSTRAINT `FK9D8F786E2FAB62C` FOREIGN KEY (`credential`) REFERENCES `credential` (`id`),
  CONSTRAINT `FK9D8F786EB98F6C22` FOREIGN KEY (`department`) REFERENCES `department` (`id`),
  CONSTRAINT `FK9D8F786EE9C48E58` FOREIGN KEY (`division`) REFERENCES `division` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinician`
--

LOCK TABLES `clinician` WRITE;
/*!40000 ALTER TABLE `clinician` DISABLE KEYS */;
INSERT INTO `clinician` VALUES (1,'','xxx','nick@wdeanmedical.com',NULL,'Joe',NULL,'Foobar','H.',NULL,'','617 980-9987','\0','',NULL,3,4,7),(2,'','yyy','nick@wdeanmedical.com',NULL,'Mary',NULL,'Moore','Kate',NULL,'','617 980-0099','\0','',NULL,4,2,3);
/*!40000 ALTER TABLE `clinician` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credential`
--

DROP TABLE IF EXISTS `credential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credential`
--

LOCK TABLES `credential` WRITE;
/*!40000 ALTER TABLE `credential` DISABLE KEYS */;
INSERT INTO `credential` VALUES (1,'MD'),(2,'PhD'),(3,'MD PhD'),(4,'RN'),(5,'RN PhD'),(6,'RN MSN'),(7,'MA/MS'),(8,'MPH'),(9,'NP'),(10,'PA'),(11,'Other');
/*!40000 ALTER TABLE `credential` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Anesthesia'),(2,'CRC'),(3,'Dermatology'),(4,'Medicine'),(5,'Neurology'),(6,'Nursing'),(7,'Nutrition'),(8,'Obstetrics, Gynecology & Reproductive Biology'),(9,'Ophthalmology'),(10,'Orthopedic Surgery'),(11,'Otology-Laryngology'),(12,'Pathology'),(13,'Pediatrics'),(14,'Physical Medicine & Rehabilitation'),(15,'Population Medicine'),(16,'Psychiatry'),(17,'Radiology'),(18,'Radiation Oncology'),(19,'Surgery'),(20,'Other');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `division`
--

DROP TABLE IF EXISTS `division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `division` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `division`
--

LOCK TABLES `division` WRITE;
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
INSERT INTO `division` VALUES (1,'Adolescent Medicine'),(2,'Allergy & Inflammation'),(3,'Cardiology'),(4,'Cardiac Surgery'),(5,'Colon & Rectal Surgery'),(6,'Endocrinology'),(7,'Nutrition'),(8,'Emergency Medicine'),(9,'Gastroenterology'),(10,'General Medicine'),(11,'Gerontology'),(12,'Gynecology'),(13,'Hematology/Oncology'),(14,'Infectious Disease'),(15,'Minimally Invasive Surgery'),(16,'Nephrology'),(17,'Neurosurgery'),(18,'Newborn Medicine'),(19,'Plastic & Reconstructive Surgery'),(20,'Podiatry'),(21,'Pulmonology'),(22,'Rheumatology'),(23,'Sleep Medicine'),(24,'Sports Medicine'),(25,'Thoracic Surgery'),(26,'Trauma Surgery & Critical Care'),(27,'Transplant'),(28,'Urologic Surgery'),(29,'Vascular & Endovascular Surgery'),(30,'Weight Loss Surgery'),(31,'Other');
/*!40000 ALTER TABLE `division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_issue`
--

DROP TABLE IF EXISTS `health_issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `health_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_issue`
--

LOCK TABLES `health_issue` WRITE;
/*!40000 ALTER TABLE `health_issue` DISABLE KEYS */;
INSERT INTO `health_issue` VALUES (1,'Hypercholesterolemia'),(2,'Liver function test abnormality'),(3,'Hypertriglyceridemia'),(4,'Rhinitis, allergic'),(5,'Obesity'),(6,'Anorexia Nervosa'),(7,'Hives'),(8,'Psoriasis'),(9,'Acne'),(10,'Heart Murmur'),(11,'Angina');
/*!40000 ALTER TABLE `health_issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immunization`
--

DROP TABLE IF EXISTS `immunization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `immunization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immunization`
--

LOCK TABLES `immunization` WRITE;
/*!40000 ALTER TABLE `immunization` DISABLE KEYS */;
INSERT INTO `immunization` VALUES (1,'Influenza Vaccine'),(2,'Td Vaccine (Adult)'),(3,'Smallpox Vaccine'),(4,'Measles Vaccine'),(5,'Tetanus Shot'),(6,'Rabies Vaccine'),(7,'Herpes Vaccine'),(8,'Gonnoreah Vaccine'),(9,'Syphillis Vaccine');
/*!40000 ALTER TABLE `immunization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_procedure`
--

DROP TABLE IF EXISTS `medical_procedure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_procedure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_procedure`
--

LOCK TABLES `medical_procedure` WRITE;
/*!40000 ALTER TABLE `medical_procedure` DISABLE KEYS */;
INSERT INTO `medical_procedure` VALUES (1,'Colonoscopy'),(2,'Physical Exam'),(3,'Ear Canal Irrigation'),(4,'Consultation'),(5,'Tick Removal');
/*!40000 ALTER TABLE `medical_procedure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_test`
--

DROP TABLE IF EXISTS `medical_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_test`
--

LOCK TABLES `medical_test` WRITE;
/*!40000 ALTER TABLE `medical_test` DISABLE KEYS */;
INSERT INTO `medical_test` VALUES (1,'EGFR'),(2,'ALT (SGPT)'),(3,'Lipid Profile'),(4,'Hemoglobin A1C'),(5,'Creatinine'),(6,'HEPATIC FUNCTION PROFILE (ALT,AST,ALK PH,BILI\'S,TP,ALB)'),(7,'Prostatic Antigen (PSA)');
/*!40000 ALTER TABLE `medical_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_test_status`
--

DROP TABLE IF EXISTS `medical_test_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_test_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_test_status`
--

LOCK TABLES `medical_test_status` WRITE;
/*!40000 ALTER TABLE `medical_test_status` DISABLE KEYS */;
INSERT INTO `medical_test_status` VALUES (1,'Final Result'),(2,'Overdue');
/*!40000 ALTER TABLE `medical_test_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
INSERT INTO `medication` VALUES (1,'Prozac'),(2,'Xanax'),(3,'Lithium'),(4,'Thorazine'),(5,'Simvistatin'),(6,'Atavan'),(7,'Penicillin'),(8,'Prednisone');
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `primary_phone` varchar(255) DEFAULT NULL,
  `purged` bit(1) DEFAULT NULL,
  `salt` varchar(255) NOT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `access_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'','nick@wdeanmedical.com','Nick','2012-10-27 09:35:10','Sophinos','Jason','gcd1Oi5ohpMROajCUlg/NDAwJp2fZFPtCFBh0N16i5o=','617 980-7794','\0','$3@Nn3HMS2012',NULL,'nick',1),(2,'','nick@wdeanmedical.com','Andy',NULL,'Warhol',NULL,'gcd1Oi5ohpMROajCUlg/NDAwJp2fZFPtCFBh0N16i5o=','617 980-7794','\0','$3@Nn3HMS2012',NULL,'andy',1),(3,'','nick@wdeanmedical.com','Bill','2012-10-27 09:35:36','Clinton',NULL,'gcd1Oi5ohpMROajCUlg/NDAwJp2fZFPtCFBh0N16i5o=','617 980-7794','\0','$3@Nn3HMS2012',NULL,'bill',1),(4,'','nick@wdeanmedical.com','Bobby','2012-10-27 09:36:01','Fisher',NULL,'gcd1Oi5ohpMROajCUlg/NDAwJp2fZFPtCFBh0N16i5o=','617 980-7794','\0','$3@Nn3HMS2012',NULL,'bobby',1),(5,'','nick@wdeanmedical.com','Rebecca','2012-10-22 21:40:55','De Mornay',NULL,'gcd1Oi5ohpMROajCUlg/NDAwJp2fZFPtCFBh0N16i5o=','617 980-7794','\0','$3@Nn3HMS2012',NULL,'rebecca',1);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_allergen`
--

DROP TABLE IF EXISTS `patient_allergen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_allergen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient` int(11) NOT NULL,
  `allergen` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDBBE67CE49911B5` (`allergen`),
  KEY `FKDBBE67CAA59FF39` (`patient`),
  KEY `FKDBBE67C95504202` (`allergen`),
  KEY `FKDBBE67C8F05114C` (`patient`),
  CONSTRAINT `FKDBBE67C8F05114C` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKDBBE67C95504202` FOREIGN KEY (`allergen`) REFERENCES `allergen` (`id`),
  CONSTRAINT `FKDBBE67CAA59FF39` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKDBBE67CE49911B5` FOREIGN KEY (`allergen`) REFERENCES `allergen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_allergen`
--

LOCK TABLES `patient_allergen` WRITE;
/*!40000 ALTER TABLE `patient_allergen` DISABLE KEYS */;
INSERT INTO `patient_allergen` VALUES (1,1,1,'\"Gives me the hives\"'),(2,1,2,'\"Stomach ache\"'),(3,1,3,'\"Dry mouth, sleepiness\"'),(4,2,4,NULL),(5,2,3,'rash on hands'),(6,2,6,'difficulty breathing, fever'),(7,2,5,'dizziness'),(8,3,5,'dry mouth'),(9,3,1,'watery eyes'),(10,3,4,'hiccups'),(11,4,3,'fever, chills'),(12,4,2,'intestinal bloating'),(13,5,4,'\"eyes swollen shut\"'),(14,5,3,'\"choking sensation\"');
/*!40000 ALTER TABLE `patient_allergen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_health_issue`
--

DROP TABLE IF EXISTS `patient_health_issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_health_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `health_issue` int(11) NOT NULL,
  `patient` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK14D928308F05114C` (`patient`),
  KEY `FK14D92830F9817355` (`health_issue`),
  CONSTRAINT `FK14D928308F05114C` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK14D92830F9817355` FOREIGN KEY (`health_issue`) REFERENCES `health_issue` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_health_issue`
--

LOCK TABLES `patient_health_issue` WRITE;
/*!40000 ALTER TABLE `patient_health_issue` DISABLE KEYS */;
INSERT INTO `patient_health_issue` VALUES (1,'2012-10-18 20:47:18',4,1),(2,'2012-01-22 20:47:35',2,1),(3,'2010-10-22 20:47:55',3,1),(4,'2002-02-22 20:48:10',5,1),(5,'2012-10-10 20:49:15',2,2),(6,'1998-02-01 20:49:28',3,2),(7,'1997-01-02 20:49:45',8,3),(8,'1999-02-02 20:50:09',11,3),(9,'2012-10-03 20:50:37',10,3),(10,'2012-10-13 20:50:50',8,4),(11,'2000-10-04 20:51:10',9,4),(12,'1999-01-01 20:51:29',10,5),(13,'2004-10-22 20:51:50',7,5);
/*!40000 ALTER TABLE `patient_health_issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_immunization`
--

DROP TABLE IF EXISTS `patient_immunization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_immunization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient` int(11) NOT NULL,
  `immunization` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60CB30FC8F05114C` (`patient`),
  KEY `FK60CB30FCB94202` (`immunization`),
  CONSTRAINT `FK60CB30FC8F05114C` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK60CB30FCB94202` FOREIGN KEY (`immunization`) REFERENCES `immunization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_immunization`
--

LOCK TABLES `patient_immunization` WRITE;
/*!40000 ALTER TABLE `patient_immunization` DISABLE KEYS */;
INSERT INTO `patient_immunization` VALUES (1,1,1,'2012-10-02 18:26:53'),(2,1,3,'2012-06-13 18:27:00'),(3,1,5,'2011-10-20 18:27:09'),(4,2,3,'2000-10-20 18:27:30'),(5,2,8,'1999-10-20 18:27:39'),(6,3,5,'2012-10-10 18:27:53'),(7,3,6,'2012-10-03 18:28:02'),(8,3,7,'2012-05-03 18:28:07'),(9,4,1,'2011-01-20 18:28:16'),(10,4,3,'2010-02-20 18:28:32'),(11,4,5,'2012-03-20 18:28:43'),(12,4,7,'2012-10-01 18:28:50'),(13,4,9,'1998-03-20 18:28:58'),(14,5,2,'2010-12-20 18:29:09'),(15,5,4,'2012-03-16 18:29:19'),(16,5,6,'2012-02-02 18:29:28'),(17,5,8,'2005-01-02 18:29:36');
/*!40000 ALTER TABLE `patient_immunization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_medical_procedure`
--

DROP TABLE IF EXISTS `patient_medical_procedure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_medical_procedure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `due_date` datetime DEFAULT NULL,
  `last_done` datetime DEFAULT NULL,
  `medical_procedure` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `patient` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK324353EB5BCF80E5` (`medical_procedure`),
  KEY `FK324353EB69DCE149` (`status`),
  KEY `FK324353EB8F05114C` (`patient`),
  CONSTRAINT `FK324353EB8F05114C` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK324353EB5BCF80E5` FOREIGN KEY (`medical_procedure`) REFERENCES `medical_procedure` (`id`),
  CONSTRAINT `FK324353EB69DCE149` FOREIGN KEY (`status`) REFERENCES `medical_test_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_medical_procedure`
--

LOCK TABLES `patient_medical_procedure` WRITE;
/*!40000 ALTER TABLE `patient_medical_procedure` DISABLE KEYS */;
INSERT INTO `patient_medical_procedure` VALUES (1,'2013-01-03 07:49:26',NULL,3,2,1),(2,'2012-10-02 07:50:03','2012-10-01 07:50:09',1,1,1),(3,'2013-01-04 07:52:53','2012-04-12 07:53:01',2,2,2),(4,'2012-07-04 07:53:50','2012-07-04 07:53:50',3,1,3),(5,'2013-08-01 07:54:18','2011-10-27 07:54:29',4,2,3),(6,'2012-10-03 07:54:53','2010-10-14 07:54:58',5,1,4),(7,'2012-11-27 07:55:27','2012-10-01 07:55:37',2,2,5);
/*!40000 ALTER TABLE `patient_medical_procedure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_medical_test`
--

DROP TABLE IF EXISTS `patient_medical_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_medical_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `patient` int(11) NOT NULL,
  `medical_test` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `clinician` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK18C3247A8F05114C` (`patient`),
  KEY `FK18C3247ADDAFC285` (`medical_test`),
  KEY `FK18C3247A69DCE149` (`status`),
  KEY `FK18C3247A461A639E` (`clinician`),
  CONSTRAINT `FK18C3247A461A639E` FOREIGN KEY (`clinician`) REFERENCES `clinician` (`id`),
  CONSTRAINT `FK18C3247A69DCE149` FOREIGN KEY (`status`) REFERENCES `medical_test_status` (`id`),
  CONSTRAINT `FK18C3247A8F05114C` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK18C3247ADDAFC285` FOREIGN KEY (`medical_test`) REFERENCES `medical_test` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_medical_test`
--

LOCK TABLES `patient_medical_test` WRITE;
/*!40000 ALTER TABLE `patient_medical_test` DISABLE KEYS */;
INSERT INTO `patient_medical_test` VALUES (1,'1999-10-10 21:42:25',1,5,1,1),(2,'2012-10-05 21:42:52',1,3,1,1),(3,'1999-02-02 21:43:08',1,2,1,2),(4,'2003-10-25 21:43:31',1,1,1,2),(5,'2000-10-05 21:44:15',1,7,1,2),(6,'2001-02-02 21:44:31',1,6,1,1),(7,'2010-01-01 21:45:04',2,2,1,2),(8,'1999-10-05 21:45:25',2,1,1,1),(9,'1999-07-07 21:45:41',2,7,1,2),(10,'2009-02-13 21:46:00',2,3,1,2),(11,'2012-10-11 21:46:24',3,1,1,2),(12,'2001-01-02 21:46:37',3,2,1,1),(13,'2008-10-11 21:46:57',3,4,1,1),(14,'2007-06-06 21:47:20',4,3,1,1),(15,'2003-08-08 21:47:38',5,5,1,2),(16,'2007-07-07 21:47:59',5,2,1,2);
/*!40000 ALTER TABLE `patient_medical_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_medication`
--

DROP TABLE IF EXISTS `patient_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_medication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient` int(11) NOT NULL,
  `medication` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `instructions` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `prescriber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6E979049AA59FF39` (`patient`),
  KEY `FK6E979049E3122A0F` (`medication`),
  KEY `FK6E9790498F05114C` (`patient`),
  KEY `FK6E97904942BE7B1C` (`medication`),
  KEY `FK6E979049FDD09DD5` (`prescriber`),
  CONSTRAINT `FK6E97904942BE7B1C` FOREIGN KEY (`medication`) REFERENCES `medication` (`id`),
  CONSTRAINT `FK6E9790498F05114C` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK6E979049AA59FF39` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK6E979049E3122A0F` FOREIGN KEY (`medication`) REFERENCES `medication` (`id`),
  CONSTRAINT `FK6E979049FDD09DD5` FOREIGN KEY (`prescriber`) REFERENCES `clinician` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_medication`
--

LOCK TABLES `patient_medication` WRITE;
/*!40000 ALTER TABLE `patient_medication` DISABLE KEYS */;
INSERT INTO `patient_medication` VALUES (1,1,1,'2012-10-03 03:57:48','Take with milk','12% LOTION',1),(2,1,2,'2012-08-08 03:57:58','Eat with cheese','12% LOTION',2),(3,1,3,'2012-01-24 03:58:05','as needed','12% LOTION',1),(4,2,2,'2003-02-02 03:58:12','Take 2 capsules daily','0.05 % CREAM',2),(5,2,3,'2001-01-01 03:58:25','Take 2 capsules daily','0.05 % CREAM',1),(6,3,1,'2000-10-04 03:58:39','Take 2 capsules daily','0.05 % CREAM',1),(7,4,1,'2008-10-01 03:58:53','Take 3 capsules daily','50 MCG/ACTUATION SPRAY, SUSPENSION',2),(8,4,2,'2006-03-04 03:59:14','Take 1 capsule daily','50 MCG/ACTUATION SPRAY, SUSPENSION',2),(9,4,4,'2009-11-22 03:59:30','Take one tablet daily every evening for cholesterol','0.1 % CREAM',2),(10,4,6,'1998-12-03 03:59:48','Apply twice daily as directed','0.1 % CREAM',1),(11,5,8,'2001-03-23 04:00:02','Take one tablet daily every evening for cholesterol','20 MG TABLET',1),(12,5,7,'2012-09-18 04:00:18','Apply twice daily as directed','20 MG TABLET',2),(13,4,3,'1996-02-29 04:00:31','Use 1 to 2 puffs in each nostril once daily','40 MG CAPSULE',2),(14,5,2,'2005-02-11 04:00:46','Apply sparingly twice daily to affected area; avoid face, under armpits and groin','40 MG CAPSULE',2);
/*!40000 ALTER TABLE `patient_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `ecommons_id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `pager` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `primary_phone` varchar(255) DEFAULT NULL,
  `purged` bit(1) DEFAULT NULL,
  `salt` varchar(255) NOT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_session`
--

DROP TABLE IF EXISTS `user_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(255) DEFAULT NULL,
  `last_access_time` datetime NOT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD1401A22E114D4C7` (`user`),
  KEY `FKD1401A22C8623394` (`user`),
  KEY `FKD1401A22BE681212` (`user`),
  CONSTRAINT `FKD1401A22BE681212` FOREIGN KEY (`user`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_session`
--

LOCK TABLES `user_session` WRITE;
/*!40000 ALTER TABLE `user_session` DISABLE KEYS */;
INSERT INTO `user_session` VALUES (161,'0:0:0:0:0:0:0:1%0','2012-10-27 09:32:24','971b8127-b5b3-41a7-8408-6716e0ce8aea',1),(162,'0:0:0:0:0:0:0:1%0','2012-10-27 09:32:44','8aaa49c5-cbf3-4d8c-806f-927456c8fdaa',1),(165,'0:0:0:0:0:0:0:1%0','2012-10-27 09:36:05','7edb473d-b17b-438d-a03a-babbe2c46434',4);
/*!40000 ALTER TABLE `user_session` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-27  9:36:36
