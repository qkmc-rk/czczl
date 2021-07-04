/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : czczl

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2021-07-04 18:53:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_item
-- ----------------------------
DROP TABLE IF EXISTS `exam_item`;
CREATE TABLE `exam_item` (
  `id` bigint(20) unsigned NOT NULL,
  `stem` varchar(255) DEFAULT NULL COMMENT '题干',
  `material_id` bigint(20) DEFAULT NULL COMMENT '对应材料id',
  `stem_type` tinyint(1) DEFAULT NULL COMMENT '试题类型(选择判断填空123)',
  `choice_a` varchar(8) DEFAULT NULL,
  `choice_b` varchar(8) DEFAULT NULL,
  `choice_c` varchar(8) DEFAULT NULL,
  `choice_d` varchar(8) DEFAULT NULL,
  `choice_answer` varchar(8) DEFAULT NULL COMMENT '选择题答案',
  `judge_answer` varchar(8) DEFAULT NULL COMMENT '判断题答案',
  `fill_answer` varchar(8) DEFAULT NULL COMMENT '填空题答案',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) unsigned zerofill NOT NULL COMMENT '逻辑删除 1为删除',
  `is_prize_raincoat` tinyint(1) DEFAULT NULL COMMENT '草鞋1还是蓑衣2',
  `choicea` varchar(255) DEFAULT NULL,
  `choiceb` varchar(255) DEFAULT NULL,
  `choicec` varchar(255) DEFAULT NULL,
  `choiced` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of exam_item
-- ----------------------------

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('3');

-- ----------------------------
-- Table structure for journey_point
-- ----------------------------
DROP TABLE IF EXISTS `journey_point`;
CREATE TABLE `journey_point` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '长征点的名字',
  `is_main` tinyint(3) unsigned DEFAULT NULL COMMENT '是否为主线 1是主线',
  `is_open` tinyint(3) unsigned DEFAULT NULL COMMENT '是否开放 1为开放',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '如果是主线，为0；如果是支线，为从属主线的id',
  `id_delete` tinyint(1) unsigned zerofill DEFAULT NULL,
  `is_hidentask` tinyint(1) DEFAULT NULL COMMENT '是否有隐藏任务',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of journey_point
-- ----------------------------

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `journey_id` bigint(20) DEFAULT NULL COMMENT '长征点的id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of material
-- ----------------------------

-- ----------------------------
-- Table structure for question_record
-- ----------------------------
DROP TABLE IF EXISTS `question_record`;
CREATE TABLE `question_record` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `point_id` bigint(20) DEFAULT NULL COMMENT '长征点',
  `question_id` bigint(20) DEFAULT NULL COMMENT '没用了，10道题目总共10个答案，怎么可能只有一个questionId呢',
  `is_answer` tinyint(1) DEFAULT NULL COMMENT '1是答题过',
  `q1` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q2` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q3` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q4` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q5` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q6` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q7` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q8` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q9` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `q10` tinyint(1) DEFAULT NULL COMMENT '1是正确的，0是错误的',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) unsigned zerofill NOT NULL COMMENT '逻辑删除 1为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of question_record
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL,
  `university` varchar(32) DEFAULT NULL COMMENT '学校',
  `college` varchar(32) DEFAULT NULL COMMENT '学院',
  `major` varchar(32) DEFAULT NULL COMMENT '专业',
  `grade` varchar(255) DEFAULT NULL COMMENT '年级',
  `clazz` varchar(255) DEFAULT NULL COMMENT '班级',
  `name` varchar(32) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `stuno` varchar(255) DEFAULT NULL COMMENT '学号',
  `openid` varchar(255) DEFAULT NULL COMMENT '小程序openid',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `add_score` int(11) DEFAULT '0' COMMENT '入账积分',
  `sub_score` int(11) DEFAULT '0' COMMENT '出账积分',
  `step` int(11) DEFAULT '0' COMMENT '学习步骤',
  `shoe` int(11) DEFAULT '0' COMMENT '草鞋',
  `raincoat` int(11) DEFAULT '0' COMMENT '蓑衣',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) unsigned zerofill NOT NULL COMMENT '逻辑删除 1为删除',
  `is_bind` tinyint(1) DEFAULT '0' COMMENT '是否绑定了学生信息。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', null, null, null, null, null, null, null, null, 'oVibi5NLksGX7bZUr8y0oyIUVMLI', '60', '60', null, '0', null, null, null, null, '0', '0');
