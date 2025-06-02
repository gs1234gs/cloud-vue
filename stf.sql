/*
 Navicat Premium Dump SQL

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80403 (8.4.3)
 Source Host           : localhost:3306
 Source Schema         : stf

 Target Server Type    : MySQL
 Target Server Version : 80403 (8.4.3)
 File Encoding         : 65001

 Date: 01/06/2025 20:14:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone_number` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` tinyint NULL DEFAULT 0 COMMENT '部门状态（0正常 1停用）',
  `del_flag` tinyint NULL DEFAULT 0 COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1014 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '0', '天畅风华科技', 0, '彭志华', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-28 21:03:38', '', '2025-03-28 21:03:41');
INSERT INTO `sys_dept` VALUES (100, 1, '0,100', '昆明总公司', 1, '刘媛', '15287218571', '15287218571@qq.com', 0, 0, 'admin', '2025-03-28 21:11:13', '', '2025-03-28 21:11:18');
INSERT INTO `sys_dept` VALUES (101, 1, '0,100', '大理分公司', 2, '王镜', '88888888888', '8888888888@qq.com', 0, 0, 'admin', '2025-03-28 21:15:31', '', '2025-03-28 21:15:34');
INSERT INTO `sys_dept` VALUES (1002, 100, '0,1000,1002', '运营部门', 1, '胡宗会', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-28 21:35:06', '', '2025-03-28 21:35:18');
INSERT INTO `sys_dept` VALUES (1003, 100, '0,1000,1003', '财务部门', 2, '柴舜钦', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-28 21:35:16', '', '2025-03-28 21:35:20');
INSERT INTO `sys_dept` VALUES (1004, 100, '0,1000,1004', '商务部门', 3, '江春阳', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-28 21:35:15', '', '2025-03-28 21:35:21');
INSERT INTO `sys_dept` VALUES (1005, 100, '0,1000,1005', '人事部门', 4, '董正辉', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-28 21:35:13', '', '2025-03-28 21:35:22');
INSERT INTO `sys_dept` VALUES (1006, 100, '0,1000,1006', '运维部门', 5, '费寻', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-28 21:35:12', '', '2025-03-28 21:35:24');
INSERT INTO `sys_dept` VALUES (1007, 100, '0,1000,1007', '技术部门', 6, '刘宽', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-28 21:35:11', '', '2025-03-28 21:35:25');
INSERT INTO `sys_dept` VALUES (1008, 101, '0,1001,1008', '运营部门', 1, '管仕运', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-28 21:35:08', '', '2025-03-28 21:35:26');
INSERT INTO `sys_dept` VALUES (1009, 101, '0,1001,1009', '运维部门', 2, '张亚琼', '15287919470', '15287919470@qq.com', 0, 0, 'admin', '2025-03-09 21:35:09', '', '2025-03-28 21:35:27');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_info`;
CREATE TABLE `sys_login_info`  (
  `info_id` int NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` tinyint NULL DEFAULT 0 COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_login_lt`(`login_time` ASC) USING BTREE,
  INDEX `idx_sys_login_s`(`status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由参数',
  `route_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由名称',
  `is_frame` tinyint NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` tinyint NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` tinyint NULL DEFAULT 0 COMMENT '菜单状态（0显示 1隐藏）',
  `status` tinyint NULL DEFAULT 0 COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10030 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '/main/system', NULL, NULL, '', 1, 0, 'M', 0, 0, 'system', 'el-icon-Setting', 'admin', '2025-03-28 23:56:45', '', '2025-03-30 21:18:27', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '/main/monitor', NULL, NULL, '', 1, 0, 'M', 0, 0, 'monitor', 'el-icon-Monitor', 'admin', '2025-03-29 11:45:51', '', '2025-03-30 21:18:28', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '商品管理', 0, 3, '/main/goods', NULL, NULL, '', 1, 0, 'M', 0, 0, 'goods', 'el-icon-ShoppingCart', 'admin', '2025-04-24 19:07:05', '', NULL, '商品管理目录');
INSERT INTO `sys_menu` VALUES (4, '通知公告', 0, 4, '/main/notice', NULL, NULL, '', 1, 0, 'M', 0, 0, 'notice', 'el-icon-BellFilled', 'admin', '2025-03-29 11:50:28', '', '2025-03-30 21:18:30', '天畅通知目录');
INSERT INTO `sys_menu` VALUES (999, '菜单管理', 1, 1, '/main/system/menu', NULL, NULL, '', 1, 0, 'C', 0, 0, 'system:menu', 'el-icon-Menu', 'admin', '2025-03-29 12:45:15', '', '2025-03-30 21:18:33', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (1000, '角色管理', 1, 2, '/main/system/role', NULL, NULL, '', 1, 0, 'C', 0, 0, 'system:role', 'el-icon-UserFilled', 'admin', '2025-03-29 12:45:17', '', '2025-03-30 21:18:31', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (1001, '部门管理', 1, 3, '/main/system/dept', NULL, NULL, '', 1, 0, 'C', 0, 0, 'system:dept', 'el-icon-Stamp', 'admin', '2025-03-29 12:45:18', '', '2025-03-30 21:18:34', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (1002, '用户管理', 1, 4, '/main/system/user', NULL, NULL, '', 1, 0, 'C', 0, 0, 'system:user', 'el-icon-User', 'admin', '2025-03-29 12:45:20', '', '2025-03-30 21:18:35', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (1003, '字典管理', 1, 6, '/main/system/dict', NULL, NULL, '', 1, 0, 'C', 0, 0, 'system:dict', 'el-icon-Reading', 'admin', '2025-03-29 12:45:24', '', '2025-03-30 21:18:36', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (1004, '岗位管理', 1, 5, '/main/system/post', NULL, NULL, '', 1, 0, 'C', 0, 0, 'system:post', 'el-icon-Crop', 'admin', '2025-03-29 12:45:25', '', '2025-03-30 21:18:37', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (1005, '在线用户', 2, 1, '/main/monitor/online', NULL, NULL, '', 1, 0, 'C', 0, 0, 'monitor:online', 'el-icon-Avatar', 'admin', '2025-03-29 12:54:20', '', '2025-03-30 21:18:39', '在线用户菜单');
INSERT INTO `sys_menu` VALUES (1006, '日志管理', 2, 2, '/main/monitor/log', NULL, NULL, '', 1, 0, 'C', 0, 0, 'monitor:log', 'el-icon-Notebook', 'admin', '2025-03-29 12:54:21', '', '2025-03-30 21:18:40', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (1007, '消息通知', 4, 1, '/main/notice/message', NULL, NULL, '', 1, 0, 'C', 0, 0, 'notice:msg', 'el-icon-ChatDotRound', 'admin', '2025-04-02 23:54:13', '', '2025-04-02 23:54:18', '消息管理菜单');
INSERT INTO `sys_menu` VALUES (1008, '系统缓存', 2, 3, '/main/monitor/cache', NULL, NULL, '', 1, 0, 'C', 0, 0, 'monitor:cache', 'el-icon-Platform', 'admin', '2025-04-24 19:03:52', '', NULL, '系统缓存菜单');
INSERT INTO `sys_menu` VALUES (1009, '商品种类', 3, 1, '/main/goods/category', NULL, NULL, '', 1, 0, 'C', 0, 0, 'goods:category', 'el-icon-MoreFilled', 'admin', '2025-04-24 19:20:58', '', NULL, '商品种类菜单');
INSERT INTO `sys_menu` VALUES (1010, '订单管理', 3, 2, '/main/goods/orders', NULL, NULL, '', 1, 0, 'C', 0, 0, 'goods:orders', 'el-icon-ShoppingCartFull', 'admin', '2025-04-24 19:21:00', '', NULL, '订单管理菜单');
INSERT INTO `sys_menu` VALUES (1011, '库存管理', 3, 3, '/main/goods/stock', NULL, NULL, '', 1, 0, 'C', 0, 0, 'goods:product', 'el-icon-Coin', 'admin', '2025-04-24 19:21:02', '', NULL, '库存管理菜单');
INSERT INTO `sys_menu` VALUES (10001, '新增菜单', 999, 1, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:menu:add', '#', 'admin', '2025-04-03 19:51:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10002, '删除菜单', 999, 2, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:menu:delete', '#', 'admin', '2025-04-03 19:51:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10003, '查询菜单', 999, 3, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:menu:list', '#', 'admin', '2025-04-03 19:51:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10004, '编辑菜单', 999, 4, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:menu:update', '#', 'admin', '2025-04-03 19:51:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10005, '新增用户', 1002, 1, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:user:add', '#', 'admin', '2025-04-03 23:53:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10006, '删除用户', 1002, 2, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:user:delete', '#', 'admin', '2025-04-03 23:53:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10007, '编辑用户', 1002, 3, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:user:update', '#', 'admin', '2025-04-03 23:53:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10008, '查询用户', 1002, 4, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:user:list', '#', 'admin', '2025-04-03 23:53:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10009, '新增部门', 1001, 1, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:dept:add', '#', 'admin', '2025-04-23 16:50:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10010, '删除部门', 1001, 2, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:dept:delete', '#', 'admin', '2025-04-23 16:50:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10011, '修改部门', 1001, 3, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:dept:update', '#', 'admin', '2025-04-23 16:50:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10012, '查询部门', 1001, 4, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:dept:list', '#', 'admin', '2025-04-23 16:50:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10013, '新增角色', 1000, 1, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:role:add', '#', 'admin', '2025-04-23 16:50:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10014, '删除角色', 1000, 2, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:role:delete', '#', 'admin', '2025-04-23 16:50:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10015, '修改角色', 1000, 3, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:role:update', '#', 'admin', '2025-04-23 16:50:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10016, '查询角色', 1000, 4, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:role:list', '#', 'admin', '2025-04-23 16:50:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10017, '新增字典', 1003, 1, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:dict:add', '#', 'admin', '2025-04-23 16:50:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10018, '删除字典', 1003, 2, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:dict:delete', '#', 'admin', '2025-04-23 16:50:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10019, '修改字典', 1003, 3, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:dict:update', '#', 'admin', '2025-04-23 16:58:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10020, '查询字典', 1003, 4, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:dict:list', '#', 'admin', '2025-04-23 16:58:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10021, '新增岗位', 1004, 1, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:post:add', '#', 'admin', '2025-04-23 16:58:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10022, '删除岗位', 1003, 2, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:post:delete', '#', 'admin', '2025-04-23 16:58:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10023, '修改岗位', 1004, 3, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:post:update', '#', 'admin', '2025-04-23 16:58:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10024, '查询岗位', 1004, 4, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'system:post:list', '#', 'admin', '2025-04-23 16:58:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10025, '查询商品', 1011, 1, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'goods:product:list', '#', 'admin', '2025-05-04 21:20:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10026, '商品统计', 1011, 2, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'goods:product:amount', '#', 'admin', '2025-05-04 23:01:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10027, '编辑商品', 1011, 3, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'goods:product:update', '#', 'admin', '2025-05-10 18:59:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10028, '添加商品', 1011, 4, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'goods:product:add', '#', 'admin', '2025-05-10 18:59:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (10029, '删除商品', 1011, 0, '', NULL, NULL, '', 1, 0, 'F', 0, 0, 'goods:product:delete', '#', 'admin', '2025-05-11 13:19:18', '', NULL, '');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` int NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `post_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` smallint NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, 0, 'admin', '2025-03-29 13:35:56', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, 0, 'admin', '2025-03-29 13:35:35', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, 0, 'admin', '2025-03-29 13:35:55', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, 0, 'admin', '2025-03-29 13:35:52', '', NULL, '');

-- ----------------------------
-- Table structure for sys_post_seq
-- ----------------------------
DROP TABLE IF EXISTS `sys_post_seq`;
CREATE TABLE `sys_post_seq`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post_seq
-- ----------------------------
INSERT INTO `sys_post_seq` VALUES (1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` tinyint NULL DEFAULT 1 COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` tinyint NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` tinyint NULL DEFAULT 0 COMMENT '删除标志（0代表已经删除 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `sys_role_pk`(`role_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, 1, 1, 1, 0, 0, 'admin', '2025-03-29 13:15:55', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, 2, 1, 1, 0, 0, 'admin', '2025-03-29 13:15:53', '', NULL, '普通角色');
INSERT INTO `sys_role` VALUES (27, 'hello', 'world', 0, 0, 0, 0, 0, 0, NULL, '2025-04-19 20:25:04', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (28, '李逍遥', 'li', 0, 0, 0, 0, 0, 0, NULL, '2025-04-15 21:34:00', NULL, '2025-05-11 15:07:16', NULL);
INSERT INTO `sys_role` VALUES (30, '令狐冲', '3456', 1, 1, 1, 1, 0, 0, 'admin', '2025-04-16 21:05:36', '', NULL, '超级管理员');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` int NOT NULL COMMENT '角色ID',
  `dept_id` int NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 888);
INSERT INTO `sys_role_dept` VALUES (2, 1000);
INSERT INTO `sys_role_dept` VALUES (2, 1006);
INSERT INTO `sys_role_dept` VALUES (2, 1007);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int NOT NULL COMMENT '角色ID',
  `menu_id` int NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 999);
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1001);
INSERT INTO `sys_role_menu` VALUES (1, 1002);
INSERT INTO `sys_role_menu` VALUES (1, 1003);
INSERT INTO `sys_role_menu` VALUES (1, 1004);
INSERT INTO `sys_role_menu` VALUES (1, 1005);
INSERT INTO `sys_role_menu` VALUES (1, 1006);
INSERT INTO `sys_role_menu` VALUES (1, 1007);
INSERT INTO `sys_role_menu` VALUES (1, 1008);
INSERT INTO `sys_role_menu` VALUES (1, 1009);
INSERT INTO `sys_role_menu` VALUES (1, 1010);
INSERT INTO `sys_role_menu` VALUES (1, 1011);
INSERT INTO `sys_role_menu` VALUES (1, 10001);
INSERT INTO `sys_role_menu` VALUES (1, 10002);
INSERT INTO `sys_role_menu` VALUES (1, 10003);
INSERT INTO `sys_role_menu` VALUES (1, 10004);
INSERT INTO `sys_role_menu` VALUES (1, 10005);
INSERT INTO `sys_role_menu` VALUES (1, 10006);
INSERT INTO `sys_role_menu` VALUES (1, 10007);
INSERT INTO `sys_role_menu` VALUES (1, 10008);
INSERT INTO `sys_role_menu` VALUES (1, 10009);
INSERT INTO `sys_role_menu` VALUES (1, 10010);
INSERT INTO `sys_role_menu` VALUES (1, 10011);
INSERT INTO `sys_role_menu` VALUES (1, 10012);
INSERT INTO `sys_role_menu` VALUES (1, 10013);
INSERT INTO `sys_role_menu` VALUES (1, 10014);
INSERT INTO `sys_role_menu` VALUES (1, 10015);
INSERT INTO `sys_role_menu` VALUES (1, 10016);
INSERT INTO `sys_role_menu` VALUES (1, 10017);
INSERT INTO `sys_role_menu` VALUES (1, 10018);
INSERT INTO `sys_role_menu` VALUES (1, 10019);
INSERT INTO `sys_role_menu` VALUES (1, 10020);
INSERT INTO `sys_role_menu` VALUES (1, 10021);
INSERT INTO `sys_role_menu` VALUES (1, 10022);
INSERT INTO `sys_role_menu` VALUES (1, 10023);
INSERT INTO `sys_role_menu` VALUES (1, 10024);
INSERT INTO `sys_role_menu` VALUES (1, 10025);
INSERT INTO `sys_role_menu` VALUES (1, 10026);
INSERT INTO `sys_role_menu` VALUES (1, 10027);
INSERT INTO `sys_role_menu` VALUES (1, 10028);
INSERT INTO `sys_role_menu` VALUES (1, 10029);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 999);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 10003);
INSERT INTO `sys_role_menu` VALUES (2, 10008);
INSERT INTO `sys_role_menu` VALUES (2, 10012);
INSERT INTO `sys_role_menu` VALUES (2, 10016);
INSERT INTO `sys_role_menu` VALUES (2, 10020);
INSERT INTO `sys_role_menu` VALUES (27, 3);
INSERT INTO `sys_role_menu` VALUES (27, 1007);
INSERT INTO `sys_role_menu` VALUES (28, 2);
INSERT INTO `sys_role_menu` VALUES (28, 1006);
INSERT INTO `sys_role_menu` VALUES (30, 3);
INSERT INTO `sys_role_menu` VALUES (30, 1007);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `user_type` tinyint NULL DEFAULT 1 COMMENT '用户类型（0表示系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phone_number` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  `id_card` char(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `gender` tinyint NULL DEFAULT 0 COMMENT '用户性别（0男 1女 2未知）',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '123456' COMMENT '密码',
  `status` tinyint NULL DEFAULT 0 COMMENT '帐号状态（0正常 1停用）',
  `del_flag` tinyint NULL DEFAULT 0 COMMENT '删除标志（0未删除 2代表删除）',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `sys_user_k`(`user_name` ASC, `id_card` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1043 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 888, 'admin', '天畅', 0, '216716242727qq.com', '15287919470', '888888888888888888', 0, NULL, '123456', 0, 0, '2025-04-20 00:39:42', 'admin', '2025-03-29 13:33:32', NULL, '2025-04-09 21:01:58', '系统管理员');
INSERT INTO `sys_user` VALUES (2, 1007, 'tester', '管仕运', 1, '216716242727qq.com', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, '2025-03-29 16:22:03', 'admin', '2025-03-29 16:22:10', '', '2025-04-09 20:57:46', '系统测试员');
INSERT INTO `sys_user` VALUES (1000, NULL, 'huzonghui', '胡总会', 1, '216716242727qq.com', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1001, NULL, 'chaishunqing', '柴舜钦', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1002, NULL, 'wangjing', '王婧', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1003, NULL, 'jiangchunyang', '江春阳', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1004, NULL, 'zhangyaqiong', '张亚琼', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1005, NULL, 'xvchaliu', '徐茶柳', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1006, NULL, 'yangkexin', '杨可欣', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1007, 0, 'liuxin', '刘欣', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', '2025-05-11 15:52:17', NULL);
INSERT INTO `sys_user` VALUES (1008, NULL, 'liuxvting', '刘绪婷', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1009, NULL, 'lizhuping', '李主平', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1010, NULL, 'liguolei', '李国磊', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1011, NULL, 'peixiukun', '裴秀坤', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1012, NULL, 'huzhongyan', '胡忠艳', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1013, NULL, 'zhongruonan', '樟若楠', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1014, NULL, 'dengchenyv', '邓成宇', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1015, NULL, 'wangsiru', '王思茹', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1016, NULL, 'shengyuan', '沈媛', 1, '', '15287919470', '888888888888888888', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1017, NULL, 'zhuweiyuan', '朱卫源', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1018, NULL, 'xieqingxi', '谢庆希', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1019, NULL, 'zhangchunbo', '张春博', 1, '', '15287919470', '888888888888888888', 0, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1020, 3, 'tianchang', '天畅', 1, '123456@qq.com', '12345678901', '123456789012345678', 0, 'C:/Users/G1528/OneDrive/Desktop/龙女妹妹.png', '123456', 0, 0, '2025-04-07 18:53:31', 'tianchang', '2025-04-07 18:53:31', 'tianchang', '2025-04-08 00:55:41', '测试');
INSERT INTO `sys_user` VALUES (1035, 1, 'tianchang1', '天畅', 1, '123456@qq.com', '12345678901', '123456789012345670', 1, 'C:/Users/G1528/OneDrive/Desktop/龙女妹妹.png', '123456', 1, 1, '2025-04-09 14:31:50', 'tianchang', '2025-04-09 14:31:50', 'tianchang', '2025-04-09 14:31:50', '测试');
INSERT INTO `sys_user` VALUES (1038, 1003, '55555', '454545', 1, '', '09876543210', '222222222222222222', 1, '', '123456', 0, 0, NULL, '', NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (1039, 1002, 'sssssssss', 'sssssssss', 1, '', '13587923467', '111111111111111111', 0, '', '123456', 0, 0, NULL, '', NULL, '', '2025-04-09 22:06:59', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (1035, 26);
INSERT INTO `sys_user_role` VALUES (1038, 2);
INSERT INTO `sys_user_role` VALUES (1039, 2);

SET FOREIGN_KEY_CHECKS = 1;
