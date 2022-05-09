package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @author ： ${author}
* @date ：Created in ${date}
* @description：${table.comment!} 服务类
* @modified By：
* @version: 1.0
*/
@Api(tags = "${table.comment!}管理")
@Slf4j
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
<#else>
public class ${table.controllerName} {
</#if>

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @ApiOperation(value = "${table.comment!} 分页列表", notes = "${table.comment!} 分页列表")
    @PostMapping(value = "/page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<${entity}> page(@Validated @RequestBody PageDTO pageDTO) {
        return ${table.serviceName?uncap_first}.page(pageDTO);
    }

    @ApiOperation(value = "${table.comment!}  新增", notes = "${table.comment!} 新增")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ${entity} add(@Validated @RequestBody ${entity} ${entity?uncap_first}) {
        return ${table.serviceName?uncap_first}.add(${entity?uncap_first});
    }


}
</#if>
