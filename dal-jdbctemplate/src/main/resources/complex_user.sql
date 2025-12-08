-- ========================================
-- 复杂用户信息表
-- 用于测试 interface getter 和 record 返回复杂属性类型
-- ========================================

CREATE TABLE IF NOT EXISTS complex_user
(
    -- 基础字段
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    username   VARCHAR(50)  NOT NULL COMMENT '用户名',
    email      VARCHAR(100) NOT NULL COMMENT '邮箱',
    age        INT COMMENT '年龄',
    status     INT          NOT NULL DEFAULT 1 COMMENT '状态: 0-未激活, 1-已激活, 2-已锁定, 9-已删除',

    -- 枚举字段
    platform   VARCHAR(20) COMMENT '平台: NONE, WEB, MOBILE, DESKTOP',

    -- JSON 字段 (存储复杂类型)
    tags       JSON COMMENT '标签列表: ["tag1", "tag2"]',
    role_ids   JSON COMMENT '角色ID列表: [1, 2, 3]',
    extras     JSON COMMENT '扩展属性: {"key1": "value1", "key2": 123}',

    -- 嵌套对象字段 (存储为JSON)
    detail     JSON COMMENT '用户详情: {"address": "xxx", "phone": "xxx", "avatar": "xxx", "hobbies": ["hobby1", "hobby2"]}',

    -- 时间字段
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    -- 索引
    UNIQUE KEY uk_username (username),
    UNIQUE KEY uk_email (email),
    KEY idx_status (status),
    KEY idx_platform (platform),
    KEY idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='复杂用户信息表';


-- ========================================
-- 插入测试数据
-- ========================================

INSERT INTO complex_user (username, email, age, status, platform, tags, role_ids, extras, detail)
VALUES
-- 用户1: 完整数据
('complex_user_1', 'complex1@test.com', 25, 1, 'WEB',
 JSON_ARRAY('developer', 'java', 'spring'),
 JSON_ARRAY(1, 2, 3),
 JSON_OBJECT('level', 5, 'vip', true, 'score', 99.5),
 JSON_OBJECT('address', '北京市朝阳区', 'phone', '13800138001', 'avatar', 'http://example.com/avatar1.jpg', 'hobbies', JSON_ARRAY('reading', 'coding', 'gaming'))),

-- 用户2: 部分NULL
('complex_user_2', 'complex2@test.com', 30, 1, 'MOBILE',
 JSON_ARRAY('manager', 'team-lead'),
 JSON_ARRAY(2, 4),
 NULL,
 JSON_OBJECT('address', '上海市浦东新区', 'phone', '13900139002', 'avatar', 'http://example.com/avatar2.jpg', 'hobbies', JSON_ARRAY('traveling', 'photography'))),

-- 用户3: 空集合
('complex_user_3', 'complex3@test.com', 28, 2, 'DESKTOP',
 JSON_ARRAY(),
 JSON_ARRAY(),
 JSON_OBJECT('level', 3),
 JSON_OBJECT('address', '广州市天河区', 'phone', '13700137003', 'avatar', NULL, 'hobbies', JSON_ARRAY())),

-- 用户4: 未激活用户
('complex_user_4', 'complex4@test.com', 22, 0, 'WEB',
 JSON_ARRAY('newbie'),
 JSON_ARRAY(1),
 JSON_OBJECT('level', 1, 'trial', true),
 JSON_OBJECT('address', '深圳市南山区', 'phone', '13600136004', 'avatar', 'http://example.com/avatar4.jpg', 'hobbies', JSON_ARRAY('music'))),

-- 用户5: 已删除用户
('complex_user_5', 'complex5@test.com', 35, 9, 'NONE',
 JSON_ARRAY('archived'),
 JSON_ARRAY(),
 JSON_OBJECT('deleted_reason', 'user requested'),
 JSON_OBJECT('address', NULL, 'phone', NULL, 'avatar', NULL, 'hobbies', JSON_ARRAY())),

-- 用户6: 多值枚举状态测试
('complex_user_6', 'complex6@test.com', 27, 1, 'WEB',
 JSON_ARRAY('developer', 'senior', 'architect'),
 JSON_ARRAY(1, 2, 3, 5, 8),
 JSON_OBJECT('level', 10, 'vip', true, 'premium', true, 'score', 100),
 JSON_OBJECT('address', '杭州市西湖区', 'phone', '13500135006', 'avatar', 'http://example.com/avatar6.jpg', 'hobbies', JSON_ARRAY('coding', 'teaching', 'blogging', 'open-source')));


-- ========================================
-- 验证数据
-- ========================================
SELECT * FROM complex_user;
