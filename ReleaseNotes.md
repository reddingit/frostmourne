# 0.5-SNAPSHOT(开发中)

### Feature

* 报警方式增加飞书 [2021-10-30]

### Bugfix

* bugfix: 解决某些情况下，从elasticsearch中查询数据count大于0，但是hit数组为空的问题 [issue#43](https://github.com/AutohomeCorp/frostmourne/issues/43) [2021-07-27]

### Mysql

* mysql: alert表增加字段feishu_robot_hook，表示飞书机器人地址 - [SQL](./doc/mysql-schema/2021-10-30/change.sql) [2020-12-05]

### Document

###

# 0.4-RELEASE

### Feature

* 实现influxdb数值监控 [2020-09-19]
* 增加influxdb数值同比监控 [2020-09-19]
* 增加mysql数据监控报警支持 [2020-11-15]
* 重构: 抽象一层监控数据读取层，对接新的数据存储只需要实现抽象层接口就可以对接完成 [2020-11-20]
* 监控配置增加是否发送恢复通知的开关选项 [issue#24](https://github.com/AutohomeCorp/frostmourne/issues/24) [2020-12-05]
* 监控保存逻辑，增加测试运行步骤，测试运行通过后才可以保存 [2021-01-11]
* 监控编辑页面：增加查询数据预览功能 [2021-02-28]
* Elasticsearch数据源增加https选项设置 [2021-03-13]
* 报警消息头文字支持自定义配置 [2021-04-17]
* 短链接增加百度短链接支持 [2021-04-17]
* Elasticsearch数据源支持HTTPS [2021-04-28] [issue#35](https://github.com/AutohomeCorp/frostmourne/issues/35)
* 增加clickhouse数据监控报警支持 [2021-05-09]
* InfluxDB支持用户名密码设置 [2021-05-09]
* http方式发送消息，post内容增加监控上下文数据字段context [2021-05-11]
* Elasticsearch数据名增加查询显示字段配置 [2021-05-27]
* 编辑Elasticsearch监控增加索引字段下拉提示 [2021-05-28]

### Bugfix

* 解决登录跳转链接没有带上参数的问题 [2020-09-26]
* 解决http请求没有正确带上头信息的问题 [issue#30](https://github.com/AutohomeCorp/frostmourne/issues/30) [2020-12-17]
* es数据查询，下载请求改为post，解决可能由于scrollid过长导致请求非法的问题 [2021-01-11]
* 解决mysql监控，语句执行报错的问题 [issue#32](https://github.com/AutohomeCorp/frostmourne/issues/32) [2021-02-01]
* 修复用户编辑输入无效的问题 [2021-04-17]

### Mysql

* mysql: alarm表增加字段recover_notice_status，表示是否开启恢复通知 - [SQL](./doc/mysql-schema/2020-11-29/change.sql) [2020-12-05]
* mysql: data_source表字段properties类型改为text - [SQL](./doc/mysql-schema/2021-04-21/change.sql) [2021-04-28]

### Document

* doc: 增加influxdb数据监控使用指南 [2020-09-24]
* doc: 增加mysql数据监控使用指南 [2020-09-24]
* doc: 增加clickhouse数据监控报警使用指南 [2021-05-11]

# 0.3-RELEASE

### Feature

* 监控列表增加按团队查询，默认只显示自己团队的监控；监控按部门隔离 [2020-07-22]
* Elasticsearch监控数值实现同比监控 [2020-07-24]
* Elasticsearch数据源更新免重启加载 [2020-07-25]
* 集成LDAP登录认证 [2020-07-25]
* 数据名保存表单数据提交增加前端验证 [2020-07-22]
* 菜单增加权限控制，部分页面(如：数据源配置)只对管理员开放 [2020-07-27]
* Elasticsearch查询增加历史语句自动提示 [2020-07-27]
* Elasticsearch查询数据柱状图可点击并自动变更时间范围 [2020-07-28]
* 另存时，监控名称增加(copy)字样标识，名字和原监控区分开 [2020-08-01]
* 报警消息模板管理功能 [2020-08-10]
* 账号增加角色(管理员，普通用户)设置功能 [issue#18](https://github.com/AutohomeCorp/frostmourne/issues/18) [2020-08-18]
* 聚合类型(unique_count, percentiles, standard deviation)数值监控
* 增加服务管理，监控可以和服务关联,监控列表增加按服务查询条件 [2020-09-03]
* 如果监控关联了服务，报警接收人自动增加对应的服务负责人 [2020-09-05]
* 监控增加风险等级设置(提示，重要，紧急，我崩了),报警消息添加风险等级信息 [2020-09-05]

### Bugfix

* bugfix: 解决列表分页问题
* bugfix: 解决Elasticsearch数据嵌套时，数据值为undefine的问题 [issue#11](https://github.com/AutohomeCorp/frostmourne/issues/11) [2020-08-01]
* bugfix: 解决部分浏览器下表头和表内容有点错位的问题 [2020-09-05]
* bugfix: 解决数据查询页面，不查询可以直接点击加载更多的问题 [2020-09-05]

### Mysql

* mysql: alarm表增加风险等级字段risk_level - [SQL](./doc/mysql-schema/2020-07-24/change.sql)
* mysql: 增加消息模板表alert_template - [SQL](./doc/mysql-schema/2020-07-31/alert_template.sql)
* mysql: 增加用户角色表user_role - [SQL](./doc/mysql-schema/2020-08-18/user_role.sql)
* mysql: 增加服务表 - [SQL](./doc/mysql-schema/2020-09-01/change.sql)

### Document

* 增加Elasticsearch数据监控使用指南 [2020-08-27]
* 增加同比监控使用指南 [2020-08-29]
* 增加ORM层选型的说明 [2020-09-05]

### Other

* mybatis-generator-maven-plugin依赖的mysql-connector升级为8.0.20
* 默认镜像服务改用阿里云
* 数据库访问层全部换成[mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql) [2020-07-30]
* 替掉蛇形命名字段，全部改为驼峰，统一代码风格 [2020-09-08]

# 0.2-RELEASE

### Feature

* 用户，团队，部门增加应用外配置文件的维护方式
* 增加数据导出CSV功能
* 增加Elasticsearch查询分享功能
* 增加HTTP报警方式
* 增加企业微信报警方式
* elasticsearch监控增加avg,min,max,sum数值metric类型
* 缺少短链接或短链接失败的情况下，使用原链接
* 数据查询页面增加创建监控跳转按钮
* 增加dockerfile和docker-compose
* HTTP监控增加头设置
* 报警接收人设置时给出提示
* 增加企业微信机器人消息发送方式 [2020-07-05]
* 用户信息，团队信息，部门信息外部文件增加定期重新加载 [2020-07-05]
* 账号管理功能模块 [2020-07-11]

### Bugfix

* bugfix: 解决dataname还在使用中，仍然可以删除的问题
* bugfix: 解决elasticsearch 7+版本，数据数量为0的问题
* bugfix: 解决邮箱需要认证的情况下，邮件发送失败的问题
* bugfix: 解决消息过长无法保存的问题

### Mysql

* mysql: alert表增加字段: http_post_url - [SQL](./doc/mysql-schema/2020-06-01/change.sql)
* mysql: data_name表增加data_name字段唯一索引 - [SQL](./doc/mysql-schema/2020-06-13/change.sql)
* mysql: alert表增加字段: wechat_robot_hook - [SQL](./doc/mysql-schema/2020-07-04/change.sql)
* mysql: 增加user_info, team_info, department_info表 - [SQL](./doc/mysql-schema/2020-07-11/change.sql)
* mysql: alarm_log表字段message类型改为text；alert_log字段content类型改为text - [SQL](./doc/mysql-schema/2020-07-16/change.sql)

### Document

* 增加query string简易教程
* 增加docker启动说明文档

### Others

* Elasticsearch-Rest-Client升级至6.6.2
* 升级guava至28.2-jre
* 引入[mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql)
* springboot升级至2.3.1-RELEASE
* 引入[maven-ci-friendly](https://maven.apache.org/maven-ci-friendly.html)实践 [2020-07-18]
* 使用[autolog4j](https://github.com/AutohomeCorp/autolog4j)程序日志格式 [2020-07-19]
* 文本日志按天滚动 [2020-07-19]

# 0.1-RELEASE

* Elasticsearch数据监控, 你只需要写一条查询就可以轻松搞定监控
* HTTP数据监控
* UI功能，简单易用
* 监控管理
* 灵活的报警消息模板定制，支持变量
* 多种消息发送方式(email,短信,钉钉(机器人))
* 多数据源管理
* Elasticsearch数据查询页面
* 报警消息附带日志查询短链接，直达报警原因
* 报警消息抑制功能，防止消息轰炸
