package com.springcloud.local.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
        //@EnableDiscoveryClient向服务中心注册 @EnableHystrix注解开启Hystrix断路器
        System.out.println("Eureka-ribbon客户端启动成功");
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        //@LoadBalanced表明开启负载均衡的功能
        return new RestTemplate();
    }
}
