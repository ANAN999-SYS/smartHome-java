/*
 Navicat Premium Data Transfer

 Source Server         : luntan
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : smart_home

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 13/04/2024 14:52:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `su_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `uesr_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'ssss', 'superAdmin', '123456', 'superAdmin', 'true', '2024-04-09 02:43:52', '2024-04-09 02:43:55', 'http://172.29.35.235:9999/upload/static/avatarUrl/dd1cbf57c5ff422faa55281d6ffb235f.jpeg');
INSERT INTO `admin` VALUES (2, 'aaaaa', 'admin', '123456', 'admin', 'true', '2024-04-12 04:06:10', '2024-04-12 04:06:14', 'http://172.29.35.235:9999/upload/static/avatarUrl/dd1cbf57c5ff422faa55281d6ffb235f.jpeg');

-- ----------------------------
-- Table structure for devices
-- ----------------------------
DROP TABLE IF EXISTS `devices`;
CREATE TABLE `devices`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `de_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `icon_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备类型',
  `room_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属房间Id',
  `room_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属房间',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型码',
  `topic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订阅主题',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者Id(openId)',
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devices
-- ----------------------------
INSERT INTO `devices` VALUES (3, '0708c7eadb53442182e5a6b9cceca416', '卧室窗帘', 'curtain', 'curtain', '155544dd4dae46aaa1e8b79b8cf5e29f', '卧室', '009', '0708c7eadb53442182e5a6b9cceca416009', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'false', '2024-04-05 01:17:45', '2024-04-05 01:17:45');
INSERT INTO `devices` VALUES (5, 'fc2bad1cfcae4565b6d710ed8f7bd002', '客厅风扇', 'wind', 'wind', '155544dd4dae46aaa1e8b79b8cf5e29f', '卧室', '003', 'fc2bad1cfcae4565b6d710ed8f7bd002003', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'false', '2024-04-05 01:18:15', '2024-04-05 01:18:30');
INSERT INTO `devices` VALUES (6, '7fe7ef50478d4e27be74f5027d5a23cc', '厨房通风', 'wind', 'wind', 'c89b5f5ec4764e9e9e335e651f9d793f', '厨房', '003', '7fe7ef50478d4e27be74f5027d5a23cc003', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'false', '2024-04-05 05:49:54', '2024-04-05 05:49:54');
INSERT INTO `devices` VALUES (11, '0e47e463470b4b76a6485be3f9029b7d', '厨房插座', 'receptacle', 'receptacle', 'c89b5f5ec4764e9e9e335e651f9d793f', '厨房', '001', '0e47e463470b4b76a6485be3f9029b7d001', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'false', '2024-04-05 06:00:32', '2024-04-05 06:00:32');
INSERT INTO `devices` VALUES (12, 'a4bc259f0edc429fb250660a84ba8790', '门锁', 'lock', 'lock', '155544dd4dae46aaa1e8b79b8cf5e29f', '卧室', '006', 'a4bc259f0edc429fb250660a84ba8790006', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'false', '2024-04-05 06:11:08', '2024-04-05 06:11:08');
INSERT INTO `devices` VALUES (13, '1b9ee7075da748a69d102e3ca3693694', '老黑快乐灯', 'light', 'light', '4d1ae35dcebf479dbb487d7d7dbbe616', '老黑快乐屋', '002', '1b9ee7075da748a69d102e3ca3693694002', 'okQyM6978O0xkoSqVQANZbEVtNyk', 'false', '2024-04-05 15:47:04', '2024-04-05 15:47:04');
INSERT INTO `devices` VALUES (15, '0df1b1daa6d349e280580e1786c86bd4', '老黑快乐帘', 'curtain', 'curtain', '4d1ae35dcebf479dbb487d7d7dbbe616', '老黑快乐屋', '009', '0df1b1daa6d349e280580e1786c86bd4009', 'okQyM6978O0xkoSqVQANZbEVtNyk', 'false', '2024-04-05 16:09:24', '2024-04-05 16:09:24');
INSERT INTO `devices` VALUES (18, 'fc88cf66051c4362bf9fdabd04f62027', '老黑快乐插座', 'receptacle', 'receptacle', '4d1ae35dcebf479dbb487d7d7dbbe616', '老黑快乐屋', '001', 'fc88cf66051c4362bf9fdabd04f62027001', 'okQyM6978O0xkoSqVQANZbEVtNyk', 'false', '2024-04-05 16:10:05', '2024-04-05 16:10:05');
INSERT INTO `devices` VALUES (19, '0b8d9650712a45b591f6a44b4026e751', '客厅灯', 'light', 'light', 'd88530a793b94fce9e53949da1332d47', '客厅', '002', '0b8d9650712a45b591f6a44b4026e751002', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'false', '2024-04-13 03:08:15', '2024-04-13 03:08:15');

-- ----------------------------
-- Table structure for exception_ logging
-- ----------------------------
DROP TABLE IF EXISTS `exception_ logging`;
CREATE TABLE `exception_ logging`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `e_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `wring_info` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报警信息',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所有用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exception_ logging
-- ----------------------------

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `r_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'id',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作者(openId)',
  `operate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细操作',
  `device_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备名称',
  `device_type` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作设备类型',
  `device_room` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备所属房间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (39, 'eb03180533b74cd6b94cf067f8d97c7e', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-10 18:46:17', NULL);
INSERT INTO `record` VALUES (40, 'dcd35801f60e4fce8669c6611f7d641e', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-06 01:22:41', NULL);
INSERT INTO `record` VALUES (41, 'f0a93fafa0f34ea68977e2ede03f0468', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-06 10:04:10', NULL);
INSERT INTO `record` VALUES (42, 'f925cc7045554defb1938f4e92e7bbbc', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-06 16:01:21', NULL);
INSERT INTO `record` VALUES (43, '8ae06e8a0b1d430e8882888dc8701fab', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-06 16:01:47', NULL);
INSERT INTO `record` VALUES (44, 'd90b9daae705479fb3997a908ecba18a', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '厨房通风', 'wind', '厨房', '2024-04-09 16:06:01', NULL);
INSERT INTO `record` VALUES (45, '8706858bd2ae449cb057bd656e3e4dfc', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '厨房通风', 'wind', '厨房', '2024-04-06 16:06:04', NULL);
INSERT INTO `record` VALUES (46, 'a7b83cce26ff4921866e6fa46642bb6d', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-06 16:57:14', NULL);
INSERT INTO `record` VALUES (47, '4b33014cc6b74be88f3583feaff51d2a', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-06 16:57:23', NULL);
INSERT INTO `record` VALUES (48, '1e4483fe543047ae8fce19f57818fbea', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-06 17:00:47', NULL);
INSERT INTO `record` VALUES (49, 'ccdf474e631d4fe9b5bf88e7ce11864c', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 00:56:32', NULL);
INSERT INTO `record` VALUES (50, 'c7bd3a1513db4fcdb0b6757ff6ca87bb', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 00:56:37', NULL);
INSERT INTO `record` VALUES (51, 'd6156c76004743fa9898cfe2bd87bd5d', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 00:56:41', NULL);
INSERT INTO `record` VALUES (52, '022d9842256744809a78c2d441daca2a', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 00:56:44', NULL);
INSERT INTO `record` VALUES (53, 'ddd7617877234ab6a385138442fd8fea', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 00:58:01', NULL);
INSERT INTO `record` VALUES (54, '1eab0a8d425549c3856df66ce0c2a2bb', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 00:58:15', NULL);
INSERT INTO `record` VALUES (55, '34f68adad1f545b18e5af259747146db', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 00:58:17', NULL);
INSERT INTO `record` VALUES (56, '8a903c2b4c3f4cc1bdb07136823b19e4', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 00:58:18', NULL);
INSERT INTO `record` VALUES (57, '267cde83dac247f5a9fc7f72609e62a6', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 01:01:06', NULL);
INSERT INTO `record` VALUES (58, '8b7340c067d64a5cbbe018d42ea4b749', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 01:01:08', NULL);
INSERT INTO `record` VALUES (59, '7a73a32367ab43e2adba34873e2c3fb3', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 01:03:24', NULL);
INSERT INTO `record` VALUES (60, '390bd9592a5c44869e1046c998d2ed68', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 01:04:07', NULL);
INSERT INTO `record` VALUES (61, '11f2c5f87ae24f208008f717391cbcf7', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 01:04:10', NULL);
INSERT INTO `record` VALUES (62, 'e652c71d7d8646838b2d99509425cffa', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 01:23:11', NULL);
INSERT INTO `record` VALUES (63, 'cc1c07ed92c4443d9a4627fd46d0721c', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 01:23:14', NULL);
INSERT INTO `record` VALUES (64, 'eab991e5bde44ad2b3100459e976732e', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 02:31:14', NULL);
INSERT INTO `record` VALUES (65, 'de52836dd8784dec93fdb8c47c1771a5', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 02:31:16', NULL);
INSERT INTO `record` VALUES (66, 'da62aa1883cc400894fc052897f80a1c', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 02:40:13', NULL);
INSERT INTO `record` VALUES (67, 'bac16bd13c7047cb935cc2274396952f', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 02:40:15', NULL);
INSERT INTO `record` VALUES (68, '2a49c1da2c8245fb8cf8cfeede4e029c', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 11:52:07', NULL);
INSERT INTO `record` VALUES (69, 'c4c8991f1f4445198e11cf06e70430be', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 11:52:09', NULL);
INSERT INTO `record` VALUES (70, '97960ea677b94df98dd4f506601e676d', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 13:19:42', NULL);
INSERT INTO `record` VALUES (71, 'd21ec6fbf693424eafc99d8f6d0bda62', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 13:19:44', NULL);
INSERT INTO `record` VALUES (72, 'cf16dd3099b44da5a4d868093b30dc4d', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-07 13:30:30', NULL);
INSERT INTO `record` VALUES (73, '9641336d75304e2e806aac3d07e51d31', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'off', '客厅灯', 'light', '客厅', '2024-04-07 13:30:32', NULL);
INSERT INTO `record` VALUES (74, '496c2a007dce4665be05135efd20e54c', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-09 02:29:19', NULL);
INSERT INTO `record` VALUES (75, 'a7df210dbeeb40a7925fb3bb2710338c', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '门锁', 'lock', '卧室', '2024-04-09 02:32:16', NULL);
INSERT INTO `record` VALUES (76, '530d9490cd1c44d8b91424fbff0019d5', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '客厅灯', 'light', '客厅', '2024-04-09 02:32:38', NULL);
INSERT INTO `record` VALUES (77, 'e86f3df506874cf3a899c98261e5a76d', 'okQyM6z8-91LinjQrbhKsssn5s5', 'on', '卧室窗帘', 'curtain', '卧室', '2024-04-13 02:36:25', NULL);
INSERT INTO `record` VALUES (78, '2bd4ec3e1c384e55ab10588b572c91fa', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'on', '卧室窗帘', 'curtain', '卧室', '2024-04-13 14:10:52', NULL);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `room_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '添加者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (12, 'd88530a793b94fce9e53949da1332d47', '客厅', 'okQyM6z8-91LinjQrbhKsssn5s5s', '2024-04-04 15:54:38', '2024-04-04 15:54:38');
INSERT INTO `room` VALUES (13, '155544dd4dae46aaa1e8b79b8cf5e29f', '卧室', 'okQyM6z8-91LinjQrbhKsssn5s5s', '2024-04-04 15:54:51', '2024-04-04 15:54:51');
INSERT INTO `room` VALUES (14, 'c89b5f5ec4764e9e9e335e651f9d793f', '厨房', 'okQyM6z8-91LinjQrbhKsssn5s5s', '2024-04-04 15:54:57', '2024-04-04 15:54:57');
INSERT INTO `room` VALUES (18, '4d1ae35dcebf479dbb487d7d7dbbe616', '老黑快乐屋', 'okQyM6978O0xkoSqVQANZbEVtNyk', '2024-04-05 15:46:46', '2024-04-05 15:46:46');

-- ----------------------------
-- Table structure for scenario
-- ----------------------------
DROP TABLE IF EXISTS `scenario`;
CREATE TABLE `scenario`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `s_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `operate` json NULL,
  `mode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenario
-- ----------------------------
INSERT INTO `scenario` VALUES (6, 'a2d50ec869874fc79e5bed52eb79eeb8', '睡眠模式', 'sleep', '[{\"name\": \"客厅灯\", \"type\": \"light\", \"topic\": \"bed0a9e3302748fca7256c740897e8a9002\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"客厅\", \"secretKey\": null}, {\"name\": \"卧室窗帘\", \"type\": \"curtain\", \"topic\": \"0708c7eadb53442182e5a6b9cceca416009\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"卧室\", \"secretKey\": null}, {\"name\": \"客厅风扇\", \"type\": \"wind\", \"topic\": \"fc2bad1cfcae4565b6d710ed8f7bd002003\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"卧室\", \"secretKey\": null}, {\"name\": \"客厅空调\", \"type\": \"airConditioning\", \"topic\": \"966119f8d715479695b84fb59cdaf49d005\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": true, \"roomName\": \"客厅\", \"secretKey\": null}]', '手动执行', 'okQyM6z8-91LinjQrbhKsssn5s5s', '2024-04-05 16:05:32', '2024-04-05 16:05:32');
INSERT INTO `scenario` VALUES (7, '0f21caa37e9e45f0a7aaec8ca6a3b663', '回家模式', 'goHome', '[{\"name\": \"客厅风扇\", \"type\": \"wind\", \"topic\": \"fc2bad1cfcae4565b6d710ed8f7bd002003\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": true, \"roomName\": \"卧室\", \"secretKey\": null}, {\"name\": \"客厅灯\", \"type\": \"light\", \"topic\": \"bed0a9e3302748fca7256c740897e8a9002\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": true, \"roomName\": \"客厅\", \"secretKey\": null}, {\"name\": \"厨房插座\", \"type\": \"receptacle\", \"topic\": \"0e47e463470b4b76a6485be3f9029b7d001\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": true, \"roomName\": \"厨房\", \"secretKey\": null}]', '手动执行', 'okQyM6z8-91LinjQrbhKsssn5s5s', '2024-04-05 16:06:25', '2024-04-05 16:06:25');
INSERT INTO `scenario` VALUES (8, '4f4b0e36478c4b7d93e1119c271ff273', '离家模式', 'leaveHome', '[{\"name\": \"卧室窗帘\", \"type\": \"curtain\", \"topic\": \"0708c7eadb53442182e5a6b9cceca416009\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"卧室\", \"secretKey\": null}, {\"name\": \"客厅灯\", \"type\": \"light\", \"topic\": \"bed0a9e3302748fca7256c740897e8a9002\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"客厅\", \"secretKey\": null}, {\"name\": \"厨房通风\", \"type\": \"wind\", \"topic\": \"7fe7ef50478d4e27be74f5027d5a23cc003\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"厨房\", \"secretKey\": null}, {\"name\": \"客厅风扇\", \"type\": \"wind\", \"topic\": \"fc2bad1cfcae4565b6d710ed8f7bd002003\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"卧室\", \"secretKey\": null}, {\"name\": \"厨房插座\", \"type\": \"receptacle\", \"topic\": \"0e47e463470b4b76a6485be3f9029b7d001\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"厨房\", \"secretKey\": null}, {\"name\": \"客厅空调\", \"type\": \"airConditioning\", \"topic\": \"966119f8d715479695b84fb59cdaf49d005\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"客厅\", \"secretKey\": null}]', '手动执行', 'okQyM6z8-91LinjQrbhKsssn5s5s', '2024-04-05 16:07:09', '2024-04-05 16:07:09');
INSERT INTO `scenario` VALUES (9, '85fb691bf0f343f4b01056282dbb8463', '休闲模式', 'leisure', '[{\"name\": \"客厅灯\", \"type\": \"light\", \"topic\": \"bed0a9e3302748fca7256c740897e8a9002\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": true, \"roomName\": \"客厅\", \"secretKey\": null}, {\"name\": \"卧室窗帘\", \"type\": \"curtain\", \"topic\": \"0708c7eadb53442182e5a6b9cceca416009\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": false, \"roomName\": \"卧室\", \"secretKey\": null}, {\"name\": \"客厅空调\", \"type\": \"airConditioning\", \"topic\": \"966119f8d715479695b84fb59cdaf49d005\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": true, \"roomName\": \"客厅\", \"secretKey\": null}]', '手动执行', 'okQyM6z8-91LinjQrbhKsssn5s5s', '2024-04-05 16:07:37', '2024-04-05 16:07:37');
INSERT INTO `scenario` VALUES (10, '740ca5a8c1e34b31aa92931cdf5be1ee', '其他模式', 'other', '[{\"name\": \"客厅灯\", \"type\": \"light\", \"topic\": \"bed0a9e3302748fca7256c740897e8a9002\", \"author\": \"okQyM6z8-91LinjQrbhKsssn5s5s\", \"operate\": true, \"roomName\": \"客厅\", \"secretKey\": null}]', '手动执行', 'okQyM6z8-91LinjQrbhKsssn5s5s', '2024-04-05 16:08:03', '2024-04-05 16:08:03');
INSERT INTO `scenario` VALUES (11, 'ad137f2477844de7a18439caab695693', '老黑快乐模式', 'sleep', '[{\"name\": \"老黑快乐帘\", \"type\": \"curtain\", \"topic\": \"0df1b1daa6d349e280580e1786c86bd4009\", \"author\": \"okQyM6978O0xkoSqVQANZbEVtNyk\", \"operate\": true, \"roomName\": \"老黑快乐屋\", \"secretKey\": null}, {\"name\": \"老黑快乐空调\", \"type\": \"airConditioning\", \"topic\": \"1a083d06575b427daaa4d71f71c582ba005\", \"author\": \"okQyM6978O0xkoSqVQANZbEVtNyk\", \"operate\": true, \"roomName\": \"老黑快乐屋\", \"secretKey\": null}, {\"name\": \"老黑快乐风扇\", \"type\": \"wind\", \"topic\": \"922ebc884555498d8cd0c7b3774e44a8003\", \"author\": \"okQyM6978O0xkoSqVQANZbEVtNyk\", \"operate\": true, \"roomName\": \"老黑快乐屋\", \"secretKey\": null}, {\"name\": \"老黑快乐插座\", \"type\": \"receptacle\", \"topic\": \"fc88cf66051c4362bf9fdabd04f62027001\", \"author\": \"okQyM6978O0xkoSqVQANZbEVtNyk\", \"operate\": true, \"roomName\": \"老黑快乐屋\", \"secretKey\": null}]', '手动执行', 'okQyM6978O0xkoSqVQANZbEVtNyk', '2024-04-05 16:10:44', '2024-04-05 16:10:44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一标识',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `province` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `country` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `union_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户在开放平台的唯一标识',
  `is_auth` int(0) NULL DEFAULT NULL COMMENT '是否授权',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `secret_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备密钥',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户状态',
  `bafa_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巴法云密钥',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 'okQyM6z8-91LinjQrbhKsssn5s5s', '已停用的微信用户', '0', '', '', '', 'http://172.29.35.235:9999/upload/static/avatarUrl/dd8b1d58b7ad4e09b17931d835db6a13.jpg', NULL, 1, '2024-04-03 23:37:57', '2024-04-03 23:37:57', '93dcb6d31ba04a2d88fe113ccea012a3', 'true', NULL);
INSERT INTO `user` VALUES (11, 'okQyM6z8-91LinjQrbhKsssn5s5', '测试用户', '0', NULL, NULL, NULL, 'http://172.29.35.235:9999/upload/static/avatarUrl/dd8b1d58b7ad4e09b17931d835db6a13.jpg', NULL, 1, '2024-04-13 14:27:05', '2024-04-13 14:27:12', '93dcb6d31ba04a2d88fe113ccea012a', 'true', NULL);

-- ----------------------------
-- Table structure for user_feedback
-- ----------------------------
DROP TABLE IF EXISTS `user_feedback`;
CREATE TABLE `user_feedback`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `f_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈id',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈人(openId)',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈人头像',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈人昵称',
  `feedback_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈消息',
  `image_url` json NULL COMMENT '图片地址',
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_feedback
-- ----------------------------
INSERT INTO `user_feedback` VALUES (13, 'c38f5900229d4465a285ebe3c27b813f', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'http://172.29.35.235:9999/upload/static/avatarUrl/76145ad3be5f48cfb0497bad526e6301.jpg', '已停用的微信用户', '测试反馈', '[{\"url\": \"http://172.29.35.235:9999/upload/static/feedbackImg/113baf913fb54d649f11f3f73fec8636.jpg\", \"name\": \"113baf913fb54d649f11f3f73fec8636.jpg\", \"type\": \"image\"}, {\"url\": \"http://172.29.35.235:9999/upload/static/feedbackImg/313c3fe6726c4c5eb70a51b9b65d1c3c.jpg\", \"name\": \"313c3fe6726c4c5eb70a51b9b65d1c3c.jpg\", \"type\": \"image\"}, {\"url\": \"http://172.29.35.235:9999/upload/static/feedbackImg/4cdc5eaee31e4292845d4ae10519178f.jpg\", \"name\": \"4cdc5eaee31e4292845d4ae10519178f.jpg\", \"type\": \"image\"}]', 'true', '2024-04-13 02:45:18');
INSERT INTO `user_feedback` VALUES (14, 'ffe7396c09bf4c91aa69d4ca1181ac9e', 'okQyM6z8-91LinjQrbhKsssn5s5s', 'http://172.29.35.235:9999/upload/static/avatarUrl/76145ad3be5f48cfb0497bad526e6301.jpg', '已停用的微信用户', '满杯心意爱皆可宜', '[{\"url\": \"http://172.29.35.235:9999/upload/static/feedbackImg/fa3965512a4b420187cf18289e5879b2.jpg\", \"name\": \"fa3965512a4b420187cf18289e5879b2.jpg\", \"type\": \"image\"}, {\"url\": \"http://172.29.35.235:9999/upload/static/feedbackImg/7ff4913fce1340d5852545fbfa5c66b8.jpg\", \"name\": \"7ff4913fce1340d5852545fbfa5c66b8.jpg\", \"type\": \"image\"}, {\"url\": \"http://172.29.35.235:9999/upload/static/feedbackImg/f67d966b18784630b6e13ac9028fafcb.jpg\", \"name\": \"f67d966b18784630b6e13ac9028fafcb.jpg\", \"type\": \"image\"}]', 'false', '2024-04-13 03:07:22');

SET FOREIGN_KEY_CHECKS = 1;
