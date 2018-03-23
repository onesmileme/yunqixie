# API 文档

## User 相关

    用户相关API，目前主要包括login  bind  info 三个接口

### login 接口

    用户登录接口，通过openid来登录，目前未发现场景

### bind 接口

    用户通过微信账号绑定，将微信的信息传入，并返回uid


## Tweet 相关

    tweet 操作包括 publish 、 delete 、 zan 、 comment 几个，转发后续再弄

### publish 接口
    帖子发布接口 

### delete 接口
    删除帖子，不真删，只是把is_del置为1

### 点赞接口
    对帖子点赞

### 取消赞接口
    对帖子取消点赞

### comment 接口
    对帖子和回复进行回复

### 删除回复接口
    删除某条回复

## tweet 获取相关
    分页获取圈子和推荐帖子


## 获取推荐内容
    getHotNews
    初期因为验证模式，先把推荐和圈子合并，合并为广场概念，待后续有了数据再插    