# MyService
工程为基于微信公众号的后台服务微服务项目,采用SpringCloud + SpringBoot应用框架开发,MyBatis作为数据持久化框架。

### 系统架构说明
<pre>
所需组件：
    redis：缓存(暂时只用作缓存)
    mysql：数据持久层
    nacos：注册中心+配置中心
    docker: 服务运行容器(建议使用)
    jdk1.8：项目运行环境(jdk-1.8及以上)
    nginx：反向的代理80端口请求到zuul网关
    solr：搜索引擎(尚未使用,后期实现商品的条件搜索)

项目基础技术:
    SpringBoot 2.2.6.RELEASE：简化Spring应用的搭建、开发
    mybatis：数据持久化框架(后期基本由MyBatis Plus完成)
    mybatis-plus：MyBatis增强工具,提供了基础的CRUD操作
    jackson：操作json数据
    fastjson：操作json数据
    restTemplate：请求外部第三方接口(例如：微信)
    nacos：服务注册、配置中心
    redisson：Redis操作工具
    zuul：网关及负载均衡等控制
    openfeign：完成内部服务之间的调用
    EasyExcel：操作Excel(阿里开源Excel处理工具)
    lombok：简化实体对象代码(例如：get、set、toString方法)
</pre>

### 功能模块说明
<pre>
核心模块：
    service-core：所有模块的基础模块(基本包含所有模块的对象、接口、常量等)
    service-core-api：所有API模块的基础模块
    service-core-db：所有操作数据库微服务的基础模块
    service-local：本地模块(因线上服务器配置有限,该模块用于处理不需要线上,服务器性能消耗巨大的功能,占用8888端口)
    
业务模块:
    service-other-impl：第三方数据处理及持久化模块(微信、淘宝、小程序等)
    service-user-impl：用户数据处理及持久化模块
    service-oAuth2：登录鉴权模块(尚未完成)
    
API模块：
    service-zuul：网关模块(目前使用,对外暴露接口,由网关统一分配请求,占用端口8080)
    service-gateway：网关模块(后期将替代service-zuul网关)
    service-other：第三方API模块(微信、淘宝、小程序等)
    service-task：定时任务模块(定时任务暴露Http接口,该模块定时调用Http地址达到定时执行任务)
    service-file：文件模块(未启用)
</pre>

### 打包部署说明

<pre>

目前开发阶段通过docker外网暴露的2375端口使用Dockerfile打包部署项目,后期可配置jenkins实现内部统一部署管理

</pre>
