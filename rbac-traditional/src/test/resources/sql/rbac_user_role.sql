/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2017-08-20 14:44:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rbac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_role`;
CREATE TABLE `rbac_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `role_id` int(8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user_role
-- ----------------------------
INSERT INTO `rbac_user_role` VALUES ('1', '1', '1');
INSERT INTO `rbac_user_role` VALUES ('2', '1', '2');
