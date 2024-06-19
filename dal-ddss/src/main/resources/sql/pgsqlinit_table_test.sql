CREATE TABLE "public"."sys_user" (
                                     "id" int4 NOT NULL,
                                     "create_time" date,
                                     "update_time" date,
                                     "update_user_name" varchar(255) COLLATE "pg_catalog"."default",
                                     "create_user_name" varchar(255) COLLATE "pg_catalog"."default",
                                     "user_no" varchar(255) COLLATE "pg_catalog"."default",
                                     "name" varchar(255) COLLATE "pg_catalog"."default",
                                     "address" varchar(255) COLLATE "pg_catalog"."default",
                                     "login_name" varchar(255) COLLATE "pg_catalog"."default",
                                     "login_pwd" varchar(255) COLLATE "pg_catalog"."default",
                                     "phone" varchar(255) COLLATE "pg_catalog"."default",
                                     "user_icon" varchar(255) COLLATE "pg_catalog"."default",
                                     PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."sys_user"  OWNER TO "postgres";

INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (1, '2021-11-17 11:08:38', '2021-12-10 16:18:51', 'testpg', 'testpg', 'admin', '超级管理员', '重庆', 'admin', '1231', '15888888888', NULL);
INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (2, '2021-12-03 13:48:14', '2021-12-10 16:20:06', 'testpg', 'testpg', '1466645430750781440', '用户1', '重庆', 'user', '123', '123', NULL);
INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (3, '2021-12-03 14:05:23', '2021-12-10 16:20:07', 'testpg', 'testpg', '1466649744075108352', '用户2', '重庆', 'user02', '123', '123', NULL);
INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (4, '2021-12-10 15:02:39', '2021-12-10 16:18:01', 'testpg', 'testpg', '1469200870634397696', '111', '222', 'SH-01', '123', '', NULL);
INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (5, '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'testpg', 'testpg', '1469200914007695360', '111', '222', 'LR-01', '1231', '1312', NULL);
INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (6, '2023-05-30 09:55:34', '2023-05-30 09:55:34', 'testpg', 'testpg', 'test', 'test', '123', 'test', '123', '123', '');
INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (7, '2023-05-30 09:58:14', '2023-05-30 09:58:14', 'testpg', 'testpg', '1663364159979991040', 'test', '123', 'test', '123', '123', '');
INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (8, '2023-05-30 09:59:42', '2023-05-30 09:59:42', 'testpg', 'testpg', '1663364529379241984', 'test', '123', 'test', '123', '123', '');
INSERT INTO "public"."sys_user" ("id", "create_time", "update_time", "update_user_name", "create_user_name", "user_no", "name", "address", "login_name", "login_pwd", "phone", "user_icon") VALUES (9, '2023-05-30 10:04:21', '2023-05-30 10:04:21', 'testpg', 'testpg', '1663365697382907904', 'test', '123', 'test', '123', '123', '');
