package com.scott.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.scott.springbootdemo.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author ： Scott S
* @date ：Created in 2022-05-08 20:48:33
* @description： Mapper 接口
* @modified By：
* @version: 1.0
*/
@Mapper
public interface TestMapper extends BaseMapper<Test> {

    /**
    * 分页查询
    *
    * @param page
    * @param pageDTO
    * @return
    */
    Page<Test> getPage(@Param("page") Page<Test> page, @Param("dto") PageDTO pageDTO);


}
