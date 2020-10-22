package com.springcloud.local.servicefeign.service.impl;

import com.springcloud.local.servicefeign.service.FeignService;
import org.springframework.stereotype.Component;

/**
 * Function：Feign使用的断路器
 *
 * @Author penglvx
 * @Date 2020/10/21 16:38
 * @Version 1.0
 * @UpdatedBy
 */
@Component
public class SchedualServiceHiHystric implements FeignService {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry："+name;
    }

}
