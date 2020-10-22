package com.springcloud.local.servicefeign.web;

import com.springcloud.local.servicefeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function：
 *
 * @Author penglvx
 * @Date 2020/10/21 11:42
 * @Version 1.0
 * @UpdatedBy
 */
@RestController
public class FeignControler {
    @Autowired
    FeignService feignService;

    @RequestMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return feignService.sayHiFromClientOne(name);
    }
}
