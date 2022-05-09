package com.scott.springbootdemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.scott.springbootdemo.service.ITestService;
import com.scott.springbootdemo.entity.Test;
import com.scott.springbootdemo.service.impl.TestServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @author ： Scott S
* @date ：Created in 2022-05-08 20:48:33
* @description： 服务类
* @modified By：
* @version: 1.0
*/
@Api(tags = "管理")
@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private TestServiceImpl testService;

    @ApiOperation(value = " 分页列表", notes = " 分页列表")
    @PostMapping(value = "/page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Test> page() {
        return testService.page(new Page<>(1,2));
    }

//    @ApiOperation(value = "  新增", notes = " 新增")
//    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Test add(@Validated @RequestBody Test test) {
//        return iTestService.add(test);
//    }

}
