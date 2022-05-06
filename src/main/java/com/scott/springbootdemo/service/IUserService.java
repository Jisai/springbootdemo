package com.scott.springbootdemo.service;

    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
    import com.scott.springbootdemo.entity.User;

    /**
    * @author ： Scott S
    * @date ：Created in 2022-05-06 20:29:42
    * @description： 服务类
    * @modified By：
    * @version: 1.0
    */
    public interface IUserService {

    /**
    *  分页列表
    *
    * @param pageDTO
    * @return
    */
    Page<User> page(PageDTO pageDTO);

    /**
    *  新增
    *
    * @param user
    * @return
    */
    User add(User user);

    /**
    *  更新
    *
    * @param user
    * @return
    */
    Boolean update(User user);

    /**
    *  详情
    *
    * @param id
    * @return
    */
    User detail(Long id);

    /**
    *  根据主键ID删除
    *
    * @param id 主键ID
    * @return
    */
    Boolean delete(Long id);

    /**
    *  批量删除
    *
    * @param ids 主键ID 以逗号隔开
    * @return
    */
    Boolean batchDelete(String ids);

    }
