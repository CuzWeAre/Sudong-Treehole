CREATE DATABASE tree_hole;
USE tree_hole;

create table if not exists admin_table
(
    admin_id int auto_increment
        primary key,
    user_id  int null comment '管理员对应的用户ID，逻辑外键关联users表的user_id'
)
    comment '管理员表，管理用户权限';

create table if not exists comment_table
(
    comment_id   int auto_increment
        primary key,
    post_id      int                                  null comment '评论所属帖子的ID，逻辑外键关联posts表的post_id',
    user_id      int                                  null comment '评论作者的用户ID，逻辑外键关联users表的user_id',
    content      text                                 not null comment '评论内容NOT NULL',
    comment_time datetime   default CURRENT_TIMESTAMP null comment '评论发布时间',
    update_time  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '评论最后更新时间',
    is_anonymous tinyint(1) default 0                 null comment '是否匿名评论'
)
    comment '评论信息表';

create table if not exists post
(
    post_id         int auto_increment
        primary key,
    user_id         int                                  null comment '帖子作者的用户ID，逻辑外键关联users表的user_id',
    title           varchar(255)                         null comment '帖子标题',
    content         text                                 not null comment '帖子内容 NOT NULL',
    post_time       datetime   default CURRENT_TIMESTAMP null comment '帖子发布时间',
    update_time     datetime   default CURRENT_TIMESTAMP null comment '帖子最后更新时间',
    last_reply_time datetime   default CURRENT_TIMESTAMP null comment '最新回复时间，记录最后一次评论的时间',
    is_anonymous    tinyint(1) default 0                 null comment '是否匿名发帖'
)
    comment '帖子信息表，包括帖子的基本信息及最新回复时间';

create table if not exists report
(
    report_id   int auto_increment
        primary key,
    type        enum ('user', 'post', 'comment')                              null comment '举报类型，可以是用户、帖子或评论',
    target_id   int                                                           null comment '被举报的目标ID，根据Type的不同，关联不同表的ID',
    reason      text                                                          null comment '举报的具体原因',
    report_time datetime                            default CURRENT_TIMESTAMP null comment '举报时间',
    status      enum ('未处理', '处理中', '已处理') default '未处理'          null comment '举报的处理状态'
)
    comment '举报信息表，记录用户的举报信息';

create table if not exists user_ban
(
    ban_id      int auto_increment comment '封禁记录唯一标识符'
        primary key,
    user_id     int                                not null comment '用户ID 逻辑外键',
    reason      varchar(255)                       not null comment '封禁原因',
    end_time    datetime                           not null comment '结束时间',
    create_time datetime default CURRENT_TIMESTAMP null comment '记录创建时间',
    update_time datetime default CURRENT_TIMESTAMP null comment '记录更新时间'
)
    comment '用户封禁记录';

create table if not exists user_block
(
    block_id        int auto_increment
        primary key,
    user_id         int                                null comment '发起拉黑的用户ID，逻辑外键关联users表的user_id',
    blocked_user_id int                                null comment '被拉黑的用户ID，逻辑外键关联users表的user_id',
    block_time      datetime default CURRENT_TIMESTAMP null comment '拉黑时间'
)
    comment '用户拉黑关系表，记录用户间的拉黑行为';

create table if not exists user_info
(
    user_id         int auto_increment
        primary key,
    username        varchar(255)                               not null comment '用户名，可重复 NOT NULL',
    password_hash   varchar(255)                               not null comment '用户密码的哈希值 NOT NULL',
    email           varchar(255)                               not null comment '用户邮箱 NOT NULL UNIQUE',
    wx_openid       varchar(32)                                null comment '微信开放ID',
    avatar_uuid     binary(16)                                 null comment '头像的uuid',
    register_time   datetime         default CURRENT_TIMESTAMP null comment '用户注册时间',
    is_banned       tinyint unsigned default '0'               null comment '用户是否被封禁，FALSE未封禁，TRUE已封禁',
    last_login_time datetime                                   null comment '上次登录时间',
    last_login_ip   varchar(15)                                null comment '上次登录的IP地址',
    deleted_time    datetime                                   null comment '软删除时间',
    constraint email
        unique (email)
)
    comment '用户信息表';

create table if not exists violation
(
    violation_id int auto_increment
        primary key,
    type         enum ('post', 'comment')           null comment '违规标记的类型，可以是帖子或评论',
    target_id    int                                null comment '被标记违规的目标ID，根据Type关联posts或comments的ID',
    mark_time    datetime default CURRENT_TIMESTAMP null comment '违规标记时间'
)
    comment '违规内容标记表，记录帖子或评论被标记违规的信息';

