/*
Navicat MySQL Data Transfer

Source Server         : EndingProject
Source Server Version : 50013
Source Host           : localhost:3306
Source Database       : endingproject_sql

Target Server Type    : MYSQL
Target Server Version : 50013
File Encoding         : 65001

Date: 2020-01-23 17:52:34
*/

SET FOREIGN_KEY_CHECKS=0;
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
