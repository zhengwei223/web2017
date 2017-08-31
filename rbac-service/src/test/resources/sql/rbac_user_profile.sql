/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2017-08-20 15:49:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rbac_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_profile`;
CREATE TABLE `rbac_user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(100) DEFAULT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user_profile
-- ----------------------------
INSERT INTO `rbac_user_profile` VALUES ('1', '999', '郑未', '1');
INSERT INTO `rbac_user_profile` VALUES ('2', '芦花荡', '当路滑', '1');
