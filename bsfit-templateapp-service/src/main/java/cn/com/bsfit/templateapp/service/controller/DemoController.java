package cn.com.bsfit.templateapp.service.controller;

import cn.com.bsfit.cloud.core.util.BsfitResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个Controller 类
 * @Author: yujl
 * @Date: 2023/4/10 16:11
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/test1")
    public BsfitResponse test1(){
        return BsfitResponse.successWithMsg("接口调用成功");
    }
}
