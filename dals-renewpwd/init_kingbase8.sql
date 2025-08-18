CREATE DATABASE  test_renewpwd ;

CREATE TABLE `test_demo` (
                             `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'name',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='测试';



INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (1, '韦致远');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (2, '曹致远');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (3, 'Leonard Bell');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (4, '邹致远');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (5, '周杰宏');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (6, '郑秀英');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (7, 'Tammy Medina');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (8, '贺嘉伦');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (9, '金宇宁');
INSERT INTO `test_renewpwd`.`test_demo` (`id`, `name`) VALUES (10, 'Robin Wilson');
