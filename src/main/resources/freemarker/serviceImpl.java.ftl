package ${package.ServiceImpl};

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import ${superServiceImplClassPackage};
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * ${table.comment!} 服务实现
 *
<#if author != "">
 * @author ${author}
</#if>
<#if date != "">
 * @since ${date}
</#if>
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
@Slf4j
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> {

    @Override
    public IPage<${entity}> page(PageDTO pageDTO) {
        // 第一种分页
        Page<${entity}> page = new Page<>(pageDTO.getCurrent(), pageDTO.getSize());
        //QueryWrapper<${entity}> wrapper = new QueryWrapper();
        //return baseMapper.selectPage(page, wrapper);
        // 第二种分页
        return baseMapper.getPageList(page,pageDTO);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class, MybatisPlusException.class})
    public ${entity} add(${entity} ${entity?uncap_first}) {
        if (!super.save(${entity?uncap_first})) {
            log.error("save fail, 请求入参: {}" , JSONObject.toJSONString(${entity?uncap_first}));
            throw new MybatisPlusException("操作失败");
        }
        return ${entity?uncap_first};
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class, MybatisPlusException.class})
    public Boolean update(${entity} ${entity?uncap_first}) {
        Assert.isFalse(Objects.isNull(super.getById(${entity?uncap_first}.getId())), "资源不存在");
        if (!super.updateById(${entity?uncap_first})) {
            log.error("updateById fail, 请求入参: {}" , JSONObject.toJSONString(${entity?uncap_first}));
            throw new MybatisPlusException("操作失败");
        }
        return Boolean.TRUE;
    }

    @Override
    public ${entity} detail(Long id) {
        ${entity} byId = super.getById(id);
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
        QueryWrapper<${entity}> wrapper=new QueryWrapper<>();
        wrapper.lambda().in(${entity}::getId,batchIds);
        wrapper.last("limit 0," + batchIds.length);
        List<${entity}> list = super.list(wrapper);
        Assert.isTrue(CollectionUtils.isNotEmpty(list) && list.size()==batchIds.length,"请求参数关联失败");
        if (!super.removeBatchByIds(Arrays.asList(batchIds))) {
           log.error("removeBatchByIds fail, 请求入参: {}" , ids);
           throw new MybatisPlusException("操作失败");
        }
        return Boolean.TRUE;
    }

}
</#if>
