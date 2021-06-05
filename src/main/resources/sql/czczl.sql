/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : czczl

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 31/05/2021 15:14:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam_item
-- ----------------------------
DROP TABLE IF EXISTS `exam_item`;
CREATE TABLE `exam_item`  (
  `id` bigint unsigned NOT NULL,
  `stem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题干',
  `material_id` bigint(0) NULL DEFAULT NULL COMMENT '对应材料id',
  `stem_type` tinyint(1) NULL DEFAULT NULL COMMENT '试题类型',
  `choice_a` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `choice_b` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `choice_c` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `choice_d` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `choice_answer` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选择题答案',
  `judge_answer` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '判断题答案',
  `fill_answer` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填空题答案',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '逻辑删除 1为删除',
  `is_prize_raincoat` tinyint(1) NULL DEFAULT NULL COMMENT '草鞋还是蓑衣',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for journey_point
-- ----------------------------
DROP TABLE IF EXISTS `journey_point`;
CREATE TABLE `journey_point`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '长征点的名字',
  `is_main` tinyint unsigned NULL COMMENT '是否为主线 1是主线',
  `is_open` tinyint unsigned NULL COMMENT '是否开放 1为开放',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '如果是主线，为0；如果是直线，为从属主线的id',
  `id_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `is_hidentask` tinyint(1) NULL DEFAULT NULL COMMENT '是否有隐藏任务',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`  (
  `id` bigint(0) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `journey_id` bigint(0) NULL DEFAULT NULL COMMENT '长征点的id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_record
-- ----------------------------
DROP TABLE IF EXISTS `question_record`;
CREATE TABLE `question_record`  (
  `id` bigint(0) NOT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `point_id` bigint(0) NULL DEFAULT NULL COMMENT '长征点',
  `question_id` bigint(0) NULL DEFAULT NULL,
  `is_answer` tinyint(1) NULL DEFAULT NULL COMMENT '1是答题过',
  `q1` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q2` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q3` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q4` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q5` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q6` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q7` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q8` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q9` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q10` tinyint(1) NULL DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '逻辑删除 1为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint unsigned NOT NULL,
  `university` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校',
  `college` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院',
  `major` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stuno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `openid` bigint(0) NULL DEFAULT NULL COMMENT '小程序openid',
  `score` bigint(0) NULL DEFAULT NULL COMMENT '积分',
  `add_score` bigint(0) NULL DEFAULT NULL COMMENT '入账积分',
  `sub_score` bigint(0) NULL DEFAULT NULL COMMENT '出账积分',
  `step` bigint(0) NULL DEFAULT NULL COMMENT '学习步骤',
  `shoe` bigint(0) NULL DEFAULT NULL COMMENT '草鞋',
  `raincoat` bigint(0) NULL DEFAULT NULL COMMENT '蓑衣',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '逻辑删除 1为删除',
  `is_bind` tinyint(1) NULL DEFAULT NULL COMMENT '是否绑定了学生信息。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '奋斗', '反对法', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
