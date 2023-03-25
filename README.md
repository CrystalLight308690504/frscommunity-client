# FRSCommunityClient

#### 介绍
这是本人的一个关于轮滑移动平台的博客，前端使用Android平台语言开发主要有 用户模块、博客模块、收藏模块、搜索模块组成
用户模块：提供用户登录注册功能，信息维护功能，粉丝数量、关注的用户、点赞数量的显示
博客模块：提供发表、查看、删除、评论、点赞、收藏、查看等功能
收藏模块：管理用户收藏博客的文件
搜索模块：提供根据关键字搜索博客、用户功能

#### 软件架构
前端使用MVP架构
JDK使用11

1) 数据层（M）: 数据层是负责对数据的存取和查找操作，例如对本地数据库里的数据的读和写以及对网络的数据进行请求等。
2) 视图层（V）：View层是视图层，在View层中这层控件只负责对数据的展示，提供友好的界面与用户进行交互。在Android开发中通常将Activity或者Fragment作为View层。
3) 发布层（P）: 它是View层与Model层的一个关键的连接桥梁，presenter并对业务逻辑进行处理。在MVP架构中Model与View无法直接进行交互，Model与View的数据交互必须需要Presenter作为桥梁进行Model与View的交互。
#### 使用技术
1、使用okhttp框架进行后台数据请求
2、使用EventBust传递消息，获取返回的数据以及更改界面UI
3、使用butterknife获取View
#### 服务器地址
https://github.com/CrystalLight308690504/frscommunity-service
