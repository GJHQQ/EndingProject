/*
Navicat MySQL Data Transfer

Source Server         : EndingProject
Source Server Version : 50013
Source Host           : localhost:3306
Source Database       : endingproject_sql

Target Server Type    : MYSQL
Target Server Version : 50013
File Encoding         : 65001

Date: 2020-01-23 17:52:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `catalog`
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `ca_id` int(11) NOT NULL auto_increment,
  `ca_name` varchar(10) NOT NULL,
  `ca_number` varchar(2) NOT NULL,
  `ca_state` varchar(1) NOT NULL,
  PRIMARY KEY  (`ca_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of catalog
-- ----------------------------
INSERT INTO `catalog` VALUES ('24', 'javaweb', '5', '1');
INSERT INTO `catalog` VALUES ('25', '其他新闻', '1', '1');
INSERT INTO `catalog` VALUES ('26', 'JavaScript', '3', '1');
INSERT INTO `catalog` VALUES ('27', 'HTML', '4', '1');
INSERT INTO `catalog` VALUES ('28', '高等数学栏目', '12', '2');
INSERT INTO `catalog` VALUES ('29', '计算机基础', '7', '1');
INSERT INTO `catalog` VALUES ('30', '计算机组成原理', '2', '2');
INSERT INTO `catalog` VALUES ('31', 'python', '8', '1');
INSERT INTO `catalog` VALUES ('32', 'Linux', '11', '2');
INSERT INTO `catalog` VALUES ('33', '黑马程序员', '6', '1');
INSERT INTO `catalog` VALUES ('34', '数学栏目1234', '9', '2');
INSERT INTO `catalog` VALUES ('35', 'aaaaaaaaaa', '10', '2');

-- ----------------------------
-- Table structure for `images`
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `img_id` int(11) NOT NULL auto_increment,
  `img_title` varchar(30) NOT NULL,
  `img_name` varchar(30) NOT NULL,
  `img_date` varchar(30) NOT NULL,
  `img_state` varchar(1) NOT NULL,
  PRIMARY KEY  (`img_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of images
-- ----------------------------

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `manager_id` int(10) NOT NULL auto_increment,
  `manager_name` varchar(20) NOT NULL,
  `passwd` varchar(15) NOT NULL,
  `mstate` varchar(1) NOT NULL,
  PRIMARY KEY  (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'wangwu', '1233', '1');
INSERT INTO `manager` VALUES ('50', 'wangwu123', '11111', '2');
INSERT INTO `manager` VALUES ('51', 'AAA', '1234', '2');
INSERT INTO `manager` VALUES ('54', '你好', '123422', '2');
INSERT INTO `manager` VALUES ('57', 'fff', '1234', '2');
INSERT INTO `manager` VALUES ('59', 'eeeqw', '123422', '3');
