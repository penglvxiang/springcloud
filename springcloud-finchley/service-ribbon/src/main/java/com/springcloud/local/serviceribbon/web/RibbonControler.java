package com.springcloud.local.serviceribbon.web;

import com.springcloud.local.serviceribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function：ribbon负载均衡测试controller
 *
 * @Author penglvx
 * @Date 2020/10/21 10:38
 * @Version 1.0
 * @UpdatedBy
 */
@RestController
public class RibbonControler {
    @Autowired
    RibbonService ribbonService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return ribbonService.hiService( name );
    }
}
