package com.scott.springbootdemo.controller;

import com.scott.springbootdemo.SpringbootdemoApplication;
import com.scott.springbootdemo.service.impl.TestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

/**
 * @ClassNamee: TestControllerMockTest
 * @Description:    https://juejin.cn/post/7036140165944836104
 * @Author: Scott S
 * @Date: 2022-05-09 22:26
 * @Version: 1.0
 **/
@SpringBootTest(classes = SpringbootdemoApplication.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class TestControllerMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TestServiceImpl testService;

    @BeforeEach
    public void setUp() {
//        Mockito.when(testService.page((Mockito.any())).thenReturn("看山说：" + System.currentTimeMillis());
    }

    @Test
    public  void echo() throws Exception {
        final String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/test/page")
                        .param("current", "1")
                        .param("size", "2")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertTrue(result.startsWith("看山"));
    }
}

