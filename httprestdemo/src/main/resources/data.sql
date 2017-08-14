-- ----------------------------
-- Records of t_dic_data
-- ----------------------------
INSERT INTO `t_dic_data` VALUES ('16b80bfb-f0ee-47a0-ba94-cc256abaed17', '专科', '', null, null, '', '1', 'xueli', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('7ed23330-5538-4943-8678-0c5a2121cf57', '高中', '', null, null, '', '1', 'xueli', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('936db407-ae1-45a7-a657-b60580e2a77a', '汉族', '101', null, null, '', '1', 'minzu', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('936db407-ae2-45a7-a657-b60580e2a77a', '回族', '', null, null, '', '1', 'minzu', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('936db407-ae3-45a7-a657-b60580e2a77a', '一级', '', null, null, '', '1', 'grade', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('936db407-ae4-45a7-a657-b60580e2a77a', '二级', '', null, null, '', '1', 'grade', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('d7d1744b-e69f-48d0-9760-b2eae6af039b', '本科', '', null, null, '', '1', 'xueli', null, null, null, null, null);

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('169815aca9cf41d390e7feb6629d361d', '栏目管理', 'business_manager', '', '/system/cms/channel/list', '1', '1', '4', '&#xe60a;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('3501ed1e23da40219b4f0fa5b7b2749a', '菜单列表', 't_menu_list', '', '/system/menu/list', '0', '1', null, '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('36ab9175f7b7423eadda974ba046be05', '修改密码', 't_user_list', '', '/system/user/modifiypwd/pre', '0', '1', null, '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('4adc1e3e3e244c0991d9dab66c63badf', '目录创建', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/uploadDic', '0', '1', '2', '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('7cd0678633d5407dba2bd6a1553cadce', '文件下载', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/downfile', '0', '1', '3', '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('91779a0d304f4b91932b63dec87a8536', '角色管理-系统', 'system_manager', '', '/system/role/list/all', '1', '1', null, '&#xe60a;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('b94392f7b8714f64819c5c0222eb134a', '角色修改-系统', 't_role_list', '', '/system/role/update/admin', '0', '1', null, '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('b9c4e8ecffe949c0b346e1fd0d6b9977', '内容管理', 'business_manager', '内容管理', '/system/cms/content/list', '1', '1', '5', '&#xe63c;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('business_manager', '业务管理', null, null, null, '1', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('ca152df1a7b44d4f81162f34b808934a', '验证老密码', '36ab9175f7b7423eadda974ba046be05', '', '/system/user/modifiypwd/ispwd', '0', '1', null, '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('ca28235dbd234b7585e133e70cc7999a', '文件上传', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/uploadFile', '0', '1', '1', '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('d6abe682007849869c3a168215ae40d4', 'WEB-INF文件管理', 'system_manager', '', '/system/file/web/list', '1', '1', '7', '&#xe61d;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('dic_manager', '字典管理', 'system_manager', '', null, '1', '1', null, '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('e51808e351c24a7e9fb4d47392930a2d', '保存新密码', '36ab9175f7b7423eadda974ba046be05', '', '/system/user/modifiypwd/save', '0', '1', null, '', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('f41b9f3b4a0d45f5a3b5842ee40e0e96', '站点管理', 'business_manager', '', '/system/cms/site/list', '1', '1', '3', '&#xe641;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('f5203235547342f094a2c126ad4603bb', '文件管理', 'system_manager', '', '/system/file/list', '1', '1', '6', '&#xe61d;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('system_manager', '系统管理', null, null, null, '1', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_auditlog_list', '修改日志', 'system_manager', '', '/system/auditlog/list', '1', '1', '1', '&#xe632;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_auditlog_look', '查看修改日志', 't_auditlog_list', null, '/system/auditlog/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_delete', '删除级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_deletemore', '批量删除级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_list', '级别管理', 'dic_manager', '', '/system/dicdata/grade/list', '1', '1', '1', '&#xe630;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_look', '查看级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_tree', '级别树形结构', 't_dic_data_grade_list', null, '/system/dicdata/grade/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_update', '修改级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_delete', '删除民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_deletemore', '批量删除民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_list', '民族管理', 'dic_manager', '', '/system/dicdata/minzu/list', '1', '1', '1', '&#xe650;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_look', '查看民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_tree', '民族树形结构', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_update', '修改民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_delete', '删除学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_deletemore', '批量删除学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_list', '学历管理', 'dic_manager', '', '/system/dicdata/xueli/list', '1', '1', '3', '&#xe621;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_look', '查看学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_tree', '学历树形结构', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_update', '修改学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_fwlog_list', '访问日志', 'system_manager', '', '/system/fwlog/list', '1', '1', '2', '&#xe62d;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_fwlog_look', '查看访问日志', 't_fwlog_list', null, '/system/fwlog/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_delete', '删除菜单', 't_menu_list', null, '/system/menu/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_deletemore', '批量删除菜单', 't_menu_list', null, '/system/menu/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_list', '菜单管理', 'system_manager', '', '/system/menu/list/all', '1', '1', '3', '&#xe631;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_look', '查看菜单', 't_menu_list', null, '/system/menu/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_tree', '菜单树形结构', 't_menu_list', null, '/system/menu/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_update', '修改菜单', 't_menu_list', null, '/system/menu/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_delete', '删除部门', 't_org_list', null, '/system/org/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_deletemore', '批量删除部门', 't_org_list', null, '/system/org/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_list', '部门管理', 'business_manager', '', '/system/org/list', '1', '1', '1', '&#xe613;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_look', '查看部门', 't_org_list', null, '/system/org/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_tree', '部门树形结构', 't_org_list', null, '/system/org/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_update', '修改部门', 't_org_list', null, '/system/org/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_delete', '删除角色', 't_role_list', null, '/system/role/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_deletemore', '批量删除角色', 't_role_list', null, '/system/role/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_list', '角色管理', 'system_manager', '', '/system/role/list', '1', '1', '4', '&#xe613;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_look', '查看角色', 't_role_list', null, '/system/role/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_update', '修改角色', 't_role_list', null, '/system/role/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_delete', '删除用户', 't_user_list', null, '/system/user/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_deletemore', '批量删除用户', 't_user_list', null, '/system/user/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_list', '用户管理', 'business_manager', '', '/system/user/list', '1', '1', '2', '&#xe612;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_list_export', '导出用户', 't_user_list', null, '/system/user/list/export', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_look', '查看用户', 't_user_list', null, '/system/user/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_update', '修改用户', 't_user_list', null, '/system/user/update', '0', '1', null, null, null, null, null, null, null);

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES ('o_10001', '平台', ',o_10001,', null, null, null, '1', '', '1', null, null, null, null, null);
INSERT INTO `t_org` VALUES ('o_10002', '网站', ',o_10001,o_10002,', 'o_10001', null, null, '1', '', '1', null, null, null, null, null);
INSERT INTO `t_org` VALUES ('o_10003', '张三的站', ',o_10001,o_10002,o_10003,', 'o_10002', '10', null, '1', '张三的站', '1', null, null, null, null, null);

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('r_10001', '超级管理员', null, null, '0', '', '1', 'o_10001', null, null, null, null, null);

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('003f6efe4e9b4b2998792953596258a2', 'r_10001', 't_org_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('003f6efe4e9b4b2998792953596258b1', 'r_10001', '3501ed1e23da40219b4f0fa5b7b2749a', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('005c20d1fba549bfabea4cd1aa23e3b6', 'r_10001', 't_fwlog_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('02e22012d3084dbe86981b23ef6e9b53', 'r_10001', 'ca152df1a7b44d4f81162f34b808934a', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('0365b249ec8c480cba9e77124a4fb4d7', 'r_10001', 't_dic_data_minzu_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('06bd3f0154314c60aaab50ef3d62bf46', 'r_10001', 't_dic_data_minzu_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('0b1d8f9d4d7d4efb9724ec8d66864be5', 'r_10001', 't_role_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('0b26312eb0f641d1af7b06caad5a3c2f', 'r_10001', 't_dic_data_xueli_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('14646b5594ad4980872e55a197755960', 'r_10001', 't_role_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('168ba9288da24624bbb8e997539454b0', 'r_10001', 't_menu_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('175860c599784439990ba4cbfffbb4c9', 'r_10001', 't_org_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('1a97031270ee4116883b1754716eba5f', 'r_10001', 't_dic_data_grade_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('1b73c387d13b4eb9a178e3a42b226ab6', 'r_10001', 't_org_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('23242847985c4bac9b0d7eb230cc5a9b', 'r_10001', 't_dic_data_xueli_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('2475300de6c64cf980becf7ebc37a668', 'r_10001', 'business_manager', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('2ddab4f6efd241b6a62b71401076aa89', 'r_10001', 'dic_manager', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('2e91d0783e404eb79051a92152c28b60', 'r_10001', '169815aca9cf41d390e7feb6629d361d', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('30b397a9791c4756bd828fbd25ae19dd', 'r_10001', 'b9c4e8ecffe949c0b346e1fd0d6b9977', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('3f12fb2443b74c7eab7a81f30a6cacc2', 'r_10001', 't_dic_data_grade_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('54385aa1b6234add88e341ce5cda95cc', 'r_10001', 't_menu_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('555c8bd2df5143a2ad3a70f7ec96bdc9', 'r_10001', 't_auditlog_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('559538ebb2bb4bbe912aa3f18f73ab49', 'r_10001', 't_role_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('562b8419a9954d3aa2998ed99dd2e534', 'r_10001', 't_dic_data_xueli_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('593c9b0b78864686869df09299b5186f', 'r_10001', 'f41b9f3b4a0d45f5a3b5842ee40e0e96', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('5c7dc109475b49df887ee16983703664', 'r_10001', 't_dic_data_xueli_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('626433cf94fa49ce86d3bbfbddb1c2e9', 'r_10001', 't_dic_data_minzu_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('649535986d5b4514a0d4c863b8db1a83', 'r_10001', 't_user_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('69a2190c890a48cfb2a790b865f8ae82', 'r_10001', 't_dic_data_grade_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('6d56f8bdc0cb47dd80a06a7d82f1da39', 'r_10001', 't_dic_data_grade_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('6ea4295a9a994a2f8e3bdf3b1fb427c4', 'r_10001', 't_user_list_export', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('71c806f8e2424b058102f8b8203ed53a', 'r_10001', 't_dic_data_xueli_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('72b283c5abe34f4e9714e5f9e96122a3', 'r_10001', 't_dic_data_grade_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('73288e75ae7e45feab6ca674274baef8', 'r_10001', 't_dic_data_minzu_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('76805a185e4941bb8d1281f056274172', 'r_10001', 't_dic_data_grade_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('84718a1752a64691a062d19c90862583', 'r_10001', 't_dic_data_xueli_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('8b737892a2f54d14966f235e0c362cf7', 'r_10001', 'e51808e351c24a7e9fb4d47392930a2d', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('8cdc76c5528447ca959b65d79cdcc77d', 'r_10001', 't_auditlog_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('9206eb1c8bf349d8bf23b19c5b137094', 'r_10001', 't_menu_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('9995e3e16b584fa78c92fffcd9e4027c', 'r_10001', 't_dic_data_minzu_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('9aec524c4d304f8bba498834d11db56c', 'r_10001', 't_menu_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('9c5a400d-fe4d-11e6-9319-bab4589d0955', 'r_10001', '91779a0d304f4b91932b63dec87a8536', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('9c5a4578-fe4d-11e6-9319-bab4589d0955', 'r_10001', 'b94392f7b8714f64819c5c0222eb134a', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('a28d0fd8e8214f0688497a67447294d5', 'r_10001', 't_menu_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('a3b411a659c247b79f0d05115c153164', 'r_10001', 't_role_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('a4532af8f0bd4f20b26c4d87f5a9fb58', 'r_10001', 't_org_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('a5516c8c746c4caabcd18c6613789838', 'r_10001', 't_user_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('aaf19f9f5fa74ab69c36952a185b26bc', 'r_10001', 't_org_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('b09b753c388343678571f977093cd59b', 'r_10001', 'system_manager', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('b1a49ce6d46541298b801dde806d12e6', 'r_10001', '36ab9175f7b7423eadda974ba046be05', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('b231af6db31a4fb7b55241f1f7a0c689', 'r_10001', 't_dic_data_minzu_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('b458712095464c0fabb4c037034fd532', 'r_10001', 't_fwlog_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('c630d47141194cf7bcdb7779e5a8d45b', 'r_10001', 't_user_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('e74f1ca58d934c7395735ac642509002', 'r_10001', 't_user_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('ee381117ef634c27bda3eaa1b6260e38', 'r_10001', 't_user_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('f45d21402ed1412497e568d8a00e4a50', 'r_10001', 't_org_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('fd3368ff61904e199a921102e0e71d9a', 'r_10001', 't_menu_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('ffdb77d726c949088953ddb35ebd1e5c', 'r_10001', 't_role_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('file001', 'r_10001', '4adc1e3e3e244c0991d9dab66c63badf', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('file002', 'r_10001', 'ca28235dbd234b7585e133e70cc7999a', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('file003', 'r_10001', 'd6abe682007849869c3a168215ae40d4', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('file004', 'r_10001', 'f5203235547342f094a2c126ad4603bb', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('file005', 'r_10001', '7cd0678633d5407dba2bd6a1553cadce', null, null, null, null, null);

-- ----------------------------
-- Records of t_tableindex
-- ----------------------------
INSERT INTO `t_tableindex` VALUES ('cms_channel', '10000', 'h_', null, null, null, null, null);
INSERT INTO `t_tableindex` VALUES ('cms_content', '100000', 'c_', null, null, null, null, null);
INSERT INTO `t_tableindex` VALUES ('cms_site', '10001', 's_', null, null, null, null, null);
INSERT INTO `t_tableindex` VALUES ('t_org', '10003', 'o_', null, null, null, null, null);
INSERT INTO `t_tableindex` VALUES ('t_user', '10001', 'u_', null, null, null, null, null);
-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('u_10001', '超级管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '男', null, null, null, '0', '1', null, null, null, null, null);

-- ----------------------------
-- Records of t_user_org
-- ----------------------------
INSERT INTO `t_user_org` VALUES ('1', 'u_10001', 'o_10001', '11', '1', null, null, null, null, null);


-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', 'u_10001', 'r_10001', null, null, null, null, null);
