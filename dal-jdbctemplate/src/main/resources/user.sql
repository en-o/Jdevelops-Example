DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动生成',
    `user_no` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT ' 用户编号',
    `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT ' 姓名',
    `address` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ' 地址',
    `login_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名称',
    `login_pwd` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
    `phone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ' 手机号/联系电话 ',
    `user_icon` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ' 用户头像',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_no_index` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';





INSERT INTO `sys_user`(`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (1, '2021-11-17 11:08:38', 'admin', '2021-12-10 16:18:51', 'admin', '重庆', 'admin', '1231', '超级管理员', '15888888888', NULL, 'admin');
INSERT INTO `sys_user`(`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (2, '2021-12-03 13:48:14', 'admin', '2021-12-10 16:20:06', 'admin', '重庆', 'user', '123', '用户1', '123', NULL, '1466645430750781440');
INSERT INTO `sys_user`(`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (3, '2021-12-03 14:05:23', 'admin', '2021-12-10 16:20:07', 'admin', '重庆', 'user02', '123', '111', '123', NULL, '1466649744075108352');
INSERT INTO `sys_user`(`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (4, '2021-12-10 15:02:39', 'admin', '2021-12-10 16:18:01', 'admin', '222', 'SH-01', '123', '111', '', NULL, '1469200870634397696');
INSERT INTO `sys_user`(`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (5, '2021-12-10 15:02:49', 'admin', '2021-12-10 15:03:00', 'admin', '222', 'LR-01', '1231', '111', '1312', NULL, '1469200914007695360');
