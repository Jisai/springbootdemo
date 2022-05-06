package com.scott.springbootdemo.controller;

    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
    import com.scott.springbootdemo.service.IUserService;
    import com.mephiste.eidos.framework.api.common.Response;
    import com.mephiste.eidos.framework.api.common.ResponseWrapper;
    import com.scott.springbootdemo.entity.User;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.MediaType;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.*;

    import javax.annotation.Resource;

    /**
    * @author ： Scott S
    * @date ：Created in 2022-05-06 20:29:42
    * @description： 服务类
    * @modified By：
    * @version: 1.0
    */
    @Api(tags = "管理")
    @Slf4j
    @RequestMapping("/user")
    @RestController
    public class UserController {

    @Resource
    private IUserService iUserService;

    @ApiOperation(value = " 分页列表", notes = " 分页列表")
    @PostMapping(value = "/v1/page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<Page<User>> page(@Validated @RequestBody PageDTO pageDTO) {
    return ResponseWrapper.ok(iUserService.page(pageDTO));
    }

    @ApiOperation(value = "  新增", notes = " 新增")
    @PostMapping(value = "/v1/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<User> add(@Validated @RequestBody User user) {
    return ResponseWrapper.ok(iUserService.add(user));
    }

    @ApiOperation(value = "  更新", notes = " 更新")
@PostMapping(value = "/v1/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public Response<Boolean> update(@Validated @RequestBody User user) {
    return ResponseWrapper.ok(iUserService.update(user));
    }

    @ApiOperation(value = " 详情", notes = " 详情")
    @GetMapping(value = "/v1/detail/{id}")
    public Response<User> detail(Long id) {
    return ResponseWrapper.ok(iUserService.detail(id));
    }

    @ApiOperation(value = " 根据主键ID删除", notes = " 根据主键ID删除")
    @GetMapping(value = "/v1/delete/{ids}")
    public Response<Boolean> delete(Long id) {
        return ResponseWrapper.ok(iUserService.delete(id));
        }

        @ApiOperation(value = " 批量删除", notes = " 批量删除")
        @GetMapping(value = "/v1/batchDelete/{ids}")
        public Response<Boolean> batchDelete(String ids) {
            return ResponseWrapper.ok(iUserService.batchDelete(ids));
            }

            }
