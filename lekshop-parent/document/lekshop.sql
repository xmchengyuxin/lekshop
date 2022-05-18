/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : lekshop

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-05-18 09:38:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_advert
-- ----------------------------
DROP TABLE IF EXISTS `cms_advert`;
CREATE TABLE `cms_advert` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cate` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '位置(1订单页面广告 2招商)',
  `img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '跳转链接',
  `nums` int DEFAULT '0' COMMENT '点击次数',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否显示',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cate` (`cate`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='广告表';

-- ----------------------------
-- Records of cms_advert
-- ----------------------------

-- ----------------------------
-- Table structure for cms_agreement
-- ----------------------------
DROP TABLE IF EXISTS `cms_agreement`;
CREATE TABLE `cms_agreement` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  `cate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型(1发票说明 2支付协议 3早餐钱包说明 4注册会员服务条款 5优惠券过期提醒说明 6优惠券使用说明 7分享好友文案 8会员权益说明)',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '活动详情',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1显示 0隐藏',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cate` (`cate`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='协议';

-- ----------------------------
-- Records of cms_agreement
-- ----------------------------
INSERT INTO `cms_agreement` VALUES ('3', '用户协议', '', '1', '内容', 0xE58685E5AEB9, '1', '2020-10-24 10:55:55', '', '2020-10-24 10:55:58', '');
INSERT INTO `cms_agreement` VALUES ('4', '隐私政策', '', '2', '内容', 0xE58685E5AEB9, '1', '2020-10-24 10:55:55', '', '2020-10-24 10:55:58', '');
INSERT INTO `cms_agreement` VALUES ('7', '提现规则', '', '3', '内容', 0xE58685E5AEB9, '1', '2020-10-24 10:55:55', '', '2020-10-24 10:55:58', '');
INSERT INTO `cms_agreement` VALUES ('10', '关于我们', '', '4', '内容', 0xE58685E5AEB9, '1', '2020-10-24 10:55:55', '', '2020-10-24 10:55:58', '');
INSERT INTO `cms_agreement` VALUES ('11', '商保服务协议', '', '5', '内容', 0xE58685E5AEB9, '1', '2021-01-30 15:03:22', '', '2021-01-30 15:03:25', '');
INSERT INTO `cms_agreement` VALUES ('12', '签到规则', '', 'point_rule', '', 0x3C703E312EE7ADBEE588B0E88EB7E5BE973130E7A7AFE588863C2F703E0A3C703E322EE9BB98E8AEA4E5819AE5AE8CE4B880E58D95E58D873130E7A7AFE588863C2F703E0A3C703E332E31E7BAA7EFBC8C307E323530E7A7AFE588862C20E68EA5E58D95E98791E9A29D313030E4BBA5E586853C2F703E0A3C703E342E32E7BAA7EFBC8C3235307E363030E7A7AFE588862C20E68EA5E58D95E98791E9A29D3130302D3430303C2F703E0A3C703E352E33E7BAA7EFBC8C3630307E31323030E7A7AFE588862C20E68EA5E58D95E98791E9A29D31303030E4BBA5E586853C2F703E0A3C703E362E34E7BAA7EFBC8C313230307E32383030E7A7AFE588862C20E68EA5E58D95E98791E9A29DE4B88DE999903C2F703E, '', '2021-06-04 20:20:07', '', '2021-06-07 17:59:22', '');

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT '1' COMMENT '1买手>>2商家',
  `cate_id` int DEFAULT NULL COMMENT '分类ID',
  `cate_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  `produce` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简介',
  `author` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作者',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '内容',
  `nums` int DEFAULT '0' COMMENT '点击次数',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '0隐藏 1显示',
  `sort` int DEFAULT NULL COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='文章';

-- ----------------------------
-- Records of cms_article
-- ----------------------------

-- ----------------------------
-- Table structure for cms_article_cate
-- ----------------------------
DROP TABLE IF EXISTS `cms_article_cate`;
CREATE TABLE `cms_article_cate` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '0隐藏 1显示',
  `sort` int DEFAULT NULL COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='文章分类';

-- ----------------------------
-- Records of cms_article_cate
-- ----------------------------
INSERT INTO `cms_article_cate` VALUES ('1', '新手帮助', '1', '1', '2021-01-15 11:34:14', '', '2021-01-15 11:34:44', '');
INSERT INTO `cms_article_cate` VALUES ('2', '常见问题', '1', '2', '2021-01-15 11:34:53', null, '2021-01-15 11:34:53', null);
INSERT INTO `cms_article_cate` VALUES ('99', '平台公告', '1', '3', '2021-01-22 17:10:46', null, '2021-09-09 09:29:08', null);

-- ----------------------------
-- Table structure for cms_banner
-- ----------------------------
DROP TABLE IF EXISTS `cms_banner`;
CREATE TABLE `cms_banner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cate` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '五代/七代',
  `location` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '位置(1首页 2菜单 3购物车)',
  `img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '跳转链接',
  `nums` int DEFAULT '0' COMMENT '点击次数',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '1' COMMENT '是否显示',
  `sort` int DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '修改时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='轮播图表';

-- ----------------------------
-- Records of cms_banner
-- ----------------------------
INSERT INTO `cms_banner` VALUES ('1', '', '1', 'https://qiniu.chengyuwb.com/1632472414882.jpg', '#', '0', '1', '1', '2021-09-01 21:28:23', '', '2021-09-01 21:28:23', '');
INSERT INTO `cms_banner` VALUES ('2', '', '2', 'https://qiniu.chengyuwb.com/1639118849514.jpg', '#', '0', '1', '1', '2021-12-09 15:48:48', '', '2021-12-09 15:48:48', '');
INSERT INTO `cms_banner` VALUES ('3', null, '2', 'https://qiniu.chengyuwb.com/1639119302924.jpg', '#', '0', '1', '2', '2021-12-10 14:55:04', null, '2021-12-10 14:55:04', null);
INSERT INTO `cms_banner` VALUES ('4', null, '2', 'https://qiniu.chengyuwb.com/1639126069277.jpg', '#', '0', '1', '3', '2021-12-10 16:47:50', null, '2021-12-10 16:47:50', null);
INSERT INTO `cms_banner` VALUES ('5', null, '2', 'https://qiniu.chengyuwb.com/1639126081949.jpg', '#', '0', '1', '2', '2021-12-10 16:48:03', null, '2021-12-10 16:48:03', null);
INSERT INTO `cms_banner` VALUES ('6', null, '3', 'https://qiniu.chengyuwb.com/1639191793163.png', '#', '0', '1', '3', '2021-12-11 11:03:14', null, '2021-12-11 11:03:14', null);

-- ----------------------------
-- Table structure for cms_feedback
-- ----------------------------
DROP TABLE IF EXISTS `cms_feedback`;
CREATE TABLE `cms_feedback` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1遇到问题 2功能建议',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容',
  `imgs` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  `video` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '视频',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0未处理 1已处理',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='意见反馈';

-- ----------------------------
-- Records of cms_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for cms_notice
-- ----------------------------
DROP TABLE IF EXISTS `cms_notice`;
CREATE TABLE `cms_notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公告标识',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '内容',
  `status` int DEFAULT '1' COMMENT '是否显示',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cate` (`cate`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='公告弹出框表';

-- ----------------------------
-- Records of cms_notice
-- ----------------------------
INSERT INTO `cms_notice` VALUES ('1', 'user', 0x3C703EE59388E59388E593883C2F703E, '1', null, '', '2022-04-15 15:05:04', '');
INSERT INTO `cms_notice` VALUES ('2', 'shop', 0x3C703E3C7370616E207374796C653D22636F6C6F723A20236666303030303B223E3C7374726F6E673EE591B5E591B5E591B53C2F7374726F6E673E3C2F7370616E3E3C2F703E, '1', null, '', '2022-04-15 15:05:09', '');

-- ----------------------------
-- Table structure for cms_source
-- ----------------------------
DROP TABLE IF EXISTS `cms_source`;
CREATE TABLE `cms_source` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cate_id` int DEFAULT NULL COMMENT '板块ID',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1图片 2视频',
  `source` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '素材',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='素材管理';

-- ----------------------------
-- Records of cms_source
-- ----------------------------
INSERT INTO `cms_source` VALUES ('270', '1', '1', 'https://qiniu.chengyuwb.com/1652256824896.png', '2022-05-11 16:13:46', null, null, null);
INSERT INTO `cms_source` VALUES ('271', '1', '1', 'https://qiniu.chengyuwb.com/1652256834662.png', '2022-05-11 16:13:56', null, null, null);

-- ----------------------------
-- Table structure for cms_source_cate
-- ----------------------------
DROP TABLE IF EXISTS `cms_source_cate`;
CREATE TABLE `cms_source_cate` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `sort` int DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='素材分类';

-- ----------------------------
-- Records of cms_source_cate
-- ----------------------------
INSERT INTO `cms_source_cate` VALUES ('1', '基础素材', '0', '2020-10-15 12:54:13', null, '2020-10-15 12:54:13', null);

-- ----------------------------
-- Table structure for config_account
-- ----------------------------
DROP TABLE IF EXISTS `config_account`;
CREATE TABLE `config_account` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT NULL COMMENT '1支付宝>>2微信>>3财付通>>4网银',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收款账号',
  `account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收款名称',
  `qr_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '二维码',
  `bank_sub` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支行',
  `bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开户银行',
  `status` int DEFAULT NULL COMMENT '0关闭>>1开启',
  `total_income` decimal(18,2) DEFAULT '0.00' COMMENT '总收款',
  `today_income` decimal(18,2) DEFAULT '0.00' COMMENT '今日收款',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `config_account_id` int DEFAULT NULL COMMENT '收款ID',
  PRIMARY KEY (`id`),
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统收款账号';

-- ----------------------------
-- Records of config_account
-- ----------------------------

-- ----------------------------
-- Table structure for config_gzh
-- ----------------------------
DROP TABLE IF EXISTS `config_gzh`;
CREATE TABLE `config_gzh` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `gzh_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公众号名称',
  `gzh_img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公众号二维码',
  `seller_status` int DEFAULT '0' COMMENT '0关闭>>1开启',
  `buyer_status` int DEFAULT '0' COMMENT '0关闭>>1开启',
  `gzh_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '公众号内容',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `sms_notice_status` int DEFAULT '0' COMMENT '短信通知>>0关闭>>1开启',
  `sms_new_task` int DEFAULT '0' COMMENT '派单通知>>0关闭>>1开启',
  `sms_need_comment` int DEFAULT '0' COMMENT '评价通知>>0关闭>>1开启',
  `sms_comment_reject` int DEFAULT '0' COMMENT '订单评价驳回>>0关闭>>1开启',
  `sms_order_will_quit` int DEFAULT '0' COMMENT '订单超时提醒>>0关闭>>1开启',
  `sms_close_account` int DEFAULT '0' COMMENT '账号禁用>>0关闭>>1开启',
  `sms_withdraw_sus` int DEFAULT '0' COMMENT '提现成功通知>>0关闭>>1开启',
  `sms_withdraw_fail` int DEFAULT '0' COMMENT '提现失败通知>>0关闭>>1开启',
  `sms_order_reject` int DEFAULT '0' COMMENT '订单驳回通知>>0关闭>>1开启',
  `sms_confirm_order` int DEFAULT '0' COMMENT '确认收货通知>>0关闭>>1开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='公众号设置';

-- ----------------------------
-- Records of config_gzh
-- ----------------------------

-- ----------------------------
-- Table structure for config_mission
-- ----------------------------
DROP TABLE IF EXISTS `config_mission`;
CREATE TABLE `config_mission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '佣金组名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '佣金组简介',
  `in_account` int DEFAULT '1' COMMENT '奖励的佣金存入>>1账户佣金>>2账户余额',
  `default_status` int DEFAULT '0' COMMENT '0不默认>>1默认',
  `sort` int DEFAULT '1' COMMENT '排序',
  `status` int DEFAULT '0' COMMENT '0关闭>>1开启',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户佣金设置';

-- ----------------------------
-- Records of config_mission
-- ----------------------------
INSERT INTO `config_mission` VALUES ('1', '普通佣金奖励组', '一般用于没特殊情况的会员佣金奖励即可	', '1', '1', '1', '1', '2021-01-16 11:27:42', '', '2021-11-06 11:44:06', '');

-- ----------------------------
-- Table structure for config_mission_detail
-- ----------------------------
DROP TABLE IF EXISTS `config_mission_detail`;
CREATE TABLE `config_mission_detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mission_config_id` int DEFAULT NULL COMMENT '佣金组别ID',
  `mission_config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '佣金组别',
  `group_id` int DEFAULT NULL COMMENT '会员组别',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员组别',
  `buyer_finish_rate_one` decimal(18,2) DEFAULT '0.00' COMMENT '买家>>一级推荐人佣金百分比',
  `buyer_finish_rate_two` decimal(18,2) DEFAULT '0.00' COMMENT '买家>>二级推荐人佣金百分比',
  `buyer_finish_rate_three` decimal(18,2) DEFAULT '0.00' COMMENT '买家>>三级推荐人佣金百分比',
  `buyer_valid_day` int DEFAULT '15' COMMENT '买家有效天数',
  `seller_finish_rate_one` decimal(18,2) DEFAULT '0.00' COMMENT '卖家>>一级推荐人佣金百分比',
  `seller_finish_rate_two` decimal(18,2) DEFAULT '0.00' COMMENT '卖家>>二级推荐人佣金百分比',
  `seller_finish_rate_three` decimal(18,2) DEFAULT '0.00' COMMENT '卖家>>三级推荐人佣金百分比',
  `seller_valid_day` int DEFAULT '15' COMMENT '卖家有效天数',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `mission_type` int DEFAULT '1' COMMENT '佣金形式>>1按百分比>>2固定金额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_id` (`group_id`,`mission_config_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='佣金设置';

-- ----------------------------
-- Records of config_mission_detail
-- ----------------------------

-- ----------------------------
-- Table structure for config_order
-- ----------------------------
DROP TABLE IF EXISTS `config_order`;
CREATE TABLE `config_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `auto_cancel_minutes` int DEFAULT '0' COMMENT '未支付订单自动取消时间',
  `auto_receive_day` int DEFAULT '0' COMMENT '自动收货时间',
  `auto_comment_day` int DEFAULT '0' COMMENT '自动评论时间',
  `last_refund_day` int DEFAULT '0' COMMENT '最后售后时间',
  `auto_agree_refund_day` int DEFAULT '0' COMMENT '自动同意售后时间',
  `buyer_refund_day` int DEFAULT '0' COMMENT '买家退货时间',
  `seller_receive_day` int DEFAULT '0' COMMENT '卖家收货时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单配置';

-- ----------------------------
-- Records of config_order
-- ----------------------------
INSERT INTO `config_order` VALUES ('1', '30', '7', '7', '7', '7', '7', '7', '2022-05-07 16:32:33', null, '2022-05-07 16:32:33', null);

-- ----------------------------
-- Table structure for config_realname
-- ----------------------------
DROP TABLE IF EXISTS `config_realname`;
CREATE TABLE `config_realname` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` int DEFAULT NULL COMMENT '会员组别',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员组别',
  `realname_status` int DEFAULT '0' COMMENT '是否开启实名认证>>0否>>1是',
  `realname_need_phone_code` int DEFAULT '0' COMMENT '实名时是否需要手机短信验证>>0否>>1是',
  `need_hand_img` int DEFAULT '0' COMMENT '实名时是否需要上传手持相片>>0否>>1是',
  `bank_status` int DEFAULT '0' COMMENT '是否开启银行认证>>0否>>1是',
  `bank_need_phone_code` int DEFAULT '0' COMMENT '认证时是否需要手机短信验证>>0否>>1是',
  `syn_realname` int DEFAULT '0' COMMENT '银行认证通过也同步实名通过>>0否>>1是',
  `third_realname_status` int DEFAULT '0' COMMENT '第三方接口实时实名认证>>0否>>1是',
  `third_bank_status` int DEFAULT '0' COMMENT '第三方接口实时银行认证>>0否>>1是',
  `bank_phone_status` int DEFAULT '0' COMMENT '是否验证银行预留手机号>>0否>>1是',
  `charge_num` int DEFAULT '0' COMMENT '第几次起收费',
  `charge_fee` decimal(18,2) DEFAULT '0.00' COMMENT '收费金额/元/次',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `weixin_need` int DEFAULT '0' COMMENT '微信账号>>0非必填>>1必填',
  `zfb_need` int DEFAULT '0' COMMENT '支付宝账号>>0非必填>>1必填',
  `realname_bank_same` int DEFAULT '0' COMMENT '实名和银行卡户名是否要一致>>0否>>1是',
  `weixin_img_need` int DEFAULT '0' COMMENT '微信收款码>>0非必填>>1必填',
  `zfb_img_need` int DEFAULT '0' COMMENT '支付宝收款码>>0非必填>>1必填',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='实名认证接口设置';

-- ----------------------------
-- Records of config_realname
-- ----------------------------

-- ----------------------------
-- Table structure for config_search
-- ----------------------------
DROP TABLE IF EXISTS `config_search`;
CREATE TABLE `config_search` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `num` int DEFAULT '0' COMMENT '搜索次数',
  `target` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标签',
  `hot_status` int DEFAULT NULL COMMENT '是否热搜(0:否 1:是)',
  `status` int DEFAULT '0' COMMENT '显示隐藏',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='搜索关键词表';

-- ----------------------------
-- Records of config_search
-- ----------------------------
INSERT INTO `config_search` VALUES ('6', '神奇裤子', '1900', '', null, '1', '2022-05-09 10:26:44', null, '2022-05-09 10:26:44', null);

-- ----------------------------
-- Table structure for config_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `config_withdraw`;
CREATE TABLE `config_withdraw` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` int DEFAULT NULL COMMENT '会员组别',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员组别',
  `withdraw_status` int DEFAULT '0' COMMENT '是否开启提现功能>>0否>>1是',
  `mission_to_balance` int DEFAULT '0' COMMENT '是否允许佣金转入账户余额>>0否>>1是',
  `withdraw_type` int DEFAULT '0' COMMENT '允许提现资金类型>>0全部>>1账户余额>>2账户佣金',
  `withdraw_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '1' COMMENT '允许提现付款方式>>1支付宝>>2微信>>3银行',
  `need_realname` int DEFAULT '0' COMMENT '是否实名认证才可提现>>0否>>1是',
  `need_bank` int DEFAULT '0' COMMENT '是否银行认证才可提现>>0否>>1是',
  `need_phone_code` int DEFAULT '0' COMMENT '是否要接收手机验证码>>0否>>1是',
  `min_balance_withdraw_amount` decimal(18,2) DEFAULT '0.00' COMMENT '账户余额提现最低金额',
  `max_balance_withdraw_amount` decimal(18,2) DEFAULT '0.00' COMMENT '账户余额提现最高金额',
  `min_mission_withdraw_amount` decimal(18,2) DEFAULT '0.00' COMMENT '账户佣金提现最低金额',
  `max_mission_withdraw_amount` decimal(18,2) DEFAULT '0.00' COMMENT '账户佣金提现最高金额',
  `withdraw_day_num` int DEFAULT '0' COMMENT '每天允许提现次数',
  `zfb_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '向你提现的支付宝账号',
  `zfb_realname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付宝姓名',
  `zfb_rate` decimal(18,2) DEFAULT '0.00' COMMENT '支付宝提现手续费率',
  `zfb_min_fee` decimal(18,2) DEFAULT '0.00' COMMENT '单笔最低手续费',
  `wx_rate` decimal(18,2) DEFAULT '0.00' COMMENT '微信提现手续费率',
  `wx_min_fee` decimal(18,2) DEFAULT '0.00' COMMENT '单笔最低手续费',
  `bank_rate` decimal(18,2) DEFAULT '0.00' COMMENT '银行提现手续费率',
  `bank_min_fee` decimal(18,2) DEFAULT '0.00' COMMENT '单笔最低手续费',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='提现设置';

-- ----------------------------
-- Records of config_withdraw
-- ----------------------------
INSERT INTO `config_withdraw` VALUES ('1', '1', '普通商家', '1', '0', '0', '1,3,2', '1', '1', '1', '50.00', '2000.00', '50.00', '2000.00', '10', '333444@qq.com', '阿里', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2021-01-11 14:46:44', '', '2021-12-28 15:25:52', '');
INSERT INTO `config_withdraw` VALUES ('2', '2', '新手会员', '1', '1', '0', '1,3,2', '1', '1', '0', '0.00', '2000.00', '0.00', '2000.00', '10', '**************', '*********', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2021-01-20 15:20:02', null, '2021-12-28 15:25:43', null);
INSERT INTO `config_withdraw` VALUES ('3', '3', '普通买家', '1', '1', '0', '1,2,3', '1', '1', '0', '100.00', '3000.00', '0.00', '0.00', '1', null, null, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2021-04-27 12:46:22', null, '2021-09-06 14:58:31', null);
INSERT INTO `config_withdraw` VALUES ('4', '7', '内部邀请', '1', '1', '0', '1,3,2', '1', '1', '1', '50.00', '2000.00', '50.00', '2000.00', '1', null, null, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2021-04-27 12:48:18', null, '2021-07-19 15:48:41', null);
INSERT INTO `config_withdraw` VALUES ('5', '4', '钻石会员', '1', '1', '0', '1,2,3', '1', '1', '0', '50.00', '50000.00', '50.00', '50000.00', '1', null, null, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2021-07-16 22:14:08', null, '2021-07-16 22:14:08', null);
INSERT INTO `config_withdraw` VALUES ('6', '5', '师傅', '1', '1', '0', '1,2,3', '1', '1', '1', '0.00', '5000.00', '0.00', '0.00', '1', null, null, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2021-07-17 11:31:28', null, '2021-07-22 15:24:02', null);
INSERT INTO `config_withdraw` VALUES ('7', '8', '会员', '1', '1', '0', '1,2,3', '1', '1', '1', '0.00', '3000.00', '0.00', '0.00', '1', null, null, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2021-07-22 15:54:57', null, '2021-07-22 15:54:58', null);

-- ----------------------------
-- Table structure for oms_car
-- ----------------------------
DROP TABLE IF EXISTS `oms_car`;
CREATE TABLE `oms_car` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_sku_id` int DEFAULT NULL COMMENT 'skuId',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `goods_param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品规格名称',
  `buy_price` decimal(18,2) DEFAULT '0.00' COMMENT '单价',
  `num` int DEFAULT NULL COMMENT '数量',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unx_memberid_goods_id_sp` (`member_id`,`goods_sku_id`) USING BTREE,
  KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='购物车';

-- ----------------------------
-- Records of oms_car
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order
-- ----------------------------
DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pay_order_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交易单号',
  `order_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `type` int DEFAULT '1' COMMENT '1普通商品>>2秒杀商品>>3拼团商品',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `member_nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户昵称',
  `member_head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `shop_logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家LOGO',
  `coupon_status` int DEFAULT '0' COMMENT '0不使用优惠券 1使用优惠券',
  `coupon_id` int DEFAULT NULL COMMENT '优惠券ID',
  `coupon_amount` decimal(18,2) DEFAULT NULL COMMENT '优惠券抵用金额',
  `freight_fee` decimal(18,2) DEFAULT NULL COMMENT '运费',
  `total_price` decimal(18,2) DEFAULT '0.00' COMMENT '商品总价',
  `pay_price` decimal(18,2) DEFAULT '0.00' COMMENT '实际支付价格',
  `refund_amount` decimal(18,2) DEFAULT '0.00' COMMENT '退款金额',
  `buy_time` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `pay_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付方式(wxPay微信 aliPay支付宝 balance余额支付)',
  `pay_channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付渠道(app,xcx,gzh)',
  `status` int DEFAULT NULL COMMENT '0待支付>>1待发货>>2待收货>>3已完成>>4已取消',
  `refund_status` int DEFAULT '0' COMMENT '0未退款>>1退款中>>2部分退款>>3整单退款',
  `del_ind` int DEFAULT '0' COMMENT '0未删除 1已删除',
  `receive_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货姓名',
  `receive_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货手机',
  `receive_phone_x` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'X号码',
  `receive_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货地址',
  `receive_lng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '经度',
  `receive_lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '纬度',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `delivery_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流方式',
  `delivery_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '第三方配送单号',
  `invoice_status` int DEFAULT '0' COMMENT '是否需要发票',
  `pay_end_time` datetime DEFAULT NULL COMMENT '超过这个时间未支付订单取消',
  `comment_expired_time` datetime DEFAULT NULL COMMENT '超过这个时间无法评论订单',
  `finish_expired_time` datetime DEFAULT NULL COMMENT '自动确认收货时间',
  `refund_expired_time` datetime DEFAULT NULL COMMENT '售后截止时间',
  `group_id` int DEFAULT NULL COMMENT '拼团购买方式>>-1单独购买>>0普通拼团>>其他为阶梯拼团ID',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`) USING BTREE,
  KEY `type` (`type`,`member_id`) USING BTREE,
  KEY `shop_id` (`shop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Records of oms_order
-- ----------------------------
INSERT INTO `oms_order` VALUES ('1', '165277018282310000005', '1652770182844036', '3', '5', 'yyw', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', null, '0', null, '0.00', '10.00', '1.00', '11.00', '0.00', '2022-05-17 14:49:43', null, null, null, null, null, '4', '0', '0', 'yuwei', '19859222520', null, '山西省阳泉市郊区801', null, null, '', null, null, null, '2022-05-17 15:19:43', null, null, null, null, '2022-05-17 14:49:43', null, '2022-05-17 15:19:42', null);
INSERT INTO `oms_order` VALUES ('2', '165277088593010020005', '1652770885937899', '3', '5', 'yyw', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', null, '0', null, '0.00', '10.00', '1.00', '11.00', '0.00', '2022-05-17 15:01:26', null, null, null, null, null, '4', '0', '0', 'yuwei', '19859222520', null, '山西省阳泉市郊区801', null, null, '', null, null, null, '2022-05-17 15:31:26', null, null, null, null, '2022-05-17 15:01:26', null, '2022-05-17 15:31:25', null);
INSERT INTO `oms_order` VALUES ('3', '165277141486610030005', '1652771414873277', '3', '5', 'yyw', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', null, '0', null, '0.00', '10.00', '1.00', '11.00', '0.00', '2022-05-17 15:10:15', null, null, null, null, null, '1', '0', '0', 'yuwei', '19859222520', null, '山西省阳泉市郊区801', null, null, '', null, null, null, '2022-05-17 15:40:15', null, null, null, null, '2022-05-17 15:10:15', null, '2022-05-17 15:23:13', null);
INSERT INTO `oms_order` VALUES ('4', '165277259977610050005', '1652772599784340', '1', '5', 'yyw', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', null, '0', null, '0.00', '10.00', '76.00', '86.00', '0.00', '2022-05-17 15:30:00', null, null, null, null, null, '1', '0', '0', 'yuwei', '19859222520', null, '山西省阳泉市郊区801', null, null, '', null, null, null, '2022-05-17 16:00:00', null, null, null, null, '2022-05-17 15:30:00', null, '2022-05-17 16:19:04', null);

-- ----------------------------
-- Table structure for oms_order_comment
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_comment`;
CREATE TABLE `oms_order_comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `detail_id` int DEFAULT NULL COMMENT '订单详情ID',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品名称',
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `goods_param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品规格名称',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `member_id` int NOT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `member_head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `anonymous_status` int DEFAULT '0' COMMENT '是否匿名',
  `status` int DEFAULT NULL COMMENT '0待评价>>1已评价>>2已追评>>3已过期',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容',
  `img` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评论图片',
  `goods_comment` int DEFAULT NULL COMMENT '1好评>>2中评>>3差评',
  `avg_star_num` decimal(18,2) DEFAULT NULL COMMENT '综合评分',
  `goods_star_num` decimal(18,2) DEFAULT NULL COMMENT '描述相符',
  `delivery_star_num` decimal(18,2) DEFAULT NULL COMMENT '发货速度',
  `shop_star_num` decimal(18,2) DEFAULT NULL COMMENT '服务态度',
  `logistic_star_num` decimal(18,2) DEFAULT NULL COMMENT '物流服务',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `detail_id` (`detail_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单评论';

-- ----------------------------
-- Records of oms_order_comment
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_detail`;
CREATE TABLE `oms_order_detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` int DEFAULT NULL COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `member_id` int NOT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `member_head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `shop_id` int NOT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_sku_id` int DEFAULT NULL COMMENT 'skuId',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `goods_param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品规格名称',
  `stock_type` int DEFAULT NULL COMMENT '库存计算方式>>1下单减库存>>2付款减库存',
  `buy_ori_price` decimal(18,2) DEFAULT NULL COMMENT '原价',
  `buy_price` decimal(18,2) DEFAULT '0.00' COMMENT '购买单价',
  `buy_num` int DEFAULT NULL COMMENT '购买数量',
  `refund_amount` decimal(18,2) DEFAULT '0.00' COMMENT '退款金额',
  `buy_total_price` decimal(18,2) DEFAULT '0.00' COMMENT '购买总价',
  `group_id` int DEFAULT NULL COMMENT '拼团购买方式>>-1单独购买>>0普通拼团>>其他为阶梯拼团ID',
  `group_num` int DEFAULT NULL COMMENT '成团人数',
  `group_limit_hours` int DEFAULT NULL COMMENT '成团限时',
  `status` int DEFAULT NULL COMMENT '0待支付>>1待发货>>2待收货>>3已完成>>4已取消>>5退款',
  `refund_status` int DEFAULT NULL COMMENT '0未退款>>1退款中>>2退款成功>>3退款驳回',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单备注',
  `comment_status` int DEFAULT '0' COMMENT '评论状态(0未评论  1已评论  2过期不能评)',
  `comment_expired_time` datetime DEFAULT NULL COMMENT '评论到期时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单明细表';

-- ----------------------------
-- Records of oms_order_detail
-- ----------------------------
INSERT INTO `oms_order_detail` VALUES ('1', '1', '1652770182844036', '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', '6', '32', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '黑/小', '1', '1.00', '1.00', '1', '0.00', '1.00', null, null, null, '4', '0', '', '0', '2022-05-24 14:49:43', '2022-05-17 14:49:43', null, '2022-05-17 15:19:42', null);
INSERT INTO `oms_order_detail` VALUES ('2', '2', '1652770885937899', '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', '6', '32', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '黑/小', '1', '1.00', '1.00', '1', '0.00', '1.00', null, null, null, '4', '0', '', '0', '2022-05-24 15:01:26', '2022-05-17 15:01:26', null, '2022-05-17 15:31:25', null);
INSERT INTO `oms_order_detail` VALUES ('3', '3', '1652771414873277', '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', '6', '32', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '黑/小', '1', '1.00', '1.00', '1', '0.00', '1.00', null, null, null, '1', '0', '', '0', '2022-05-24 15:10:15', '2022-05-17 15:10:15', null, '2022-05-17 15:23:13', null);
INSERT INTO `oms_order_detail` VALUES ('4', '4', '1652772599784340', '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', '7', '36', '亚克力门牌号码牌家用定制门牌号创意个性房间酒店可爱情侣房号数字门贴民宿入户轻奢包厢牌子字订制木质贴牌', 'https://qiniu.chengyuwb.com/1652772544350.jpg', '黑/小', '1', '55.00', '38.00', '1', '0.00', '38.00', null, null, null, '1', '0', '', '0', '2022-05-24 15:30:00', '2022-05-17 15:30:00', null, '2022-05-17 15:30:07', null);
INSERT INTO `oms_order_detail` VALUES ('5', '4', '1652772599784340', '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', 'LEKSHOP旗舰店', '7', '35', '亚克力门牌号码牌家用定制门牌号创意个性房间酒店可爱情侣房号数字门贴民宿入户轻奢包厢牌子字订制木质贴牌', 'https://qiniu.chengyuwb.com/1652772544350.jpg', '黑/大', '1', '55.00', '38.00', '1', '0.00', '38.00', null, null, null, '1', '0', '', '0', '2022-05-24 15:30:00', '2022-05-17 15:30:00', null, '2022-05-17 15:30:07', null);

-- ----------------------------
-- Table structure for oms_order_freight
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_freight`;
CREATE TABLE `oms_order_freight` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` int DEFAULT NULL COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `delivery_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流方式',
  `delivery_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流名称',
  `delivery_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流单号',
  `status` int DEFAULT '1' COMMENT '1待揽货 2已发货 3已完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单物流表';

-- ----------------------------
-- Records of oms_order_freight
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order_freight_detail
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_freight_detail`;
CREATE TABLE `oms_order_freight_detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` int DEFAULT NULL COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `order_freight_id` int DEFAULT NULL COMMENT '订单物流ID',
  `delivery_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流单号',
  `detail` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流详情',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单物流明细表';

-- ----------------------------
-- Records of oms_order_freight_detail
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order_group
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_group`;
CREATE TABLE `oms_order_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` int DEFAULT NULL COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `member_id` int DEFAULT NULL COMMENT '发起用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品名称',
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `original_price` decimal(18,2) DEFAULT '0.00' COMMENT '商品单买价',
  `group_price` decimal(18,2) DEFAULT '0.00' COMMENT '商品拼购价',
  `group_num` int DEFAULT NULL COMMENT '需要拼团人数',
  `have_group_num` int DEFAULT NULL COMMENT '已拼团人数',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int DEFAULT NULL COMMENT '1进行中 2成功 3失败',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拼团表';

-- ----------------------------
-- Records of oms_order_group
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order_group_member
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_group_member`;
CREATE TABLE `oms_order_group_member` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_group_id` int DEFAULT NULL COMMENT '拼购ID',
  `order_id` int DEFAULT NULL COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `member_id` int NOT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `member_head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `original_price` decimal(18,2) DEFAULT NULL COMMENT '商品原单价',
  `group_price` decimal(18,2) DEFAULT '0.00' COMMENT '商品拼购价',
  `buy_num` int DEFAULT NULL COMMENT '购买数量',
  `pay_price` decimal(18,2) DEFAULT NULL COMMENT '支付金额',
  `pay_status` int DEFAULT NULL COMMENT '1已支付 2退款中 3已退款',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='拼购成员表';

-- ----------------------------
-- Records of oms_order_group_member
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order_invoice
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_invoice`;
CREATE TABLE `oms_order_invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL COMMENT '用户ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `order_id` int NOT NULL COMMENT '订单ID',
  `order_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单编号',
  `type` int DEFAULT NULL COMMENT '1电子发票 2纸质发票',
  `per_type` int DEFAULT NULL COMMENT '1个人 2公司',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `company_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位名称',
  `taxpayer_num` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '纳税人识别码',
  `reg_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册地址',
  `reg_phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册电话',
  `bank` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开户银行',
  `bank_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行账户',
  `status` int DEFAULT '0' COMMENT '0未开 1已开',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单发票信息';

-- ----------------------------
-- Records of oms_order_invoice
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order_price_log
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_price_log`;
CREATE TABLE `oms_order_price_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` int DEFAULT NULL COMMENT '订单ID',
  `order_no` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单编号',
  `old_price` decimal(18,2) DEFAULT '0.00' COMMENT '原价',
  `now_price` decimal(18,2) DEFAULT '0.00' COMMENT '现价',
  `edit_price` decimal(18,2) DEFAULT '0.00' COMMENT '增减价格',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改原因',
  `edit_id` int DEFAULT NULL COMMENT '修改人ID',
  `edit_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单价格修改记录';

-- ----------------------------
-- Records of oms_order_price_log
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order_refund
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_refund`;
CREATE TABLE `oms_order_refund` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `refund_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退款单号',
  `order_id` int DEFAULT NULL COMMENT '订单ID',
  `detail_id` int DEFAULT NULL COMMENT '订单详情ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `refund_ind` int DEFAULT NULL COMMENT '1仅退款 2退货退款',
  `buy_time` datetime DEFAULT NULL COMMENT '下单时间',
  `original_amount` decimal(11,2) DEFAULT NULL COMMENT '原订单金额',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `refund_amount` decimal(18,2) DEFAULT NULL COMMENT '退款金额',
  `reason` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退款原因',
  `img` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退款凭证',
  `status` int DEFAULT NULL COMMENT '0申请退款>>1等待买家退货>>2买家已退货>>3商家已拒绝>>4平台介入>>5退款完成>>6退款关闭',
  `pay_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付方式',
  `refund_time` datetime DEFAULT NULL COMMENT '退单时间',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `goods_param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品规格名称',
  `return_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退货收件人',
  `return_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退货电话',
  `return_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退货地址',
  `delivery_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流方式',
  `delivery_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '第三方配送单号',
  `auto_agree_time` datetime DEFAULT NULL COMMENT '自动同意售后时间',
  `buyer_send_goods_time` datetime DEFAULT NULL COMMENT '买家寄货时间',
  `seller_confirm_time` datetime DEFAULT NULL COMMENT '卖家确认收货时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `refund_no` (`refund_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单退款';

-- ----------------------------
-- Records of oms_order_refund
-- ----------------------------

-- ----------------------------
-- Table structure for oms_order_refund_log
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_refund_log`;
CREATE TABLE `oms_order_refund_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `refund_id` int DEFAULT NULL COMMENT '退款ID',
  `refund_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退款单号',
  `operator_id` int DEFAULT NULL COMMENT '用户ID',
  `operator_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容',
  `img` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退款凭证',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `refund_id` (`refund_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单退款记录';

-- ----------------------------
-- Records of oms_order_refund_log
-- ----------------------------

-- ----------------------------
-- Table structure for oms_pay_order
-- ----------------------------
DROP TABLE IF EXISTS `oms_pay_order`;
CREATE TABLE `oms_pay_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pay_order_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付订单号',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `amount` decimal(18,2) DEFAULT NULL COMMENT '支付金额',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_end_time` datetime DEFAULT NULL COMMENT '支付到期时间',
  `pay_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付方式(aliPay支付宝  wxPay微信支付  balance余额)',
  `status` int DEFAULT NULL COMMENT '0待支付>>1支付成功>>2支付失败',
  `pay_channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付渠道(app,xcx,gzh)',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='支付订单表';

-- ----------------------------
-- Records of oms_pay_order
-- ----------------------------
INSERT INTO `oms_pay_order` VALUES ('1', '165277018282310000005', '5', 'yyw', '11.00', null, '2022-05-17 15:19:43', null, '2', null, '2022-05-17 14:49:43', null, '2022-05-17 15:19:42', null);
INSERT INTO `oms_pay_order` VALUES ('2', '165277088593010020005', '5', 'yyw', '11.00', null, '2022-05-17 15:31:26', null, '2', null, '2022-05-17 15:01:26', null, '2022-05-17 15:31:25', null);
INSERT INTO `oms_pay_order` VALUES ('3', '165277141486610030005', '5', 'yyw', '11.00', null, '2022-05-17 15:40:15', null, '1', null, '2022-05-17 15:10:15', null, '2022-05-17 15:23:13', null);
INSERT INTO `oms_pay_order` VALUES ('4', '165277259977610050005', '5', 'yyw', '86.00', null, '2022-05-17 16:00:00', null, '1', null, '2022-05-17 15:30:00', null, '2022-05-17 15:30:07', null);

-- ----------------------------
-- Table structure for pms_goods
-- ----------------------------
DROP TABLE IF EXISTS `pms_goods`;
CREATE TABLE `pms_goods` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `cate_pid` int DEFAULT NULL COMMENT '一级分类ID',
  `cate_tid` int DEFAULT NULL COMMENT '二级分类ID',
  `cate_id` int DEFAULT NULL COMMENT '分类ID',
  `cate_ids` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `cate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类目名称',
  `shop_cate_pid` int DEFAULT NULL COMMENT '店铺自建分类ID',
  `shop_cate_tid` int DEFAULT NULL COMMENT '店铺自建分类ID',
  `shop_cate_id` int DEFAULT NULL COMMENT '店铺自建分类ID',
  `shop_cate_ids` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shop_cate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺内类目',
  `type` int DEFAULT '1' COMMENT '1普通商品>>2秒杀商品>>3拼团商品',
  `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品标题',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品描述',
  `main_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `main_video` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '视频',
  `goods_img` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品图片',
  `detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '详情',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `sort` int DEFAULT '0' COMMENT '排序',
  `weight` int DEFAULT '0' COMMENT '权重',
  `status` int DEFAULT NULL COMMENT '0待审核>>1上架>>2仓库>>3删除>>4驳回',
  `stock_type` int DEFAULT NULL COMMENT '库存计算方式>>1下单减库存>>2付款减库存',
  `freight_template_id` int DEFAULT NULL COMMENT '运费模板ID',
  `point_num` int DEFAULT '0' COMMENT '点击数量',
  `sell_num` int DEFAULT '0' COMMENT '销量',
  `pay_num` int DEFAULT '0' COMMENT '付款数量',
  `comment_num` int DEFAULT '0' COMMENT '评论数量',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `collection_num` int DEFAULT '0' COMMENT '收藏数量',
  `group_type` int DEFAULT '1' COMMENT '1普通拼团>>2阶梯拼团',
  `group_num` int DEFAULT NULL COMMENT '成团人数',
  `group_limit_hours` int DEFAULT NULL COMMENT '成团限时',
  `group_limit_buy` int DEFAULT NULL COMMENT '个人限购次数',
  `group_single_buy` int DEFAULT '0' COMMENT '0不支持单买>>1支持单买',
  `seckill_begin_time` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `seckill_end_time` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  `seckill_limit_num` int DEFAULT NULL COMMENT '秒杀限购数量',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品表';

-- ----------------------------
-- Records of pms_goods
-- ----------------------------
INSERT INTO `pms_goods` VALUES ('3', '4', 'LEKSHOP旗舰店', '6', '7', null, null, '衣服/Nike', null, null, '7', null, '睡衣', '1', '日式简约插花工艺花瓶 圆润厚重玻璃花瓶装饰水培工艺品装饰摆件', '啊实打实的', 'https://qiniu.chengyuwb.com/1652342032948.png', null, 'https://qiniu.chengyuwb.com/1652342032948.png', 0x3C703EE5958AE5AE9EE68993E5AE9EE79A843C2F703E, '2.00', '9999', '0', '3', '1', '5', '0', '0', '0', '0', '0', '0', '0', '1', null, null, null, '0', null, null, null, '2022-05-14 15:29:29', null, '2022-05-14 17:01:37', null);
INSERT INTO `pms_goods` VALUES ('4', '4', 'LEKSHOP旗舰店', '18', '46', '81', '18,46', '手机数码/维修/T恤', '45', null, '45', '45', '3C配件', '2', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', '啊实打实的', 'https://qiniu.chengyuwb.com/1652518263793.jpg', null, 'https://qiniu.chengyuwb.com/1652518267659.jpg|https://qiniu.chengyuwb.com/1652518267657.jpg', 0x3C703EE998BFE4B889E5A4A7E88B8FE689933C2F703E, '2.00', '9999', '0', '1', '1', '5', '0', '0', '0', '0', '0', '0', '0', '1', null, null, null, '0', '2022-06-28 00:00:00', '2022-06-30 00:00:00', '200', '2022-05-14 16:51:18', null, '2022-05-17 18:12:34', null);
INSERT INTO `pms_goods` VALUES ('5', '4', 'LEKSHOP旗舰店', '19', '48', null, null, '男装/上装', null, null, '46', null, '维修', '3', '北欧简约创意玻璃花瓶透明水养插花瓶鲜花玫瑰百合花客厅摆件网红', '阿松大', 'https://qiniu.chengyuwb.com/1652519275830.jpg', null, 'https://qiniu.chengyuwb.com/1652519281582.jpg|https://qiniu.chengyuwb.com/1652519281583.jpg|https://qiniu.chengyuwb.com/1652519281582.jpg', 0x3C703EE998BFE8BEBE3C2F703E, '1.00', '9999', '0', '1', '1', '3', '0', '0', '0', '0', '0', '0', '0', '1', '3', '10', '1', '1', null, null, null, '2022-05-14 17:08:10', null, '2022-05-16 10:10:39', null);
INSERT INTO `pms_goods` VALUES ('6', '4', 'LEKSHOP旗舰店', '16', '29', '88', '16,29,88', '女装/女裙/半身裙', '21', '53', '56', '21,53', '生鲜/时令生鲜', '3', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', '阿三大苏打', 'https://qiniu.chengyuwb.com/1652519851448.jpg', null, 'https://qiniu.chengyuwb.com/1652519855010.jpg|https://qiniu.chengyuwb.com/1652519855013.jpg|https://qiniu.chengyuwb.com/1652519855013.jpg', 0x3C703EE998BFE4B889E5A4A7E88B8FE689933C2F703E, '1.00', '9999', '0', '1', '1', '5', '0', '0', '0', '0', '0', '0', '0', '2', null, '20', '1', '1', null, null, null, '2022-05-14 17:17:42', null, '2022-05-17 15:01:16', null);
INSERT INTO `pms_goods` VALUES ('7', '4', 'LEKSHOP旗舰店', '19', '48', null, '19,48', '男装/上装', '20', '49', null, '20,49', '鞋靴/女鞋', '1', '亚克力门牌号码牌家用定制门牌号创意个性房间酒店可爱情侣房号数字门贴民宿入户轻奢包厢牌子字订制木质贴牌', '阿松大', 'https://qiniu.chengyuwb.com/1652772544350.jpg', null, 'https://qiniu.chengyuwb.com/1652772550380.jpg|https://qiniu.chengyuwb.com/1652772550383.jpg|https://qiniu.chengyuwb.com/1652772550379.jpg|https://qiniu.chengyuwb.com/1652772550383.jpg', 0x3C703EE998BFE4B889E5A4A7E88B8FE689933C2F703E, '38.00', '9999', '0', '1', '1', '5', '0', '0', '0', '0', '0', '0', '0', '1', null, null, null, '0', null, null, null, '2022-05-17 15:29:20', null, '2022-05-17 15:29:20', null);

-- ----------------------------
-- Table structure for pms_goods_attr_key
-- ----------------------------
DROP TABLE IF EXISTS `pms_goods_attr_key`;
CREATE TABLE `pms_goods_attr_key` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品规格属性表';

-- ----------------------------
-- Records of pms_goods_attr_key
-- ----------------------------
INSERT INTO `pms_goods_attr_key` VALUES ('4', '4', '颜色');
INSERT INTO `pms_goods_attr_key` VALUES ('5', '4', '尺寸');
INSERT INTO `pms_goods_attr_key` VALUES ('6', '3', '空空');
INSERT INTO `pms_goods_attr_key` VALUES ('7', '3', '大大');
INSERT INTO `pms_goods_attr_key` VALUES ('8', '5', '颜色');
INSERT INTO `pms_goods_attr_key` VALUES ('9', '5', '尺寸');
INSERT INTO `pms_goods_attr_key` VALUES ('10', '6', '颜色');
INSERT INTO `pms_goods_attr_key` VALUES ('11', '6', '尺寸');
INSERT INTO `pms_goods_attr_key` VALUES ('12', '7', '颜色');
INSERT INTO `pms_goods_attr_key` VALUES ('13', '7', '大小');

-- ----------------------------
-- Table structure for pms_goods_attr_val
-- ----------------------------
DROP TABLE IF EXISTS `pms_goods_attr_val`;
CREATE TABLE `pms_goods_attr_val` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `attr_key_id` int DEFAULT NULL COMMENT '属性ID',
  `attr_key_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品规格属性值表';

-- ----------------------------
-- Records of pms_goods_attr_val
-- ----------------------------
INSERT INTO `pms_goods_attr_val` VALUES ('8', '4', '4', '颜色', '黑');
INSERT INTO `pms_goods_attr_val` VALUES ('9', '4', '4', '颜色', '白');
INSERT INTO `pms_goods_attr_val` VALUES ('10', '4', '5', '尺寸', '大');
INSERT INTO `pms_goods_attr_val` VALUES ('11', '4', '5', '尺寸', '小');
INSERT INTO `pms_goods_attr_val` VALUES ('12', '3', '6', '空空', '空');
INSERT INTO `pms_goods_attr_val` VALUES ('13', '3', '6', '空空', '大');
INSERT INTO `pms_goods_attr_val` VALUES ('14', '3', '7', '大大', '空');
INSERT INTO `pms_goods_attr_val` VALUES ('15', '3', '7', '大大', '小');
INSERT INTO `pms_goods_attr_val` VALUES ('16', '5', '8', '颜色', '黑');
INSERT INTO `pms_goods_attr_val` VALUES ('17', '5', '8', '颜色', '白');
INSERT INTO `pms_goods_attr_val` VALUES ('18', '5', '9', '尺寸', '大');
INSERT INTO `pms_goods_attr_val` VALUES ('19', '5', '9', '尺寸', '小');
INSERT INTO `pms_goods_attr_val` VALUES ('20', '6', '10', '颜色', '黑');
INSERT INTO `pms_goods_attr_val` VALUES ('21', '6', '10', '颜色', '白');
INSERT INTO `pms_goods_attr_val` VALUES ('22', '6', '11', '尺寸', '大');
INSERT INTO `pms_goods_attr_val` VALUES ('23', '6', '11', '尺寸', '小');
INSERT INTO `pms_goods_attr_val` VALUES ('24', '7', '12', '颜色', '黑');
INSERT INTO `pms_goods_attr_val` VALUES ('25', '7', '12', '颜色', '白');
INSERT INTO `pms_goods_attr_val` VALUES ('26', '7', '13', '大小', '大');
INSERT INTO `pms_goods_attr_val` VALUES ('27', '7', '13', '大小', '小');

-- ----------------------------
-- Table structure for pms_goods_cate
-- ----------------------------
DROP TABLE IF EXISTS `pms_goods_cate`;
CREATE TABLE `pms_goods_cate` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `level` int DEFAULT NULL COMMENT '分类等级(1一级 2二级 3三级)',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类展示图',
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标签',
  `pid` int DEFAULT '0' COMMENT '一级分类ID',
  `sort` int DEFAULT '0' COMMENT '排序',
  `status` int DEFAULT '1' COMMENT '显示隐藏',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品分类表';

-- ----------------------------
-- Records of pms_goods_cate
-- ----------------------------
INSERT INTO `pms_goods_cate` VALUES ('16', '女装', '1', 'https://qiniu.chengyuwb.com/1652524805171.jpg', null, '0', '1', '1', '2022-05-14 18:05:20', null, '2022-05-14 18:40:05', null);
INSERT INTO `pms_goods_cate` VALUES ('17', '母婴', '1', null, null, '0', '2', '1', '2022-05-14 18:05:27', null, '2022-05-14 18:05:27', null);
INSERT INTO `pms_goods_cate` VALUES ('18', '手机数码', '1', null, null, '0', '3', '1', '2022-05-14 18:05:36', null, '2022-05-14 18:05:36', null);
INSERT INTO `pms_goods_cate` VALUES ('19', '男装', '1', null, null, '0', '4', '1', '2022-05-14 18:05:44', null, '2022-05-14 18:05:44', null);
INSERT INTO `pms_goods_cate` VALUES ('20', '鞋靴', '1', null, null, '0', '5', '1', '2022-05-14 18:05:51', null, '2022-05-14 18:05:51', null);
INSERT INTO `pms_goods_cate` VALUES ('21', '生鲜', '1', null, null, '0', '6', '1', '2022-05-14 18:06:00', null, '2022-05-14 18:06:00', null);
INSERT INTO `pms_goods_cate` VALUES ('22', '美妆洗护', '1', null, null, '0', '7', '1', '2022-05-14 18:06:14', null, '2022-05-14 18:06:14', null);
INSERT INTO `pms_goods_cate` VALUES ('23', '百货', '1', null, null, '0', '8', '1', '2022-05-14 18:06:22', null, '2022-05-14 18:06:22', null);
INSERT INTO `pms_goods_cate` VALUES ('24', '家电', '1', null, null, '0', '9', '1', '2022-05-14 18:06:30', null, '2022-05-14 18:06:30', null);
INSERT INTO `pms_goods_cate` VALUES ('25', '饰品', '1', null, null, '0', '10', '1', '2022-05-14 18:06:40', null, '2022-05-14 18:06:40', null);
INSERT INTO `pms_goods_cate` VALUES ('26', '箱包', '1', null, null, '0', '11', '1', '2022-05-14 18:06:50', null, '2022-05-14 18:06:50', null);
INSERT INTO `pms_goods_cate` VALUES ('27', '女裤', '2', null, null, '16', '1', '1', '2022-05-14 18:07:29', null, '2022-05-14 18:07:29', null);
INSERT INTO `pms_goods_cate` VALUES ('28', '上装', '2', null, null, '16', '2', '1', '2022-05-14 18:07:38', null, '2022-05-14 18:07:38', null);
INSERT INTO `pms_goods_cate` VALUES ('29', '女裙', '2', null, null, '16', '3', '1', '2022-05-14 18:07:47', null, '2022-05-14 18:07:47', null);
INSERT INTO `pms_goods_cate` VALUES ('30', '套装', '2', null, null, '16', '4', '1', '2022-05-14 18:07:59', null, '2022-05-14 18:07:59', null);
INSERT INTO `pms_goods_cate` VALUES ('31', '外套', '2', null, null, '16', '5', '1', '2022-05-14 18:08:08', null, '2022-05-14 18:08:08', null);
INSERT INTO `pms_goods_cate` VALUES ('32', '儿童玩具', '2', null, null, '17', '1', '1', '2022-05-14 18:08:27', null, '2022-05-14 18:08:27', null);
INSERT INTO `pms_goods_cate` VALUES ('33', '童装', '2', null, null, '17', '2', '1', '2022-05-14 18:08:37', null, '2022-05-14 18:08:37', null);
INSERT INTO `pms_goods_cate` VALUES ('34', '童鞋', '2', null, null, '17', '3', '1', '2022-05-14 18:08:45', null, '2022-05-14 18:08:45', null);
INSERT INTO `pms_goods_cate` VALUES ('35', '洗护用品', '2', null, null, '17', '4', '1', '2022-05-14 18:08:57', null, '2022-05-14 18:08:57', null);
INSERT INTO `pms_goods_cate` VALUES ('36', '孕产穿搭', '2', null, null, '17', '5', '1', '2022-05-14 18:09:14', null, '2022-05-14 18:09:14', null);
INSERT INTO `pms_goods_cate` VALUES ('37', '哺乳喂养', '2', null, null, '17', '6', '1', '2022-05-14 18:09:29', null, '2022-05-14 18:09:29', null);
INSERT INTO `pms_goods_cate` VALUES ('38', '儿童家具', '2', null, null, '17', '7', '1', '2022-05-14 18:09:48', null, '2022-05-14 18:09:48', null);
INSERT INTO `pms_goods_cate` VALUES ('39', '婴儿装', '2', null, null, '17', '8', '1', '2022-05-14 18:10:00', null, '2022-05-14 18:10:00', null);
INSERT INTO `pms_goods_cate` VALUES ('40', '玩具车', '2', null, null, '17', '9', '1', '2022-05-14 18:10:09', null, '2022-05-14 18:10:09', null);
INSERT INTO `pms_goods_cate` VALUES ('41', '宝宝出行', '2', null, null, '17', '10', '1', '2022-05-14 18:10:21', null, '2022-05-14 18:10:21', null);
INSERT INTO `pms_goods_cate` VALUES ('42', '孕产用品', '2', null, null, '17', '11', '1', '2022-05-14 18:10:34', null, '2022-05-14 18:10:34', null);
INSERT INTO `pms_goods_cate` VALUES ('43', '奶粉营养', '2', null, null, '17', '12', '1', '2022-05-14 18:10:52', null, '2022-05-14 18:10:52', null);
INSERT INTO `pms_goods_cate` VALUES ('44', '手机', '2', null, null, '18', '1', '1', '2022-05-14 18:11:02', null, '2022-05-14 18:11:02', null);
INSERT INTO `pms_goods_cate` VALUES ('45', '3C配件', '2', null, null, '18', '2', '1', '2022-05-14 18:11:12', null, '2022-05-14 18:11:12', null);
INSERT INTO `pms_goods_cate` VALUES ('46', '维修', '2', null, null, '18', '4', '1', '2022-05-14 18:11:24', null, '2022-05-14 18:11:24', null);
INSERT INTO `pms_goods_cate` VALUES ('47', '裤子', '2', null, null, '19', '1', '1', '2022-05-14 18:12:23', null, '2022-05-14 18:12:23', null);
INSERT INTO `pms_goods_cate` VALUES ('48', '上装', '2', null, null, '19', '2', '1', '2022-05-14 18:12:32', null, '2022-05-14 18:12:32', null);
INSERT INTO `pms_goods_cate` VALUES ('49', '女鞋', '2', null, null, '20', '1', '1', '2022-05-14 18:12:44', null, '2022-05-14 18:12:44', null);
INSERT INTO `pms_goods_cate` VALUES ('50', '男鞋', '2', null, null, '20', '2', '1', '2022-05-14 18:12:48', null, '2022-05-14 18:12:48', null);
INSERT INTO `pms_goods_cate` VALUES ('51', '美味蔬菜', '2', null, null, '21', '1', '1', '2022-05-14 18:13:05', null, '2022-05-14 18:13:05', null);
INSERT INTO `pms_goods_cate` VALUES ('52', '禽蛋', '2', null, null, '21', '2', '1', '2022-05-14 18:13:11', null, '2022-05-14 18:13:11', null);
INSERT INTO `pms_goods_cate` VALUES ('53', '时令生鲜', '2', null, null, '21', '3', '1', '2022-05-14 18:13:18', null, '2022-05-14 18:13:18', null);
INSERT INTO `pms_goods_cate` VALUES ('54', '新鲜肉类', '2', null, null, '21', '4', '1', '2022-05-14 18:13:27', null, '2022-05-14 18:13:27', null);
INSERT INTO `pms_goods_cate` VALUES ('55', '彩妆', '2', null, null, '22', '1', '1', '2022-05-14 18:13:36', null, '2022-05-14 18:13:36', null);
INSERT INTO `pms_goods_cate` VALUES ('56', '美容美体', '2', null, null, '22', '2', '1', '2022-05-14 18:13:43', null, '2022-05-14 18:13:43', null);
INSERT INTO `pms_goods_cate` VALUES ('57', '护肤', '2', null, null, '22', '3', '1', '2022-05-14 18:13:47', null, '2022-05-14 18:13:47', null);
INSERT INTO `pms_goods_cate` VALUES ('58', '居家日用', '2', '', '', '23', '1', '1', '2022-05-14 18:13:56', '', '2022-05-14 18:15:54', '');
INSERT INTO `pms_goods_cate` VALUES ('59', '餐饮用具', '2', '', '', '23', '2', '1', '2022-05-14 18:14:04', '', '2022-05-14 18:16:01', '');
INSERT INTO `pms_goods_cate` VALUES ('60', '绿植宠物', '2', '', '', '23', '3', '1', '2022-05-14 18:14:11', '', '2022-05-14 18:15:21', '');
INSERT INTO `pms_goods_cate` VALUES ('61', '窗帘布艺', '2', '', '', '23', '4', '1', '2022-05-14 18:14:19', '', '2022-05-14 18:15:29', '');
INSERT INTO `pms_goods_cate` VALUES ('62', '厨房用品', '2', '', '', '23', '5', '1', '2022-05-14 18:14:25', '', '2022-05-14 18:15:34', '');
INSERT INTO `pms_goods_cate` VALUES ('63', '床上用品', '2', null, null, '23', '6', '1', '2022-05-14 18:14:45', null, '2022-05-14 18:14:45', null);
INSERT INTO `pms_goods_cate` VALUES ('64', '家具装饰', '2', null, null, '23', '7', '1', '2022-05-14 18:14:51', null, '2022-05-14 18:14:51', null);
INSERT INTO `pms_goods_cate` VALUES ('65', '收纳整理', '2', null, null, '23', '8', '1', '2022-05-14 18:14:57', null, '2022-05-14 18:14:57', null);
INSERT INTO `pms_goods_cate` VALUES ('66', '清洁工具', '2', null, null, '23', '9', '1', '2022-05-14 18:15:07', null, '2022-05-14 18:15:07', null);
INSERT INTO `pms_goods_cate` VALUES ('67', '厨房电器', '2', null, null, '24', '1', '1', '2022-05-14 18:16:18', null, '2022-05-14 18:16:18', null);
INSERT INTO `pms_goods_cate` VALUES ('68', '生活电器', '2', null, null, '24', '2', '1', '2022-05-14 18:16:24', null, '2022-05-14 18:16:24', null);
INSERT INTO `pms_goods_cate` VALUES ('69', '个护电器', '2', null, null, '24', '3', '1', '2022-05-14 18:16:32', null, '2022-05-14 18:16:32', null);
INSERT INTO `pms_goods_cate` VALUES ('70', '大家电', '2', null, null, '24', '4', '1', '2022-05-14 18:16:37', null, '2022-05-14 18:16:37', null);
INSERT INTO `pms_goods_cate` VALUES ('71', '手表', '2', null, null, '25', '1', '1', '2022-05-14 18:16:47', null, '2022-05-14 18:16:47', null);
INSERT INTO `pms_goods_cate` VALUES ('72', '眼镜', '2', null, null, '25', '2', '1', '2022-05-14 18:16:54', null, '2022-05-14 18:16:54', null);
INSERT INTO `pms_goods_cate` VALUES ('73', '珠宝', '2', null, null, '25', '3', '1', '2022-05-14 18:17:00', null, '2022-05-14 18:17:00', null);
INSERT INTO `pms_goods_cate` VALUES ('74', '女士背包', '2', null, null, '26', '1', '1', '2022-05-14 18:17:11', null, '2022-05-14 18:17:11', null);
INSERT INTO `pms_goods_cate` VALUES ('75', '男士背包', '2', null, null, '26', '2', '1', '2022-05-14 18:17:18', null, '2022-05-14 18:17:18', null);
INSERT INTO `pms_goods_cate` VALUES ('76', '箱包', '2', null, null, '26', '3', '1', '2022-05-14 18:17:23', null, '2022-05-14 18:17:23', null);
INSERT INTO `pms_goods_cate` VALUES ('77', '休闲裤', '3', 'https://qiniu.chengyuwb.com/1652525191075.jpg', null, '27', '1', '1', '2022-05-14 18:17:41', null, '2022-05-14 18:46:32', null);
INSERT INTO `pms_goods_cate` VALUES ('78', '牛仔裤', '3', 'https://qiniu.chengyuwb.com/1652525198511.jpg', null, '27', '2', '1', '2022-05-14 18:17:48', null, '2022-05-14 18:46:39', null);
INSERT INTO `pms_goods_cate` VALUES ('79', '打底裤', '3', 'https://qiniu.chengyuwb.com/1652525220908.jpg', null, '27', '3', '1', '2022-05-14 18:17:54', null, '2022-05-14 18:47:01', null);
INSERT INTO `pms_goods_cate` VALUES ('80', '西装裤', '3', 'https://qiniu.chengyuwb.com/1652525230079.jpg', null, '27', '4', '1', '2022-05-14 18:18:01', null, '2022-05-14 18:47:10', null);
INSERT INTO `pms_goods_cate` VALUES ('81', 'T恤', '3', 'https://qiniu.chengyuwb.com/1652525239461.jpg', null, '28', '1', '1', '2022-05-14 18:18:16', null, '2022-05-14 18:47:20', null);
INSERT INTO `pms_goods_cate` VALUES ('82', '衬衫', '3', 'https://qiniu.chengyuwb.com/1652525243341.jpg', null, '28', '2', '1', '2022-05-14 18:18:22', null, '2022-05-14 18:47:24', null);
INSERT INTO `pms_goods_cate` VALUES ('83', '雪纺衫', '3', 'https://qiniu.chengyuwb.com/1652525246922.jpg', null, '28', '3', '1', '2022-05-14 18:18:29', null, '2022-05-14 18:47:27', null);
INSERT INTO `pms_goods_cate` VALUES ('84', '卫衣', '3', 'https://qiniu.chengyuwb.com/1652525250913.jpg', null, '28', '4', '1', '2022-05-14 18:18:38', null, '2022-05-14 18:47:31', null);
INSERT INTO `pms_goods_cate` VALUES ('85', '毛衣', '3', 'https://qiniu.chengyuwb.com/1652525262257.jpg', null, '28', '5', '1', '2022-05-14 18:18:45', null, '2022-05-14 18:47:43', null);
INSERT INTO `pms_goods_cate` VALUES ('86', '马甲', '3', 'https://qiniu.chengyuwb.com/1652525275662.jpg', null, '28', '6', '1', '2022-05-14 18:18:51', null, '2022-05-14 18:47:56', null);
INSERT INTO `pms_goods_cate` VALUES ('87', '连衣裙', '3', 'https://qiniu.chengyuwb.com/1652525292240.jpg', null, '29', '1', '1', '2022-05-14 18:19:02', null, '2022-05-14 18:48:12', null);
INSERT INTO `pms_goods_cate` VALUES ('88', '半身裙', '3', 'https://qiniu.chengyuwb.com/1652525299016.jpg', null, '29', '2', '1', '2022-05-14 18:19:08', null, '2022-05-14 18:48:19', null);
INSERT INTO `pms_goods_cate` VALUES ('89', '旗袍', '3', 'https://qiniu.chengyuwb.com/1652525303646.jpg', null, '29', '3', '1', '2022-05-14 18:19:14', null, '2022-05-14 18:48:24', null);
INSERT INTO `pms_goods_cate` VALUES ('90', '时尚套装', '3', 'https://qiniu.chengyuwb.com/1652525310210.jpg', null, '30', '1', '1', '2022-05-14 18:19:23', null, '2022-05-14 18:48:30', null);
INSERT INTO `pms_goods_cate` VALUES ('91', '运动套装', '3', 'https://qiniu.chengyuwb.com/1652525315632.jpg', null, '30', '2', '1', '2022-05-14 18:19:31', null, '2022-05-14 18:48:36', null);
INSERT INTO `pms_goods_cate` VALUES ('92', '短外套', '3', 'https://qiniu.chengyuwb.com/1652525322146.jpg', null, '31', '1', '1', '2022-05-14 18:19:44', null, '2022-05-14 18:48:42', null);
INSERT INTO `pms_goods_cate` VALUES ('93', '西装', '3', 'https://qiniu.chengyuwb.com/1652525331029.jpg', null, '31', '2', '1', '2022-05-14 18:19:49', null, '2022-05-14 18:48:51', null);
INSERT INTO `pms_goods_cate` VALUES ('94', '风衣', '3', 'https://qiniu.chengyuwb.com/1652525337861.jpg', null, '31', '3', '1', '2022-05-14 18:19:54', null, '2022-05-14 18:48:58', null);
INSERT INTO `pms_goods_cate` VALUES ('95', '羽绒服', '3', 'https://qiniu.chengyuwb.com/1652525341839.jpg', null, '31', '4', '1', '2022-05-14 18:20:02', null, '2022-05-14 18:49:02', null);
INSERT INTO `pms_goods_cate` VALUES ('96', '毛呢大衣', '3', 'https://qiniu.chengyuwb.com/1652525347059.jpg', null, '31', '5', '1', '2022-05-14 18:20:10', null, '2022-05-14 18:49:07', null);
INSERT INTO `pms_goods_cate` VALUES ('97', '棉衣棉服', '3', 'https://qiniu.chengyuwb.com/1652525355967.jpg', null, '31', '6', '1', '2022-05-14 18:20:17', null, '2022-05-14 18:49:16', null);
INSERT INTO `pms_goods_cate` VALUES ('98', '皮草', '3', 'https://qiniu.chengyuwb.com/1652525360777.jpg', null, '31', '7', '1', '2022-05-14 18:20:25', null, '2022-05-14 18:49:21', null);
INSERT INTO `pms_goods_cate` VALUES ('99', '皮衣', '3', 'https://qiniu.chengyuwb.com/1652525364653.jpg', null, '31', '8', '1', '2022-05-14 18:20:30', null, '2022-05-14 18:49:25', null);

-- ----------------------------
-- Table structure for pms_goods_group
-- ----------------------------
DROP TABLE IF EXISTS `pms_goods_group`;
CREATE TABLE `pms_goods_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `num` int DEFAULT NULL COMMENT '成团人数',
  `discounts` decimal(18,2) DEFAULT '0.00' COMMENT '优惠阶梯%',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='阶梯拼团';

-- ----------------------------
-- Records of pms_goods_group
-- ----------------------------
INSERT INTO `pms_goods_group` VALUES ('1', '5', null, '0.00');
INSERT INTO `pms_goods_group` VALUES ('2', '6', '2', '90.00');
INSERT INTO `pms_goods_group` VALUES ('3', '6', '3', '80.00');
INSERT INTO `pms_goods_group` VALUES ('4', '6', '4', '70.00');
INSERT INTO `pms_goods_group` VALUES ('5', '6', '5', '60.00');

-- ----------------------------
-- Table structure for pms_goods_params
-- ----------------------------
DROP TABLE IF EXISTS `pms_goods_params`;
CREATE TABLE `pms_goods_params` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'icon',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数名称',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数值',
  `sort` int DEFAULT NULL COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品参数表';

-- ----------------------------
-- Records of pms_goods_params
-- ----------------------------
INSERT INTO `pms_goods_params` VALUES ('4', '6', null, '品牌', '爱马仕', '1', null, null, '2022-05-16 12:04:13', null);
INSERT INTO `pms_goods_params` VALUES ('5', '6', null, '档次', '高档', '2', null, null, '2022-05-16 12:04:23', null);

-- ----------------------------
-- Table structure for pms_goods_sku
-- ----------------------------
DROP TABLE IF EXISTS `pms_goods_sku`;
CREATE TABLE `pms_goods_sku` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `attr_symbol_path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性搭配方式',
  `attr_symbol_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性名称',
  `sku_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku编码',
  `ori_price` decimal(18,2) DEFAULT '0.00' COMMENT '原价',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '现价',
  `stock` int DEFAULT '0' COMMENT '库存',
  `weight` decimal(18,2) DEFAULT '0.00' COMMENT '重量/体积',
  `img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品规格SKU表';

-- ----------------------------
-- Records of pms_goods_sku
-- ----------------------------
INSERT INTO `pms_goods_sku` VALUES ('19', '4', '8/10', '黑/大', 'LEK1652518277974523', '1.00', '2.00', '3', '4.00', 'https://qiniu.chengyuwb.com/1652518263793.jpg');
INSERT INTO `pms_goods_sku` VALUES ('20', '4', '8/11', '黑/小', 'LEK1652518277975530', '1.00', '2.00', '3', '4.00', 'https://qiniu.chengyuwb.com/1652518263793.jpg');
INSERT INTO `pms_goods_sku` VALUES ('21', '4', '9/10', '白/大', 'LEK1652518277976242', '1.00', '2.00', '0', '4.00', 'https://qiniu.chengyuwb.com/1652518263793.jpg');
INSERT INTO `pms_goods_sku` VALUES ('22', '4', '9/11', '白/小', 'LEK1652518277977931', '1.00', '2.00', '3', '4.00', 'https://qiniu.chengyuwb.com/1652518263793.jpg');
INSERT INTO `pms_goods_sku` VALUES ('23', '3', '12/14', '空/空', 'LEK1652518897036987', '1.00', '2.00', '3', '4.00', 'https://qiniu.chengyuwb.com/1652342032948.png');
INSERT INTO `pms_goods_sku` VALUES ('24', '3', '12/15', '空/小', 'LEK1652518897040157', '1.00', '2.00', '3', '4.00', 'https://qiniu.chengyuwb.com/1652342032948.png');
INSERT INTO `pms_goods_sku` VALUES ('25', '3', '13/14', '大/空', 'LEK1652518897042444', '1.00', '2.00', '3', '4.00', 'https://qiniu.chengyuwb.com/1652342032948.png');
INSERT INTO `pms_goods_sku` VALUES ('26', '3', '13/15', '大/小', 'LEK1652518897045113', '1.00', '2.00', '3', '4.00', 'https://qiniu.chengyuwb.com/1652342032948.png');
INSERT INTO `pms_goods_sku` VALUES ('27', '5', '16/18', '黑/大', 'LEK1652519290420022', '1.00', '1.00', '1', '1.00', 'https://qiniu.chengyuwb.com/1652519275830.jpg');
INSERT INTO `pms_goods_sku` VALUES ('28', '5', '16/19', '黑/小', 'LEK1652519290421093', '1.00', '1.00', '1', '1.00', 'https://qiniu.chengyuwb.com/1652519275830.jpg');
INSERT INTO `pms_goods_sku` VALUES ('29', '5', '17/18', '白/大', 'LEK1652519290422790', '1.00', '1.00', '1', '1.00', 'https://qiniu.chengyuwb.com/1652519275830.jpg');
INSERT INTO `pms_goods_sku` VALUES ('30', '5', '17/19', '白/小', 'LEK1652519290423357', '1.00', '1.00', '1', '1.00', 'https://qiniu.chengyuwb.com/1652519275830.jpg');
INSERT INTO `pms_goods_sku` VALUES ('31', '6', '20/22', '黑/大', 'LEK1652519862485020', '1.00', '1.00', '999', '1.00', 'https://qiniu.chengyuwb.com/1652519851448.jpg');
INSERT INTO `pms_goods_sku` VALUES ('32', '6', '20/23', '黑/小', 'LEK1652519862486706', '1.00', '1.00', '999', '1.00', 'https://qiniu.chengyuwb.com/1652519851448.jpg');
INSERT INTO `pms_goods_sku` VALUES ('33', '6', '21/22', '白/大', 'LEK1652519862487811', '1.00', '1.00', '999', '1.00', 'https://qiniu.chengyuwb.com/1652519851448.jpg');
INSERT INTO `pms_goods_sku` VALUES ('34', '6', '21/23', '白/小', 'LEK1652519862488249', '1.00', '1.00', '999', '1.00', 'https://qiniu.chengyuwb.com/1652519851448.jpg');
INSERT INTO `pms_goods_sku` VALUES ('35', '7', '24/26', '黑/大', 'LEK1652772560138299', '55.00', '38.00', '998', '1.00', 'https://qiniu.chengyuwb.com/1652772544350.jpg');
INSERT INTO `pms_goods_sku` VALUES ('36', '7', '24/27', '黑/小', 'LEK1652772560140918', '55.00', '38.00', '998', '1.00', 'https://qiniu.chengyuwb.com/1652772544350.jpg');
INSERT INTO `pms_goods_sku` VALUES ('37', '7', '25/26', '白/大', 'LEK1652772560142969', '55.00', '38.00', '999', '1.00', 'https://qiniu.chengyuwb.com/1652772544350.jpg');
INSERT INTO `pms_goods_sku` VALUES ('38', '7', '25/27', '白/小', 'LEK1652772560143970', '55.00', '38.00', '999', '1.00', 'https://qiniu.chengyuwb.com/1652772544350.jpg');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `INT_PROP_1` int DEFAULT NULL,
  `INT_PROP_2` int DEFAULT NULL,
  `LONG_PROP_1` bigint DEFAULT NULL,
  `LONG_PROP_2` bigint DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint DEFAULT NULL,
  `PREV_FIRE_TIME` bigint DEFAULT NULL,
  `PRIORITY` int DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for rep_conversion_day
-- ----------------------------
DROP TABLE IF EXISTS `rep_conversion_day`;
CREATE TABLE `rep_conversion_day` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` date NOT NULL COMMENT '统计日期',
  `xcx_open_num` int DEFAULT '0' COMMENT '小程序打开次数',
  `app_open_num` int DEFAULT '0' COMMENT 'APP打开次数',
  `link_open_num` int DEFAULT '0' COMMENT 'H5打开次数',
  `trans_rate` decimal(18,2) DEFAULT '0.00' COMMENT '转化率(打开次数/成交单数)',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='转化日报表';

-- ----------------------------
-- Records of rep_conversion_day
-- ----------------------------

-- ----------------------------
-- Table structure for rep_member_day
-- ----------------------------
DROP TABLE IF EXISTS `rep_member_day`;
CREATE TABLE `rep_member_day` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` date NOT NULL COMMENT '统计日期',
  `total_num` int DEFAULT '0' COMMENT '本日总人数',
  `activite_num` int DEFAULT '0' COMMENT '本日活跃人数',
  `register_num` int DEFAULT '0' COMMENT '今日增长数量',
  `order_num` int DEFAULT '0' COMMENT '下单人数',
  `xcx_num` int DEFAULT '0' COMMENT '小程序注册人数',
  `app_num` int DEFAULT '0' COMMENT 'APP注册人数',
  `link_num` int DEFAULT '0' COMMENT 'H5注册人数',
  `xcx_order_num` int DEFAULT '0' COMMENT '小程序下单人数',
  `app_order_num` int DEFAULT '0' COMMENT 'APP下单人数',
  `link_order_num` int DEFAULT '0' COMMENT 'H5下单人数',
  `new_order_num` int DEFAULT '0' COMMENT '新客下单数',
  `old_order_num` int DEFAULT '0' COMMENT '老客下单数',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='顾客日报表';

-- ----------------------------
-- Records of rep_member_day
-- ----------------------------

-- ----------------------------
-- Table structure for rep_member_month
-- ----------------------------
DROP TABLE IF EXISTS `rep_member_month`;
CREATE TABLE `rep_member_month` (
  `id` int NOT NULL AUTO_INCREMENT,
  `month` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '统计月份',
  `total_num` int DEFAULT '0' COMMENT '本月总人数',
  `activite_num` int DEFAULT '0' COMMENT '本月活跃人数',
  `register_num` int DEFAULT '0' COMMENT '本月注册数量',
  `order_num` int DEFAULT '0' COMMENT '本月下单人数',
  `xcx_num` int DEFAULT '0' COMMENT '本月小程序注册人数',
  `app_num` int DEFAULT '0' COMMENT '本月APP注册人数',
  `link_num` int DEFAULT '0' COMMENT '本月H5注册人数',
  `xcx_order_num` int DEFAULT '0' COMMENT '本月小程序下单人数',
  `app_order_num` int DEFAULT '0' COMMENT '本月APP下单人数',
  `link_order_num` int DEFAULT '0' COMMENT '本月H5下单人数',
  `new_order_num` int DEFAULT '0' COMMENT '新客下单数',
  `old_order_num` int DEFAULT '0' COMMENT '老客下单数',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`month`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='顾客月报表';

-- ----------------------------
-- Records of rep_member_month
-- ----------------------------

-- ----------------------------
-- Table structure for rep_platform_day
-- ----------------------------
DROP TABLE IF EXISTS `rep_platform_day`;
CREATE TABLE `rep_platform_day` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` date NOT NULL COMMENT '统计日期',
  `business_amount` decimal(18,2) DEFAULT '0.00' COMMENT '营业额',
  `delivery_amount` decimal(18,2) DEFAULT '0.00' COMMENT '运费支出',
  `order_num` int DEFAULT '0' COMMENT '有效订单数量',
  `member_num` int DEFAULT '0' COMMENT '下单人数',
  `cancel_num` int DEFAULT '0' COMMENT '取消数量',
  `refund_num` int DEFAULT '0' COMMENT '退单数量',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `medium_comment_num` int DEFAULT '0' COMMENT '中评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `goods_amount` decimal(18,2) DEFAULT '0.00' COMMENT '商品销售额',
  `coupon_amount` decimal(18,2) DEFAULT '0.00' COMMENT '优惠券支出',
  `refund_rate` decimal(18,2) DEFAULT '0.00' COMMENT '退单率',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台日报表';

-- ----------------------------
-- Records of rep_platform_day
-- ----------------------------

-- ----------------------------
-- Table structure for rep_platform_goods_day
-- ----------------------------
DROP TABLE IF EXISTS `rep_platform_goods_day`;
CREATE TABLE `rep_platform_goods_day` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` date NOT NULL COMMENT '统计日期',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `cate_id` int DEFAULT NULL COMMENT '分类ID',
  `cate_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `sell_num` int DEFAULT '0' COMMENT '销售数量',
  `sell_amount` decimal(18,2) DEFAULT '0.00' COMMENT '销售金额',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `medium_comment_num` int DEFAULT '0' COMMENT '中评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `rebuy_rate` decimal(18,2) DEFAULT '0.00' COMMENT '复购率(购买两次以上人数/购买总人数)',
  `buy_two_mannum` int DEFAULT '0' COMMENT '购买2次以上人数',
  `buy_mannum` int DEFAULT '0' COMMENT '购买总人数',
  `rebuy_num` int DEFAULT '0' COMMENT '复购销量',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`goods_id`,`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='平台商品日报表';

-- ----------------------------
-- Records of rep_platform_goods_day
-- ----------------------------

-- ----------------------------
-- Table structure for rep_platform_goods_month
-- ----------------------------
DROP TABLE IF EXISTS `rep_platform_goods_month`;
CREATE TABLE `rep_platform_goods_month` (
  `id` int NOT NULL AUTO_INCREMENT,
  `month` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '统计月份',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `cate_id` int DEFAULT NULL COMMENT '分类ID',
  `cate_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `sell_num` int DEFAULT '0' COMMENT '销售数量',
  `sell_amount` decimal(18,2) DEFAULT '0.00' COMMENT '销售金额',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `medium_comment_num` int DEFAULT '0' COMMENT '中评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `rebuy_rate` decimal(18,2) DEFAULT '0.00' COMMENT '复购率(购买两次以上人数/购买总人数)',
  `buy_two_mannum` int DEFAULT '0' COMMENT '购买2次以上人数',
  `buy_mannum` int DEFAULT '0' COMMENT '购买总人数',
  `rebuy_num` int DEFAULT '0' COMMENT '复购销量',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`goods_id`,`month`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='平台商品月报表';

-- ----------------------------
-- Records of rep_platform_goods_month
-- ----------------------------

-- ----------------------------
-- Table structure for rep_platform_month
-- ----------------------------
DROP TABLE IF EXISTS `rep_platform_month`;
CREATE TABLE `rep_platform_month` (
  `id` int NOT NULL AUTO_INCREMENT,
  `month` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '统计月份',
  `business_amount` decimal(18,2) DEFAULT '0.00' COMMENT '营业额',
  `delivery_amount` decimal(18,2) DEFAULT '0.00' COMMENT '运费支出',
  `order_num` int DEFAULT '0' COMMENT '有效订单数量',
  `member_num` int DEFAULT '0' COMMENT '下单人数',
  `cancel_num` int DEFAULT '0' COMMENT '取消数量',
  `refund_num` int DEFAULT '0' COMMENT '退单数量',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `medium_comment_num` int DEFAULT '0' COMMENT '中评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `goods_amount` decimal(18,2) DEFAULT '0.00' COMMENT '商品销售额',
  `coupon_amount` decimal(18,2) DEFAULT '0.00' COMMENT '优惠券支出',
  `refund_rate` decimal(18,2) DEFAULT '0.00' COMMENT '退单率',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`month`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台月报表';

-- ----------------------------
-- Records of rep_platform_month
-- ----------------------------

-- ----------------------------
-- Table structure for rep_shop_day
-- ----------------------------
DROP TABLE IF EXISTS `rep_shop_day`;
CREATE TABLE `rep_shop_day` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `day` date NOT NULL COMMENT '统计日期',
  `business_amount` decimal(18,2) DEFAULT '0.00' COMMENT '营业额',
  `delivery_amount` decimal(18,2) DEFAULT '0.00' COMMENT '运费支出',
  `order_num` int DEFAULT '0' COMMENT '有效订单数量',
  `member_num` int DEFAULT '0' COMMENT '下单人数',
  `cancel_num` int DEFAULT '0' COMMENT '取消数量',
  `refund_num` int DEFAULT '0' COMMENT '退单数量',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `medium_comment_num` int DEFAULT '0' COMMENT '中评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `goods_amount` decimal(18,2) DEFAULT '0.00' COMMENT '商品销售额',
  `coupon_amount` decimal(18,2) DEFAULT '0.00' COMMENT '优惠券支出',
  `refund_rate` decimal(18,2) DEFAULT '0.00' COMMENT '退单率',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`shop_id`,`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺日报表';

-- ----------------------------
-- Records of rep_shop_day
-- ----------------------------

-- ----------------------------
-- Table structure for rep_shop_goods_day
-- ----------------------------
DROP TABLE IF EXISTS `rep_shop_goods_day`;
CREATE TABLE `rep_shop_goods_day` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `day` date NOT NULL COMMENT '统计日期',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `cate_id` int DEFAULT NULL COMMENT '分类ID',
  `cate_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `sell_num` int DEFAULT '0' COMMENT '销售数量',
  `sell_amount` decimal(18,2) DEFAULT '0.00' COMMENT '销售金额',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `medium_comment_num` int DEFAULT '0' COMMENT '中评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `rebuy_rate` decimal(18,2) DEFAULT '0.00' COMMENT '复购率(购买两次以上人数/购买总人数)',
  `buy_two_mannum` int DEFAULT '0' COMMENT '购买2次以上人数',
  `buy_mannum` int DEFAULT '0' COMMENT '购买总人数',
  `rebuy_num` int DEFAULT '0' COMMENT '复购销量',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`shop_id`,`goods_id`,`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='店铺商品日报表';

-- ----------------------------
-- Records of rep_shop_goods_day
-- ----------------------------

-- ----------------------------
-- Table structure for rep_shop_goods_month
-- ----------------------------
DROP TABLE IF EXISTS `rep_shop_goods_month`;
CREATE TABLE `rep_shop_goods_month` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `month` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '统计月份',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `cate_id` int DEFAULT NULL COMMENT '分类ID',
  `cate_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `sell_num` int DEFAULT '0' COMMENT '销售数量',
  `sell_amount` decimal(18,2) DEFAULT '0.00' COMMENT '销售金额',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `medium_comment_num` int DEFAULT '0' COMMENT '中评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `rebuy_rate` decimal(18,2) DEFAULT '0.00' COMMENT '复购率(购买两次以上人数/购买总人数)',
  `buy_two_mannum` int DEFAULT '0' COMMENT '购买2次以上人数',
  `buy_mannum` int DEFAULT '0' COMMENT '购买总人数',
  `rebuy_num` int DEFAULT '0' COMMENT '复购销量',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`shop_id`,`goods_id`,`month`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='店铺商品月报表';

-- ----------------------------
-- Records of rep_shop_goods_month
-- ----------------------------

-- ----------------------------
-- Table structure for rep_shop_month
-- ----------------------------
DROP TABLE IF EXISTS `rep_shop_month`;
CREATE TABLE `rep_shop_month` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `month` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '统计月份',
  `business_amount` decimal(18,2) DEFAULT '0.00' COMMENT '营业额',
  `delivery_amount` decimal(18,2) DEFAULT '0.00' COMMENT '运费支出',
  `order_num` int DEFAULT '0' COMMENT '有效订单数量',
  `member_num` int DEFAULT '0' COMMENT '下单人数',
  `cancel_num` int DEFAULT '0' COMMENT '取消数量',
  `refund_num` int DEFAULT '0' COMMENT '退单数量',
  `good_comment_num` int DEFAULT '0' COMMENT '好评数量',
  `medium_comment_num` int DEFAULT '0' COMMENT '中评数量',
  `bad_comment_num` int DEFAULT '0' COMMENT '差评数量',
  `goods_amount` decimal(18,2) DEFAULT '0.00' COMMENT '商品销售额',
  `coupon_amount` decimal(18,2) DEFAULT '0.00' COMMENT '优惠券支出',
  `refund_rate` decimal(18,2) DEFAULT '0.00' COMMENT '退单率',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report` (`shop_id`,`month`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺月报表';

-- ----------------------------
-- Records of rep_shop_month
-- ----------------------------

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` int DEFAULT NULL COMMENT '管理员类型ID',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `is_admin` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态 -1锁定 1正常',
  `auth_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='管理员表';

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('29', '1', '超级管理员', 'admin', '超管', 'https://qiniuimg.wurun.net/1602589139762.png', '$2a$10$mlxSLi1WK273vRY8sofiTeA5z.KLgGivycV1rTawne8v4kKmngmVS', '1', '1', '5QLWL4BCSGCKXCML', '2017-05-27 21:14:48', 'admincp', '2021-03-06 22:24:38', 'admin');

-- ----------------------------
-- Table structure for sys_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_login_log`;
CREATE TABLE `sys_admin_login_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `admin_id` int DEFAULT NULL COMMENT '用户ID',
  `admin_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `user_agent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览器登录类型',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COMMENT='后台用户登录日志表';

-- ----------------------------
-- Records of sys_admin_login_log
-- ----------------------------
INSERT INTO `sys_admin_login_log` VALUES ('1', '29', '超管', '127.0.0.1', null, null, '2022-04-22 14:10:35', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('2', '29', '超管', '127.0.0.1', null, null, '2022-05-07 11:02:21', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('3', '29', '超管', '127.0.0.1', null, null, '2022-05-07 11:29:49', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('4', '29', '超管', '127.0.0.1', null, null, '2022-05-09 10:22:48', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('5', '29', '超管', '127.0.0.1', null, null, '2022-05-09 16:47:26', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('6', '29', '超管', '127.0.0.1', null, null, '2022-05-09 17:41:28', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('7', '29', '超管', '127.0.0.1', null, null, '2022-05-10 09:08:16', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('8', '29', '超管', '127.0.0.1', null, null, '2022-05-10 09:42:40', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('9', '29', '超管', '127.0.0.1', null, null, '2022-05-11 09:42:50', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('10', '29', '超管', '127.0.0.1', null, null, '2022-05-11 11:25:04', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('11', '29', '超管', '127.0.0.1', null, null, '2022-05-11 16:10:30', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('12', '29', '超管', '127.0.0.1', null, null, '2022-05-11 17:00:16', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('13', '29', '超管', '127.0.0.1', null, null, '2022-05-12 10:29:14', 'admin');
INSERT INTO `sys_admin_login_log` VALUES ('14', '29', '超管', '127.0.0.1', null, null, '2022-05-14 18:04:07', 'admin');

-- ----------------------------
-- Table structure for sys_admin_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_operation_log`;
CREATE TABLE `sys_admin_operation_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `operate_id` int DEFAULT NULL COMMENT '操作人ID',
  `operate_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人用户名',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作内容',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '操作参数',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='操作日志';

-- ----------------------------
-- Records of sys_admin_operation_log
-- ----------------------------
INSERT INTO `sys_admin_operation_log` VALUES ('1', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323638227D, '2022-04-15 15:46:35', '超管', '2022-04-15 15:46:35', null);
INSERT INTO `sys_admin_operation_log` VALUES ('2', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323637227D, '2022-04-15 15:46:36', '超管', '2022-04-15 15:46:36', null);
INSERT INTO `sys_admin_operation_log` VALUES ('3', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323636227D, '2022-04-15 15:46:36', '超管', '2022-04-15 15:46:36', null);
INSERT INTO `sys_admin_operation_log` VALUES ('4', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323633227D, '2022-04-15 15:46:36', '超管', '2022-04-15 15:46:36', null);
INSERT INTO `sys_admin_operation_log` VALUES ('5', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323634227D, '2022-04-15 15:46:36', '超管', '2022-04-15 15:46:36', null);
INSERT INTO `sys_admin_operation_log` VALUES ('6', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323635227D, '2022-04-15 15:46:37', '超管', '2022-04-15 15:46:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('7', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323539227D, '2022-04-15 15:46:37', '超管', '2022-04-15 15:46:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('8', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323630227D, '2022-04-15 15:46:37', '超管', '2022-04-15 15:46:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('9', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323631227D, '2022-04-15 15:46:37', '超管', '2022-04-15 15:46:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('10', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323632227D, '2022-04-15 15:46:38', '超管', '2022-04-15 15:46:38', null);
INSERT INTO `sys_admin_operation_log` VALUES ('11', '29', 'admin', '操作人:admin:超管|修改系统参数', 0x7B22636F6E666967466F726D223A7B226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633393337383133383438382E706E67222C2264656661756C745F686561645F696D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313631393639343338313434332E6A7067222C227765625F6E616D65223A22E5908EE58FB0E7AEA1E79086222C22616E64726F69645F6170705F646F776E6C6F61645F75726C223A2268747470733A2F2F7777612E6C616E7A6F75692E636F6D2F6A696B65222C22696F735F6170705F646F776E6C6F61645F75726C223A226E756C6C222C22616E64726F69645F76657273696F6E223A223130303030222C22696F735F76657273696F6E223A223230303031222C22616E64726F69645F7570646174655F737461747573223A2231222C22696F735F7570646174655F737461747573223A2231222C226B6566755F74656C223A22313131313131222C226B6566755F7171223A223131313131222C226B6566755F7778223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633333030353937393733372E6A7067222C2266735F6B6566755F74656C223A2231313132323333222C2266735F6B6566755F7171223A223131313131222C2266735F6B6566755F7778223A22666379666C6F6174222C226963705F6E6F223A22E997BD494350E5A4873230303034383737E58FB72D31222C227765625F75726C223A22687474703A2F2F6C6574616F73686F702E6461797573686F702E746F70222C2268355F75726C223A22687474703A2F2F6C6574616F68352E6461797573686F702E746F70222C227370726561645F636F6E74656E74223A22E5889AE58F91E78EB0E4B8AAE5819AE4BBBBE58AA1E8B59AE99BB6E992B1E5B9B3E58FB02C20E4BBBBE4BD95E4BABAE983BDE58FAFE4BBA5E5819A2C20E997B2E697B6E8B59AE782B9E7949FE6B4BBE8B4B9222C2263616E65725F737461747573223A2231222C22706F7274616C5F617069223A22687474703A2F2F706F7274616C6170692E6A6B62642E766970222C2277656968755F6E6F5F6C6F67696E223A2230222C2277656968755F6E6F5F74616B655F7461736B223A2230222C226B6566755F75726C223A22687474703A2F2F696D2E6368797578696E2E636E2F696E6465782F696E6465782F686F6D653F766973697465725F69645C75303033647B7569647D5C7530303236766973697465725F6E616D655C75303033647B757365726E616D657D5C75303032366176617461725C75303033647B68656164496D677D5C7530303236627573696E6573735F69645C7530303364325C753030323667726F757069645C7530303364315C75303032367370656369616C5C7530303364335C74222C226B6566755F717132223A22313131222C226175746F5F74616B655F6F72646572223A2231222C226175746F5F74616B655F747261666669635F6F72646572223A2231222C2273686F705F6261636B67726F756E64223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F62366639363230392D353233642D343739372D623530392D37636566306333662E6A7067222C227265675F66696E6973685F7475726E223A2232222C2277675F74696D65223A223534303030222C2273686172655F626C61636B5F737769746368223A2231222C226170705F6C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633393130343032363034302E706E67222C226170705F6E616D65223A22E69E81E5AEA2E6BC94E7A4BA227D7D, '2022-04-15 15:47:11', '超管', '2022-04-15 15:47:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('12', '29', 'admin', '操作人:admin:超管|修改系统参数', 0x7B22636F6E666967466F726D223A7B226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633393337383133383438382E706E67222C2264656661756C745F686561645F696D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313631393639343338313434332E6A7067222C227765625F6E616D65223A22E5908EE58FB0E7AEA1E79086222C22616E64726F69645F6170705F646F776E6C6F61645F75726C223A2268747470733A2F2F7777612E6C616E7A6F75692E636F6D2F6A696B65222C22696F735F6170705F646F776E6C6F61645F75726C223A226E756C6C222C22616E64726F69645F76657273696F6E223A223130303030222C22696F735F76657273696F6E223A223230303031222C22616E64726F69645F7570646174655F737461747573223A2231222C22696F735F7570646174655F737461747573223A2231222C226B6566755F74656C223A22313131313131222C226B6566755F7171223A223131313131222C226B6566755F7778223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633333030353937393733372E6A7067222C2266735F6B6566755F74656C223A2231313132323333222C2266735F6B6566755F7171223A223131313131222C2266735F6B6566755F7778223A22666379666C6F6174222C226963705F6E6F223A22E997BD494350E5A4873230303034383737E58FB72D31222C227765625F75726C223A22687474703A2F2F6C6574616F73686F702E6461797573686F702E746F70222C2268355F75726C223A22687474703A2F2F6C6574616F68352E6461797573686F702E746F70222C227370726561645F636F6E74656E74223A22E5889AE58F91E78EB0E4B8AAE5819AE4BBBBE58AA1E8B59AE99BB6E992B1E5B9B3E58FB02C20E4BBBBE4BD95E4BABAE983BDE58FAFE4BBA5E5819A2C20E997B2E697B6E8B59AE782B9E7949FE6B4BBE8B4B9222C2263616E65725F737461747573223A2231222C22706F7274616C5F617069223A22687474703A2F2F706F7274616C6170692E6A6B62642E766970222C2277656968755F6E6F5F6C6F67696E223A2230222C2277656968755F6E6F5F74616B655F7461736B223A2230222C226B6566755F75726C223A22687474703A2F2F696D2E6368797578696E2E636E2F696E6465782F696E6465782F686F6D653F766973697465725F69645C75303033647B7569647D5C7530303236766973697465725F6E616D655C75303033647B757365726E616D657D5C75303032366176617461725C75303033647B68656164496D677D5C7530303236627573696E6573735F69645C7530303364325C753030323667726F757069645C7530303364315C75303032367370656369616C5C7530303364335C74222C226B6566755F717132223A22313131222C2273686F705F6261636B67726F756E64223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F62366639363230392D353233642D343739372D623530392D37636566306333662E6A7067222C227265675F66696E6973685F7475726E223A2232222C226170705F6C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633393130343032363034302E706E67222C226170705F6E616D65223A22E69E81E5AEA2E6BC94E7A4BA227D7D, '2022-04-22 16:07:57', '超管', '2022-04-22 16:07:57', null);
INSERT INTO `sys_admin_operation_log` VALUES ('13', '29', 'admin', '操作人:admin:超管|修改系统参数', 0x7B22636F6E666967466F726D223A7B226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633393337383133383438382E706E67222C2264656661756C745F686561645F696D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313631393639343338313434332E6A7067222C227765625F6E616D65223A224C454B53484F50222C22616E64726F69645F6170705F646F776E6C6F61645F75726C223A2268747470733A2F2F7777612E6C616E7A6F75692E636F6D2F6A696B65222C22696F735F6170705F646F776E6C6F61645F75726C223A226E756C6C222C22616E64726F69645F76657273696F6E223A223130303030222C22696F735F76657273696F6E223A223230303031222C22616E64726F69645F7570646174655F737461747573223A2231222C22696F735F7570646174655F737461747573223A2231222C226B6566755F74656C223A22313131313131222C226B6566755F7171223A223131313131222C226B6566755F7778223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633333030353937393733372E6A7067222C2266735F6B6566755F74656C223A2231313132323333222C2266735F6B6566755F7171223A223131313131222C2266735F6B6566755F7778223A22666379666C6F6174222C226963705F6E6F223A22E997BD494350E5A4873230303034383737E58FB72D31222C227765625F75726C223A22687474703A2F2F6C6574616F73686F702E6461797573686F702E746F70222C2268355F75726C223A22687474703A2F2F6C6574616F68352E6461797573686F702E746F70222C227370726561645F636F6E74656E74223A22E5889AE58F91E78EB0E4B8AAE5819AE4BBBBE58AA1E8B59AE99BB6E992B1E5B9B3E58FB02C20E4BBBBE4BD95E4BABAE983BDE58FAFE4BBA5E5819A2C20E997B2E697B6E8B59AE782B9E7949FE6B4BBE8B4B9222C2263616E65725F737461747573223A2231222C22706F7274616C5F617069223A22687474703A2F2F706F7274616C6170692E6A6B62642E766970222C2277656968755F6E6F5F6C6F67696E223A2230222C2277656968755F6E6F5F74616B655F7461736B223A2230222C226B6566755F75726C223A22687474703A2F2F696D2E6368797578696E2E636E2F696E6465782F696E6465782F686F6D653F766973697465725F69645C75303033647B7569647D5C7530303236766973697465725F6E616D655C75303033647B757365726E616D657D5C75303032366176617461725C75303033647B68656164496D677D5C7530303236627573696E6573735F69645C7530303364325C753030323667726F757069645C7530303364315C75303032367370656369616C5C7530303364335C74222C226B6566755F717132223A22313131222C2273686F705F6261636B67726F756E64223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F62366639363230392D353233642D343739372D623530392D37636566306333662E6A7067222C227265675F66696E6973685F7475726E223A2232222C226170705F6C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633393130343032363034302E706E67222C226170705F6E616D65223A22E69E81E5AEA2E6BC94E7A4BA227D7D, '2022-05-07 11:04:07', '超管', '2022-05-07 11:04:07', null);
INSERT INTO `sys_admin_operation_log` VALUES ('14', '29', 'admin', '操作人:admin:超管|修改系统参数', 0x7B22636F6E666967466F726D223A7B226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635313839353338323330342E706E67222C2264656661756C745F686561645F696D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313631393639343338313434332E6A7067222C227765625F6E616D65223A224C454B53484F50222C22616E64726F69645F6170705F646F776E6C6F61645F75726C223A2268747470733A2F2F7777612E6C616E7A6F75692E636F6D2F6A696B65222C22696F735F6170705F646F776E6C6F61645F75726C223A226E756C6C222C22616E64726F69645F76657273696F6E223A223130303030222C22696F735F76657273696F6E223A223230303031222C22616E64726F69645F7570646174655F737461747573223A2231222C22696F735F7570646174655F737461747573223A2231222C226B6566755F74656C223A22313131313131222C226B6566755F7171223A223131313131222C226B6566755F7778223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633333030353937393733372E6A7067222C2266735F6B6566755F74656C223A2231313132323333222C2266735F6B6566755F7171223A223131313131222C2266735F6B6566755F7778223A22666379666C6F6174222C226963705F6E6F223A22E997BD494350E5A4873230303034383737E58FB72D31222C227765625F75726C223A22687474703A2F2F6C6574616F73686F702E6461797573686F702E746F70222C2268355F75726C223A22687474703A2F2F6C6574616F68352E6461797573686F702E746F70222C227370726561645F636F6E74656E74223A22E5889AE58F91E78EB0E4B8AAE5819AE4BBBBE58AA1E8B59AE99BB6E992B1E5B9B3E58FB02C20E4BBBBE4BD95E4BABAE983BDE58FAFE4BBA5E5819A2C20E997B2E697B6E8B59AE782B9E7949FE6B4BBE8B4B9222C2263616E65725F737461747573223A2231222C22706F7274616C5F617069223A22687474703A2F2F706F7274616C6170692E6A6B62642E766970222C2277656968755F6E6F5F6C6F67696E223A2230222C2277656968755F6E6F5F74616B655F7461736B223A2230222C226B6566755F75726C223A22687474703A2F2F696D2E6368797578696E2E636E2F696E6465782F696E6465782F686F6D653F766973697465725F69645C75303033647B7569647D5C7530303236766973697465725F6E616D655C75303033647B757365726E616D657D5C75303032366176617461725C75303033647B68656164496D677D5C7530303236627573696E6573735F69645C7530303364325C753030323667726F757069645C7530303364315C75303032367370656369616C5C7530303364335C74222C226B6566755F717132223A22313131222C2273686F705F6261636B67726F756E64223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F62366639363230392D353233642D343739372D623530392D37636566306333662E6A7067222C227265675F66696E6973685F7475726E223A2232222C226170705F6C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633393130343032363034302E706E67222C226170705F6E616D65223A22E69E81E5AEA2E6BC94E7A4BA227D7D, '2022-05-07 11:49:50', '超管', '2022-05-07 11:49:50', null);
INSERT INTO `sys_admin_operation_log` VALUES ('15', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3338397D, '2022-05-07 12:06:26', '超管', '2022-05-07 12:06:26', null);
INSERT INTO `sys_admin_operation_log` VALUES ('16', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3435347D, '2022-05-07 12:06:32', '超管', '2022-05-07 12:06:32', null);
INSERT INTO `sys_admin_operation_log` VALUES ('17', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3338387D, '2022-05-07 12:06:35', '超管', '2022-05-07 12:06:35', null);
INSERT INTO `sys_admin_operation_log` VALUES ('18', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3339367D, '2022-05-07 12:06:37', '超管', '2022-05-07 12:06:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('19', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3337327D, '2022-05-07 12:06:39', '超管', '2022-05-07 12:06:39', null);
INSERT INTO `sys_admin_operation_log` VALUES ('20', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3339357D, '2022-05-07 12:06:42', '超管', '2022-05-07 12:06:42', null);
INSERT INTO `sys_admin_operation_log` VALUES ('21', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3333367D, '2022-05-07 12:06:44', '超管', '2022-05-07 12:06:44', null);
INSERT INTO `sys_admin_operation_log` VALUES ('22', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3333357D, '2022-05-07 12:06:46', '超管', '2022-05-07 12:06:46', null);
INSERT INTO `sys_admin_operation_log` VALUES ('23', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3233362C22706964223A39332C226C6576656C223A322C226E616D65223A22E993B6E8A18CE8AEA4E8AF81222C2275726C223A222F6D656D6265722F62616E6B222C22696D616765223A22222C22736F7274223A342C226164644279223A22222C2275706454696D65223A224D617920372C20323032322031323A30373A303720504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 12:07:07', '超管', '2022-05-07 12:07:07', null);
INSERT INTO `sys_admin_operation_log` VALUES ('24', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3236332C22706964223A39332C226C6576656C223A322C226E616D65223A22E5AE9EE5908DE8AEA4E8AF81222C2275726C223A222F6D656D6265722F7265616C6E616D65222C22696D616765223A22222C22736F7274223A342C226164644279223A22222C2275706454696D65223A224D617920372C20323032322031323A30373A313020504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 12:07:11', '超管', '2022-05-07 12:07:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('25', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3335302C22706964223A39332C226C6576656C223A322C226E616D65223A22E794A8E688B7E5A484E7BD9A222C2275726C223A222F6D656D6265722F70756E6973686D656E74222C22696D616765223A22222C22736F7274223A372C226164644279223A22222C2275706454696D65223A224D617920372C20323032322031323A30373A313320504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 12:07:14', '超管', '2022-05-07 12:07:14', null);
INSERT INTO `sys_admin_operation_log` VALUES ('26', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3338342C22706964223A39332C226C6576656C223A322C226E616D65223A22E6898BE69CBAE9809AE8AEAFE5BD95222C2275726C223A222F6D656D6265722F70686F6E65626F6F6B222C22696D616765223A22222C22736F7274223A31302C226164644279223A22222C2275706454696D65223A224D617920372C20323032322031323A30373A323320504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 12:07:24', '超管', '2022-05-07 12:07:24', null);
INSERT INTO `sys_admin_operation_log` VALUES ('27', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3235352C22706964223A3137322C226C6576656C223A322C226E616D65223A22E68F90E78EB0E8AEB0E5BD95222C2275726C223A222F66756E642F7769746864726177222C22696D616765223A22222C22736F7274223A312C226164644279223A22222C2275706454696D65223A224D617920372C20323032322031323A30373A333120504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 12:07:32', '超管', '2022-05-07 12:07:32', null);
INSERT INTO `sys_admin_operation_log` VALUES ('28', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3232382C22706964223A3137322C226C6576656C223A322C226E616D65223A22E68EA8E5B9BFE4BDA3E98791222C2275726C223A222F66756E642F73706D697373696F6E222C22696D616765223A22222C22736F7274223A352C226164644279223A22222C2275706454696D65223A224D617920372C20323032322031323A30373A333720504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 12:07:38', '超管', '2022-05-07 12:07:38', null);
INSERT INTO `sys_admin_operation_log` VALUES ('29', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3334367D, '2022-05-07 12:07:53', '超管', '2022-05-07 12:07:53', null);
INSERT INTO `sys_admin_operation_log` VALUES ('30', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3331347D, '2022-05-07 12:08:00', '超管', '2022-05-07 12:08:00', null);
INSERT INTO `sys_admin_operation_log` VALUES ('31', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3337317D, '2022-05-07 12:08:03', '超管', '2022-05-07 12:08:03', null);
INSERT INTO `sys_admin_operation_log` VALUES ('32', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3331352C22706964223A3237342C226C6576656C223A322C226E616D65223A22E5BEAEE4BFA1E79FADE4BFA1E9809AE79FA5222C2275726C223A222F636F6E6669672F677A68222C22696D616765223A22222C22736F7274223A342C226164644279223A22222C2275706454696D65223A224D617920372C20323032322031323A30383A313120504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 12:08:12', '超管', '2022-05-07 12:08:12', null);
INSERT INTO `sys_admin_operation_log` VALUES ('33', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3331367D, '2022-05-07 12:08:17', '超管', '2022-05-07 12:08:17', null);
INSERT INTO `sys_admin_operation_log` VALUES ('34', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3338367D, '2022-05-07 12:08:20', '超管', '2022-05-07 12:08:20', null);
INSERT INTO `sys_admin_operation_log` VALUES ('35', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3331377D, '2022-05-07 12:08:24', '超管', '2022-05-07 12:08:24', null);
INSERT INTO `sys_admin_operation_log` VALUES ('36', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3337337D, '2022-05-07 12:08:27', '超管', '2022-05-07 12:08:27', null);
INSERT INTO `sys_admin_operation_log` VALUES ('37', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3331397D, '2022-05-07 12:08:29', '超管', '2022-05-07 12:08:29', null);
INSERT INTO `sys_admin_operation_log` VALUES ('38', '29', 'admin', '操作人:admin:超管|删除菜单', 0x7B226964223A3332307D, '2022-05-07 12:08:31', '超管', '2022-05-07 12:08:31', null);
INSERT INTO `sys_admin_operation_log` VALUES ('39', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3133382C22706964223A3133372C226C6576656C223A322C226E616D65223A22E8BDAEE692ADE59BBE222C2275726C223A222F636F6E74656E742F62616E6E6572222C22696D616765223A22222C22736F7274223A312C226164644279223A22222C2275706454696D65223A224D617920372C20323032322031323A30383A343220504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 12:08:43', '超管', '2022-05-07 12:08:43', null);
INSERT INTO `sys_admin_operation_log` VALUES ('40', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3435362C226C6576656C223A312C226E616D65223A22E5BA97E993BAE7AEA1E79086222C2275726C223A222F73686F70222C22736F7274223A312C2261646454696D65223A224D617920372C203230323220323A32393A323720504D222C2275706454696D65223A224D617920372C203230323220323A32393A323720504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:29:28', '超管', '2022-05-07 14:29:28', null);
INSERT INTO `sys_admin_operation_log` VALUES ('41', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3435372C22706964223A3435362C226C6576656C223A322C226E616D65223A22E5BA97E993BAE58897E8A1A8222C2275726C223A222F73686F702F6C697374222C22736F7274223A312C2261646454696D65223A224D617920372C203230323220323A32393A343320504D222C2275706454696D65223A224D617920372C203230323220323A32393A343320504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:29:43', '超管', '2022-05-07 14:29:43', null);
INSERT INTO `sys_admin_operation_log` VALUES ('42', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3435382C22706964223A3435362C226C6576656C223A322C226E616D65223A22E696B0E5BA97E993BAE5AEA1E6A0B8222C2275726C223A222F73686F702F766572696679222C22736F7274223A322C2261646454696D65223A224D617920372C203230323220323A32393A353220504D222C2275706454696D65223A224D617920372C203230323220323A32393A353220504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:29:53', '超管', '2022-05-07 14:29:53', null);
INSERT INTO `sys_admin_operation_log` VALUES ('43', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3435392C22706964223A3435362C226C6576656C223A322C226E616D65223A22E69C8DE58AA1E7AEA1E79086222C2275726C223A222F73686F702F73686F7053657276696365222C22736F7274223A332C2261646454696D65223A224D617920372C203230323220323A33303A303020504D222C2275706454696D65223A224D617920372C203230323220323A33303A303020504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:30:01', '超管', '2022-05-07 14:30:01', null);
INSERT INTO `sys_admin_operation_log` VALUES ('44', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436302C22706964223A3435362C226C6576656C223A322C226E616D65223A22E8BF90E8B4B9E6A8A1E69DBF222C2275726C223A222F73686F702F66726569676874222C22736F7274223A342C2261646454696D65223A224D617920372C203230323220323A33303A313020504D222C2275706454696D65223A224D617920372C203230323220323A33303A313020504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:30:10', '超管', '2022-05-07 14:30:10', null);
INSERT INTO `sys_admin_operation_log` VALUES ('45', '29', 'admin', '操作人:admin:超管|添加编辑角色', 0x5B7B226964223A317D2C7B226E616D65223A22E8B685E7BAA7E7AEA1E79086E59198227D2C7B226D656E75496473223A223435362C3435372C3435382C3435392C3436302C39332C3335352C39342C3238312C3233362C3236332C3335302C3236342C3334382C3338342C3338352C3137322C3235352C3137342C3235362C3232372C3232382C3237342C3331312C3331332C3331352C3331382C3334372C3133372C3133382C3134312C3232302C3333322C3134322C3134332C3134342C3333332C3235342C3133322C3133332C3133342C3133352C3237312C3234332C3236322C3231372C3231382C3231392C3232332C3134352C3335312C3134372C3337392C3338372C3339342C333334227D5D, '2022-05-07 14:30:18', '超管', '2022-05-07 14:30:18', null);
INSERT INTO `sys_admin_operation_log` VALUES ('46', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A39332C226C6576656C223A312C226E616D65223A22E4BC9AE59198E7AEA1E79086222C2275726C223A222F6D656D626572222C22696D616765223A2275736572222C22736F7274223A322C226164644279223A22222C2275706454696D65223A224D617920372C203230323220323A33333A343520504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:33:46', '超管', '2022-05-07 14:33:46', null);
INSERT INTO `sys_admin_operation_log` VALUES ('47', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436312C226C6576656C223A312C226E616D65223A22E59586E59381E7AEA1E79086222C2275726C223A222F676F6F6473222C22736F7274223A332C2261646454696D65223A224D617920372C203230323220323A33333A353820504D222C2275706454696D65223A224D617920372C203230323220323A33333A353820504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:33:59', '超管', '2022-05-07 14:33:59', null);
INSERT INTO `sys_admin_operation_log` VALUES ('48', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436322C22706964223A3436312C226C6576656C223A322C226E616D65223A22E794B5E59586E58886E7B1BB222C2275726C223A222F676F6F64732F676F6F647343617465222C22736F7274223A312C2261646454696D65223A224D617920372C203230323220323A33343A313720504D222C2275706454696D65223A224D617920372C203230323220323A33343A313720504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:34:17', '超管', '2022-05-07 14:34:17', null);
INSERT INTO `sys_admin_operation_log` VALUES ('49', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436332C22706964223A3436312C226C6576656C223A322C226E616D65223A22E59586E59381E58897E8A1A8222C2275726C223A222F676F6F64732F6C697374222C22736F7274223A322C2261646454696D65223A224D617920372C203230323220323A33343A323720504D222C2275706454696D65223A224D617920372C203230323220323A33343A323720504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:34:28', '超管', '2022-05-07 14:34:28', null);
INSERT INTO `sys_admin_operation_log` VALUES ('50', '29', 'admin', '操作人:admin:超管|添加编辑角色', 0x5B7B226964223A317D2C7B226E616D65223A22E8B685E7BAA7E7AEA1E79086E59198227D2C7B226D656E75496473223A223435362C3435372C3435382C3435392C3436302C39332C3335352C39342C3238312C3233362C3236332C3335302C3236342C3334382C3338342C3338352C3436312C3436322C3436332C3137322C3235352C3137342C3235362C3232372C3232382C3237342C3331312C3331332C3331352C3331382C3334372C3133372C3133382C3134312C3232302C3333322C3134322C3134332C3134342C3333332C3235342C3133322C3133332C3133342C3133352C3237312C3234332C3236322C3231372C3231382C3231392C3232332C3134352C3335312C3134372C3337392C3338372C3339342C333334227D5D, '2022-05-07 14:34:35', '超管', '2022-05-07 14:34:35', null);
INSERT INTO `sys_admin_operation_log` VALUES ('51', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436342C22706964223A3237342C226C6576656C223A322C226E616D65223A22E8AEA2E58D95E9858DE7BDAE222C2275726C223A222F636F6E6669672F6F72646572222C22736F7274223A362C2261646454696D65223A224D617920372C203230323220323A33383A303620504D222C2275706454696D65223A224D617920372C203230323220323A33383A303620504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:38:07', '超管', '2022-05-07 14:38:07', null);
INSERT INTO `sys_admin_operation_log` VALUES ('52', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436352C22706964223A3237342C226C6576656C223A322C226E616D65223A22E6909CE7B4A2E7AEA1E79086222C2275726C223A222F636F6E6669672F736561726368222C22736F7274223A372C2261646454696D65223A224D617920372C203230323220323A33393A333020504D222C2275706454696D65223A224D617920372C203230323220323A33393A333020504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:39:31', '超管', '2022-05-07 14:39:31', null);
INSERT INTO `sys_admin_operation_log` VALUES ('53', '29', 'admin', '操作人:admin:超管|添加编辑角色', 0x5B7B226964223A317D2C7B226E616D65223A22E8B685E7BAA7E7AEA1E79086E59198227D2C7B226D656E75496473223A223435362C3435372C3435382C3435392C3436302C39332C3335352C39342C3238312C3233362C3236332C3335302C3236342C3334382C3338342C3338352C3436312C3436322C3436332C3137322C3235352C3137342C3235362C3232372C3232382C3237342C3331312C3331332C3331352C3436342C3331382C3436352C3334372C3133372C3133382C3134312C3232302C3333322C3134322C3134332C3134342C3333332C3235342C3133322C3133332C3133342C3133352C3237312C3234332C3236322C3231372C3231382C3231392C3232332C3134352C3335312C3134372C3337392C3338372C3339342C333334227D5D, '2022-05-07 14:39:43', '超管', '2022-05-07 14:39:43', null);
INSERT INTO `sys_admin_operation_log` VALUES ('54', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436352C22706964223A3237342C226C6576656C223A322C226E616D65223A22E6909CE7B4A2E7AEA1E79086222C2275726C223A222F636F6E6669672F736561726368222C22696D616765223A22222C22736F7274223A382C226164644279223A22222C2275706454696D65223A224D617920372C203230323220323A34303A303920504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:40:10', '超管', '2022-05-07 14:40:10', null);
INSERT INTO `sys_admin_operation_log` VALUES ('55', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436342C22706964223A3237342C226C6576656C223A322C226E616D65223A22E8AEA2E58D95E9858DE7BDAE222C2275726C223A222F636F6E6669672F6F72646572222C22696D616765223A22222C22736F7274223A372C226164644279223A22222C2275706454696D65223A224D617920372C203230323220323A34303A313320504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:40:14', '超管', '2022-05-07 14:40:14', null);
INSERT INTO `sys_admin_operation_log` VALUES ('56', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3331382C22706964223A3237342C226C6576656C223A322C226E616D65223A22E7B3BBE7BB9FE694B6E6ACBEE8B4A6E688B7222C2275726C223A222F636F6E6669672F6163636F756E74222C22696D616765223A22222C22736F7274223A362C226164644279223A22222C2275706454696D65223A224D617920372C203230323220323A34303A313720504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:40:17', '超管', '2022-05-07 14:40:17', null);
INSERT INTO `sys_admin_operation_log` VALUES ('57', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436362C226C6576656C223A312C226E616D65223A22E8AEA2E58D95E79B91E7AEA1222C2275726C223A222F6F72646572222C22736F7274223A332C2261646454696D65223A224D617920372C203230323220323A34333A333820504D222C2275706454696D65223A224D617920372C203230323220323A34333A333820504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:43:39', '超管', '2022-05-07 14:43:39', null);
INSERT INTO `sys_admin_operation_log` VALUES ('58', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436312C226C6576656C223A312C226E616D65223A22E59586E59381E7AEA1E79086222C2275726C223A222F676F6F6473222C22696D616765223A22222C22736F7274223A342C226164644279223A22222C2275706454696D65223A224D617920372C203230323220323A34333A343220504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:43:43', '超管', '2022-05-07 14:43:43', null);
INSERT INTO `sys_admin_operation_log` VALUES ('59', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3137322C226C6576656C223A312C226E616D65223A22E8B4A2E58AA1E7AEA1E79086222C2275726C223A222F66756E64222C22696D616765223A22222C22736F7274223A352C226164644279223A22222C2275706454696D65223A224D617920372C203230323220323A34333A343520504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:43:46', '超管', '2022-05-07 14:43:46', null);
INSERT INTO `sys_admin_operation_log` VALUES ('60', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436372C22706964223A3436362C226C6576656C223A322C226E616D65223A22E8AEA2E58D95E7AEA1E79086222C2275726C223A222F6F726465722F6C697374222C22736F7274223A312C2261646454696D65223A224D617920372C203230323220323A34343A303520504D222C2275706454696D65223A224D617920372C203230323220323A34343A303520504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:44:05', '超管', '2022-05-07 14:44:05', null);
INSERT INTO `sys_admin_operation_log` VALUES ('61', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436382C22706964223A3436362C226C6576656C223A322C226E616D65223A22E8AF84E8AEBAE7AEA1E79086222C2275726C223A222F6F726465722F636F6D6D656E74222C22736F7274223A322C2261646454696D65223A224D617920372C203230323220323A34343A313520504D222C2275706454696D65223A224D617920372C203230323220323A34343A313520504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:44:15', '超管', '2022-05-07 14:44:15', null);
INSERT INTO `sys_admin_operation_log` VALUES ('62', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3436392C22706964223A3436362C226C6576656C223A322C226E616D65223A22E98080E6ACBEE7AEA1E79086222C2275726C223A222F6F726465722F726566756E64222C22736F7274223A332C2261646454696D65223A224D617920372C203230323220323A34343A323420504D222C2275706454696D65223A224D617920372C203230323220323A34343A323420504D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-07 14:44:25', '超管', '2022-05-07 14:44:25', null);
INSERT INTO `sys_admin_operation_log` VALUES ('63', '29', 'admin', '操作人:admin:超管|添加编辑角色', 0x5B7B226964223A317D2C7B226E616D65223A22E8B685E7BAA7E7AEA1E79086E59198227D2C7B226D656E75496473223A223435362C3435372C3435382C3435392C3436302C39332C3335352C39342C3238312C3233362C3236332C3335302C3236342C3334382C3338342C3338352C3436362C3436372C3436382C3436392C3436312C3436322C3436332C3137322C3235352C3137342C3235362C3232372C3232382C3237342C3331312C3331332C3331352C3331382C3436342C3436352C3334372C3133372C3133382C3134312C3232302C3333322C3134322C3134332C3134342C3333332C3235342C3133322C3133332C3133342C3133352C3237312C3234332C3236322C3231372C3231382C3231392C3232332C3134352C3335312C3134372C3337392C3338372C3339342C333334227D5D, '2022-05-07 14:44:32', '超管', '2022-05-07 14:44:32', null);
INSERT INTO `sys_admin_operation_log` VALUES ('64', '29', 'admin', '操作人:admin:超管|修改短信配置', 0x7B22636F6E666967223A7B226964223A312C226163636F756E74223A224C54414934466971546F32614B3157473361476E785A6B6F222C2270617373776F7264223A2274795463357873625453514A5637755849725656775063524D525170366C222C2273656E6455726C223A226479736D736170692E616C6979756E63732E636F6D222C22736572766572506F7274223A223830222C227369676E4E616D65223A224C454B53484F50222C22726567697374657254656D706C6174654964223A22534D535F323135343035333234222C2262696E6454656D706C6174654964223A22534D535F323135343035333234222C22666F7267657454656D706C6174654964223A22534D535F323135343035333234222C22636F6D6D6F6E54656D706C6174654964223A22534D535F323135343035333234222C2270617373776F726454656D706C6174654964223A22534D535F323135343035333234222C2264656C697665727954656D706C6174654964223A22534D535F323035303535303130222C226164644279223A2261646D696E222C2275706454696D65223A224D617920372C203230323220333A33353A353820504D222C227570644279223A2261646D696E222C22736D7354797065223A22227D7D, '2022-05-07 15:35:59', '超管', '2022-05-07 15:35:59', null);
INSERT INTO `sys_admin_operation_log` VALUES ('65', '29', 'admin', '操作人:admin:超管|修改参数设置', 0x5B7B226B6579223A22636F6E6669675F6F72646572227D2C7B226A736F6E44617461223A227B5C226175746F43616E63656C4D696E757465735C223A5C2233305C222C5C226175746F526563656976654461795C223A5C22375C222C5C226175746F436F6D6D656E744461795C223A5C22375C222C5C226C617374526566756E644461795C223A5C22375C222C5C226175746F4167726565526566756E644461795C223A5C22335C222C5C226275796572526566756E644461795C223A5C22375C222C5C2273656C6C6572526563656976654461795C223A5C22375C222C5C2261646454696D655C223A6E756C6C2C5C2275706454696D655C223A6E756C6C7D227D5D, '2022-05-07 16:30:42', '超管', '2022-05-07 16:30:42', null);
INSERT INTO `sys_admin_operation_log` VALUES ('66', '29', 'admin', '操作人:admin:超管|修改参数设置', 0x5B7B226B6579223A22636F6E6669675F6F72646572227D2C7B226A736F6E44617461223A227B5C226175746F43616E63656C4D696E757465735C223A5C2233305C222C5C226175746F526563656976654461795C223A5C22375C222C5C226175746F436F6D6D656E744461795C223A5C22375C222C5C226C617374526566756E644461795C223A5C22375C222C5C226175746F4167726565526566756E644461795C223A5C22375C222C5C226275796572526566756E644461795C223A5C22375C222C5C2273656C6C6572526563656976654461795C223A5C22375C222C5C2261646454696D655C223A6E756C6C2C5C2275706454696D655C223A6E756C6C7D227D5D, '2022-05-07 16:31:14', '超管', '2022-05-07 16:31:14', null);
INSERT INTO `sys_admin_operation_log` VALUES ('67', '29', 'admin', '操作人:admin:超管|修改参数设置', 0x5B7B226B6579223A22636F6E6669675F6F72646572227D2C7B226A736F6E44617461223A227B5C226175746F43616E63656C4D696E757465735C223A5C2233305C222C5C226175746F526563656976654461795C223A5C22375C222C5C226175746F436F6D6D656E744461795C223A5C22375C222C5C226C617374526566756E644461795C223A5C22375C222C5C226175746F4167726565526566756E644461795C223A5C22375C222C5C226275796572526566756E644461795C223A5C22375C222C5C2273656C6C6572526563656976654461795C223A5C22375C222C5C2261646454696D655C223A6E756C6C2C5C2275706454696D655C223A6E756C6C7D227D5D, '2022-05-07 16:32:33', '超管', '2022-05-07 16:32:33', null);
INSERT INTO `sys_admin_operation_log` VALUES ('68', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A312C226E756D223A3130392C22746172676574223A22686F74222C22737461747573223A312C2261646454696D65223A224D617920372C203230323220343A34393A343720504D222C2275706454696D65223A224D617920372C203230323220343A34393A343720504D227D7D, '2022-05-07 16:49:47', '超管', '2022-05-07 16:49:47', null);
INSERT INTO `sys_admin_operation_log` VALUES ('69', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A322C226E616D65223A22E5A5B3E8A1A3222C226E756D223A3130392C22746172676574223A22686F74222C22737461747573223A312C2261646454696D65223A224D617920372C203230323220343A35303A323220504D222C226164644279223A22222C2275706454696D65223A224D617920372C203230323220343A35303A323220504D222C227570644279223A22227D7D, '2022-05-07 16:50:23', '超管', '2022-05-07 16:50:23', null);
INSERT INTO `sys_admin_operation_log` VALUES ('70', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A332C226E616D65223A22313131222C226E756D223A3130392C22746172676574223A22686F74222C22737461747573223A312C2261646454696D65223A224D617920372C203230323220343A35303A333820504D222C226164644279223A22222C2275706454696D65223A224D617920372C203230323220343A35303A333820504D222C227570644279223A22227D7D, '2022-05-07 16:50:39', '超管', '2022-05-07 16:50:39', null);
INSERT INTO `sys_admin_operation_log` VALUES ('71', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A342C22737461747573223A302C2261646454696D65223A224D617920372C203230323220343A35313A313420504D222C2275706454696D65223A224D617920372C203230323220343A35313A313420504D227D7D, '2022-05-07 16:51:15', '超管', '2022-05-07 16:51:15', null);
INSERT INTO `sys_admin_operation_log` VALUES ('72', '29', 'admin', '操作人:admin:超管|删除搜索设置', 0x7B22696473223A22332C322C312C34227D, '2022-05-07 16:51:21', '超管', '2022-05-07 16:51:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('73', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A352C226E616D65223A22E8A1A3E69C8D222C226E756D223A3130302C22746172676574223A22686F74222C22737461747573223A312C2261646454696D65223A224D617920392C20323032322031303A32363A323020414D222C2275706454696D65223A224D617920392C20323032322031303A32363A323020414D227D7D, '2022-05-09 10:26:21', '超管', '2022-05-09 10:26:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('74', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A352C22737461747573223A302C2275706454696D65223A224D617920392C20323032322031303A32363A323220414D227D7D, '2022-05-09 10:26:23', '超管', '2022-05-09 10:26:23', null);
INSERT INTO `sys_admin_operation_log` VALUES ('75', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A352C22737461747573223A312C2275706454696D65223A224D617920392C20323032322031303A32363A323520414D227D7D, '2022-05-09 10:26:25', '超管', '2022-05-09 10:26:25', null);
INSERT INTO `sys_admin_operation_log` VALUES ('76', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A352C226E616D65223A22E8A1A3E69C8D31222C226E756D223A3130302C22746172676574223A22686F74222C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D617920392C20323032322031303A32363A333020414D222C227570644279223A22227D7D, '2022-05-09 10:26:30', '超管', '2022-05-09 10:26:30', null);
INSERT INTO `sys_admin_operation_log` VALUES ('77', '29', 'admin', '操作人:admin:超管|添加编辑搜索设置', 0x7B22636F6E666967223A7B226964223A362C226E616D65223A22E7A59EE5A587E8A3A4E5AD90222C226E756D223A313930302C22746172676574223A22222C22737461747573223A312C2261646454696D65223A224D617920392C20323032322031303A32363A343420414D222C2275706454696D65223A224D617920392C20323032322031303A32363A343420414D227D7D, '2022-05-09 10:26:44', '超管', '2022-05-09 10:26:44', null);
INSERT INTO `sys_admin_operation_log` VALUES ('78', '29', 'admin', '操作人:admin:超管|删除搜索设置', 0x7B22696473223A2235227D, '2022-05-09 10:26:51', '超管', '2022-05-09 10:26:51', null);
INSERT INTO `sys_admin_operation_log` VALUES ('79', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A312C226D656D6265724964223A312C226D656D6265724E616D65223A22313131222C2274797065223A332C226C6576656C223A302C226E616D65223A224C454B53484F50E69797E888B0E5BA97222C226B6579776F7264223A224C454B53484F50222C2270726F64756365223A224C454B53484F50222C2273657276696365223A22312C322C33222C22736572766963654E616D65223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323036383439323230342E706E67222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2270726F76696E6365436F6465223A22313430303030222C2263697479223A22E995BFE6B2BBE5B882222C2263697479436F6465223A22313430343030222C2261726561223A22E5B1AFE79599E58CBA222C2261726561436F6465223A22313430343035222C2261646472657373223A22222C226C696B654E756D223A3130302C22676F6F647353746172223A342E352C2273656C6C657253746172223A342E352C226C6F67697374696353746172223A342E352C22776569676874223A302C22736F7274223A312C22737461747573223A312C2272656D61726B223A224C454B53484F50222C2273656C66537461747573223A302C226C697665537461747573223A302C226164644279223A22222C2275706454696D65223A224D617920392C20323032322031313A35373A333120414D222C227570644279223A22227D7D, '2022-05-09 11:57:31', '超管', '2022-05-09 11:57:31', null);
INSERT INTO `sys_admin_operation_log` VALUES ('80', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A312C22737461747573223A312C2275706454696D65223A224D617920392C20323032322031313A35373A343320414D227D7D, '2022-05-09 11:57:43', '超管', '2022-05-09 11:57:43', null);
INSERT INTO `sys_admin_operation_log` VALUES ('81', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A312C22736F7274223A322C2275706454696D65223A224D617920392C20323032322031313A35383A333920414D227D7D, '2022-05-09 11:58:39', '超管', '2022-05-09 11:58:39', null);
INSERT INTO `sys_admin_operation_log` VALUES ('82', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A312C2273656C66537461747573223A312C2275706454696D65223A224D617920392C20323032322031323A30303A323920504D227D7D, '2022-05-09 12:00:29', '超管', '2022-05-09 12:00:29', null);
INSERT INTO `sys_admin_operation_log` VALUES ('83', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A312C226D656D6265724964223A312C226D656D6265724E616D65223A22313131222C2274797065223A332C226C6576656C223A302C226E616D65223A224C454B53484F50E69797E888B0E5BA97222C226B6579776F7264223A224C454B53484F50222C2270726F64756365223A224C454B53484F50222C2273657276696365223A22312C322C34222C22736572766963654E616D65223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323036383439323230342E706E67222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2270726F76696E6365436F6465223A22313430303030222C2263697479223A22E995BFE6B2BBE5B882222C2263697479436F6465223A22313430343030222C2261726561223A22E5B1AFE79599E58CBA222C2261726561436F6465223A22313430343035222C2261646472657373223A22222C226C696B654E756D223A3130302C22676F6F647353746172223A342E352C2273656C6C657253746172223A342E352C226C6F67697374696353746172223A342E352C22776569676874223A302C22736F7274223A322C22737461747573223A312C2272656D61726B223A224C454B53484F50222C2273656C66537461747573223A312C226C697665537461747573223A302C226164644279223A22222C2275706454696D65223A224D617920392C20323032322031323A30333A323520504D222C227570644279223A22227D7D, '2022-05-09 12:03:25', '超管', '2022-05-09 12:03:25', null);
INSERT INTO `sys_admin_operation_log` VALUES ('84', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A312C22736F7274223A312C2275706454696D65223A224D617920392C20323032322031323A30353A353320504D227D7D, '2022-05-09 12:05:53', '超管', '2022-05-09 12:05:53', null);
INSERT INTO `sys_admin_operation_log` VALUES ('85', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226964223A312C22737461747573223A302C2275706454696D65223A224D617920392C203230323220323A35333A353420504D227D7D, '2022-05-09 14:53:54', '超管', '2022-05-09 14:53:54', null);
INSERT INTO `sys_admin_operation_log` VALUES ('86', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226964223A312C22737461747573223A312C2275706454696D65223A224D617920392C203230323220323A35343A313120504D227D7D, '2022-05-09 14:54:11', '超管', '2022-05-09 14:54:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('87', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226964223A312C22737461747573223A302C2275706454696D65223A224D617920392C203230323220323A35343A313820504D227D7D, '2022-05-09 14:54:19', '超管', '2022-05-09 14:54:19', null);
INSERT INTO `sys_admin_operation_log` VALUES ('88', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226964223A312C22737461747573223A312C2275706454696D65223A224D617920392C203230323220323A35343A323120504D227D7D, '2022-05-09 14:54:21', '超管', '2022-05-09 14:54:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('89', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226E616D65223A22E7B2BEE59381E5BA97E993BA222C226465736372697074696F6E223A22E4BFA1E8AA89E794A8E688B7E59CA8E98080E8B4A7E5908EEFBC8CE680A5E9809FE98080E6ACBEE887B3E8B4A6E688B7222C22737461747573223A312C2275706454696D65223A224D617920392C203230323220323A35353A353020504D227D7D, '2022-05-09 14:55:50', '超管', '2022-05-09 14:55:50', null);
INSERT INTO `sys_admin_operation_log` VALUES ('90', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226964223A352C226E616D65223A22E7B2BEE59381E5BA97E993BA222C226465736372697074696F6E223A22E4BFA1E8AA89E794A8E688B7E59CA8E98080E8B4A7E5908EEFBC8CE680A5E9809FE98080E6ACBEE887B3E8B4A6E688B7222C22737461747573223A312C2261646454696D65223A224D617920392C203230323220323A35373A323520504D222C2275706454696D65223A224D617920392C203230323220323A35373A323520504D227D7D, '2022-05-09 14:57:26', '超管', '2022-05-09 14:57:26', null);
INSERT INTO `sys_admin_operation_log` VALUES ('91', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226964223A352C226E616D65223A22E7B2BEE59381E5BA97E993BA31222C226465736372697074696F6E223A22E4BFA1E8AA89E794A8E688B7E59CA8E98080E8B4A7E5908EEFBC8CE680A5E9809FE98080E6ACBEE887B3E8B4A6E688B7222C22737461747573223A302C226164644279223A22222C2275706454696D65223A224D617920392C203230323220323A35373A333420504D222C227570644279223A22227D7D, '2022-05-09 14:57:34', '超管', '2022-05-09 14:57:34', null);
INSERT INTO `sys_admin_operation_log` VALUES ('92', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226964223A352C226E616D65223A22E7B2BEE59381E5BA97E993BA222C226465736372697074696F6E223A22E4BFA1E8AA89E794A8E688B7E59CA8E98080E8B4A7E5908EEFBC8CE680A5E9809FE98080E6ACBEE887B3E8B4A6E688B7222C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D617920392C203230323220323A35373A333920504D222C227570644279223A22227D7D, '2022-05-09 14:57:39', '超管', '2022-05-09 14:57:39', null);
INSERT INTO `sys_admin_operation_log` VALUES ('93', '29', 'admin', '操作人:admin:超管|编辑服务管理', 0x7B2273686F7053657276696365223A7B226964223A362C226E616D65223A22313233222C226465736372697074696F6E223A22313233222C22737461747573223A312C2261646454696D65223A224D617920392C203230323220323A35373A343220504D222C2275706454696D65223A224D617920392C203230323220323A35373A343220504D227D7D, '2022-05-09 14:57:42', '超管', '2022-05-09 14:57:42', null);
INSERT INTO `sys_admin_operation_log` VALUES ('94', '29', 'admin', '操作人:admin:超管|删除服务管理', 0x7B22696473223A2236227D, '2022-05-09 14:57:45', '超管', '2022-05-09 14:57:45', null);
INSERT INTO `sys_admin_operation_log` VALUES ('95', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A2231222C226C6576656C223A312C2275706454696D65223A224D617920392C203230323220353A35383A303320504D227D7D, '2022-05-09 17:58:03', '超管', '2022-05-09 17:58:03', null);
INSERT INTO `sys_admin_operation_log` VALUES ('96', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A2231222C226C6576656C223A317D7D, '2022-05-09 18:00:06', '超管', '2022-05-09 18:00:06', null);
INSERT INTO `sys_admin_operation_log` VALUES ('97', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A2232222C226C6576656C223A317D7D, '2022-05-09 18:00:19', '超管', '2022-05-09 18:00:19', null);
INSERT INTO `sys_admin_operation_log` VALUES ('98', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A2231227D, '2022-05-09 18:00:37', '超管', '2022-05-09 18:00:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('99', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A2232227D, '2022-05-09 18:00:39', '超管', '2022-05-09 18:00:39', null);
INSERT INTO `sys_admin_operation_log` VALUES ('100', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A2231222C226C6576656C223A317D7D, '2022-05-09 18:08:21', '超管', '2022-05-09 18:08:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('101', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A2232222C226C6576656C223A317D7D, '2022-05-09 18:23:27', '超管', '2022-05-09 18:23:27', null);
INSERT INTO `sys_admin_operation_log` VALUES ('102', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A2233222C226C6576656C223A322C22706964223A347D7D, '2022-05-09 18:24:48', '超管', '2022-05-09 18:24:48', null);
INSERT INTO `sys_admin_operation_log` VALUES ('103', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A2233227D, '2022-05-09 18:50:43', '超管', '2022-05-09 18:50:43', null);
INSERT INTO `sys_admin_operation_log` VALUES ('104', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A2234227D, '2022-05-09 18:56:16', '超管', '2022-05-09 18:56:16', null);
INSERT INTO `sys_admin_operation_log` VALUES ('105', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E8A1A3E69C8D222C226C6576656C223A312C22736F7274223A317D7D, '2022-05-09 19:03:50', '超管', '2022-05-09 19:03:50', null);
INSERT INTO `sys_admin_operation_log` VALUES ('106', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A224E696B65222C226C6576656C223A312C22706964223A362C22736F7274223A317D7D, '2022-05-09 19:04:37', '超管', '2022-05-09 19:04:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('107', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E998BFE8BFAAE8BEBEE696AF222C226C6576656C223A312C22706964223A362C22736F7274223A327D7D, '2022-05-09 19:04:53', '超管', '2022-05-09 19:04:53', null);
INSERT INTO `sys_admin_operation_log` VALUES ('108', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E99E8BE5AD90222C226C6576656C223A312C22706964223A382C22736F7274223A317D7D, '2022-05-09 19:05:06', '超管', '2022-05-09 19:05:06', null);
INSERT INTO `sys_admin_operation_log` VALUES ('109', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E8A3A4E5AD90222C226C6576656C223A312C22736F7274223A327D7D, '2022-05-10 10:07:51', '超管', '2022-05-10 10:07:51', null);
INSERT INTO `sys_admin_operation_log` VALUES ('110', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E696B0E8A3A4E5AD90222C226C6576656C223A312C22706964223A31302C22736F7274223A317D7D, '2022-05-10 10:08:02', '超管', '2022-05-10 10:08:02', null);
INSERT INTO `sys_admin_operation_log` VALUES ('111', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E697A7E8A3A4E5AD90222C226C6576656C223A312C22706964223A31302C22736F7274223A327D7D, '2022-05-10 10:08:11', '超管', '2022-05-10 10:08:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('112', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A4A7E8A3A4E5AD90222C226C6576656C223A312C22706964223A31302C22736F7274223A337D7D, '2022-05-10 10:08:21', '超管', '2022-05-10 10:08:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('113', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5B08FE697A7222C226C6576656C223A312C22706964223A31322C22736F7274223A317D7D, '2022-05-10 10:08:33', '超管', '2022-05-10 10:08:33', null);
INSERT INTO `sys_admin_operation_log` VALUES ('114', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A4A7E697A7222C226C6576656C223A312C22706964223A31322C22736F7274223A327D7D, '2022-05-10 10:08:44', '超管', '2022-05-10 10:08:44', null);
INSERT INTO `sys_admin_operation_log` VALUES ('115', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A223135227D, '2022-05-10 10:34:11', '超管', '2022-05-10 10:34:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('116', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A31342C226E616D65223A22E5B08FE697A7222C226C6576656C223A332C22696D67223A22222C22746172676574223A22222C22706964223A31332C22736F7274223A312C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031302C20323032322031303A33343A313820414D222C227570644279223A22227D7D, '2022-05-10 10:34:19', '超管', '2022-05-10 10:34:19', null);
INSERT INTO `sys_admin_operation_log` VALUES ('117', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A31342C226E616D65223A22E5B08FE697A7222C226C6576656C223A332C22696D67223A22222C22746172676574223A22222C22706964223A31312C22736F7274223A312C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031302C20323032322031303A33363A333020414D222C227570644279223A22227D7D, '2022-05-10 10:36:30', '超管', '2022-05-10 10:36:30', null);
INSERT INTO `sys_admin_operation_log` VALUES ('118', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A31342C22737461747573223A302C2275706454696D65223A224D61792031302C20323032322031303A33373A323120414D227D7D, '2022-05-10 10:37:21', '超管', '2022-05-10 10:37:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('119', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A31342C22736F7274223A322C2275706454696D65223A224D61792031302C20323032322031303A33373A323420414D227D7D, '2022-05-10 10:37:25', '超管', '2022-05-10 10:37:25', null);
INSERT INTO `sys_admin_operation_log` VALUES ('120', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A31342C22736F7274223A312C2275706454696D65223A224D61792031302C20323032322031303A33373A323520414D227D7D, '2022-05-10 10:37:26', '超管', '2022-05-10 10:37:26', null);
INSERT INTO `sys_admin_operation_log` VALUES ('121', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A31342C22737461747573223A312C2275706454696D65223A224D61792031302C20323032322031303A33373A323720414D227D7D, '2022-05-10 10:37:28', '超管', '2022-05-10 10:37:28', null);
INSERT INTO `sys_admin_operation_log` VALUES ('122', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A31342C226E616D65223A22E5B08FE697A7222C226C6576656C223A332C22696D67223A22222C22746172676574223A22222C22706964223A31322C22736F7274223A312C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031302C20323032322031303A33373A333320414D222C227570644279223A22227D7D, '2022-05-10 10:37:33', '超管', '2022-05-10 10:37:33', null);
INSERT INTO `sys_admin_operation_log` VALUES ('123', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A362C226E616D65223A22E8A1A3E69C8D222C226C6576656C223A312C22696D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323135303237363035372E6A7067222C22746172676574223A22222C22706964223A302C22736F7274223A312C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031302C20323032322031303A33373A353720414D222C227570644279223A22227D7D, '2022-05-10 10:37:58', '超管', '2022-05-10 10:37:58', null);
INSERT INTO `sys_admin_operation_log` VALUES ('124', '29', 'admin', '操作人:admin:超管|添加编辑会员组别', 0x7B226D656D62657247726F7570223A7B226964223A362C2274797065223A322C226E616D65223A22E9BB98E8AEA4E58D96E5AEB6222C2264656661756C74537461747573223A312C226C6576656C223A312C22696E6974416D6F756E74223A302C22696E69744D697373696F6E223A302C22736F7274223A312C22737461747573223A312C2275706454696D65223A224D61792031302C203230323220353A30323A303220504D227D7D, '2022-05-10 17:02:03', '超管', '2022-05-10 17:02:03', null);
INSERT INTO `sys_admin_operation_log` VALUES ('125', '29', 'admin', '操作人:admin:超管|提交菜单', 0x5B7B226D656E75223A7B226964223A3437302C22706964223A3134352C226C6576656C223A322C226E616D65223A22E4B883E7899BE4BA91E9858DE7BDAE222C2275726C223A222F74686972642F71696E6975222C22736F7274223A352C2261646454696D65223A224D61792031312C20323032322031303A31353A313020414D222C2275706454696D65223A224D61792031312C20323032322031303A31353A313020414D222C227570644279223A2261646D696E227D7D2C7B227072696E636970616C223A7B227072696E636970616C223A7B2261646D696E223A7B226964223A32392C22747970654964223A312C22747970654E616D65223A22E8B685E7BAA7E7AEA1E79086E59198222C22636F6465223A2261646D696E222C226E616D65223A22E8B685E7AEA1222C2268656164496D67223A2268747470733A2F2F71696E6975696D672E777572756E2E6E65742F313630323538393133393736322E706E67222C2270617373776F7264223A22243261243130246D6C78534C6931574B32373376525938736F6669546541357A2E4B4C6747697679635631725461776E653876346B4B6D6E676D5653222C22697341646D696E223A2231222C22737461747573223A2231222C22617574684B6579223A2235514C574C3442435347434B58434D4C222C2261646454696D65223A224D61792032372C203230313720393A31343A343820504D222C226164644279223A2261646D696E6370222C2275706454696D65223A224D617220362C20323032312031303A32343A333820504D222C227570644279223A2261646D696E227D7D2C22617574686F726974696573223A5B5D2C2264657461696C73223A7B2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31227D2C2261757468656E74696361746564223A747275657D7D5D, '2022-05-11 10:15:10', '超管', '2022-05-11 10:15:10', null);
INSERT INTO `sys_admin_operation_log` VALUES ('126', '29', 'admin', '操作人:admin:超管|添加编辑角色', 0x5B7B226964223A317D2C7B226E616D65223A22E8B685E7BAA7E7AEA1E79086E59198227D2C7B226D656E75496473223A223435362C3435372C3435382C3435392C3436302C39332C3335352C39342C3238312C3233362C3236332C3335302C3236342C3334382C3338342C3338352C3436362C3436372C3436382C3436392C3436312C3436322C3436332C3137322C3235352C3137342C3235362C3232372C3232382C3237342C3331312C3331332C3331352C3331382C3436342C3436352C3334372C3133372C3133382C3134312C3232302C3333322C3134322C3134332C3134342C3333332C3235342C3133322C3133332C3133342C3133352C3237312C3234332C3236322C3231372C3231382C3231392C3232332C3134352C3335312C3134372C3337392C3338372C3339342C3333342C343730227D5D, '2022-05-11 10:15:21', '超管', '2022-05-11 10:15:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('127', '29', 'admin', '操作人:admin:超管|修改会员信息', 0x7B226D656D626572223A7B226964223A312C22737461747573223A2D312C2275706454696D65223A224D61792031312C20323032322031313A32353A353820414D227D7D, '2022-05-11 11:25:59', '超管', '2022-05-11 11:25:59', null);
INSERT INTO `sys_admin_operation_log` VALUES ('128', '29', 'admin', '操作人:admin:超管|修改会员信息', 0x7B226D656D626572223A7B226964223A312C22737461747573223A312C2275706454696D65223A224D61792031312C20323032322031313A32363A303020414D227D7D, '2022-05-11 11:26:00', '超管', '2022-05-11 11:26:00', null);
INSERT INTO `sys_admin_operation_log` VALUES ('129', '29', 'admin', '操作人:admin:超管|审核店铺', 0x5B7B2273686F70496E666F4964223A317D2C7B22737461747573223A327D2C7B22726561736F6E223A22E993B6E8A18CE58DA1E5A1ABE58699E99499E8AFAF227D5D, '2022-05-11 15:08:02', '超管', '2022-05-11 15:08:02', null);
INSERT INTO `sys_admin_operation_log` VALUES ('130', '29', 'admin', '操作人:admin:超管|审核店铺', 0x5B7B2273686F70496E666F4964223A317D2C7B22737461747573223A347D2C7B22726561736F6E223A22E993B6E8A18CE58DA1E5A1ABE58699E99499E8AFAF227D5D, '2022-05-11 15:09:08', '超管', '2022-05-11 15:09:08', null);
INSERT INTO `sys_admin_operation_log` VALUES ('131', '29', 'admin', '操作人:admin:超管|删除店铺', 0x7B22696473223A2231227D, '2022-05-11 15:29:11', '超管', '2022-05-11 15:29:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('132', '29', 'admin', '操作人:admin:超管|审核店铺', 0x5B7B2273686F70496E666F4964223A327D2C7B22737461747573223A347D2C7B22726561736F6E223A22E4B88AE4BCA0E59BBEE78987E8BF9DE8A784227D5D, '2022-05-11 15:39:24', '超管', '2022-05-11 15:39:24', null);
INSERT INTO `sys_admin_operation_log` VALUES ('133', '29', 'admin', '操作人:admin:超管|审核店铺', 0x5B7B2273686F70496E666F4964223A327D2C7B22737461747573223A337D2C7B7D5D, '2022-05-11 15:41:10', '超管', '2022-05-11 15:41:10', null);
INSERT INTO `sys_admin_operation_log` VALUES ('134', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A322C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A332C226C6576656C223A302C226E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C226B6579776F7264223A22E5A4A9E5AEABE5928CE4B880E9AB98222C2270726F64756365223A22E5A4A9E5AEABE5928CE4B880E9AB98222C2273657276696365223A22312C322C332C342C35222C22736572766963654E616D65223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343631303032302E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E5AEABE5928CE4B880E9AB98222C226C696B654E756D223A3939392C22676F6F647353746172223A342E352C2273656C6C657253746172223A342E352C226C6F67697374696353746172223A342E352C22776569676874223A302C22736F7274223A393939392C22737461747573223A312C2272656D61726B223A22E5A4A9E5AEABE5928CE4B880E9AB98222C226164644279223A22222C2275706454696D65223A224D61792031312C203230323220333A34323A353320504D222C227570644279223A22227D7D, '2022-05-11 15:42:53', '超管', '2022-05-11 15:42:53', null);
INSERT INTO `sys_admin_operation_log` VALUES ('135', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A322C2273656C66537461747573223A312C2275706454696D65223A224D61792031312C203230323220333A34333A303320504D227D7D, '2022-05-11 15:43:03', '超管', '2022-05-11 15:43:03', null);
INSERT INTO `sys_admin_operation_log` VALUES ('136', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A322C2273656C66537461747573223A302C2275706454696D65223A224D61792031312C203230323220333A34333A303420504D227D7D, '2022-05-11 15:43:04', '超管', '2022-05-11 15:43:04', null);
INSERT INTO `sys_admin_operation_log` VALUES ('137', '29', 'admin', '操作人:admin:超管|审核店铺', 0x5B7B2273686F70496E666F4964223A337D2C7B22737461747573223A337D2C7B7D5D, '2022-05-11 15:48:52', '超管', '2022-05-11 15:48:52', null);
INSERT INTO `sys_admin_operation_log` VALUES ('138', '29', 'admin', '操作人:admin:超管|上传素材', 0x5B7B22736F7572636573223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235363832343839362E706E677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235363832343839362E706E67227D2C7B22636174654964223A317D2C7B2274797065223A2231227D5D, '2022-05-11 16:13:46', '超管', '2022-05-11 16:13:46', null);
INSERT INTO `sys_admin_operation_log` VALUES ('139', '29', 'admin', '操作人:admin:超管|删除素材', 0x7B22696473223A22323639227D, '2022-05-11 16:13:51', '超管', '2022-05-11 16:13:51', null);
INSERT INTO `sys_admin_operation_log` VALUES ('140', '29', 'admin', '操作人:admin:超管|上传素材', 0x5B7B22736F7572636573223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235363833343636322E706E67227D2C7B22636174654964223A317D2C7B2274797065223A2231227D5D, '2022-05-11 16:13:56', '超管', '2022-05-11 16:13:56', null);
INSERT INTO `sys_admin_operation_log` VALUES ('141', '29', 'admin', '操作人:admin:超管|修改系统参数', 0x7B22636F6E666967466F726D223A7B226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635313839353338323330342E706E67222C2264656661756C745F686561645F696D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235363833343636322E706E67222C227765625F6E616D65223A224C454B53484F50222C22616E64726F69645F6170705F646F776E6C6F61645F75726C223A2268747470733A2F2F7777612E6C616E7A6F75692E636F6D2F6A696B65222C22696F735F6170705F646F776E6C6F61645F75726C223A226E756C6C222C22616E64726F69645F76657273696F6E223A223130303030222C22696F735F76657273696F6E223A223230303031222C22616E64726F69645F7570646174655F737461747573223A2231222C22696F735F7570646174655F737461747573223A2231222C226B6566755F74656C223A22313131313131222C226B6566755F7171223A223131313131222C226B6566755F7778223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633333030353937393733372E6A7067222C2266735F6B6566755F74656C223A2231313132323333222C2266735F6B6566755F7171223A223131313131222C2266735F6B6566755F7778223A22666379666C6F6174222C226963705F6E6F223A22E997BD494350E5A4873230303034383737E58FB72D31222C227765625F75726C223A22687474703A2F2F6C6574616F73686F702E6461797573686F702E746F70222C2268355F75726C223A22687474703A2F2F6C6574616F68352E6461797573686F702E746F70222C227370726561645F636F6E74656E74223A22E5889AE58F91E78EB0E4B8AAE5819AE4BBBBE58AA1E8B59AE99BB6E992B1E5B9B3E58FB02C20E4BBBBE4BD95E4BABAE983BDE58FAFE4BBA5E5819A2C20E997B2E697B6E8B59AE782B9E7949FE6B4BBE8B4B9222C2263616E65725F737461747573223A2231222C22706F7274616C5F617069223A22687474703A2F2F706F7274616C6170692E6A6B62642E766970222C2277656968755F6E6F5F6C6F67696E223A2230222C2277656968755F6E6F5F74616B655F7461736B223A2230222C226B6566755F75726C223A22687474703A2F2F696D2E6368797578696E2E636E2F696E6465782F696E6465782F686F6D653F766973697465725F69645C75303033647B7569647D5C7530303236766973697465725F6E616D655C75303033647B757365726E616D657D5C75303032366176617461725C75303033647B68656164496D677D5C7530303236627573696E6573735F69645C7530303364325C753030323667726F757069645C7530303364315C75303032367370656369616C5C7530303364335C74222C226B6566755F717132223A22313131222C2273686F705F6261636B67726F756E64223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F62366639363230392D353233642D343739372D623530392D37636566306333662E6A7067222C227265675F66696E6973685F7475726E223A2232222C226170705F6C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313633393130343032363034302E706E67222C226170705F6E616D65223A22E69E81E5AEA2E6BC94E7A4BA227D7D, '2022-05-11 16:14:04', '超管', '2022-05-11 16:14:04', null);
INSERT INTO `sys_admin_operation_log` VALUES ('142', '29', 'admin', '操作人:admin:超管|审核店铺', 0x5B7B2273686F70496E666F4964223A347D2C7B22737461747573223A347D2C7B22726561736F6E223A22E4B88AE4BCA0E59BBEE78987E4B88DE6B885E699B0227D5D, '2022-05-12 10:30:06', '超管', '2022-05-12 10:30:06', null);
INSERT INTO `sys_admin_operation_log` VALUES ('143', '29', 'admin', '操作人:admin:超管|审核店铺', 0x5B7B2273686F70496E666F4964223A347D2C7B22737461747573223A337D2C7B7D5D, '2022-05-12 10:32:01', '超管', '2022-05-12 10:32:01', null);
INSERT INTO `sys_admin_operation_log` VALUES ('144', '29', 'admin', '操作人:admin:超管|编辑店铺', 0x7B2273686F70223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C226C6576656C223A302C226E616D65223A224C454B53484F50E69797E888B0E5BA97222C226B6579776F7264223A2232222C2270726F64756365223A223232222C2273657276696365223A22222C22736572766963654E616D65223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C226C696B654E756D223A302C22676F6F647353746172223A302C2273656C6C657253746172223A302C226C6F67697374696353746172223A302C22776569676874223A302C22736F7274223A393939392C22737461747573223A312C2272656D61726B223A2232222C2273656C66537461747573223A302C226C697665537461747573223A302C226164644279223A22222C2275706454696D65223A224D61792031322C20323032322031303A33323A323820414D222C227570644279223A22227D7D, '2022-05-12 10:32:29', '超管', '2022-05-12 10:32:29', null);
INSERT INTO `sys_admin_operation_log` VALUES ('145', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A2239227D, '2022-05-14 18:04:36', '超管', '2022-05-14 18:04:36', null);
INSERT INTO `sys_admin_operation_log` VALUES ('146', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A2238227D, '2022-05-14 18:04:39', '超管', '2022-05-14 18:04:39', null);
INSERT INTO `sys_admin_operation_log` VALUES ('147', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A223131227D, '2022-05-14 18:04:43', '超管', '2022-05-14 18:04:43', null);
INSERT INTO `sys_admin_operation_log` VALUES ('148', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A223133227D, '2022-05-14 18:04:46', '超管', '2022-05-14 18:04:46', null);
INSERT INTO `sys_admin_operation_log` VALUES ('149', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A223134227D, '2022-05-14 18:04:48', '超管', '2022-05-14 18:04:48', null);
INSERT INTO `sys_admin_operation_log` VALUES ('150', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A223132227D, '2022-05-14 18:04:50', '超管', '2022-05-14 18:04:50', null);
INSERT INTO `sys_admin_operation_log` VALUES ('151', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A223130227D, '2022-05-14 18:04:52', '超管', '2022-05-14 18:04:52', null);
INSERT INTO `sys_admin_operation_log` VALUES ('152', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A2237227D, '2022-05-14 18:04:53', '超管', '2022-05-14 18:04:53', null);
INSERT INTO `sys_admin_operation_log` VALUES ('153', '29', 'admin', '操作人:admin:超管|删除电商分类', 0x7B22696473223A2236227D, '2022-05-14 18:04:55', '超管', '2022-05-14 18:04:55', null);
INSERT INTO `sys_admin_operation_log` VALUES ('154', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A5B3E8A385222C22736F7274223A317D7D, '2022-05-14 18:05:20', '超管', '2022-05-14 18:05:20', null);
INSERT INTO `sys_admin_operation_log` VALUES ('155', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6AF8DE5A9B4222C22736F7274223A327D7D, '2022-05-14 18:05:27', '超管', '2022-05-14 18:05:27', null);
INSERT INTO `sys_admin_operation_log` VALUES ('156', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6898BE69CBAE695B0E7A081222C22736F7274223A337D7D, '2022-05-14 18:05:36', '超管', '2022-05-14 18:05:36', null);
INSERT INTO `sys_admin_operation_log` VALUES ('157', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E794B7E8A385222C22736F7274223A347D7D, '2022-05-14 18:05:44', '超管', '2022-05-14 18:05:44', null);
INSERT INTO `sys_admin_operation_log` VALUES ('158', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E99E8BE99DB4222C22736F7274223A357D7D, '2022-05-14 18:05:51', '超管', '2022-05-14 18:05:51', null);
INSERT INTO `sys_admin_operation_log` VALUES ('159', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7949FE9B29C222C22736F7274223A367D7D, '2022-05-14 18:06:00', '超管', '2022-05-14 18:06:00', null);
INSERT INTO `sys_admin_operation_log` VALUES ('160', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7BE8EE5A686E6B497E68AA4222C22736F7274223A377D7D, '2022-05-14 18:06:14', '超管', '2022-05-14 18:06:14', null);
INSERT INTO `sys_admin_operation_log` VALUES ('161', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E799BEE8B4A7222C22736F7274223A387D7D, '2022-05-14 18:06:22', '超管', '2022-05-14 18:06:22', null);
INSERT INTO `sys_admin_operation_log` VALUES ('162', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5AEB6E794B5222C22736F7274223A397D7D, '2022-05-14 18:06:30', '超管', '2022-05-14 18:06:30', null);
INSERT INTO `sys_admin_operation_log` VALUES ('163', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E9A5B0E59381222C22736F7274223A31307D7D, '2022-05-14 18:06:40', '超管', '2022-05-14 18:06:40', null);
INSERT INTO `sys_admin_operation_log` VALUES ('164', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7AEB1E58C85222C22736F7274223A31317D7D, '2022-05-14 18:06:50', '超管', '2022-05-14 18:06:50', null);
INSERT INTO `sys_admin_operation_log` VALUES ('165', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A5B3E8A3A4222C22706964223A31362C22736F7274223A317D7D, '2022-05-14 18:07:29', '超管', '2022-05-14 18:07:29', null);
INSERT INTO `sys_admin_operation_log` VALUES ('166', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E4B88AE8A385222C22706964223A31362C22736F7274223A327D7D, '2022-05-14 18:07:38', '超管', '2022-05-14 18:07:38', null);
INSERT INTO `sys_admin_operation_log` VALUES ('167', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A5B3E8A399222C22706964223A31362C22736F7274223A337D7D, '2022-05-14 18:07:47', '超管', '2022-05-14 18:07:47', null);
INSERT INTO `sys_admin_operation_log` VALUES ('168', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A597E8A385222C22706964223A31362C22736F7274223A347D7D, '2022-05-14 18:07:59', '超管', '2022-05-14 18:07:59', null);
INSERT INTO `sys_admin_operation_log` VALUES ('169', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A496E5A597222C22706964223A31362C22736F7274223A357D7D, '2022-05-14 18:08:08', '超管', '2022-05-14 18:08:08', null);
INSERT INTO `sys_admin_operation_log` VALUES ('170', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E584BFE7ABA5E78EA9E585B7222C22706964223A31372C22736F7274223A317D7D, '2022-05-14 18:08:27', '超管', '2022-05-14 18:08:27', null);
INSERT INTO `sys_admin_operation_log` VALUES ('171', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7ABA5E8A385222C22706964223A31372C22736F7274223A327D7D, '2022-05-14 18:08:37', '超管', '2022-05-14 18:08:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('172', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7ABA5E99E8B222C22706964223A31372C22736F7274223A337D7D, '2022-05-14 18:08:45', '超管', '2022-05-14 18:08:45', null);
INSERT INTO `sys_admin_operation_log` VALUES ('173', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6B497E68AA4E794A8E59381222C22706964223A31372C22736F7274223A347D7D, '2022-05-14 18:08:57', '超管', '2022-05-14 18:08:57', null);
INSERT INTO `sys_admin_operation_log` VALUES ('174', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5AD95E4BAA7E7A9BFE690AD222C22706964223A31372C22736F7274223A357D7D, '2022-05-14 18:09:14', '超管', '2022-05-14 18:09:14', null);
INSERT INTO `sys_admin_operation_log` VALUES ('175', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E593BAE4B9B3E59682E585BB222C22706964223A31372C22736F7274223A367D7D, '2022-05-14 18:09:29', '超管', '2022-05-14 18:09:29', null);
INSERT INTO `sys_admin_operation_log` VALUES ('176', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E584BFE7ABA5E5AEB6E585B7222C22706964223A31372C22736F7274223A377D7D, '2022-05-14 18:09:48', '超管', '2022-05-14 18:09:48', null);
INSERT INTO `sys_admin_operation_log` VALUES ('177', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A9B4E584BFE8A385222C22706964223A31372C22736F7274223A387D7D, '2022-05-14 18:10:00', '超管', '2022-05-14 18:10:00', null);
INSERT INTO `sys_admin_operation_log` VALUES ('178', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E78EA9E585B7E8BDA6222C22706964223A31372C22736F7274223A397D7D, '2022-05-14 18:10:10', '超管', '2022-05-14 18:10:10', null);
INSERT INTO `sys_admin_operation_log` VALUES ('179', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5AE9DE5AE9DE587BAE8A18C222C22706964223A31372C22736F7274223A31307D7D, '2022-05-14 18:10:21', '超管', '2022-05-14 18:10:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('180', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5AD95E4BAA7E794A8E59381222C22706964223A31372C22736F7274223A31317D7D, '2022-05-14 18:10:34', '超管', '2022-05-14 18:10:34', null);
INSERT INTO `sys_admin_operation_log` VALUES ('181', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A5B6E7B289E890A5E585BB222C22706964223A31372C22736F7274223A31327D7D, '2022-05-14 18:10:52', '超管', '2022-05-14 18:10:52', null);
INSERT INTO `sys_admin_operation_log` VALUES ('182', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6898BE69CBA222C22706964223A31382C22736F7274223A317D7D, '2022-05-14 18:11:02', '超管', '2022-05-14 18:11:02', null);
INSERT INTO `sys_admin_operation_log` VALUES ('183', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A223343E9858DE4BBB6222C22706964223A31382C22736F7274223A327D7D, '2022-05-14 18:11:12', '超管', '2022-05-14 18:11:12', null);
INSERT INTO `sys_admin_operation_log` VALUES ('184', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7BBB4E4BFAE222C22706964223A31382C22736F7274223A347D7D, '2022-05-14 18:11:24', '超管', '2022-05-14 18:11:24', null);
INSERT INTO `sys_admin_operation_log` VALUES ('185', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E8A3A4E5AD90222C22706964223A31392C22736F7274223A317D7D, '2022-05-14 18:12:23', '超管', '2022-05-14 18:12:23', null);
INSERT INTO `sys_admin_operation_log` VALUES ('186', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E4B88AE8A385222C22706964223A31392C22736F7274223A327D7D, '2022-05-14 18:12:32', '超管', '2022-05-14 18:12:32', null);
INSERT INTO `sys_admin_operation_log` VALUES ('187', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A5B3E99E8B222C22706964223A32302C22736F7274223A317D7D, '2022-05-14 18:12:44', '超管', '2022-05-14 18:12:44', null);
INSERT INTO `sys_admin_operation_log` VALUES ('188', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E794B7E99E8B222C22706964223A32302C22736F7274223A327D7D, '2022-05-14 18:12:48', '超管', '2022-05-14 18:12:48', null);
INSERT INTO `sys_admin_operation_log` VALUES ('189', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7BE8EE591B3E894ACE88F9C222C22706964223A32312C22736F7274223A317D7D, '2022-05-14 18:13:05', '超管', '2022-05-14 18:13:05', null);
INSERT INTO `sys_admin_operation_log` VALUES ('190', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7A6BDE89B8B222C22706964223A32312C22736F7274223A327D7D, '2022-05-14 18:13:11', '超管', '2022-05-14 18:13:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('191', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E697B6E4BBA4E7949FE9B29C222C22706964223A32312C22736F7274223A337D7D, '2022-05-14 18:13:18', '超管', '2022-05-14 18:13:18', null);
INSERT INTO `sys_admin_operation_log` VALUES ('192', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E696B0E9B29CE88289E7B1BB222C22706964223A32312C22736F7274223A347D7D, '2022-05-14 18:13:27', '超管', '2022-05-14 18:13:27', null);
INSERT INTO `sys_admin_operation_log` VALUES ('193', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5BDA9E5A686222C22706964223A32322C22736F7274223A317D7D, '2022-05-14 18:13:36', '超管', '2022-05-14 18:13:36', null);
INSERT INTO `sys_admin_operation_log` VALUES ('194', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7BE8EE5AEB9E7BE8EE4BD93222C22706964223A32322C22736F7274223A327D7D, '2022-05-14 18:13:43', '超管', '2022-05-14 18:13:43', null);
INSERT INTO `sys_admin_operation_log` VALUES ('195', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E68AA4E882A4222C22706964223A32322C22736F7274223A337D7D, '2022-05-14 18:13:47', '超管', '2022-05-14 18:13:47', null);
INSERT INTO `sys_admin_operation_log` VALUES ('196', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5B185E5AEB6E697A5E794A8222C22706964223A32322C22736F7274223A317D7D, '2022-05-14 18:13:56', '超管', '2022-05-14 18:13:56', null);
INSERT INTO `sys_admin_operation_log` VALUES ('197', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E9A490E9A5AEE794A8E585B7222C22706964223A32322C22736F7274223A327D7D, '2022-05-14 18:14:04', '超管', '2022-05-14 18:14:04', null);
INSERT INTO `sys_admin_operation_log` VALUES ('198', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7BBBFE6A48DE5AEA0E789A9222C22706964223A32322C22736F7274223A337D7D, '2022-05-14 18:14:11', '超管', '2022-05-14 18:14:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('199', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7AA97E5B898E5B883E889BA222C22706964223A32322C22736F7274223A347D7D, '2022-05-14 18:14:19', '超管', '2022-05-14 18:14:19', null);
INSERT INTO `sys_admin_operation_log` VALUES ('200', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E58EA8E688BFE794A8E59381222C22706964223A32322C22736F7274223A357D7D, '2022-05-14 18:14:25', '超管', '2022-05-14 18:14:25', null);
INSERT INTO `sys_admin_operation_log` VALUES ('201', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5BA8AE4B88AE794A8E59381222C22706964223A32332C22736F7274223A367D7D, '2022-05-14 18:14:45', '超管', '2022-05-14 18:14:45', null);
INSERT INTO `sys_admin_operation_log` VALUES ('202', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5AEB6E585B7E8A385E9A5B0222C22706964223A32332C22736F7274223A377D7D, '2022-05-14 18:14:51', '超管', '2022-05-14 18:14:51', null);
INSERT INTO `sys_admin_operation_log` VALUES ('203', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E694B6E7BAB3E695B4E79086222C22706964223A32332C22736F7274223A387D7D, '2022-05-14 18:14:57', '超管', '2022-05-14 18:14:57', null);
INSERT INTO `sys_admin_operation_log` VALUES ('204', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6B885E6B481E5B7A5E585B7222C22706964223A32332C22736F7274223A397D7D, '2022-05-14 18:15:07', '超管', '2022-05-14 18:15:07', null);
INSERT INTO `sys_admin_operation_log` VALUES ('205', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A36302C226E616D65223A22E7BBBFE6A48DE5AEA0E789A9222C226C6576656C223A322C22696D67223A22222C22746172676574223A22222C22706964223A32332C22736F7274223A332C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031342C203230323220363A31353A323120504D222C227570644279223A22227D7D, '2022-05-14 18:15:21', '超管', '2022-05-14 18:15:21', null);
INSERT INTO `sys_admin_operation_log` VALUES ('206', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A36312C226E616D65223A22E7AA97E5B898E5B883E889BA222C226C6576656C223A322C22696D67223A22222C22746172676574223A22222C22706964223A32332C22736F7274223A342C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031342C203230323220363A31353A323820504D222C227570644279223A22227D7D, '2022-05-14 18:15:29', '超管', '2022-05-14 18:15:29', null);
INSERT INTO `sys_admin_operation_log` VALUES ('207', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A36322C226E616D65223A22E58EA8E688BFE794A8E59381222C226C6576656C223A322C22696D67223A22222C22746172676574223A22222C22706964223A32332C22736F7274223A352C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031342C203230323220363A31353A333420504D222C227570644279223A22227D7D, '2022-05-14 18:15:34', '超管', '2022-05-14 18:15:34', null);
INSERT INTO `sys_admin_operation_log` VALUES ('208', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A35382C226E616D65223A22E5B185E5AEB6E697A5E794A8222C226C6576656C223A322C22696D67223A22222C22746172676574223A22222C22706964223A32332C22736F7274223A312C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031342C203230323220363A31353A353320504D222C227570644279223A22227D7D, '2022-05-14 18:15:54', '超管', '2022-05-14 18:15:54', null);
INSERT INTO `sys_admin_operation_log` VALUES ('209', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226964223A35392C226E616D65223A22E9A490E9A5AEE794A8E585B7222C226C6576656C223A322C22696D67223A22222C22746172676574223A22222C22706964223A32332C22736F7274223A322C22737461747573223A312C226164644279223A22222C2275706454696D65223A224D61792031342C203230323220363A31363A303120504D222C227570644279223A22227D7D, '2022-05-14 18:16:01', '超管', '2022-05-14 18:16:01', null);
INSERT INTO `sys_admin_operation_log` VALUES ('210', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E58EA8E688BFE794B5E599A8222C22706964223A32342C22736F7274223A317D7D, '2022-05-14 18:16:18', '超管', '2022-05-14 18:16:18', null);
INSERT INTO `sys_admin_operation_log` VALUES ('211', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7949FE6B4BBE794B5E599A8222C22706964223A32342C22736F7274223A327D7D, '2022-05-14 18:16:24', '超管', '2022-05-14 18:16:24', null);
INSERT INTO `sys_admin_operation_log` VALUES ('212', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E4B8AAE68AA4E794B5E599A8222C22706964223A32342C22736F7274223A337D7D, '2022-05-14 18:16:32', '超管', '2022-05-14 18:16:32', null);
INSERT INTO `sys_admin_operation_log` VALUES ('213', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A4A7E5AEB6E794B5222C22706964223A32342C22736F7274223A347D7D, '2022-05-14 18:16:37', '超管', '2022-05-14 18:16:37', null);
INSERT INTO `sys_admin_operation_log` VALUES ('214', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6898BE8A1A8222C22706964223A32352C22736F7274223A317D7D, '2022-05-14 18:16:47', '超管', '2022-05-14 18:16:47', null);
INSERT INTO `sys_admin_operation_log` VALUES ('215', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E79CBCE9959C222C22706964223A32352C22736F7274223A327D7D, '2022-05-14 18:16:54', '超管', '2022-05-14 18:16:54', null);
INSERT INTO `sys_admin_operation_log` VALUES ('216', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E78FA0E5AE9D222C22706964223A32352C22736F7274223A337D7D, '2022-05-14 18:17:00', '超管', '2022-05-14 18:17:00', null);
INSERT INTO `sys_admin_operation_log` VALUES ('217', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E5A5B3E5A3ABE8838CE58C85222C22706964223A32362C22736F7274223A317D7D, '2022-05-14 18:17:11', '超管', '2022-05-14 18:17:11', null);
INSERT INTO `sys_admin_operation_log` VALUES ('218', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E794B7E5A3ABE8838CE58C85222C22706964223A32362C22736F7274223A327D7D, '2022-05-14 18:17:18', '超管', '2022-05-14 18:17:18', null);
INSERT INTO `sys_admin_operation_log` VALUES ('219', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7AEB1E58C85222C22706964223A32362C22736F7274223A337D7D, '2022-05-14 18:17:23', '超管', '2022-05-14 18:17:23', null);
INSERT INTO `sys_admin_operation_log` VALUES ('220', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E4BC91E997B2E8A3A4222C22706964223A32372C22736F7274223A317D7D, '2022-05-14 18:17:41', '超管', '2022-05-14 18:17:41', null);
INSERT INTO `sys_admin_operation_log` VALUES ('221', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7899BE4BB94E8A3A4222C22706964223A32372C22736F7274223A327D7D, '2022-05-14 18:17:48', '超管', '2022-05-14 18:17:48', null);
INSERT INTO `sys_admin_operation_log` VALUES ('222', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E68993E5BA95E8A3A4222C22706964223A32372C22736F7274223A337D7D, '2022-05-14 18:17:54', '超管', '2022-05-14 18:17:54', null);
INSERT INTO `sys_admin_operation_log` VALUES ('223', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E8A5BFE8A385E8A3A4222C22706964223A32372C22736F7274223A347D7D, '2022-05-14 18:18:01', '超管', '2022-05-14 18:18:01', null);
INSERT INTO `sys_admin_operation_log` VALUES ('224', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A2254E681A4222C22706964223A32382C22736F7274223A317D7D, '2022-05-14 18:18:16', '超管', '2022-05-14 18:18:16', null);
INSERT INTO `sys_admin_operation_log` VALUES ('225', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E8A1ACE8A1AB222C22706964223A32382C22736F7274223A327D7D, '2022-05-14 18:18:22', '超管', '2022-05-14 18:18:22', null);
INSERT INTO `sys_admin_operation_log` VALUES ('226', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E99BAAE7BABAE8A1AB222C22706964223A32382C22736F7274223A337D7D, '2022-05-14 18:18:29', '超管', '2022-05-14 18:18:29', null);
INSERT INTO `sys_admin_operation_log` VALUES ('227', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E58DABE8A1A3222C22706964223A32382C22736F7274223A347D7D, '2022-05-14 18:18:38', '超管', '2022-05-14 18:18:38', null);
INSERT INTO `sys_admin_operation_log` VALUES ('228', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6AF9BE8A1A3222C22706964223A32382C22736F7274223A357D7D, '2022-05-14 18:18:45', '超管', '2022-05-14 18:18:45', null);
INSERT INTO `sys_admin_operation_log` VALUES ('229', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E9A9ACE794B2222C22706964223A32382C22736F7274223A367D7D, '2022-05-14 18:18:51', '超管', '2022-05-14 18:18:51', null);
INSERT INTO `sys_admin_operation_log` VALUES ('230', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E8BF9EE8A1A3E8A399222C22706964223A32392C22736F7274223A317D7D, '2022-05-14 18:19:02', '超管', '2022-05-14 18:19:02', null);
INSERT INTO `sys_admin_operation_log` VALUES ('231', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E58D8AE8BAABE8A399222C22706964223A32392C22736F7274223A327D7D, '2022-05-14 18:19:08', '超管', '2022-05-14 18:19:08', null);
INSERT INTO `sys_admin_operation_log` VALUES ('232', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E69797E8A28D222C22706964223A32392C22736F7274223A337D7D, '2022-05-14 18:19:14', '超管', '2022-05-14 18:19:14', null);
INSERT INTO `sys_admin_operation_log` VALUES ('233', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E697B6E5B09AE5A597E8A385222C22706964223A33302C22736F7274223A317D7D, '2022-05-14 18:19:23', '超管', '2022-05-14 18:19:23', null);
INSERT INTO `sys_admin_operation_log` VALUES ('234', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E8BF90E58AA8E5A597E8A385222C22706964223A33302C22736F7274223A327D7D, '2022-05-14 18:19:31', '超管', '2022-05-14 18:19:31', null);
INSERT INTO `sys_admin_operation_log` VALUES ('235', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E79FADE5A496E5A597222C22706964223A33312C22736F7274223A317D7D, '2022-05-14 18:19:44', '超管', '2022-05-14 18:19:44', null);
INSERT INTO `sys_admin_operation_log` VALUES ('236', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E8A5BFE8A385222C22706964223A33312C22736F7274223A327D7D, '2022-05-14 18:19:49', '超管', '2022-05-14 18:19:49', null);
INSERT INTO `sys_admin_operation_log` VALUES ('237', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E9A38EE8A1A3222C22706964223A33312C22736F7274223A337D7D, '2022-05-14 18:19:54', '超管', '2022-05-14 18:19:54', null);
INSERT INTO `sys_admin_operation_log` VALUES ('238', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E7BEBDE7BB92E69C8D222C22706964223A33312C22736F7274223A347D7D, '2022-05-14 18:20:02', '超管', '2022-05-14 18:20:02', null);
INSERT INTO `sys_admin_operation_log` VALUES ('239', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6AF9BE591A2E5A4A7E8A1A3222C22706964223A33312C22736F7274223A357D7D, '2022-05-14 18:20:10', '超管', '2022-05-14 18:20:10', null);
INSERT INTO `sys_admin_operation_log` VALUES ('240', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E6A389E8A1A3E6A389E69C8D222C22706964223A33312C22736F7274223A367D7D, '2022-05-14 18:20:17', '超管', '2022-05-14 18:20:17', null);
INSERT INTO `sys_admin_operation_log` VALUES ('241', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E79AAEE88D89222C22706964223A33312C22736F7274223A377D7D, '2022-05-14 18:20:25', '超管', '2022-05-14 18:20:25', null);
INSERT INTO `sys_admin_operation_log` VALUES ('242', '29', 'admin', '操作人:admin:超管|编辑电商分类', 0x7B2263617465223A7B226E616D65223A22E79AAEE8A1A3222C22706964223A33312C22736F7274223A387D7D, '2022-05-14 18:20:30', '超管', '2022-05-14 18:20:30', null);

-- ----------------------------
-- Table structure for sys_admin_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_type`;
CREATE TABLE `sys_admin_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '管理员类型名称',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态 -1锁定 1正常',
  `menus` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '菜单权限',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='管理员类型表';

-- ----------------------------
-- Records of sys_admin_type
-- ----------------------------
INSERT INTO `sys_admin_type` VALUES ('1', '超级管理员', '1', 0x3435362C3435372C3435382C3435392C3436302C39332C3335352C39342C3238312C3233362C3236332C3335302C3236342C3334382C3338342C3338352C3436362C3436372C3436382C3436392C3436312C3436322C3436332C3137322C3235352C3137342C3235362C3232372C3232382C3237342C3331312C3331332C3331352C3331382C3436342C3436352C3334372C3133372C3133382C3134312C3232302C3333322C3134322C3134332C3134342C3333332C3235342C3133322C3133332C3133342C3133352C3237312C3234332C3236322C3231372C3231382C3231392C3232332C3134352C3335312C3134372C3337392C3338372C3339342C3333342C343730, '2017-03-10 19:14:30', 'system', '2022-05-11 10:15:21', 'admin');
INSERT INTO `sys_admin_type` VALUES ('2', '管理', '1', 0x3131302C3131312C313135, '2021-04-21 00:04:41', null, '2021-09-13 20:28:45', null);

-- ----------------------------
-- Table structure for sys_alisms_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_alisms_config`;
CREATE TABLE `sys_alisms_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账户',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `send_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求链接',
  `server_port` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求端口',
  `sign_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用ID',
  `register_template_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册模板ID',
  `bind_template_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '绑定模板ID',
  `forget_template_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '忘记密码模板ID',
  `common_template_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '通用模板ID',
  `password_template_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '员工密码通知模板',
  `delivery_template_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配送通知',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `sms_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短信类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='短信配置表';

-- ----------------------------
-- Records of sys_alisms_config
-- ----------------------------
INSERT INTO `sys_alisms_config` VALUES ('1', 'LTAI4FiqTo2aK1WG3aGnxZko', 'tyTc5xsbTSQJV7uXIrVVwPcRMRQp6l', 'dysmsapi.aliyuncs.com', '80', 'LEKSHOP', 'SMS_215405324', 'SMS_215405324', 'SMS_215405324', 'SMS_215405324', 'SMS_215405324', 'SMS_205055010', '2019-06-28 11:27:11', 'admin', '2022-05-07 15:35:59', 'admin', '');

-- ----------------------------
-- Table structure for sys_approve_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_approve_config`;
CREATE TABLE `sys_approve_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商户ID',
  `secret` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密钥',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `api_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='身份证银行卡认证配置表';

-- ----------------------------
-- Records of sys_approve_config
-- ----------------------------
INSERT INTO `sys_approve_config` VALUES ('3', '595836', '190bdb2429d34c378bced5c612329a65', '2021-01-29 12:02:12', '', '2022-02-14 23:57:11', '', 'http://route.showapi.com');

-- ----------------------------
-- Table structure for sys_bank_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_bank_config`;
CREATE TABLE `sys_bank_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `bank` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行标识',
  `bank_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行名称',
  `bank_zhihang` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支行',
  `bank_no` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行联号',
  `sort` int DEFAULT '0' COMMENT '显示顺序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='银行配置表';

-- ----------------------------
-- Records of sys_bank_config
-- ----------------------------
INSERT INTO `sys_bank_config` VALUES ('129', 'ICBC', '中国工商银行', '', '102100099996', '0', '2018-12-11 14:53:58', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('130', 'ABC', '中国农业银行', '', '103100000026', '1', '2018-12-11 14:54:01', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('131', 'BOC', '中国银行', '', '104100000004', '2', '2018-12-11 14:54:04', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('132', 'CCB', '中国建设银行', '', '105100000017', '3', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('133', 'CIB', '兴业银行', '', '309391000011', '4', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('134', 'SPDB', '浦东发展银行', '', '310290000013', '5', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('135', 'BCM', '交通银行', '', '301290000007', '6', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('136', 'ECITIC', '中信银行', '', '302100011000', '7', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('137', 'CEB', '中国光大银行', '', '303100000006', '8', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('138', 'CMB', '招商银行', '', '308584000013', '9', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('139', 'GDB', '广发银行', '', '306581000003', '10', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('140', 'PSBC', '中国邮政储蓄银行', '', '403100000004', '11', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('141', 'PAB', '平安银行', '', '307584007998', '12', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('142', 'CMSB', '民生银行', '', '00000', '13', '2018-12-11 14:54:07', '', '2018-12-11 14:54:07', '');
INSERT INTO `sys_bank_config` VALUES ('143', 'RCC', '农村信用社', '', '', '14', '2020-08-18 13:56:33', '', '2020-08-18 13:56:35', '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '1' COMMENT '类型 0不显示 1显示',
  `cate` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '1' COMMENT '分类 1系统参数类 2会员提成',
  `nid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数标识',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数名称',
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数值',
  `image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'NVL' COMMENT '图片',
  `sort` int DEFAULT '0' COMMENT '显示顺序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uqx_config` (`nid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统参数表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('139', '1', '1', 'logo', '后台LOGO', 'https://qiniu.chengyuwb.com/1651895382304.png', 'https://qiniu.miaotu2018.com/1563181444028.png?e=1563185044&token=61jvnw0pbsDbY4ZJQDWJQyP2JLOZ_RMwpDqfcJag:av7AXYB5xq6A1L6vNlFrpRPg9J0=', '2', '2019-03-05 14:28:29', 'admin', '2019-07-15 17:04:05', 'admin');
INSERT INTO `sys_config` VALUES ('145', '1', '1', 'default_head_img', '默认头像', 'https://qiniu.chengyuwb.com/1652256834662.png', 'http://qiniu2.chengyuwb.com/1561130653544.jpg', '1', '2019-03-20 14:52:59', 'admin', '2019-06-21 23:24:15', 'admin');
INSERT INTO `sys_config` VALUES ('151', '1', '1', 'web_name', '网站名称', 'LEKSHOP', 'NVL', '3', '2019-04-13 17:40:14', 'admin', '2019-07-09 14:37:58', 'admin');
INSERT INTO `sys_config` VALUES ('183', '1', '1', 'android_app_download_url', '安卓APP下载连接', 'https://wwa.lanzoui.com/jike', 'NVL', '17', '2019-07-12 16:01:31', 'admin', '2019-10-25 11:36:37', 'admin');
INSERT INTO `sys_config` VALUES ('184', '1', '1', 'ios_app_download_url', '苹果APP下载连接', 'null', 'NVL', '18', '2019-07-12 16:01:59', 'admin', '2019-09-26 11:22:01', 'admin');
INSERT INTO `sys_config` VALUES ('185', '1', '1', 'android_version', '安卓版本', '10000', 'NVL', '19', '2019-09-09 14:46:34', 'admin', '2019-11-26 18:09:08', 'admin');
INSERT INTO `sys_config` VALUES ('186', '1', '1', 'ios_version', '苹果版本', '20001', 'NVL', '20', '2019-09-09 14:47:04', 'admin', '2019-10-22 11:18:32', 'admin');
INSERT INTO `sys_config` VALUES ('187', '1', '1', 'android_update_status', '安卓是否强制更新(0:关 1开)', '1', 'NVL', '21', '2019-09-16 20:09:55', 'admin', '2019-09-24 17:51:57', 'admin');
INSERT INTO `sys_config` VALUES ('188', '1', '1', 'ios_update_status', 'IOS是否强制更新(0:关 1开)', '1', 'NVL', '22', '2019-09-16 20:10:23', 'admin', '2019-09-26 11:17:47', 'admin');
INSERT INTO `sys_config` VALUES ('189', '1', '1', 'kefu_tel', '客服电话', '111111', 'NVL', '0', '2019-07-09 14:37:58', 'admin', '2019-07-09 14:37:58', 'admin');
INSERT INTO `sys_config` VALUES ('190', '1', '1', 'kefu_qq', '客服QQ', '11111', 'NVL', '17', '2019-07-12 16:01:31', 'admin', '2019-10-25 11:36:37', 'admin');
INSERT INTO `sys_config` VALUES ('191', '1', '1', 'kefu_wx', '客服微信', 'https://qiniu.chengyuwb.com/1633005979737.jpg', 'NVL', '19', '2019-09-09 14:46:34', 'admin', '2019-11-26 18:09:08', 'admin');
INSERT INTO `sys_config` VALUES ('195', '1', '1', 'icp_no', '主站备案号', '闽ICP备20004877号-1', 'NVL', '17', '2019-07-12 16:01:31', 'admin', '2019-10-25 11:36:37', 'admin');
INSERT INTO `sys_config` VALUES ('197', '1', '1', 'android_update_content', '安卓更新内容', '1.详情页优化\n2.bug 修改', 'NVL', '23', '2019-09-16 20:10:23', 'admin', '2019-09-16 20:10:23', 'admin');
INSERT INTO `sys_config` VALUES ('199', '1', '1', 'ios_update_content', 'IOS更新内容', '1.详情页优化\n2.bug 修改', 'NVL', '24', '2019-09-16 20:10:23', 'admin', '2019-09-16 20:10:23', 'admin');
INSERT INTO `sys_config` VALUES ('200', '1', '1', 'web_url', '主站链接', 'http://letaoshop.dayushop.top', 'NVL', '25', '2021-01-29 15:03:57', 'admin', '2019-09-16 20:10:23', 'admin');
INSERT INTO `sys_config` VALUES ('201', '1', '1', 'spread_content', '推广文案', '刚发现个做任务赚零钱平台, 任何人都可以做, 闲时赚点生活费', 'NVL', '26', '2021-01-29 15:04:33', 'admin', '2021-01-29 15:04:36', 'admin');
INSERT INTO `sys_config` VALUES ('202', '1', '1', 'caner_status', '隐藏与显示', '1', 'NVL', '0', '2021-02-01 17:55:01', 'admin', '2021-02-01 17:55:03', 'admin');
INSERT INTO `sys_config` VALUES ('203', '1', '1', 'portal_api', '前端请求URL', 'http://portalapi.jkbd.vip', 'NVL', '0', '2021-02-06 19:07:51', '', '2021-02-06 19:07:53', '');
INSERT INTO `sys_config` VALUES ('204', '1', '1', 'fs_kefu_tel', '客服电话', '1112233', 'NVL', '0', '2019-07-09 14:37:58', 'admin', '2019-07-09 14:37:58', 'admin');
INSERT INTO `sys_config` VALUES ('205', '1', '1', 'fs_kefu_qq', '客服QQ', '11111', 'NVL', '17', '2019-07-12 16:01:31', 'admin', '2019-10-25 11:36:37', 'admin');
INSERT INTO `sys_config` VALUES ('206', '1', '1', 'fs_kefu_wx', '客服微信', 'fcyfloat', 'NVL', '19', '2019-09-09 14:46:34', 'admin', '2019-11-26 18:09:08', 'admin');
INSERT INTO `sys_config` VALUES ('207', '1', '1', 'weihu_no_login', '只能登录不能操作的网站维护', '0', 'NVL', '0', '2021-03-08 18:53:46', null, '2021-03-08 18:53:48', null);
INSERT INTO `sys_config` VALUES ('208', '1', '1', 'weihu_no_take_task', '能登录能提现不能接单的网站维护', '0', 'NVL', '0', '2021-03-08 18:55:19', null, '2021-03-08 18:55:21', null);
INSERT INTO `sys_config` VALUES ('209', '1', '1', 'h5_url', 'h5主站链接', 'http://letaoh5.dayushop.top', 'NVL', '25', '2021-01-29 15:03:57', 'admin', '2019-09-16 20:10:23', 'admin');
INSERT INTO `sys_config` VALUES ('210', '1', '1', 'kefu_url', '第三方客服链接', 'http://im.chyuxin.cn/index/index/home?visiter_id={uid}&visiter_name={username}&avatar={headImg}&business_id=2&groupid=1&special=3	', 'NVL', '0', '2021-06-03 19:24:26', 'admin', '2021-06-03 19:24:30', 'admin');
INSERT INTO `sys_config` VALUES ('211', '1', '1', 'kefu_qq2', '充值咨询QQ', '111', 'NVL', '0', '2021-06-03 19:25:17', null, '2021-06-03 19:25:20', null);
INSERT INTO `sys_config` VALUES ('214', '1', '1', 'shop_background', '', 'https://qiniu.chengyuwb.com/b6f96209-523d-4797-b509-7cef0c3f.jpg', 'NVL', '0', null, '', null, '');
INSERT INTO `sys_config` VALUES ('216', '1', '1', 'reg_finish_turn', '注册后跳转页面>>1H5>>2下载', '2', 'NVL', '0', '2021-07-19 10:06:57', null, '2021-07-19 10:06:57', null);
INSERT INTO `sys_config` VALUES ('219', '1', '1', 'app_logo', 'APP名称', 'https://qiniu.chengyuwb.com/1639104026040.png', 'NVL', '2', '2019-03-05 14:28:29', 'admin', '2019-07-15 17:04:05', 'admin');
INSERT INTO `sys_config` VALUES ('220', '1', '1', 'app_name', 'APPLOGO', '极客演示', 'NVL', '3', '2019-04-13 17:40:14', 'admin', '2019-07-09 14:37:58', 'admin');
INSERT INTO `sys_config` VALUES ('221', '1', '1', 'shop_need_verify', '店铺是否需要审核', '1', 'NVL', '0', null, null, null, null);
INSERT INTO `sys_config` VALUES ('222', '1', '1', 'local_img_save_path', '图片存储地址', 'D:\\java\\bucket', 'NVL', '0', null, null, null, null);
INSERT INTO `sys_config` VALUES ('223', '1', '1', 'local_img_url', '图片服务器地址', 'https://qiniu.chengyuwb.com', 'NVL', '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_invite_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_invite_code`;
CREATE TABLE `sys_invite_code` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT NULL COMMENT '邀请码类型>>1用户>>2员工',
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  `invite_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邀请码',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `invite_code` (`invite_code`) USING BTREE,
  UNIQUE KEY `type` (`type`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='邀请码';

-- ----------------------------
-- Records of sys_invite_code
-- ----------------------------
INSERT INTO `sys_invite_code` VALUES ('1', '1', '1', '022023', '2022-05-10 17:02:10', null, null, null);
INSERT INTO `sys_invite_code` VALUES ('2', '1', '2', '397354', '2022-05-10 17:11:29', null, null, null);
INSERT INTO `sys_invite_code` VALUES ('3', '1', '3', '054471', '2022-05-10 17:12:40', null, null, null);
INSERT INTO `sys_invite_code` VALUES ('4', '1', '4', '010535', '2022-05-11 18:49:34', null, null, null);
INSERT INTO `sys_invite_code` VALUES ('5', '1', '5', '512945', '2022-05-16 11:37:38', null, null, null);

-- ----------------------------
-- Table structure for sys_jiguang_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_jiguang_config`;
CREATE TABLE `sys_jiguang_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_key` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'appID',
  `app_secret` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'APPSECRET',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='极光配置表';

-- ----------------------------
-- Records of sys_jiguang_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pid` int DEFAULT NULL COMMENT '上级菜单ID',
  `level` int DEFAULT NULL COMMENT '菜单等级',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单URL',
  `image` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单图标',
  `sort` int DEFAULT NULL COMMENT '顺序',
  `sort1` int DEFAULT NULL COMMENT '顺序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=471 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='后台菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('93', null, '1', '会员管理', '/member', 'user', '2', null, '2019-04-16 15:10:53', '', '2022-05-07 14:33:46', 'admin');
INSERT INTO `sys_menu` VALUES ('94', '93', '2', '会员列表', '/member/list', '', '2', null, '2019-04-16 15:10:53', '', '2021-05-27 19:36:22', 'admin');
INSERT INTO `sys_menu` VALUES ('132', null, '1', '系统管理', '/system', '', '13', null, '2019-04-16 15:10:53', '', '2019-04-16 15:10:53', 'admin');
INSERT INTO `sys_menu` VALUES ('133', '132', '2', '管理员', '/system/admin', '', '1', null, '2019-04-16 15:10:53', '', '2019-04-16 15:10:53', '');
INSERT INTO `sys_menu` VALUES ('134', '132', '2', '角色管理', '/system/adminType', '', '1', null, '2019-04-16 15:10:53', '', '2020-03-19 14:34:25', 'admin');
INSERT INTO `sys_menu` VALUES ('135', '132', '2', '系统参数', '/system/params', '', '1', null, '2019-04-16 15:10:53', '', '2020-03-19 14:34:29', 'admin');
INSERT INTO `sys_menu` VALUES ('137', null, '1', '内容管理', '/content', '', '10', null, '2019-04-16 15:10:53', '', '2021-10-27 18:40:46', 'admin');
INSERT INTO `sys_menu` VALUES ('138', '137', '2', '轮播图', '/content/banner', '', '1', null, '2019-04-16 15:10:53', '', '2022-05-07 12:08:43', 'admin');
INSERT INTO `sys_menu` VALUES ('141', '137', '2', '协议文案', '/content/agreement', '', '3', null, '2019-04-16 15:10:53', '', '2020-04-10 12:52:25', 'admin');
INSERT INTO `sys_menu` VALUES ('142', '137', '2', '文章列表', '/content/article', '', '6', null, '2019-04-16 15:10:53', '', '2020-09-27 14:25:16', 'admin');
INSERT INTO `sys_menu` VALUES ('143', '137', '2', '发表文章', '/content/article/create', '', '7', null, '2019-04-16 15:10:53', '', '2020-09-27 14:25:20', 'admin');
INSERT INTO `sys_menu` VALUES ('144', '137', '2', '编辑文章', '/content/article/edit/:id(\\d+)', '', '8', null, '2019-04-16 15:10:53', '', '2020-09-27 14:25:25', 'admin');
INSERT INTO `sys_menu` VALUES ('145', null, '1', '三方管理', '/third', '', '15', null, '2019-04-16 15:10:53', '', '2020-03-11 12:54:05', 'admin');
INSERT INTO `sys_menu` VALUES ('147', '145', '2', '短信配置', '/third/sms', '', '2', null, '2019-04-16 15:10:53', '', '2019-04-16 15:10:53', '');
INSERT INTO `sys_menu` VALUES ('172', null, '1', '财务管理', '/fund', '', '5', null, '2019-04-16 15:10:53', '', '2022-05-07 14:43:46', 'admin');
INSERT INTO `sys_menu` VALUES ('174', '172', '2', '充值记录', '/fund/recharge', '', '2', null, '2019-04-16 15:10:53', '', '2021-01-14 20:24:27', 'admin');
INSERT INTO `sys_menu` VALUES ('217', null, '1', '系统监控', '/log', '', '14', null, '2020-03-11 12:53:47', '', '2021-01-14 20:41:58', 'admin');
INSERT INTO `sys_menu` VALUES ('218', '217', '2', '后台登录日志', '/log/loginLog', '', '1', null, '2020-03-11 12:54:28', '', '2021-01-14 20:42:07', 'admin');
INSERT INTO `sys_menu` VALUES ('219', '217', '2', '后台操作日志', '/log/operationLog', '', '2', null, '2020-03-11 12:54:36', '', '2021-01-14 20:42:13', 'admin');
INSERT INTO `sys_menu` VALUES ('220', '137', '2', '意见反馈', '/content/feedback', '', '4', null, '2020-03-13 10:27:58', '', '2021-01-14 20:40:41', 'admin');
INSERT INTO `sys_menu` VALUES ('223', '217', '2', '验证码记录', '/log/messageCode', null, '3', null, '2020-03-13 10:34:38', null, '2020-03-13 10:34:38', 'admin');
INSERT INTO `sys_menu` VALUES ('227', '172', '2', '积分明细', '/fund/point', '', '4', null, '2020-03-15 11:54:08', '', '2021-08-23 19:32:13', 'admin');
INSERT INTO `sys_menu` VALUES ('228', '172', '2', '推广佣金', '/fund/spmission', '', '5', null, '2020-03-15 11:54:25', '', '2022-05-07 12:07:38', 'admin');
INSERT INTO `sys_menu` VALUES ('236', '93', '2', '银行认证', '/member/bank', '', '4', null, '2020-03-29 12:05:30', '', '2022-05-07 12:07:07', 'admin');
INSERT INTO `sys_menu` VALUES ('243', '132', '2', '个人中心', '/system/center', null, '5', null, '2020-04-04 17:01:07', null, '2020-04-04 17:01:07', 'admin');
INSERT INTO `sys_menu` VALUES ('254', '137', '2', '素材管理', '/content/source', null, '14', null, '2020-04-23 18:48:33', null, '2020-04-23 18:48:33', 'admin');
INSERT INTO `sys_menu` VALUES ('255', '172', '2', '提现记录', '/fund/withdraw', '', '1', null, '2020-08-14 12:14:54', '', '2022-05-07 12:07:32', 'admin');
INSERT INTO `sys_menu` VALUES ('256', '172', '2', '资金明细', '/fund/account', '', '3', null, '2020-08-14 14:15:35', '', '2021-01-14 20:24:44', 'admin');
INSERT INTO `sys_menu` VALUES ('262', '132', '2', '菜单管理', '/system/menuList', '', '5', null, '2020-08-14 12:12:38', '', '2021-01-14 20:41:47', 'admin');
INSERT INTO `sys_menu` VALUES ('263', '93', '2', '实名认证', '/member/realname', '', '4', null, '2020-09-27 14:04:11', '', '2022-05-07 12:07:11', 'admin');
INSERT INTO `sys_menu` VALUES ('264', '93', '2', '会员日志', '/member/log', '', '8', null, '2020-09-27 14:05:16', '', '2021-01-14 20:23:14', 'admin');
INSERT INTO `sys_menu` VALUES ('271', '132', '2', 'APP更新', '/system/modParams', null, '4', null, '2020-10-20 11:10:06', null, '2020-10-20 11:10:06', 'admin');
INSERT INTO `sys_menu` VALUES ('274', null, '1', '参数管理', '/config', '', '8', null, '2020-10-26 18:20:38', '', '2021-07-05 16:58:34', 'admin');
INSERT INTO `sys_menu` VALUES ('281', '93', '2', '会员组别', '/member/group', '', '3', null, '2020-11-05 14:33:41', '', '2021-05-27 19:36:27', 'admin');
INSERT INTO `sys_menu` VALUES ('311', '274', '2', '会员组参数', '/config/member', null, '1', null, '2021-01-14 20:33:13', null, '2021-01-14 20:33:13', 'admin');
INSERT INTO `sys_menu` VALUES ('313', '274', '2', '佣金奖励设置', '/config/mission', null, '2', null, '2021-01-14 20:33:38', null, '2021-01-14 20:33:38', 'admin');
INSERT INTO `sys_menu` VALUES ('315', '274', '2', '微信短信通知', '/config/gzh', '', '4', null, '2021-01-14 20:34:11', '', '2022-05-07 12:08:12', 'admin');
INSERT INTO `sys_menu` VALUES ('318', '274', '2', '系统收款账户', '/config/account', '', '6', null, '2021-01-14 20:35:08', '', '2022-05-07 14:40:17', 'admin');
INSERT INTO `sys_menu` VALUES ('332', '137', '2', '文章分类', '/content/articleCate', null, '5', null, '2021-01-14 20:40:31', null, '2021-01-14 20:40:31', 'admin');
INSERT INTO `sys_menu` VALUES ('333', '137', '2', '公告管理', '/content/notice', null, '9', null, '2021-01-14 20:41:09', null, '2021-01-14 20:41:09', 'admin');
INSERT INTO `sys_menu` VALUES ('334', '145', '2', '实名认证接口', '/third/realnameApi', '', '4', null, '2021-01-14 20:42:33', '', '2021-01-14 20:43:24', 'admin');
INSERT INTO `sys_menu` VALUES ('347', '274', '2', '设置佣金组别比例', '/config/mission/edit/:id(\\d+)', null, '9', null, '2021-01-16 11:51:01', null, '2021-01-16 11:51:01', 'admin');
INSERT INTO `sys_menu` VALUES ('348', '93', '2', '编辑会员详情', '/member/detail/:id(\\d+)', null, '8', null, '2021-01-20 14:30:35', null, '2021-01-20 14:30:35', 'admin');
INSERT INTO `sys_menu` VALUES ('350', '93', '2', '用户处罚', '/member/punishment', '', '7', null, '2021-01-30 11:06:36', '', '2022-05-07 12:07:14', 'admin');
INSERT INTO `sys_menu` VALUES ('351', '145', '2', '微信配置', '/third/weixin', null, '1', null, '2021-02-05 20:04:00', null, '2021-02-05 20:04:00', 'admin');
INSERT INTO `sys_menu` VALUES ('355', '93', '2', '会员详情', '/member/detail', '', '1', null, '2021-05-27 19:36:13', '', '2021-05-27 19:36:18', 'admin');
INSERT INTO `sys_menu` VALUES ('379', '145', '2', '微信模板消息', '/third/weixinTemplate', null, '2', null, '2021-07-07 11:52:49', null, '2021-07-07 11:52:49', 'admin');
INSERT INTO `sys_menu` VALUES ('384', '93', '2', '手机通讯录', '/member/phonebook', '', '10', null, '2021-02-04 16:46:01', '', '2022-05-07 12:07:24', 'admin');
INSERT INTO `sys_menu` VALUES ('385', '93', '2', '查看通讯录', '/member/phonebook/viewLog/:id(\\d+)', '', '12', null, '2021-02-05 16:05:14', '', '2021-06-26 13:39:14', 'admin');
INSERT INTO `sys_menu` VALUES ('387', '145', '2', '接口切换配置', '/third/thirdConfig', null, '3', null, '2021-10-22 16:54:53', null, '2021-10-22 16:54:53', 'admin');
INSERT INTO `sys_menu` VALUES ('394', '145', '2', '接口预警设置', '/third/thirdWarn', null, '3', null, '2021-10-23 14:19:57', null, '2021-10-23 14:19:57', 'admin');
INSERT INTO `sys_menu` VALUES ('456', null, '1', '店铺管理', '/shop', null, '1', null, '2022-05-07 14:29:28', null, '2022-05-07 14:29:28', 'admin');
INSERT INTO `sys_menu` VALUES ('457', '456', '2', '店铺列表', '/shop/list', null, '1', null, '2022-05-07 14:29:43', null, '2022-05-07 14:29:43', 'admin');
INSERT INTO `sys_menu` VALUES ('458', '456', '2', '新店铺审核', '/shop/verify', null, '2', null, '2022-05-07 14:29:53', null, '2022-05-07 14:29:53', 'admin');
INSERT INTO `sys_menu` VALUES ('459', '456', '2', '服务管理', '/shop/shopService', null, '3', null, '2022-05-07 14:30:01', null, '2022-05-07 14:30:01', 'admin');
INSERT INTO `sys_menu` VALUES ('460', '456', '2', '运费模板', '/shop/freight', null, '4', null, '2022-05-07 14:30:10', null, '2022-05-07 14:30:10', 'admin');
INSERT INTO `sys_menu` VALUES ('461', null, '1', '商品管理', '/goods', '', '4', null, '2022-05-07 14:33:59', '', '2022-05-07 14:43:43', 'admin');
INSERT INTO `sys_menu` VALUES ('462', '461', '2', '电商分类', '/goods/goodsCate', null, '1', null, '2022-05-07 14:34:17', null, '2022-05-07 14:34:17', 'admin');
INSERT INTO `sys_menu` VALUES ('463', '461', '2', '商品列表', '/goods/list', null, '2', null, '2022-05-07 14:34:28', null, '2022-05-07 14:34:28', 'admin');
INSERT INTO `sys_menu` VALUES ('464', '274', '2', '订单配置', '/config/order', '', '7', null, '2022-05-07 14:38:07', '', '2022-05-07 14:40:14', 'admin');
INSERT INTO `sys_menu` VALUES ('465', '274', '2', '搜索管理', '/config/search', '', '8', null, '2022-05-07 14:39:31', '', '2022-05-07 14:40:10', 'admin');
INSERT INTO `sys_menu` VALUES ('466', null, '1', '订单监管', '/order', null, '3', null, '2022-05-07 14:43:39', null, '2022-05-07 14:43:39', 'admin');
INSERT INTO `sys_menu` VALUES ('467', '466', '2', '订单管理', '/order/list', null, '1', null, '2022-05-07 14:44:05', null, '2022-05-07 14:44:05', 'admin');
INSERT INTO `sys_menu` VALUES ('468', '466', '2', '评论管理', '/order/comment', null, '2', null, '2022-05-07 14:44:15', null, '2022-05-07 14:44:15', 'admin');
INSERT INTO `sys_menu` VALUES ('469', '466', '2', '退款管理', '/order/refund', null, '3', null, '2022-05-07 14:44:25', null, '2022-05-07 14:44:25', 'admin');
INSERT INTO `sys_menu` VALUES ('470', '145', '2', '七牛云配置', '/third/qiniu', null, '5', null, '2022-05-11 10:15:10', null, '2022-05-11 10:15:10', 'admin');

-- ----------------------------
-- Table structure for sys_qiniu_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_qiniu_config`;
CREATE TABLE `sys_qiniu_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `access_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'AK',
  `secret_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'SK',
  `img_zone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '七牛存储区域',
  `img_bucket` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '空间名称',
  `img_view_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '下载路径',
  `video_zone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '视频存储区域',
  `video_bucket` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '空间名称',
  `video_view_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '下载路径',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='七牛配置表';

-- ----------------------------
-- Records of sys_qiniu_config
-- ----------------------------
INSERT INTO `sys_qiniu_config` VALUES ('1', 'Dtu2_poFKTO9Tm3og_3GIrNNmXMp9y6DJDmkDS6B', 'syIfkSpI1GsWY50Ggo3PH01KLrrIgrfKZP1Y1TJP', 'z2', 'chengyu', 'https://qiniu.chengyuwb.com', 'z2', 'chengyu-video', 'http://qiniu2.chengyuwb.com', '2018-12-01 15:27:44', 'admin', '2018-12-01 15:27:46', 'admin');

-- ----------------------------
-- Table structure for sys_third_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_config`;
CREATE TABLE `sys_third_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数标识',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数名称',
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数值',
  `platforms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '包括平台',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uqx_config` (`nid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='接口切换表';

-- ----------------------------
-- Records of sys_third_config
-- ----------------------------
INSERT INTO `sys_third_config` VALUES ('1', 'idcard', '身份证实时检验', 'wwyw', 'wwyw,ali', '2021-10-22 17:46:24', null, null, null);
INSERT INTO `sys_third_config` VALUES ('2', 'bank', '银行卡二要素', 'wwyw', 'wwyw,ali', '2021-10-22 17:46:22', null, null, null);
INSERT INTO `sys_third_config` VALUES ('3', 'upload', '上传图片视频', 'qiniu', 'qiniu,local,ali', '2021-10-26 16:36:06', null, null, null);

-- ----------------------------
-- Table structure for sys_third_warn
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_warn`;
CREATE TABLE `sys_third_warn` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '第三方平台',
  `nid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台标识',
  `interface_nid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '接口标识',
  `left_amount` decimal(18,2) DEFAULT '0.00' COMMENT '剩余金额',
  `warn_amount` decimal(18,2) DEFAULT NULL COMMENT '预警金额',
  `warn_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '预警手机, 填0不预警',
  `warn_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '预警邮箱, 填0不预警',
  `status` int DEFAULT '1' COMMENT '状态',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='第三方余额预警';

-- ----------------------------
-- Records of sys_third_warn
-- ----------------------------
INSERT INTO `sys_third_warn` VALUES ('1', '短信宝', 'dxb', 'balance', '0.00', '10.00', '0', '0', '1', '2021-10-23 14:09:38', null, '2021-10-23 14:09:41', null);
INSERT INTO `sys_third_warn` VALUES ('2', '万维易网-实名认证', 'wwyw', 'idcard', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('3', '万维易网-京东详情', 'wwyw', 'jd_detail', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('4', '万维易网-拼多多详情', 'wwyw', 'pdd_detail', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('5', '淘发客', 'tfk', 'balance', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('6', '礼品网-爱采购', 'acg', 'balance', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('7', '礼品网-快递礼品网', 'kdlp', 'balance', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('8', '小飞象', 'xfx', 'balance', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('9', '查电商', 'cds', 'balance', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('10', '订单侠', 'ddx', 'balance', '0.00', '10.00', '0', '0', '1', null, null, null, null);
INSERT INTO `sys_third_warn` VALUES ('11', '丰巢开放', 'fc', 'balance', '0.00', '10.00', '0', '0', '1', null, null, null, null);

-- ----------------------------
-- Table structure for sys_verifycode
-- ----------------------------
DROP TABLE IF EXISTS `sys_verifycode`;
CREATE TABLE `sys_verifycode` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '验证类型(1手机 2邮箱)',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '验证号码',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '验证码',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `valid_time` datetime DEFAULT NULL COMMENT '有效时间',
  `send_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发送状态(0:未发送 1:已发送 2:发送失败)',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='验证码表';

-- ----------------------------
-- Records of sys_verifycode
-- ----------------------------
INSERT INTO `sys_verifycode` VALUES ('1', '1', '19859222520', '672163', '2022-05-16 11:37:19', null, '2', null, null, null, null);
INSERT INTO `sys_verifycode` VALUES ('2', '1', '19859222512', '117754', '2022-05-10 17:11:20', null, '0', null, null, null, null);
INSERT INTO `sys_verifycode` VALUES ('3', '1', '19859222513', '183112', '2022-05-10 17:12:30', null, '0', null, null, null, null);
INSERT INTO `sys_verifycode` VALUES ('4', '1', '19859222511', '317710', '2022-05-11 17:34:18', null, '0', null, null, null, null);
INSERT INTO `sys_verifycode` VALUES ('5', '1', '19859222511', '150437', '2022-05-11 17:35:29', null, '0', null, null, null, null);
INSERT INTO `sys_verifycode` VALUES ('6', '1', '19859222511', '473807', '2022-05-11 17:44:28', null, '0', null, null, null, null);
INSERT INTO `sys_verifycode` VALUES ('7', '1', '19859222511', '793490', '2022-05-11 17:47:49', null, '0', null, null, null, null);
INSERT INTO `sys_verifycode` VALUES ('8', '1', '13444555666', '200376', '2022-05-11 18:49:30', null, '0', null, null, null, null);
INSERT INTO `sys_verifycode` VALUES ('9', '1', '19859222520', '672163', '2022-05-16 11:37:19', null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_weixin_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_weixin_config`;
CREATE TABLE `sys_weixin_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `kf_app_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开放平台APPID',
  `kf_secret` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开放平台密钥',
  `xcx_app_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '小程序APPID',
  `xcx_secret` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '小程序密钥',
  `gzh_app_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公众号APPID',
  `gzh_secret` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公众号密钥',
  `mch_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信商户号',
  `mch_key` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信商户密钥',
  `pay_notify_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付回调',
  `cert_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商户证书地址',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信配置表';

-- ----------------------------
-- Records of sys_weixin_config
-- ----------------------------
INSERT INTO `sys_weixin_config` VALUES ('3', '', '', 'null', 'null', 'admin', 'a123123', '', '', '', '', '2020-10-12 10:43:38', '', '2022-02-13 00:10:24', '');

-- ----------------------------
-- Table structure for sys_weixin_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_weixin_template`;
CREATE TABLE `sys_weixin_template` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板分类',
  `template_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板ID',
  `content` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板内容',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cate` (`cate`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信消息模板表';

-- ----------------------------
-- Records of sys_weixin_template
-- ----------------------------
INSERT INTO `sys_weixin_template` VALUES ('1', '1', 'lCbEN8dp9EzczQO5BjmwOAK-kwcdJJRlYb5IL1z8YxM', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);
INSERT INTO `sys_weixin_template` VALUES ('2', '2', 'L-c__qLeW4NqHpyQZ3n84t4Lo5AHnlsKhUyUaYOgimg', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);
INSERT INTO `sys_weixin_template` VALUES ('3', '3', 'FAPz0Tqsl6s2ZxuOVGKS-i8zmXWawiLKvdhsPtGm_04', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);
INSERT INTO `sys_weixin_template` VALUES ('4', '4', 'KuxEF9aWZOcCPjCUntcJ2HsVIDgjL5T9_vzj7VYK8yM', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);
INSERT INTO `sys_weixin_template` VALUES ('5', '5', 'ZdkCfLoF28DuZ6-9ZiaXn0AQQsgwn13-wz9Q2CRv9-E', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);
INSERT INTO `sys_weixin_template` VALUES ('6', '6', 'pFPZNt5mhcedDcUwjAeqA8jEG3xA33BxhzzJX_lasWI', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);
INSERT INTO `sys_weixin_template` VALUES ('7', '7', 'VjG6twg3iPVURXfTzjezYDqI2uuvSAPGzPDJO8Xtcrk', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);
INSERT INTO `sys_weixin_template` VALUES ('8', '8', 'R4In6cDpQI8GhqkKpkbvYxSgzAhgaWsnpQOATOnpO9E', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);
INSERT INTO `sys_weixin_template` VALUES ('9', '9', 'VPv_9VBHpoo8J9kpGOLZt4lKp2eajSa936k2-5CxXL4', null, '2021-07-07 12:17:53', null, '2021-07-07 12:17:53', null);

-- ----------------------------
-- Table structure for sys_zfb_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_zfb_config`;
CREATE TABLE `sys_zfb_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商户ID',
  `zfb_public_key` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付宝公钥',
  `app_public_key` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用公钥',
  `app_private_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '应用密钥',
  `pay_notify_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付回调',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='支付宝配置表';

-- ----------------------------
-- Records of sys_zfb_config
-- ----------------------------

-- ----------------------------
-- Table structure for task_trigger
-- ----------------------------
DROP TABLE IF EXISTS `task_trigger`;
CREATE TABLE `task_trigger` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '定时器类型',
  `trigger_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '定时ID',
  `task_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务编号',
  `status` int DEFAULT NULL COMMENT '0待执行>>1已执行>>2已取消',
  `start_time` datetime DEFAULT NULL COMMENT '执行时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `trigger_id` (`trigger_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=931 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='任务定时器';

-- ----------------------------
-- Records of task_trigger
-- ----------------------------
INSERT INTO `task_trigger` VALUES ('927', 'OrderAutoCancelJob', '98706baf-eabb-458f-8d89-3896f6eae0e0', '165277018282310000005', '1', '2022-05-17 15:19:43', '2022-05-17 14:49:43', null, '2022-05-17 15:19:42', null);
INSERT INTO `task_trigger` VALUES ('928', 'OrderAutoCancelJob', '4ece0c0e-50c8-405e-9741-82881f5953eb', '165277088593010020005', '1', '2022-05-17 15:31:26', '2022-05-17 15:01:26', null, '2022-05-17 15:31:25', null);
INSERT INTO `task_trigger` VALUES ('929', 'OrderAutoCancelJob', '135ca0c2-0a54-4288-9f7b-a916712b632e', '165277141486610030005', '1', '2022-05-17 15:40:15', '2022-05-17 15:10:15', null, '2022-05-17 15:40:14', null);
INSERT INTO `task_trigger` VALUES ('930', 'OrderAutoCancelJob', 'bf6dbf21-2878-4692-b419-bdf5bbb9669d', '165277259977610050005', '1', '2022-05-17 16:00:00', '2022-05-17 15:30:00', null, '2022-05-17 15:59:59', null);

-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` int DEFAULT NULL COMMENT '用户唯一id',
  `group_id` int DEFAULT NULL COMMENT '会员组别',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员组别',
  `group_icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组别ICON',
  `type` int DEFAULT NULL COMMENT '1买家>>2商家',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `kf_open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开放平台openID',
  `xcx_open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '小程序openid',
  `gzh_open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公众号openId',
  `unionid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户唯一标识',
  `head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `weixin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信名称',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1男 2女',
  `realname` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'qq',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `age` int DEFAULT NULL COMMENT '年齡',
  `password` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录密码',
  `pay_password` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付密码',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
  `province_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
  `city_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
  `area_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '详细地址',
  `person_sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '个人签名',
  `method` int DEFAULT NULL COMMENT '注册方式(1网页端 2APP 3邀请链接 4未知)',
  `status` int DEFAULT '1' COMMENT '用户状态(-1:停用 1:正常)',
  `phone_status` int DEFAULT '0' COMMENT '手机认证状态(0:未认证 1:已认证)',
  `realname_status` int DEFAULT '0' COMMENT '实名认证状态(0:未认证 1:已认证)',
  `bank_status` int DEFAULT '0' COMMENT '银行认证状态(0:未认证 1:已认证)',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tjr_id` int DEFAULT NULL COMMENT '推荐人ID',
  `invite_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邀请码',
  `register_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册IP',
  `register_ip_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册IP实际地址',
  `register_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册链接',
  `mission_config_id` int DEFAULT NULL COMMENT '佣金组ID',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `registration_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密保问题',
  `secret_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密保问题答案',
  `punishment_status` int DEFAULT '0' COMMENT '处罚状态>>0未处罚>>1已处罚',
  `tjr_uid` int DEFAULT NULL COMMENT '推荐人Uid',
  `ime_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '设备ID',
  `invite_status` int DEFAULT '1' COMMENT '0不可邀请>>1可邀请',
  `freeze_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '冻结理由',
  `withdraw_limit` int DEFAULT '0' COMMENT '0未限制>>1已限制提现',
  `remind_voice` int DEFAULT '1' COMMENT '0不播放>>1播放',
  `gzh_focus_status` int DEFAULT '0' COMMENT '是否关注公众号>>0否>>1是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `uid` (`uid`) USING BTREE,
  UNIQUE KEY `invite_code` (`invite_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='会员表';

-- ----------------------------
-- Records of ums_member
-- ----------------------------
INSERT INTO `ums_member` VALUES ('1', '1000001', '6', '默认卖家', null, '2', '19859222511', null, null, null, null, 'https://qiniu.chengyuwb.com/1619694381443.jpg', null, null, null, '19859222511', '19859222511', null, null, null, null, '$2a$10$jqEZrbcxvYIStPrCPruUheiwXrjUOxTaoN3MdBzrKB96TeISNPcia', null, null, null, null, null, null, null, null, null, '1', '1', '1', '0', '0', null, null, '022023', '192.168.0.109', null, 'localhost:9529', '1', '2022-05-10 17:02:10', null, '2022-05-11 17:47:50', null, null, null, null, '0', null, null, '1', null, '0', '1', '0');
INSERT INTO `ums_member` VALUES ('2', '1000002', '6', '默认卖家', null, '2', '19859222512', null, null, null, null, 'https://qiniu.chengyuwb.com/1619694381443.jpg', null, null, null, '19859222512', '19859222512', null, null, null, null, '$2a$10$ScoI2GxFGKhIXoK5EB3elOaHs3T4YKezpkjJO85J/xT7nj9y9u/KC', null, null, null, null, null, null, null, null, null, '1', '1', '1', '0', '0', null, null, '397354', '192.168.0.109', null, 'localhost:9529', '1', '2022-05-10 17:11:29', null, '2022-05-11 15:48:52', null, null, null, null, '0', null, null, '1', null, '0', '1', '0');
INSERT INTO `ums_member` VALUES ('3', '1000003', '6', '默认卖家', null, '1', '19859222513', null, null, null, null, 'https://qiniu.chengyuwb.com/1619694381443.jpg', null, null, null, '19859222513', '19859222513', null, null, null, null, '$2a$10$hfM7DnB6EDtpTV97d7sne.kq8cTdIGnHg4/s459eDwcGtwBSVTcKa', null, null, null, null, null, null, null, null, null, '1', '1', '1', '0', '0', null, null, '054471', '192.168.0.109', null, 'localhost:9529', '1', '2022-05-10 17:12:40', null, null, null, null, null, null, '0', null, null, '1', null, '0', '1', '0');
INSERT INTO `ums_member` VALUES ('4', '1000004', '2', '默认组别', '', '2', '13444555666', null, null, null, null, 'https://qiniu.chengyuwb.com/1652256834662.png', null, null, null, '13444555666', '13444555666', null, null, null, null, '$2a$10$a/09rrOKlHWbGgz9GCk/suu5.pPNsAoez0maAF6nnSrW9jqyjoE0i', null, null, null, null, null, null, null, null, null, '1', '1', '1', '0', '1', null, null, '010535', '192.168.0.105', null, 'localhost:9530', '1', '2022-05-11 18:49:34', null, '2022-05-12 11:58:09', null, null, null, null, '0', null, null, '1', null, '0', '1', '0');
INSERT INTO `ums_member` VALUES ('5', '1000005', '2', '默认组别', '', '1', 'yyw', null, null, null, null, 'https://qiniu.chengyuwb.com/1652685527471.jpeg', null, '1', null, 'yyw', '19859222520', null, null, null, null, '$2a$10$/FI1T2gNhdTisNzLfRbp3.n2C18Ds8nKrEohBLL.exUOpAtLns5FK', null, null, null, null, null, null, null, null, '闪电闪电', '1', '1', '1', '0', '0', null, null, '512945', '192.168.0.109', null, 'localhost:8080', '1', '2022-05-16 11:37:38', null, '2022-05-16 15:29:46', null, null, null, null, '0', null, null, '1', null, '0', '1', '0');

-- ----------------------------
-- Table structure for ums_member_account
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_account`;
CREATE TABLE `ums_member_account` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `amount` decimal(18,2) DEFAULT '0.00' COMMENT '余额',
  `freeze_amount` decimal(18,2) DEFAULT '0.00' COMMENT '冻结金额',
  `mission` decimal(18,2) DEFAULT '0.00' COMMENT '任务佣金',
  `freeze_mission` decimal(18,2) DEFAULT '0.00' COMMENT '冻结任务佣金',
  `spmission` decimal(18,2) DEFAULT '0.00' COMMENT '推广佣金',
  `freeze_spmission` decimal(18,2) DEFAULT '0.00' COMMENT '冻结推广佣金',
  `point` decimal(18,2) DEFAULT '0.00' COMMENT '积分',
  `freeze_point` decimal(18,2) DEFAULT '0.00' COMMENT '冻结积分',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='会员账户表';

-- ----------------------------
-- Records of ums_member_account
-- ----------------------------
INSERT INTO `ums_member_account` VALUES ('1', '1', '19859222511', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2022-05-10 17:02:10', null, '2022-05-10 17:02:10', null);
INSERT INTO `ums_member_account` VALUES ('2', '2', '19859222512', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2022-05-10 17:11:29', null, '2022-05-10 17:11:29', null);
INSERT INTO `ums_member_account` VALUES ('3', '3', '19859222513', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2022-05-10 17:12:40', null, '2022-05-10 17:12:40', null);
INSERT INTO `ums_member_account` VALUES ('4', '4', '13444555666', '18678.00', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2022-05-11 18:49:34', null, '2022-05-11 18:49:34', null);
INSERT INTO `ums_member_account` VALUES ('5', '5', 'yyw', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2022-05-16 11:37:38', null, '2022-05-16 11:37:38', null);

-- ----------------------------
-- Table structure for ums_member_account_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_account_log`;
CREATE TABLE `ums_member_account_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT NULL COMMENT '资金类型',
  `in_out` int DEFAULT NULL COMMENT '收支类型 1收入 -1支出',
  `pay_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付方式(wxPay微信 aliPay支付宝 balance余额支付)',
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `amount` decimal(15,2) DEFAULT NULL COMMENT '金额',
  `amount_before` decimal(15,2) DEFAULT NULL COMMENT '操作前金额',
  `amount_after` decimal(15,2) DEFAULT NULL COMMENT '操作后金额',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP',
  `operate_ip_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP实际地址',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`member_id`,`order_no`) USING BTREE,
  UNIQUE KEY `type_2` (`type`,`member_id`,`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='资金明细';

-- ----------------------------
-- Records of ums_member_account_log
-- ----------------------------
INSERT INTO `ums_member_account_log` VALUES ('1', '6', '-1', null, '4', '13444555666', '1652338404561437', '10.00', '18778.00', '18768.00', null, '', '申请提现,等待打款', '2022-05-12 14:53:25', null, '2022-05-12 14:53:25', null);
INSERT INTO `ums_member_account_log` VALUES ('2', '6', '-1', null, '4', '13444555666', '1652338428122607', '20.00', '18768.00', '18748.00', null, '', '申请提现,等待打款', '2022-05-12 14:53:48', null, '2022-05-12 14:53:48', null);
INSERT INTO `ums_member_account_log` VALUES ('3', '6', '-1', null, '4', '13444555666', '1652338435757438', '30.00', '18748.00', '18718.00', null, '', '申请提现,等待打款', '2022-05-12 14:53:56', null, '2022-05-12 14:53:56', null);
INSERT INTO `ums_member_account_log` VALUES ('4', '6', '-1', null, '4', '13444555666', '1652338588780136', '40.00', '18718.00', '18678.00', null, '', '申请提现,等待打款', '2022-05-12 14:56:29', null, '2022-05-12 14:56:29', null);
INSERT INTO `ums_member_account_log` VALUES ('5', '6', '-1', null, '4', '13444555666', '1652338777786963', '0.00', '18678.00', '18678.00', null, '', '申请提现,等待打款', '2022-05-12 14:59:38', null, '2022-05-12 14:59:38', null);

-- ----------------------------
-- Table structure for ums_member_address
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_address`;
CREATE TABLE `ums_member_address` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `realname` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1男 2女',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机',
  `province_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省代码',
  `city_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市代码',
  `area_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区代码',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
  `area` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货地址',
  `lng` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'x坐标',
  `lat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'y坐标',
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标签',
  `status` int DEFAULT NULL COMMENT '默认状态(0:未默认 1:默认)',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='收货地址';

-- ----------------------------
-- Records of ums_member_address
-- ----------------------------
INSERT INTO `ums_member_address` VALUES ('1', '5', 'yyw', 'yuwei', null, '19859222520', '140000', '140300', '140311', '山西省', '阳泉市', '郊区', '801', null, null, null, '1', '2022-05-17 12:06:49', null, null, null);

-- ----------------------------
-- Table structure for ums_member_bank
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_bank`;
CREATE TABLE `ums_member_bank` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开户名称',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证',
  `bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行',
  `bank_sub` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支行',
  `bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行卡号',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
  `province_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
  `city_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
  `area_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `num` int DEFAULT '0' COMMENT '认证次数',
  `status` int DEFAULT NULL COMMENT '0待审核 1审核通过 2审核失败',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '原因',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `zfb_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付宝账号',
  `wx_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信账号',
  `zfb_erweima` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付宝二维码',
  `wx_erweima` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信二维码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户结算银行卡';

-- ----------------------------
-- Records of ums_member_bank
-- ----------------------------
INSERT INTO `ums_member_bank` VALUES ('2', '4', '13444555666', '杨大大', null, '支付宝', null, null, null, null, null, null, null, null, '0', '1', null, '2022-05-12 11:56:45', null, '2022-05-12 11:58:03', null, '13444555666@qq.com', null, null, null);
INSERT INTO `ums_member_bank` VALUES ('3', '4', '13444555666', '杨小小', null, '微信', null, null, null, null, null, null, null, null, '0', '1', null, '2022-05-12 11:57:15', null, '2022-05-12 11:57:55', null, null, null, null, 'https://qiniu.chengyuwb.com/1652327833580.png');
INSERT INTO `ums_member_bank` VALUES ('4', '4', '13444555666', '杨大大', null, '中国农业银行', null, '62284899900131231231', null, null, null, null, null, null, '0', '1', null, '2022-05-12 11:57:29', null, '2022-05-12 11:57:46', null, null, null, null, null);

-- ----------------------------
-- Table structure for ums_member_collection_goods
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_collection_goods`;
CREATE TABLE `ums_member_collection_goods` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '单价',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`,`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品收藏表';

-- ----------------------------
-- Records of ums_member_collection_goods
-- ----------------------------
INSERT INTO `ums_member_collection_goods` VALUES ('2', '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:14:58', null, '2022-05-17 17:14:58', null);

-- ----------------------------
-- Table structure for ums_member_collection_shop
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_collection_shop`;
CREATE TABLE `ums_member_collection_shop` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `shop_id` int DEFAULT NULL COMMENT '店铺ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺名称',
  `logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'logo',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`,`shop_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺收藏表';

-- ----------------------------
-- Records of ums_member_collection_shop
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_coupon
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_coupon`;
CREATE TABLE `ums_member_coupon` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `shop_id` int DEFAULT NULL COMMENT '来自商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来自商家名称',
  `coupon_config_id` int DEFAULT NULL COMMENT '优惠券配置ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券名称',
  `type` int DEFAULT NULL COMMENT '1满减券>>2折扣券',
  `use_type` int DEFAULT NULL COMMENT '可用范围>>1全部商品>>2指定商品>>3指定分类',
  `use_goods_ids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '可使用商品',
  `use_goods_cate_ids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '可使用商品类别',
  `amount` decimal(18,2) DEFAULT '0.00' COMMENT '金额/折扣',
  `full_amount` decimal(18,2) DEFAULT NULL COMMENT '满减金额',
  `status` int DEFAULT NULL COMMENT '状态(0:未使用 1:已使用 2:已过期)',
  `validity_begin_time` datetime DEFAULT NULL COMMENT '有效开始时间',
  `validity_end_time` datetime DEFAULT NULL COMMENT '有效结束时间',
  `rules` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '使用规则',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='优惠券';

-- ----------------------------
-- Records of ums_member_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_group
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_group`;
CREATE TABLE `ums_member_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT '1' COMMENT '1买家组别>>2卖家组别',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组别名称',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'icon',
  `default_status` int DEFAULT '0' COMMENT '0不默认>>1默认',
  `level` int DEFAULT '0' COMMENT '级别',
  `up_point` decimal(18,2) DEFAULT '0.00' COMMENT '升级所需积分',
  `init_amount` decimal(18,2) DEFAULT '0.00' COMMENT '初始金额',
  `init_mission` decimal(18,2) DEFAULT '0.00' COMMENT '初始佣金',
  `init_point` decimal(18,2) DEFAULT '0.00' COMMENT '初始积分',
  `sort` int DEFAULT '0' COMMENT '排序',
  `status` int DEFAULT '0' COMMENT '0关闭>>1开启',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='会员组别';

-- ----------------------------
-- Records of ums_member_group
-- ----------------------------
INSERT INTO `ums_member_group` VALUES ('2', '1', '默认组别', '', '1', '0', '0.00', '0.00', '0.00', '0.00', '1', '1', null, '', '2022-04-15 14:56:05', '');
INSERT INTO `ums_member_group` VALUES ('6', '2', '默认卖家', null, '1', '1', '0.00', '0.00', '0.00', '0.00', '1', '1', null, null, '2022-05-10 17:02:03', null);

-- ----------------------------
-- Table structure for ums_member_imeid
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_imeid`;
CREATE TABLE `ums_member_imeid` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  `imeid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备ID',
  `phone_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机型号',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `imeid` (`imeid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 COMMENT='登录设备表';

-- ----------------------------
-- Records of ums_member_imeid
-- ----------------------------
INSERT INTO `ums_member_imeid` VALUES ('3', '914', '13331394141', '2489f43a28479085', 'ELE-AL00', '2021-09-24 08:09:22', null);
INSERT INTO `ums_member_imeid` VALUES ('4', '905', '19859222512', '869385092819279,869385092819279', 'G6+', '2021-09-24 23:02:22', null);
INSERT INTO `ums_member_imeid` VALUES ('5', '905', '19859222512', 'cb5afa8cb3f2582f', 'ELS-AN00', '2021-10-17 00:55:05', null);
INSERT INTO `ums_member_imeid` VALUES ('6', '905', '19859222512', '735db36a08657928', 'JAD-AL50', '2021-10-18 23:13:24', null);
INSERT INTO `ums_member_imeid` VALUES ('7', '893', '13666011848', '861951044685479,861951044782771', 'INE-AL00', '2021-10-22 11:16:15', null);
INSERT INTO `ums_member_imeid` VALUES ('8', '905', '19859222512', 'd0a0f078d4faeb86', 'ANG-AN00', '2021-10-23 17:29:56', null);
INSERT INTO `ums_member_imeid` VALUES ('9', '905', '19859222512', '869994036702417,869994036702425', 'Lenovo L78011', '2021-11-08 19:51:30', null);
INSERT INTO `ums_member_imeid` VALUES ('10', '905', '19859222512', 'C0597117-D665-4075-9EFD-B28E1FE7B6C1', 'iPhone11', '2021-11-10 10:38:20', null);
INSERT INTO `ums_member_imeid` VALUES ('11', '893', '13666011848', '8b37afdcc6501d75', 'PCT-AL10', '2021-11-19 11:43:02', null);
INSERT INTO `ums_member_imeid` VALUES ('12', '905', '19859222512', '171e9feebb7f6c2121beb58244ae5aa699d3eb6d6f17c18dd15e329847ba68b1', 'SM-N9860', '2021-11-20 11:24:40', null);
INSERT INTO `ums_member_imeid` VALUES ('13', '905', '19859222512', '871c42c5aa556309', 'ELS-AN00', '2021-11-20 14:34:27', null);
INSERT INTO `ums_member_imeid` VALUES ('14', '905', '19859222512', 'c7422b74-f14c-4784-b528-179472b61daf', 'Redmi 3S', '2021-11-22 19:16:32', null);
INSERT INTO `ums_member_imeid` VALUES ('15', '905', '19859222512', 'B378FAD6CEFE4A95BADDD64D04C5A4A4ae26945b7bb99d6759298947e0f91dff', 'PEGM00', '2021-11-30 10:47:30', null);
INSERT INTO `ums_member_imeid` VALUES ('16', '905', '19859222512', '55fc1d5d-3382-4991-bd34-0017132b036e', 'M2101K9C', '2021-12-04 10:58:27', null);
INSERT INTO `ums_member_imeid` VALUES ('17', '905', '19859222512', '20fa225a-ccb1-4f57-b19c-5b43decbd82c', 'MI 9', '2021-12-29 19:20:03', null);
INSERT INTO `ums_member_imeid` VALUES ('18', '905', '19859222512', 'fc5890a0-2278-46d3-a19e-505c18535dd7', 'Redmi K30 Pro', '2022-01-17 16:00:06', null);
INSERT INTO `ums_member_imeid` VALUES ('19', '905', '19859222512', '869096036954640,869096038954648', 'OE106', '2022-01-30 23:16:03', null);
INSERT INTO `ums_member_imeid` VALUES ('20', '905', '19859222512', 'b16899ca-1c7e-4706-953d-4f913071b7e3', '2106118C', '2022-02-17 20:51:02', null);

-- ----------------------------
-- Table structure for ums_member_invoice
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_invoice`;
CREATE TABLE `ums_member_invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL COMMENT '用户ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `type` int DEFAULT NULL COMMENT '1电子发票 2纸质发票',
  `per_type` int DEFAULT NULL COMMENT '1个人 2公司',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `company_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位名称',
  `taxpayer_num` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '纳税人识别码',
  `reg_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册地址',
  `reg_phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册电话',
  `bank` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开户银行',
  `bank_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行账户',
  `is_default` int DEFAULT '0' COMMENT '是否默认（0:否  1:是,创建的第一个默认为默认)',
  `status` int DEFAULT '1',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='发票信息';

-- ----------------------------
-- Records of ums_member_invoice
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_login_log`;
CREATE TABLE `ums_member_login_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `user_agent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览器登录类型',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb3 COMMENT='用户登录日志表';

-- ----------------------------
-- Records of ums_member_login_log
-- ----------------------------
INSERT INTO `ums_member_login_log` VALUES ('1', '2', '19859222512', '192.168.0.109', null, null, '2022-05-10 17:11:29', '19859222512');
INSERT INTO `ums_member_login_log` VALUES ('2', '3', '19859222513', '192.168.0.109', null, null, '2022-05-10 17:12:40', '19859222513');
INSERT INTO `ums_member_login_log` VALUES ('3', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:20:13', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('4', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:20:20', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('5', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:21:32', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('6', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:22:38', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('7', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:23:24', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('8', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:24:06', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('9', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:28:50', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('10', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:35:35', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('11', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:39:04', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('12', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:45:51', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('13', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:49:22', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('14', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:50:12', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('15', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:50:16', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('16', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:51:50', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('17', '1', '19859222511', '192.168.0.109', null, null, '2022-05-10 17:53:38', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('18', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 11:32:13', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('19', '2', '19859222512', '192.168.0.109', null, null, '2022-05-11 15:48:05', '19859222512');
INSERT INTO `ums_member_login_log` VALUES ('20', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 16:08:08', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('21', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 16:14:28', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('22', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 16:16:35', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('23', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 16:19:23', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('24', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 16:23:29', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('25', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 16:24:22', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('26', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 16:25:08', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('27', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 17:00:51', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('28', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 17:00:58', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('29', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 17:02:54', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('30', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 17:04:13', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('31', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 17:48:05', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('32', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 17:57:54', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('33', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 18:00:24', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('34', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 18:01:07', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('35', '1', '19859222511', '192.168.0.109', null, null, '2022-05-11 18:01:35', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('36', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 18:02:42', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('37', '4', '13444555666', '192.168.0.105', null, null, '2022-05-11 18:49:34', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('38', '4', '13444555666', '192.168.0.105', null, null, '2022-05-11 18:49:52', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('39', '4', '13444555666', '192.168.0.105', null, null, '2022-05-11 18:50:03', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('40', '1', '19859222511', '192.168.0.105', null, null, '2022-05-11 18:52:20', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('41', '4', '13444555666', '192.168.0.105', null, null, '2022-05-11 18:52:31', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('42', '4', '13444555666', '192.168.0.105', null, null, '2022-05-11 18:52:46', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('43', '4', '13444555666', '192.168.0.105', null, null, '2022-05-12 10:07:47', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('44', '4', '13444555666', '192.168.0.105', null, null, '2022-05-12 10:30:20', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('45', '4', '13444555666', '192.168.0.105', null, null, '2022-05-13 09:26:13', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('46', '4', '13444555666', '192.168.0.105', null, null, '2022-05-14 09:59:09', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('47', '4', '13444555666', '192.168.0.105', null, null, '2022-05-16 09:55:00', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('48', '4', '13444555666', '192.168.0.105', null, null, '2022-05-16 10:13:17', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('49', '1', '19859222511', '192.168.0.109', null, null, '2022-05-16 11:02:04', '19859222511');
INSERT INTO `ums_member_login_log` VALUES ('50', '5', 'yyw', '192.168.0.109', null, null, '2022-05-16 11:38:58', 'yyw');
INSERT INTO `ums_member_login_log` VALUES ('51', '4', '13444555666', '192.168.0.105', null, null, '2022-05-17 09:37:42', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('52', '4', '13444555666', '192.168.0.105', null, null, '2022-05-17 10:00:35', '13444555666');
INSERT INTO `ums_member_login_log` VALUES ('53', '5', 'yyw', '192.168.0.109', null, null, '2022-05-17 11:10:41', 'yyw');
INSERT INTO `ums_member_login_log` VALUES ('54', '5', 'yyw', '192.168.0.109', null, null, '2022-05-17 11:15:10', 'yyw');
INSERT INTO `ums_member_login_log` VALUES ('55', '5', 'yyw', '192.168.0.109', null, null, '2022-05-17 11:20:07', 'yyw');

-- ----------------------------
-- Table structure for ums_member_mission_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_mission_log`;
CREATE TABLE `ums_member_mission_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT NULL COMMENT '资金类型',
  `in_out` int DEFAULT NULL COMMENT '收支类型 1收入 -1支出',
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `amount` decimal(15,2) DEFAULT NULL COMMENT '金额',
  `amount_before` decimal(15,2) DEFAULT NULL COMMENT '操作前金额',
  `amount_after` decimal(15,2) DEFAULT NULL COMMENT '操作后金额',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP',
  `operate_ip_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP实际地址',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='发布点明细';

-- ----------------------------
-- Records of ums_member_mission_log
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_news
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_news`;
CREATE TABLE `ums_member_news` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `type` int DEFAULT NULL COMMENT '消息类型',
  `img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  `produce` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简介',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '内容',
  `turn_page` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '跳转模块',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `send_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发送状态(0:未发送 1:已发送 2:发送失败)',
  `read_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0未读  1已读',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `from_id` int DEFAULT NULL COMMENT '用户ID',
  `from_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户消息表';

-- ----------------------------
-- Records of ums_member_news
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_operation_log`;
CREATE TABLE `ums_member_operation_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作内容',
  `params` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '操作参数',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=262 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户操作日志';

-- ----------------------------
-- Records of ums_member_operation_log
-- ----------------------------
INSERT INTO `ums_member_operation_log` VALUES ('1', '1', '19859222511', '192.168.0.109', '申请入驻-提交资质', 0x5B7B2273686F70496E666F223A7B226964223A312C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A312C227265616C6E616D65223A227975776569222C22696463617264223A22333132333132333132333132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323234323238343836312E706E67222C226272616E64496D67223A22222C2270686F6E65223A223139383539323232353131222C2277656978696E223A2277656978696E303031222C2270726F64756365223A22222C226C6F676F223A22222C2261646472657373223A22222C22737461747573223A302C2261646454696D65223A224D61792031312C20323032322031323A31313A323920504D222C2275706454696D65223A224D61792031312C20323032322031323A31313A323920504D227D7D5D, '2022-05-11 12:11:29', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('2', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A312C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A312C227265616C6E616D65223A227975776569222C22696463617264223A22333132333132333132333132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323234323238343836312E706E67222C226272616E64496D67223A22222C2270686F6E65223A223139383539323232353131222C2277656978696E223A2277656978696E303031222C226E616D65223A22E5A4A9E79CBC31E58FB7222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235313230303732332E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E79CBC31E58FB7222C22737461747573223A312C2275706454696D65223A224D61792031312C203230323220323A34303A303320504D227D7D5D, '2022-05-11 14:40:03', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('3', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A312C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A312C227265616C6E616D65223A227975776569222C22696463617264223A22333132333132333132333132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323234323238343836312E706E67222C226272616E64496D67223A22222C2270686F6E65223A223139383539323232353131222C2277656978696E223A2277656978696E303031222C226E616D65223A22E5A4A9E79CBC31E58FB7222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235313230303732332E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E79CBC31E58FB7222C22737461747573223A312C2275706454696D65223A224D61792031312C203230323220333A30303A343420504D227D7D5D, '2022-05-11 15:00:45', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('4', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B22737461747573223A322C2275706454696D65223A224D61792031312C203230323220333A30303A343920504D227D7D5D, '2022-05-11 15:00:50', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('5', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B22737461747573223A322C2275706454696D65223A224D61792031312C203230323220333A30323A313020504D227D7D5D, '2022-05-11 15:02:11', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('6', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B22737461747573223A322C2275706454696D65223A224D61792031312C203230323220333A30323A323020504D227D7D5D, '2022-05-11 15:02:20', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('7', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A312C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A312C227265616C6E616D65223A227975776569222C22696463617264223A22333132333132333132333132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323234323238343836312E706E67222C226272616E64496D67223A22222C2270686F6E65223A223139383539323232353131222C2277656978696E223A2277656978696E303031222C226E616D65223A22E5A4A9E79CBC31E58FB7222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235313230303732332E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E79CBC31E58FB7222C22737461747573223A312C2275706454696D65223A224D61792031312C203230323220333A30343A333920504D227D7D5D, '2022-05-11 15:04:40', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('8', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A312C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A312C227265616C6E616D65223A227975776569222C22696463617264223A22333132333132333132333132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323234323238343836312E706E67222C226272616E64496D67223A22222C2270686F6E65223A223139383539323232353131222C2277656978696E223A2277656978696E303031222C226E616D65223A22E5A4A9E79CBC31E58FB7222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235313230303732332E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E79CBC31E58FB7222C22737461747573223A322C2275706454696D65223A224D61792031312C203230323220333A30343A343620504D227D7D5D, '2022-05-11 15:04:46', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('9', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B2274797065223A332C227265616C6E616D65223A22E5A4A9E7A9BAE4B880E58FB7222C22696463617264223A22313232313132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343235393339312E706E67222C226272616E64496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343236333132312E6A7067222C2270686F6E65223A223139383539323232353131222C2277656978696E223A22E5A4A9E7A9BAE4B880E58FB7222C226E616D65223A22E5A4A9E7A9BAE4B880E58FB7222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343237303138342E6A7067222C2270726F76696E6365223A22E6B2B3E58C97E79C81222C2270726F76696E6365436F6465223A22313330303030222C2263697479223A22E59490E5B1B1E5B882222C2263697479436F6465223A22313330323030222C2261726561223A22E8B7AFE58C97E58CBA222C2261726561436F6465223A22313330323033222C2261646472657373223A22E5A4A9E7A9BAE4B880E58FB7222C22737461747573223A312C2275706454696D65223A224D61792031312C203230323220333A33313A313620504D227D7D5D, '2022-05-11 15:31:16', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('10', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B2274797065223A332C227265616C6E616D65223A22E5A4A9E5AEABE4B880E58FB7222C22696463617264223A22313232313231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343335363634382E706E67222C226272616E64496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343336303738332E706E67222C2270686F6E65223A223139383539323232353131222C2277656978696E223A22E5A4A9E5AEABE4B880E58FB7222C226E616D65223A22E5A4A9E5AEABE4B880E58FB7222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343336393330302E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E5AEABE4B880E58FB7222C22737461747573223A312C2275706454696D65223A224D61792031312C203230323220333A33333A303620504D227D7D5D, '2022-05-11 15:33:07', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('11', '1', '19859222511', '192.168.0.109', '申请入驻-提交资质', 0x5B7B2273686F70496E666F223A7B226964223A322C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A332C227265616C6E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22696463617264223A223131323231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539313432332E6A7067222C226272616E64496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539353034332E6A7067222C2270686F6E65223A223139383539323232353131222C2277656978696E223A22E5A4A9E5AEABE5928CE4B880E9AB98222C226E616D65223A22222C2270726F64756365223A22222C226C6F676F223A22222C2270726F76696E6365223A22222C2270726F76696E6365436F6465223A22222C2263697479223A22222C2263697479436F6465223A22222C2261726561223A22222C2261726561436F6465223A22222C2261646472657373223A22222C22737461747573223A302C2261646454696D65223A224D61792031312C203230323220333A33363A343120504D222C2275706454696D65223A224D61792031312C203230323220333A33363A343120504D227D7D5D, '2022-05-11 15:36:41', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('12', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A322C2274797065223A332C227265616C6E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22696463617264223A223131323231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539313432332E6A7067222C226272616E64496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539353034332E6A7067222C2270686F6E65223A223139383539323232353131222C2277656978696E223A22E5A4A9E5AEABE5928CE4B880E9AB98222C226E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343631303032302E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22737461747573223A312C2275706454696D65223A224D61792031312C203230323220333A33363A353520504D227D7D5D, '2022-05-11 15:36:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('13', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A322C2274797065223A332C227265616C6E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22696463617264223A223131323231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539313432332E6A7067222C226272616E64496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539353034332E6A7067222C2270686F6E65223A223139383539323232353131222C2277656978696E223A22E5A4A9E5AEABE5928CE4B880E9AB98222C226E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343631303032302E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22737461747573223A322C2275706454696D65223A224D61792031312C203230323220333A33363A353820504D227D7D5D, '2022-05-11 15:36:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('14', '1', '19859222511', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A322C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A332C227265616C6E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22696463617264223A223131323231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539313432332E6A7067222C226272616E64496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539353034332E6A7067222C2270686F6E65223A223139383539323232353131222C2277656978696E223A22E5A4A9E5AEABE5928CE4B880E9AB98222C226E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343631303032302E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22737461747573223A312C22726561736F6E223A22E4B88AE4BCA0E59BBEE78987E8BF9DE8A784222C2272656D61726B223A22E5A4A9E5AEABE5928CE4B880E9AB98222C2275706454696D65223A224D61792031312C203230323220333A33393A333920504D227D7D5D, '2022-05-11 15:39:39', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('15', '1', '19859222511', '192.168.0.109', '申请入驻-提交审核', 0x5B7B2273686F70496E666F223A7B226964223A322C226D656D6265724964223A312C226D656D6265724E616D65223A223139383539323232353131222C2274797065223A332C227265616C6E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22696463617264223A223131323231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539313432332E6A7067222C226272616E64496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343539353034332E6A7067222C2270686F6E65223A223139383539323232353131222C2277656978696E223A22E5A4A9E5AEABE5928CE4B880E9AB98222C226E616D65223A22E5A4A9E5AEABE5928CE4B880E9AB98222C2270726F64756365223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235343631303032302E6A7067222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E4B89CE58CBA222C2261726561436F6465223A22313230313032222C2261646472657373223A22E5A4A9E5AEABE5928CE4B880E9AB98222C22737461747573223A322C22726561736F6E223A22E4B88AE4BCA0E59BBEE78987E8BF9DE8A784222C2272656D61726B223A22E5A4A9E5AEABE5928CE4B880E9AB98222C2275706454696D65223A224D61792031312C203230323220333A33393A353520504D227D7D5D, '2022-05-11 15:39:55', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('16', '2', '19859222512', '192.168.0.109', '申请入驻-提交资质', 0x5B7B2273686F70496E666F223A7B226964223A332C226D656D6265724964223A322C226D656D6265724E616D65223A223139383539323232353132222C2274797065223A312C227265616C6E616D65223A22E5A4A9E5AEABE4B8AAE4BABA222C22696463617264223A223132333132333132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235353330303037312E706E67222C226272616E64496D67223A22222C2270686F6E65223A223139383539323232353132222C2277656978696E223A22E5A4A9E5AEABE4B8AAE4BABA222C226E616D65223A22222C2270726F64756365223A22222C226C6F676F223A22222C2270726F76696E6365223A22222C2270726F76696E6365436F6465223A22222C2263697479223A22222C2263697479436F6465223A22222C2261726561223A22222C2261726561436F6465223A22222C2261646472657373223A22222C22737461747573223A302C2272656D61726B223A22222C2261646454696D65223A224D61792031312C203230323220333A34383A323120504D222C2275706454696D65223A224D61792031312C203230323220333A34383A323120504D227D7D5D, '2022-05-11 15:48:21', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('17', '2', '19859222512', '192.168.0.109', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A332C2274797065223A312C227265616C6E616D65223A22E5A4A9E5AEABE4B8AAE4BABA222C22696463617264223A223132333132333132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235353330303037312E706E67222C226272616E64496D67223A22222C2270686F6E65223A223139383539323232353132222C2277656978696E223A22E5A4A9E5AEABE4B8AAE4BABA222C226E616D65223A22E5A4A9E5AEABE4B8AAE4BABA222C2270726F64756365223A225C7530303363705C7530303365E5A4A9E5AEABE4B8AAE4BABA5C75303033632F705C7530303365222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235353330383932382E6A7067222C2270726F76696E6365223A22E6B2B3E58C97E79C81222C2270726F76696E6365436F6465223A22313330303030222C2263697479223A22E59490E5B1B1E5B882222C2263697479436F6465223A22313330323030222C2261726561223A22E8B7AFE58C97E58CBA222C2261726561436F6465223A22313330323033222C2261646472657373223A22E5A4A9E5AEABE4B8AAE4BABA222C22737461747573223A312C2272656D61726B223A22E5A4A9E5AEABE4B8AAE4BABA222C2275706454696D65223A224D61792031312C203230323220333A34383A333520504D227D7D5D, '2022-05-11 15:48:35', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('18', '2', '19859222512', '192.168.0.109', '申请入驻-提交审核', 0x5B7B2273686F70496E666F223A7B226964223A332C2274797065223A312C227265616C6E616D65223A22E5A4A9E5AEABE4B8AAE4BABA222C22696463617264223A223132333132333132222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235353330303037312E706E67222C226272616E64496D67223A22222C2270686F6E65223A223139383539323232353132222C2277656978696E223A22E5A4A9E5AEABE4B8AAE4BABA222C226E616D65223A22E5A4A9E5AEABE4B8AAE4BABA222C2270726F64756365223A225C7530303363705C7530303365E5A4A9E5AEABE4B8AAE4BABA5C75303033632F705C7530303365222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323235353330383932382E6A7067222C2270726F76696E6365223A22E6B2B3E58C97E79C81222C2270726F76696E6365436F6465223A22313330303030222C2263697479223A22E59490E5B1B1E5B882222C2263697479436F6465223A22313330323030222C2261726561223A22E8B7AFE58C97E58CBA222C2261726561436F6465223A22313330323033222C2261646472657373223A22E5A4A9E5AEABE4B8AAE4BABA222C22737461747573223A322C2272656D61726B223A22E5A4A9E5AEABE4B8AAE4BABA222C2275706454696D65223A224D61792031312C203230323220333A34383A333820504D227D7D5D, '2022-05-11 15:48:38', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('19', '1', '19859222511', '192.168.0.109', '设置登录密码', 0x5B7B2270617373776F7264223A22313233343536227D2C7B22636F6D6669726D50617373776F7264223A22313233343536227D5D, '2022-05-11 17:45:26', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('20', '1', '19859222511', '192.168.0.109', '设置登录密码', 0x5B7B2270617373776F7264223A22313233343536227D2C7B22636F6D6669726D50617373776F7264223A22313233343536227D5D, '2022-05-11 17:47:50', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('21', '4', '13444555666', '192.168.0.105', '申请入驻-提交资质', 0x5B7B2273686F70496E666F223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A2231222C226E616D65223A22222C2270726F64756365223A22222C226C6F676F223A22222C2270726F76696E6365223A22222C2270726F76696E6365436F6465223A22222C2263697479223A22222C2263697479436F6465223A22222C2261726561223A22222C2261726561436F6465223A22222C2261646472657373223A22222C22737461747573223A302C2272656D61726B223A22222C2261646454696D65223A224D61792031322C20323032322031303A31303A333020414D222C2275706454696D65223A224D61792031322C20323032322031303A31303A333020414D227D7D5D, '2022-05-12 10:10:31', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('22', '4', '13444555666', '192.168.0.105', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A342C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A2231222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A312C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A31303A343620414D227D7D5D, '2022-05-12 10:10:46', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('23', '4', '13444555666', '192.168.0.105', '申请入驻-提交资质', 0x5B7B2273686F70496E666F223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A2231222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A302C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A31313A303120414D227D7D5D, '2022-05-12 10:11:02', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('24', '4', '13444555666', '192.168.0.105', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A342C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A2231222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A312C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A31373A303820414D227D7D5D, '2022-05-12 10:17:08', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('25', '4', '13444555666', '192.168.0.105', '申请入驻-提交资质', 0x5B7B2273686F70496E666F223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A2231222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A302C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A32343A333020414D227D7D5D, '2022-05-12 10:24:31', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('26', '4', '13444555666', '192.168.0.105', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A2231222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A312C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A32343A333220414D227D7D5D, '2022-05-12 10:24:32', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('27', '4', '13444555666', '192.168.0.105', '申请入驻-提交审核', 0x5B7B2273686F70496E666F223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A2231222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A322C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A32343A333320414D227D7D5D, '2022-05-12 10:24:34', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('28', '4', '13444555666', '192.168.0.105', '申请入驻-提交资质', 0x5B7B2273686F70496E666F223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A22313233323133313233222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A302C22726561736F6E223A22E4B88AE4BCA0E59BBEE78987E4B88DE6B885E699B0222C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A33313A353020414D227D7D5D, '2022-05-12 10:31:50', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('29', '4', '13444555666', '192.168.0.105', '申请入驻-完善店铺', 0x5B7B2273686F70496E666F223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A22313233323133313233222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A312C22726561736F6E223A22E4B88AE4BCA0E59BBEE78987E4B88DE6B885E699B0222C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A33313A353120414D227D7D5D, '2022-05-12 10:31:52', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('30', '4', '13444555666', '192.168.0.105', '申请入驻-提交审核', 0x5B7B2273686F70496E666F223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C227265616C6E616D65223A2231222C22696463617264223A2231222C2263617264496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313432383539352E706E67222C226272616E64496D67223A22222C2277656978696E223A22313233323133313233222C226E616D65223A2232222C2270726F64756365223A223232222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C22737461747573223A322C22726561736F6E223A22E4B88AE4BCA0E59BBEE78987E4B88DE6B885E699B0222C2272656D61726B223A2232222C2275706454696D65223A224D61792031322C20323032322031303A33313A353320414D227D7D5D, '2022-05-12 10:31:54', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('31', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A312C2275706454696D65223A224D61792031322C20323032322031313A30333A303620414D227D7D5D, '2022-05-12 11:03:07', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('32', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A312C2264656C697665727946656554797065223A322C2275706454696D65223A224D61792031322C20323032322031313A30333A303820414D227D7D5D, '2022-05-12 11:03:09', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('33', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A312C2264656C697665727946656554797065223A312C2275706454696D65223A224D61792031322C20323032322031313A30333A313020414D227D7D5D, '2022-05-12 11:03:10', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('34', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A322C2264656C697665727946656554797065223A312C2275706454696D65223A224D61792031322C20323032322031313A30333A313720414D227D7D5D, '2022-05-12 11:03:17', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('35', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A322C2264656C697665727946656554797065223A332C2275706454696D65223A224D61792031322C20323032322031313A30333A343220414D227D7D5D, '2022-05-12 11:03:43', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('36', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A322C2264656C697665727946656554797065223A332C226B645072696E74223A312C226B6444656C6976657279223A312C226B64536563726574223A2231222C226B644B6579223A2231222C226B644163636F756E74223A2231222C226B6450617373776F7264223A2231222C2275706454696D65223A224D61792031322C20323032322031313A30343A323120414D227D7D5D, '2022-05-12 11:04:22', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('37', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A322C2264656C697665727946656554797065223A332C226B645072696E74223A312C226B6444656C6976657279223A312C226B64536563726574223A2231222C226B644B6579223A2231222C226B644163636F756E74223A2231222C226B6450617373776F7264223A2231222C226B6445717569706D656E744964223A2232222C226B6454656D706C6174654964223A2232222C2275706454696D65223A224D61792031322C20323032322031313A30343A323820414D227D7D5D, '2022-05-12 11:04:29', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('38', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A322C2264656C697665727946656554797065223A332C226B645072696E74223A312C226B6444656C6976657279223A312C226B64536563726574223A2231222C226B644B6579223A2231222C226B644163636F756E74223A2231222C226B6450617373776F7264223A2231222C226B6445717569706D656E744964223A2232222C226B6454656D706C6174654964223A2232222C2273656E644E616D65223A22E883A1E58588E7949F222C2273656E6450686F6E65223A223133343132333435363738222C2273656E6441646472657373223A22E5B9BFE4B89CE79C81E5B9BFE5B79EE5B882E6B5B7E78FA0E58CBAE696B0E6B8AFE8A5BFE8B7AF31343530E58FB7222C2275706454696D65223A224D61792031322C20323032322031313A30343A343420414D227D7D5D, '2022-05-12 11:04:44', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('39', '4', '13444555666', '192.168.0.105', '修改店铺配置', 0x5B7B22636F6E666967223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A2232222C22636174655374796C65223A322C2264656C697665727946656554797065223A332C226B645072696E74223A312C226B6444656C6976657279223A312C226B64536563726574223A2231222C226B644B6579223A2231222C226B644163636F756E74223A2231222C226B6450617373776F7264223A2231222C226B6445717569706D656E744964223A2232222C226B6454656D706C6174654964223A2232222C2273656E644E616D65223A22E883A1E58588E7949F222C2273656E6450686F6E65223A223133343132333435363738222C2273656E6441646472657373223A22E5B9BFE4B89CE79C81E5B9BFE5B79EE5B882E6B5B7E78FA0E58CBAE696B0E6B8AFE8A5BFE8B7AF31343530E58FB7222C2272657475726E4E616D65223A22E883A1E58588E7949F222C2272657475726E50686F6E65223A223133343132333435363738222C2272657475726E41646472657373223A22E5B9BFE4B89CE79C81E5B9BFE5B79EE5B882E6B5B7E78FA0E58CBAE696B0E6B8AFE8A5BFE8B7AF31343530E58FB7222C2275706454696D65223A224D61792031322C20323032322031313A30343A353820414D227D7D5D, '2022-05-12 11:04:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('40', '4', '13444555666', '192.168.0.105', '修改银行认证', 0x5B7B226D656D62657242616E6B223A7B226964223A312C226163636F756E744E616D65223A22E69DA8E5A4A7E5A4A7222C2262616E6B223A22E694AFE4BB98E5AE9D222C22737461747573223A312C2275706454696D65223A224D61792031322C20323032322031313A35343A353320414D222C227A66624163636F756E74223A223133343434353535363636227D7D5D, '2022-05-12 11:54:53', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('41', '4', '13444555666', '192.168.0.105', '修改银行认证', 0x5B7B226D656D62657242616E6B223A7B226964223A322C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C226163636F756E744E616D65223A22E69DA8E5A4A7E5A4A7222C2262616E6B223A22E694AFE4BB98E5AE9D222C22737461747573223A312C2261646454696D65223A224D61792031322C20323032322031313A35363A343520414D222C2275706454696D65223A224D61792031322C20323032322031313A35363A343520414D222C227A66624163636F756E74223A223133343434353535363636227D7D5D, '2022-05-12 11:56:45', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('42', '4', '13444555666', '192.168.0.105', '修改银行认证', 0x5B7B226D656D62657242616E6B223A7B226964223A332C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C226163636F756E744E616D65223A22E69DA8E5A4A7E5A4A7222C2262616E6B223A22E5BEAEE4BFA1222C22737461747573223A312C2261646454696D65223A224D61792031322C20323032322031313A35373A313520414D222C2275706454696D65223A224D61792031322C20323032322031313A35373A313520414D222C22777845727765696D61223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332373833333538302E706E67227D7D5D, '2022-05-12 11:57:15', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('43', '4', '13444555666', '192.168.0.105', '修改银行认证', 0x5B7B226D656D62657242616E6B223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C226163636F756E744E616D65223A22E69DA8E5A4A7E5A4A7222C2262616E6B223A22E4B8ADE59BBDE5B7A5E59586E993B6E8A18C222C2262616E6B4163636F756E74223A2236323238343839393930303133313233313233222C22737461747573223A312C2261646454696D65223A224D61792031322C20323032322031313A35373A323920414D222C2275706454696D65223A224D61792031322C20323032322031313A35373A323920414D227D7D5D, '2022-05-12 11:57:30', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('44', '4', '13444555666', '192.168.0.105', '修改银行认证', 0x5B7B226D656D62657242616E6B223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C226163636F756E744E616D65223A22E69DA8E5A4A7E5A4A7222C2262616E6B223A22E4B8ADE59BBDE5869CE4B89AE993B6E8A18C222C2262616E6B4163636F756E74223A223632323834383939393030313331323331323331222C226E756D223A302C22737461747573223A312C2275706454696D65223A224D61792031322C20323032322031313A35373A343520414D227D7D5D, '2022-05-12 11:57:46', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('45', '4', '13444555666', '192.168.0.105', '修改银行认证', 0x5B7B226D656D62657242616E6B223A7B226964223A332C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C226163636F756E744E616D65223A22E69DA8E5B08FE5B08F222C2262616E6B223A22E5BEAEE4BFA1222C226E756D223A302C22737461747573223A312C2275706454696D65223A224D61792031322C20323032322031313A35373A353520414D222C22777845727765696D61223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332373833333538302E706E67227D7D5D, '2022-05-12 11:57:55', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('46', '4', '13444555666', '192.168.0.105', '修改银行认证', 0x5B7B226D656D62657242616E6B223A7B226964223A322C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C226163636F756E744E616D65223A22E69DA8E5A4A7E5A4A7222C2262616E6B223A22E694AFE4BB98E5AE9D222C226E756D223A302C22737461747573223A312C2275706454696D65223A224D61792031322C20323032322031313A35383A303220414D222C227A66624163636F756E74223A2231333434343535353636364071712E636F6D227D7D5D, '2022-05-12 11:58:03', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('47', '4', '13444555666', '192.168.0.105', '修改银行认证', 0x5B7B226D656D62657242616E6B223A7B226964223A352C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C226163636F756E744E616D65223A223132222C2262616E6B223A22E4B8ADE59BBDE5B7A5E59586E993B6E8A18C222C2262616E6B4163636F756E74223A223131222C22737461747573223A312C2261646454696D65223A224D61792031322C20323032322031313A35383A303920414D222C2275706454696D65223A224D61792031322C20323032322031313A35383A303920414D227D7D5D, '2022-05-12 11:58:09', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('48', '4', '13444555666', '192.168.0.105', '删除银行认证', 0x5B7B22696473223A2235227D5D, '2022-05-12 11:58:14', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('49', '4', '13444555666', '192.168.0.105', '编辑分类', 0x5B7B2263617465223A7B226964223A312C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E59FBAE7A180E7B4A0E69D90222C2261646454696D65223A224D61792031322C203230323220333A35323A353720504D222C2275706454696D65223A224D61792031322C203230323220333A35323A353720504D227D7D5D, '2022-05-12 15:52:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('50', '4', '13444555666', '192.168.0.105', '上传素材', 0x5B7B22736F7572636573223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67227D2C7B22636174654964223A317D2C7B2274797065223A2231227D5D, '2022-05-12 15:53:54', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('51', '4', '13444555666', '192.168.0.105', '编辑分类', 0x5B7B2263617465223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22222C2261646454696D65223A224D61792031322C203230323220333A35343A333620504D222C2275706454696D65223A224D61792031322C203230323220333A35343A333620504D227D7D5D, '2022-05-12 15:54:36', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('52', '4', '13444555666', '192.168.0.105', '删除分类', 0x5B7B22696473223A2232227D5D, '2022-05-12 15:54:39', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('53', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226E616D65223A22E8A1A3E69C8D222C22736F7274223A317D7D5D, '2022-05-12 16:03:25', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('54', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226E616D65223A22E8A3A4E5AD90222C22736F7274223A327D7D5D, '2022-05-12 16:03:35', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('55', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226E616D65223A22E5A4A7E8A1A3222C22706964223A312C22736F7274223A317D7D5D, '2022-05-12 16:03:44', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('56', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226E616D65223A22E9A38EE8A1A3222C22706964223A332C22736F7274223A317D7D5D, '2022-05-12 16:03:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('57', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A342C22736F7274223A322C2275706454696D65223A224D61792031322C203230323220343A30333A353820504D227D7D5D, '2022-05-12 16:03:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('58', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A332C22736F7274223A322C2275706454696D65223A224D61792031322C203230323220343A30343A303620504D227D7D5D, '2022-05-12 16:04:07', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('59', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A312C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E8A1A3E69C8D222C226C6576656C223A312C22706964223A302C22736F7274223A312C22737461747573223A312C2275706454696D65223A224D61792031322C203230323220343A30343A313020504D227D7D5D, '2022-05-12 16:04:10', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('60', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A312C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E8A1A3E69C8D222C226C6576656C223A312C22706964223A302C22736F7274223A322C22737461747573223A312C2275706454696D65223A224D61792031322C203230323220343A30343A313420504D227D7D5D, '2022-05-12 16:04:15', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('61', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226E616D65223A22E9A38EE8A1A3222C22706964223A312C22736F7274223A317D7D5D, '2022-05-12 16:05:16', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('62', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226E616D65223A22E8939DE889B2E9A38EE8A1A3222C22706964223A352C22736F7274223A327D7D5D, '2022-05-12 16:05:27', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('63', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A362C22736F7274223A312C2275706454696D65223A224D61792031322C203230323220343A30353A323820504D227D7D5D, '2022-05-12 16:05:29', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('64', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A352C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E9A38EE8A1A3222C226C6576656C223A322C22706964223A312C22736F7274223A312C22737461747573223A312C2275706454696D65223A224D61792031322C203230323220343A30363A343220504D227D7D5D, '2022-05-12 16:06:43', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('65', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A352C22736F7274223A322C2275706454696D65223A224D61792031322C203230323220343A30363A343420504D227D7D5D, '2022-05-12 16:06:44', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('66', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A352C22736F7274223A312C2275706454696D65223A224D61792031322C203230323220343A30363A343620504D227D7D5D, '2022-05-12 16:06:46', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('67', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226E616D65223A22E79DA1E8A1A3222C22706964223A352C22736F7274223A317D7D5D, '2022-05-12 16:06:57', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('68', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE6A8A1E69DBF222C2274797065223A322C2276616C75746554797065223A312C2264656C697665727954696D65223A2238E5B08FE697B6E58685227D7D2C7B7D5D, '2022-05-12 16:56:42', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('69', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE6A8A1E69DBF222C2274797065223A322C2276616C75746554797065223A312C2264656C697665727954696D65223A223230E5B08FE697B6E58685227D7D2C7B7D5D, '2022-05-12 16:59:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('70', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE6A8A1E69DBF222C2274797065223A322C2276616C75746554797065223A312C2264656C697665727954696D65223A223230E5B08FE697B6E58685227D7D2C7B7D5D, '2022-05-12 17:00:41', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('71', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE6A8A1E69DBF222C2274797065223A322C2276616C75746554797065223A312C2264656C697665727954696D65223A223230E5B08FE697B6E58685227D7D2C7B7D5D, '2022-05-12 17:00:52', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('72', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE6A8A1E69DBF222C2274797065223A322C2276616C75746554797065223A312C2264656C697665727954696D65223A2231E5A4A9E58685227D7D2C7B7D5D, '2022-05-12 17:01:42', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('73', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE6A8A1E69DBF222C2274797065223A322C2276616C75746554797065223A312C2264656C697665727954696D65223A223136E5B08FE697B6E58685227D7D2C7B7D5D, '2022-05-12 17:03:06', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('74', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A342C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A2235E58583E8BF90E8B4B9222C2274797065223A312C2276616C75746554797065223A312C2264656661756C7446697273744E756D223A312C2264656661756C7446697273745072696365223A352C2264656661756C745365636F6E644E756D223A312C2264656661756C745365636F6E645072696365223A302C2264656C697665727954696D65223A223230E5B08FE697B6E58685227D7D2C7B7D5D, '2022-05-12 17:04:05', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('75', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A352C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE59CB0E58CBAE99990E588B6222C2274797065223A312C2276616C75746554797065223A312C2264656661756C7446697273744E756D223A312C2264656661756C7446697273745072696365223A302C2264656661756C745365636F6E644E756D223A312C2264656661756C745365636F6E645072696365223A302C2264656C697665727954696D65223A2231E5A4A9E58685227D7D2C7B7D5D, '2022-05-12 17:04:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('76', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A352C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE59CB0E58CBAE99990E588B6222C2274797065223A312C2276616C75746554797065223A312C2264656661756C7446697273744E756D223A312C2264656661756C7446697273745072696365223A302C2264656661756C745365636F6E644E756D223A312C2264656661756C745365636F6E645072696365223A302C2264656C697665727954696D65223A2231E5A4A9E58685227D7D2C7B22617265614A736F6E223A225B7B5C22616464726573735C223A5B5C223335303030305C222C5C225C225D2C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A312C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A312C5C22636F756E7472795C223A5C226368696E615C222C5C2270726F76696E63655C223A5C223335303030305C222C5C22636974795C223A5C225C227D5D227D5D, '2022-05-12 17:06:37', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('77', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A352C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE59CB0E58CBAE99990E588B6222C2274797065223A312C2276616C75746554797065223A312C2264656661756C7446697273744E756D223A312C2264656661756C7446697273745072696365223A302C2264656661756C745365636F6E644E756D223A312C2264656661756C745365636F6E645072696365223A302C2264656C697665727954696D65223A2231E5A4A9E58685227D7D2C7B22617265614A736F6E223A225B7B5C2269645C223A32312C5C2274656D706C61746549645C223A352C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A312C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A312C5C2270726F76696E63655C223A5C223335303030305C222C5C22636974795C223A5C225C222C5C22616464726573735C223A5B5C223335303030305C222C5C225C225D2C5C22636F756E7472795C223A5C226368696E615C227D2C7B5C22616464726573735C223A5B5C223435303030305C222C5C225C225D2C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A31302C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A31302C5C22636F756E7472795C223A5C226368696E615C222C5C2270726F76696E63655C223A5C223435303030305C222C5C22636974795C223A5C225C227D5D227D5D, '2022-05-12 17:06:54', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('78', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A352C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE59CB0E58CBAE99990E588B6222C2274797065223A312C2276616C75746554797065223A312C2264656661756C7446697273744E756D223A312C2264656661756C7446697273745072696365223A302C2264656661756C745365636F6E644E756D223A312C2264656661756C745365636F6E645072696365223A302C2264656C697665727954696D65223A2231E5A4A9E58685227D7D2C7B22617265614A736F6E223A225B7B5C2269645C223A32322C5C2274656D706C61746549645C223A352C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A312C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A312C5C2270726F76696E63655C223A5C223335303030305C222C5C22636974795C223A5C225C222C5C22616464726573735C223A5B5C223335303030305C222C5C225C225D2C5C22636F756E7472795C223A5C226368696E615C227D2C7B5C2269645C223A32332C5C2274656D706C61746549645C223A352C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A31302C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A31302C5C2270726F76696E63655C223A5C223435303030305C222C5C22636974795C223A5C225C222C5C22616464726573735C223A5B5C223435303030305C222C5C225C225D2C5C22636F756E7472795C223A5C226368696E615C227D5D227D5D, '2022-05-12 17:07:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('79', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A312C22736F7274223A312C2275706454696D65223A224D61792031322C203230323220353A30373A343120504D227D7D5D, '2022-05-12 17:07:41', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('80', '4', '13444555666', '192.168.0.105', '编辑店铺类目', 0x5B7B2263617465223A7B226964223A312C22736F7274223A322C2275706454696D65223A224D61792031322C203230323220353A30373A343220504D227D7D5D, '2022-05-12 17:07:42', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('81', '4', '13444555666', '192.168.0.105', '删除店铺运费模板', 0x5B7B22696473223A2234227D5D, '2022-05-12 17:08:57', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('82', '4', '13444555666', '192.168.0.105', '修改店铺资料', 0x5B7B2273686F70223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C226C6576656C223A302C226E616D65223A224C454B53484F50E69797E888B0E5BA97222C226B6579776F7264223A2232222C2270726F64756365223A223232222C2273657276696365223A22222C22736572766963654E616D65223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5A4A9E6B4A5E5B882222C2270726F76696E6365436F6465223A22313230303030222C2263697479223A22E5B882E8BE96E58CBA222C2263697479436F6465223A22313230313030222C2261726561223A22E6B2B3E8A5BFE58CBA222C2261726561436F6465223A22313230313033222C2261646472657373223A2232222C226C696B654E756D223A302C22676F6F647353746172223A302C2273656C6C657253746172223A302C226C6F67697374696353746172223A302C22776569676874223A302C22736F7274223A393939392C22737461747573223A312C2272656D61726B223A2261736461736461736461222C2273656C66537461747573223A302C226C697665537461747573223A302C226164644279223A22222C2275706454696D65223A224D61792031322C203230323220353A32353A303120504D222C227570644279223A22227D7D5D, '2022-05-12 17:25:01', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('83', '4', '13444555666', '192.168.0.105', '修改店铺资料', 0x5B7B2273686F70223A7B226964223A342C226D656D6265724964223A342C226D656D6265724E616D65223A223133343434353535363636222C2274797065223A312C226C6576656C223A302C226E616D65223A224C454B53484F50E69797E888B0E5BA97222C226B6579776F7264223A2232222C2270726F64756365223A223232222C2273657276696365223A22322C332C34222C22736572766963654E616D65223A22222C226C6F676F223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323332313433363430332E706E67222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2270726F76696E6365436F6465223A22313430303030222C2263697479223A22E995BFE6B2BBE5B882222C2263697479436F6465223A22313430343030222C2261726561223A22E4B88AE5859AE58CBA222C2261726561436F6465223A22313430343034222C2261646472657373223A2232222C226C696B654E756D223A302C22676F6F647353746172223A302C2273656C6C657253746172223A302C226C6F67697374696353746172223A302C22776569676874223A302C22736F7274223A393939392C22737461747573223A312C2272656D61726B223A2261736461736461736461222C2273656C66537461747573223A302C226C697665537461747573223A302C226164644279223A22222C2275706454696D65223A224D61792031322C203230323220353A33303A353820504D222C227570644279223A22227D7D5D, '2022-05-12 17:30:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('84', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A312C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E5878F3530222C2274797065223A312C2275736554797065223A312C22616D6F756E74223A35302C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A313030302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A312C22626567696E44617465223A224D61792031322C20323032322031323A30303A303020414D222C22656E6444617465223A224A756E2032332C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5A4A7E88B8FE68993E890A8E8BEBE222C22737461747573223A312C2261646454696D65223A224D61792031322C203230323220363A33323A303420504D222C2275706454696D65223A224D61792031322C203230323220363A33323A303420504D227D7D5D, '2022-05-12 18:32:05', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('85', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A312C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E5878F35222C2274797065223A312C2275736554797065223A312C22616D6F756E74223A35302C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A313030302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A312C22626567696E44617465223A224D61792031322C20323032322031323A30303A303020414D222C22656E6444617465223A224A756E2032332C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5A4A7E88B8FE68993E890A8E8BEBE3131222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A33353A353220414D222C2275706454696D65223A224D61792031332C203230323220393A33353A353220414D227D7D5D, '2022-05-13 09:35:53', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('86', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A22322C31222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A33383A303420414D222C2275706454696D65223A224D61792031332C203230323220393A33383A303420414D227D7D5D, '2022-05-13 09:38:04', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('87', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E696B0E4BABAE4BC98E683A0E588B8222C2274797065223A312C2275736554797065223A332C22757365476F6F647343617465496473223A22312C312C352C32222C22616D6F756E74223A31302C2266756C6C416D6F756E74223A35302C22746F74616C4E756D223A3130302C22647261774E756D223A302C226C696D69744E756D223A322C2276616C696469747954797065223A332C226669786564426567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C226669786564456E6444617465223A224A756E2033302C20323032322031323A30303A303020414D222C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A33383A353520414D222C2275706454696D65223A224D61792031332C203230323220393A33383A353520414D227D7D5D, '2022-05-13 09:38:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('88', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A2232222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A34373A353220414D222C2275706454696D65223A224D61792031332C203230323220393A34373A353220414D227D7D5D, '2022-05-13 09:47:52', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('89', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A22322C31222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A34383A343420414D222C2275706454696D65223A224D61792031332C203230323220393A34383A343420414D227D7D5D, '2022-05-13 09:48:45', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('90', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A2231222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A34393A303520414D222C2275706454696D65223A224D61792031332C203230323220393A34393A303520414D227D7D5D, '2022-05-13 09:49:06', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('91', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A2232222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A34393A353720414D222C2275706454696D65223A224D61792031332C203230323220393A34393A353720414D227D7D5D, '2022-05-13 09:49:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('92', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A22322C31222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A35303A333120414D222C2275706454696D65223A224D61792031332C203230323220393A35303A333120414D227D7D5D, '2022-05-13 09:50:32', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('93', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A2231222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A35303A343320414D222C2275706454696D65223A224D61792031332C203230323220393A35303A343320414D227D7D5D, '2022-05-13 09:50:43', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('94', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A22312C32222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A35303A343820414D222C2275706454696D65223A224D61792031332C203230323220393A35303A343820414D227D7D5D, '2022-05-13 09:50:48', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('95', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A2232222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A35323A303920414D222C2275706454696D65223A224D61792031332C203230323220393A35323A303920414D227D7D5D, '2022-05-13 09:52:09', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('96', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A2231222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A35323A313320414D222C2275706454696D65223A224D61792031332C203230323220393A35323A313320414D227D7D5D, '2022-05-13 09:52:14', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('97', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A322C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E6BBA1313030E6899335E68A98222C2274797065223A322C2275736554797065223A322C22757365476F6F6473496473223A22312C32222C22616D6F756E74223A352C2266756C6C416D6F756E74223A3130302C22746F74616C4E756D223A3530302C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31302C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A35323A313720414D222C2275706454696D65223A224D61792031332C203230323220393A35323A313720414D227D7D5D, '2022-05-13 09:52:17', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('98', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E696B0E4BABAE4BC98E683A0E588B8222C2274797065223A312C2275736554797065223A332C22757365476F6F647343617465496473223A22312C312C352C32222C22616D6F756E74223A31302C2266756C6C416D6F756E74223A35302C22746F74616C4E756D223A3130302C22647261774E756D223A302C226C696D69744E756D223A322C2276616C696469747954797065223A332C226669786564426567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C226669786564456E6444617465223A224A756E2033302C20323032322031323A30303A303020414D222C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C203230323220393A35333A333220414D222C2275706454696D65223A224D61792031332C203230323220393A35333A333220414D227D7D5D, '2022-05-13 09:53:32', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('99', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E696B0E4BABAE4BC98E683A0E588B8222C2274797065223A312C2275736554797065223A332C22757365476F6F647343617465496473223A22312C312C352C322C37222C22616D6F756E74223A31302C2266756C6C416D6F756E74223A35302C22746F74616C4E756D223A3130302C22647261774E756D223A302C226C696D69744E756D223A322C2276616C696469747954797065223A332C226669786564426567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C226669786564456E6444617465223A224A756E2033302C20323032322031323A30303A303020414D222C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C20323032322031303A31373A313020414D222C2275706454696D65223A224D61792031332C20323032322031303A31373A313020414D227D7D5D, '2022-05-13 10:17:11', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('100', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E696B0E4BABAE4BC98E683A0E588B8222C2274797065223A312C2275736554797065223A332C22757365476F6F647343617465496473223A22312C312C35222C22616D6F756E74223A31302C2266756C6C416D6F756E74223A35302C22746F74616C4E756D223A3130302C22647261774E756D223A302C226C696D69744E756D223A322C2276616C696469747954797065223A332C226669786564426567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C226669786564456E6444617465223A224A756E2033302C20323032322031323A30303A303020414D222C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C20323032322031303A31373A313620414D222C2275706454696D65223A224D61792031332C20323032322031303A31373A313620414D227D7D5D, '2022-05-13 10:17:16', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('101', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E696B0E4BABAE4BC98E683A0E588B8222C2274797065223A312C2275736554797065223A332C22757365476F6F647343617465496473223A22312C352C32222C22616D6F756E74223A31302C2266756C6C416D6F756E74223A35302C22746F74616C4E756D223A3130302C22647261774E756D223A302C226C696D69744E756D223A322C2276616C696469747954797065223A332C226669786564426567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C226669786564456E6444617465223A224A756E2033302C20323032322031323A30303A303020414D222C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C20323032322031303A31373A333420414D222C2275706454696D65223A224D61792031332C20323032322031303A31373A333420414D227D7D5D, '2022-05-13 10:17:34', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('102', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A332C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E696B0E4BABAE4BC98E683A0E588B8222C2274797065223A312C2275736554797065223A332C22757365476F6F647343617465496473223A22322C37222C22616D6F756E74223A31302C2266756C6C416D6F756E74223A35302C22746F74616C4E756D223A3130302C22647261774E756D223A302C226C696D69744E756D223A322C2276616C696469747954797065223A332C226669786564426567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C226669786564456E6444617465223A224A756E2033302C20323032322031323A30303A303020414D222C22626567696E44617465223A224D61792031332C20323032322031323A30303A303020414D222C22656E6444617465223A224D61792033312C20323032322031323A30303A303020414D222C2272756C6573223A22E5958AE5AE9EE68993E5AE9EE5A4A7E88B8FE68993222C22737461747573223A312C2261646454696D65223A224D61792031332C20323032322031303A31373A343420414D222C2275706454696D65223A224D61792031332C20323032322031303A31373A343420414D227D7D5D, '2022-05-13 10:17:45', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('103', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A342C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22617364617364222C2274797065223A322C2275736554797065223A332C22757365476F6F647343617465496473223A22352C37222C22616D6F756E74223A312C2266756C6C416D6F756E74223A3131312C22746F74616C4E756D223A312C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31312C22626567696E44617465223A224D61792032362C20323032322031323A30303A303020414D222C22656E6444617465223A224A756E2032372C20323032322031323A30303A303020414D222C2272756C6573223A2261736461736461736461222C22737461747573223A312C2261646454696D65223A224D61792031332C20323032322031303A31383A323920414D222C2275706454696D65223A224D61792031332C20323032322031303A31383A323920414D227D7D5D, '2022-05-13 10:18:30', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('104', '4', '13444555666', '192.168.0.105', '添加店铺优惠券', 0x5B7B22636F75706F6E223A7B226964223A342C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22617364617364222C2274797065223A322C2275736554797065223A332C22757365476F6F647343617465496473223A22352C31222C22616D6F756E74223A312C2266756C6C416D6F756E74223A3131312C22746F74616C4E756D223A312C22647261774E756D223A302C226C696D69744E756D223A312C2276616C696469747954797065223A322C2276616C696469747944617973223A31312C22626567696E44617465223A224D61792032362C20323032322031323A30303A303020414D222C22656E6444617465223A224A756E2032372C20323032322031323A30303A303020414D222C2272756C6573223A2261736461736461736461222C22737461747573223A312C2261646454696D65223A224D61792031332C20323032322031303A31383A333820414D222C2275706454696D65223A224D61792031332C20323032322031303A31383A333820414D227D7D5D, '2022-05-13 10:18:39', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('105', '4', '13444555666', '192.168.0.105', '删除店铺优惠券', 0x5B7B22696473223A2234227D5D, '2022-05-13 10:18:51', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('106', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A22312C352C37222C2274797065223A312C227469746C65223A2231222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2264657461696C223A225C7530303363705C7530303365E998BFE69DBEE5A4A7E5958A5C75303033632F705C7530303365222C22737461747573223A317D7D2C7B7D5D, '2022-05-14 10:31:10', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('107', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A22312C352C37222C2274797065223A312C227469746C65223A2231222C226465736372697074696F6E223A2231222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2264657461696C223A225C7530303363705C7530303365315C75303033632F705C7530303365222C22737461747573223A317D7D2C7B7D5D, '2022-05-14 10:33:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('108', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A22312C352C37222C2274797065223A312C227469746C65223A22313131222C226465736372697074696F6E223A22313231222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2264657461696C223A225C7530303363705C75303033653132315C75303033632F705C7530303365222C22737461747573223A317D7D2C7B7D5D, '2022-05-14 10:43:09', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('109', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A22312C352C37222C2274797065223A312C227469746C65223A22E697A5E5BC8FE7AE80E7BAA6E68F92E88AB1E5B7A5E889BAE88AB1E793B620E59C86E6B6A6E58E9AE9878DE78EBBE79283E88AB1E793B6E8A385E9A5B0E6B0B4E59FB9E5B7A5E889BAE59381E8A385E9A5B0E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2264657461696C223A225C7530303363705C7530303365E5958AE5AE9EE68993E5AE9EE79A845C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D5D227D7D2C7B7D5D, '2022-05-14 15:29:29', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('110', '4', '13444555666', '192.168.0.105', '批量下架', 0x5B7B22696473223A2233227D5D, '2022-05-14 15:54:10', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('111', '4', '13444555666', '192.168.0.105', '批量上架', 0x5B7B22696473223A2233227D5D, '2022-05-14 15:56:21', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('112', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A332C2263617465506964223A362C2263617465546964223A372C2273686F70436174654964223A372C22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A2237222C2274797065223A312C227469746C65223A22E697A5E5BC8FE7AE80E7BAA6E68F92E88AB1E5B7A5E889BAE88AB1E793B620E59C86E6B6A6E58E9AE9878DE78EBBE79283E88AB1E793B6E8A385E9A5B0E6B0B4E59FB9E5B7A5E889BAE59381E8A385E9A5B0E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A312C2267726F757053696E676C65427579223A302C2264657461696C223A225C7530303363705C7530303365E5958AE5AE9EE68993E5AE9EE79A845C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531333336393133373439385C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531333336393134313732345C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531333336393134343738365C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531333336393134363233345C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E675C225D5D227D7D2C7B7D5D, '2022-05-14 16:46:02', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('113', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A332C2263617465506964223A362C2263617465546964223A372C2273686F70436174654964223A372C22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A2237222C2274797065223A312C227469746C65223A22E697A5E5BC8FE7AE80E7BAA6E68F92E88AB1E5B7A5E889BAE88AB1E793B620E59C86E6B6A6E58E9AE9878DE78EBBE79283E88AB1E793B6E8A385E9A5B0E6B0B4E59FB9E5B7A5E889BAE59381E8A385E9A5B0E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A312C2267726F757053696E676C65427579223A302C2264657461696C223A225C7530303363705C7530303365E5958AE5AE9EE68993E5AE9EE79A845C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C222C5C22E8939D5C225D2C5C226174747256616C75655C223A5C225C227D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E8939D5C222C5C22E5A4A75C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E8939D5C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D5D227D7D2C7B7D5D, '2022-05-14 16:46:49', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('114', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A332C2263617465506964223A362C2263617465546964223A372C2273686F70436174654964223A372C22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A2237222C2274797065223A312C227469746C65223A22E697A5E5BC8FE7AE80E7BAA6E68F92E88AB1E5B7A5E889BAE88AB1E793B620E59C86E6B6A6E58E9AE9878DE78EBBE79283E88AB1E793B6E8A385E9A5B0E6B0B4E59FB9E5B7A5E889BAE59381E8A385E9A5B0E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A312C2267726F757053696E676C65427579223A302C2264657461696C223A225C7530303363705C7530303365E5958AE5AE9EE68993E5AE9EE79A845C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C222C5C22E8939D5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D2C7B5C22617474724B65795C223A5C22E58886E7B1BB5C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E4B880E7B1BB5C222C5C22E4BA8CE7B1BB5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22E4B880E7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5A4A75C222C5C22E4BA8CE7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22E4B880E7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22E4BA8CE7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22E4B880E7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22E4BA8CE7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22E4B880E7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22E4BA8CE7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E8939D5C222C5C22E5A4A75C222C5C22E4B880E7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E8939D5C222C5C22E5A4A75C222C5C22E4BA8CE7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E8939D5C222C5C22E5B08F5C222C5C22E4B880E7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E8939D5C222C5C22E5B08F5C222C5C22E4BA8CE7B1BB5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D5D227D7D2C7B7D5D, '2022-05-14 16:48:11', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('115', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B22676F6F647343617465496473223A2231302C3131222C2273686F7043617465496473223A22312C352C37222C2274797065223A322C227469746C65223A22E4B889E4BBB6E5A597E58C97E6ACA7696E73E9A38EE78EBBE79283E88AB1E793B6E9808FE6988EE5B9B2E88AB1E5AEA2E58E85E68F92E88AB1E6B0B4E585BBE7BBBFE8909DE6B0B4E59FB9E799BEE59088E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635392E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635372E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C227365636B696C6C4C696D69744E756D223A3230302C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D5D227D7D2C7B7D5D, '2022-05-14 16:51:18', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('116', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A332C2263617465506964223A362C2263617465546964223A372C2273686F70436174654964223A372C22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A2237222C2274797065223A312C227469746C65223A22E697A5E5BC8FE7AE80E7BAA6E68F92E88AB1E5B7A5E889BAE88AB1E793B620E59C86E6B6A6E58E9AE9878DE78EBBE79283E88AB1E793B6E8A385E9A5B0E6B0B4E59FB9E5B7A5E889BAE59381E8A385E9A5B0E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323334323033323934382E706E67222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A312C2267726F757053696E676C65427579223A302C2264657461696C223A225C7530303363705C7530303365E5958AE5AE9EE68993E5AE9EE79A845C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E7A9BAE7A9BA5C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E7A9BA5C222C5C22E5A4A75C225D7D2C7B5C22617474724B65795C223A5C22E5A4A7E5A4A75C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E7A9BA5C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E7A9BA5C222C5C22E7A9BA5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E7A9BA5C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E5A4A75C222C5C22E7A9BA5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D2C5B5C22E5A4A75C222C5C22E5B08F5C222C5C22315C222C5C22325C222C5C22335C222C5C22345C222C5C225C222C5C225C225D5D227D7D2C7B7D5D, '2022-05-14 17:01:37', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('117', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A342C2263617465506964223A31302C2263617465546964223A31312C2273686F70436174654964223A372C22676F6F647343617465496473223A2231302C3131222C2273686F7043617465496473223A2237222C2274797065223A322C227469746C65223A22E4B889E4BBB6E5A597E58C97E6ACA7696E73E9A38EE78EBBE79283E88AB1E793B6E9808FE6988EE5B9B2E88AB1E5AEA2E58E85E68F92E88AB1E6B0B4E585BBE7BBBFE8909DE6B0B4E59FB9E799BEE59088E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635392E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635372E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A312C2267726F757053696E676C65427579223A302C227365636B696C6C426567696E54696D65223A224D61792031342C20323032322031323A30303A303020414D222C227365636B696C6C456E6454696D65223A224D61792033312C20323032322031323A30303A303020414D222C227365636B696C6C4C696D69744E756D223A3230302C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937343532335C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937353533305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937363234325C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937373933315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D5D227D7D2C7B7D5D, '2022-05-14 17:06:26', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('118', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A22312C352C37222C2274797065223A332C227469746C65223A22E58C97E6ACA7E7AE80E7BAA6E5889BE6848FE78EBBE79283E88AB1E793B6E9808FE6988EE6B0B4E585BBE68F92E88AB1E793B6E9B29CE88AB1E78EABE791B0E799BEE59088E88AB1E5AEA2E58E85E69186E4BBB6E7BD91E7BAA2222C226465736372697074696F6E223A22E998BFE69DBEE5A4A7222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538322E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538322E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A332C2267726F757054797065223A312C2267726F75704E756D223A332C2267726F75704C696D6974486F757273223A31302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A312C22676F6F64734964223A357D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE8BEBE5C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22315C222C5C22315C222C5C22315C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22315C222C5C22315C222C5C22315C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22315C222C5C22315C222C5C22315C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22315C222C5C22315C222C5C22315C222C5C22315C222C5C225C222C5C225C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C226E756D5C223A6E756C6C2C5C22646973636F756E74735C223A6E756C6C7D5D227D5D, '2022-05-14 17:08:10', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('119', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A352C2263617465506964223A362C2263617465546964223A372C2273686F70436174654964223A372C22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A2237222C2274797065223A332C227469746C65223A22E58C97E6ACA7E7AE80E7BAA6E5889BE6848FE78EBBE79283E88AB1E793B6E9808FE6988EE6B0B4E585BBE68F92E88AB1E793B6E9B29CE88AB1E78EABE791B0E799BEE59088E88AB1E5AEA2E58E85E69186E4BBB6E7BD91E7BAA2222C226465736372697074696F6E223A22E998BFE69DBEE5A4A7222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538322E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538322E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A332C2267726F757054797065223A312C2267726F75704E756D223A332C2267726F75704C696D6974486F757273223A31302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2264657461696C223A225C7530303363705C7530303365E998BFE8BEBE5C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432303032325C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432313039335C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432323739305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432333335375C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D5D227D7D2C7B7D5D, '2022-05-14 17:09:07', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('120', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A352C2263617465506964223A362C2263617465546964223A372C2273686F70436174654964223A372C22676F6F647343617465496473223A22362C37222C2273686F7043617465496473223A2237222C2274797065223A332C227469746C65223A22E58C97E6ACA7E7AE80E7BAA6E5889BE6848FE78EBBE79283E88AB1E793B6E9808FE6988EE6B0B4E585BBE68F92E88AB1E793B6E9B29CE88AB1E78EABE791B0E799BEE59088E88AB1E5AEA2E58E85E69186E4BBB6E7BD91E7BAA2222C226465736372697074696F6E223A22E998BFE69DBEE5A4A7222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538322E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538322E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A332C2267726F757054797065223A312C2267726F75704E756D223A332C2267726F75704C696D6974486F757273223A31302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2264657461696C223A225C7530303363705C7530303365E998BFE8BEBE5C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432303032325C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432313039335C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432323739305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432333335375C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D5D227D7D2C7B7D5D, '2022-05-14 17:09:24', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('121', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B22676F6F647343617465496473223A22362C382C39222C2273686F7043617465496473223A22312C352C37222C2274797065223A332C227469746C65223A22E59C9FE999B6E7B29220E697A0E59C9FE6A0BDE59FB9E6A48DE789A9E4B893E794A8E999B6E7B29220E99A94E6B0B4E9808FE6B09420E998B2E6ADA2E78382E6A0B9E69588E69E9CE5A5BD222C226465736372697074696F6E223A22E998BFE4B889E5A4A7E88B8FE68993222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031302E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A322C2267726F75704C696D6974486F757273223A32302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A322C22676F6F64734964223A362C226E756D223A322C22646973636F756E7473223A39307D2C7B226964223A332C22676F6F64734964223A362C226E756D223A332C22646973636F756E7473223A38307D2C7B226964223A342C22676F6F64734964223A362C226E756D223A342C22646973636F756E7473223A37307D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22315C222C5C22315C222C5C22315C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22315C222C5C22315C222C5C22315C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22315C222C5C22315C222C5C22315C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22315C222C5C22315C222C5C22315C222C5C22315C222C5C225C222C5C225C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C226E756D5C223A322C5C22646973636F756E74735C223A5C2239305C227D2C7B5C226E756D5C223A332C5C22646973636F756E74735C223A5C2238305C227D2C7B5C226E756D5C223A342C5C22646973636F756E74735C223A5C2237305C227D5D227D5D, '2022-05-14 17:17:42', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('122', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A362C2263617465506964223A362C2263617465546964223A382C22636174654964223A392C2273686F70436174654964223A372C22676F6F647343617465496473223A22362C382C39222C2273686F7043617465496473223A2237222C2274797065223A332C227469746C65223A22E59C9FE999B6E7B29220E697A0E59C9FE6A0BDE59FB9E6A48DE789A9E4B893E794A8E999B6E7B29220E99A94E6B0B4E9808FE6B09420E998B2E6ADA2E78382E6A0B9E69588E69E9CE5A5BD222C226465736372697074696F6E223A22E998BFE4B889E5A4A7E88B8FE68993222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031302E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A322C2267726F75704C696D6974486F757273223A32302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A322C22676F6F64734964223A362C226E756D223A322C22646973636F756E7473223A39307D2C7B226964223A332C22676F6F64734964223A362C226E756D223A332C22646973636F756E7473223A38307D2C7B226964223A342C22676F6F64734964223A362C226E756D223A342C22646973636F756E7473223A37307D2C7B226964223A352C22676F6F64734964223A362C226E756D223A352C22646973636F756E7473223A36307D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438353032305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438363730365C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438373831315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438383234395C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C2269645C223A322C5C22676F6F647349645C223A362C5C226E756D5C223A322C5C22646973636F756E74735C223A39307D2C7B5C2269645C223A332C5C22676F6F647349645C223A362C5C226E756D5C223A332C5C22646973636F756E74735C223A38307D2C7B5C2269645C223A342C5C22676F6F647349645C223A362C5C226E756D5C223A342C5C22646973636F756E74735C223A37307D2C7B5C226E756D5C223A352C5C22646973636F756E74735C223A5C2236305C227D5D227D5D, '2022-05-14 17:19:04', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('123', '4', '13444555666', '192.168.0.105', '删除商品', 0x5B7B22696473223A2233227D5D, '2022-05-14 17:19:43', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('124', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A342C2263617465506964223A31302C2263617465546964223A31312C2273686F70436174654964223A372C22676F6F647343617465496473223A2231302C3131222C2273686F7043617465496473223A2237222C2274797065223A322C227469746C65223A22E4B889E4BBB6E5A597E58C97E6ACA7696E73E9A38EE78EBBE79283E88AB1E793B6E9808FE6988EE5B9B2E88AB1E5AEA2E58E85E68F92E88AB1E6B0B4E585BBE7BBBFE8909DE6B0B4E59FB9E799BEE59088E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635392E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635372E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A312C2267726F757053696E676C65427579223A302C227365636B696C6C426567696E54696D65223A224A756E2032382C20323032322031323A30303A303020414D222C227365636B696C6C456E6454696D65223A224A756E2033302C20323032322031323A30303A303020414D222C227365636B696C6C4C696D69744E756D223A3230302C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937343532335C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937353533305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937363234325C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937373933315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D5D227D7D2C7B7D5D, '2022-05-14 17:43:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('125', '4', '13444555666', '192.168.0.105', '删除店铺类目', 0x5B7B22696473223A2232227D5D, '2022-05-16 09:55:36', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('126', '4', '13444555666', '192.168.0.105', '删除店铺类目', 0x5B7B22696473223A2237227D5D, '2022-05-16 09:55:38', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('127', '4', '13444555666', '192.168.0.105', '删除店铺类目', 0x5B7B22696473223A2235227D5D, '2022-05-16 09:55:40', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('128', '4', '13444555666', '192.168.0.105', '删除店铺类目', 0x5B7B22696473223A2231227D5D, '2022-05-16 09:55:42', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('129', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A342C2263617465506964223A31302C2263617465546964223A31312C22676F6F647343617465496473223A2231362C32382C3831222C2273686F7043617465496473223A2231382C3435222C2274797065223A322C227469746C65223A22E4B889E4BBB6E5A597E58C97E6ACA7696E73E9A38EE78EBBE79283E88AB1E793B6E9808FE6988EE5B9B2E88AB1E5AEA2E58E85E68F92E88AB1E6B0B4E585BBE7BBBFE8909DE6B0B4E59FB9E799BEE59088E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635392E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635372E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A312C2267726F757053696E676C65427579223A302C227365636B696C6C426567696E54696D65223A224A756E2032382C20323032322031323A30303A303020414D222C227365636B696C6C456E6454696D65223A224A756E2033302C20323032322031323A30303A303020414D222C227365636B696C6C4C696D69744E756D223A3230302C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937343532335C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937353533305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937363234325C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937373933315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D5D227D7D2C7B7D5D, '2022-05-16 10:10:28', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('130', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A352C2263617465506964223A362C2263617465546964223A372C22676F6F647343617465496473223A2231392C3438222C2273686F7043617465496473223A2231382C3436222C2274797065223A332C227469746C65223A22E58C97E6ACA7E7AE80E7BAA6E5889BE6848FE78EBBE79283E88AB1E793B6E9808FE6988EE6B0B4E585BBE68F92E88AB1E793B6E9B29CE88AB1E78EABE791B0E799BEE59088E88AB1E5AEA2E58E85E69186E4BBB6E7BD91E7BAA2222C226465736372697074696F6E223A22E998BFE69DBEE5A4A7222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538322E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393238313538322E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A332C2267726F757054797065223A312C2267726F75704E756D223A332C2267726F75704C696D6974486F757273223A31302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A312C22676F6F64734964223A352C22646973636F756E7473223A307D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE8BEBE5C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432303032325C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432313039335C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432323739305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393239303432333335375C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393237353833302E6A70675C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C2269645C223A312C5C22676F6F647349645C223A352C5C22646973636F756E74735C223A307D5D227D5D, '2022-05-16 10:10:39', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('131', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A362C2263617465506964223A362C2263617465546964223A382C22636174654964223A392C22676F6F647343617465496473223A2232302C3439222C2273686F7043617465496473223A2232322C3536222C2274797065223A332C227469746C65223A22E59C9FE999B6E7B29220E697A0E59C9FE6A0BDE59FB9E6A48DE789A9E4B893E794A8E999B6E7B29220E99A94E6B0B4E9808FE6B09420E998B2E6ADA2E78382E6A0B9E69588E69E9CE5A5BD222C226465736372697074696F6E223A22E998BFE4B889E5A4A7E88B8FE68993222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031302E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A322C2267726F75704C696D6974486F757273223A32302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A322C22676F6F64734964223A362C226E756D223A322C22646973636F756E7473223A39307D2C7B226964223A332C22676F6F64734964223A362C226E756D223A332C22646973636F756E7473223A38307D2C7B226964223A342C22676F6F64734964223A362C226E756D223A342C22646973636F756E7473223A37307D2C7B226964223A352C22676F6F64734964223A362C226E756D223A352C22646973636F756E7473223A36307D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438353032305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438363730365C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438373831315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438383234395C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C2269645C223A322C5C22676F6F647349645C223A362C5C226E756D5C223A322C5C22646973636F756E74735C223A39307D2C7B5C2269645C223A332C5C22676F6F647349645C223A362C5C226E756D5C223A332C5C22646973636F756E74735C223A38307D2C7B5C2269645C223A342C5C22676F6F647349645C223A362C5C226E756D5C223A342C5C22646973636F756E74735C223A37307D2C7B5C2269645C223A352C5C22676F6F647349645C223A362C5C226E756D5C223A352C5C22646973636F756E74735C223A36307D5D227D5D, '2022-05-16 10:10:55', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('132', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A352C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE59CB0E58CBAE99990E588B6222C2274797065223A312C2276616C75746554797065223A312C2264656661756C7446697273744E756D223A312C2264656661756C7446697273745072696365223A302C2264656661756C745365636F6E644E756D223A312C2264656661756C745365636F6E645072696365223A302C2264656C697665727954696D65223A2231E5A4A9E58685227D7D2C7B22617265614A736F6E223A225B7B5C2269645C223A32342C5C2274656D706C61746549645C223A352C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A312C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A312C5C2270726F76696E63655C223A5C22E9BB91E9BE99E6B19FE79C815C222C5C22636974795C223A5C22E585A8E983A85C222C5C22616464726573735C223A5B5C223233303030305C222C5C225C225D2C5C22636F756E7472795C223A5C226368696E615C222C5C2270726F76696E6365436F64655C223A5C223233303030305C222C5C2263697479436F64655C223A5C225C227D2C7B5C2269645C223A32352C5C2274656D706C61746549645C223A352C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A31302C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A31302C5C2270726F76696E63655C223A5C22E5B1B1E8A5BFE79C815C222C5C22636974795C223A5C22E998B3E6B389E5B8825C222C5C22616464726573735C223A5B5C223134303030305C222C5C223134303330305C225D2C5C22636F756E7472795C223A5C226368696E615C222C5C2270726F76696E6365436F64655C223A5C223134303030305C222C5C2263697479436F64655C223A5C223134303330305C227D5D227D5D, '2022-05-16 11:28:45', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('133', '4', '13444555666', '192.168.0.105', '添加店铺运费模板', 0x5B7B2274656D706C617465223A7B226964223A352C2273686F704964223A342C2273686F704E616D65223A224C454B53484F50E69797E888B0E5BA97222C226E616D65223A22E58C85E982AEE59CB0E58CBAE99990E588B6222C2274797065223A312C2276616C75746554797065223A312C2264656661756C7446697273744E756D223A312C2264656661756C7446697273745072696365223A302C2264656661756C745365636F6E644E756D223A312C2264656661756C745365636F6E645072696365223A302C2264656C697665727954696D65223A2231E5A4A9E58685222C2270726F76696E6365436F6465223A22313330303030222C2263697479436F6465223A22313330333030222C2261726561436F6465223A22313330333031222C2270726F76696E6365223A22E6B2B3E58C97E79C81222C2263697479223A22E7A7A6E79A87E5B29BE5B882222C2261726561223A22E5B882E8BE96E58CBA227D7D2C7B22617265614A736F6E223A225B7B5C2269645C223A32362C5C2274656D706C61746549645C223A352C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A312C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A312C5C2270726F76696E63655C223A5C22E9BB91E9BE99E6B19FE79C815C222C5C2270726F76696E6365436F64655C223A5C223233303030305C222C5C22636974795C223A5C22E585A8E983A85C222C5C2263697479436F64655C223A5C225C222C5C22616464726573735C223A5B5C223233303030305C222C5C225C225D2C5C22636F756E7472795C223A5C226368696E615C227D2C7B5C2269645C223A32372C5C2274656D706C61746549645C223A352C5C2266697273744E756D5C223A312C5C22666972737450726963655C223A31302C5C227365636F6E644E756D5C223A312C5C227365636F6E6450726963655C223A31302C5C2270726F76696E63655C223A5C22E5B1B1E8A5BFE79C815C222C5C2270726F76696E6365436F64655C223A5C223134303030305C222C5C22636974795C223A5C22E998B3E6B389E5B8825C222C5C2263697479436F64655C223A5C223134303330305C222C5C22616464726573735C223A5B5C223134303030305C222C5C223134303330305C225D2C5C22636F756E7472795C223A5C226368696E615C227D5D227D5D, '2022-05-16 11:30:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('134', '5', 'yyw', '192.168.0.109', '上传头像', 0x5B7B2266696C65223A7B2270617274223A7B2266696C654974656D223A7B226669656C644E616D65223A2266696C65222C22636F6E74656E7454797065223A22696D6167652F6A706567222C226973466F726D4669656C64223A66616C73652C2266696C654E616D65223A22343661366438343136613233383964333461316330336539353839333861362E6A7067222C2273697A65223A2D312C2273697A655468726573686F6C64223A302C227265706F7369746F7279223A7B2270617468223A22433A5C5C55736572735C5C79616E676B756E7765695C5C417070446174615C5C4C6F63616C5C5C54656D705C5C746F6D6361742E323639343437353031373230303133313435302E373037365C5C776F726B5C5C546F6D6361745C5C6C6F63616C686F73745C5C524F4F54227D2C2268656164657273223A7B226865616465724E616D65546F56616C75654C6973744D6170223A7B22636F6E74656E742D646973706F736974696F6E223A5B22666F726D2D646174613B206E616D655C75303033645C2266696C655C223B2066696C656E616D655C75303033645C22343661366438343136613233383964333461316330336539353839333861362E6A70675C22225D2C22636F6E74656E742D74797065223A5B22696D6167652F6A706567225D7D7D2C2264656661756C7443686172736574223A2249534F2D383835392D31227D2C226C6F636174696F6E223A7B2270617468223A22433A5C5C55736572735C5C79616E676B756E7765695C5C417070446174615C5C4C6F63616C5C5C54656D705C5C746F6D6361742E323639343437353031373230303133313435302E373037365C5C776F726B5C5C546F6D6361745C5C6C6F63616C686F73745C5C524F4F54227D7D2C2266696C656E616D65223A22343661366438343136613233383964333461316330336539353839333861362E6A7067227D7D5D, '2022-05-16 14:39:36', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('135', '5', 'yyw', '192.168.0.109', '上传头像', 0x5B7B2266696C65223A7B2270617274223A7B2266696C654974656D223A7B226669656C644E616D65223A2266696C65222C22636F6E74656E7454797065223A22696D6167652F706E67222C226973466F726D4669656C64223A66616C73652C2266696C654E616D65223A227863786D2D4D6F64656C36383039313634363936353537313030332E706E67222C2273697A65223A2D312C2273697A655468726573686F6C64223A302C227265706F7369746F7279223A7B2270617468223A22433A5C5C55736572735C5C79616E676B756E7765695C5C417070446174615C5C4C6F63616C5C5C54656D705C5C746F6D6361742E323235343438383830303037323438333031322E373037365C5C776F726B5C5C546F6D6361745C5C6C6F63616C686F73745C5C524F4F54227D2C2268656164657273223A7B226865616465724E616D65546F56616C75654C6973744D6170223A7B22636F6E74656E742D646973706F736974696F6E223A5B22666F726D2D646174613B206E616D655C75303033645C2266696C655C223B2066696C656E616D655C75303033645C227863786D2D4D6F64656C36383039313634363936353537313030332E706E675C22225D2C22636F6E74656E742D74797065223A5B22696D6167652F706E67225D7D7D2C2264656661756C7443686172736574223A2249534F2D383835392D31227D2C226C6F636174696F6E223A7B2270617468223A22433A5C5C55736572735C5C79616E676B756E7765695C5C417070446174615C5C4C6F63616C5C5C54656D705C5C746F6D6361742E323235343438383830303037323438333031322E373037365C5C776F726B5C5C546F6D6361745C5C6C6F63616C686F73745C5C524F4F54227D7D2C2266696C656E616D65223A227863786D2D4D6F64656C36383039313634363936353537313030332E706E67227D7D5D, '2022-05-16 15:17:44', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('136', '5', 'yyw', '192.168.0.109', '上传头像', 0x5B7B2266696C65223A7B2270617274223A7B2266696C654974656D223A7B226669656C644E616D65223A2266696C65222C22636F6E74656E7454797065223A22696D6167652F706E67222C226973466F726D4669656C64223A66616C73652C2266696C654E616D65223A22E5BEAEE4BFA1E59BBEE789875F32303231313130343039343234342E706E67222C2273697A65223A2D312C2273697A655468726573686F6C64223A302C227265706F7369746F7279223A7B2270617468223A22433A5C5C55736572735C5C79616E676B756E7765695C5C417070446174615C5C4C6F63616C5C5C54656D705C5C746F6D6361742E323235343438383830303037323438333031322E373037365C5C776F726B5C5C546F6D6361745C5C6C6F63616C686F73745C5C524F4F54227D2C2268656164657273223A7B226865616465724E616D65546F56616C75654C6973744D6170223A7B22636F6E74656E742D646973706F736974696F6E223A5B22666F726D2D646174613B206E616D655C75303033645C2266696C655C223B2066696C656E616D655C75303033645C22E5BEAEE4BFA1E59BBEE789875F32303231313130343039343234342E706E675C22225D2C22636F6E74656E742D74797065223A5B22696D6167652F706E67225D7D7D2C2264656661756C7443686172736574223A2249534F2D383835392D31227D2C226C6F636174696F6E223A7B2270617468223A22433A5C5C55736572735C5C79616E676B756E7765695C5C417070446174615C5C4C6F63616C5C5C54656D705C5C746F6D6361742E323235343438383830303037323438333031322E373037365C5C776F726B5C5C546F6D6361745C5C6C6F63616C686F73745C5C524F4F54227D7D2C2266696C656E616D65223A22E5BEAEE4BFA1E59BBEE789875F32303231313130343039343234342E706E67227D7D5D, '2022-05-16 15:18:48', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('137', '5', 'yyw', '192.168.0.109', '更新个人资料', 0x5B7B2267656E646572223A2231227D2C7B7D2C7B7D2C7B7D2C7B7D2C7B7D2C7B7D2C7B7D2C7B7D2C7B7D2C7B7D2C7B22706572736F6E5369676E223A22E997AAE794B5E997AAE794B5227D2C7B7D5D, '2022-05-16 15:29:46', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('138', '5', 'yyw', '192.168.0.109', '将商品加入购物车', 0x5B7B22676F6F64734964223A367D2C7B226174747253796D626F6C50617468223A2232312F3233227D2C7B226E756D223A317D5D, '2022-05-16 18:38:33', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('139', '5', 'yyw', '192.168.0.109', '将商品加入购物车', 0x5B7B22676F6F64734964223A367D2C7B226174747253796D626F6C50617468223A2232312F3233227D2C7B226E756D223A317D5D, '2022-05-16 18:38:39', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('140', '5', 'yyw', '192.168.0.109', '将商品加入购物车', 0x5B7B22676F6F64734964223A367D2C7B226174747253796D626F6C50617468223A2232302F3233227D2C7B226E756D223A317D5D, '2022-05-16 18:39:19', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('141', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A362C2263617465506964223A32302C2263617465546964223A34392C22636174654964223A392C2273686F70436174654964223A35362C22676F6F647343617465496473223A2232302C3439222C2273686F7043617465496473223A223536222C2274797065223A332C227469746C65223A22E59C9FE999B6E7B29220E697A0E59C9FE6A0BDE59FB9E6A48DE789A9E4B893E794A8E999B6E7B29220E99A94E6B0B4E9808FE6B09420E998B2E6ADA2E78382E6A0B9E69588E69E9CE5A5BD222C226465736372697074696F6E223A22E998BFE4B889E5A4A7E88B8FE68993222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031302E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A322C2267726F75704C696D6974486F757273223A32302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A322C22676F6F64734964223A362C226E756D223A322C22646973636F756E7473223A39307D2C7B226964223A332C22676F6F64734964223A362C226E756D223A332C22646973636F756E7473223A38307D2C7B226964223A342C22676F6F64734964223A362C226E756D223A342C22646973636F756E7473223A37307D2C7B226964223A352C22676F6F64734964223A362C226E756D223A352C22646973636F756E7473223A36307D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438353032305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438363730365C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438373831315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438383234395C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C2269645C223A322C5C22676F6F647349645C223A362C5C226E756D5C223A322C5C22646973636F756E74735C223A39307D2C7B5C2269645C223A332C5C22676F6F647349645C223A362C5C226E756D5C223A332C5C22646973636F756E74735C223A38307D2C7B5C2269645C223A342C5C22676F6F647349645C223A362C5C226E756D5C223A342C5C22646973636F756E74735C223A37307D2C7B5C2269645C223A352C5C22676F6F647349645C223A362C5C226E756D5C223A352C5C22646973636F756E74735C223A36307D5D227D5D, '2022-05-17 09:44:41', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('142', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A362C2263617465506964223A32302C2263617465546964223A34392C22636174654964223A392C2273686F70436174654964223A35362C22676F6F647343617465496473223A2232312C3533222C2273686F7043617465496473223A223536222C2274797065223A332C227469746C65223A22E59C9FE999B6E7B29220E697A0E59C9FE6A0BDE59FB9E6A48DE789A9E4B893E794A8E999B6E7B29220E99A94E6B0B4E9808FE6B09420E998B2E6ADA2E78382E6A0B9E69588E69E9CE5A5BD222C226465736372697074696F6E223A22E998BFE4B889E5A4A7E88B8FE68993222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031302E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A322C2267726F75704C696D6974486F757273223A32302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A322C22676F6F64734964223A362C226E756D223A322C22646973636F756E7473223A39307D2C7B226964223A332C22676F6F64734964223A362C226E756D223A332C22646973636F756E7473223A38307D2C7B226964223A342C22676F6F64734964223A362C226E756D223A342C22646973636F756E7473223A37307D2C7B226964223A352C22676F6F64734964223A362C226E756D223A352C22646973636F756E7473223A36307D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438353032305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438363730365C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438373831315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438383234395C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C2269645C223A322C5C22676F6F647349645C223A362C5C226E756D5C223A322C5C22646973636F756E74735C223A39307D2C7B5C2269645C223A332C5C22676F6F647349645C223A362C5C226E756D5C223A332C5C22646973636F756E74735C223A38307D2C7B5C2269645C223A342C5C22676F6F647349645C223A362C5C226E756D5C223A342C5C22646973636F756E74735C223A37307D2C7B5C2269645C223A352C5C22676F6F647349645C223A362C5C226E756D5C223A352C5C22646973636F756E74735C223A36307D5D227D5D, '2022-05-17 09:44:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('143', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A362C2263617465506964223A32312C2263617465546964223A35332C22676F6F647343617465496473223A2231362C32392C3838222C2273686F7043617465496473223A2232312C3533222C2274797065223A332C227469746C65223A22E59C9FE999B6E7B29220E697A0E59C9FE6A0BDE59FB9E6A48DE789A9E4B893E794A8E999B6E7B29220E99A94E6B0B4E9808FE6B09420E998B2E6ADA2E78382E6A0B9E69588E69E9CE5A5BD222C226465736372697074696F6E223A22E998BFE4B889E5A4A7E88B8FE68993222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031302E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A322C2267726F75704C696D6974486F757273223A32302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A322C22676F6F64734964223A362C226E756D223A322C22646973636F756E7473223A39307D2C7B226964223A332C22676F6F64734964223A362C226E756D223A332C22646973636F756E7473223A38307D2C7B226964223A342C22676F6F64734964223A362C226E756D223A342C22646973636F756E7473223A37307D2C7B226964223A352C22676F6F64734964223A362C226E756D223A352C22646973636F756E7473223A36307D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438353032305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438363730365C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438373831315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C5C22315C222C5C22312E30305C222C5C224C454B313635323531393836323438383234395C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C2269645C223A322C5C22676F6F647349645C223A362C5C226E756D5C223A322C5C22646973636F756E74735C223A39307D2C7B5C2269645C223A332C5C22676F6F647349645C223A362C5C226E756D5C223A332C5C22646973636F756E74735C223A38307D2C7B5C2269645C223A342C5C22676F6F647349645C223A362C5C226E756D5C223A342C5C22646973636F756E74735C223A37307D2C7B5C2269645C223A352C5C22676F6F647349645C223A362C5C226E756D5C223A352C5C22646973636F756E74735C223A36307D5D227D5D, '2022-05-17 10:12:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('144', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 10:27:46', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('145', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:52', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('146', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:53', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('147', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:53', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('148', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:53', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('149', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:53', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('150', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:54', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('151', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:54', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('152', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:54', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('153', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:27:54', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('154', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A317D5D, '2022-05-17 10:27:55', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('155', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A317D5D, '2022-05-17 10:27:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('156', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A317D5D, '2022-05-17 10:27:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('157', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A317D5D, '2022-05-17 10:27:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('158', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A317D5D, '2022-05-17 10:27:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('159', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:55', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('160', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:55', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('161', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:55', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('162', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('163', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('164', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('165', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('166', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:57', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('167', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:57', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('168', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:57', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('169', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:57', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('170', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('171', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('172', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('173', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('174', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('175', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('176', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('177', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('178', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('179', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 10:28:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('180', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 10:58:47', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('181', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 11:07:23', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('182', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 11:10:47', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('183', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 11:13:09', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('184', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 11:15:20', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('185', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 11:20:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('186', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 11:21:35', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('187', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 11:22:33', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('188', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 14:28:40', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('189', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B7D7D2C7B7D5D, '2022-05-17 14:32:18', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('190', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 14:33:37', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('191', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A367D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A33317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 14:45:08', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('192', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:46:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('193', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:46:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('194', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:46:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('195', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:46:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('196', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:46:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('197', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('198', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('199', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('200', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('201', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('202', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('203', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A317D5D, '2022-05-17 14:47:02', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('204', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:08', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('205', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:08', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('206', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:08', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('207', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:09', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('208', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:09', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('209', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:10', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('210', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:10', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('211', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A327D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:10', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('212', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:11', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('213', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:11', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('214', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:11', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('215', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:12', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('216', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:12', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('217', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:12', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('218', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:12', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('219', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:12', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('220', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:12', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('221', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('222', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('223', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('224', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('225', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('226', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:14', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('227', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:14', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('228', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:14', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('229', '5', 'yyw', '192.168.0.109', '购物车加减数量', 0x5B7B226361724964223A317D2C7B226E756D223A2D317D5D, '2022-05-17 14:47:14', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('230', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 14:47:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('231', '5', 'yyw', '192.168.0.109', '添加订单', 0x5B7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B22616464466F726D4A736F6E223A225B7B5C22676F6F647344657461696C5C223A5B7B5C22736B7549645C223A33322C5C226E756D5C223A317D5D2C5C226361724964735C223A5C225C222C5C22636F75706F6E49645C223A5C225C222C5C2272656D61726B5C223A5C225C222C5C2267726F757049645C223A5C225C227D5D227D5D, '2022-05-17 14:49:43', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('232', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 14:59:54', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('233', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A362C2263617465506964223A31362C2263617465546964223A32392C22636174654964223A38382C22676F6F647343617465496473223A2231362C32392C3838222C2273686F7043617465496473223A2232312C3533222C2274797065223A332C227469746C65223A22E59C9FE999B6E7B29220E697A0E59C9FE6A0BDE59FB9E6A48DE789A9E4B893E794A8E999B6E7B29220E99A94E6B0B4E9808FE6B09420E998B2E6ADA2E78382E6A0B9E69588E69E9CE5A5BD222C226465736372697074696F6E223A22E998BFE4B889E5A4A7E88B8FE68993222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031302E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835353031332E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A322C2267726F75704C696D6974486F757273223A32302C2267726F75704C696D6974427579223A312C2267726F757053696E676C65427579223A312C2267726F75704C697374223A5B7B226964223A322C22676F6F64734964223A362C226E756D223A322C22646973636F756E7473223A39307D2C7B226964223A332C22676F6F64734964223A362C226E756D223A332C22646973636F756E7473223A38307D2C7B226964223A342C22676F6F64734964223A362C226E756D223A342C22646973636F756E7473223A37307D2C7B226964223A352C22676F6F64734964223A362C226E756D223A352C22646973636F756E7473223A36307D5D2C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C3939392C5C22312E30305C222C5C224C454B313635323531393836323438353032305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C3939392C5C22312E30305C222C5C224C454B313635323531393836323438363730365C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22312E30305C222C3939392C5C22312E30305C222C5C224C454B313635323531393836323438373831315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22312E30305C222C3939392C5C22312E30305C222C5C224C454B313635323531393836323438383234395C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531393835313434382E6A70675C225D5D227D7D2C7B2267726F75704A736F6E223A225B7B5C2269645C223A322C5C22676F6F647349645C223A362C5C226E756D5C223A322C5C22646973636F756E74735C223A39307D2C7B5C2269645C223A332C5C22676F6F647349645C223A362C5C226E756D5C223A332C5C22646973636F756E74735C223A38307D2C7B5C2269645C223A342C5C22676F6F647349645C223A362C5C226E756D5C223A342C5C22646973636F756E74735C223A37307D2C7B5C2269645C223A352C5C22676F6F647349645C223A362C5C226E756D5C223A352C5C22646973636F756E74735C223A36307D5D227D5D, '2022-05-17 15:01:16', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('234', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 15:01:25', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('235', '5', 'yyw', '192.168.0.109', '添加订单', 0x5B7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B22616464466F726D4A736F6E223A225B7B5C22676F6F647344657461696C5C223A5B7B5C22736B7549645C223A33322C5C226E756D5C223A317D5D2C5C226361724964735C223A5C22325C222C5C22636F75706F6E49645C223A5C225C222C5C2272656D61726B5C223A5C225C222C5C2267726F757049645C223A5C225C227D5D227D5D, '2022-05-17 15:01:26', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('236', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 15:04:18', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('237', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 15:07:48', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('238', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 15:09:17', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('239', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 15:09:23', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('240', '5', 'yyw', '192.168.0.109', '添加订单', 0x5B7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B22616464466F726D4A736F6E223A225B7B5C22676F6F647344657461696C5C223A5B7B5C22736B7549645C223A33322C5C226E756D5C223A317D5D2C5C226361724964735C223A5C22325C222C5C22636F75706F6E49645C223A5C225C222C5C2272656D61726B5C223A5C225C222C5C2267726F757049645C223A5C225C227D5D227D5D, '2022-05-17 15:10:15', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('241', '5', 'yyw', '192.168.0.109', '支付订单', 0x5B7B227061794F726465724E6F223A22313635323737313431343836363130303330303035227D5D, '2022-05-17 15:23:13', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('242', '5', 'yyw', '192.168.0.109', '将商品加入购物车', 0x5B7B22676F6F64734964223A367D2C7B226174747253796D626F6C50617468223A2232312F3233227D2C7B226E756D223A317D5D, '2022-05-17 15:25:40', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('243', '5', 'yyw', '192.168.0.109', '将商品加入购物车', 0x5B7B22676F6F64734964223A367D2C7B226174747253796D626F6C50617468223A2232312F3232227D2C7B226E756D223A317D5D, '2022-05-17 15:25:44', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('244', '5', 'yyw', '192.168.0.109', '将商品加入购物车', 0x5B7B22676F6F64734964223A347D2C7B226174747253796D626F6C50617468223A22392F3130227D2C7B226E756D223A317D5D, '2022-05-17 15:25:50', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('245', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A32312C5C226E756D5C223A317D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33332C5C226E756D5C223A317D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33342C5C226E756D5C223A317D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33322C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 15:26:11', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('246', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B22676F6F647343617465496473223A2231392C3438222C2273686F7043617465496473223A2232302C3439222C2274797065223A312C227469746C65223A22E4BA9AE5858BE58A9BE997A8E7898CE58FB7E7A081E7898CE5AEB6E794A8E5AE9AE588B6E997A8E7898CE58FB7E5889BE6848FE4B8AAE680A7E688BFE997B4E98592E5BA97E58FAFE788B1E68385E4BEA3E688BFE58FB7E695B0E5AD97E997A8E8B4B4E6B091E5AEBFE585A5E688B7E8BDBBE5A5A2E58C85E58EA2E7898CE5AD90E5AD97E8AEA2E588B6E69CA8E8B4A8E8B4B4E7898C222C226465736372697074696F6E223A22E998BFE69DBEE5A4A7222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323737323534343335302E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323737323535303338302E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323737323535303338332E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323737323535303337392E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323737323535303338332E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5A4A7E5B08F5C222C5C226174747256616C75655C223A5C225C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C2235355C222C5C2233385C222C5C223939395C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C2235355C222C5C2233385C222C5C223939395C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C2235355C222C5C2233385C222C5C223939395C222C5C22315C222C5C225C222C5C225C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C2235355C222C5C2233385C222C5C223939395C222C5C22315C222C5C225C222C5C225C225D5D227D7D2C7B7D5D, '2022-05-17 15:29:20', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('247', '5', 'yyw', '192.168.0.109', '将商品加入购物车', 0x5B7B22676F6F64734964223A377D2C7B226174747253796D626F6C50617468223A2232342F3236227D2C7B226E756D223A317D5D, '2022-05-17 15:29:44', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('248', '5', 'yyw', '192.168.0.109', '将商品加入购物车', 0x5B7B22676F6F64734964223A377D2C7B226174747253796D626F6C50617468223A2232342F3237227D2C7B226E756D223A317D5D, '2022-05-17 15:29:48', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('249', '5', 'yyw', '192.168.0.109', '确认订单查询', 0x5B7B22676F6F6473506172616D73223A225B7B5C2273686F7049645C223A342C5C22736B7549645C223A33362C5C226E756D5C223A317D2C7B5C2273686F7049645C223A342C5C22736B7549645C223A33352C5C226E756D5C223A317D5D227D2C7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B7D5D, '2022-05-17 15:29:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('250', '5', 'yyw', '192.168.0.109', '添加订单', 0x5B7B2261646472657373223A7B226964223A312C226D656D6265724964223A352C226D656D6265724E616D65223A22797977222C227265616C6E616D65223A227975776569222C2270686F6E65223A223139383539323232353230222C2270726F76696E6365436F6465223A22313430303030222C2263697479436F6465223A22313430333030222C2261726561436F6465223A22313430333131222C2270726F76696E6365223A22E5B1B1E8A5BFE79C81222C2263697479223A22E998B3E6B389E5B882222C2261726561223A22E9838AE58CBA222C2261646472657373223A22383031222C22737461747573223A317D7D2C7B22616464466F726D4A736F6E223A225B7B5C22676F6F647344657461696C5C223A5B7B5C22736B7549645C223A33362C5C226E756D5C223A317D2C7B5C22736B7549645C223A33352C5C226E756D5C223A317D5D2C5C226361724964735C223A5C22372C365C222C5C22636F75706F6E49645C223A5C225C222C5C2272656D61726B5C223A5C225C222C5C2267726F757049645C223A5C225C227D5D227D5D, '2022-05-17 15:30:00', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('251', '5', 'yyw', '192.168.0.109', '支付订单', 0x5B7B227061794F726465724E6F223A22313635323737323539393737363130303530303035227D5D, '2022-05-17 15:30:07', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('252', '5', 'yyw', '192.168.0.109', '修改地址', 0x5B7B226F726465724964223A347D2C7B22616464726573734964223A317D5D, '2022-05-17 16:16:34', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('253', '5', 'yyw', '192.168.0.109', '修改地址', 0x5B7B226F726465724964223A347D2C7B22616464726573734964223A317D5D, '2022-05-17 16:18:44', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('254', '5', 'yyw', '192.168.0.109', '修改地址', 0x5B7B226F726465724964223A347D2C7B22616464726573734964223A317D5D, '2022-05-17 16:19:04', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('255', '5', 'yyw', '192.168.0.109', '删除购物车', 0x5B7B22696473223A22332C32227D5D, '2022-05-17 16:35:50', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('256', '5', 'yyw', '192.168.0.109', '删除购物车', 0x5B7B22696473223A22352C34227D5D, '2022-05-17 16:35:59', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('257', '5', 'yyw', '192.168.0.109', '清空购物车', null, '2022-05-17 16:42:14', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('258', '5', 'yyw', '192.168.0.109', '关注取关商品', 0x5B7B22676F6F64734964223A347D5D, '2022-05-17 17:14:41', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('259', '5', 'yyw', '192.168.0.109', '关注取关商品', 0x5B7B22676F6F64734964223A347D5D, '2022-05-17 17:14:56', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('260', '5', 'yyw', '192.168.0.109', '关注取关商品', 0x5B7B22676F6F64734964223A347D5D, '2022-05-17 17:14:58', null, null, null);
INSERT INTO `ums_member_operation_log` VALUES ('261', '4', '13444555666', '192.168.0.105', '添加编辑商品', 0x5B7B227075626C697368466F726D223A7B226964223A342C2263617465506964223A31362C2263617465546964223A32382C22636174654964223A38312C2273686F70436174654964223A34352C22676F6F647343617465496473223A2231382C3436222C2273686F7043617465496473223A223435222C2274797065223A322C227469746C65223A22E4B889E4BBB6E5A597E58C97E6ACA7696E73E9A38EE78EBBE79283E88AB1E793B6E9808FE6988EE5B9B2E88AB1E5AEA2E58E85E68F92E88AB1E6B0B4E585BBE7BBBFE8909DE6B0B4E59FB9E799BEE59088E69186E4BBB6222C226465736372697074696F6E223A22E5958AE5AE9EE68993E5AE9EE79A84222C226D61696E496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A7067222C22676F6F6473496D67223A2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635392E6A70677C68747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236373635372E6A7067222C2273746F636B54797065223A312C226672656967687454656D706C6174654964223A352C2267726F757054797065223A312C2267726F757053696E676C65427579223A302C227365636B696C6C426567696E54696D65223A224A756E2032382C20323032322031323A30303A303020414D222C227365636B696C6C456E6454696D65223A224A756E2033302C20323032322031323A30303A303020414D222C227365636B696C6C4C696D69744E756D223A3230302C2264657461696C223A225C7530303363705C7530303365E998BFE4B889E5A4A7E88B8FE689935C75303033632F705C7530303365222C22737461747573223A312C22617474724B65794C697374223A225B7B5C22617474724B65795C223A5C22E9A29CE889B25C222C5C226174747256616C75654C6973745C223A5B5C22E9BB915C222C5C22E799BD5C225D7D2C7B5C22617474724B65795C223A5C22E5B0BAE5AFB85C222C5C226174747256616C75654C6973745C223A5B5C22E5A4A75C222C5C22E5B08F5C225D7D5D222C22736B754C697374223A225B5B5C22E9BB915C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937343532335C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E9BB915C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937353533305C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E799BD5C222C5C22E5A4A75C222C5C22312E30305C222C5C22322E30305C222C302C5C22342E30305C222C5C224C454B313635323531383237373937363234325C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D2C5B5C22E799BD5C222C5C22E5B08F5C222C5C22312E30305C222C5C22322E30305C222C5C22335C222C5C22342E30305C222C5C224C454B313635323531383237373937373933315C222C5C2268747470733A2F2F71696E69752E6368656E67797577622E636F6D2F313635323531383236333739332E6A70675C225D5D227D7D2C7B7D5D, '2022-05-17 18:12:34', null, null, null);

-- ----------------------------
-- Table structure for ums_member_point_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_point_log`;
CREATE TABLE `ums_member_point_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT NULL COMMENT '资金类型',
  `in_out` int DEFAULT NULL COMMENT '收支类型 1收入 -1支出',
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `amount` decimal(15,2) DEFAULT NULL COMMENT '金额',
  `amount_before` decimal(15,2) DEFAULT NULL COMMENT '操作前金额',
  `amount_after` decimal(15,2) DEFAULT NULL COMMENT '操作后金额',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP',
  `operate_ip_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP实际地址',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='积分明细';

-- ----------------------------
-- Records of ums_member_point_log
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_punishment
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_punishment`;
CREATE TABLE `ums_member_punishment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '处罚内容',
  `days` int DEFAULT '0' COMMENT '拉黑天数',
  `expired_time` datetime DEFAULT NULL COMMENT '到期时间',
  `status` int DEFAULT NULL COMMENT '0处罚中>>1已到期',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户处罚表';

-- ----------------------------
-- Records of ums_member_punishment
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_realname
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_realname`;
CREATE TABLE `ums_member_realname` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `realname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证',
  `link_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系手机',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `idcard_face` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证正面',
  `idcard_face_hand` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手持身份证正面',
  `idcard_back` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证反面',
  `idcard_back_hand` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手持身份证反面',
  `idcard_begin_time` date DEFAULT NULL COMMENT '身份证有效期',
  `idcard_end_time` date DEFAULT NULL COMMENT '身份证有效期',
  `num` int DEFAULT '0' COMMENT '认证次数',
  `status` int DEFAULT NULL COMMENT '0待审核 1审核通过 2审核失败',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '原因',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户信息表';

-- ----------------------------
-- Records of ums_member_realname
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_recharge
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_recharge`;
CREATE TABLE `ums_member_recharge` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '单号',
  `member_id` int NOT NULL COMMENT '用户ID',
  `member_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `paymethod` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '支付方式',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '付款截图',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '充值金额',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '实际金额',
  `remit_bank` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '打款银行',
  `remit_realname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '打款姓名',
  `remit_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '打款账号(后四位)',
  `config_account_id` int DEFAULT NULL COMMENT '收款ID',
  `save_time` datetime DEFAULT NULL COMMENT '存款时间',
  `verify_time` datetime DEFAULT NULL COMMENT '审核时间',
  `verify_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核人',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态 0待审核 1审核通过 2审核不通过 3取消',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='充值';

-- ----------------------------
-- Records of ums_member_recharge
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_rel
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_rel`;
CREATE TABLE `ums_member_rel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` int NOT NULL COMMENT '层级关系',
  `up_member_id` int NOT NULL COMMENT '上级',
  `down_member_id` int NOT NULL COMMENT '下级',
  `add_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户关系';

-- ----------------------------
-- Records of ums_member_rel
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_remind
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_remind`;
CREATE TABLE `ums_member_remind` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT NULL COMMENT '提醒类型',
  `to_type` int DEFAULT NULL COMMENT '1商家>>2后台',
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '提醒标题',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '提醒内容',
  `status` int DEFAULT '0' COMMENT '0未读>>1已读',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息提醒';

-- ----------------------------
-- Records of ums_member_remind
-- ----------------------------
INSERT INTO `ums_member_remind` VALUES ('2', '1', '2', '29', '待审核实名', '「18605851126」申请了实名认证, 请尽快审批', '1', '2021-12-29 12:02:23', null, '2021-12-29 12:03:01', null);
INSERT INTO `ums_member_remind` VALUES ('3', '3', '2', '29', '待审核银行', '「18605851126」申请了银行认证, 请尽快审批', '1', '2021-12-29 12:04:43', null, '2021-12-29 12:05:16', null);
INSERT INTO `ums_member_remind` VALUES ('4', '2', '2', '29', '待审核买号', '「18605851126」绑定了新买号, 请尽快审批', '1', '2021-12-29 12:08:04', null, '2021-12-29 12:08:52', null);
INSERT INTO `ums_member_remind` VALUES ('5', '4', '2', '29', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '1', '2021-12-29 12:09:36', null, '2021-12-29 12:09:51', null);
INSERT INTO `ums_member_remind` VALUES ('6', '4', '2', '29', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '1', '2021-12-29 12:12:27', null, '2021-12-29 12:14:27', null);
INSERT INTO `ums_member_remind` VALUES ('7', '4', '2', '30', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '1', '2021-12-29 12:12:27', null, '2021-12-29 12:13:06', null);
INSERT INTO `ums_member_remind` VALUES ('8', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367803048', '1', '2021-12-29 13:28:08', null, '2021-12-29 14:35:54', null);
INSERT INTO `ums_member_remind` VALUES ('9', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367807077', '1', '2021-12-29 15:57:41', null, '2022-01-05 21:04:04', null);
INSERT INTO `ums_member_remind` VALUES ('10', '7', '2', '29', '待审核店铺', '「19859222551」绑定了新店铺, 请尽快审核', '1', '2021-12-30 14:33:30', null, '2021-12-30 15:00:51', null);
INSERT INTO `ums_member_remind` VALUES ('11', '7', '2', '30', '待审核店铺', '「19859222551」绑定了新店铺, 请尽快审核', '0', '2021-12-30 14:33:30', null, '2021-12-30 14:33:30', null);
INSERT INTO `ums_member_remind` VALUES ('12', '4', '2', '29', '待审核充值', '「19859222551」进行了充值, 请尽快审批', '1', '2021-12-30 14:34:21', null, '2021-12-30 15:00:51', null);
INSERT INTO `ums_member_remind` VALUES ('13', '4', '2', '30', '待审核充值', '「19859222551」进行了充值, 请尽快审批', '0', '2021-12-30 14:34:21', null, '2021-12-30 14:34:21', null);
INSERT INTO `ums_member_remind` VALUES ('14', '4', '2', '29', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '1', '2021-12-30 17:52:23', null, '2021-12-30 17:54:16', null);
INSERT INTO `ums_member_remind` VALUES ('15', '4', '2', '30', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '0', '2021-12-30 17:52:23', null, '2021-12-30 17:52:23', null);
INSERT INTO `ums_member_remind` VALUES ('16', '4', '2', '29', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '1', '2021-12-30 17:53:11', null, '2021-12-30 17:54:16', null);
INSERT INTO `ums_member_remind` VALUES ('17', '4', '2', '30', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '0', '2021-12-30 17:53:11', null, '2021-12-30 17:53:11', null);
INSERT INTO `ums_member_remind` VALUES ('18', '5', '2', '29', '待审核提现', '「19859222511」进行了提现, 请尽快审批', '1', '2021-12-30 17:54:05', null, '2021-12-30 17:54:16', null);
INSERT INTO `ums_member_remind` VALUES ('19', '5', '2', '30', '待审核提现', '「19859222511」进行了提现, 请尽快审批', '0', '2021-12-30 17:54:05', null, '2021-12-30 17:54:05', null);
INSERT INTO `ums_member_remind` VALUES ('20', '4', '2', '29', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '1', '2021-12-30 17:56:22', null, '2021-12-30 17:57:20', null);
INSERT INTO `ums_member_remind` VALUES ('21', '4', '2', '30', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '0', '2021-12-30 17:56:22', null, '2021-12-30 17:56:22', null);
INSERT INTO `ums_member_remind` VALUES ('22', '4', '2', '29', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '1', '2021-12-30 17:56:46', null, '2021-12-30 17:57:20', null);
INSERT INTO `ums_member_remind` VALUES ('23', '4', '2', '30', '待审核充值', '「19859222511」进行了充值, 请尽快审批', '0', '2021-12-30 17:56:46', null, '2021-12-30 17:56:46', null);
INSERT INTO `ums_member_remind` VALUES ('24', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367807077', '1', '2021-12-31 15:03:24', null, '2022-01-05 21:04:04', null);
INSERT INTO `ums_member_remind` VALUES ('25', '2', '2', '29', '待审核买号', '「13666011848」绑定了新买号, 请尽快审批', '1', '2022-01-01 22:18:49', null, '2022-01-05 19:05:25', null);
INSERT INTO `ums_member_remind` VALUES ('26', '2', '2', '30', '待审核买号', '「13666011848」绑定了新买号, 请尽快审批', '0', '2022-01-01 22:18:49', null, '2022-01-01 22:18:49', null);
INSERT INTO `ums_member_remind` VALUES ('27', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367807077', '1', '2022-01-04 16:03:10', null, '2022-01-05 21:04:04', null);
INSERT INTO `ums_member_remind` VALUES ('28', '4', '1', '900', '等待返款', '任务编号：R1638606367807077 的买家已经下单了,请尽快核实并发货返款', '1', '2022-01-04 16:04:25', null, '2022-01-05 21:04:04', null);
INSERT INTO `ums_member_remind` VALUES ('29', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367811828', '1', '2022-01-05 15:36:10', null, '2022-01-05 21:04:04', null);
INSERT INTO `ums_member_remind` VALUES ('30', '4', '1', '900', '等待返款', '任务编号：R1638606367811828 的买家已经下单了,请尽快核实并发货返款', '1', '2022-01-05 15:38:05', null, '2022-01-05 21:04:04', null);
INSERT INTO `ums_member_remind` VALUES ('31', '5', '1', '900', '等待审核评价', '任务编号：R1638606367811828 的买家已经收货评价了，请尽快审核', '1', '2022-01-05 15:42:33', null, '2022-01-05 21:04:04', null);
INSERT INTO `ums_member_remind` VALUES ('32', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-10 00:50:00', null, '2022-01-10 01:39:00', null);
INSERT INTO `ums_member_remind` VALUES ('33', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-10 17:58:22', null, '2022-01-14 11:01:04', null);
INSERT INTO `ums_member_remind` VALUES ('34', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1641899201879702', '1', '2022-01-11 19:08:46', null, '2022-01-14 11:01:04', null);
INSERT INTO `ums_member_remind` VALUES ('35', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1641898927704888', '1', '2022-01-11 19:12:13', null, '2022-01-14 11:01:04', null);
INSERT INTO `ums_member_remind` VALUES ('36', '4', '1', '900', '等待返款', '任务编号：R1641898927704888 的买家已经下单了,请尽快核实并发货返款', '1', '2022-01-11 19:12:47', null, '2022-01-14 11:01:04', null);
INSERT INTO `ums_member_remind` VALUES ('37', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-11 21:04:09', null, '2022-01-14 11:01:04', null);
INSERT INTO `ums_member_remind` VALUES ('38', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-14 09:31:52', null, '2022-01-14 11:01:04', null);
INSERT INTO `ums_member_remind` VALUES ('39', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-14 15:50:00', null, '2022-01-18 20:51:28', null);
INSERT INTO `ums_member_remind` VALUES ('40', '2', '2', '29', '待审核买号', '「13638896017」绑定了新买号, 请尽快审批', '1', '2022-01-14 23:39:54', null, '2022-01-30 00:12:24', null);
INSERT INTO `ums_member_remind` VALUES ('41', '2', '2', '30', '待审核买号', '「13638896017」绑定了新买号, 请尽快审批', '0', '2022-01-14 23:39:54', null, '2022-01-14 23:39:54', null);
INSERT INTO `ums_member_remind` VALUES ('42', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-16 16:05:12', null, '2022-01-18 20:51:28', null);
INSERT INTO `ums_member_remind` VALUES ('43', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1639791191953316', '1', '2022-01-17 16:11:51', null, '2022-01-18 20:51:28', null);
INSERT INTO `ums_member_remind` VALUES ('44', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1639791191953316', '1', '2022-01-17 17:28:35', null, '2022-01-18 20:51:28', null);
INSERT INTO `ums_member_remind` VALUES ('45', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-17 18:11:01', null, '2022-01-18 20:51:28', null);
INSERT INTO `ums_member_remind` VALUES ('46', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-18 11:19:27', null, '2022-01-18 20:51:28', null);
INSERT INTO `ums_member_remind` VALUES ('47', '5', '1', '900', '等待审核评价', '任务编号：R1639791948412087 的买家已经收货评价了，请尽快审核', '1', '2022-01-18 15:06:50', null, '2022-01-18 20:51:28', null);
INSERT INTO `ums_member_remind` VALUES ('48', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-18 15:18:36', null, '2022-01-18 20:51:28', null);
INSERT INTO `ums_member_remind` VALUES ('49', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367815683', '1', '2022-01-24 10:11:59', null, '2022-01-30 00:34:57', null);
INSERT INTO `ums_member_remind` VALUES ('50', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367819461', '1', '2022-01-24 11:02:11', null, '2022-01-30 00:34:57', null);
INSERT INTO `ums_member_remind` VALUES ('51', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367819461', '1', '2022-01-30 00:09:42', null, '2022-01-30 00:34:57', null);
INSERT INTO `ums_member_remind` VALUES ('52', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367819461', '1', '2022-01-30 00:50:35', null, '2022-02-04 23:52:42', null);
INSERT INTO `ums_member_remind` VALUES ('53', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1643474881851875', '1', '2022-01-30 00:58:55', null, '2022-02-04 23:52:42', null);
INSERT INTO `ums_member_remind` VALUES ('54', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1638606367819461', '1', '2022-01-30 01:30:00', null, '2022-02-04 23:52:42', null);
INSERT INTO `ums_member_remind` VALUES ('55', '4', '1', '900', '等待返款', '任务编号：R1643474881851875 的买家已经下单了,请尽快核实并发货返款', '1', '2022-01-30 01:36:59', null, '2022-02-04 23:52:42', null);
INSERT INTO `ums_member_remind` VALUES ('56', '5', '1', '900', '等待审核评价', '任务编号：R1643474881851875 的买家已经收货评价了，请尽快审核', '1', '2022-01-30 21:08:57', null, '2022-02-04 23:52:42', null);
INSERT INTO `ums_member_remind` VALUES ('57', '6', '1', '900', '等待审核追评', '任务编号：R1643474881851875 的买家已进行追加评价, 请尽快审核', '1', '2022-02-05 00:06:12', null, '2022-02-13 01:10:28', null);
INSERT INTO `ums_member_remind` VALUES ('58', '2', '1', '900', '任务被接单', '亲，有人接到您的任务了，任务编号: R1643990501862377', '1', '2022-02-05 00:14:39', null, '2022-02-13 01:10:28', null);
INSERT INTO `ums_member_remind` VALUES ('59', '3', '1', '900', '等待审核买号', '亲，您有任务已被接手，需要你审核买号，任务编号: R1643990501862377', '1', '2022-02-05 00:14:39', null, '2022-02-13 01:10:28', null);
INSERT INTO `ums_member_remind` VALUES ('60', '4', '1', '900', '等待返款', '任务编号：R1643990501862377 的买家已经下单了,请尽快核实并发货返款', '1', '2022-02-05 00:28:49', null, '2022-02-13 01:10:28', null);
INSERT INTO `ums_member_remind` VALUES ('61', '1', '1', '900', '提现到账', '您提现的50.00元已到账,请注意查收!', '1', '2022-02-11 21:01:09', null, '2022-02-13 01:10:28', null);
INSERT INTO `ums_member_remind` VALUES ('62', '4', '2', '29', '待审核充值', '「19859222551」进行了充值, 请尽快审批', '1', '2022-02-12 17:31:47', null, '2022-02-13 00:08:41', null);
INSERT INTO `ums_member_remind` VALUES ('63', '4', '2', '30', '待审核充值', '「19859222551」进行了充值, 请尽快审批', '0', '2022-02-12 17:31:47', null, '2022-02-12 17:31:47', null);
INSERT INTO `ums_member_remind` VALUES ('64', '4', '2', '31', '待审核充值', '「19859222551」进行了充值, 请尽快审批', '0', '2022-02-12 17:31:47', null, '2022-02-12 17:31:47', null);
INSERT INTO `ums_member_remind` VALUES ('65', '5', '1', '900', '等待审核评价', '任务编号：R1643990501862377 的买家已经收货评价了，请尽快审核', '0', '2022-02-15 12:07:58', null, '2022-02-15 12:07:58', null);
INSERT INTO `ums_member_remind` VALUES ('66', '5', '2', '29', '待审核提现', '「13444555666」进行了提现, 请尽快审批', '0', '2022-05-12 14:53:25', null, '2022-05-12 14:53:25', null);
INSERT INTO `ums_member_remind` VALUES ('67', '5', '2', '29', '待审核提现', '「13444555666」进行了提现, 请尽快审批', '0', '2022-05-12 14:53:48', null, '2022-05-12 14:53:48', null);
INSERT INTO `ums_member_remind` VALUES ('68', '5', '2', '29', '待审核提现', '「13444555666」进行了提现, 请尽快审批', '0', '2022-05-12 14:53:56', null, '2022-05-12 14:53:56', null);
INSERT INTO `ums_member_remind` VALUES ('69', '5', '2', '29', '待审核提现', '「13444555666」进行了提现, 请尽快审批', '0', '2022-05-12 14:56:29', null, '2022-05-12 14:56:29', null);
INSERT INTO `ums_member_remind` VALUES ('70', '5', '2', '29', '待审核提现', '「13444555666」进行了提现, 请尽快审批', '0', '2022-05-12 14:59:38', null, '2022-05-12 14:59:38', null);

-- ----------------------------
-- Table structure for ums_member_sign
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_sign`;
CREATE TABLE `ums_member_sign` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int NOT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `sign_date` date DEFAULT NULL COMMENT '签到日期',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户签到表';

-- ----------------------------
-- Records of ums_member_sign
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_spmission_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_spmission_log`;
CREATE TABLE `ums_member_spmission_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT NULL COMMENT '资金类型',
  `in_out` int DEFAULT NULL COMMENT '收支类型 1收入 -1支出',
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `devote_id` int DEFAULT NULL COMMENT '贡献会员ID',
  `devote_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '贡献用户名',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `amount` decimal(15,2) DEFAULT NULL COMMENT '金额',
  `amount_before` decimal(15,2) DEFAULT NULL COMMENT '操作前金额',
  `amount_after` decimal(15,2) DEFAULT NULL COMMENT '操作后金额',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP',
  `operate_ip_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP实际地址',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='推广佣金明细';

-- ----------------------------
-- Records of ums_member_spmission_log
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_spmission_plan_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_spmission_plan_log`;
CREATE TABLE `ums_member_spmission_plan_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int DEFAULT NULL COMMENT '资金类型',
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `devote_id` int DEFAULT NULL COMMENT '贡献会员ID',
  `devote_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '贡献用户名',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `amount` decimal(15,2) DEFAULT NULL COMMENT '金额',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `status` int DEFAULT '0' COMMENT '0待入账>>1已入账>>2已取消',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='推广佣金预计收入明细';

-- ----------------------------
-- Records of ums_member_spmission_plan_log
-- ----------------------------
INSERT INTO `ums_member_spmission_plan_log` VALUES ('1', '3', '905', '19859222512', '909', '13444555777', 'R1636980024669834', '0.60', '[预计]完成任务成功获得一级推荐奖励', '0', '2021-12-04 11:48:39', null, '2021-12-04 11:48:39', null);
INSERT INTO `ums_member_spmission_plan_log` VALUES ('2', '3', '893', '13666011848', '909', '13444555777', 'R1636980024669834', '5.00', '[预计]完成任务成功获得二级推荐奖励', '0', '2021-12-04 11:48:39', null, '2021-12-04 11:48:39', null);
INSERT INTO `ums_member_spmission_plan_log` VALUES ('3', '3', '893', '13666011848', '905', '19859222512', 'R1639791948412087', '10.00', '[预计]完成任务成功获得一级推荐奖励', '0', '2021-12-18 17:07:18', null, '2021-12-18 17:07:18', null);
INSERT INTO `ums_member_spmission_plan_log` VALUES ('4', '3', '905', '19859222512', '909', '13444555777', 'R1638606367811828', '1.00', '[预计]完成任务成功获得一级推荐奖励', '0', '2022-01-05 15:38:04', null, '2022-01-05 15:38:04', null);
INSERT INTO `ums_member_spmission_plan_log` VALUES ('5', '3', '893', '13666011848', '909', '13444555777', 'R1638606367811828', '5.00', '[预计]完成任务成功获得二级推荐奖励', '0', '2022-01-05 15:38:04', null, '2022-01-05 15:38:04', null);
INSERT INTO `ums_member_spmission_plan_log` VALUES ('6', '16', '905', '19859222512', '909', '13444555777', 'R1638606367811828', '0.50', '[预计]大师傅奖励', '0', '2022-01-05 15:38:05', null, '2022-01-05 15:38:05', null);

-- ----------------------------
-- Table structure for ums_member_view_goods
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_view_goods`;
CREATE TABLE `ums_member_view_goods` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `date` date DEFAULT NULL COMMENT '浏览时间',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '单价',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`,`goods_id`,`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品足迹表';

-- ----------------------------
-- Records of ums_member_view_goods
-- ----------------------------
INSERT INTO `ums_member_view_goods` VALUES ('1', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 16:46:17', null, '2022-05-16 16:46:17', null);
INSERT INTO `ums_member_view_goods` VALUES ('2', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 16:51:39', null, '2022-05-16 16:51:39', null);
INSERT INTO `ums_member_view_goods` VALUES ('3', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 16:55:53', null, '2022-05-16 16:55:53', null);
INSERT INTO `ums_member_view_goods` VALUES ('4', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 16:59:32', null, '2022-05-16 16:59:32', null);
INSERT INTO `ums_member_view_goods` VALUES ('5', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:06:04', null, '2022-05-16 17:06:04', null);
INSERT INTO `ums_member_view_goods` VALUES ('6', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:08:05', null, '2022-05-16 17:08:05', null);
INSERT INTO `ums_member_view_goods` VALUES ('7', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:08:19', null, '2022-05-16 17:08:19', null);
INSERT INTO `ums_member_view_goods` VALUES ('8', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:16:40', null, '2022-05-16 17:16:40', null);
INSERT INTO `ums_member_view_goods` VALUES ('9', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:24:52', null, '2022-05-16 17:24:52', null);
INSERT INTO `ums_member_view_goods` VALUES ('10', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:29:45', null, '2022-05-16 17:29:45', null);
INSERT INTO `ums_member_view_goods` VALUES ('11', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:31:37', null, '2022-05-16 17:31:37', null);
INSERT INTO `ums_member_view_goods` VALUES ('12', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:35:39', null, '2022-05-16 17:35:39', null);
INSERT INTO `ums_member_view_goods` VALUES ('13', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:44:39', null, '2022-05-16 17:44:39', null);
INSERT INTO `ums_member_view_goods` VALUES ('14', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:46:19', null, '2022-05-16 17:46:19', null);
INSERT INTO `ums_member_view_goods` VALUES ('15', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:48:11', null, '2022-05-16 17:48:11', null);
INSERT INTO `ums_member_view_goods` VALUES ('16', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:48:31', null, '2022-05-16 17:48:31', null);
INSERT INTO `ums_member_view_goods` VALUES ('17', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:49:48', null, '2022-05-16 17:49:48', null);
INSERT INTO `ums_member_view_goods` VALUES ('18', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:52:02', null, '2022-05-16 17:52:02', null);
INSERT INTO `ums_member_view_goods` VALUES ('19', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:52:17', null, '2022-05-16 17:52:17', null);
INSERT INTO `ums_member_view_goods` VALUES ('20', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:52:49', null, '2022-05-16 17:52:49', null);
INSERT INTO `ums_member_view_goods` VALUES ('21', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:53:37', null, '2022-05-16 17:53:37', null);
INSERT INTO `ums_member_view_goods` VALUES ('22', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:59:01', null, '2022-05-16 17:59:01', null);
INSERT INTO `ums_member_view_goods` VALUES ('23', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 17:59:08', null, '2022-05-16 17:59:08', null);
INSERT INTO `ums_member_view_goods` VALUES ('24', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:04:07', null, '2022-05-16 18:04:07', null);
INSERT INTO `ums_member_view_goods` VALUES ('25', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:11:27', null, '2022-05-16 18:11:27', null);
INSERT INTO `ums_member_view_goods` VALUES ('26', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:12:02', null, '2022-05-16 18:12:02', null);
INSERT INTO `ums_member_view_goods` VALUES ('27', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:12:56', null, '2022-05-16 18:12:56', null);
INSERT INTO `ums_member_view_goods` VALUES ('28', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:12:56', null, '2022-05-16 18:12:56', null);
INSERT INTO `ums_member_view_goods` VALUES ('29', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:15:46', null, '2022-05-16 18:15:46', null);
INSERT INTO `ums_member_view_goods` VALUES ('30', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:39:07', null, '2022-05-16 18:39:07', null);
INSERT INTO `ums_member_view_goods` VALUES ('31', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:39:51', null, '2022-05-16 18:39:51', null);
INSERT INTO `ums_member_view_goods` VALUES ('32', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:42:23', null, '2022-05-16 18:42:23', null);
INSERT INTO `ums_member_view_goods` VALUES ('33', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:46:44', null, '2022-05-16 18:46:44', null);
INSERT INTO `ums_member_view_goods` VALUES ('34', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:48:37', null, '2022-05-16 18:48:37', null);
INSERT INTO `ums_member_view_goods` VALUES ('35', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:50:53', null, '2022-05-16 18:50:53', null);
INSERT INTO `ums_member_view_goods` VALUES ('36', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:53:10', null, '2022-05-16 18:53:10', null);
INSERT INTO `ums_member_view_goods` VALUES ('37', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-16 18:54:00', null, '2022-05-16 18:54:00', null);
INSERT INTO `ums_member_view_goods` VALUES ('38', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-17 15:25:35', null, '2022-05-17 15:25:35', null);
INSERT INTO `ums_member_view_goods` VALUES ('39', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 15:25:46', null, '2022-05-17 15:25:46', null);
INSERT INTO `ums_member_view_goods` VALUES ('40', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '7', '亚克力门牌号码牌家用定制门牌号创意个性房间酒店可爱情侣房号数字门贴民宿入户轻奢包厢牌子字订制木质贴牌', 'https://qiniu.chengyuwb.com/1652772544350.jpg', '38.00', '2022-05-17 15:29:40', null, '2022-05-17 15:29:40', null);
INSERT INTO `ums_member_view_goods` VALUES ('41', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:07:05', null, '2022-05-17 17:07:05', null);
INSERT INTO `ums_member_view_goods` VALUES ('42', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:10:32', null, '2022-05-17 17:10:32', null);
INSERT INTO `ums_member_view_goods` VALUES ('43', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:11:12', null, '2022-05-17 17:11:12', null);
INSERT INTO `ums_member_view_goods` VALUES ('44', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:13:18', null, '2022-05-17 17:13:18', null);
INSERT INTO `ums_member_view_goods` VALUES ('45', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:14:24', null, '2022-05-17 17:14:24', null);
INSERT INTO `ums_member_view_goods` VALUES ('46', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:14:32', null, '2022-05-17 17:14:32', null);
INSERT INTO `ums_member_view_goods` VALUES ('47', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:14:55', null, '2022-05-17 17:14:55', null);
INSERT INTO `ums_member_view_goods` VALUES ('48', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:15:23', null, '2022-05-17 17:15:23', null);
INSERT INTO `ums_member_view_goods` VALUES ('49', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:16:20', null, '2022-05-17 17:16:20', null);
INSERT INTO `ums_member_view_goods` VALUES ('50', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:52:11', null, '2022-05-17 17:52:11', null);
INSERT INTO `ums_member_view_goods` VALUES ('51', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:52:16', null, '2022-05-17 17:52:16', null);
INSERT INTO `ums_member_view_goods` VALUES ('52', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:53:10', null, '2022-05-17 17:53:10', null);
INSERT INTO `ums_member_view_goods` VALUES ('53', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:54:42', null, '2022-05-17 17:54:42', null);
INSERT INTO `ums_member_view_goods` VALUES ('54', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:57:33', null, '2022-05-17 17:57:33', null);
INSERT INTO `ums_member_view_goods` VALUES ('55', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 17:59:01', null, '2022-05-17 17:59:01', null);
INSERT INTO `ums_member_view_goods` VALUES ('56', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:00:18', null, '2022-05-17 18:00:18', null);
INSERT INTO `ums_member_view_goods` VALUES ('57', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:01:45', null, '2022-05-17 18:01:45', null);
INSERT INTO `ums_member_view_goods` VALUES ('58', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:03:27', null, '2022-05-17 18:03:27', null);
INSERT INTO `ums_member_view_goods` VALUES ('59', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:03:29', null, '2022-05-17 18:03:29', null);
INSERT INTO `ums_member_view_goods` VALUES ('60', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:03:32', null, '2022-05-17 18:03:32', null);
INSERT INTO `ums_member_view_goods` VALUES ('61', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:04:06', null, '2022-05-17 18:04:06', null);
INSERT INTO `ums_member_view_goods` VALUES ('62', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:05:07', null, '2022-05-17 18:05:07', null);
INSERT INTO `ums_member_view_goods` VALUES ('63', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:06:46', null, '2022-05-17 18:06:46', null);
INSERT INTO `ums_member_view_goods` VALUES ('64', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:07:07', null, '2022-05-17 18:07:07', null);
INSERT INTO `ums_member_view_goods` VALUES ('65', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:07:21', null, '2022-05-17 18:07:21', null);
INSERT INTO `ums_member_view_goods` VALUES ('66', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:07:42', null, '2022-05-17 18:07:42', null);
INSERT INTO `ums_member_view_goods` VALUES ('67', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:09:41', null, '2022-05-17 18:09:41', null);
INSERT INTO `ums_member_view_goods` VALUES ('68', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:10:15', null, '2022-05-17 18:10:15', null);
INSERT INTO `ums_member_view_goods` VALUES ('69', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:11:16', null, '2022-05-17 18:11:16', null);
INSERT INTO `ums_member_view_goods` VALUES ('70', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:11:18', null, '2022-05-17 18:11:18', null);
INSERT INTO `ums_member_view_goods` VALUES ('71', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:11:46', null, '2022-05-17 18:11:46', null);
INSERT INTO `ums_member_view_goods` VALUES ('72', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:11:51', null, '2022-05-17 18:11:51', null);
INSERT INTO `ums_member_view_goods` VALUES ('73', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:12:46', null, '2022-05-17 18:12:46', null);
INSERT INTO `ums_member_view_goods` VALUES ('74', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:13:41', null, '2022-05-17 18:13:41', null);
INSERT INTO `ums_member_view_goods` VALUES ('75', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:22:20', null, '2022-05-17 18:22:20', null);
INSERT INTO `ums_member_view_goods` VALUES ('76', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:22:45', null, '2022-05-17 18:22:45', null);
INSERT INTO `ums_member_view_goods` VALUES ('77', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:31:30', null, '2022-05-17 18:31:30', null);
INSERT INTO `ums_member_view_goods` VALUES ('78', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:52:36', null, '2022-05-17 18:52:36', null);
INSERT INTO `ums_member_view_goods` VALUES ('79', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '4', '三件套北欧ins风玻璃花瓶透明干花客厅插花水养绿萝水培百合摆件', 'https://qiniu.chengyuwb.com/1652518263793.jpg', '2.00', '2022-05-17 18:56:11', null, '2022-05-17 18:56:11', null);
INSERT INTO `ums_member_view_goods` VALUES ('80', null, '5', 'yyw', 'https://qiniu.chengyuwb.com/1652685527471.jpeg', '6', '土陶粒 无土栽培植物专用陶粒 隔水透气 防止烂根效果好', 'https://qiniu.chengyuwb.com/1652519851448.jpg', '1.00', '2022-05-18 09:37:54', null, '2022-05-18 09:37:54', null);

-- ----------------------------
-- Table structure for ums_member_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_withdraw`;
CREATE TABLE `ums_member_withdraw` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '提款单号',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `type` int DEFAULT NULL COMMENT '提现方式>>1银行>>2支付宝>>3微信',
  `method` int DEFAULT NULL COMMENT '出款方式>>1账户资金>>2佣金资金',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行',
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '银行名称',
  `bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收款账号',
  `amount` decimal(18,2) DEFAULT NULL COMMENT '提款金额',
  `fee` decimal(18,2) DEFAULT NULL COMMENT '手续费',
  `total_amount` decimal(18,2) DEFAULT NULL COMMENT '实际到账金额',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态 0待审核 1提现成功 2提现失败',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `register_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册IP',
  `register_ip_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册IP实际地址',
  `withdraw_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '提现IP',
  `withdraw_ip_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '提现IP实际地址',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户提现';

-- ----------------------------
-- Records of ums_member_withdraw
-- ----------------------------
INSERT INTO `ums_member_withdraw` VALUES ('1', '1652338404561437', '4', '13444555666', '1', '1', '杨大大', '中国农业银行', '中国农业银行', '62284899900131231231', '10.00', '0.00', '10.00', '0', null, null, null, null, null, '2022-05-12 14:53:25', null, null, null);
INSERT INTO `ums_member_withdraw` VALUES ('2', '1652338428122607', '4', '13444555666', '3', '1', '杨小小', '微信', '微信', 'https://qiniu.chengyuwb.com/1652327833580.png', '20.00', '0.00', '20.00', '0', null, null, null, null, null, '2022-05-12 14:53:48', null, null, null);
INSERT INTO `ums_member_withdraw` VALUES ('3', '1652338435757438', '4', '13444555666', '2', '1', '杨大大', '支付宝', '支付宝', null, '30.00', '0.00', '30.00', '0', null, null, null, null, null, '2022-05-12 14:53:56', null, null, null);
INSERT INTO `ums_member_withdraw` VALUES ('4', '1652338588780136', '4', '13444555666', '2', '1', '杨大大', '支付宝', '支付宝', '13444555666@qq.com', '40.00', '0.00', '40.00', '0', null, null, null, null, null, '2022-05-12 14:56:29', null, null, null);
INSERT INTO `ums_member_withdraw` VALUES ('5', '1652338777786963', '4', '13444555666', '3', '1', '杨小小', '微信', '微信', 'https://qiniu.chengyuwb.com/1652327833580.png', '0.00', '0.00', '0.00', '0', null, null, null, null, null, '2022-05-12 14:59:38', null, null, null);

-- ----------------------------
-- Table structure for ums_phonebook_invite
-- ----------------------------
DROP TABLE IF EXISTS `ums_phonebook_invite`;
CREATE TABLE `ums_phonebook_invite` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `phone_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机唯一标识',
  `phone_model` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机型号',
  `this_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '本机号码',
  `invite_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邀请码',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='手机通讯录邀请';

-- ----------------------------
-- Records of ums_phonebook_invite
-- ----------------------------

-- ----------------------------
-- Table structure for ums_phonebook_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_phonebook_log`;
CREATE TABLE `ums_phonebook_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `invite_id` int DEFAULT NULL COMMENT '邀请ID',
  `phone_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机唯一标识',
  `phone_model` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机型号',
  `this_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '本机号码',
  `invite_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邀请码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`),
  KEY `invite_id` (`invite_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='手机通讯录';

-- ----------------------------
-- Records of ums_phonebook_log
-- ----------------------------

-- ----------------------------
-- Table structure for ums_phonebook_message
-- ----------------------------
DROP TABLE IF EXISTS `ums_phonebook_message`;
CREATE TABLE `ums_phonebook_message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `invite_id` int DEFAULT NULL COMMENT '邀请ID',
  `phone_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机唯一标识',
  `phone_model` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机型号',
  `this_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '本机号码',
  `invite_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邀请码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发件人',
  `message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '短信内容',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `msg_status` int DEFAULT '0' COMMENT '0发送>>1接收>>2草稿',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`),
  KEY `invite_id` (`invite_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='手机短信';

-- ----------------------------
-- Records of ums_phonebook_message
-- ----------------------------

-- ----------------------------
-- Table structure for ums_shop
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop`;
CREATE TABLE `ums_shop` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `type` int DEFAULT NULL COMMENT '店铺类型>>1个人>>2企业>>3旗舰',
  `level` int DEFAULT NULL COMMENT '店铺等级',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺名称',
  `keyword` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关键字',
  `produce` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `service` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '服务',
  `service_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '服务名称(逗号隔开)',
  `logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'LOGO',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
  `province_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
  `city_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
  `area_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `like_num` int DEFAULT '0' COMMENT '收藏喜欢数量',
  `goods_star` decimal(18,2) DEFAULT '0.00' COMMENT '宝贝描述评分',
  `seller_star` decimal(18,2) DEFAULT '0.00' COMMENT '卖家服务评分',
  `logistic_star` decimal(18,2) DEFAULT '0.00' COMMENT '物流评分',
  `weight` int DEFAULT '0' COMMENT '权重',
  `sort` int DEFAULT NULL COMMENT '排序',
  `status` int DEFAULT NULL COMMENT '状态(0关闭>>1正常)',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `self_status` int DEFAULT '0' COMMENT '是否自营',
  `live_status` int DEFAULT '0' COMMENT '直播权限',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺表';

-- ----------------------------
-- Records of ums_shop
-- ----------------------------
INSERT INTO `ums_shop` VALUES ('2', '1', '19859222511', '3', '0', '天宫和一高', '天宫和一高', '天宫和一高', '1,2,3,4,5', '', 'https://qiniu.chengyuwb.com/1652254610020.jpg', '天津市', '120000', '市辖区', '120100', '河东区', '120102', '天宫和一高', '999', '4.50', '4.50', '4.50', '0', '9999', '1', '天宫和一高', '0', null, '2022-05-11 15:41:10', '', '2022-05-11 15:43:04', '');
INSERT INTO `ums_shop` VALUES ('3', '2', '19859222512', '1', '0', '天宫个人', null, '<p>天宫个人</p>', null, null, 'https://qiniu.chengyuwb.com/1652255308928.jpg', '河北省', '130000', '唐山市', '130200', '路北区', '130203', '天宫个人', '0', '0.00', '0.00', '0.00', '0', '9999', '1', '天宫个人', '0', '0', '2022-05-11 15:48:52', null, '2022-05-11 15:48:52', null);
INSERT INTO `ums_shop` VALUES ('4', '4', '13444555666', '1', '0', 'LEKSHOP旗舰店', '2', '22', '2,3,4', '', 'https://qiniu.chengyuwb.com/1652321436403.png', '山西省', '140000', '长治市', '140400', '上党区', '140404', '2', '0', '0.00', '0.00', '0.00', '0', '9999', '1', 'asdasdasda', '0', '0', '2022-05-12 10:32:01', '', '2022-05-12 17:30:59', '');

-- ----------------------------
-- Table structure for ums_shop_cate
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_cate`;
CREATE TABLE `ums_shop_cate` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int DEFAULT NULL COMMENT '店铺ID',
  `shop_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺名称',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `level` int DEFAULT NULL COMMENT '分类等级(1一级 2二级 3三级)',
  `pid` int DEFAULT '0' COMMENT '一级分类ID',
  `sort` int DEFAULT '0' COMMENT '排序',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类展示图',
  `status` int DEFAULT '1' COMMENT '显示隐藏',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺分类表';

-- ----------------------------
-- Records of ums_shop_cate
-- ----------------------------
INSERT INTO `ums_shop_cate` VALUES ('16', '4', 'LEKSHOP旗舰店', '女装', '1', '0', '1', 'https://qiniu.chengyuwb.com/1652524805171.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('17', '4', 'LEKSHOP旗舰店', '母婴', '1', '0', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('18', '4', 'LEKSHOP旗舰店', '手机数码', '1', '0', '3', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('19', '4', 'LEKSHOP旗舰店', '男装', '1', '0', '4', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('20', '4', 'LEKSHOP旗舰店', '鞋靴', '1', '0', '5', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('21', '4', 'LEKSHOP旗舰店', '生鲜', '1', '0', '6', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('22', '4', 'LEKSHOP旗舰店', '美妆洗护', '1', '0', '7', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('23', '4', 'LEKSHOP旗舰店', '百货', '1', '0', '8', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('24', '4', 'LEKSHOP旗舰店', '家电', '1', '0', '9', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('25', '4', 'LEKSHOP旗舰店', '饰品', '1', '0', '10', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('26', '4', 'LEKSHOP旗舰店', '箱包', '1', '0', '11', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('27', '4', 'LEKSHOP旗舰店', '女裤', '2', '16', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('28', '4', 'LEKSHOP旗舰店', '上装', '2', '16', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('29', '4', 'LEKSHOP旗舰店', '女裙', '2', '16', '3', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('30', '4', 'LEKSHOP旗舰店', '套装', '2', '16', '4', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('31', '4', 'LEKSHOP旗舰店', '外套', '2', '16', '5', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('32', '4', 'LEKSHOP旗舰店', '儿童玩具', '2', '17', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('33', '4', 'LEKSHOP旗舰店', '童装', '2', '17', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('34', '4', 'LEKSHOP旗舰店', '童鞋', '2', '17', '3', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('35', '4', 'LEKSHOP旗舰店', '洗护用品', '2', '17', '4', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('36', '4', 'LEKSHOP旗舰店', '孕产穿搭', '2', '17', '5', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('37', '4', 'LEKSHOP旗舰店', '哺乳喂养', '2', '17', '6', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('38', '4', 'LEKSHOP旗舰店', '儿童家具', '2', '17', '7', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('39', '4', 'LEKSHOP旗舰店', '婴儿装', '2', '17', '8', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('40', '4', 'LEKSHOP旗舰店', '玩具车', '2', '17', '9', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('41', '4', 'LEKSHOP旗舰店', '宝宝出行', '2', '17', '10', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('42', '4', 'LEKSHOP旗舰店', '孕产用品', '2', '17', '11', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('43', '4', 'LEKSHOP旗舰店', '奶粉营养', '2', '17', '12', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('44', '4', 'LEKSHOP旗舰店', '手机', '2', '18', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('45', '4', 'LEKSHOP旗舰店', '3C配件', '2', '18', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('46', '4', 'LEKSHOP旗舰店', '维修', '2', '18', '4', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('47', '4', 'LEKSHOP旗舰店', '裤子', '2', '19', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('48', '4', 'LEKSHOP旗舰店', '上装', '2', '19', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('49', '4', 'LEKSHOP旗舰店', '女鞋', '2', '20', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('50', '4', 'LEKSHOP旗舰店', '男鞋', '2', '20', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('51', '4', 'LEKSHOP旗舰店', '美味蔬菜', '2', '21', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('52', '4', 'LEKSHOP旗舰店', '禽蛋', '2', '21', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('53', '4', 'LEKSHOP旗舰店', '时令生鲜', '2', '21', '3', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('54', '4', 'LEKSHOP旗舰店', '新鲜肉类', '2', '21', '4', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('55', '4', 'LEKSHOP旗舰店', '彩妆', '2', '22', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('56', '4', 'LEKSHOP旗舰店', '美容美体', '2', '22', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('57', '4', 'LEKSHOP旗舰店', '护肤', '2', '22', '3', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('58', '4', 'LEKSHOP旗舰店', '居家日用', '2', '23', '1', '', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('59', '4', 'LEKSHOP旗舰店', '餐饮用具', '2', '23', '2', '', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('60', '4', 'LEKSHOP旗舰店', '绿植宠物', '2', '23', '3', '', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('61', '4', 'LEKSHOP旗舰店', '窗帘布艺', '2', '23', '4', '', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('62', '4', 'LEKSHOP旗舰店', '厨房用品', '2', '23', '5', '', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('63', '4', 'LEKSHOP旗舰店', '床上用品', '2', '23', '6', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('64', '4', 'LEKSHOP旗舰店', '家具装饰', '2', '23', '7', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('65', '4', 'LEKSHOP旗舰店', '收纳整理', '2', '23', '8', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('66', '4', 'LEKSHOP旗舰店', '清洁工具', '2', '23', '9', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('67', '4', 'LEKSHOP旗舰店', '厨房电器', '2', '24', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('68', '4', 'LEKSHOP旗舰店', '生活电器', '2', '24', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('69', '4', 'LEKSHOP旗舰店', '个护电器', '2', '24', '3', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('70', '4', 'LEKSHOP旗舰店', '大家电', '2', '24', '4', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('71', '4', 'LEKSHOP旗舰店', '手表', '2', '25', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('72', '4', 'LEKSHOP旗舰店', '眼镜', '2', '25', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('73', '4', 'LEKSHOP旗舰店', '珠宝', '2', '25', '3', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('74', '4', 'LEKSHOP旗舰店', '女士背包', '2', '26', '1', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('75', '4', 'LEKSHOP旗舰店', '男士背包', '2', '26', '2', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('76', '4', 'LEKSHOP旗舰店', '箱包', '2', '26', '3', null, '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('77', '4', 'LEKSHOP旗舰店', '休闲裤', '3', '27', '1', 'https://qiniu.chengyuwb.com/1652525191075.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('78', '4', 'LEKSHOP旗舰店', '牛仔裤', '3', '27', '2', 'https://qiniu.chengyuwb.com/1652525198511.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('79', '4', 'LEKSHOP旗舰店', '打底裤', '3', '27', '3', 'https://qiniu.chengyuwb.com/1652525220908.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('80', '4', 'LEKSHOP旗舰店', '西装裤', '3', '27', '4', 'https://qiniu.chengyuwb.com/1652525230079.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('81', '4', 'LEKSHOP旗舰店', 'T恤', '3', '28', '1', 'https://qiniu.chengyuwb.com/1652525239461.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('82', '4', 'LEKSHOP旗舰店', '衬衫', '3', '28', '2', 'https://qiniu.chengyuwb.com/1652525243341.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('83', '4', 'LEKSHOP旗舰店', '雪纺衫', '3', '28', '3', 'https://qiniu.chengyuwb.com/1652525246922.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('84', '4', 'LEKSHOP旗舰店', '卫衣', '3', '28', '4', 'https://qiniu.chengyuwb.com/1652525250913.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('85', '4', 'LEKSHOP旗舰店', '毛衣', '3', '28', '5', 'https://qiniu.chengyuwb.com/1652525262257.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('86', '4', 'LEKSHOP旗舰店', '马甲', '3', '28', '6', 'https://qiniu.chengyuwb.com/1652525275662.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('87', '4', 'LEKSHOP旗舰店', '连衣裙', '3', '29', '1', 'https://qiniu.chengyuwb.com/1652525292240.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('88', '4', 'LEKSHOP旗舰店', '半身裙', '3', '29', '2', 'https://qiniu.chengyuwb.com/1652525299016.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('89', '4', 'LEKSHOP旗舰店', '旗袍', '3', '29', '3', 'https://qiniu.chengyuwb.com/1652525303646.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('90', '4', 'LEKSHOP旗舰店', '时尚套装', '3', '30', '1', 'https://qiniu.chengyuwb.com/1652525310210.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('91', '4', 'LEKSHOP旗舰店', '运动套装', '3', '30', '2', 'https://qiniu.chengyuwb.com/1652525315632.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('92', '4', 'LEKSHOP旗舰店', '短外套', '3', '31', '1', 'https://qiniu.chengyuwb.com/1652525322146.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('93', '4', 'LEKSHOP旗舰店', '西装', '3', '31', '2', 'https://qiniu.chengyuwb.com/1652525331029.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('94', '4', 'LEKSHOP旗舰店', '风衣', '3', '31', '3', 'https://qiniu.chengyuwb.com/1652525337861.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('95', '4', 'LEKSHOP旗舰店', '羽绒服', '3', '31', '4', 'https://qiniu.chengyuwb.com/1652525341839.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('96', '4', 'LEKSHOP旗舰店', '毛呢大衣', '3', '31', '5', 'https://qiniu.chengyuwb.com/1652525347059.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('97', '4', 'LEKSHOP旗舰店', '棉衣棉服', '3', '31', '6', 'https://qiniu.chengyuwb.com/1652525355967.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('98', '4', 'LEKSHOP旗舰店', '皮草', '3', '31', '7', 'https://qiniu.chengyuwb.com/1652525360777.jpg', '1', '2022-05-16 10:09:05', null, null, null);
INSERT INTO `ums_shop_cate` VALUES ('99', '4', 'LEKSHOP旗舰店', '皮衣', '3', '31', '8', 'https://qiniu.chengyuwb.com/1652525364653.jpg', '1', '2022-05-16 10:09:05', null, null, null);

-- ----------------------------
-- Table structure for ums_shop_config
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_config`;
CREATE TABLE `ums_shop_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int DEFAULT NULL COMMENT '店铺ID',
  `shop_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺名称',
  `cate_style` int DEFAULT NULL COMMENT '类目样式>>1一级类目-大图>>2一级类目-九宫格>>3二级类目>>4多级类目样式',
  `delivery_fee_type` int DEFAULT '1' COMMENT '运费组合策略>>1运费叠加>>2以最低结算>>3以最高结算',
  `welcome` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IM欢迎消息',
  `kd_print` int DEFAULT '0' COMMENT '是否开启云打印',
  `kd_delivery` int DEFAULT '0' COMMENT '是否云打印自动发货',
  `kd_secret` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密钥',
  `kd_key` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '授权KEY',
  `kd_account` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '面单账号',
  `kd_password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '面单密码',
  `kd_equipment_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '打印设备码',
  `kd_template_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板ID',
  `send_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '寄件人',
  `send_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '寄件电话',
  `send_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '寄件地址',
  `return_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退货收件人',
  `return_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退货电话',
  `return_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '退货地址',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `shop_id` (`shop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺配置表';

-- ----------------------------
-- Records of ums_shop_config
-- ----------------------------
INSERT INTO `ums_shop_config` VALUES ('1', '2', '天宫和一高', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2022-05-11 15:41:10', null, '2022-05-11 15:41:10', null);
INSERT INTO `ums_shop_config` VALUES ('2', '3', '天宫个人', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2022-05-11 15:48:52', null, '2022-05-11 15:48:52', null);
INSERT INTO `ums_shop_config` VALUES ('3', '4', '2', '2', '3', null, '1', '1', '1', '1', '1', '1', '2', '2', '胡先生', '13412345678', '广东省广州市海珠区新港西路1450号', '胡先生', '13412345678', '广东省广州市海珠区新港西路1450号', '2022-05-12 10:32:01', null, '2022-05-12 11:04:58', null);

-- ----------------------------
-- Table structure for ums_shop_coupon
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_coupon`;
CREATE TABLE `ums_shop_coupon` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券名称',
  `type` int DEFAULT NULL COMMENT '1满减券>>2折扣券',
  `use_type` int DEFAULT NULL COMMENT '可用范围>>1全部商品>>2指定商品>>3指定分类',
  `use_goods_ids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '可使用商品',
  `use_goods_cate_ids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '可使用商品类别',
  `amount` decimal(18,2) DEFAULT '0.00' COMMENT '金额/折扣',
  `full_amount` decimal(18,2) DEFAULT NULL COMMENT '满减金额',
  `total_num` int DEFAULT NULL COMMENT '发放数量',
  `draw_num` int DEFAULT '0' COMMENT '已领取数量',
  `limit_num` int DEFAULT '1' COMMENT '每人限领次数',
  `validity_type` int DEFAULT '1' COMMENT '1长期有效>>2有限天数>>3固定时间',
  `validity_days` int DEFAULT NULL COMMENT '有效期(天)',
  `fixed_begin_date` date DEFAULT NULL COMMENT '固定开始日期',
  `fixed_end_date` date DEFAULT NULL COMMENT '固定结束日期',
  `begin_date` date DEFAULT NULL COMMENT '活动开始日期',
  `end_date` date DEFAULT NULL COMMENT '活动结束日期',
  `rules` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '使用规则',
  `status` int DEFAULT NULL COMMENT '0发放中  1已领完  2已过期',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='优惠券配置';

-- ----------------------------
-- Records of ums_shop_coupon
-- ----------------------------
INSERT INTO `ums_shop_coupon` VALUES ('1', '4', 'LEKSHOP旗舰店', '满100减5', '1', '1', null, null, '50.00', '100.00', '1000', '0', '1', '1', null, null, null, '2022-05-12', '2022-06-23', '啊大苏打萨达11', '1', '2022-05-13 09:35:53', null, '2022-05-13 09:35:53', null);
INSERT INTO `ums_shop_coupon` VALUES ('2', '4', 'LEKSHOP旗舰店', '满100打5折', '2', '2', '1,2', null, '5.00', '100.00', '500', '0', '1', '2', '10', null, null, '2022-05-13', '2022-05-31', '啊实打实大苏打', '1', '2022-05-13 09:52:17', null, '2022-05-13 09:52:17', null);
INSERT INTO `ums_shop_coupon` VALUES ('3', '4', 'LEKSHOP旗舰店', '新人优惠券', '1', '3', null, '2,7', '10.00', '50.00', '100', '0', '2', '3', null, '2022-05-13', '2022-06-30', '2022-05-13', '2022-05-31', '啊实打实大苏打', '1', '2022-05-13 10:17:45', null, '2022-05-13 10:17:45', null);

-- ----------------------------
-- Table structure for ums_shop_freight_template
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_freight_template`;
CREATE TABLE `ums_shop_freight_template` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int DEFAULT NULL COMMENT '商家ID(0为系统自营）',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
  `type` int DEFAULT NULL COMMENT '模板类型（1买家承担运费 2卖家包邮）',
  `valute_type` int DEFAULT NULL COMMENT '计价方式(1按件数 2按重量 3按体积)',
  `default_first_num` decimal(18,2) DEFAULT NULL COMMENT '首件(几件内运费)',
  `default_first_price` decimal(18,2) DEFAULT '0.00' COMMENT '首件价格(几件内运费为多少)',
  `default_second_num` decimal(18,2) DEFAULT NULL COMMENT '续件(每续几件运费)',
  `default_second_price` decimal(18,2) DEFAULT '0.00' COMMENT '续件价格(每续几件内运费为多少)',
  `delivery_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发货时间',
  `province_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省代码',
  `city_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市代码',
  `area_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区代码',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
  `area` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='运费模板表';

-- ----------------------------
-- Records of ums_shop_freight_template
-- ----------------------------
INSERT INTO `ums_shop_freight_template` VALUES ('3', '4', 'LEKSHOP旗舰店', '包邮模板', '2', '1', null, null, null, null, '16小时内', null, null, null, null, null, null);
INSERT INTO `ums_shop_freight_template` VALUES ('5', '4', 'LEKSHOP旗舰店', '包邮地区限制', '1', '1', '1.00', '0.00', '1.00', '0.00', '1天内', '130000', '130300', '130301', '河北省', '秦皇岛市', '市辖区');

-- ----------------------------
-- Table structure for ums_shop_freight_template_area
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_freight_template_area`;
CREATE TABLE `ums_shop_freight_template_area` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `template_id` int DEFAULT NULL COMMENT '运费模板ID',
  `first_num` decimal(18,2) DEFAULT NULL COMMENT '首件(几件内运费)',
  `first_price` decimal(18,2) DEFAULT '0.00' COMMENT '首件价格(几件内运费为多少)',
  `second_num` decimal(18,2) DEFAULT NULL COMMENT '续件(每续几件运费)',
  `second_price` decimal(18,2) DEFAULT '0.00' COMMENT '续件价格(每续几件内运费为多少)',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
  `province_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
  `city_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
  `area_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='运费模板地域表';

-- ----------------------------
-- Records of ums_shop_freight_template_area
-- ----------------------------
INSERT INTO `ums_shop_freight_template_area` VALUES ('28', '5', '1.00', '1.00', '1.00', '1.00', '黑龙江省', '230000', '全部', '', null, null);
INSERT INTO `ums_shop_freight_template_area` VALUES ('29', '5', '1.00', '10.00', '1.00', '10.00', '山西省', '140000', '阳泉市', '140300', null, null);

-- ----------------------------
-- Table structure for ums_shop_info
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_info`;
CREATE TABLE `ums_shop_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `type` int DEFAULT NULL COMMENT '店铺类型>>1个人>>2企业>>3旗舰',
  `realname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业名/姓名',
  `idcard` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证/统一信用',
  `card_img` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '证件图片',
  `brand_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商标证书',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `weixin` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺名称',
  `produce` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'LOGO',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
  `province_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
  `city_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
  `area_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `status` int DEFAULT NULL COMMENT '状态(0已提交资质>>1完善店铺>>2审核中>>3通过>>4驳回)',
  `reason` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '原因',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺信息审核表';

-- ----------------------------
-- Records of ums_shop_info
-- ----------------------------
INSERT INTO `ums_shop_info` VALUES ('2', '1', '19859222511', '3', '天宫和一高', '11221', 'https://qiniu.chengyuwb.com/1652254591423.jpg', 'https://qiniu.chengyuwb.com/1652254595043.jpg', '19859222511', '天宫和一高', '天宫和一高', '', 'https://qiniu.chengyuwb.com/1652254610020.jpg', '天津市', '120000', '市辖区', '120100', '河东区', '120102', '天宫和一高', '3', '上传图片违规', '天宫和一高', '2022-05-11 15:36:41', null, '2022-05-11 15:41:10', null);
INSERT INTO `ums_shop_info` VALUES ('3', '2', '19859222512', '1', '天宫个人', '12312312', 'https://qiniu.chengyuwb.com/1652255300071.png', '', '19859222512', '天宫个人', '天宫个人', '<p>天宫个人</p>', 'https://qiniu.chengyuwb.com/1652255308928.jpg', '河北省', '130000', '唐山市', '130200', '路北区', '130203', '天宫个人', '3', null, '天宫个人', '2022-05-11 15:48:21', null, '2022-05-11 15:48:52', null);
INSERT INTO `ums_shop_info` VALUES ('4', '4', '13444555666', '1', '1', '1', 'https://qiniu.chengyuwb.com/1652321428595.png', '', null, '123213123', '2', '22', 'https://qiniu.chengyuwb.com/1652321436403.png', '天津市', '120000', '市辖区', '120100', '河西区', '120103', '2', '3', '上传图片不清晰', '2', '2022-05-12 10:10:31', null, '2022-05-12 10:32:01', null);

-- ----------------------------
-- Table structure for ums_shop_service
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_service`;
CREATE TABLE `ums_shop_service` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '服务名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `status` int DEFAULT '1' COMMENT '显示隐藏',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='服务管理';

-- ----------------------------
-- Records of ums_shop_service
-- ----------------------------
INSERT INTO `ums_shop_service` VALUES ('1', '24小时发货', null, '1', null, null, '2022-05-09 14:54:21', null);
INSERT INTO `ums_shop_service` VALUES ('2', '急速退款', null, '1', null, null, null, null);
INSERT INTO `ums_shop_service` VALUES ('3', '假一赔十', null, '1', null, null, null, null);
INSERT INTO `ums_shop_service` VALUES ('4', '7天无理由退款', null, '1', null, null, null, null);
INSERT INTO `ums_shop_service` VALUES ('5', '精品店铺', '信誉用户在退货后，急速退款至账户', '1', '2022-05-09 14:57:26', '', '2022-05-09 14:57:39', '');

-- ----------------------------
-- Table structure for ums_shop_source
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_source`;
CREATE TABLE `ums_shop_source` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int DEFAULT NULL COMMENT '商家ID(0为系统自营）',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `cate_id` int DEFAULT NULL COMMENT '板块ID',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1图片 2视频',
  `source` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '素材',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺素材管理';

-- ----------------------------
-- Records of ums_shop_source
-- ----------------------------
INSERT INTO `ums_shop_source` VALUES ('1', '4', 'LEKSHOP旗舰店', '1', '1', 'https://qiniu.chengyuwb.com/1652342032948.png', '2022-05-12 15:53:54', null, null, null);

-- ----------------------------
-- Table structure for ums_shop_source_cate
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop_source_cate`;
CREATE TABLE `ums_shop_source_cate` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_id` int DEFAULT NULL COMMENT '商家ID(0为系统自营）',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `sort` int DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺素材分类';

-- ----------------------------
-- Records of ums_shop_source_cate
-- ----------------------------
INSERT INTO `ums_shop_source_cate` VALUES ('1', '4', 'LEKSHOP旗舰店', '基础素材', '0', '2022-05-12 15:52:58', null, '2022-05-12 15:52:58', null);

-- ----------------------------
-- Table structure for walk_trends
-- ----------------------------
DROP TABLE IF EXISTS `walk_trends`;
CREATE TABLE `walk_trends` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` int DEFAULT NULL COMMENT '1短视频>>2宝贝上新>>3种草>>4买家秀',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `member_head_img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `shop_logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家LOGO',
  `content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文案',
  `images` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片合集',
  `video_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '视频链接',
  `sort` int DEFAULT NULL COMMENT '排序',
  `weight` int DEFAULT NULL COMMENT '权重',
  `view_num` int DEFAULT '0' COMMENT '浏览量',
  `collection_num` int DEFAULT '0' COMMENT '收藏量',
  `comment_num` int DEFAULT '0' COMMENT '评论数量',
  `status` int DEFAULT NULL COMMENT '0:待审核 1:通过 2不通过 3草稿',
  `add_time` datetime DEFAULT NULL COMMENT '修改时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `verify_time` datetime DEFAULT NULL COMMENT '审核时间',
  `verify_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审核人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='动态';

-- ----------------------------
-- Records of walk_trends
-- ----------------------------

-- ----------------------------
-- Table structure for walk_trends_collection
-- ----------------------------
DROP TABLE IF EXISTS `walk_trends_collection`;
CREATE TABLE `walk_trends_collection` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `member_head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `shop_logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家LOGO',
  `trends_id` int DEFAULT NULL COMMENT '动态ID',
  `trends_content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '动态内容',
  `trends_images` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '动态主图',
  `trends_video_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '动态链接',
  `add_time` datetime DEFAULT NULL COMMENT '修改时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`,`trends_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='动态收藏表';

-- ----------------------------
-- Records of walk_trends_collection
-- ----------------------------

-- ----------------------------
-- Table structure for walk_trends_comment
-- ----------------------------
DROP TABLE IF EXISTS `walk_trends_comment`;
CREATE TABLE `walk_trends_comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` int DEFAULT NULL COMMENT '用户ID',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `member_head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像',
  `shop_id` int DEFAULT NULL COMMENT '商家ID',
  `shop_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家名称',
  `shop_logo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家LOGO',
  `trends_id` int DEFAULT NULL COMMENT '动态ID',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '内容',
  `status` int DEFAULT NULL COMMENT '0隐藏 1显示',
  `pid` int DEFAULT NULL COMMENT '父级ID',
  `type` int DEFAULT NULL COMMENT '1直评 2回评',
  `add_time` datetime DEFAULT NULL COMMENT '修改时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`,`shop_id`,`trends_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='动态评论';

-- ----------------------------
-- Records of walk_trends_comment
-- ----------------------------

-- ----------------------------
-- Table structure for walk_trends_goods
-- ----------------------------
DROP TABLE IF EXISTS `walk_trends_goods`;
CREATE TABLE `walk_trends_goods` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `trends_id` int DEFAULT NULL COMMENT '订单ID',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_main_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '购买单价',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `upd_time` datetime DEFAULT NULL COMMENT '修改时间',
  `upd_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='动态商品';

-- ----------------------------
-- Records of walk_trends_goods
-- ----------------------------
