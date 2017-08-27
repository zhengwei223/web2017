/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2017-08-20 14:44:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rbac_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_menu`;
CREATE TABLE `rbac_role_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(8) NOT NULL,
  `menu_id` int(8) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='角色和菜单的关联';

-- ----------------------------
-- Records of rbac_role_menu
-- ----------------------------
INSERT INTO `rbac_role_menu` VALUES ('1', '1', '1', null, null);
INSERT INTO `rbac_role_menu` VALUES ('2', '1', '2', null, null);
INSERT INTO `rbac_role_menu` VALUES ('3', '1', '3', null, null);
INSERT INTO `rbac_role_menu` VALUES ('4', '1', '4', null, null);
INSERT INTO `rbac_role_menu` VALUES ('5', '1', '5', null, null);
INSERT INTO `rbac_role_menu` VALUES ('6', '1', '6', null, null);
INSERT INTO `rbac_role_menu` VALUES ('7', '1', '7', null, null);
INSERT INTO `rbac_role_menu` VALUES ('8', '1', '8', null, null);
INSERT INTO `rbac_role_menu` VALUES ('9', '1', '9', null, null);
INSERT INTO `rbac_role_menu` VALUES ('10', '1', '10', null, null);
