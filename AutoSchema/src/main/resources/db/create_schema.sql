CREATE database if NOT EXISTS `test_auto_schema` default character set utf8mb4 collate utf8mb4_unicode_ci;
use `test_auto_schema`;

SET NAMES utf8mb4;

CREATE TABLE IF NOT EXISTS `xxl_job_info` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
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
    `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
    `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
    `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
    `glue_source` mediumtext COMMENT 'GLUE源代码',
    `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
    `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
    `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
    `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
    `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
    `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;