package ${package.Controller};

    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
    import ${package.Service}.${table.serviceName};
    import com.mephiste.eidos.framework.api.common.Response;
    import com.mephiste.eidos.framework.api.common.ResponseWrapper;
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
    @PostMapping(value = "/v1/page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<Page<${entity}>> page(@Validated @RequestBody PageDTO pageDTO) {
    return ResponseWrapper.ok(${table.serviceName?uncap_first}.page(pageDTO));
    }

    @ApiOperation(value = "${table.comment!}  新增", notes = "${table.comment!} 新增")
    @PostMapping(value = "/v1/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<${entity}> add(@Validated @RequestBody ${entity} ${entity?uncap_first}) {
    return ResponseWrapper.ok(${table.serviceName?uncap_first}.add(${entity?uncap_first}));
    }

    @ApiOperation(value = "${table.comment!}  更新", notes = "${table.comment!} 更新")
@PostMapping(value = "/v1/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public Response<Boolean> update(@Validated @RequestBody ${entity} ${entity?uncap_first}) {
    return ResponseWrapper.ok(${table.serviceName?uncap_first}.update(${entity?uncap_first}));
    }

    @ApiOperation(value = "${table.comment!} 详情", notes = "${table.comment!} 详情")
    @GetMapping(value = "/v1/detail/{id}")
    public Response<${entity}> detail(Long id) {
    return ResponseWrapper.ok(${table.serviceName?uncap_first}.detail(id));
    }

    @ApiOperation(value = "${table.comment!} 根据主键ID删除", notes = "${table.comment!} 根据主键ID删除")
    @GetMapping(value = "/v1/delete/{ids}")
    public Response<Boolean> delete(Long id) {
        return ResponseWrapper.ok(${table.serviceName?uncap_first}.delete(id));
        }

        @ApiOperation(value = "${table.comment!} 批量删除", notes = "${table.comment!} 批量删除")
        @GetMapping(value = "/v1/batchDelete/{ids}")
        public Response<Boolean> batchDelete(String ids) {
            return ResponseWrapper.ok(${table.serviceName?uncap_first}.batchDelete(ids));
            }

            }
            </#if>
