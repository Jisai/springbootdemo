package com.scott.springbootdemo.controller;


import com.alibaba.fastjson.JSON;
import com.scott.springbootdemo.SpringbootdemoApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

/**
 * @ClassNamee: TestControllerTest
 * @Description: https://juejin.cn/post/7036140165944836104
 * @Author: Scott S
 * @Date: 2022-05-08 22:26
 * @Version: 1.0
 **/
@SpringBootTest(classes = SpringbootdemoApplication.class)
@AutoConfigureMockMvc
public class TestControllerNoMockitoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPage() throws Exception {
        final String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/test/page")
                        .param("current", "1")
                        .param("size", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(JSON.toJSONString(result));
    }

}
