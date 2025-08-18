/* =========================================================
 * KingbaseES V8 用户与密码管理脚本（MySQL 功能对应）
 * =========================================================*/

/* 1. 创建账号
   说明：KingbaseES 没有 `user@host` 写法，直接在 sys_user 中创建。
         如需限制登录来源，可配合 sys_hba.conf 或 ALTER SYSTEM 设置。
*/
CREATE USER tan WITH PASSWORD '123456';

/* 2. 授权（等价于 MySQL 的 GRANT ALL）
   ALL PRIVILEGES 在 KingbaseES 中不能针对所有库（*.*），
   通常做法是给库级或模式级授权，或者授予 SUPERUSER。
*/
GRANT ALL PRIVILEGES ON DATABASE test_renewpwd TO tan;   -- 仅示例库
-- 如果想拥有所有权限（包括创建角色、数据库等）：
ALTER USER tan WITH SUPERUSER;

/* 3. 立即生效：KingbaseES 不需要 flush privileges */

/* 4. 修改密码 */
ALTER USER tan WITH PASSWORD '新密码';

/* 5. 密码策略
   5.1 强制下次登录修改密码
       KingbaseES 没有一次性过期的关键字，用有效期=0 天模拟
*/
ALTER USER tan VALID UNTIL '1970-01-01';

/* 5.2 设置密码 N 天后过期 */
ALTER USER tan VALID UNTIL current_date + 90;   -- 90 天后过期

/* 5.3 全局默认有效期（对所有新建用户生效）
   需要在 kingbase.conf 中配置：
   password_policy = 'expire=90d'     -- 单位支持 d(天)、h(小时)
   修改后 reload 配置：
   SELECT sys_reload_conf();
*/

/* 5.4 禁用单个用户密码过期 */
ALTER USER tan VALID UNTIL 'infinity';

/* 6. 查看密码过期信息
   KingbaseES 中口令信息记录在系统视图 sys_user
*/
SELECT
    usename                         AS "用户名",
    valuntil                        AS "密码过期时间",
    CASE
        WHEN valuntil IS NULL THEN '永不过期'
        WHEN valuntil < now() THEN '已过期'
        ELSE '有效'
        END                             AS "密码状态"
FROM sys_user
WHERE usename = 'tan';

