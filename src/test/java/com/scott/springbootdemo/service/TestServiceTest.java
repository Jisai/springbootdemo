package com.scott.springbootdemo.service;

import com.alibaba.fastjson.JSON;
import com.scott.springbootdemo.service.impl.TestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassNamee: TestServiceTest
 * @Description:
 * @Author: Scott S
 * @Date: 2022-05-12 09:28
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {

    @Resource
    private TestServiceImpl testService;


    @Test
    public void list(){
        System.out.println(JSON.toJSONString(testService.list()));
    }



}
