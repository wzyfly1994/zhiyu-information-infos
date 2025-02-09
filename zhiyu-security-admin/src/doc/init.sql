drop table if exists user;
create table user
(
    id          bigint auto_increment
        primary key,
    account     varchar(32)                          null comment '账号',
    pass_word   varchar(64)                          null comment '密码',
    user_name   varchar(16)                          null comment '用户名',
    sex         smallint                             null comment '性别 1:男，2:女，3:未知',
    email       varchar(255)                         null comment '邮箱',
    phone       varchar(32)                          null comment '手机号',
    dept_id     bigint                               null comment '组织id',
    is_admin    tinyint(1) default 0                 not null comment '是否超级管理员',
    status      tinyint(1) default 0                 null comment '状态(0:正常 1:禁用)',
    deleted     tinyint(1) default 0                 not null comment '是否删除,1是，0否',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time timestamp                            null comment '删除时间',
    create_at   bigint                               null comment '创建人',
    update_at   bigint                               null comment '修改人',
    delete_at   bigint                               null comment '删除人'
)
    comment '用户';

drop table if exists department;
create table department
(
    id          bigint auto_increment
        primary key,
    number      varchar(128)                         null comment '部门编码',
    name        varchar(255)                         null comment '部门名称',
    parent_id   bigint                               null comment '父部门id，32位整型',
    seq         bigint                               null comment '排序值，seq值小的排序靠前',
    leader_id   bigint     default null comment '负责人id',
    description varchar(255)                         null comment '组织描述',
    status      tinyint(1)                           null comment '状态(0:正常 1:禁用)',
    deleted     tinyint(1) default 0                 not null comment '是否删除,1是，0否',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time timestamp                            null comment '删除时间',
    create_at   bigint                               null comment '创建人',
    update_at   bigint                               null comment '修改人',
    delete_at   bigint                               null comment '删除人'
)
    comment '部门';


drop table if exists role;
create table role
(
    id          bigint auto_increment
        primary key,
    role_name   varchar(16)                          null comment '角色名',
    role_value  varchar(64)                          null comment '角色值',
    data_scope  char(1)    default '1' comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    status      tinyint(1) default 0                 null comment '状态(0:正常 1:禁用)',
    deleted     tinyint(1) default 0                 not null comment '是否删除,1是，0否',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time timestamp                            null comment '删除时间',
    create_at   bigint                               null comment '创建人',
    update_at   bigint                               null comment '修改人',
    delete_at   bigint                               null comment '删除人'
)
    comment '角色';

drop table if exists menu;
create table menu
(
    id          bigint auto_increment
        primary key,
    level       tinyint                              null comment '菜单级别',
    menu_name   varchar(128)                         null comment '菜单名称',
    serial_num  smallint                             null comment '菜单排序号',
    parent_id   bigint                               null comment '父节点ID',
    url  varchar(255)                         null comment '菜单URL',
    permissions varchar(64)                          null comment '权限值',
    description varchar(255)                         null comment '菜单描述',
    image_url   varchar(128)                         null comment '菜单图标',
    menu_type   smallint                             null comment '菜单类型（1目录 2菜单 3按钮）',
    status      tinyint(1) default 0                 null comment '状态(0:正常 1:禁用)',
    deleted     tinyint(1) default 0                 not null comment '是否删除,1是，0否',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time timestamp                            null comment '删除时间',
    create_at   bigint                               null comment '创建人',
    update_at   bigint                               null comment '修改人',
    delete_at   bigint                               null comment '删除人'
)
    comment '菜单';


drop table if exists role_menu;
create table role_menu
(
    id          bigint auto_increment
        primary key,
    menu_id     bigint                               null comment '菜单id',
    role_id     bigint                               null comment '角色id',
    deleted     tinyint(1) default 0                 not null comment '是否删除,1是，0否',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time timestamp                            null comment '删除时间',
    create_at   bigint                               null comment '创建人',
    update_at   bigint                               null comment '修改人',
    delete_at   bigint                               null comment '删除人'
)
    comment '角色菜单';

drop table if exists user_role;
create table user_role
(
    id          bigint auto_increment
        primary key,
    user_id     bigint                               null,
    role_id     bigint                               null,
    deleted     tinyint(1) default 0                 not null comment '是否删除,1是，0否',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    delete_time timestamp                            null comment '删除时间',
    create_at   bigint                               null comment '创建人',
    update_at   bigint                               null comment '修改人',
    delete_at   bigint                               null comment '删除人'
)
    comment '用户角色';



INSERT INTO user (id, account, pass_word, user_name, sex, email, phone, dept_id, is_admin, status, deleted, create_time, update_time, delete_time, create_at, update_at, delete_at) VALUES (1, 'jason001', '$2a$10$0Z/RPNjU9VFfP0WCmYDcl.uFnSlSM26Y9oCt0monnZo58ddPaY4JS', 'jason', 1, '123@gmail.com', '12311', 1, 0, 0, 0, '2024-12-23 16:05:38', '2024-12-23 16:05:38', null, null, null, null);
INSERT INTO user_role (id, user_id, role_id, deleted, create_time, update_time, delete_time, create_at, update_at, delete_at) VALUES (1, 1, 1, 0, '2024-12-25 16:00:54', '2024-12-25 16:00:54', null, null, null, null);
INSERT INTO role (id, role_name, role_value, data_scope, status, deleted, create_time, update_time, delete_time, create_at, update_at, delete_at) VALUES (1, '超级管理员', 'admin', '1', 0, 0, '2024-12-25 15:55:20', '2024-12-25 15:55:25', null, 1, null, null);
INSERT INTO role (id, role_name, role_value, data_scope, status, deleted, create_time, update_time, delete_time, create_at, update_at, delete_at) VALUES (2, '普通角色', 'common', '2', 0, 0, '2024-12-25 16:00:10', '2024-12-25 16:00:14', null, 1, null, null);
INSERT INTO role_menu (id, menu_id, role_id, deleted, create_time, update_time, delete_time, create_at, update_at, delete_at) VALUES (1, 1, 1, 0, '2024-12-25 16:18:02', '2024-12-25 16:18:02', null, null, null, null);
INSERT INTO role_menu (id, menu_id, role_id, deleted, create_time, update_time, delete_time, create_at, update_at, delete_at) VALUES (2, 2, 1, 0, '2024-12-25 16:18:02', '2024-12-25 16:18:02', null, null, null, null);
INSERT INTO menu (id, level, menu_name, serial_num, parent_id, url, permissions, description, image_url, menu_type, status, deleted, create_time, update_time, delete_time, create_at, update_at, delete_at) VALUES (1, 1, '系统管理', 1, 0, 'system', 'system', null, null, null, 0, 0, '2024-12-25 16:08:47', '2024-12-25 16:08:47', null, null, null, null);
INSERT INTO menu (id, level, menu_name, serial_num, parent_id, url, permissions, description, image_url, menu_type, status, deleted, create_time, update_time, delete_time, create_at, update_at, delete_at) VALUES (2, 2, '用户管理', 1, 1, 'system/user/index', 'system:user:list', null, null, null, 0, 0, '2024-12-25 16:16:51', '2024-12-25 16:16:51', null, null, null, null);

