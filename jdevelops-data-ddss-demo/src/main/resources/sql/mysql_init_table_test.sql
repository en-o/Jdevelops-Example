-- 分别给 test1 2 3 都创建此表
CREATE TABLE `sys_user` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自动生成',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
                            `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
                            `update_user_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
                            `create_user_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
                            `user_no` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT ' 用户编号',
                            `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT ' 姓名',
                            `address` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ' 地址',
                            `login_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名称',
                            `login_pwd` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
                            `phone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ' 手机号/联系电话 ',
                            `user_icon` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ' 用户头像',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `user_no_index` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';



-- 初始化三个表数据
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (1, '2021-11-17 11:08:38', '2021-12-10 16:18:51', 'admin', 'admin', 'admin', '超级管理员', '重庆', 'admin', '1231', '15888888888', NULL);
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (2, '2021-12-03 13:48:14', '2021-12-10 16:20:06', 'admin', 'admin', '1466645430750781440', '用户1', '重庆', 'user', '123', '123', NULL);
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (3, '2021-12-03 14:05:23', '2021-12-10 16:20:07', 'admin', 'admin', '1466649744075108352', '用户2', '重庆', 'user02', '123', '123', NULL);
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (4, '2021-12-10 15:02:39', '2021-12-10 16:18:01', 'admin', 'admin', '1469200870634397696', '111', '222', 'SH-01', '123', '', NULL);
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (5, '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'admin', 'admin', '1469200914007695360', '111', '222', 'LR-01', '1231', '1312', NULL);
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (6, '2023-05-30 09:55:34', '2023-05-30 09:55:34', 'administrator', 'administrator', 'test', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (7, '2023-05-30 09:58:14', '2023-05-30 09:58:14', 'tan', 'tan', '1663364159979991040', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (8, '2023-05-30 09:59:42', '2023-05-30 09:59:42', NULL, NULL, '1663364529379241984', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test1`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (9, '2023-05-30 10:04:21', '2023-05-30 10:04:21', 'tan', 'tan', '1663365697382907904', 'test', '123', 'test', '123', '123', '');





INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (1, '2021-11-17 11:08:38', '2021-12-10 16:18:51', 'test2', 'test2', 'test2', '超级管理员', '重庆', 'test2', '1231', '15888888888', NULL);
INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (2, '2021-12-03 13:48:14', '2021-12-10 16:20:06', 'test2', 'test2', '1466645430750781440', '用户1', '重庆', 'user', '123', '123', NULL);
INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (3, '2021-12-03 14:05:23', '2021-12-10 16:20:07', 'test2', 'test2', '1466649744075108352', '用户2', '重庆', 'user02', '123', '123', NULL);
INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (4, '2021-12-10 15:02:39', '2021-12-10 16:18:01', 'test2', 'test2', '1469200870634397696', '111', '222', 'SH-01', '123', '', NULL);
INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (5, '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'test2', 'test2', '1469200914007695360', '111', '222', 'LR-01', '1231', '1312', NULL);
INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (6, '2023-05-30 09:55:34', '2023-05-30 09:55:34', 'test2istrator', 'test2istrator', 'test', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (7, '2023-05-30 09:58:14', '2023-05-30 09:58:14', 'tan', 'tan', '1663364159979991040', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (8, '2023-05-30 09:59:42', '2023-05-30 09:59:42', NULL, NULL, '1663364529379241984', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test2`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (9, '2023-05-30 10:04:21', '2023-05-30 10:04:21', 'tan', 'tan', '1663365697382907904', 'test', '123', 'test', '123', '123', '');



INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (1, '2021-11-17 11:08:38', '2021-12-10 16:18:51', 'test3', 'test3', 'test3', '超级管理员', '重庆', 'test3', '1231', '15888888888', NULL);
INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (2, '2021-12-03 13:48:14', '2021-12-10 16:20:06', 'test3', 'test3', '1466645430750781440', '用户1', '重庆', 'user', '123', '123', NULL);
INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (3, '2021-12-03 14:05:23', '2021-12-10 16:20:07', 'test3', 'test3', '1466649744075108352', '用户2', '重庆', 'user02', '123', '123', NULL);
INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (4, '2021-12-10 15:02:39', '2021-12-10 16:18:01', 'test3', 'test3', '1469200870634397696', '111', '222', 'SH-01', '123', '', NULL);
INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (5, '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'test3', 'test3', '1469200914007695360', '111', '222', 'LR-01', '1231', '1312', NULL);
INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (6, '2023-05-30 09:55:34', '2023-05-30 09:55:34', 'test3istrator', 'test3istrator', 'test', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (7, '2023-05-30 09:58:14', '2023-05-30 09:58:14', 'tan', 'tan', '1663364159979991040', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (8, '2023-05-30 09:59:42', '2023-05-30 09:59:42', NULL, NULL, '1663364529379241984', 'test', '123', 'test', '123', '123', '');
INSERT INTO `test3`.`sys_user` (`id`, `create_time`, `update_time`, `update_user_name`, `create_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES (9, '2023-05-30 10:04:21', '2023-05-30 10:04:21', 'tan', 'tan', '1663365697382907904', 'test', '123', 'test', '123', '123', '');
