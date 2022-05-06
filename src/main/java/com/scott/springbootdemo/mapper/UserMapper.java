package com.scott.springbootdemo.mapper;

    import com.baomidou.mybatisplus.core.mapper.BaseMapper;
    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
    import com.scott.springbootdemo.entity.User;
    import org.apache.ibatis.annotations.Mapper;
    import org.apache.ibatis.annotations.Param;

    /**
    * @author ： Scott S
    * @date ：Created in 2022-05-06 20:29:42
    * @description： Mapper 接口
    * @modified By：
    * @version: 1.0
    */
    @Mapper
    public interface UserMapper extends BaseMapper<User> {

    /**
    * 分页查询
    *
    * @param page
    * @param pageDTO
    * @return
    */
    Page<User> getPage(@Param("page") Page<User> page, @Param("dto") PageDTO pageDTO);

}
