/*
SQLyog Ultimate v8.32 
MySQL - 5.7.22-log : Database - layui
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`layui` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `layui`;

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `menu_id` bigint(20) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_title` varchar(50) DEFAULT NULL,
  `menu_type` int(11) DEFAULT NULL,
  `menu_sort` int(11) DEFAULT NULL,
  `menu_href` varchar(255) DEFAULT NULL,
  `menu_icon` varchar(50) DEFAULT NULL,
  `permissions` varchar(50) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_menu` */

LOCK TABLES `t_menu` WRITE;

insert  into `t_menu`(`menu_id`,`parent_id`,`menu_name`,`menu_title`,`menu_type`,`menu_sort`,`menu_href`,`menu_icon`,`permissions`,`deleted`,`created_by`,`created_time`,`modified_by`,`modified_time`) values (1,0,'system','系统管理',0,1,'javascript:;','layui-icon-component',NULL,0,NULL,NULL,NULL,NULL),(2,1,'role','角色管理',1,1,'/system/role',NULL,'system:role:list',0,NULL,NULL,NULL,NULL),(3,2,'role','角色新增',2,1,NULL,NULL,'system:role:add',0,NULL,NULL,NULL,NULL),(4,2,'role','角色编辑',2,2,NULL,NULL,'system:role:edit',0,NULL,NULL,NULL,NULL),(5,2,'role','角色删除',2,3,NULL,NULL,'system:role:del',0,NULL,NULL,NULL,NULL),(6,1,'menu','菜单管理',1,2,'/system/menu',NULL,'system:menu:list',0,NULL,NULL,NULL,NULL),(7,6,'menu','菜单新增',2,1,NULL,NULL,'system:menu:add',0,NULL,NULL,NULL,NULL),(8,6,'menu','菜单编辑',2,2,NULL,NULL,'system:menu:edit',0,NULL,NULL,NULL,NULL),(9,6,'menu','菜单删除',2,3,NULL,NULL,'system:menu:del',0,NULL,NULL,NULL,NULL),(10,1,'dict','字典管理',1,3,'/system/dict',NULL,'system:dict:list',0,NULL,NULL,NULL,NULL),(11,10,'dict','字典新增',2,1,NULL,NULL,'system:dict:add',0,NULL,NULL,NULL,NULL),(12,10,'dict','字典编辑',2,2,NULL,NULL,'system:dict:edit',0,NULL,NULL,NULL,NULL),(13,10,'dict','字典删除',2,3,NULL,NULL,'system:dict:del',0,NULL,NULL,NULL,NULL),(100,0,'hr','人事管理',0,2,'javascript:;','layui-icon-component',NULL,0,NULL,NULL,NULL,NULL),(101,100,'dept','部门管理',1,1,'/hr/dept',NULL,'hr:dept:list',0,NULL,NULL,NULL,NULL),(102,101,'dept','部门新增',2,1,NULL,NULL,'hr:dept:add',0,NULL,NULL,NULL,NULL),(103,101,'dept','部门编辑',2,2,NULL,NULL,'hr:dept:edit',0,NULL,NULL,NULL,NULL),(104,101,'dept','部门删除',2,3,NULL,NULL,'hr:dept:del',0,NULL,NULL,NULL,NULL),(105,100,'user','用户管理',1,2,'/hr/user',NULL,'hr:user:list',0,NULL,NULL,NULL,NULL),(106,105,'user','用户新增',2,1,NULL,NULL,'hr:user:add',0,NULL,NULL,NULL,NULL),(107,105,'user','用户编辑',2,2,NULL,NULL,'hr:user:edit',0,NULL,NULL,NULL,NULL),(108,105,'user','用户删除',2,3,NULL,NULL,'hr:user:del',0,NULL,NULL,NULL,NULL),(109,100,'post','岗位管理',1,3,'/hr/post',NULL,'hr:post:list',0,NULL,NULL,NULL,NULL),(110,109,'post','岗位新增',2,1,NULL,NULL,'hr:post:add',0,NULL,NULL,NULL,NULL),(111,109,'post','岗位编辑',2,2,NULL,NULL,'hr:post:edit',0,NULL,NULL,NULL,NULL),(112,109,'post','岗位删除',2,3,NULL,NULL,'hr:post:del',0,NULL,NULL,NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_title` varchar(50) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_role` */

LOCK TABLES `t_role` WRITE;

insert  into `t_role`(`role_id`,`role_name`,`role_title`,`deleted`,`created_by`,`created_time`,`modified_by`,`modified_time`) values (1,'user','普通用户',0,NULL,NULL,NULL,NULL),(2,'admin','管理员',0,NULL,NULL,NULL,NULL),(3,'manager','部门经理',0,NULL,NULL,NULL,NULL),(4,'hr','人力资源',0,NULL,NULL,NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_role_menu` */

LOCK TABLES `t_role_menu` WRITE;

insert  into `t_role_menu`(`role_id`,`menu_id`) values (1,2),(1,6),(1,10),(1,101),(1,105),(1,109),(4,2),(4,6),(4,10),(4,101),(4,102),(4,103),(4,104),(4,105),(4,106),(4,107),(4,108),(4,109),(4,110),(4,111),(4,112);

UNLOCK TABLES;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user` */

LOCK TABLES `t_user` WRITE;

insert  into `t_user`(`user_id`,`user_no`,`user_name`,`password`,`deleted`,`created_by`,`created_time`,`modified_by`,`modified_time`) values (1,'1001','zhangsan',NULL,0,NULL,NULL,NULL,NULL),(2,'1002','lisi',NULL,0,NULL,NULL,NULL,NULL),(3,'1003','wangwu',NULL,0,NULL,NULL,NULL,NULL),(4,'1004','zhaoliu',NULL,0,NULL,NULL,NULL,NULL),(5,'1005','tianqi',NULL,0,NULL,NULL,NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user_role` */

LOCK TABLES `t_user_role` WRITE;

insert  into `t_user_role`(`user_id`,`role_id`) values (1,1),(2,1),(3,2),(4,4),(5,3);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
