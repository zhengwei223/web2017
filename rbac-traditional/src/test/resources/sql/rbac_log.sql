/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2017-08-20 14:43:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rbac_log
-- ----------------------------
DROP TABLE IF EXISTS `rbac_log`;
CREATE TABLE `rbac_log` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `opt_type` tinyint(8) NOT NULL COMMENT '操作类型',
  `content` varchar(1000) DEFAULT NULL COMMENT '记录内容',
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='日志';

-- ----------------------------
-- Records of rbac_log
-- ----------------------------
INSERT INTO `rbac_log` VALUES ('1', '1', '1', '??', '2016-07-28 00:00:00', null);
