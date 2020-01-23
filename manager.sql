/*
Navicat MySQL Data Transfer

Source Server         : EndingProject
Source Server Version : 50013
Source Host           : localhost:3306
Source Database       : endingproject_sql

Target Server Type    : MYSQL
Target Server Version : 50013
File Encoding         : 65001

Date: 2020-01-23 17:52:40
*/

SET FOREIGN_KEY_CHECKS=0;
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
