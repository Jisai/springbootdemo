package ${package.Service};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import ${package.Entity}.${entity};

/**
* @author ： ${author}
* @date ：Created in ${date}
* @description：${table.comment!} 服务类
* @modified By：
* @version: 1.0
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} {

    /**
    * ${table.comment!} 分页列表
    *
    * @param pageDTO
    * @return
    */
    Page<${entity}> page(PageDTO pageDTO);

    /**
    * ${table.comment!} 新增
    *
    * @param ${entity?uncap_first}
    * @return
    */
    ${entity} add(${entity} ${entity?uncap_first});

    /**
    * ${table.comment!} 更新
    *
    * @param ${entity?uncap_first}
    * @return
    */
    Boolean update(${entity} ${entity?uncap_first});

    /**
    * ${table.comment!} 详情
    *
    * @param id
    * @return
    */
    ${entity} detail(Long id);

    /**
    * ${table.comment!} 根据主键ID删除
    *
    * @param id 主键ID
    * @return
    */
    Boolean delete(Long id);

    /**
    * ${table.comment!} 批量删除
    *
    * @param ids 主键ID 以逗号隔开
    * @return
    */
    Boolean batchDelete(String ids);

}
</#if>
