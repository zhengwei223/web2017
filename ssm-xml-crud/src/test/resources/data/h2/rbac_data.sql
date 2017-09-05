-- ----------------------------
-- Records of rbac_account
-- ----------------------------
INSERT INTO rbac_account (user_profile_id, account, password, type, login_time, last_login_time, gmt_create, gmt_modified, token) VALUES (1, 'admin@163.com', 'admin', 1, '2016-07-28 19:07:25', '2016-07-28 19:07:28', '2017-08-30 20:31:31', '2017-08-30 20:31:34', '70c774a24261456a87b4d0283fe968ff');
INSERT INTO rbac_account (user_profile_id, account, password, type, login_time, last_login_time, gmt_create, gmt_modified, token) VALUES (1, '123123', '123123', 2, '2016-07-29 19:24:46', '2016-07-29 19:24:52', '2017-08-30 20:31:36', '2017-08-30 20:31:38', null);

-- ----------------------------
-- Records of rbac_log
-- ----------------------------
INSERT INTO `rbac_log` VALUES ('1', '1', '1', '??', '2016-07-28 00:00:00', null);


-- ----------------------------
-- Records of rbac_menu
-- ----------------------------
INSERT INTO `rbac_menu` VALUES ('1', '系统管理', '0', 'menu.do?getMenuListnew', '1', '0', '系统管理的菜单', null, null);
INSERT INTO `rbac_menu` VALUES ('2', '日志管理', '0', 'menu.do?menu.do/getMenuListnew', '1', '0', '操作日志的菜单', null, null);
INSERT INTO `rbac_menu` VALUES ('3', '菜单管理', '1', 'menu.do?method=getMenuListPage', '1', '0', '对菜单进行操作的菜单', null, null);
INSERT INTO `rbac_menu` VALUES ('4', '角色管理', '1', 'menu.do?menu.do/getMenuListnew', '1', '0', '对角色进行管理的菜单', null, null);
INSERT INTO `rbac_menu` VALUES ('5', '用户管理', '1', 'user.do?method=ShowUserByRoleUrl', '1', '0', '从来管理用户的菜单', null, null);

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
INSERT INTO `rbac_role` VALUES ('1', '管理员', '2016-07-15 08:12:00', null, '权限所有者');
INSERT INTO `rbac_role` VALUES ('2', '普通用户', '2016-07-15 09:02:21', null, '普通的用户');

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

-- ----------------------------
-- Records of rbac_user_profile
-- ----------------------------
INSERT INTO `rbac_user_profile` VALUES ('1', '999', '郑未', '1');
INSERT INTO `rbac_user_profile` VALUES ('2', '芦花荡', '当路滑', '1');

-- ----------------------------
-- Records of rbac_user_role
-- ----------------------------
INSERT INTO `rbac_user_role` VALUES ('1', '1', '1');
INSERT INTO `rbac_user_role` VALUES ('2', '1', '2');
