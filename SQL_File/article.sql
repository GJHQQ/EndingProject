/*
Navicat MySQL Data Transfer

Source Server         : EndingProject
Source Server Version : 50013
Source Host           : localhost:3306
Source Database       : endingproject_sql

Target Server Type    : MYSQL
Target Server Version : 50013
File Encoding         : 65001

Date: 2020-01-23 17:46:56
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `ar_id` varchar(10) NOT NULL,
  `ca_id` int(11) NOT NULL,
  `ar_number` varchar(4) NOT NULL,
  `ar_title` varchar(500) NOT NULL,
  `ar_image` varchar(500) default NULL,
  `ar_content` varchar(3000) NOT NULL,
  `ar_user` varchar(30) NOT NULL,
  `ar_time` varchar(30) NOT NULL,
  `ar_state` varchar(1) NOT NULL,
  `clicks` int(11) default NULL,
  PRIMARY KEY  (`ar_id`),
  KEY `ca_id` (`ca_id`),
  CONSTRAINT `ca_id` FOREIGN KEY (`ca_id`) REFERENCES `catalog` (`ca_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1912191031', '25', '1', '日本一颗草莓900 香港买家拍走30颗约合9万7千人民币', null, '<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;近日，到了草莓上市的季节，各位网友纷纷讨论有没有实现草莓自由时，日本天价草莓1颗900元登上微博热搜。</p><p>据报道，奈良县产的一盒3种共30颗草莓的高级礼盒拍出150万日元(约合9万7千人民币)的高价，一颗草莓值约900人民币。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;据悉，是由一位香港买家拍走，表示这草莓就值这个价，要拿回去观赏。卖家则表示“这不是草莓的价格”。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;延伸阅读：日本一只螃蟹拍出500万日元高价，是目前世界已知的最高价<br/></p><p>转眼又到了品尝螃蟹的季节。11月7日，在日本鸟取县举行的一次拍卖会上，一只螃蟹被拍出了500万日元（约合人民币32.1万元）的高价，日本螃蟹500万，还突破了此前的吉尼斯世界纪录。</p><p><br/></p>', '北晚新视觉综合', '2019-12-19', '1', '6');
INSERT INTO `article` VALUES ('1912202114', '27', '3', '特朗普被弹劾案绊了一跤，影响最大的是什么？', null, '<p>在创造历史这件事上，第45任美国总统特朗普收获了一个难堪的“第三”。</p><p>12月18日，在经过了漫长辩论后，美国国会众议院表决通过两项针对特朗普的弹劾条款，正式指控他滥用职权和妨碍国会，下一步将启动把弹劾案移交参议院审理的程序。</p><p><br/></p><p>特朗普成为了美国历史上第三位遭众议院弹劾的总统。</p><p><br/></p><p>那么，这场弹劾对特朗普来说是场灾难吗？</p><p>答案是，部分算。</p><p><img src=\"img/1579154738936071401.jpg\" title=\"1579154738936071401.jpg\" alt=\"28.jpg\" width=\"874\" height=\"441\"/></p><p>弹劾案在民主党主导的众议院获得通过，的确让特朗普十分恼怒。精于“推特治国”的他，用一张受迫害感十足的图片，表达了自己的态度：</p><p>“他们不是想搞我，他们是想搞你，我只是挡了他们的道”。</p><p><br/></p><p>在被众议院弹劾后，特朗普在他的官方推特上发的图片。</p><p>而在众议院就弹劾条款进行激辩时，特朗普选择前往密歇根州西北部城市巴特尔克里克参加集会。</p><p>密歇根州原本是民主党的票仓，但在2016年总统大选中却被特朗普从民主党总统候选人希拉里手中夺下，这一带有羞辱和示威意味的选择，可谓意味深长。</p><p>但特朗普的愤怒，难以洗刷“耻辱之日”带给他的污点。</p><p>正如美国政治新闻网站Politico评论的，“弹劾将永远附着在特朗普身上。从今往后，这将是学生们最早学到的有关第45任总统的事情之一。”</p><p><br/></p><p>从目前看，弹劾条款获通过后最大的影响，可能只是让特朗普的总统任期没有他预想的辉煌。</p><p>美国宪法规定，要想通过弹劾成功将现任总统赶下台，需要走完两个程序：</p><p>第一，众议院司法委员会表决通过弹劾条款，将弹劾案交给众议院全院辩论，对每一条款单独表决。如果众议院以简单多数通过弹劾，则移交参议院。这一程序有点类似于“起诉”，这也是特朗普刚刚输了的阶段。</p><p><br/></p><p>弹劾的重头戏，则在第二个程序：参议院审理。</p><p>此阶段中，将由联邦最高法院首席法官主持审理过程，100名参议员充当陪审员，听取控辩双方的辩论和有关证人的证词。</p><p>众议院以其司法委员会主席为代表扮演控方的角色，白宫则组成辩护团。如果结果为三分之二参议院同意，则总统将被罢免。</p><p>在美国历史上，还没有一位总统被成功罢免。</p><p><img src=\"img/1576807507472028422.jpg\" title=\"1576807507472028422.jpg\" alt=\"1ZG6104403-1.jpg\" width=\"651\" height=\"379\"/></p><p><br/></p><p>佩洛西说：“我们一旦启动弹劾，就希望这对我们国家会起到团结的作用，而不是分裂”。</p><p>但实际上，因为弹劾导致的两党分裂，已经不可避免。在众议院投票中，没有一名共和党议员支持弹劾条款，而只有三名民主党议员针对弹劾条款投下反对票。</p><p>此情此景，令共和党众议员彼得金想起了对克林顿的弹劾投票，对比之后他认为：“那显然是党派之争，但不像现在这么激烈。”</p><p>因此，预测参议院审理结果只是一道小学算术题。参议院的100个席位中，共和党人占53席，更何况通过弹劾案需要争取到三分之二的多数票。</p><p><br/></p><p>事实上，他们的真正用意并非赶特朗普下台，而是试图影响明年到来的美国总统选举。</p><p>两党恶斗，争夺的就是美国政治的硬通货??选票。</p><p><br/></p><p>以针对克林顿的弹劾为例。当年克林顿性骚扰莱温斯基，为自己迎来的弹劾条款中就有“说谎”和“妨碍公正”两项指控，虽然弹劾案最终未获通过，但也对接下来的总统选举产生了重要影响。事实上，很多美国人之所以为共和党总统候选人小布什投票，就是觉得他直率而诚实，品行更佳。</p><p>弹劾案会对选情产生什么影响，目前还难以预料。</p><p><br/></p><p>根据盖洛普公司的调查数据，尽管弹劾浪潮不断，但特朗普的支持率从10月份的39%一路攀升至12月初的45%。而《华尔街日报》的最新民调显示，在是否罢免的问题上，美国人中的支持和反对意见占比均为48%，没有哪种观点占据多数。</p><p>不过，在美国政治的“纸牌屋”里，本来就不缺乏阴谋、背刺和意外。</p><p><br/></p><p>也许特朗普会成为首位遭弹劾罢免的美国总统，也许正相反，他会成为首位被弹劾仍连任的美国总统。谁又能知道呢？</p><p>太平洋对岸，我们拭目以待。</p><p><br/></p>', '奶油小生', '2019-12-20', '1', '13');
INSERT INTO `article` VALUES ('1912251226', '25', '4', '儒家哲学', null, '<h1 style=\"text-align: center;\">儒家哲学儒家哲学儒家哲学儒家哲学</h1><p><strong>儒家哲学儒家哲学儒家哲学</strong></p>', '我的天哪', '2019-12-25', '1', '2');
INSERT INTO `article` VALUES ('2001163437', '31', '5', '特朗普被弹劾案绊了一跤，影响最大的是什么？', null, '<h1 style=\"text-align: center;\">pf8mpf8mpf8mpf8mpf8m</h1><p style=\"text-align: center;\">绊了一跤，影响最绊了一跤，影响最绊了一跤，</p><p style=\"text-align: center;\">影响最绊了一跤，影响最绊了一跤，影响最绊了一跤，影响最</p><p style=\"text-align: center;\">绊了一跤，影响最</p>', '我的天哪', '2020-01-16', '1', '0');

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
