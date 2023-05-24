/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : xxl_job

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 24/05/2023 12:01:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `address_type` tinyint NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_group` (`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (1, 'xxl-job-executor-sample', '示例执行器', 0, NULL, '2022-12-22 10:31:50');
INSERT INTO `xxl_job_group` (`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (2, 'lekshop-admin', 'LEKSHOP后台', 0, 'http://192.168.0.100:9999/', '2022-12-22 10:31:50');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_info` (`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`) VALUES (2, 2, '执行清除额度', '2022-12-21 14:31:28', '2022-12-21 14:42:35', 'LeGreen', '', 'CRON', '0 00 00 ? * *', 'DO_NOTHING', 'FIRST', 'clearTodayIncomeHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-12-21 14:31:28', '', 1, 1671638400000, 1671724800000);
INSERT INTO `xxl_job_info` (`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`) VALUES (3, 2, '每日报表', '2022-12-21 14:40:59', '2022-12-21 14:42:39', 'LeGreen', '', 'CRON', '0 15 00 ? * *', 'DO_NOTHING', 'FIRST', 'dayReportHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-12-21 14:40:59', '', 1, 1671639300000, 1671725700000);
INSERT INTO `xxl_job_info` (`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`) VALUES (4, 2, '每月报表', '2022-12-21 14:42:22', '2022-12-21 14:42:42', 'LeGreen', '', 'CRON', '0 20 0 1 * ?', 'DO_NOTHING', 'FIRST', 'monthReportHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-12-21 14:42:22', '', 1, 0, 1672503600000);
INSERT INTO `xxl_job_info` (`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`) VALUES (5, 2, '第三方接口预警', '2022-12-21 14:44:27', '2022-12-21 14:46:21', 'LeGreen', '', 'CRON', '0 0/30 * * * ?', 'DO_NOTHING', 'FIRST', 'thirdWarnHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-12-21 14:44:27', '', 1, 1671676200000, 1671678000000);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_lock` (`lock_name`) VALUES ('schedule_lock');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (1, 2, 2, 'http://192.168.0.100:9999/', 'clearTodayIncomeHandler', '', NULL, 0, '2022-12-21 14:31:38', 500, '任务触发类型：手动触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：500<br>msg：job handler [clearTodayIncomeHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (2, 2, 2, NULL, 'clearTodayIncomeHandler', '', NULL, 0, '2022-12-21 14:33:17', 500, '任务触发类型：手动触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (3, 2, 2, 'http://192.168.0.100:9999/', 'clearTodayIncomeHandler', '', NULL, 0, '2022-12-21 14:34:37', 200, '任务触发类型：手动触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：200<br>msg：null', '2022-12-21 14:34:37', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (4, 2, 5, 'http://192.168.0.100:9999/', 'thirdWarnHandler', '', NULL, 0, '2022-12-21 15:00:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：500<br>msg：job handler [thirdWarnHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (5, 2, 5, 'http://192.168.0.100:9999/', 'thirdWarnHandler', '', NULL, 0, '2022-12-21 15:30:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：500<br>msg：job handler [thirdWarnHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (6, 2, 5, 'http://192.168.0.100:9999/', 'thirdWarnHandler', '', NULL, 0, '2022-12-21 16:00:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：500<br>msg：job handler [thirdWarnHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (7, 2, 5, 'http://192.168.0.100:9999/', 'thirdWarnHandler', '', NULL, 0, '2022-12-21 16:30:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：500<br>msg：job handler [thirdWarnHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (8, 2, 5, 'http://192.168.0.100:9999/', 'thirdWarnHandler', '', NULL, 0, '2022-12-21 17:00:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：500<br>msg：job handler [thirdWarnHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (9, 2, 5, 'http://192.168.0.100:9999/', 'thirdWarnHandler', '', NULL, 0, '2022-12-21 17:30:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：500<br>msg：job handler [thirdWarnHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (10, 2, 5, 'http://192.168.0.100:9999/', 'thirdWarnHandler', '', NULL, 0, '2022-12-21 18:00:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：500<br>msg：job handler [thirdWarnHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (11, 2, 5, 'http://192.168.0.100:9999/', 'thirdWarnHandler', '', NULL, 0, '2022-12-22 10:30:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.0.100<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.0.100:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.100:9999/<br>code：200<br>msg：null', '2022-12-22 10:30:00', 200, '', 0);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (1, '2022-12-20 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (2, '2022-12-19 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (3, '2022-12-18 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (4, '2022-12-21 00:00:00', 0, 1, 9, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (5, '2022-12-22 00:00:00', 0, 1, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_registry` (`id`, `registry_group`, `registry_key`, `registry_value`, `update_time`) VALUES (5, 'EXECUTOR', 'lekshop-admin', 'http://192.168.0.100:9999/', '2022-12-22 10:31:42');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_user` (`id`, `username`, `password`, `role`, `permission`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
