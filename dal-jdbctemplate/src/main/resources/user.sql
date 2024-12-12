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


INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (1, '重庆1', 'admin', '1231', '用户1', '15888888888', '头像', 'admin' );
INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (2, '重庆2', 'user', '1234', '用户2', '123', NULL, '1466645430750781440' );
INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (3, '重庆', 'user02', '123', '用户3', '123', NULL, '1466649744075108352' );
INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (4, '222', 'SH-01', '123', '用户4', '', NULL, '1469200870634397696' );
INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (5, '222', 'LR-01', '1231', '111', '1312', NULL, '1469200914007695360' );
INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (6, '222', 'XX-01', '1231', '111', '1312', NULL, '1469200914007695361' );
INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (9, '重庆', 'XX-01', '1231', '111', '1312', NULL, '1469200914007695362' );
INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (7, '重庆test', '重庆test', '111', 'test2', NULL, NULL, '1231' );
INSERT INTO `sys_user` (`id`, `address`, `login_name`, `login_pwd`, `name`, `phone`, `user_icon`, `user_no`) VALUES (8, '重庆test', '重庆test2', 'test2', 'test2', NULL, NULL, 'test2' );
