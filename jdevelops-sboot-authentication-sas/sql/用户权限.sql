-- org\springframework\security\core\userdetails\jdbc\users.ddl(spring-security-core包)
-- 用户
CREATE TABLE `users` (
                         `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
                         `password` varchar(500) COLLATE utf8mb4_general_ci NOT NULL,
                         `enabled` tinyint(1) NOT NULL,
                         PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
-- 权限
CREATE TABLE `authorities` (
                               `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
                               `authority` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
                               UNIQUE KEY `ix_auth_username` (`username`,`authority`),
                               CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

