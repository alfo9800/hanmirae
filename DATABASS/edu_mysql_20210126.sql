-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: edu
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.13-MariaDB

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
-- Table structure for table `tbl_attach`
--

DROP TABLE IF EXISTS `tbl_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_attach` (
  `save_file_name` varchar(255) NOT NULL COMMENT '첨부파일명 \n(검색 때문에 상단에 배치)\n',
  `real_file_name` varchar(255) DEFAULT NULL COMMENT '서버에 저장되는 한글명이 아닌 파일명\n',
  `bno` int(11) NOT NULL COMMENT '부모게시물의 일련번호 ',
  `reg_date` timestamp NULL DEFAULT current_timestamp() COMMENT '등록일시 ',
  PRIMARY KEY (`save_file_name`),
  KEY `fk_tbl_attach_tbl_board_idx` (`bno`),
  CONSTRAINT `fk_tbl_attach_tbl_board` FOREIGN KEY (`bno`) REFERENCES `tbl_board` (`bno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시물 첨부 파일 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_attach`
--

LOCK TABLES `tbl_attach` WRITE;
/*!40000 ALTER TABLE `tbl_attach` DISABLE KEYS */;
INSERT INTO `tbl_attach` VALUES ('3c7df778-13c8-4c96-984b-0a3c4e29f7ad.jpg','sea.jpg',27,'2021-01-22 02:30:02'),('c67cd28c-e0b8-4def-ba76-4acd3ba7f8e2.jpg','light.jpg',29,'2021-01-22 08:12:16'),('cf996f81-9ab3-4831-8018-5d9f783f5e7b.png','스크린샷(143).png',27,'2021-01-13 00:58:31'),('eb0db7c7-70e7-4d69-a293-0e148ef7ecff.png','스크린샷(107).png',27,'2021-01-13 00:58:31'),('f97bf5c2-592c-42eb-930e-28c2d6a97d01.png','gardenleaf (1).png',27,'2021-01-22 02:29:43');
/*!40000 ALTER TABLE `tbl_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_board`
--

DROP TABLE IF EXISTS `tbl_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_board` (
  `bno` int(11) NOT NULL AUTO_INCREMENT COMMENT '게시물 일련번호\nAI(Auto Incremental)-일련번호를 자동 증가 시킴. ',
  `board_type` varchar(45) DEFAULT NULL COMMENT '게시판 타입: tbl_board_type테이블의 값을 가져다 사용 ',
  `title` varchar(255) DEFAULT NULL COMMENT '게시물 제목\n',
  `content` text DEFAULT NULL COMMENT '게시물내용 ',
  `writer` varchar(45) DEFAULT NULL COMMENT '작성자 ',
  `view_count` int(11) DEFAULT 0 COMMENT '게시물 조회 수\n',
  `reply_count` int(11) DEFAULT 0 COMMENT '댓글 갯수\n',
  `reg_date` timestamp NULL DEFAULT current_timestamp() COMMENT '등록일시 ',
  `update_date` timestamp NULL DEFAULT current_timestamp() COMMENT '수정일시 ',
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COMMENT='게시물관리';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_board`
--

LOCK TABLES `tbl_board` WRITE;
/*!40000 ALTER TABLE `tbl_board` DISABLE KEYS */;
INSERT INTO `tbl_board` VALUES (1,'notice','1번째 게시물 입니다.','게시물 내용입니다.','admin',3,0,'2020-12-23 03:20:57','2020-12-23 03:20:57'),(3,'notice','3번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(4,'notice','4번째 게시물 입니다.','게시물 내용입니다.','admin',1,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(5,'notice','5번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(6,'notice','6번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(7,'notice','7번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(8,'notice','8번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(9,'notice','9번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(10,'notice','10번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(11,'notice','11번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(12,'notice','12번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(13,'notice','13번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(14,'notice','14번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(15,'notice','15번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(16,'notice','16번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(17,'notice','17번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(18,'notice','18번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(19,'notice','19번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(20,'notice','20번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(21,'notice','21번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(22,'notice','22번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(23,'notice','23번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(24,'notice','24번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(25,'notice','25번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(26,'notice','26번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:17','2020-12-23 03:47:17'),(27,'notice','첨부 수정 없이 테스트 27번째 게시물 입니다.','게시물 내용입니다.','admin',208,5,'2020-12-23 03:47:18','2021-01-22 02:30:02'),(29,'notice ','29번째 게시물 입니다.','게시물 내용입니다.','admin',28,1,'2020-12-23 03:47:18','2021-01-25 03:14:11'),(30,'notice','30번째 게시물 입니다.','게시물 내용입니다.','admin',7,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(31,'notice','31번째 게시물 입니다.','게시물 내용입니다.','admin',1,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(32,'notice','32번째 게시물 입니다.','게시물 내용입니다.','admin',2,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(33,'notice','33번째 게시물 입니다.','게시물 내용입니다.','admin',9,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(34,'notice','34번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(35,'notice','35번째 게시물 입니다.','게시물 내용입니다.','admin',1,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(36,'notice','36번째 게시물 입니다.','게시물 내용입니다.','admin',4,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(37,'notice','37번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(38,'notice','38번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(39,'notice','39번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(40,'notice','40번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(41,'notice','41번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(42,'notice','42번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(43,'notice','43번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(44,'notice','44번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(45,'notice','45번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(46,'notice','46번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(47,'notice','47번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(48,'notice','48번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(49,'notice','49번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(50,'notice','50번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(51,'notice','51번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(53,'notice','53번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(54,'notice','54번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(55,'notice','55번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(56,'notice','56번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(57,'notice','57번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(58,'notice','58번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(59,'notice','59번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(60,'notice','60번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(61,'notice','61번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(62,'notice','62번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(63,'notice','63번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(64,'notice','64번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(65,'notice','65번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(66,'notice','66번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(67,'notice','67번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(68,'notice','68번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(69,'notice','69번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(70,'notice','70번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(71,'notice','71번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(72,'notice','72번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(73,'notice','73번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(74,'notice','74번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(75,'notice','75번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(76,'notice','76번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(77,'notice','77번째 수정 테스트 입니다.','<p><span style=\"font-family: \'Comic Sans MS\';\">Hola!<br>Encantada! soy mirae. <br><span style=\"font-family: \'Comic Sans MS\';\"><span style=\"font-family: \'Comic Sans MS\';\">soy de soul.<br>que tal?<br>me muy bien.<br>uno dos tres cautro cinco seis siete ocho nueve diez<br>Hasta luego~<br></span></span></span></p>','admin',7,0,'2020-12-23 03:47:18','2020-12-29 07:20:36'),(78,'notice','78번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(79,'notice','79번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(80,'notice','80번째 게시물 입니다.','게시물 내용입니다.','admin',1,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(81,'notice','81번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(82,'notice','82번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(83,'notice','83번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(84,'notice','84번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(85,'notice','85번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(86,'notice','86번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(87,'notice','87번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(88,'notice','88번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(89,'notice','89번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(90,'notice','90번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(91,'notice','91번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(92,'notice','92번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(93,'notice','93번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(94,'notice','94번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(95,'notice','95번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(96,'notice','96번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(97,'notice','97번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(98,'notice','98번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(99,'notice','99번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(100,'notice','100번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(101,'notice','101번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(102,'notice','102번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(103,'notice','103번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(104,'notice','104번째 게시물 입니다.','게시물 내용입니다.','admin',1,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(105,'notice','105번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(106,'notice','106번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(107,'notice','107번째 게시물 입니다.','게시물 내용입니다.','admin',0,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(108,'galllery','108번째 게시물 입니다.','게시물 내용입니다.','admin',14,0,'2020-12-23 03:47:18','2021-01-25 03:03:07'),(109,'notice','109번째 게시물 입니다.','게시물 내용입니다.','admin',3,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(110,'notice','110번째 게시물 입니다.','게시물 내용입니다.','admin',4,0,'2020-12-23 03:47:18','2020-12-23 03:47:18'),(123,'gallery','첨부파일이 안보임. ghdn testdddddd','<p>파일이 안보임.testxzczxczc<br></p>','미래',27,0,'2021-01-22 02:27:14','2021-01-25 02:26:42'),(124,'test','테ㅡ트','<p>테스트<br></p>','미래',0,0,'2021-01-26 01:11:31','2021-01-26 01:11:31');
/*!40000 ALTER TABLE `tbl_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_board_type`
--

DROP TABLE IF EXISTS `tbl_board_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_board_type` (
  `board_type` varchar(45) NOT NULL COMMENT '게시판 타입: notice, gallery. Q&A 등등... ',
  `board_name` varchar(45) DEFAULT NULL COMMENT '게시판 이름(한글로 된) ',
  `board_sun` int(11) DEFAULT NULL COMMENT '게시판 출력 순서 ',
  PRIMARY KEY (`board_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시판 생성 테이블 -> (=게시판 타입 생성)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_board_type`
--

LOCK TABLES `tbl_board_type` WRITE;
/*!40000 ALTER TABLE `tbl_board_type` DISABLE KEYS */;
INSERT INTO `tbl_board_type` VALUES ('galllery','갤러리',2),('notice ','이벤트',1),('test','테스트게시판',3),('test2','테스트2',4);
/*!40000 ALTER TABLE `tbl_board_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_member`
--

DROP TABLE IF EXISTS `tbl_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_member` (
  `user_id` varchar(45) NOT NULL COMMENT '회원아이디 ',
  `user_pw` varchar(255) DEFAULT NULL COMMENT '회원비밀번호 \n',
  `user_name` varchar(255) DEFAULT NULL COMMENT '회원이름 ',
  `email` varchar(255) DEFAULT NULL COMMENT '회원 이메일 ',
  `point` int(11) DEFAULT 0 COMMENT '회원 적립포인트 ',
  `enabled` int(1) DEFAULT 1 COMMENT '[로그인 인증 여부]\n활동가능한 회원여부\n(1은 true:로그인 가능)\n(0은 false:로그인 불가능)\n',
  `levels` varchar(45) DEFAULT 'ROLE_USER' COMMENT '[로그인 권한 여부]\n<2가지레벨>\nROLE_ADMIN(관리자)\nROLE_USER(사용자)',
  `reg_date` timestamp NULL DEFAULT current_timestamp() COMMENT '등록일자.\n(1970년부터 초단위로 현재까지 계산한 값)',
  `update_date` timestamp NULL DEFAULT current_timestamp() COMMENT '수정일',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='회원관리';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_member`
--

LOCK TABLES `tbl_member` WRITE;
/*!40000 ALTER TABLE `tbl_member` DISABLE KEYS */;
INSERT INTO `tbl_member` VALUES ('admin','$2a$10$kIqR/PTloYan/MRNiEsy6uYO6OCHVmAKR4kflVKQkJ345nqTiuGeO','최고관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 03:03:05','2021-01-22 05:37:11'),('dummy_1','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2021-01-12 07:32:56'),('dummy_10','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_100','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_101','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_102','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_103','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_104','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_105','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_106','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_107','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_108','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_109','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_11','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_110','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_12','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_13','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_14','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_15','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_16','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_17','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_18','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_19','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_2','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_20','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_21','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_22','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_23','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_24','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_25','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_26','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_27','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_28','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_29','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_3','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_30','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_31','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_32','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_33','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_34','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_35','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_36','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_37','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_38','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_39','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_4','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_40','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_41','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_42','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_43','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_44','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_45','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_46','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_47','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_48','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_49','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_5','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_50','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_51','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_52','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_53','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_54','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_55','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_56','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_57','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_58','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_59','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_6','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_60','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_61','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_62','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_63','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_64','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_65','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_66','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_67','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_68','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_69','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_7','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_70','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_71','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_72','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_73','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_74','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_75','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_76','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_77','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_78','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_79','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_8','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_80','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_81','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_82','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_83','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_84','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_85','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_86','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_87','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_88','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_89','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_9','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_90','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_91','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_92','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_93','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_94','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_95','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_96','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_97','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_98','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('dummy_99','$2a$10$lf9JOjNzgfrfyniYFdFUm.0B1KdxZyWVVRwDXZwJ3Z/bqJpzS1WAK','관리자','admin@abc.com',0,1,'ROLE_ADMIN','2020-12-23 06:01:50','2020-12-23 06:01:50'),('user01','$2a$10$Ua.E60yqA2XmDEvAnkeCNeJ2ihRtk9nDxTakPYNpRK/zXc.dlqLda','일반사용자','user@user.com',0,1,'ROLE_USER','2021-01-12 07:27:41','2021-01-12 07:27:41');
/*!40000 ALTER TABLE `tbl_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reply`
--

DROP TABLE IF EXISTS `tbl_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reply` (
  `rno` int(11) NOT NULL AUTO_INCREMENT COMMENT '댓글의 일련번호 \n(검색 때문에 상단에 배치)',
  `bno` int(11) NOT NULL COMMENT '부모게시물의 일련번호 ',
  `reply_text` varchar(1000) DEFAULT NULL COMMENT '댓글 내용\n',
  `replyer` varchar(45) DEFAULT NULL COMMENT '작성자\n',
  `reg_date` timestamp NULL DEFAULT current_timestamp() COMMENT '등록일시\n',
  `update_date` timestamp NULL DEFAULT current_timestamp() COMMENT '수정일시\n',
  PRIMARY KEY (`rno`),
  KEY `fk_tbl_reply_tbl_board1_idx` (`bno`),
  CONSTRAINT `fk_tbl_reply_tbl_board1` FOREIGN KEY (`bno`) REFERENCES `tbl_board` (`bno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='게시물 댓글 관리';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reply`
--

LOCK TABLES `tbl_reply` WRITE;
/*!40000 ALTER TABLE `tbl_reply` DISABLE KEYS */;
INSERT INTO `tbl_reply` VALUES (30,29,'soy estudiante','ff','2021-01-07 05:27:05','2021-01-14 02:43:41'),(33,27,'Hola','미래','2021-01-07 05:33:07','2021-01-07 06:10:20'),(34,27,'encantada','미래','2021-01-07 05:35:55','2021-01-07 05:35:55'),(35,27,'ff','ff','2021-01-14 00:38:05','2021-01-14 00:38:05'),(36,27,'ff','ff','2021-01-14 00:38:11','2021-01-14 02:46:30'),(37,27,'나나','한미래','2021-01-14 01:14:21','2021-01-14 01:14:21');
/*!40000 ALTER TABLE `tbl_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'edu'
--
/*!50003 DROP PROCEDURE IF EXISTS `dummyBoard` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dummyBoard`(IN p_loop int)
BEGIN
	declare cnt int default 2;
    delete from tbl_board where bno > 1;
    while cnt <= p_loop do
    INSERT INTO tbl_board (bno,title,content,writer)
    VALUES
    (cnt, concat(cnt,'번째 게시물 입니다.'), '게시물 내용입니다.', 'admin');	
    set cnt = cnt+1;
    end while;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dummyMember` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dummyMember`(IN p_loop int)
BEGIN
	declare cnt int default 1; #반복문 변수선언
    delete from tbl_member where user_id like 'dummy%';
    while cnt <= p_loop do
		INSERT INTO tbl_member (user_id,user_pw,user_name,email,point,enabled,levels) 
		VALUES
		(concat('dummy_',cnt), '1234', '관리자', 'admin@abc.com', '0', '1', 'ROLE_ADMIN');
		set cnt = cnt+1;
    end while;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-26 15:56:07
