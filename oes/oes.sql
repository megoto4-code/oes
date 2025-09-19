-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: oesdb
-- ------------------------------------------------------
-- Server version	5.5.22

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
-- Table structure for table `answersheet`
--

DROP TABLE IF EXISTS `answersheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answersheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registerId` int(11) NOT NULL,
  `questionId` int(11) NOT NULL,
  `answer` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answersheet`
--

LOCK TABLES `answersheet` WRITE;
/*!40000 ALTER TABLE `answersheet` DISABLE KEYS */;
INSERT INTO `answersheet` VALUES (1,1,77,'2'),(2,1,78,'1'),(3,1,79,'2'),(4,1,80,'4'),(5,1,81,''),(6,2,82,'3'),(7,2,83,''),(8,2,84,'2'),(9,3,77,'2'),(10,3,78,'1'),(11,3,79,'3'),(12,3,80,'4'),(13,3,81,''),(14,4,77,'3'),(15,4,78,'2'),(16,4,79,'3'),(17,4,80,'2'),(18,4,81,'5'),(19,5,77,'2'),(20,5,78,''),(21,5,79,'3'),(22,5,80,'4'),(23,5,81,''),(24,6,85,'4'),(25,6,86,'3'),(26,6,87,''),(27,6,88,'1'),(28,6,89,''),(29,6,90,'3'),(30,6,91,''),(31,6,92,'3'),(32,6,93,''),(33,7,85,'5'),(34,7,86,'1'),(35,7,87,''),(36,7,88,'2'),(37,7,89,'4'),(38,7,90,'1'),(39,7,91,'5'),(40,7,92,'1'),(41,7,93,'5'),(42,8,85,'3'),(43,8,86,'4'),(44,8,87,'2'),(45,8,88,'3'),(46,8,89,''),(47,8,90,''),(48,8,91,''),(49,8,92,'2'),(50,8,93,'5'),(51,9,85,''),(52,9,86,''),(53,9,87,''),(54,9,88,''),(55,9,89,''),(56,9,90,''),(57,9,91,''),(58,9,92,''),(59,9,93,''),(60,10,85,'5'),(61,10,86,'2'),(62,10,87,'2'),(63,10,88,'4'),(64,10,89,'1'),(65,10,90,'3'),(66,10,91,'1'),(67,10,92,'2'),(68,10,93,'5'),(69,11,85,'4'),(70,11,86,''),(71,11,87,'2'),(72,11,88,'1'),(73,11,89,''),(74,11,90,''),(75,11,91,'5'),(76,11,92,'3'),(77,11,93,''),(78,12,85,'4'),(79,12,86,'3'),(80,12,87,''),(81,12,88,''),(82,12,89,''),(83,12,90,'3'),(84,12,91,''),(85,12,92,'2'),(86,12,93,''),(87,13,85,''),(88,13,86,''),(89,13,87,''),(90,13,88,''),(91,13,89,''),(92,13,90,''),(93,13,91,''),(94,13,92,''),(95,13,93,''),(96,14,85,'4'),(97,14,86,'3'),(98,14,87,''),(99,14,88,''),(100,14,89,''),(101,14,90,'2'),(102,14,91,''),(103,14,92,'3'),(104,14,93,'3'),(105,15,94,'2'),(106,15,95,'3'),(107,15,96,'2'),(108,15,97,'3'),(109,15,98,'2'),(110,15,99,''),(111,15,100,'3');
/*!40000 ALTER TABLE `answersheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Reasoning'),(3,'English'),(4,'Aptitude'),(5,'General Awareness');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `time` mediumint(9) NOT NULL,
  `instructions` longtext,
  `status` tinyint(1) DEFAULT '0',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
INSERT INTO `exams` VALUES (3,'Test1',5,'It i a very tough Exam :)',1,NULL),(4,'Test2',6,'It is easy rdg',1,NULL),(5,'NEW Bank Exam',20,'Sep 30, 2011 - Download PDF ... Related. Book Download: Data Communications and Networking Edition 4th by Behrouz A. ForouzanIn \"E-Books\" ... Slide of Chapter 11 Data Link Control (HDLC) - Behrouz A Forouzan BookIn \"Networking\".',1,NULL),(6,'RRB',20,'1. Dear candidate, we thank you for responding to our advertisement for recruitment in the Indian Railways.\r\n2. The candidate should report at the centre of examination well in advance of reporting time. Reporting time and examination time are indicated in the call letter. The candidates reporting after 10 minutes of the start of examination shall not be admitted.\r\n3. Your admission to examination will be subject to your producing this call letter in original before the officials-in-charge of the examination centre.\r\n4. Please paste a photograph in the bottom portion of the call letter. Candidate with call letters without the photograph pasted will not be allowed to appear in the examination.\r\n5. Your candidature for this post is provisional and subject to your fulfilling the prescribed age, educational qualifications and other conditions as specified in our advertisement and will be verified during document verification.\r\n6. The candidates must fill up and darken the correct circles for their roll no., question booklet no. etc in answer sheet correctly. Wrong filling of roll no., question booklet no. and name etc will lead to disqualification. \r\n7. No request for change in date of examination and centre will be entertained.\r\n8. This call letter should be carefully retained by the candidates. Those candidates who are called for interview/verification of credentials/skill test will be required to produce this call letter also, failing which they will be disqualified.\r\n9. For SC/ST only : On production of this admit card you are entitled to travel by Rail in Second Class from your nearest Railway Station (mentioned on the reverse) to the place of written examination and back till the mentioned date. You should produce original caste certificate at the time of reservation/traveling.\r\n10. Any form of canvassing will disqualify the candidate.',1,'2016-02-07 06:21:42');
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` longtext NOT NULL,
  `option1` varchar(200) NOT NULL,
  `option2` varchar(200) NOT NULL,
  `option3` varchar(200) DEFAULT NULL,
  `option4` varchar(200) DEFAULT NULL,
  `option5` varchar(200) DEFAULT NULL,
  `answer` smallint(6) NOT NULL,
  `mark` smallint(6) NOT NULL,
  `negative` smallint(6) NOT NULL,
  `examId` int(11) NOT NULL,
  `category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Exam_FK_idx` (`examId`),
  KEY `Category_FK_idx` (`category`),
  CONSTRAINT `Category_FK` FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Exam_FK` FOREIGN KEY (`examId`) REFERENCES `exams` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (77,'This is the 1st reasoning question','cghhg','yf','ghgh','dgg','',2,1,25,3,1),(78,'rydghruryry','tr','yu','et','try','',1,1,25,3,1),(79,'reterfgetye','er','re','er','re','',3,1,25,3,3),(80,'rtrtree','rrt','ttr','tyte','rte','rye',4,1,25,3,3),(81,'reyegetgte','rt','tty','reb','bgs','ghd',3,1,25,3,3),(82,'uyvuygyu','ghhg','jhg','jggh','uyu','',2,1,25,4,1),(83,'yfyuf','a','b','c','d','',2,1,25,4,1),(84,'hh7u7h','ygf','highest railway station','longest railway station','Arjuna Award','dfj',3,1,25,4,5),(85,'wat kif yhi ygukjuyf  uyfh?','Bhatnagar Award','highest railway station','Third and Fourth stanza','First and Second stanza','rye',4,1,50,5,1),(86,'njjgvkh guuyv hgiugy oigugii j?','a','b','c','d','e',3,1,50,5,1),(87,'vugvuy iigu  gjggjuyub uuhjhv y?','Argentina, Australia, Belgium and South Africa','Chili, France and Japan','New Zealand, Norway, the former USSR, the UK and the USA','All of the above','None of these',2,1,50,5,1),(88,'what is this?','Only the first stanza','The whole song','Arjun','Tandu Muni','',1,1,50,5,3),(89,'hjfvj iuf  uyfuyf uyf?','jycgjgc','James B. Conant','unconfined groundwater','Descriptive and Statistical','',2,1,50,5,3),(90,'zdas liugyf jdj dkuyf h?','artesian','Performance','reb','wjhyf','',3,1,50,5,4),(91,'uydtyf iuf  iufuf uuyf ui?','U Thant, Kurt Waldheim, Dag Hammarskjoeld, Trygve Lie.','Insects','the north-west pacific region','Technological factor, service factor, self factor','9/21',5,1,50,5,4),(92,'ytyt uyyf iif7 6  f iytfy yuty uyf  uytdf?','jycgjgc','larger, less','prohibited by regulations of GATT','indexing, planning, control and implementation','',3,1,50,5,5),(93,'uyvuyf ufyug gcutc uyfuyfyuffo i uy f uyf uyf ufuyf o ufy uyf uyf uoyfg ouyfuuyff uyf?','Banjo','Volleyball','Chemistry in 1954 and Peace in 1962','Joshua Rumble','ghd',3,1,50,5,5),(94,'	\r\nGrand Central Terminal, Park Avenue, New York is the world\'s','largest railway station','highest railway station','longest railway station','None of the above','',1,1,50,6,1),(95,'Entomology is the science that studies','Behavior of human beings','Insects','The origin and history of technical and scientific terms','The formation of rocks','',2,1,50,6,3),(96,'	\r\nEritrea, which became the 182nd member of the UN in 1993, is in the continent of','Asia','Africa','Europe','Australia','',2,1,50,6,4),(97,'	\r\nGarampani sanctuary is located at','Junagarh, Gujarat','Diphu, Assam','Kohima, Nagaland','Gangtok, Sikkim','',2,1,50,6,3),(98,'For which of the following disciplines is Nobel Prize awarded?','Physics and Chemistry','Physiology or Medicine','Literature, Peace and Economics','All of the above','',4,1,50,6,1),(99,'	\r\nHitler party which came into power in 1933 is known as','Labour Party','Nazi Party','Ku-Klux-Klan','Democratic Party','',2,1,50,6,4),(100,'	\r\nDRDL stands for','Defence Research and Development Laboratary','Department of Research and Development Laboratory','Differential Research and Documentation Laboratary','None of the above','',1,1,50,6,5);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roll` varchar(45) NOT NULL,
  `name` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `dob` varchar(45) NOT NULL,
  `address` longtext,
  `examId` int(11) NOT NULL,
  `systemNo` varchar(45) NOT NULL,
  `uniqueId` varchar(100) NOT NULL,
  `examCompleted` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqueId_UNIQUE` (`uniqueId`),
  KEY `Fk_exam_idx` (`examId`),
  CONSTRAINT `Fk_exam` FOREIGN KEY (`examId`) REFERENCES `exams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` VALUES (1,'1001','sourav','male','7775569447','sdfbj@sadf.ci','09/09/1990','hvjbjgb',3,'C001','c19b5678-5cba-4a6a-8c74-8bde796e2790',1,'2016-02-02 16:12:43'),(2,'1016','Shibu Das','male','9965478988','dsf@gmail.com','12/09/1997','karimganj, tillabazar, pin: 788709',4,'C004','8f63979c-55ef-4640-8ec5-911e2c62a719',3,'2016-02-04 02:31:09'),(3,'1016','Shibu Das','male','9954237689','ertery@fgfh','12/12/1990','ufufkj',3,'C004','92e23b8e-60f7-45f3-bd78-5f17c8e27ba7',2,'2016-02-04 03:13:53'),(4,'1150','Kamal Kumar','male','7775569447','switch@gmail.com','09/09/1990','gdhtdt',3,'C003','c350d090-b582-4879-949b-d1d9c9ceec6d',1,'2016-02-04 03:25:52'),(5,'4360','Virat Kohli','male','7775569447','ertery@fgfh','09/09/1990','ryygff',3,'C010','40faa3ed-2120-4976-b14f-231d5b489eb0',1,'2016-02-04 04:55:48'),(6,'4365','Rohit Sarma','male','9958745899','switch@gmail.com','12/09/1997','iydtyv  ytf yf iyfu',5,'C011','a1994c99-d13d-4cb6-a2a4-267ac6b0485e',3,'2016-02-04 14:53:27'),(7,'1025','shaan','male','9954237689','switch@gmail.com','12/12/1990','gygcycggc',5,'C004','7c811c9b-d35f-4bbd-9de0-4efe046cb1c4',1,'2016-02-04 14:58:13'),(8,'4360','Manish Pandeeee','male','7775569447','ertery@fgfh','12/12/1990','kfutyyjxf',5,'C001','20f5d080-3f06-4547-bbc7-07dd65506775',5,'2016-02-04 15:01:07'),(9,'1001','Rahane','male','7775569447','dsf@gmail.com','09/09/1990','yjdftycr ur',5,'C004','9437a1e8-688e-4120-b93f-70c365b5822e',5,'2016-02-04 15:20:20'),(10,'1150','Rahul Das','male','9954179491','ddytdttetre@gdt.co','12/09/1998','jyrdjyr',5,'C004','037e57fc-0123-4aec-8de1-ee5a42e51e33',8,'2016-02-04 15:37:57'),(11,'436','Gogoi','male','7775569447','sdfbj@sadf.ci','09/09/1990','grssju',5,'C003','f71351bf-39eb-4647-ab65-479d55a71ed4',1,'2016-02-04 15:49:44'),(12,'1025','jhonn','male','7775560447','sdffbj@sadf.ci','09/09/1990','hnhn',5,'C001','d52fb782-e4c6-41f0-9c7a-fe8a4f8fcf77',1,'2016-02-04 15:53:45'),(13,'3450','Shikhar Dhawan','male','9978654542','dsf@gmail.com','20/12/1986','karimganj',5,'C003','a3ae8bb8-f295-4212-aacb-4e06f1319fd0',0,'2016-02-05 11:43:01'),(14,'4599','Lion King','male','8855995567','dsf@gmail.com','12/09/1923','tdhdtr',5,'C014','7d9f2f0a-51b2-4512-b658-1ced6df83010',2,'2016-02-07 06:00:57'),(15,'1222','sagar','male','4582269412','switch@gmail.com','12/08/2000','jiahg',6,'C020','1b14d3ee-050b-4942-9134-40408ff91eff',19,'2016-02-07 06:39:57');
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `property` varchar(45) NOT NULL,
  `value` longtext,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `property_UNIQUE` (`property`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,'appName','Online Examination System','2016-01-15 02:50:15'),(2,'appCopyright','Online Examination Message goes here','2016-01-15 02:52:02');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','password');
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

-- Dump completed on 2016-02-08 10:51:35
