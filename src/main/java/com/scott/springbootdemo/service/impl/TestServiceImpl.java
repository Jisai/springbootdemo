package com.scott.springbootdemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scott.springbootdemo.entity.Test;
import com.scott.springbootdemo.mapper.TestMapper;
import com.scott.springbootdemo.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
*  服务实现
*
* @author Scott S
* @since 2022-05-08 20:48:33
*/
@Service
@Slf4j
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {


//    QueryWrapper queryWrapper;
//    LambdaQueryWrapper lambdaQueryWrapper;
//    LambdaQueryChainWrapper lambdaQueryChainWrapper;



}
