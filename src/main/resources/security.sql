/*
 Navicat Premium Data Transfer

 Source Server         : 1.94.147.176_3306
 Source Server Type    : MySQL
 Source Server Version : 80403
 Source Host           : 1.94.147.176:3306
 Source Schema         : security

 Target Server Type    : MySQL
 Target Server Version : 80403
 File Encoding         : 65001

 Date: 24/03/2025 17:58:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `type` int NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
  `icon` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `orderNum` int NULL DEFAULT NULL COMMENT '排序',
  `created` datetime NOT NULL,
  `updated` datetime NULL DEFAULT NULL,
  `statu` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '', 'sys:manage', '', 0, 'el-icon-s-operation', 1, '2021-01-15 18:58:18', '2021-01-15 18:58:20', 1);
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', '/sys/users', 'sys:user:list', 'sys/User', 1, 'el-icon-s-custom', 1, '2021-01-15 19:03:45', '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', '/sys/roles', 'sys:role:list', 'sys/Role', 1, 'el-icon-rank', 2, '2021-01-15 19:03:45', '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', '/sys/menus', 'sys:menu:list', 'sys/Menu', 1, 'el-icon-menu', 3, '2021-01-15 19:03:45', '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu` VALUES (5, 0, '系统工具', '', 'sys:tools', NULL, 0, 'el-icon-s-tools', 2, '2021-01-15 19:06:11', NULL, 1);
INSERT INTO `sys_menu` VALUES (6, 5, '数字字典', '/sys/dicts', 'sys:dict:list', 'sys/Dict', 1, 'el-icon-s-order', 1, '2021-01-15 19:07:18', '2021-01-18 16:32:13', 1);
INSERT INTO `sys_menu` VALUES (7, 3, '添加角色', '', 'sys:role:save', '', 2, '', 1, '2021-01-15 23:02:25', '2021-01-17 21:53:14', 0);
INSERT INTO `sys_menu` VALUES (9, 2, '添加用户', NULL, 'sys:user:save', NULL, 2, NULL, 1, '2021-01-17 21:48:32', NULL, 1);
INSERT INTO `sys_menu` VALUES (10, 2, '修改用户', NULL, 'sys:user:update', NULL, 2, NULL, 2, '2021-01-17 21:49:03', '2021-01-17 21:53:04', 1);
INSERT INTO `sys_menu` VALUES (11, 2, '删除用户', NULL, 'sys:user:delete', NULL, 2, NULL, 3, '2021-01-17 21:49:21', NULL, 1);
INSERT INTO `sys_menu` VALUES (12, 2, '分配角色', NULL, 'sys:user:role', NULL, 2, NULL, 4, '2021-01-17 21:49:58', NULL, 1);
INSERT INTO `sys_menu` VALUES (13, 2, '重置密码', NULL, 'sys:user:repass', NULL, 2, NULL, 5, '2021-01-17 21:50:36', NULL, 1);
INSERT INTO `sys_menu` VALUES (14, 3, '修改角色', NULL, 'sys:role:update', NULL, 2, NULL, 2, '2021-01-17 21:51:14', NULL, 1);
INSERT INTO `sys_menu` VALUES (15, 3, '删除角色', NULL, 'sys:role:delete', NULL, 2, NULL, 3, '2021-01-17 21:51:39', NULL, 1);
INSERT INTO `sys_menu` VALUES (16, 3, '分配权限', NULL, 'sys:role:perm', NULL, 2, NULL, 5, '2021-01-17 21:52:02', NULL, 1);
INSERT INTO `sys_menu` VALUES (17, 4, '添加菜单', NULL, 'sys:menu:save', NULL, 2, NULL, 1, '2021-01-17 21:53:53', '2021-01-17 21:55:28', 1);
INSERT INTO `sys_menu` VALUES (18, 4, '修改菜单', NULL, 'sys:menu:update', NULL, 2, NULL, 2, '2021-01-17 21:56:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (19, 4, '删除菜单', NULL, 'sys:menu:delete', NULL, 2, NULL, 3, '2021-01-17 21:56:36', NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `remark` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created` datetime NULL DEFAULT NULL,
  `updated` datetime NULL DEFAULT NULL,
  `statu` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE,
  UNIQUE INDEX `code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (3, '普通用户', 'normal', '只有基本查看功能', '2021-01-04 10:09:14', '2021-01-30 08:19:52', 1);
INSERT INTO `sys_role` VALUES (6, '超级管理员', 'admin', '系统默认最高权限，不可以编辑和任意修改', '2021-01-16 13:29:03', '2021-01-17 15:50:45', 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (60, 6, 1);
INSERT INTO `sys_role_menu` VALUES (61, 6, 2);
INSERT INTO `sys_role_menu` VALUES (62, 6, 9);
INSERT INTO `sys_role_menu` VALUES (63, 6, 10);
INSERT INTO `sys_role_menu` VALUES (64, 6, 11);
INSERT INTO `sys_role_menu` VALUES (65, 6, 12);
INSERT INTO `sys_role_menu` VALUES (66, 6, 13);
INSERT INTO `sys_role_menu` VALUES (67, 6, 3);
INSERT INTO `sys_role_menu` VALUES (68, 6, 7);
INSERT INTO `sys_role_menu` VALUES (69, 6, 14);
INSERT INTO `sys_role_menu` VALUES (70, 6, 15);
INSERT INTO `sys_role_menu` VALUES (71, 6, 16);
INSERT INTO `sys_role_menu` VALUES (72, 6, 4);
INSERT INTO `sys_role_menu` VALUES (73, 6, 17);
INSERT INTO `sys_role_menu` VALUES (74, 6, 18);
INSERT INTO `sys_role_menu` VALUES (75, 6, 19);
INSERT INTO `sys_role_menu` VALUES (76, 6, 5);
INSERT INTO `sys_role_menu` VALUES (77, 6, 6);
INSERT INTO `sys_role_menu` VALUES (96, 3, 1);
INSERT INTO `sys_role_menu` VALUES (97, 3, 2);
INSERT INTO `sys_role_menu` VALUES (98, 3, 3);
INSERT INTO `sys_role_menu` VALUES (99, 3, 4);
INSERT INTO `sys_role_menu` VALUES (100, 3, 5);
INSERT INTO `sys_role_menu` VALUES (101, 3, 6);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `city` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `created` datetime NULL DEFAULT NULL,
  `updated` datetime NULL DEFAULT NULL,
  `last_login` datetime NULL DEFAULT NULL,
  `statu` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_USERNAME`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$R7zegeWzOXPw871CmNuJ6upC0v8D373GuLuTw8jn6NET4BkPRZfgK', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', '123@qq.com', '广州', '2021-01-12 22:13:53', '2021-01-16 16:57:32', '2020-12-30 08:38:37', 1);
INSERT INTO `sys_user` VALUES (2, 'test', '$2a$10$0ilP4ZD1kLugYwLCs4pmb.ZT9cFqzOZTNaMiHxrBnVIQUGUwEvBIO', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', 'test@qq.com', NULL, '2021-01-30 08:20:22', '2021-01-30 08:55:57', '2020-12-30 08:38:37', 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (4, 1, 6);
INSERT INTO `sys_user_role` VALUES (7, 1, 3);
INSERT INTO `sys_user_role` VALUES (13, 2, 3);

-- ----------------------------
-- Table structure for tb_hotel
-- ----------------------------
DROP TABLE IF EXISTS `tb_hotel`;
CREATE TABLE `tb_hotel`  (
  `id` bigint NOT NULL COMMENT '酒店id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '酒店名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '酒店地址',
  `price` int NOT NULL COMMENT '酒店价格',
  `score` int NOT NULL COMMENT '酒店评分',
  `brand` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '酒店品牌',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所在城市',
  `star_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '酒店星级，1星到5星，1钻到5钻',
  `business` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商圈',
  `latitude` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '纬度',
  `longitude` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '经度',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '酒店图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_hotel
-- ----------------------------
INSERT INTO `tb_hotel` VALUES (36934, '7天连锁酒店(上海宝山路地铁站店)', '静安交通路40号', 336, 37, '7天酒店', '上海', '二钻', '四川北路商业区', '31.251433', '121.47522', 'https://m.tuniucdn.com/fb2/t1/G1/M00/3E/40/Cii9EVkyLrKIXo1vAAHgrxo_pUcAALcKQLD688AAeDH564_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (38609, '速8酒店(上海赤峰路店)', '广灵二路126号', 249, 35, '速8', '上海', '二钻', '四川北路商业区', '31.282444', '121.479385', 'https://m.tuniucdn.com/fb2/t1/G2/M00/DF/96/Cii-TFkx0ImIQZeiAAITil0LM7cAALCYwKXHQ4AAhOi377_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (38665, '速8酒店上海中山北路兰田路店', '兰田路38号', 226, 35, '速8', '上海', '二钻', '长风公园地区', '31.244288', '121.422419', 'https://m.tuniucdn.com/fb2/t1/G2/M00/EF/86/Cii-Tlk2mV2IMZ-_AAEucgG3dx4AALaawEjiycAAS6K083_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (38812, '7天连锁酒店(上海漕溪路地铁站店)', '徐汇龙华西路315弄58号', 298, 37, '7天酒店', '上海', '二钻', '八万人体育场地区', '31.174377', '121.442875', 'https://m.tuniucdn.com/fb2/t1/G2/M00/E0/0E/Cii-TlkyIr2IEWNoAAHQYv7i5CkAALD-QP2iJwAAdB6245_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (39106, '7天连锁酒店（上海莘庄地铁站店）', '闵行莘庄镇七莘路299号', 348, 41, '7天酒店', '上海', '二钻', '莘庄工业区', '31.113812', '121.375869', 'https://m.tuniucdn.com/fb2/t1/G2/M00/D8/11/Cii-T1ku2zGIGR7uAAF1NYY9clwAAKxZAHO8HgAAXVN368_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (39141, '7天连锁酒店(上海五角场复旦同济大学店)', '杨浦国权路315号', 349, 38, '7天酒店', '上海', '二钻', '江湾、五角场商业区', '31.290057', '121.508804', 'https://m.tuniucdn.com/fb2/t1/G2/M00/C7/E3/Cii-T1knFXCIJzNYAAFB8-uFNAEAAKYkQPcw1IAAUIL012_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (45845, '上海西藏大厦万怡酒店', '虹桥路100号', 589, 45, '万怡', '上海', '四钻', '徐家汇地区', '31.192714', '121.434717', 'https://m.tuniucdn.com/fb3/s1/2n9c/48GNb9GZpJDCejVAcQHYWwYyU8T_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (45870, '上海临港豪生大酒店', '新元南路555号', 896, 45, '豪生', '上海', '四星级', '滴水湖临港地区', '30.871729', '121.81959', 'https://m.tuniucdn.com/fb3/s1/2n9c/2F5HoQvBgypoDUE46752ppnQaTqs_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (46829, '上海浦西万怡酒店', '恒丰路338号', 726, 46, '万怡', '上海', '四钻', '上海火车站地区', '31.242977', '121.455864', 'https://m.tuniucdn.com/fb3/s1/2n9c/x87VCoyaR8cTuYFZmKHe8VC6Wk1_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (47066, '上海浦东东站华美达酒店', '施新路958号', 408, 46, '华美达', '上海', '四钻', '浦东机场核心区', '31.147989', '121.759199', 'https://m.tuniucdn.com/fb3/s1/2n9c/2pNujAVaQbXACzkHp8bQMm6zqwhp_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (47478, '速8酒店(上海松江中心店)', '松江荣乐东路677号', 428, 35, '速8', '上海', '二钻', '佘山、松江大学城', '31.016712', '121.261606', 'https://m.tuniucdn.com/filebroker/cdn/res/07/36/073662e1718fccefb7130a9da44ddf5c_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (56201, '上海齐鲁万怡大酒店', '东方路838号', 873, 44, '万怡', '上海', '四星级', '浦东陆家嘴金融贸易区', '31.226031', '121.525801', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/B6/Cii-TF3eXKeIJeN7AASiKHbTtx4AAGRegDSBzMABKJA111_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (56214, '上海浦东华美达大酒店', '新金桥路18号', 830, 45, '华美达', '上海', '四星级', '浦东金桥地区', '31.244916', '121.590752', 'https://m.tuniucdn.com/fb3/s1/2n9c/3jtXiuMKZEXJAuKuAkc47yLCjUBt_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (56227, '上海圣淘沙万怡酒店', '南桥镇南桥路1号', 899, 45, '万怡', '上海', '四星级', '奉贤开发区', '30.910917', '121.456525', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/B9/Cii-U13eXSiIdJjXAARSA6FywFYAAGRnwHvy1AABFIb158_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (56392, '上海银星皇冠假日酒店', '番禺路400号', 809, 47, '皇冠假日', '上海', '五星级', '徐家汇地区', '31.202768', '121.429524', 'https://m.tuniucdn.com/fb3/s1/2n9c/37ucQ38K3UFdcRqntJ8M5dt884HR_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (56852, '上海财大豪生大酒店', '武东路188号', 592, 46, '豪生', '上海', '五钻', '江湾/五角场商业区', '31.304182', '121.492936', 'https://m.tuniucdn.com/fb3/s1/2n9c/2jGHezLZvPZqC9cBGesbP5vAhCXi_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (56912, '上海华凯华美达广场酒店', '月华路9号', 747, 40, '华美达', '上海', '四钻', '奉贤开发区', '30.814382', '121.464521', 'https://m.tuniucdn.com/fb3/s1/2n9c/45iaCNCuZavJTxwTLskhVKzwynLD_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (56977, '上海五角场华美达大酒店', '黄兴路1888号', 499, 40, '华美达', '上海', '三钻', '江湾/五角场商业区', '31.292932', '121.519759', 'https://m.tuniucdn.com/fb3/s1/2n9c/26VREqAQdaGFvJdAJALVtjxcNMpL_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60214, '上海金茂君悦大酒店', '世纪大道88号（54楼办理入住）', 699, 46, '君悦', '上海', '五星级', '浦东陆家嘴金融贸易区', '31.235152', '121.506082', 'https://m.tuniucdn.com/fb3/s1/2n9c/7Azm3jvGUHuXe3eS1DrixAWVTXY_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60223, '上海希尔顿酒店', '静安华山路250号', 2688, 37, '希尔顿', '上海', '五星级', '静安寺地区', '31.219306', '121.445427', 'https://m.tuniucdn.com/filebroker/cdn/res/92/10/9210e74442aceceaf6e196d61fc3b6b1_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60359, '上海外高桥皇冠假日酒店', '杨高北路1000号', 3299, 46, '皇冠假日', '上海', '五星级', '浦东外高桥地区', '31.338944', '121.590611', 'https://m.tuniucdn.com/fb3/s1/2n9c/VcKUM9zUSiVgDhFioc6mWQoX9ES_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60363, '上海新世界丽笙大酒店', '南京西路88号', 1341, 46, '丽笙', '上海', '五星级', '人民广场地区', '31.23462', '121.47327', 'https://m.tuniucdn.com/fb3/s1/2n9c/2j31b7X3YzGkf4Li3phS6TG1mtwm_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60398, '上海复旦皇冠假日酒店', '邯郸路199号', 924, 47, '皇冠假日', '上海', '五星级', '江湾/五角场商业区', '31.295382', '121.502537', 'https://m.tuniucdn.com/fb3/s1/2n9c/2H1Gk8LHaBWZfYvR6NYYcGTvACmL_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60487, '上海外滩茂悦大酒店', '黄浦路199号', 689, 44, '君悦', '上海', '五星级', '外滩地区', '31.245409', '121.492969', 'https://m.tuniucdn.com/fb3/s1/2n9c/2Swp2h1fdj9zCUKsk63BQvVgKLTo_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60522, '上海嘉豪淮海国际豪生酒店', '汾阳路1号', 425, 45, '豪生', '上海', '四钻', '淮海路/新天地地区', '31.215497', '121.456297', 'https://m.tuniucdn.com/fb3/s1/2n9c/38UBi4QYuaF8jN94CxQ7tb7tjtmZ_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60916, '上海绿地万怡酒店', '沪宜公路3101号', 328, 45, '万怡', '上海', '四钻', '嘉定新城', '31.368523', '121.258567', 'https://m.tuniucdn.com/fb3/s1/2n9c/3VLwG9tTQQnp3M3MTeMTdx9nas9B_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60922, '上海虹桥祥源希尔顿酒店', '红松东路1116号', 1108, 45, '希尔顿', '上海', '五钻', '虹桥地区', '31.18746', '121.395312', 'https://m.tuniucdn.com/fb3/s1/2n9c/tQRqDTFkHnHzMZiDKjcGV81ekvc_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (60935, '上海虹口三至喜来登酒店', '四平路59号', 1899, 46, '喜来登', '上海', '五星级', '四川北路商业区', '31.2579', '121.487954', 'https://m.tuniucdn.com/fb3/s1/2n9c/3C3gxLxLjVwnkxJwJm8rd3f38kcd_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (61075, '上海雅居乐万豪酒店', '西藏中路555号', 1152, 46, '万豪', '上海', '五钻', '人民广场地区', '31.236681', '121.473529', 'https://m.tuniucdn.com/fb3/s1/2n9c/3FoT16PkXavKsssvktVvVq5Si6Cr_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (61083, '上海滴水湖皇冠假日酒店', '自由贸易试验区临港新片区南岛1号', 971, 44, '皇冠假日', '上海', '五钻', '滴水湖临港地区', '30.890867', '121.937241', 'https://m.tuniucdn.com/fb3/s1/2n9c/312e971Rnj9qFyR3pPv4bTtpj1hX_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (309208, '北京王府井希尔顿酒店', '王府井东街8号', 1679, 46, '希尔顿', '北京', '五钻', '天安门/王府井地区', '39.914539', '116.413392', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/10/Cii-TF3ePt2IX9UEAALb6VYBSmoAAGKMgGsuW8AAtwB147_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (394559, '北京东方君悦大酒店', '长安街1号东方广场', 686, 45, '君悦', '北京', '五星级', '天安门/王府井地区', '39.909635', '116.414621', 'https://m.tuniucdn.com/fb3/s1/2n9c/3mFqcNSh7eEo9yc3Rw2P5HDNTdDe_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (394617, '北京宝辰饭店', '建国门内大街甲18号', 418, 44, '豪生', '北京', '四星级', '北京站/建国门地区', '39.905768', '116.428153', 'https://m.tuniucdn.com/fb3/s1/2n9c/NEYa6EfDHuhhb19Ct85WBbkKHZU_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (394796, '北京新云南皇冠假日酒店', '东北三环西坝河太阳宫桥东北角云南大厦', 485, 46, '皇冠假日', '北京', '五星级', '国展中心地区', '39.972409', '116.434698', 'https://m.tuniucdn.com/fb3/s1/2n9c/dfP8K782eTsohQWSRdkd7St9LA2_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (395434, '北京希尔顿酒店', '东三环北路东方路1号', 350, 45, '希尔顿', '北京', '五星级', '燕莎/朝阳公园商业区', '39.952703', '116.462387', 'https://m.tuniucdn.com/fb3/s1/2n9c/3fwNbKGhk6XCrkdVyxwhC5uGpLVy_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (395702, '北京首都机场希尔顿酒店', '首都机场3号航站楼三经路1号', 222, 46, '希尔顿', '北京', '五钻', '首都机场/新国展地区', '40.048969', '116.619566', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/10/Cii-U13ePtuIMRSjAAFZ58NGQrMAAGKMgADZ1QAAVn_167_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (395787, '北京海航大厦万豪酒店', '霄云路甲26号', 1302, 46, '万豪', '北京', '五钻', '燕莎/朝阳公园商业区', '39.959861', '116.467363', 'https://m.tuniucdn.com/fb3/s1/2n9c/3zFiWi2C9SmbcQwCZgJFQC9ahvs5_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (395799, '北京国际艺苑皇冠假日酒店', '王府井大街48号', 636, 44, '皇冠假日', '北京', '五星级', '天安门/王府井地区', '39.918994', '116.411277', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/10/Cii-U13ePvyIahjPAAMykV278aEAAGKOQO9e4UAAzKp283_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (395815, '北京明豪华美达酒店', '天竺镇府前一街13号', 558, 46, '华美达', '北京', '四钻', '首都机场/新国展地区', '40.062832', '116.580678', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/13/Cii-U13eP2mIKCwvAAODTZXT-fAAAGKVAA9taIAA4Nl245_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (396189, '北京朝阳悠唐皇冠假日酒店', '三丰北里3号', 944, 46, '皇冠假日', '北京', '五钻', '三里屯/工体/东直门地区', '39.92129', '116.43847', 'https://m.tuniucdn.com/fb3/s1/2n9c/tT6ipLain1ZovR5gnQ7tJ4KKym5_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (396471, '维也纳酒店（北京花园路店）', '海淀北太平庄花园路甲17号', 381, 36, '维也纳', '北京', '三钻', '马甸、安贞地区', '39.970837', '116.365244', 'https://m.tuniucdn.com/filebroker/cdn/res/17/00/1700926908bae6ba3e5ef96de7b7d4cc_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (396506, '北京金隅喜来登酒店', '北三环东路36号', 357, 47, '喜来登', '北京', '五星级', '马甸/安贞地区', '39.967163', '116.4099', 'https://m.tuniucdn.com/fb3/s1/2n9c/29FW2WtGzzUtPhWR1LKxcFZAVa9P_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (413460, '7天连锁酒店(北京天坛店)', '东城天坛东里甲48号', 753, 38, '7天酒店', '北京', '二钻', '前门、崇文门商贸区', '39.875786', '116.421987', 'https://m.tuniucdn.com/fb2/t1/G2/M00/C7/D8/Cii-T1knCK6IWTtxAAI0plLButMAAKYTAJu-woAAjS-422_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (413476, '7天连锁酒店(北京南站店)', '丰台马家堡西路22号', 478, 37, '7天酒店', '北京', '二钻', '永定门、南站、大红门、南苑地区', '39.845363', '116.372327', 'https://m.tuniucdn.com/fb2/t1/G1/M00/26/B7/Cii-U1knCtaISM4VAAHkEQd-mrAAAKw0ALVCwEAAeQp741_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (414168, '7天连锁酒店(北京西直门店)', '西城平安里西大街翠花街育幼胡同甲20-22号', 419, 37, '7天酒店', '北京', '二钻', '西单、金融街地区', '39.931338', '116.364982', 'https://m2.tuniucdn.com/filebroker/cdn/res/bc/66/bc666859edf4fc072a8006c66758058d_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (414481, '7天连锁酒店(北京团结湖地铁站店)', '朝阳团结湖北里9号楼', 525, 36, '7天酒店', '北京', '二钻', '燕莎、三里屯商业区', '39.928457', '116.466132', 'https://m.tuniucdn.com/fb2/t1/G1/M00/38/2D/Cii9EFkv2-uIPTaBAALX6P-rbdUAALPpwHv4ykAAtgA277_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (414698, '7天连锁酒店（北京798艺术区店）', '朝阳北京市朝阳区酒仙桥北路（798艺术区北门）彩虹路6号-电通创意广场大院内', 553, 37, '7天酒店', '北京', '二钻', '望京、酒仙桥、798地区', '39.990671', '116.498452', 'https://m.tuniucdn.com/fb2/t1/G2/M00/D8/11/Cii-T1ku2zqIN7SiAAEdvT6RrjUAAKxZQKFooYAAR3V090_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (415600, '如家酒店(北京朝阳北路传媒大学褡裢坡地铁站店)', '三间房乡褡裢坡村青年沟西侧558号', 259, 47, '如家', '北京', '二钻', '传媒大学/管庄地区', '39.923212', '116.560023', 'https://m.tuniucdn.com/fb3/s1/2n9c/3NezpxNZWQMdNXibwbMkQuAZjDyJ_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (415659, '7天连锁酒店（北京紫竹桥店）', '海淀北洼路甲3号', 781, 42, '7天酒店', '北京', '二钻', '西直门及北京展览馆地区', '39.936138', '116.302405', 'https://m.tuniucdn.com/fb2/t1/G1/M00/3C/22/Cii9EVkxPMqIZJz-AAIh0esETAIAALXbgNQkH8AAiHp053_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (416121, '如家酒店(北京西客站北广场店)', '莲花池东路120-2号6层', 275, 43, '如家', '北京', '二钻', '北京西站/丽泽商务区', '39.896449', '116.317382', 'https://m.tuniucdn.com/fb3/s1/2n9c/42DTRnKbiYoiGFVzrV9ZJUxNbvRo_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (416260, '7天连锁酒店(北京通州八里桥店)', '永顺镇西马庄小区', 534, 38, '7天酒店', '北京', '二钻', '果园环岛、通州区', '39.915443', '116.631871', 'https://m.tuniucdn.com/fb2/t1/G2/M00/DF/5A/Cii-TlkxkeGIKM0oAAGOb64RvToAALBAAH9Fg8AAY6H201_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (416268, '和颐酒店(北京传媒大学财满街店)', '朝阳路高井176号', 524, 46, '和颐', '北京', '三钻', '国贸地区', '39.918277', '116.53015', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/13/Cii-TF3eP5GIJIOLAAUwsIVCxdAAAGKXgK5a0IABTDI239_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (416307, '速8酒店(北京新国展首都机场后沙峪店)', '后沙峪镇裕民大街32号', 350, 39, '速8', '北京', '二钻', '首都机场/新国展地区', '40.099019', '116.543655', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/3E/Cii-TF3eRgGIHCkKAAP_ATvriiQAAGL0AIoLtUAA_8Z513_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (432335, '7天连锁酒店(上海北外滩国际客运中心地铁站店)', '唐山路145号', 249, 35, '7天酒店', '上海', '二钻', '北外滩地区', '31.252585', '121.498753', 'https://m2.tuniucdn.com/filebroker/cdn/res/c1/ba/c1baf64418437c56617f89840c6411ef_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (433576, '如家酒店(上海南京路步行街店)', '南京东路480号保安坊内', 379, 44, '如家', '上海', '二钻', '人民广场地区', '31.236454', '121.480948', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/BA/Cii-U13eXVaIQmdaAAWxgzdXXxEAAGRrgNIOkoABbGb143_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (434082, '如家酒店·neo(上海外滩城隍庙小南门地铁站店)', '复兴东路260号', 392, 44, '如家', '上海', '二钻', '豫园地区', '31.220706', '121.498769', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/B6/Cii-U13eXLGIdHFzAAIG-5cEwDEAAGRfQNNIV0AAgcT627_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (441836, '如家酒店(北京国展三元桥店)', '西坝河东里36号', 458, 47, '如家', '北京', '二钻', '国展中心地区', '39.966238', '116.450142', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/39/Cii-TF3eRTGITp1UAAYIilRD7skAAGLngIuAnQABgii479_w200_h200_c1_t0.png');
INSERT INTO `tb_hotel` VALUES (485775, '如家酒店(上海闵行华东师范大学吴泾店)', '吴泾镇宝秀路977号', 161, 45, '如家', '上海', '二钻', '交大/闵行经济开发区', '31.047135', '121.46224', 'https://m.tuniucdn.com/fb3/s1/2n9c/V8pz15CkiMX5xYJRmbbp5zkKWJ8_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (489756, '7天连锁酒店(北京平谷店)', '文化北街4-16号', 544, 40, '7天酒店', '北京', '二钻', '平谷城区', '40.14308', '117.111554', 'https://m2.tuniucdn.com/filebroker/cdn/res/2e/b4/2eb4edb22ddb981307d8570beb1d746d_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (517915, '如家酒店·neo(深圳草埔地铁站店)', '布吉路1036号', 159, 44, '如家', '深圳', '二钻', '田贝/水贝珠宝城', '22.583191', '114.118499', 'https://m.tuniucdn.com/fb3/s1/2n9c/228vhBCQmFRFWQBYX1cgoFQb6x58_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (527938, '深圳好日子皇冠假日酒店', '福华一路28号', 590, 46, '皇冠假日', '深圳', '五星级', '会展中心/CBD', '22.537153', '114.053529', 'https://m.tuniucdn.com/fb3/s1/2n9c/b6Ztz5jn4MngK3Hzfxuu9JGsjrm_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (541619, '如家酒店(上海莘庄地铁站龙之梦商业广场店)', '莘庄镇莘浜路172号', 149, 44, '如家', '上海', '二钻', '莘庄工业区', '31.105797', '121.37755', 'https://m.tuniucdn.com/fb3/s1/2n9c/3mKs3jETvJDj3dDdkRB9UyLLvPna_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (542068, '上海茂业华美达广场酒店', '沪南路938号', 646, 40, '华美达', '上海', '三钻', '浦东新国际博览中心', '31.182761', '121.554106', 'https://m.tuniucdn.com/fb3/s1/2n9c/2139uDFUZ2VKxrathwSeeE4DwyFU_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (546869, '深圳彭年万丽酒店', '嘉宾路2002号（毗邻金光华购物广场）', 701, 46, '万丽', '深圳', '五钻', '罗湖口岸/火车站', '22.540989', '114.122665', 'https://m.tuniucdn.com/fb3/s1/2n9c/gwAbqEXFUpjUBmnxUfK89p3zBBT_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (581859, '维也纳酒店（北京丰益桥店）（原申晨酒店）', '丰台丰管路8号', 648, 45, '维也纳', '北京', '三钻', '北京西站、丽泽商务区', '39.857707', '116.312482', 'https://m2.tuniucdn.com/filebroker/cdn/res/97/43/97438481b9e79abad429e5c30d7f303f_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (584697, '7天连锁酒店（深圳华强赛格广场店）', '华强南路3024号赛格苑1栋', 362, 36, '7天酒店', '深圳', '二钻', '华强北商业区', '22.539831', '114.087899', 'https://m.tuniucdn.com/fb2/t1/G2/M00/D8/11/Cii-T1ku2zmIcP4sAAEw8iuLXFgAAKxZQH7HVYAATEK972_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (593228, '7天连锁酒店（北京颐和园店）', '海淀厢红旗路功德寺桥北侧', 730, 38, '7天酒店', '北京', '二钻', '香山、八大处风景区', '40.003959', '116.256718', 'https://m2.tuniucdn.com/filebroker/cdn/res/55/84/55841f502c5a711e66dd5454b64f559b_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (598591, '上海丽昂豪生大酒店', '金新路99号', 529, 47, '豪生', '上海', '四钻', '浦东金桥地区', '31.252496', '121.600085', 'https://m.tuniucdn.com/fb3/s1/2n9c/2KfPPyPx9rWyVXif2CUuxv61Nryc_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (607915, '汉庭酒店(深圳皇岗店)', '滨河大道6033号海滨广场国皇大厦3楼', 313, 42, '汉庭', '深圳', '二钻', '皇岗口岸/福田口岸', '22.528101', '114.064221', 'https://m.tuniucdn.com/fb3/s1/2n9c/qMyCJVYuW21nsCeEBt8CMfmEhra_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (608374, '如家酒店(上海浦东机场龙东大道合庆店)', '东川公路5863号', 160, 45, '如家', '上海', '二钻', '浦东机场核心区', '31.237662', '121.718556', 'https://m.tuniucdn.com/fb3/s1/2n9c/LUYxGGV4pzjKeN5a69K4deU8JD8_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (609023, '如家酒店·neo(上海外高桥保税区北地铁站店)', '花山路1209号', 266, 45, '如家', '上海', '二钻', '浦东外高桥地区', '31.351148', '121.585606', 'https://m.tuniucdn.com/fb3/s1/2n9c/3cJ6KTfms9cfEnME8WRkQQBXBkYm_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (609372, '豪派特华美达广场酒店(深圳北站店)', '民治街道梅龙路与民旺路交汇处', 498, 45, '华美达', '深圳', '四钻', '深圳北站地区', '22.620501', '114.033874', 'https://m.tuniucdn.com/fb3/s1/2n9c/3G5TnUCPbjGYHAVWfvuixw8bs69t_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (624417, '深圳君悦酒店', '宝安南路1881号', 442, 47, '君悦', '深圳', '五钻', '万象城/京基100', '22.537247', '114.111182', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/EA/Cii-TF3ZpVmIVDJ9AAXvJftz_AgAAFrrQKbI4oABe89086_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (628327, '如家酒店·neo(深圳罗湖口岸国贸地铁站店)', '人民南路2011号', 223, 45, '如家', '深圳', '二钻', '罗湖口岸/火车站', '22.536734', '114.118336', 'https://m.tuniucdn.com/fb3/s1/2n9c/2rKHmQWHYiY8GZA3xBHpFKCLZwZo_w200_h200_c1_t0.png');
INSERT INTO `tb_hotel` VALUES (629023, '和颐酒店(北京十里河欢乐谷店)', '十八里店乡周家庄288号', 390, 47, '和颐', '北京', '四钻', '劲松/潘家园地区', '39.853354', '116.483437', 'https://m.tuniucdn.com/fb3/s1/2n9c/28hnDdqn5uzuzCKYkw2x4pYmunXM_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (629729, '7天连锁酒店(上海张江高科园区店)', '浦东新区蔡伦路103号', 267, 36, '7天酒店', '上海', '二钻', '浦东张江地区', '31.196154', '121.62071', 'https://m2.tuniucdn.com/filebroker/cdn/res/d9/61/d961508a10865b9b29c033064f31b913_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (635963, '深圳龙岗珠江皇冠假日酒店', '龙岗中心城龙翔大道9009号珠江广场', 737, 46, '皇冠假日', '深圳', '五星级', '龙岗中心区/大运新城', '22.722941', '114.250002', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/EA/Cii-U13ZpWGIasKjAAY1SNE36KMAAFrrwMNoAwABjVg973_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (636080, '深圳大中华喜来登酒店', '福华路一号大中华国际交易广场', 1556, 47, '喜来登', '深圳', '五星级', '会展中心/CBD', '22.535567', '114.062005', 'https://m.tuniucdn.com/fb3/s1/2n9c/3hQRTmAUW9PegTjxMiEfYwh2HnKp_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (644417, '上海外高桥喜来登酒店', '自由贸易试验区基隆路28号（二号门内）', 2419, 46, '喜来登', '上海', '五钻', '浦东外高桥地区', '31.350989', '121.588751', 'https://m.tuniucdn.com/fb3/s1/2n9c/1Rrtg9n7PdMEivVDhsehbJBrEre_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (648219, '上海金桥红枫万豪酒店', '新金桥路15号', 891, 47, '万豪', '上海', '五钻', '浦东金桥地区', '31.244061', '121.591153', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/B6/Cii-TF3eXKuIR_a0AAUx-Xd2JLQAAGRfACSpvUABTIR560_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (657192, '深圳宝安中天美景华美达酒店', '新桥街道万丰社区中心路7-1号', 498, 45, '华美达', '深圳', '四钻', '深圳国际会展中心商圈', '22.716473', '113.826391', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/EA/Cii-U13ZpVeIRbhTAAOzGZSDtlcAAFrrQEWM-AAA7Mx626_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (659496, '维也纳酒店(深圳国王店)', '龙华新区龙华龙观西路95号', 714, 37, '维也纳', '深圳', '三钻', '深圳北站地区', '22.65892', '114.006817', 'https://m2.tuniucdn.com/filebroker/cdn/res/b4/76/b476cacc575a7ff237128ba2fd63923a_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (670673, '北京龙城华美达酒店', '昌平路319号', 506, 45, '华美达', '北京', '四钻', '回龙观/天通苑地区', '40.084219', '116.304313', 'https://m.tuniucdn.com/fb3/s1/2n9c/T3WruZV3S4MfcxdD1HFVhZjaBLW_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (672207, '和颐至尊酒店(北京望京798店)', '酒仙桥北路9号荧屏里4号楼', 579, 44, '和颐', '北京', '四钻', '望京/酒仙桥/798地区', '39.98835', '116.491217', 'https://m.tuniucdn.com/fb3/s1/2n9c/2y56zwK8kd2tBuRUyF7XeJ2ucvWM_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (684720, '和颐酒店(深圳罗湖口岸火车地铁站店)', '沿河南路1064号', 208, 47, '和颐', '深圳', '四钻', '罗湖口岸/火车站', '22.533753', '114.122491', 'https://m.tuniucdn.com/fb3/s1/2n9c/2LFgB2iFawKKoGADwzhW6jpCSaJT_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (696948, '上海新园华美达广场酒店', '漕宝路509号', 1290, 45, '华美达', '上海', '四星级', '光大会展中心/漕河泾地区', '31.163802', '121.405618', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/B6/Cii-TF3eXHuIE57sAAZx8iP6rMIAAGRbgAH09gABnIK621_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (706343, '速8酒店（北京西客站北广场店）', '丰台莲花池东路126号', 268, 39, '速8', '北京', '二钻', '北京西站、丽泽商务区', '39.896623', '116.315586', 'https://m.tuniucdn.com/fb2/t1/G2/M00/E3/46/Cii-TlkzMXWIL0sAAAGG8a3YwiwAALJlgG-r5YAAYcJ067_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (707357, '维也纳酒店（上海南站光大店）', '徐汇桂林路46号（钦州南路路口往南100米）', 3288, 36, '维也纳', '上海', '三钻', '光大会展中心', '31.156297', '121.419948', 'https://m2.tuniucdn.com/filebroker/cdn/res/f7/13/f713f8f98d777d8d53aafefb37a79ef6_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (711837, '速8酒店（北京立水桥店）', '朝阳安立路3号1幢3层', 268, 36, '速8', '北京', '二钻', '亚运村、奥体中心地区', '40.043717', '116.410962', 'https://m2.tuniucdn.com/filebroker/cdn/res/b3/87/b3876eaf16af62521cf6fb474504b8ca_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (727679, '7天连锁酒店(深圳步行街老街地铁站二店)', '罗湖区东门中路2216号华佳广场12-14楼（东门天桥东头）', 742, 40, '7天酒店', '深圳', '二钻', '东门商业区', '22.54585', '114.122227', 'https://m.tuniucdn.com/fb2/t1/G1/M00/39/99/Cii9EFkwVMKIP_mCAAI3fOHlS1wAALSDAMeO2MAAjeU309_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (728180, '如家酒店(深圳宝安西乡地铁站店)', '西乡大道298-7号（富通城二期公交站旁）', 184, 43, '如家', '深圳', '二钻', '宝安体育中心商圈', '22.569693', '113.860186', 'https://m.tuniucdn.com/fb3/s1/2n9c/FHdugqgUgYLPMoC4u4rdTbAPrVF_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (728415, '如家酒店·neo(深圳东门步行街晒布地铁站店)', '晒布路67号', 152, 46, '如家', '深圳', '二钻', '东门商业区', '22.550183', '114.120771', 'https://m.tuniucdn.com/fb2/t1/G6/M00/25/57/Cii-U13PFNWISSnQAAEpTtoilsQAAEVWgEvur8AASlm647_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (728461, '汉庭酒店(深圳会展中心店)', '新洲路世纪工艺品广场307栋', 258, 44, '汉庭', '深圳', '二钻', '皇岗口岸/福田口岸', '22.518026', '114.046061', 'https://m.tuniucdn.com/fb2/t1/G6/M00/25/56/Cii-TF3PFKOIPl0JAANm4ge6DdMAAEVTQK2SP8AA2b6365_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (728604, '如家酒店·neo(深圳南山地铁站南山市场店)', '南新路顺富街18号化州大厦', 198, 43, '如家', '深圳', '二钻', '科技园', '22.525561', '113.920058', 'https://m.tuniucdn.com/fb2/t1/G6/M00/25/57/Cii-TF3PFLmIDGWiAAPHkaNTuOIAAEVVQBGazAAA8ep611_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (730454, '7天连锁酒店(深圳万象城店)', '罗湖区嘉宾路4025号城市天地广场内', 552, 43, '7天酒店', '深圳', '二钻', '罗湖口岸/火车站', '22.537078', '114.113733', 'https://m.tuniucdn.com/fb2/t1/G2/M00/C8/4C/Cii-Tlknhz2IFnYNAAInF2jEK14AAKbcQNB5M8AAicv660_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (730569, '7天连锁酒店(深圳梅林卓越城店)', '孖岭地铁口C出口左手边，青年学院对面', 269, 39, '7天酒店', '深圳', '二钻', '莲花山/梅林', '22.568701', '114.068464', 'https://m.tuniucdn.com/fb2/t1/G2/M00/D8/0B/Cii-Tlku2dOISeGXAAAUw3MvPrIAAKxYALB2VAAABTb555_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (730968, '7天连锁酒店(深圳宝安地铁站店)', '宝安区宝安25区创业二路步行街金麒麟服装广场4栋', 314, 36, '7天酒店', '深圳', '二钻', '宝安中心区/前海', '22.568162', '113.900968', 'https://m.tuniucdn.com/fb2/t1/G2/M00/D8/0B/Cii-T1ku2cqIWTOOAAAS_bvRI5UAAKxXwILCFYAABMV285_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (751035, '7天连锁酒店(上海自贸区北门地铁站店)(原外高桥地铁北站店)', '花山路706号', 328, 39, '7天酒店', '上海', '二钻', '浦东外高桥地区', '31.348029', '121.576896', 'https://m.tuniucdn.com/fb2/t1/G1/M00/3A/21/Cii-U1kwxUCINXaHAAGmh7z6qRAAALUdwMKGREAAaaf928_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (844350, '7天连锁酒店(深圳世界之窗店)', '南山区白石洲沙河街2号金三角大厦', 769, 36, '7天酒店', '深圳', '二钻', '华侨城', '22.540501', '113.968858', 'https://m.tuniucdn.com/fb2/t1/G2/M00/D8/11/Cii-TFku2zmIOdjCAAAQJpLFhEEAAKxZQIEvQ0AABA-920_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2316304, '如家酒店(深圳双龙地铁站店)', '龙岗街道龙岗墟社区龙平东路62号', 135, 45, '如家', '深圳', '二钻', '龙岗中心区/大运新城', '22.730828', '114.278337', 'https://m.tuniucdn.com/fb3/s1/2n9c/4AzEoQ44awd1D2g95a6XDtJf3dkw_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2329005, '维也纳酒店(深圳华之沙店)', '福强路新洲九街28号', 651, 39, '维也纳', '深圳', '三钻', '皇岗口岸/福田口岸', '22.524835', '114.048214', 'https://m2.tuniucdn.com/filebroker/cdn/res/88/f0/88f05cd11990ef39ae187886c76f40a5_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2351601, '深圳蛇口希尔顿南海酒店', '望海路1177号', 509, 47, '希尔顿', '深圳', '五钻', '深圳湾口岸/蛇口', '22.479373', '113.916013', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/EA/Cii-TF3ZpXOIfa6fAAJjiUOiuYgAAFrtgDtgpQAAmOh799_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2359697, '如家酒店(北京上地安宁庄东路店)', '清河小营安宁庄东路18号20号楼', 420, 46, '如家', '北京', '二钻', '上地产业园/西三旗', '40.041322', '116.333316', 'https://m.tuniucdn.com/fb3/s1/2n9c/2wj2f8mo9WZQCmzm51cwkZ9zvyp8_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (5865979, '北京丽都皇冠假日酒店', '将台路6号', 1168, 47, '皇冠假日', '北京', '五钻', '望京/酒仙桥/798地区', '39.978133', '116.478642', 'https://m.tuniucdn.com/fb3/s1/2n9c/Yo4xL3RUsYUnDDc5QcQWj7sCrUX_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (5870456, '上海宝华万豪酒店', '广中西路333号', 922, 47, '万豪', '上海', '五钻', '大宁国际商业区', '31.279371', '121.446327', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/BA/Cii-U13eXVqIZXDFAAUC_xbrQDAAAGRrwPRyOcABQMX057_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (5871652, '上海圣诺亚皇冠假日酒店', '金沙江路1699号', 770, 46, '皇冠假日', '上海', '五钻', '长风公园地区', '31.232346', '121.377709', 'https://m.tuniucdn.com/fb3/s1/2n9c/J4sP7qRSHa9rFYnKTW75ZPB393M_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (5872067, '崇明金茂凯悦酒店', '陈家镇揽海路799弄', 1024, 46, '凯悦', '上海', '五钻', '崇明岛/长兴岛/横沙岛', '31.466563', '121.799671', 'https://m.tuniucdn.com/fb3/s1/2n9c/fsKrbnNsmSsYnNLmhh3ZvVjZ5cA_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (5873072, '速8酒店（上海火车站北广场店）', '闸北芷江西路796号', 190, 41, '速8', '上海', '二钻', '上海火车站地区', '31.255579', '121.452903', 'https://m2.tuniucdn.com/filebroker/cdn/res/96/6d/966d6596e6cb7b48c9cc1d7da79b57c8_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (7094829, '汉庭酒店(深圳华强南店)', '松岭路9号（南园小学对面）', 215, 40, '汉庭', '深圳', '二钻', '华强北商业区', '22.536842', '114.094316', 'https://m.tuniucdn.com/fb3/s1/2n9c/3WDhaZZ9yALHw8yNiU6HJyrdC3u5_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (189429168, '7天连锁酒店(北京大兴黄村清源路地铁站店)', '清源西路55号', 392, 38, '7天酒店', '北京', '二钻', '大兴农业生态观光区', '39.743751', '116.321676', 'https://m.tuniucdn.com/fb2/t1/G1/M00/4F/25/Cii9EFk3LmOIFtnDAAHm5kdIiM8AAL1FQM8kG0AAeb-418_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197487869, '如家酒店(北京第二外国语大学南门双桥地铁站店)', '三间房乡新房村1号', 321, 47, '如家', '北京', '二钻', '传媒大学/管庄地区', '39.90635', '116.565528', 'https://m.tuniucdn.com/fb3/s1/2n9c/ZkgDAs8tTMvgFHdVPpikNqENEn1_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197488318, '速8酒店（北京立水桥地铁南站店）', '朝阳北苑路18号院3号楼4层', 344, 36, '速8', '北京', '二钻', '亚运村、奥体中心地区', '40.043689', '116.414138', 'https://m.tuniucdn.com/fb2/t1/G1/M00/36/4D/Cii9EVkvP72IYYjgAAF7yZeWV-wAALMQACOARMAAXvh983_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197492277, '速8酒店（北京平谷兴谷环岛店）', '平谷平谷大街31号', 614, 39, '速8', '北京', '二钻', '平谷城区', '40.159255', '117.12401', 'https://m.tuniucdn.com/fb2/t1/G1/M00/38/D5/Cii9EFkwFCiII79zAAHKsXy_LAoAALQuQEmEZ4AAcrJ339_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197492479, '如家酒店(北京顺义中心地铁站店)', '光明南大街14号', 306, 45, '如家', '北京', '二钻', '顺义温泉休闲区', '40.124783', '116.65751', 'https://m.tuniucdn.com/fb3/s1/2n9c/2hNBSjmMTk6JQ2o8ixr5s3ioevhB_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197496182, '和颐酒店(北京团结湖地铁站店)', '团结湖路9号楼', 341, 44, '和颐', '北京', '三钻', '燕莎/朝阳公园商业区', '39.930731', '116.466602', 'https://m.tuniucdn.com/fb3/s1/2n9c/2gK41VpMb4AwyNkwQEkfFo83uTUU_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197496980, '速8酒店(北京温都水城王府店)', '昌平北七家镇平西府村（温都水城东200米）', 585, 39, '速8', '北京', '二钻', '小汤山温泉度假区', '40.10144', '116.380641', 'https://m.tuniucdn.com/fb2/t1/G2/M00/C7/CB/Cii-T1km_5eICnpJAAHOWN1GylMAAKYJwF0Hp8AAc5w000_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197516492, '速8酒店（北京南苑东高地店）', '丰台南大红门路东营房15号', 651, 39, '速8', '北京', '二钻', '永定门、南站、大红门、南苑地区', '39.78996', '116.42081', 'https://m.tuniucdn.com/fb2/t1/G1/M00/3B/D8/Cii-U1kxKGWIQlaxAAIdkjkSALkAALXDQMFbTsAAh2q158_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197835483, '7天连锁酒店(深圳大学桃园店)', '南山区桃园西路160号', 431, 36, '7天酒店', '深圳', '二钻', '科技园', '22.532576', '113.916362', 'https://m.tuniucdn.com/fb2/t1/G1/M00/38/40/Cii9EFkv4XKIQN85AAFUcDrkXe0AALPvwPRn08AAVSI037_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (197837109, '如家酒店·neo(深圳龙岗大道布吉地铁站店)', '布吉镇深惠路龙珠商城', 127, 43, '如家', '深圳', '二钻', '布吉/深圳东站', '22.602482', '114.123284', 'https://m.tuniucdn.com/fb2/t1/G6/M00/25/58/Cii-TF3PFZOIA7jwAAKInGFN4xgAAEVbAGeP4AAAoi0485_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (198323591, '汉庭酒店(深圳北站龙华汽车站店)', '龙华新区建辉路2号', 209, 46, '汉庭', '深圳', '二钻', '深圳北站地区', '22.671313', '114.02784', 'https://m.tuniucdn.com/fb3/s1/2n9c/2dkB2HzbaBUJ7adZZfZaeS9JCvjP_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200208940, '上海浦东喜来登由由公寓', '浦建路38号', 3168, 45, '喜来登', '上海', '五钻', '浦东新国际博览中心', '31.208553', '121.518552', 'https://m.tuniucdn.com/fb3/s1/2n9c/m3Nrm37Yx6YV4NwqRvSYnFRNSGk_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200210163, '7天连锁酒店(上海徐家汇宜山路地铁站店)', '徐汇钦州北路78号', 219, 35, '7天酒店', '上海', '二钻', '光大会展中心', '31.180615', '121.422916', 'https://m.tuniucdn.com/fb2/t1/G2/M00/DF/96/Cii-Tlkx0TOIGtOzAAEe_xcDxeIAALCZQJyxf4AAR8X941_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200214437, '上海浦东机场华美达广场酒店', '浦东机场启航路1100号', 600, 45, '华美达', '上海', '四星级', '浦东机场核心区', '31.160969', '121.799086', 'https://m.tuniucdn.com/fb3/s1/2n9c/2D2gbXDgrMx76uWfwzmoWpmSCCXx_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200214538, '上海绿地万豪酒店', '江滨路99号（打浦路底）', 720, 43, '万豪', '上海', '五星级', '打浦桥地区', '31.192103', '121.47298', 'https://m.tuniucdn.com/fb3/s1/2n9c/268jVMuWdYok5ehGFhQ2QNhBhUhs_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200214715, '上海浦东喜来登由由大酒店', '浦建路38号', 2489, 45, '喜来登', '上海', '五星级', '浦东新国际博览中心', '31.208739', '121.518305', 'https://m.tuniucdn.com/fb3/s1/2n9c/36t2KUGs4h5YgYSaLSkr5pMXLM54_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200214824, '7天连锁酒店(上海陆家嘴八佰伴店)', '崂山路689号', 249, 36, '7天酒店', '上海', '二钻', '浦东陆家嘴金融贸易区', '31.220656', '121.525127', 'https://m.tuniucdn.com/fb2/t1/G1/M00/3D/95/Cii9EVkx0JCIRDFfAAIMnl8npiYAALaawHMG2kAAgy2536_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200215226, '上海颖奕皇冠假日酒店', '博园路6555号', 907, 45, '皇冠假日', '上海', '五钻', '嘉定新城', '31.272533', '121.19179', 'https://m.tuniucdn.com/fb3/s1/2n9c/3Uyfi2aBRETE1K5PChiLVZCwtDLF_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200215286, '上海万豪虹桥大酒店', '虹桥路2270号', 910, 46, '万豪', '上海', '五星级', '虹桥地区', '31.191529', '121.375577', 'https://m.tuniucdn.com/fb2/t1/G6/M00/52/B6/Cii-TF3eXK6IBQoRAAbgs1dyxJwAAGRfAPXbPQABuDL314_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200215365, '如家酒店(上海虹桥漕河泾古北店)', '虹梅路2971号', 189, 44, '如家', '上海', '二钻', '虹桥地区', '31.180968', '121.392415', 'https://m.tuniucdn.com/fb3/s1/2n9c/2WPfVp6auQkYoHzAdSbxwHAtQFfa_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200215548, '速8酒店（上海虹桥枢纽九亭中心路店）', '松江九亭镇中心路128号', 198, 39, '速8', '上海', '二钻', '七宝古镇', '31.119363', '121.322768', 'https://m.tuniucdn.com/fb2/t1/G1/M00/42/40/Cii-U1kziVOIGTw-AAGRMfcIwJwAALi6ACRnUsAAZFJ536_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (200216665, '维也纳酒店（上海奉贤南桥中心店）', '奉贤沪杭公路1758-8号', 1289, 38, '维也纳', '上海', '三钻', '奉贤开发区', '30.934646', '121.451449', 'https://m.tuniucdn.com/fb2/t1/G2/M00/DC/A8/Cii-T1kw5leIQAA3AAFzNYtL4loAAK9OQOyk4sAAXNN152_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (219484323, '7天连锁酒店(深圳观澜章阁店）', '宝安区章阁村桂月路章阁市场汇龙峰景一期A栋2-4层', 725, 40, '7天酒店', '深圳', '二钻', '观澜', '22.746493', '114.023', 'https://m.tuniucdn.com/fb2/t1/G1/M00/3C/8C/Cii-U1kxXqaIeqeuAAHwF4GDmOcAALYFQI9zMMAAfAv100_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (223318960, '和颐酒店(深圳深南大道华强路地铁站店)', '深南中路2081号', 637, 46, '和颐', '深圳', '四钻', '华强北商业区', '22.540313', '114.088611', 'https://m.tuniucdn.com/fb3/s1/2n9c/2M7am7D8rPTeTQAhxqBeMSANaqGr_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (233036941, '7天连锁酒店(上海东林寺店)', '朱泾镇文商路79号', 218, 37, '7天酒店', '上海', '二钻', '金山枫泾古镇地区', '30.895912', '121.160238', 'https://m.tuniucdn.com/fb2/t1/G4/M00/35/13/Cii_J1zr5PyIY3acAAFCnHJPxLUAAGX-ABvcIMAAUK0087_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (234719711, '如家酒店·neo(北京朝阳北路十里堡地铁站店)', '朝阳北路八里庄南里26号', 378, 47, '如家', '北京', '二钻', '国贸地区', '39.922472', '116.501118', 'https://m.tuniucdn.com/fb3/s1/2n9c/2rHdXNCmycnUxw99AniFC25ZDSfJ_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (234719728, '速8酒店（北京房山城关店）', '房山城关镇城隍庙街10号(原房山老公安局)', 392, 47, '速8', '北京', '二钻', '', '39.705216', '115.981904', 'https://m.tuniucdn.com/fb2/t1/G1/M00/3F/66/Cii9EFkyeImIB3ZVAAHcTtTFt4oAALdsgICDO0AAdxm378_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (235196477, '和颐酒店(北京总部基地店)', '丰葆路106号', 379, 45, '和颐', '北京', '四钻', '总部基地/丰台体育中心/南宫地区', '39.815383', '116.291012', 'https://m.tuniucdn.com/fb3/s1/2n9c/3J7Hcvwt5xZJL3NkS4wPJ6csmFb9_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (339777429, '上海嘉定喜来登酒店', '菊园新区嘉唐公路66号', 1286, 44, '喜来登', '上海', '五钻', '嘉定新城', '31.394595', '121.245773', 'https://m.tuniucdn.com/fb3/s1/2n9c/2v2fKuo5bzhunSBC1n1E42cLTkZV_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (339952837, '如家酒店(北京良乡西路店)', '良乡西路7号', 159, 46, '如家', '北京', '二钻', '房山风景区', '39.73167', '116.132482', 'https://m.tuniucdn.com/fb3/s1/2n9c/3Dpgf5RTTzrxpeN5y3RLnRVtxMEA_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (343341695, '和颐酒店(北京中关村软件园店)', '东北旺西路8号中关村软件园一期9号楼', 245, 47, '和颐', '北京', '四钻', '上地产业园/西三旗', '40.044663', '116.29607', 'https://m.tuniucdn.com/fb3/s1/2n9c/3hSkPeWRQ3VK1heRQpHzJNMTanQz_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (365011120, '和颐酒店(北京石景山万达广场店)', '鲁谷东街甲26号', 505, 47, '和颐', '北京', '四钻', '公主坟/五棵松/石景山游乐园地区', '39.895479', '116.240386', 'https://m.tuniucdn.com/fb3/s1/2n9c/3iwohdQzyZP9azUkYAwTFj7WzBwd_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (368343863, '如家酒店(上海金桥博兴路地铁站店)', '博兴路1119号', 218, 45, '如家', '上海', '二钻', '浦东金桥地区', '31.266272', '121.593829', 'https://m.tuniucdn.com/fb3/s1/2n9c/w5ERtGJEmdgdgy5qtLPatR1xfm4_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (368701368, '深圳大中华希尔顿酒店', '福田深南大道1003号', 1666, 46, '希尔顿', '深圳', '五钻', '会展中心/CBD', '22.539313', '114.069763', 'https://m.tuniucdn.com/fb3/s1/2n9c/4EnHseZ73LXdFJY7DSdJ8xqAcjXe_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1393017952, '汉庭酒店(深圳宝安松岗地铁站店)', '松岗镇河滨北路12号盛华大厦', 166, 47, '汉庭', '深圳', '二钻', '松岗商业中心区', '22.768912', '113.83325', 'https://m.tuniucdn.com/fb3/s1/2n9c/4NehRjdHyZDKxTjAxTYv27FHq8LJ_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1400304687, '如家酒店(深圳横岗地铁站新马商贸城店)', '龙岗大道横岗段4004号', 149, 43, '如家', '深圳', '二钻', '龙岗中心区/大运新城', '22.642629', '114.202799', 'https://m.tuniucdn.com/fb2/t1/G6/M00/25/5A/Cii-TF3PFkiIb27dAAEqdDcKl3YAAEViQGVWY0AASqM960_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1406627919, '深圳中洲万豪酒店', '海德一道88号中洲控股中心A座', 204, 47, '万豪', '深圳', '五钻', '海岸城/后海', '22.517293', '113.933785', 'https://m.tuniucdn.com/fb3/s1/2n9c/3wsinQAcuWtCdmv1yxauVG2PSYpC_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1418471719, '上海宝龙丽笙酒店', '金海路2449弄2号', 860, 46, '丽笙', '上海', '五钻', '浦东金桥地区', '31.26571', '121.650132', 'https://m.tuniucdn.com/fb3/s1/2n9c/3myGUurFCriEVMGPy9yYMPFdb9Zh_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1455383931, '如家酒店(深圳宝安客运中心站店)', '西乡河西金雅新苑34栋', 169, 45, '如家', '深圳', '二钻', '宝安商业区', '22.590272', '113.881933', 'https://m.tuniucdn.com/fb3/s1/2n9c/2w9cbbpzjjsyd2wRhFrnUpBMT8b4_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1457521002, '7天连锁酒店(深圳西丽茶光地铁站店)', '珠光北路280号天下湘军1楼', 517, 39, '7天酒店', '深圳', '二钻', '大学城/西丽动物园', '22.576187', '113.956166', 'https://m.tuniucdn.com/fb2/t1/G2/M00/E3/E0/Cii-Tlkzdl6IfQYfAAHCgNVDe1sAALK6gPBDhQAAcKY242_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1463484295, '上海和平豪生酒店', '沪南公路2653-2号', 650, 41, '豪生', '上海', '四钻', '周浦康桥地区', '31.146478', '121.568218', 'https://m.tuniucdn.com/fb3/s1/2n9c/ZxM9gWHqj657ndRsHw4j4p3CQ5k_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1514269829, '如家酒店(上海东川路碧江商业广场店)', '东川路2645号', 218, 45, '如家', '上海', '二钻', '交大/闵行经济开发区', '31.008875', '121.402813', 'https://m.tuniucdn.com/fb3/s1/2n9c/R92UunuCRXiG826G9Ptu7orqs7b_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1557882030, '维也纳酒店（深圳东门老街店）', '罗湖区东门新园路37号益德楼', 336, 43, '维也纳', '深圳', '三钻', '东门商业区', '22.549413', '114.118866', 'https://m.tuniucdn.com/fb2/t1/G1/M00/45/83/Cii9EFk0opCIPl9CAAKHl3Egm6oAALoKwLNWlwAAoev470_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1557997004, '上海五角场凯悦酒店', '国定东路88号', 1104, 46, '凯悦', '上海', '五钻', '江湾/五角场商业区', '31.300645', '121.51918', 'https://m.tuniucdn.com/fb3/s1/2n9c/3a3Zz9cDgbJEEJ1GcXzKhTh21YqK_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1565094427, '上海国际旅游度假区万怡酒店', '秀浦路3999弄17号', 713, 45, '万怡', '上海', '四钻', '迪士尼度假区', '31.132913', '121.63464', 'https://m.tuniucdn.com/fb3/s1/2n9c/KPBUPunPDETYWg8WaJDSmiZC65z_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1584362548, '如家酒店(上海浦东国际旅游度假区御桥地铁站店)', '御青路315-317号', 339, 44, '如家', '上海', '二钻', '周浦康桥地区', '31.15719', '121.572392', 'https://m.tuniucdn.com/fb3/s1/2n9c/2ybd3wqdoBtBeKcPxmyso9y1hNXa_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1630005459, '7天连锁酒店（深圳地王大厦红桂路店）（原红桂路店）', '罗湖区宝安南路2078号深港豪苑（与红桂路交汇处）', 143, 39, '7天酒店', '深圳', '二钻', '', '22.550341', '114.10965', 'https://m.tuniucdn.com/fb2/t1/G2/M00/EA/18/Cii-T1k1KaGIIkQVAAD4fD_T3FcAALTtABiCJ8AAPiU164_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1637944903, '速8酒店北京后海店', '西城北京市西城区德胜门内大街兴华胡同五福里2号', 213, 39, '速8', '北京', '二钻', '后海', '39.934452', '116.38184', 'https://m.tuniucdn.com/fb2/t1/G1/M00/48/0C/Cii9EVk1JNuILdBWAAHv5O89TjMAALrFgJ8bwcAAe_8197_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1649956165, '上海南青华美达酒店', '华夏东路811号', 299, 47, '华美达', '上海', '四钻', '迪士尼度假区', '31.195206', '121.664791', 'https://m.tuniucdn.com/fb3/s1/2n9c/2RHmQgTpte3UVSDJ5KbqobbZGRnE_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1714520967, '速8酒店(北京安华桥黄寺大街店)', '黄寺大街12号院16号楼', 559, 43, '速8', '北京', '二钻', '马甸、安贞地区', '39.962742', '116.388431', 'https://m.tuniucdn.com/fb2/t1/G1/M00/4A/21/Cii-U1k1o-uIdcUZAAIbmIKVlKAAALtvQGBb6kAAhuw170_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1725781423, '上海三迪华美达酒店', '广富林路600弄7号', 690, 43, '华美达', '上海', '四钻', '佘山/松江大学城', '31.058023', '121.246536', 'https://m.tuniucdn.com/fb3/s1/2n9c/NoHym6tuKwVazxy33wRNTNuQWd2_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1734220581, '汉庭酒店(深圳大鹏佳兆业店)', '大鹏街道新园街12号', 378, 43, '汉庭', '深圳', '二钻', '较场尾/大鹏所城', '22.592661', '114.475167', 'https://m.tuniucdn.com/fb3/s1/2n9c/3nWzyWt63gtwPzRf5xbHvwKM27vU_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1754929048, '上海环球港凯悦酒店', '宁夏路718号', 1336, 45, '凯悦', '上海', '五钻', '中山公园商业区', '31.232041', '121.412492', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/B6/Cii-U13ZY-CIF-8MAAXkwQoY7FIAAFpQgEE1bgABeTZ750_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1762661667, '深圳佳兆业万豪酒店', '棕榈大道33号', 223, 46, '万豪', '深圳', '五钻', '较场尾/大鹏所城', '22.569193', '114.459325', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/EC/Cii-TF3ZqE-IYM0NAAY6GIHLZNsAAFr2QDhR8EABjow444_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1765008760, '如家酒店(北京西直门北京北站店)', '西直门北大街49号', 356, 44, '如家', '北京', '二钻', '西直门/北京展览馆地区', '39.945106', '116.353827', 'https://m.tuniucdn.com/fb3/s1/2n9c/4CLwbCE9346jYn7nFsJTQXuBExTJ_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1880614409, '上海崇明由由喜来登酒店', '揽海路2888号', 2198, 45, '喜来登', '上海', '五钻', '崇明岛/长兴岛/横沙岛', '31.462167', '121.823103', 'https://m.tuniucdn.com/fb3/s1/2n9c/21gDCGgRT3xFqCd3FxBh633j6Qsu_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1889333146, '如家酒店(北京西客站丽泽桥店)', '西三环南路44号-218', 459, 47, '如家', '北京', '二钻', '北京西站/丽泽商务区', '39.869638', '116.313075', 'https://m.tuniucdn.com/fb3/s1/2n9c/kG5corYUDC7U1qE8RAY6xCVnGxq_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1908594080, '上海建工浦江皇冠假日酒店', '陈行公路3701号', 843, 46, '皇冠假日', '上海', '五钻', '浦江镇地区', '31.090063', '121.489728', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/B7/Cii-U13ZZDWIePrGAAPyImW93N0AAFpRgMmj4MAA_I6005_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1913922369, '上海中建万怡酒店', '蟠文路333号', 889, 47, '万怡', '上海', '四钻', '虹桥机场/国家会展中心', '31.185504', '121.287709', 'https://m.tuniucdn.com/fb3/s1/2n9c/39Afm5Bxgd784eMeFB5DrcsPnhT_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1916002015, '上海苏宁环球万怡酒店', '丹巴路99号', 689, 45, '万怡', '上海', '四钻', '长风公园地区', '31.22292', '121.379912', 'https://m.tuniucdn.com/fb3/s1/2n9c/svpYHdmVDck91NqAhjtngcXth2G_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1928731731, '上海康桥万豪酒店', '康新公路4499号', 811, 46, '万豪', '上海', '五钻', '迪士尼度假区', '31.119187', '121.618966', 'https://m.tuniucdn.com/fb3/s1/2n9c/3inpPxTnvRjMCEB39K9FuHaXohYw_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1931442052, '深圳机场凯悦酒店', '宝安国际机场地面交通中心（GTC）18号出口', 291, 47, '凯悦', '深圳', '五钻', '宝安机场商圈', '22.622498', '113.812341', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/EC/Cii-TF3ZqVeIJcTPAAUq_Ou_CrcAAFr4gKufPUABSsU446_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1931602865, '深圳联投东方万怡酒店', '松岗东方大道46号', 688, 45, '万怡', '深圳', '五钻', '松岗商业中心区', '22.760746', '113.856961', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/EC/Cii-U13ZqVyIOjdpAARI9aeBh-IAAFr4gOQh7oABEkN297_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1937347815, '北京望京凯悦酒店', '广顺南大街8号院2号楼', 617, 46, '凯悦', '北京', '五钻', '望京/酒仙桥/798地区', '39.991546', '116.476288', 'https://m.tuniucdn.com/fb3/s1/2n9c/2gLT4ZgJ8ZuS7sSmXzYoCXnV248p_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1942938880, '北京乐多港万豪酒店', '城南街道南口路29号', 227, 45, '万豪', '北京', '五钻', '昌平城区/十三陵度假区', '40.23264', '116.188888', 'https://m.tuniucdn.com/fb3/s1/2n9c/3mBWaZeaqq54E7kX2n7g9b2CZX6q_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1942992995, '上海嘉定凯悦酒店', '裕民南路1366号', 758, 46, '凯悦', '上海', '五钻', '嘉定新城', '31.352298', '121.263314', 'https://m.tuniucdn.com/fb2/t1/G6/M00/53/2D/Cii-U13edkqIfZhLAAJEW25WIF4AAGVxQIg38sAAkRz517_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1951709780, '深圳同泰万怡酒店', '福海街道宝安大道 6259号', 617, 48, '万怡', '深圳', '五钻', '深圳国际会展中心商圈', '22.678611', '113.805695', 'https://m.tuniucdn.com/fb3/s1/2n9c/3oUfktphxMAWq9hUxD9uqdjRdZGB_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1973839294, '深圳湾万怡酒店', '科技南路16号', 508, 47, '万怡', '深圳', '五钻', '科技园', '22.531101', '113.950615', 'https://m.tuniucdn.com/fb3/s1/2n9c/8C9QscRsvTWCx92wt17GAegEMFn_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1975922994, '如家酒店·neo(深圳南山海岸城南油地铁站店)', '南商路84-6号', 238, 44, '如家', '深圳', '二钻', '海岸城/后海', '22.513566', '113.9291', 'https://m.tuniucdn.com/fb2/t1/G6/M00/25/5E/Cii-TF3PGD-IQ0FcAAFIZC82AnkAAEVvAKdj4YAAUh8638_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1989806195, '深圳博林天瑞喜来登酒店', '留仙大道4088号', 1369, 48, '喜来登', '深圳', '五钻', '大学城/西丽动物园', '22.582918', '113.97219', 'https://m.tuniucdn.com/fb3/s1/2n9c/4Rx55fZoneUeKbE3TCRSPB6WQ6bw_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1996823660, '上海紫竹万怡酒店', '紫星路588号3幢', 642, 46, '万怡', '上海', '四钻', '交大/闵行经济开发区', '31.02118', '121.465186', 'https://m.tuniucdn.com/fb2/t1/G6/M00/53/2F/Cii-TF3edraIPzK9AAH_p8vdHKoAAGV3AJgSVEAAf-_019_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (1997830708, '和颐至尚酒店(北京上地软件园店)', '清河小营西路48号汇苑仁和大厦一层', 753, 47, '和颐', '北京', '四钻', '上地产业园/西三旗', '40.034623', '116.323925', 'https://m.tuniucdn.com/fb3/s1/2n9c/2sKjxS1hFYyBFVKVBqo2x2hSFvGj_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2003479905, '上海榕港万怡酒店', '新松江路1277号', 798, 46, '万怡', '上海', '四钻', '佘山/松江大学城', '31.038198', '121.210178', 'https://m.tuniucdn.com/fb3/s1/2n9c/2GM761BYH8k15qkNrJrja3cwfr2D_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2009548883, '和颐至尚酒店(北京首都机场新国展店)', '府前二街6号', 611, 46, '和颐', '北京', '三钻', '首都机场/新国展地区', '40.063953', '116.576829', 'https://m.tuniucdn.com/fb3/s1/2n9c/43zCTomkMSkUfZByZxn77YH2XidJ_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2011785622, '北京世园凯悦酒店', '阜康南路1号院1号楼A', 558, 47, '凯悦', '北京', '五星级', '延庆休闲度假区', '40.440732', '115.963259', 'https://m.tuniucdn.com/fb3/s1/2n9c/uhGcQze3zZQxe4avSU8BysgYVvx_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2022598930, '上海宝华喜来登酒店', '南奉公路3111弄228号', 2899, 46, '喜来登', '上海', '五钻', '奉贤开发区', '30.921659', '121.575572', 'https://m.tuniucdn.com/fb2/t1/G6/M00/45/BD/Cii-TF3ZaBmIStrbAASnoOyg7FoAAFpYwEoz9oABKe4992_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2022881967, '深圳盐田凯悦酒店', '海景二路1025号1-6层、30-50层', 650, 47, '凯悦', '深圳', '五钻', '盐田区政府/沙头角', '22.551323', '114.23781', 'https://m.tuniucdn.com/fb3/s1/2n9c/2RFMLSujkczEn1HoybD6dUpN9pzr_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2031683181, '和颐至尚酒店(北京雍和宫和平里店)', '小黄庄1区15号', 299, 47, '和颐', '北京', '四钻', '马甸/安贞地区', '39.962361', '116.412931', 'https://m.tuniucdn.com/fb3/s1/2n9c/4Xqm5BN9pZTamwmYS3eLxL417YYt_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2045052822, '深圳国际会展中心皇冠假日酒店', '展云路6号', 675, 47, '皇冠假日', '深圳', '五钻', '深圳国际会展中心商圈', '22.686581', '113.777655', 'https://m.tuniucdn.com/fb3/s1/2n9c/4DGZygQpE4iSpcBDCoXJvjNr4oiR_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2048042240, '北京大兴希尔顿酒店', '高米店南里18号楼', 1283, 48, '希尔顿', '北京', '五钻', '大兴北京新机场地区', '39.76875', '116.339199', 'https://m.tuniucdn.com/fb3/s1/2n9c/3B32F8zSU2CJCWzs1hoH2o4WcquR_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2048047291, '北京新青海喜来登酒店', '丽泽金融商务区凤凰嘴北路与金中都西路交叉口西营街8号院1号楼', 723, 47, '喜来登', '北京', '五钻', '北京西站/丽泽商务区', '39.864026', '116.322505', 'https://m.tuniucdn.com/fb3/s1/2n9c/4DPQMu5sMM7XR1mvcjoqtWngc7TF_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2048050570, '汉庭酒店（深圳坪山火车站店）', '新和路127-2号', 436, 47, '汉庭', '深圳', '二钻', '坪山高铁站商圈', '22.700753', '114.339089', 'https://m.tuniucdn.com/fb3/s1/2n9c/2nXN2bWjfoqoTkPwHvLJQPYz17qD_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2048671293, '汉庭酒店(深圳观澜五和大道店)', '观湖街道五和大道327号', 234, 43, '汉庭', '深圳', '二钻', '观澜', '22.684459', '114.07708', 'https://m.tuniucdn.com/fb3/s1/2n9c/2JrQi83S9qgDEkXqWpe5iyi44Uh2_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2051661320, '汉庭酒店(深圳罗湖口岸万象城二店)', '桂园街道宝安南路1050号嘉宾花园C栋', 667, 47, '汉庭', '深圳', '三钻', '万象城/京基100', '22.540352', '114.112668', 'https://m.tuniucdn.com/fb3/s1/2n9c/34FRP7HLPhvKZP1a6tXu4XrJeiaw_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2056105938, '北京通州北投希尔顿酒店', '新华东街289号2号楼', 1068, 48, '希尔顿', '北京', '五钻', '果园环岛/通州区', '39.908805', '116.659748', 'https://m.tuniucdn.com/fb3/s1/2n9c/NGKdpec3tZJNUUNWJ5pd67Cp5AY_w200_h200_c1_t0.png');
INSERT INTO `tb_hotel` VALUES (2056126831, '上海虹桥金臣皇冠假日酒店', '申长路630弄1-3 号', 2488, 48, '皇冠假日', '上海', '五钻', '虹桥机场/国家会展中心', '31.19036', '121.31535', 'https://m.tuniucdn.com/fb3/s1/2n9c/PvFh4Vzc84xXhm5N41F6AqdAqyJ_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2056132395, '深圳深铁皇冠假日酒店', '深南大道9819号', 340, 47, '皇冠假日', '深圳', '五钻', '科技园', '22.538923', '113.944794', 'https://m.tuniucdn.com/fb3/s1/2n9c/eBLtrED2uJs7yURWfjnWge9dT1P_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2056298828, '上海中优城市万豪酒店', '沪南公路7688弄1号', 1200, 45, '万豪', '上海', '五钻', '南汇/野生动物园', '31.030053', '121.662943', 'https://m.tuniucdn.com/fb3/s1/2n9c/2gBATEyysyQWmw3wZL863HGdqjaq_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2058250574, '深圳湾万丽酒店', '粤海街道高新区社区科技南路18号', 351, 47, '万丽', '深圳', '五钻', '科技园', '22.531674', '113.951882', 'https://m.tuniucdn.com/fb3/s1/2n9c/2YWUpZsvPVkRiKgdPg95LJxaFmB6_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2060510277, '北京金隅八达岭皇冠假日酒店', '妫水北街1号1-14幢', 1026, 44, '皇冠假日', '北京', '五钻', '延庆休闲度假区', '40.476483', '115.97481', 'https://m.tuniucdn.com/fb3/s1/2n9c/3Dzq2KxgiQbmb1sbc5iK6xqpVuFr_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2060618247, '汉庭酒店(深圳海岸城店)', '粤海街道后海社区后海第二统建楼商业裙楼第二层B', 562, 49, '汉庭', '深圳', '二钻', '海岸城/后海', '22.507276', '113.931251', 'https://m.tuniucdn.com/fb3/s1/2n9c/TBoXdgEx5Yjc2HobeC3fPWWnSJi_w200_h200_c1_t0.jpg');
INSERT INTO `tb_hotel` VALUES (2062643512, '深圳国际会展中心希尔顿酒店', '展丰路80号', 285, 46, '希尔顿', '深圳', '五钻', '深圳国际会展中心商圈', '22.705335', '113.77794', 'https://m.tuniucdn.com/fb3/s1/2n9c/2SHUVXNrN5NsXsTUwcd1yaHKbrGq_w200_h200_c1_t0.jpg');

SET FOREIGN_KEY_CHECKS = 1;
