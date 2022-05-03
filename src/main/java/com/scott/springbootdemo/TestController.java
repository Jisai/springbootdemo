package com.scott.springbootdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassNamee: TestController
 * @Description:
 * @Author: Scott S
 * @Date: 2022-05-03 19:15
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("getResult")
    public String home(){
        return "Hello World";
    }
}