/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.30 : Database - student_info
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`student_info` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `student_info`;

/*Table structure for table `admin_user` */

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `adminName` varchar(50) NOT NULL,
  `adminPwd` varchar(50) NOT NULL,
  `lim` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`adminName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_user` */

insert  into `admin_user`(`adminName`,`adminPwd`,`lim`) values ('admin','981206',1);

/*Table structure for table `student_baseinfo` */

DROP TABLE IF EXISTS `student_baseinfo`;

CREATE TABLE `student_baseinfo` (
  `stuId` int(11) NOT NULL COMMENT '学生学号',
  `stuName` varchar(50) NOT NULL COMMENT '学生姓名',
  `stuSex` varchar(50) NOT NULL COMMENT '学生性别',
  `stuAge` int(11) NOT NULL COMMENT '学生年龄',
  `stuMaj` varchar(50) NOT NULL COMMENT '学生专业',
  `lim` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_baseinfo` */

insert  into `student_baseinfo`(`stuId`,`stuName`,`stuSex`,`stuAge`,`stuMaj`,`lim`) values (1,'周梦波','男',23,'计算机科学与技术',-1),(2,'小红','女',21,'计算机科学与技术',-1),(3,'小刘','男',21,'计算机科学与技术',-1),(4,'小王','男',22,'计算机科学与技术',-1),(5,'小余','男',21,'计算机科学与技术',-1),(6,'小罗','男',21,'计算机科学与技术',-1),(7,'小梅','女',22,'计算机科学与技术',-1),(8,'小明','男',20,'计算机科学与技术',-1),(9,'小方','男',21,'计算机科学与技术',-1),(10,'小蠢','男',21,'计算机科学与技术',-1),(11,'小马','男',22,'计算机科学与技术',-1);

/*Table structure for table `student_dipinfo` */

DROP TABLE IF EXISTS `student_dipinfo`;

CREATE TABLE `student_dipinfo` (
  `stuId` int(11) NOT NULL COMMENT '学生学号',
  `stuDipName` varchar(50) NOT NULL COMMENT '学生姓名',
  `stuDipName1` varchar(50) NOT NULL DEFAULT '无' COMMENT '学生获奖名称',
  `stuDipTim` date NOT NULL DEFAULT '0000-00-00' COMMENT '学生获奖时间',
  `stuDipPs` varchar(50) NOT NULL DEFAULT '无' COMMENT '学生获奖备注',
  PRIMARY KEY (`stuId`),
  CONSTRAINT `student_dipinfo_ibfk_1` FOREIGN KEY (`stuId`) REFERENCES `student_baseinfo` (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_dipinfo` */

insert  into `student_dipinfo`(`stuId`,`stuDipName`,`stuDipName1`,`stuDipTim`,`stuDipPs`) values (1,'周梦波','诺贝尔文学奖','2020-06-02','本校获得诺贝尔文学奖第一人'),(2,'小红','图灵奖','2020-05-22','无'),(4,'小王','图灵奖','2020-05-22','无'),(7,'小梅','金扫把奖','2020-06-07','好'),(10,'小蠢','东方食神奖','2020-06-08','实至名归'),(11,'小马','金马奖','2020-06-01','本校获得金马奖第一人！');

/*Table structure for table `student_familyinfo` */

DROP TABLE IF EXISTS `student_familyinfo`;

CREATE TABLE `student_familyinfo` (
  `stuId` int(11) NOT NULL COMMENT '学生学号',
  `stuFamName` varchar(50) NOT NULL COMMENT '学生姓名',
  `stuFamAdd` varchar(50) NOT NULL COMMENT '学生家庭地址',
  `stuFamNum` int(11) NOT NULL COMMENT '学生家庭人口',
  `stuFamTel` varchar(50) NOT NULL COMMENT '学生家庭电话',
  PRIMARY KEY (`stuId`),
  CONSTRAINT `student_familyinfo_ibfk_1` FOREIGN KEY (`stuId`) REFERENCES `student_baseinfo` (`stuId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_familyinfo` */

insert  into `student_familyinfo`(`stuId`,`stuFamName`,`stuFamAdd`,`stuFamNum`,`stuFamTel`) values (1,'周梦波','四川省成都市',4,'17621183530'),(2,'小红','四川省绵阳市',3,'15426648750'),(3,'小刘','四川省达州市',5,'15421158963'),(4,'小王','四川省宜宾市',4,'15896687454'),(5,'小余','重庆市',3,'13269963658'),(6,'小罗','云南省',4,'18965585232'),(7,'小梅','广西省桂林市',3,'14565214587'),(8,'小明','陕西省',4,'16654484587'),(9,'小方','上海市',4,'16523569658'),(10,'小蠢','河南省',4,'15845845896'),(11,'小马','河北省',3,'12356896589');

/*Table structure for table `student_worinfo` */

DROP TABLE IF EXISTS `student_worinfo`;

CREATE TABLE `student_worinfo` (
  `stuId` int(11) NOT NULL COMMENT '学生学号',
  `stuWorName` varchar(50) NOT NULL COMMENT '学生姓名',
  `stuWorNum` int(11) NOT NULL COMMENT '学生缺勤次数',
  `stuWorPun` varchar(50) NOT NULL COMMENT '学生缺勤处分',
  `stuWorPs` varchar(50) NOT NULL COMMENT '学生缺勤备注',
  PRIMARY KEY (`stuId`),
  CONSTRAINT `student_worinfo_ibfk_1` FOREIGN KEY (`stuId`) REFERENCES `student_baseinfo` (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_worinfo` */

insert  into `student_worinfo`(`stuId`,`stuWorName`,`stuWorNum`,`stuWorPun`,`stuWorPs`) values (1,'周梦波',0,'无','无'),(2,'小红',0,'无','无'),(3,'小刘',1,'无','无'),(4,'小王',5,'记过','缺勤次数较多'),(5,'小余',0,'无','无'),(6,'小罗',2,'无','无'),(7,'小梅',0,'无','无'),(8,'小明',0,'无','无'),(9,'小方',0,'无','无'),(10,'小蠢',10,'留校察看','缺勤次数很多，且毫无改进之意，建议留校察看'),(11,'小马',45,'退学','没来上过课，建议退学');

/*Table structure for table `teacher_user` */

DROP TABLE IF EXISTS `teacher_user`;

CREATE TABLE `teacher_user` (
  `teaName` varchar(50) NOT NULL COMMENT '教师编号',
  `teaPwd` varchar(50) NOT NULL COMMENT '教师密码',
  `lim` int(11) NOT NULL DEFAULT '0' COMMENT '教师权限',
  PRIMARY KEY (`teaName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher_user` */

insert  into `teacher_user`(`teaName`,`teaPwd`,`lim`) values ('teacher','981206',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
