# 项目简介
**`SpringCloud微服务框架`**
    目前支持的版本为Spring Boot版本2.0.3.RELEASE,Spring Cloud版本为Finchley.RELEASE
    Finchley版本的官方文档如下：
    http://cloud.spring.io/spring-cloud-static/Finchley.RELEASE/single/spring-cloud.html
    
    spring cloud为开发人员提供了快速构建分布式系统的一些工具，包括配置管理、服务发现、
    断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等。它运行环境简单，可以
    在开发人员的电脑上跑。另外说明spring cloud是基于springboot的，所以需要开发中对springboot
    有一定的了解
    
## 1.服务的注册与发现Eureka
    eureka是一个高可用的组件，它没有后端缓存，每一个实例注册之后需要向注册中心发送心跳（可以在内存中完成），
    在默认情况下erureka server也是一个eureka client ,必须要指定一个 server，先启动server，再启动client

## 2.服务调用ribbon+restTemplate,可以理解为类似F5的负载均衡   
    在微服务架构中，业务都会被拆分成一个独立的服务，服务与服务的通讯是基于http restful的
    Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign 
    ribbon是一个负载均衡客户端，可以很好的控制http和tcp的一些行为，Feign默认集成了ribbon
    ribbon已经默认实现了这些配置bean：
    IClientConfig ribbonClientConfig: DefaultClientConfigImpl
    IRule ribbonRule: ZoneAvoidanceRule
    IPing ribbonPing: NoOpPing
    ServerList ribbonServerList: ConfigurationBasedServerList  
    ServerListFilter ribbonServerListFilter: ZonePreferenceServerListFilter    
    ILoadBalancer ribbonLoadBalancer: ZoneAwareLoadBalancer
   
## 3.服务调用Feign,可以理解为类似F5的负载均衡  
    Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解
    它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。Feign支持可插拔的编码器和解码器。
    Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果
    简而言之：
    Feign 采用的是基于接口的注解
    Feign 整合了ribbon，具有负载均衡的能力
    整合了Hystrix，具有熔断的能力     
 
## 4.断路器(Hystrix)   
    在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC），
    在Spring Cloud可以用RestTemplate+Ribbon和Feign来调用。为了保证其高可用，单个
    服务通常会集群部署。由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个
    服务出现问题，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，Servlet容器的
    线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，会对整个微
    服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应
    为了解决这个问题，业界提出了断路器模型，简单理解类似于java中的异常捕捉，统一异常返回
    1)在ribbon使用断路器
      首先在pox.xml文件中加入spring-cloud-starter-netflix-hystrix的起步依赖
      在程序的启动类ServiceRibbonApplication 加@EnableHystrix注解开启Hystrix
      在Service方法上加上@HystrixCommand注解，该注解对该方法创建了断路器的功能，
      并指定了fallbackMethod熔断方法，熔断方法可自定义
    2)在Feign使用断路器
      Feign是自带断路器的，在D版本的Spring Cloud之后，它没有默认打开
      需要在配置文件中配置打开它，在配置文件加以下代码feign.hystrix.enabled=true
      需要在FeignClient的Service接口的注解中加上fallback的指定类  
      
## 5. 路由网关(zuul)      
    Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/user转发到到user服务
    /api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能
    zuul不仅只是路由，并且还能过滤，做一些自定义安全验证
    
## 6. 分布式配置中心(Spring Cloud Config) 
    在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组件
    在Spring Cloud中，有分布式配置中心组件spring cloud config ，它支持配置服务放在配置服务的内存中（即本地）
    也支持放在远程Git仓库中,在spring cloud config 组件中，分两个角色，一是config server，二是config client

    
    