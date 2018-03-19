# 数据库设计
## 简介	

    根据需求主要需要user、tweet、zan、comment、relation表

## Tweet 表结构

    tweet用来存储用户发的帖子的结构

    ​```
    create table tweet (
        tid int(11) unsigned not null  auto_increment comment '帖子id',
        uid int(11) unsigned not null comment '创建者 uid',
        type int unsigned  not null default '0'  comment '帖子类型 默认是普通文本帖子',
        title varchar(128) default '' comment '帖子标题',
        content mediumtext not null comment '帖子内容',
        images varchar(1000) comment '帖子图片链接',
        ctime timestamp not null default CURRENT_TIMESTAMP comment '帖子创建时间',
        is_del int unsigned not null default '0' comment '是否被删除',
        primary key(tid) comment 'primary key'
    );
    
    #考虑是否需要转发，转发的时候是否允许追加文案；考虑是否需要增加@功能
    `content` mediumtext NOT NULL COMMENT '转发文案组合',
    `content_org` mediumtext NOT NULL COMMENT '转发文案原文',
    `parent_tid` int(11) DEFAULT '0',
    `origin_tid` int(11) DEFAULT '0',
    
    ​```

## zan 表结构

    点赞帖子表，用来存储对某帖子点赞的信息

```
create table zan(
    zid int(11) unsigned not null auto_increment comment '赞id',
    tid int(11) unsigned not null comment '帖子id',
    uid int(11) unsigned not null comment '点赞者uid',
    to_uid int (11) unsigned not null comment '被点赞者uid',
    username varchar(128) not null default '' comment '点赞者昵称',
    ctime timestamp not null default current_timestamp comment '点赞时间',
    primary key(zid)
);
```

## comment 表结构

    评论的表结构，评论有直接回复帖子的评论也有回复回复的回复

```
create table comment (
   cid int(11) unsigned not null auto_increment comment '回复id',
   tid int(11) unsigned not null comment '帖子id',
   from_uid int(11) unsigned not null comment '回复者uid',
   to_uid int(11) unsigned default '0' comment '被回复评论的评论者uid',
   to_cid int(11) unsigned default '0' comment '回复评论的cid',
   is_del int unsigned not null default '0' comment '是否被删除',
   ctime timestamp not null default current_timestamp comment '创建评论的时间戳',
   primary key(cid)
);
```

## relation 表结构

    记录两用户直接的关注关系 根据type确定
        0x1  a follow b
        0x2  b follow a
        0x3  a and follow each other 
    
    用户关系表由于当时的需求，被强制当做好友表进行使用；其实可以考虑和微博一样，在调取时候仅提取某个人关注列表；如果要做用户搜索，可以考虑
    把被关注用户的昵称进行存储


```
create table relation (
    a_uid int(11) unsigned not null comment 'a uid',
    b_uid int(11) unsigned not null comment 'b uid',
    type int unsigned not null default '0' comment 'type 0 no relation , 0x1 a follow b , 0x2 a fllow b , 0x 3 follow each other',
    primary key(a_uid,b_uid)
);
```

## user 表结构

- user表用来存储用户信息的表，当前用户均有微信登录，故根据微信获取用户信息设定


 ```
create table user (
	`uid`  int(11) unsigned not null AUTO_INCREMENT comment '自增用户id',
	`openid` varchar(128) not null default '' comment '微信登录openid',
	`unionid` varchar(256) not null default '' comment '微信登录unionid',
	`wechat_nickname` varchar(128) NOT NULL DEFAULT '' COMMENT '微信获取的昵称',
	`nickname` varchar(128) not null default '' comment '用户昵称，默认同微信昵称',
	`avatar` varchar(256) NOT NULL DEFAULT '' COMMENT '微信头像',
	`country` varchar(32) DEFAULT '' COMMENT '国家',
    `province` varchar(32) DEFAULT '' COMMENT '省',
    `city` varchar(32) DEFAULT '' COMMENT '城市',
    `sex` int(11) DEFAULT '1' COMMENT '性别',
    `ctime` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `utime` int(11) DEFAULT '0' COMMENT '更新时间',    
    `role` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:普通用户 1:企业',
    `birthday` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '生日时间戳',
    `autograph` varchar(1024) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '个性签名',
	`status` tinyint(4) DEFAULT '0' COMMENT '0:正常 1:拉黑; 2:禁言;',
	`lift_ban_time` int(11) DEFAULT '0' COMMENT '解禁时间',   
    `mobile` varchar(31) NOT NULL DEFAULT '' COMMENT '绑定手机号',
    `is_robot` tinyint(2) NOT NULL DEFAULT '0' COMMENT '1机器人账户，0真人账户',
     primary key (uid) comment 'primary key',
     unique key uni_openid (openid)
);

 ```