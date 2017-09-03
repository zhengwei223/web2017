-- ----------------------------
-- Table structure for rbac_account
-- ----------------------------
DROP TABLE IF EXISTS `rbac_account`;
CREATE TABLE `rbac_account`
(
  `id`              INT(20) AUTO_INCREMENT,
  `user_profile_id` INT(11) ,
  `account`         VARCHAR(100)        NOT NULL,
  `password`        VARCHAR(64)         NOT NULL,
  `type`            SMALLINT(20) ,
  `login_time`      DATETIME,
  `last_login_time` DATETIME,
  `gmt_create`      DATETIME            NOT NULL,
  `gmt_modified`    DATETIME,
  `token`           CHAR(64),
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for rbac_log
-- ----------------------------
DROP TABLE IF EXISTS `rbac_log`;
CREATE TABLE `rbac_log` (
  `id`           INT(20)    NOT NULL AUTO_INCREMENT,
  `user_id`      INT(20)    NOT NULL,
  `opt_type`     TINYINT(8) NOT NULL,
  `content`      VARCHAR(1000)       DEFAULT NULL,
  `gmt_created`  DATETIME   NOT NULL,
  `gmt_modified` DATETIME            DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for rbac_menu
-- ----------------------------
DROP TABLE IF EXISTS `rbac_menu`;
CREATE TABLE `rbac_menu` (
  `id`           INT(8)        NOT NULL AUTO_INCREMENT,
  `title`        VARCHAR(200)  NOT NULL,
  `pid`          INT(8)        NOT NULL,
  `url`          VARCHAR(1000) NOT NULL,
  `state`        TINYINT(1)    NOT NULL,
  `is_button`    TINYINT(1)             DEFAULT NULL,
  `description`  VARCHAR(200)           DEFAULT NULL,
  `gmt_modified` DATETIME               DEFAULT NULL,
  `gmt_create`   DATETIME               DEFAULT NULL,
  PRIMARY KEY (`id`)
);


-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ;


-- ----------------------------
-- Table structure for rbac_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_menu`;
CREATE TABLE `rbac_role_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_id` int(8) NOT NULL,
  `menu_id` int(8) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);


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
) ;


-- ----------------------------
-- Table structure for rbac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_role`;
CREATE TABLE `rbac_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `role_id` int(8) NOT NULL,
  PRIMARY KEY (`id`)
);

