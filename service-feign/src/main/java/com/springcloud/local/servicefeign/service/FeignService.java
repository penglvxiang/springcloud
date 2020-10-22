package com.springcloud.local.servicefeign.service;

import com.springcloud.local.servicefeign.service.impl.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Function：
 *
 * @Author penglvx
 * @Date 2020/10/21 11:44
 * @Version 1.0
 * @UpdatedBy
 */
@FeignClient(value = "service-client", fallback = SchedualServiceHiHystric.class)
@Service
public interface FeignService {

    @RequestMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam String name);

}
