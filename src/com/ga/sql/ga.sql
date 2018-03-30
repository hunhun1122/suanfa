/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.36 : Database - suanfa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`suanfa` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `suanfa`;

/*Table structure for table `questionbean` */

DROP TABLE IF EXISTS `questionbean`;

CREATE TABLE `questionbean` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `type` int(11) DEFAULT NULL COMMENT '题目类型 1-单选  2-填空 3-主观',
  `difficulty` double DEFAULT NULL COMMENT '难度系数 0.3-1之间',
  `pointId` int(11) DEFAULT NULL COMMENT '对应知识点id',
  `stem` varchar(100) DEFAULT NULL COMMENT '题干',
  `choice1` varchar(50) DEFAULT NULL COMMENT '选项a',
  `choice2` varchar(50) DEFAULT NULL COMMENT '选项b',
  `choice3` varchar(50) DEFAULT NULL COMMENT '选项c',
  `choice4` varchar(50) DEFAULT NULL COMMENT '选项D',
  `answer` varchar(50) DEFAULT NULL COMMENT '答案',
  `userId` int(11) DEFAULT NULL COMMENT '出题人id',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `userName` varchar(20) DEFAULT NULL COMMENT '出题人姓名',
  `knowledgeName` varchar(100) DEFAULT NULL COMMENT '知识点名称',
  `score` int(11) DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 MIN_ROWS=1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `questionbean` */

insert  into `questionbean`(`id`,`type`,`difficulty`,`pointId`,`stem`,`choice1`,`choice2`,`choice3`,`choice4`,`answer`,`userId`,`createTime`,`userName`,`knowledgeName`,`score`) values (1,1,0.3,1,'以下哪位是三国人士？','猪八戒','唐太宗','张飞','雍正','C',1,'2018-03-04','帅哥','历史三国',2),(2,1,0.3,2,'以下哪位是三国人士2？','孙悟空','李白','杜甫','曹操','D',1,'2018-03-04','帅哥','历史三国',2),(3,1,0.7,1,'以下哪位是三国人士3？','姜子牙','关羽','关晓彤','亚瑟','B',1,'2018-03-04','帅哥','历史三国',2),(4,1,0.3,1,'以下哪位是三国人士4？','华雄','安其拉','康熙','邓小平','A',1,'2018-03-04','帅哥','历史三国',2),(5,2,0.9,2,'关羽温酒斩了谁？？',NULL,NULL,NULL,NULL,'华雄',1,'2018-03-04','帅哥','历史填空',3),(6,2,0.5,1,'曹操和刘备在哪煮酒了？',NULL,NULL,NULL,NULL,'不知道',1,'2018-03-04','帅哥','历史填空',3),(7,3,0.6,2,'刘备手下大将s姓名：',NULL,NULL,NULL,NULL,'。。',1,'2018-03-04','帅哥','历史简答',3),(8,4,0.7,1,'曹操手下大将s姓名：','',NULL,NULL,NULL,'..',1,'2018-03-04','帅哥','历史简答',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
