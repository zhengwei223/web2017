/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2017-08-20 14:43:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rbac_account
-- ----------------------------
DROP TABLE IF EXISTS `rbac_account`;
CREATE TABLE `rbac_account` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_profile_id` int(11) DEFAULT NULL COMMENT '用户信息id',
  `account` varchar(100) NOT NULL COMMENT '账户标识',
  `password` varchar(64) NOT NULL,
  `type` smallint(20) DEFAULT NULL COMMENT '账户类型：邮箱，手机，微信，qq',
  `login_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_account
-- ----------------------------
INSERT INTO `rbac_account` VALUES ('1', '1', 'admin@163.com', 'admin', '1', '2016-07-28 19:07:25', '2016-07-28 19:07:28', '0000-00-00 00:00:00', null);
INSERT INTO `rbac_account` VALUES ('2', '1', '123123', '123123', '2', '2016-07-29 19:24:46', '2016-07-29 19:24:52', '0000-00-00 00:00:00', null);
