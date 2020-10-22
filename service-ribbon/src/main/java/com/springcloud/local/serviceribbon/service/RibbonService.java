package com.springcloud.local.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Function：ribbon负载均衡测试service
 *
 * @Author penglvx
 * @Date 2020/10/21 10:31
 * @Version 1.0
 * @UpdatedBy
 */

@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * decription:通过restTemplate负载均衡向服务客户端发送请求，指定断路器方法
     * @param [name]
     * @return java.lang.String
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        /* 在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，
           根据服务实例在请求的时候会用具体的url替换掉服务名 */
        return restTemplate.getForObject("http://service-client/hi?name=" + name, String.class);
    }

    /**
     * decription: 断路器指定的熔断方法，自定义
     * @param [name]
     * @return java.lang.String
     */
    public String hiError(String name) {

        return "hi,"+name+",sorry,error!";
    }

}
