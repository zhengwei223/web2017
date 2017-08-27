/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2017-08-20 15:46:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rbac_menu
-- ----------------------------
DROP TABLE IF EXISTS `rbac_menu`;
CREATE TABLE `rbac_menu` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL COMMENT '文字标题',
  `pid` int(8) NOT NULL COMMENT '父级菜单',
  `url` varchar(1000) NOT NULL COMMENT '对应的后台url',
  `state` tinyint(1) NOT NULL COMMENT '状态',
  `is_button` tinyint(1) DEFAULT NULL COMMENT '是否为按钮资源',
  `description` varchar(200) DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='菜单/按钮';

-- ----------------------------
-- Records of rbac_menu
-- ----------------------------
INSERT INTO `rbac_menu` VALUES ('1', '系统管理', '0', 'menu.do?getMenuListnew', '1', '0', '系统管理的菜单', null, null);
INSERT INTO `rbac_menu` VALUES ('2', '日志管理', '0', 'menu.do?menu.do/getMenuListnew', '1', '0', '操作日志的菜单', null, null);
INSERT INTO `rbac_menu` VALUES ('3', '菜单管理', '1', 'menu.do?method=getMenuListPage', '1', '0', '对菜单进行操作的菜单', null, null);
INSERT INTO `rbac_menu` VALUES ('4', '角色管理', '1', 'menu.do?menu.do/getMenuListnew', '1', '0', '对角色进行管理的菜单', null, null);
INSERT INTO `rbac_menu` VALUES ('5', '用户管理', '1', 'user.do?method=ShowUserByRoleUrl', '1', '0', '从来管理用户的菜单', null, null);
