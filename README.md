## [Github地址](https://github.com/AutohomeCorp/frostmourne) | [Gitee地址](https://gitee.com/tim_guai/frostmourne)

## 介绍

frostmourne(霜之哀伤)是汽车之家经销商技术部监控系统的开源版本，用于帮助开发监控应用日志，现主要用于监控Elasticsearch数据。
关于内部日志系统的设计实现感兴趣的话，请移步文章: <a href="./doc/wiki/design.md" target="_blank">之家经销商技术部基于Elasticsearch的日志系统设计与实现</a> 可以认为frostmoure是监控部分的实现。
如果你现在使用Elastic stack(ELK)建立起了日志系统，却苦恼于没有一个配套日志监控系统，也许它能帮到你。

## 主要功能

* 只需要写一条数据查询就可以轻松搞定监控
* 多数据源(Elasticsearch, InfluxDB, Mysql, ClickHouse)支持
* 多种数值计算类型监控(count,min,max,avg,sum,unique count,percentiles,standard deviation)
* 数值同比监控
* HTTP数据监控, js表达式判断是否报警
* UI功能，简单易用
* 监控管理，测试，另存。执行日志，历史消息。
* 灵活的报警消息freemarker模板定制，支持变量；消息模板管理
* 多种报警消息发送方式(email,短信,钉钉(机器人),企业微信(机器人), HTTP请求)
* Elasticsearch数据查询,分享,下载
* 报警消息附带日志查询短链接，直达报警原因
* 报警消息抑制功能，防止消息轰炸
* 每个监控都是独立调度，互不影响
* 自带账号,团队,部门信息管理模块，也可自己实现内部对接
* 集成LDAP登录认证
* 权限控制，数据隔离，各团队互不影响

## 在线demo

为了更快的理解本项目的作用，提供了一个接口全mock的静态站点供大家预览功能: <a href="https://frostmourne-demo.github.io/">在线demo</a>
在线demo更新不及时，请以项目实际运行效果为准，demo只是用于快速浏览

## 功能截图

* 报警消息

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/message.png" />

* 数据源管理

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/datasource.png" />

* 数据名管理

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/dataname.png" />

对于elasticsearch数据源来说，数据名等同于索引的概念

* elasticsearch数据查询

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/es.png" />

配置完数据源和数据名，你就可以用查询页面验证数据配置是否正确了。

* 新增或编辑监控

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/edit.png" />

* HTTP数据监控

以监控Elasticsearch集群健康状态为例。

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/http_metric.png" />

检测条件为：集群状态字段status不为green，或者集群节点数量不等于11

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/http_rule.png" />

* 监控列表

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/list.png" />

监控保存成功后，就可以在监控列表里看到了

## 项目初衷

在用ELK建立起日志系统之后，我们发现应用日志监控这块除了ElastAlert之外，没有其他方案。我们初期使用ElastAlert来解决日志监控的问题，
但是随着配置的增加，管理成本，使用成本较高和，配置文件多了之后，稳定性方面也不能让我们满意，所以为了更好的易用性，稳定性，我们决定自己做一套简单的监控系统，
来解决日志监控的问题。如果你面临和我们同样的问题，不妨一试。

但是项目并不仅限于elasticsearch数据，还有HTTP数据监控，InfluxDB数据监控，Mysql数据监控, ClickHouse数据监控，后面还会加入更多的常用数据源(如：prometheus, skywalking,
iotdb, loki等)纳入监控范畴，需要做的东西还有很多，需要更多相关开发加入进来，欢迎联系我们。

## 联系我们

有问题或需要帮助请提issue或者加入QQ群: 1082617505，请优先选择提issue，便于问题的讨论和记录追踪，也方便有类似问题的伙伴搜索解决。 也欢迎对项目感兴趣的同僚加群沟通。
特别提一下：关于文档觉得哪里写的不通畅，不好理解，或者有哪方面缺失，都欢迎提issue。

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/frostmourne-qq.png" />

## 快速启动

提供docker-compose方式，让你更快运行起来便于更好理解项目作用。
详细请看文档：<a href="./doc/wiki/quick-start.md" target="_blank">Quick-Start</a>

## 实现简易示意图

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/alarm_design.png" />

## Elasticsearch数据监控指南

<a href="./doc/wiki/es.md" target="_blank">Elasticsearch数据监控指南</a>

## HTTP类型监控指南

HTTP数据监控，非常灵活强大，请参考说明： <a href="./doc/wiki/http-alarm.md" target="_blank">HTTP监控使用说明</a>

## 利用HTTP监控prometheus数据举例

prometheus自带HTTP API，可以非常方便得使用HTTP监控数据。下面举例说明。

假设local.prometheus.com是prometheus使用的域名。那么查询语句可以这么写

```
http://local.prometheus.com/api/v1/query?query=sum by(cluster_name,cluster_env,pod) (kube_pod_info{pod_ip="127.0.0.1"})
```

点击预览数据可以看到返回的数据。

```
{
    "data": {
        "resultType": "vector",
        "result": [
            {
                "metric": {
                    "cluster_env": "prod",
                    "cluster_name": "cluster-1",
                    "pod": "my-api-fsfsf-fbbp"
                },
                "value": [
                    1621938928.222,
                    "1"
                ]
            }
        ]
    },
    "HTTP_STATUS": 200,
    "HTTP_COST": 65,
    "status": "success"
}
```

这样返回的json数据就可以用表达式来判断是否报警了

```
data.result[0].value[1] > 0
```

报警模板也可以根据返回的json定制消息。例如：

```
存在不合法的pod。
环境: ${data.result[0].metric.cluster_env}
集群名称: ${data.result[0].metric.cluster_name}
pod: ${data.result[0].metric.pod}
```

这样一个prometheus数据监控就完成了，非常简便而且强大，快点试试。

## InfluxDB数据监控指南

<a href="./doc/wiki/influxdb.md" target="_blank">InfluxDB数据监控指南</a>

## Mysql数据监控指南

<a href="./doc/wiki/jdbc-mysql.md" target="_blank">Mysql数据监控指南</a>

## Clickhouse数据监控指南

<a href="./doc/wiki/jdbc-clickhouse.md" target="_blank">Clickhouse数据监控指南</a>

## 数值同比监控使用指南

<a href="./doc/wiki/same-time-compare.md" target="_blank">数值同比监控使用指南</a>

## 消息模板配置

消息模板采用freemarker语法，详细使用方法请参考文档：[消息模板配置](./doc/wiki/template.md)

## 报警发送

提供了多种报警消息发送方式，详细请看文档： [报警发送](./doc/wiki/ways.md)

## 报警抑制

为了防止消息轰炸，提供报警抑制机制，详细请看文档： [报警抑制](./doc/wiki/supress.md)

## 调度配置

调度配置有一个需要特别注意的地方，就是调度间隔和你的数据查询窗口有关系。一般日志系统采集日志多少都会有延迟，少的话几秒，多的话几分钟都
是可以预见的，所以尽量保证两次调度之间查询的日志数据有一定的重叠是很明智的做法，切忌出现数据真空(两次调度之间有数据未被查询窗口覆盖)。
举例：一般的程序错误日志监控配置调度间隔为每2分钟调度一次，查询数据窗口可以配置为3分钟。这样虽然因为1分钟数据重叠可能导致多报(事实上因为报警抑制
的原因，你并不会受到多条报警消息的骚扰)，但是基本可以保证不会漏报。这里只是举一个常见的例子，具体如何配置，你需要根据自己的实际情况。

## 监控测试

一般在创建监控或者刚创建完监控的时候，你会想测试一下监控的执行。在监控保存页面有测试功能，你可以尝试不同的查询
语句来验证你的想法。

另外在监控列表页面，你可以点击运行按钮让监控立即运行而不必等待调度来验证你的想法。

## 监控另存

在创建了很多监控之后，你会发现同一类型的大部分监控是非常相似的，这时候你就会想要监控另存功能。你可以在监控列表的已有监控
中找一个和你想要创建的监控相似的监控，点编辑进入监控编辑页面后，直接另存，就会生成一个一模一样的新监控，然后你就可以安全的
修改这个新监控了。之所以建议直接另存是因为你会非常容易忘记你是想另存一个监控，而去点了保存按钮。就会把现有监控覆盖掉。

## 短链接服务

为了方便使用者快速查看产生报警的日志，报警消息最后会有一个日志查询地址的短链接，打开即可看到产生报警的日志。目前默认支持的短链接
有两种：四五短网址和百度短网址。默认短链接实现使用的是四五短网址免费版，网址: <a href="http://www.45dwz.cn/" target="_blank">45短网址</a>, 默认申请的token限制很大，
调用次数有限制，你可以去45短网址申请自己token，或者你可以自己选择换别的短网址服务都行，只需要自己实现简单适配即可。

如果你自己申请了token，请修改配置文件 frostmourne-spi/src/main/resources/application.properties 如下配置值：

```
dwz.type=dwz45
dwz45.token=t8HGzRNv9TmvqUFICNoW3SaYNA1C9OAC
```

如果想切换使用<a href="https://dwz.cn/console/apidoc/v3" target="_blank">百度短网址</a>，请通过修改如下配置：

```
dwz.type=baidu
dwz.baidu.token=
```

token需要自己申请。

> 说明：如果短链接服务出错或者不使用，报警消息里的链接将使用原链接，会比较长。

## 用户管理和登录认证

请参考文档：[用户管理和登录认证](./doc/wiki/auth.md)

## 主要项目结构

* frostmourne-vue

UI项目，使用vue-element-template实现，打包时会打到frostmourne-monitor下

* frostmourne-monitor

监控运行主体项目, 依赖frostmourne-spi和xxl-job。监控调度模块依赖xxl-job[https://github.com/xuxueli/xxl-job] 实现。

* frostmourne-spi

需要根据各自情况适配实现的模块，包括用户相关接口，短链接生成接口, 消息发送(短信发送和钉钉消息发送)接口, 需要自己实现，邮件发送,
钉钉机器人消息发送，企业微信消息发送和HTTP请求消息发送已经实现好了，其中邮箱配置和企业微信需要修改为自己的

```
email.smtp.host=${your.email.smtp.host:}
email.smtp.port=${your.email.smtp.port:}
email.smtp.auth=${your.email.smtp.auth:true}
email.sender=${your.email.sender:}
email.sender.password=${your.email.sender.password:}

wechat.corpid=${your.wechat.corpid:}
wechat.agentid=${your.wechat.agentid:}
wechat.secret=${your.wechat.secret:}
```

com.autohome.frostmourne.spi.plugin包下的接口，需要你根据自己情况实现。

* frostmourne-spi-starter

为了方便frostmourne-monitor使用frostmourne-spi，增加了frostmourne-spi-starter, 里面主要是接口定义和feign接口的自动注入。

## 为什么设计frostmourne-spi模块

请参考文档: <a href="./doc/wiki/frostmourne-spi.md" target="_blank">为什么设计frostmourne-spi</a>

## 调试环境要求

* JDK 1.8
* xxl-job 2.1.0
* nodejs
* mysql
* elasticsearch 6.3.2+

## 数据库相关

* frostmourne库

frostmourne所有表的创建语句在[doc/mysql-schema/frostmourne.sql](./doc/mysql-schema/frostmourne.sql)文件中，数据库开发使用druid + mybatis，创建好语句后，自己修改frostmourne-monitor模块的数据库配置

```
druid.datasource.frostmourne.url=jdbc:mysql://[mysql]:3306/frostmourne?characterEncoding=utf8
druid.datasource.frostmourne.username=[username]
druid.datasource.frostmourne.password=[plain_password]
```

密码默认使用明文，没有加密策略，如果你需要对密码进行加密，请参考druid官方文档：[druid数据库密码加密](https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter)

* xxl-job库

xxl-job库的创建语句在[/doc/xxl-job/xxl-job.sql](./doc/xxl-job/xxl-job.sql)

## xxl-job服务

本项目依赖xxl-job, 请自己部署xxl-job，并将相关接口权限认证去掉(在action上加注解 @PermissionLimit(limit=false) )，让frostmourne可以访问这些接口。需要了解xxl-job请
查阅官方站点[https://www.xuxueli.com/xxl-job/]. 当前依赖版本为2.1.0，如果存在版本兼容问题，请自行修改适配, 建议单独部署一套新的xxl-job，能避免很多不必要的麻烦。
依赖的xxl-job接口列表如下:

* /jobinfo/add
* /jobinfo/update
* /jobinfo/remove
* /jobinfo/start
* /jobinfo/stop

如果你觉得从xxl-job官方下载源码修改部署太麻烦，你可以使用我处理好了的jar包 <a href="./doc/xxl-job/xxl-job-admin-2.1.0.zip" target="_blank">xxl-job-admin-2.1.0.zip</a>，你可以下载直接解压使用
xxl-job部署好之后，你需要在xxl-job-admin的执行器管理中创建一个名为frostmourne的执行器，注册方式为自动注册，如下图：

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/executor.png"/>

启动脚本都已经写好，你只需要修改application.properties设置自己的应用配置，修改env设置环境变量配置。然后执行启动脚本即可。

```bash
./scripts/startup.sh
```

执行如下命令停止应用：

```bash
./scripts/shutdown.sh
```

如果嫌包部署麻烦，测试环境也可以直接用<a href="./doc/wiki/quick-start.md" target="_blank">Quick-Start</a>
docker里启动一个xxl-job服务，供本地调用

## 为什么需要xxl-job

引入xxl-job是为了让每个监控任务都可以独立调度，在创建监控的同时，会调用xxl-job的服务的接口创建一个调度任务。引入xxl-job确实给部署带来了
一定的难度，但是也带来了如下好处:

* 节约很多开发成本，让项目可以很快完成
* 将调度作为一个服务独立出去，大大降低了主体功能项目的复杂度

所以在权衡利弊之后，还是决定好好利用优秀的国内开源项目xxl-job

## query string简易教程

本项目elasticsearch查询语句使用的是query string语句，并非DSL query, 这里提供了一个<a href="./doc/wiki/query-string.md" target="_blank">简易教程</a>供不会的同学快速
入门，英文水平可以的同学最好是看<a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html#query-string-syntax" target="_blank">官方文档</a>

## 部署

UI项目frostmourne-vue会自动把资源打到frostmourne-monitor的resources/dist下，所以你只需要独立部署frostmourne-spi和frostmourne-monitor，
他们都是无状态的服务，分配好域名做负载均衡，其中frostmourne-monitor依赖frostomourne-spi。在frostmourne-monitor配置文件中配置frostomourne-spi地址:

```
frostmourne.spi.service-addr=http://${frostmourne-spi-address}
frostmourne.monitor.address=http://${frostmourne-monitor-address}
```

其中frostmourne.monitor.address配置用于生成日志查询地址。最后以短链接的形式放在报警消息里。**注意：直接使用ip是无法生成短链接的**

### 打包和zip包部署

frostmourne-spi和frostmourne-monitor已经配置了assembly打包，target目录下会生成zip包，你只需要将zip包解压，然后根据自己的
环境修改应用配置文件application.properties文件和环境变量配置文件env，然后执行如下命令启动：

```bash
./scripts/startup.sh
```

执行如下命令停止应用：

```bash
./scripts/shutdown.sh
```

[xxl-job-admin-2.1.0.zip](./doc/xxl-job/xxl-job-admin-2.1.0.zip)的zip包也已经放在了仓库里，供下载使用，使用方式相同。

## 开发调试

修改frostmourne-monitor里和xxl-job相关配置。其中xxl.job.executor.id配置为刚在xxl-job中创建的执行器id。一般执行器id是2。

```
### xxl-job admin address list, such as "http://address" or "http://address01,http://address02"
xxl.job.admin.addresses=http://[your_xxljob_address]/xxl-job-admin
### xxl-job executor address
xxl.job.executor.id=2
xxl.job.executor.appname=frostmourne
xxl.job.executor.ip=
xxl.job.executor.port=-1
### xxl-job, access token
xxl.job.accessToken=
### xxl-job log path
xxl.job.executor.logpath=/data/applogs/xxl-job/jobhandler
### xxl-job log retention days
xxl.job.executor.logretentiondays=3
### xxl-job alarm email
xxl.job.alarm.email=[your_email]
```

启动frostmourne-spi项目，启动参数增加：-Dlog.console.level=INFO，将active profile设置为default, 测试地址: http://localhost:10053
启动frostmourne-monitor项目, 启动参数增加：-Dlog.console.level=INFO，active profile设置为local, 测试地址: http://localhost:10054
使用VS Code打开frostmourne-vue目录，进行UI调试。执行如下命令:

```bash
# install dependency
npm install

# 建议不要直接使用 cnpm 安装以来，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# develop
npm run dev
```

会自动打开： http://localhost:9528

搭建本地开发调试环境或者需要做二次开发遇到什么困难的都可以加群沟通，欢迎各路英雄多多PR

## ORM选型说明

项目的数据库ORM使用的是mybatis，一直以来mybatis的xml配置sql深受国内开发欢迎，因为
它非常的灵活，能比较好的应付快速多变的的迭代需求。但是缺点也比较明显。

* 对于一些简单的查询来说，xml定义过于繁琐
* 太灵活了，稍不注意sql就会写得很复杂，后面维护艰难

mybatis最新推出了新的模块[mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql)，代码即sql
的查询，大部分基于生成的代码可以直接写代码完成，不需要写任何sql。

为了兼顾方便和灵活，我同时在项目里引入了mybatis-dynamic-sql和xml-sql两种方式，让他们互补配合一起完成数据访问。
大部分(90%以上)查询直接用mybatis-dynamic-sql，对于一些很少的需要灵活的稍复杂sql使用xml-sql来完成。既提高了
编码效率，又保留了原来的灵活强大的xml-sql。我们只需要按需选择使用。

## 后续规划

目前已知的规划有:

* ~~bugfix: 解决某些情况下，从elasticsearch中查询数据count大于0，但是hit数组为空的问题~~ [issue#43](https://github.com/AutohomeCorp/frostmourne/issues/43) [2021-07-27]
* ~~报警方式增加飞书~~ [2021-10-30]
* http监控兼容异常状态码
* 解决邮箱报警不支持ssl的问题
* msyql监控增加表达式监控规则
* 增加报警组支持
* Elasticsearch数据名增加traceid字段配置，可以配置为skywalking的traceId，显示skywalking traceid的时候，增加连接，跳转到skywalking对应的调用链
* 报警消息格式增加类型: text, markdown选项
* 数据配置支持数据分组，分组类型支持两种：1. 按字段值分组，相当于ES里的Terms Aggregation; 2. 按时间分组,相当于ES里的DateHistogramAggregation
* 内置实现一个短链接功能，移除外部短链接服务依赖，解决外部短链接服务请求比较慢的问题
* 增加[prometheus](https://github.com/prometheus/prometheus)数据监控报警支持
* 增加[skywalking](https://github.com/apache/skywalking)数据监控报警支持
* 增加[iotdb](https://github.com/apache/iotdb)数据监控报警
* 增加[loki](https://github.com/grafana/loki)数据监控报警
* mysql数据监控增加除count外其他聚合类型支持
* clickhouse数据监控增加除count外其他聚合类型支持
* 报警增加持续周期设置，可以配置为连续X次触发报警才发送消息，否则只记录不发送。用在不想收到那种偶发性的
抖动产生报警消息的情况
* influxDB数据查询除了返回数值，另外返回最新一个point详细数据用于报警消息模板
* 增加influxDB数据查询页面
* influxdb数据监控增加短链接，跳转到influxdb数据查询页面
* 监控列表增加"执行日志"操作按钮，点击跳转到对应监控执行日志列表页
* 集成测试，单元测试
* 增加web访问日志字段映射设置
* 增加web访问日志常用分析图表
* Elasticsearch监控数值实现环比监控
* 增加时序数据历史数据比较规则
* 监控增加报警消息允许发送时间段设置，非允许发送时间段内消息将只记录不发送，发送状态为FORBID
* 增加企业钉钉发消息默认实现(本地没有环境，需要帮助，欢迎PR，或者提供示例代码，先行谢过)
* 更多报警方式补充（欢迎PR）
* 后端接口增加数据校验并返回合适的提示信息
* 监控列表增加一个开关选项，只显示我的监控
* 监控调度配置后显示预计调度时间
* Elasticsearch数据名配置时自动提示索引名称
* 数据源增加连接测试功能
* 增加监控模板功能：可以创建多个变量，变量名用于填写监控模板，保存时将变量名替换为变量值，
基于监控模板创建监控只需要填写变量值即可，基于模板一次可以创建多个监控。
* 国际化
* 发布1.0-RELEASE
* 增加frostmourne程序日志格式采集方案
* 增加frostmourne程序日志查询和分析功能
* 3-sigma离群点检测报警规则
* 加入时序数据异常检测算法规则(需要实验可行性，欢迎有相关经验的同僚联系)
* 移除xxl-job依赖，内置实现分布式监控调度，减小部署难度(待定)
* 总结项目用到的知识点

## 发版历史

[ReleaseNotes](./ReleaseNotes.md)

## 主要技术栈

* springboot 2.x
* element ui
* vue-admin-template
* xxl-job
* mybatis
* freemarker
* elasticsearch
* InfluxDB
* jjwt
* nashorn

## Contributors

[@menong-chen](https://github.com/menong-chen) [@fox2zz](https://github.com/fox2zz) [@xyzj91](https://github.com/xyzj91)
[@wxmclub](https://github.com/wxmclub)

## 致谢
- [springboot](https://github.com/spring-projects/spring-boot)
- [vue](https://cn.vuejs.org/index.html)
- [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template)
- [xxl-job](https://github.com/xuxueli/xxl-job)
- [element ui](https://element.eleme.cn/#/zh-CN)
- [45短网址](https://45dwz.cn/)
- [jjwt](https://github.com/jwtk/jjwt)
- [mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql)
- [JetBrains](https://www.jetbrains.com/)

## License

The project is licensed under the [MIT](LICENSE).

## 如何参与贡献

如果你觉得这个项目对你有所帮助想有所回馈，非常欢迎参与贡献。可以通过如下方式：

* 顺手star表示鼓励
* 帮忙扩散推广
* 部署本项目使用起来并通过[issue#17](https://github.com/AutohomeCorp/frostmourne/issues/17)告知
* 在issue提出你的宝贵建议
* 对文档进行必要补充
* 加入交流群，解答一些问题
* 从后续规划里选择合适的任务提交PR

## 项目事记

* 2019-12-16: 发布github
* 2020-06-14: 发布gitee
* 2020-07-02: 合并第一个PR
* 2020-07-04: 被elastic中文社区收录 [Elastic日报988期](https://elasticsearch.cn/article/14018)
* 2020-07-13: github项目设置为私有，丢失82个star，29个fork
* 2020-07-15: 重新公开github
* 2020-08-23: 上gitee推荐
* 2020-08-27: gitee star破百
* 2020-10-12: github star破百
