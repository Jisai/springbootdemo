package com.scott.springbootdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scott.springbootdemo.entity.User;
import com.scott.springbootdemo.mapper.UserMapper;
import com.scott.springbootdemo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *  服务实现
 *
 * @author Scott S
 * @since 2022-05-06 20:29:42
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> {

    @Override
    public IPage<User> page(PageDTO pageDTO) {
        // 第一种分页
        Page<User> page = new Page<>(pageDTO.getCurrent(), pageDTO.getSize());
        //QueryWrapper<User> wrapper = new QueryWrapper();
        //return baseMapper.selectPage(page, wrapper);
        // 第二种分页
        return baseMapper.getPageList(page,pageDTO);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class, MybatisPlusException.class})
    public User add(User user) {
        if (!super.save(user)) {
            log.error("save fail, 请求入参: {}" , JSONObject.toJSONString(user));
            throw new MybatisPlusException("操作失败");
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class, MybatisPlusException.class})
    public Boolean update(User user) {
        Assert.isFalse(Objects.isNull(super.getById(user.getId())), "资源不存在");
        if (!super.updateById(user)) {
            log.error("updateById fail, 请求入参: {}" , JSONObject.toJSONString(user));
            throw new MybatisPlusException("操作失败");
        }
        return Boolean.TRUE;
    }

    @Override
    public User detail(Long id) {
        User byId = super.getById(id);
        Assert.isFalse(Objects.isNull(byId), "资源不存在");
        return byId;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class, MybatisPlusException.class})
    public Boolean delete(Long id) {
        Assert.isFalse(Objects.isNull(super.getById(id)), "资源不存在");
        if (!super.removeById(id)) {
            log.error("removeById fail, 请求入参: {}" , id );
            throw new MybatisPlusException("操作失败");
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class, MybatisPlusException.class})
    public Boolean batchDelete(String ids) {
        Assert.isTrue(StringUtils.isNotBlank(ids), "请求参数错误");
        String[] batchIds = StringUtils.split(ids, ',');
        Assert.isFalse(Objects.isNull(batchIds), "请求参数错误");
        Assert.isTrue(batchIds.length > 0, "请求参数错误");
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.lambda().in(User::getId,batchIds);
        wrapper.last("limit 0," + batchIds.length);
        List<User> list = super.list(wrapper);
        Assert.isTrue(CollectionUtils.isNotEmpty(list) && list.size()==batchIds.length,"请求参数关联失败");
        if (!super.removeBatchByIds(Arrays.asList(batchIds))) {
           log.error("removeBatchByIds fail, 请求入参: {}" , ids);
           throw new MybatisPlusException("操作失败");
        }
        return Boolean.TRUE;
    }

}
