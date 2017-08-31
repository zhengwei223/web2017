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

CREATE TABLE rbac_account
(
    id INT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_profile_id INT(11) COMMENT '用户信息id',
    account VARCHAR(100) NOT NULL COMMENT '账户标识',
    password VARCHAR(64) NOT NULL,
    type SMALLINT(20) COMMENT '账户类型：邮箱，手机，微信，qq',
    login_time DATETIME,
    last_login_time DATETIME,
    gmt_create DATETIME NOT NULL,
    gmt_modified DATETIME,
    token CHAR(64)
);

-- ----------------------------
-- Records of rbac_account
-- ----------------------------
INSERT INTO test.rbac_account (user_profile_id, account, password, type, login_time, last_login_time, gmt_create, gmt_modified, token) VALUES (1, 'admin@163.com', 'admin', 1, '2016-07-28 19:07:25', '2016-07-28 19:07:28', '2017-08-30 20:31:31', '2017-08-30 20:31:34', '70c774a24261456a87b4d0283fe968ff');
INSERT INTO test.rbac_account (user_profile_id, account, password, type, login_time, last_login_time, gmt_create, gmt_modified, token) VALUES (1, '123123', '123123', 2, '2016-07-29 19:24:46', '2016-07-29 19:24:52', '2017-08-30 20:31:36', '2017-08-30 20:31:38', null);