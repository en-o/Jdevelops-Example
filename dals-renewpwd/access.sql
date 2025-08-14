-- 创建账号
CREATE USER 'tan'@'%' IDENTIFIED BY '123456';

-- 授权
GRANT ALL PRIVILEGES ON *.* TO 'tan'@'%' WITH GRANT OPTION;

-- 刷新权限
FLUSH PRIVILEGES;


-- 修改密码
ALTER USER 'tan'@'%' IDENTIFIED BY '新密码';


-- 密码策略设置
-- 设置密码立即过期（强制下次登录修改）
ALTER USER 'tan'@'%' PASSWORD EXPIRE;
-- 设置密码在指定天数后过期
ALTER USER 'tan'@'%' PASSWORD EXPIRE INTERVAL 天数 DAY;

-- 设置全局密码过期策略
-- 设置所有用户密码过期时间（天数）
SET GLOBAL default_password_lifetime = 天数;
-- 在my.cnf/my.ini中永久设置
[mysqld]
default_password_lifetime=90

-- 禁用密码过期
ALTER USER '用户名'@'主机名' PASSWORD EXPIRE NEVER;
-- 或全局禁用
SET GLOBAL default_password_lifetime = 0;

-- 查看密码过期信息
-- 查看用户密码状态 ( password_lifetime 密码有效期天数)
SELECT user, host, password_last_changed, password_lifetime
FROM mysql.user
WHERE user='用户名';

-- 查询全局有效 0表示不过期：SHOW VARIABLES LIKE 'default_password_lifetime';
SELECT
    user AS '用户名',
    host AS '允许访问的主机',
    password_last_changed AS '最后修改时间',
    IFNULL(password_lifetime, '使用全局设置') AS '密码有效期(天)',
    IF(password_expired='Y', '已过期', '有效') AS '密码状态'
FROM mysql.user
WHERE user = 'root';


-- 查看全局设置
SHOW VARIABLES LIKE 'default_password_lifetime';
