-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_equipment
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `equ_user`
--

DROP TABLE IF EXISTS `equ_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_user` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `userName` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `newpassword` varchar(255) DEFAULT NULL,
  `roleName` varchar(20) DEFAULT '用户' COMMENT '管理员、使用者、维护者',
  `trueName` varchar(20) DEFAULT NULL COMMENT '用户名字',
  `deptName` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `createId` int(5) DEFAULT NULL COMMENT '创建人ID',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `repwdTime` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `activeCode` varchar(255) DEFAULT NULL COMMENT '激活验证码',
  `status` int(11) DEFAULT NULL COMMENT '0 未激活 1 已激活 2 此前注册的用户，特殊处理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equ_user`
--

LOCK TABLES `equ_user` WRITE;
/*!40000 ALTER TABLE `equ_user` DISABLE KEYS */;
INSERT INTO `equ_user` VALUES (2,'aa','aa',NULL,'维修者','小张','维修部',1,'2017-05-03 09:58:26',NULL,'18722348797',NULL,2),(6,'admin','666',NULL,'管理员','赵总','行政部',1,'2017-05-05 15:12:28',NULL,'18723400983',NULL,2),(7,'bb','bb',NULL,'用户','刘师傅','仓储部',6,'2017-05-05 15:13:56',NULL,'18766664444',NULL,2),(8,'S','S',NULL,'Admin','S',NULL,7,NULL,NULL,NULL,NULL,2),(9,'asdfasdf','asdfasdfsad',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:15:56',NULL,NULL,NULL,2),(10,'asdfasdfa','sdfasdfsa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:16:42',NULL,NULL,NULL,2),(11,'asdfasdfa','sdfasdfas',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:16:59',NULL,NULL,NULL,2),(12,'sdfasda','asdfdasdfsa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:23:51',NULL,NULL,NULL,2),(13,'asdfasdf','sdfasdfa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:23:58',NULL,NULL,NULL,2),(14,'dd','dd',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:25:42',NULL,NULL,NULL,2),(15,'aaa','aaaaa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:28:08',NULL,NULL,NULL,2),(16,'sdasdfasd','asdfdsafa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:28:34',NULL,NULL,NULL,2),(17,'safasd','asdfsasd',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:29:36',NULL,NULL,NULL,2),(18,'sasas','ASDas',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:31:14',NULL,NULL,NULL,2),(19,'SDSAD','ASD',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:31:30',NULL,NULL,NULL,2),(20,'asdfasdf','asdfasdfsa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:35:36',NULL,NULL,NULL,2),(21,'asdfasdfad','asdfasdfsdf',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:35:44',NULL,NULL,NULL,2),(22,'asdfasf','asdfsasadfa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:47:06',NULL,NULL,NULL,2),(23,'asdfsafs','sadfsadf',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:47:12',NULL,NULL,NULL,2),(27,'qqqq','qqqqq',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:51:49',NULL,NULL,NULL,2),(28,'qqqq','qqq',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:51:58',NULL,NULL,NULL,2),(29,'sdfasda','asdfasdf',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:57:09',NULL,NULL,NULL,2),(30,'asdfasdfas','asdfadsfsdfsa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 19:12:31',NULL,NULL,NULL,2),(31,'asdfasdfsadf','asdfasdfsaf',NULL,'用户',NULL,NULL,NULL,'2017-08-20 19:12:36',NULL,NULL,NULL,2),(35,'123@1231','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-20 21:36:16',NULL,NULL,NULL,2),(36,'123@1231','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-20 21:38:40',NULL,NULL,NULL,2),(37,'123@1231','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-20 21:41:07',NULL,NULL,NULL,2),(38,'123@124','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-20 21:51:48',NULL,NULL,NULL,2),(44,'345@345','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-21 07:27:59',NULL,NULL,NULL,2),(45,'123@123','123','12','鐢ㄦ埛',NULL,NULL,NULL,'2017-08-21 07:28:29','2017-09-20 17:34:57',NULL,NULL,2),(46,'123@123','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-21 08:26:24',NULL,NULL,NULL,2),(47,'guest','guest',NULL,'guest',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),(48,'123@123','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-21 09:40:03',NULL,NULL,NULL,2),(49,'123@123','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-21 09:40:10',NULL,NULL,NULL,2),(50,'sdasdfasd','asdfdsafa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:28:34',NULL,NULL,NULL,2),(51,'safasd','asdfsasd',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:29:36',NULL,NULL,NULL,2),(52,'sasas','ASDas',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:31:14',NULL,NULL,NULL,2),(53,'SDSAD','ASD',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:31:30',NULL,NULL,NULL,2),(54,'asdfasdf','asdfasdfsa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:35:36',NULL,NULL,NULL,2),(55,'asdfasdfad','asdfasdfsdf',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:35:44',NULL,NULL,NULL,2),(56,'asdfasf','asdfsasadfa',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:47:06',NULL,NULL,NULL,2),(119,'qqw','qqq',NULL,'用户',NULL,NULL,NULL,'2017-08-20 18:51:33',NULL,NULL,NULL,2),(120,'456@456','456',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-23 00:51:25',NULL,NULL,NULL,2),(121,'456@456','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-23 00:52:43',NULL,NULL,NULL,2),(122,'234@234','234',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-08-23 00:56:22',NULL,NULL,NULL,2),(136,'2342@123','123',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-09-20 13:17:07',NULL,NULL,'a75cbe81fb7d0a3586957ac7385df63c',2),(137,'234234@12','1',NULL,'鐢ㄦ埛',NULL,NULL,NULL,'2017-09-20 13:17:39',NULL,NULL,'8b471aade4468c4c7a886d6494e87e9c',2),(142,'ewwqe@23','sdf',NULL,'user',NULL,NULL,NULL,'2017-09-20 17:24:41',NULL,NULL,'741505899481638f615058994816383c1505899481638f21505899481638fb15058994816383a1505899481638d815058994816382615058994816389b1505899481638a41505899481638f715058994816383a1505899481638be15058994816387015058994816385a1505899481638b91505899481638',2),(149,'wangcheng2234@163.com','163',NULL,'user',NULL,NULL,NULL,'2017-09-20 21:07:27',NULL,NULL,'ff6fda913f0d3a500469ec3d4bcb83711505912847881',0),(153,'877217445@qq.com','2234','2234','user',NULL,NULL,NULL,'2017-09-20 21:19:33','2017-09-20 21:20:58',NULL,'6b67e01c8c67f67a6d1fdbe0dcb34b101505913669953',1),(154,'ningyunfa@163.com','123456',NULL,'用户','ningyunfa123',NULL,NULL,'2018-07-13 16:02:10',NULL,NULL,NULL,NULL),(155,NULL,NULL,NULL,'用户',NULL,NULL,NULL,'2018-07-13 16:02:10',NULL,NULL,NULL,NULL),(156,NULL,NULL,NULL,'用户',NULL,NULL,NULL,'2018-07-13 16:04:31',NULL,NULL,NULL,NULL),(157,NULL,NULL,NULL,'用户',NULL,NULL,NULL,'2018-07-13 16:45:09',NULL,NULL,NULL,NULL),(158,NULL,NULL,NULL,'用户',NULL,NULL,NULL,'2018-07-13 20:55:53',NULL,NULL,NULL,NULL),(159,'logback@outlook.com','123456',NULL,'用户','logback',NULL,NULL,'2018-07-13 20:55:53',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `equ_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_type`
--

DROP TABLE IF EXISTS `log_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(10) NOT NULL,
  `logshow` varchar(50) NOT NULL,
  `status` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_type`
--

LOCK TABLES `log_type` WRITE;
/*!40000 ALTER TABLE `log_type` DISABLE KEYS */;
INSERT INTO `log_type` VALUES (1,1,'testtt',1),(2,1,'eee',1);
/*!40000 ALTER TABLE `log_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_audit`
--

DROP TABLE IF EXISTS `product_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `username` varchar(10) NOT NULL,
  `auditstatus` int(4) NOT NULL,
  `productname` varchar(20) NOT NULL,
  `createtime` varchar(20) DEFAULT NULL,
  `updatetime` varchar(20) DEFAULT NULL,
  `reason` varchar(140) DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `applytime` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_audit`
--

LOCK TABLES `product_audit` WRITE;
/*!40000 ALTER TABLE `product_audit` DISABLE KEYS */;
INSERT INTO `product_audit` VALUES (2,45,1,'123@123',1,'测试','2018-06-21 16:44:47','2018-06-21 16:44:47','',1,30),(3,45,2,'123@123',0,'ewew','2018-06-21 16:44:47','2018-06-21 16:44:47','test',1,30);
/*!40000 ALTER TABLE `product_audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_interface`
--

DROP TABLE IF EXISTS `product_interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productid` int(11) NOT NULL,
  `interfacename` varchar(50) NOT NULL,
  `interfacepath` varchar(50) NOT NULL,
  `interfaceparam` varchar(100) NOT NULL,
  `interfaceresponse` varchar(1000) DEFAULT NULL,
  `status` int(4) NOT NULL,
  `requeststype` varchar(10) NOT NULL,
  `createtime` varchar(20) DEFAULT NULL,
  `paramexam` varchar(1000) DEFAULT 'test',
  `updatetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_interface`
--

LOCK TABLES `product_interface` WRITE;
/*!40000 ALTER TABLE `product_interface` DISABLE KEYS */;
INSERT INTO `product_interface` VALUES (1,1,'createtes','/sgcserver/order/create','bduss:,devicetype:1,cellphone:13162236515','{“errno”:0,\"msg\":\"success\",\"data\":{}}',1,'GET','2018-06-08 19:47:27','test','2018-07-08 17:15:30'),(2,11,'sdfa','dfa','dfa','dfa',1,'dfa','2018-06-09 14:51:21','test',NULL),(3,2,'create1','/trade/order/create','deviceType:1,service:iPhone','{\"errno\":0,\"msg\";“success”,\"data\":{}}',1,'POST','2018-06-09 14:56:52','test',NULL),(4,3,'create','/trade/order/create2','deviceType:1,service:iPhone','{\"errno\":0,\"msg\";“success”,\"data\":{}}',1,'POST','2018-06-09 14:59:25','test',NULL),(5,4,'create3','/trade/order/create2','deviceType:1,service:iPhone','{\"errno\":0,\"msg\";“success”,\"data\":{}}',1,'POST','2018-06-09 15:01:31','test',NULL),(6,6,'create4','/trade/order/create4','deviceType:1,service:iPhone','{\"errno\":0,\"msg\";“success”,\"data\":{}}',1,'POST','2018-06-09 15:06:25','test',NULL),(7,7,'create7','/trade/order/create4','deviceType:1,service:iPhone','{\"errno\":0,\"msg\";“success”,\"data\":{}}',1,'POST','2018-06-09 15:08:59','test',NULL),(8,8,'create8','/trade/order/create4','deviceType:1,service:iPhone','{\"errno\":0,\"msg\";“success”,\"data\":{}}',1,'POST','2018-06-09 15:17:44','test',NULL),(9,23,'paynotify','/trade/buy/paynotify','test','test',1,'post','2018-06-09 15:24:01','test',NULL),(10,5,'orderrefu','test','test','test',1,'test','2018-06-09 15:28:22','test',NULL),(11,3,'buy','/sgcserver/order/buy','test','{\"errno\":0,\"msg\";“success”,\"data\":{}}',1,'GET','2018-06-09 16:46:12','test',NULL),(12,33222,'4fdfa','dfadf','adfadsf','adsfadsf',1,'sdfaf','2018-06-17 15:06:47',NULL,'2018-06-17 15:06:47'),(13,1,'buy','','','',1,'POST','2018-06-17 15:28:48','','2018-06-17 15:28:48'),(14,1,'buy123','/trade/buy/notify','bduss,service,orderId,money','{\"errno\":0,\"msg\":\"success\",\"data\":{}}',1,'GET','2018-06-17 15:31:02','rrrrr','2018-06-17 15:31:02'),(15,1,'tradecenter','/trade/buy/notify','bduss,service,orderId,money','{\"errno\":0,\"msg\":\"success\",\"data\":{}}',1,'POST','2018-06-17 15:32:42','rrrrr','2018-06-17 15:32:42'),(16,1,'test321','/trade/buy/notify','bduss,service,orderId,money','{\"errno\":0,\"msg\":\"success\",\"data\":{}}',1,'POST','2018-06-17 15:40:41','test','2018-06-17 15:40:41'),(17,1,'23123123','/trade/buy/notify','bduss,service,orderId,money','{\"errno\":0,\"msg\":\"success\",\"data\":{}}',1,'POST','2018-06-17 15:42:07','rrrrr','2018-06-17 15:42:07'),(18,1,'新增','/trade/buy/notify','bduss,service,orderId,money','{\"errno\":0,\"msg\":\"success\",\"data\":{}}',1,'POST','2018-06-17 16:37:18','rrrrr','2018-06-17 16:37:18'),(19,1,'buybuy','/trade/buy/notify','bduss,service,orderId,money','{\"errno\":0,\"msg\":\"success\",\"data\":{}}',1,'GET','2018-06-20 15:55:20','rrrrr','2018-06-20 15:55:20'),(20,1,'createtest123','/trade/buy/notify','bduss,service,orderId,money','{\"errno\":0,\"msg\":\"success\",\"data\":{}}',1,'GET','2018-07-08 17:15:09','fffe','2018-07-08 17:15:09'),(21,1,'createtest1234','/trade/buy/notify','bduss,service,orderId,money','{\"errno\":0,\"msg\":\"success\",\"data\":{}}',1,'GET','2018-07-08 17:15:20','fffe','2018-07-08 17:15:20');
/*!40000 ALTER TABLE `product_interface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_line`
--

DROP TABLE IF EXISTS `product_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productname` varchar(40) NOT NULL,
  `userid` int(11) NOT NULL,
  `username` varchar(10) NOT NULL,
  `status` int(4) NOT NULL,
  `createtime` varchar(20) DEFAULT NULL,
  `parentmodule` varchar(20) NOT NULL,
  `productpath` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_line`
--

LOCK TABLES `product_line` WRITE;
/*!40000 ALTER TABLE `product_line` DISABLE KEYS */;
INSERT INTO `product_line` VALUES (1,'测试',10011,'sfasgfaf',1,'2018-5-12','子模块','baidu/baidunuomi/trade-refund'),(2,'ewew',45,'123@123',1,'2018-06-07 21:28:40','','');
/*!40000 ALTER TABLE `product_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_user`
--

DROP TABLE IF EXISTS `product_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `username` varchar(10) NOT NULL,
  `status` int(4) NOT NULL,
  `productname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_user`
--

LOCK TABLES `product_user` WRITE;
/*!40000 ALTER TABLE `product_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_company`
--

DROP TABLE IF EXISTS `user_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_company` (
  `user_id` int(11) unsigned NOT NULL,
  `cpy_id` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0' COMMENT '投递状态 0 未投递 1已投 2 没兴趣'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_company`
--

LOCK TABLES `user_company` WRITE;
/*!40000 ALTER TABLE `user_company` DISABLE KEYS */;
INSERT INTO `user_company` VALUES (2,1,0),(6,6,0),(7,7,0),(8,1,0),(2,6,0),(6,7,0),(7,1,1),(8,6,1),(2,7,1),(6,19,1),(7,20,1),(8,21,0),(2,23,0),(6,24,0),(7,25,0),(8,26,1),(2,27,0),(7,28,0),(6,1,1),(8,6,1),(2,7,0),(6,0,0),(0,0,0),(10,1,0),(7,6,0),(7,19,0),(7,24,0),(7,22,0),(47,1,0),(47,19,1),(8,7,2),(8,59,2),(8,19,2),(8,20,2),(8,22,2),(8,23,2),(8,24,2),(8,56,2),(7,21,2),(7,23,2),(7,56,2),(7,58,2),(7,57,2),(7,59,2),(7,61,2),(7,68,0),(7,60,2),(8,25,2),(8,69,0),(8,68,2),(8,60,2),(8,63,2),(8,65,2),(8,67,2),(8,66,2),(8,71,0),(8,64,1),(45,72,1),(45,73,0),(45,19,2),(45,20,1),(45,74,0),(45,75,1),(45,76,1),(45,77,1),(45,78,1),(7,79,0),(7,80,0),(45,87,0),(35,19,1),(45,59,1),(45,21,0),(45,22,1),(45,23,0),(45,24,1),(45,25,0),(45,88,0),(45,89,0),(45,90,0),(45,91,0),(45,92,0),(45,26,2),(45,60,2),(45,56,2),(45,63,2),(45,64,1),(45,65,0),(45,85,1),(45,93,0),(45,94,0),(45,95,1),(45,96,1),(45,97,0),(45,66,0),(45,67,1),(45,71,1),(45,69,0),(45,68,1),(45,83,1),(45,86,0),(45,81,1),(45,98,0),(45,82,2),(45,21,2),(45,21,2),(45,20,2),(45,77,2),(45,84,2),(45,21,2),(45,21,2),(45,20,2),(45,21,2),(45,21,2),(45,21,2),(45,21,2),(45,21,2),(45,21,2),(45,20,2),(45,21,2),(45,23,2),(45,23,2),(45,20,2),(45,20,2),(45,77,2),(45,20,2),(45,20,2),(45,20,2),(45,25,2),(45,90,2),(45,89,2),(45,88,2),(45,92,2),(45,95,2),(45,98,2);
/*!40000 ALTER TABLE `user_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vps`
--

DROP TABLE IF EXISTS `vps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `port` int(10) NOT NULL,
  `ip` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL,
  `use_status` tinyint(4) NOT NULL DEFAULT '1',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `default` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vps`
--

LOCK TABLES `vps` WRITE;
/*!40000 ALTER TABLE `vps` DISABLE KEYS */;
INSERT INTO `vps` VALUES (43,8989,'0.0.0.0','password0',2,0,1531910340006,1531978833376,1),(44,9001,'0.0.0.0','password1',2,0,1531910340027,1531981133616,1),(45,9002,'0.0.0.0','password2',2,0,1531910340038,1531987989286,1),(46,9003,'0.0.0.0','password3',2,0,1531910340044,1532004469332,1),(47,9004,'0.0.0.0','password4',2,0,1531910340048,1532004644725,1);
/*!40000 ALTER TABLE `vps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vps_record`
--

DROP TABLE IF EXISTS `vps_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vps_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `port` int(10) NOT NULL,
  `ip` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `use_time` int(10) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `vps_id` int(10) NOT NULL,
  `use_amount` int(10) NOT NULL,
  `cert_code` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vps_record`
--

LOCK TABLES `vps_record` WRITE;
/*!40000 ALTER TABLE `vps_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `vps_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vps_type`
--

DROP TABLE IF EXISTS `vps_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vps_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(10) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vps_type`
--

LOCK TABLES `vps_type` WRITE;
/*!40000 ALTER TABLE `vps_type` DISABLE KEYS */;
INSERT INTO `vps_type` VALUES (2,300,1,0,1531623806953,NULL);
/*!40000 ALTER TABLE `vps_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-19 21:15:58
