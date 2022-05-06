package com.scott.springbootdemo.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.*;
        import lombok.experimental.Accessors;

    /**
    * @author ： Scott S
    * @date ：Created in 2022-05-06 20:29:42
    * @description： 实体类
    * @modified By：
    * @version: 1.0
    */
    @TableName("user")
        @EqualsAndHashCode(callSuper = false)
        @Accessors(chain = true)
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(value = "User 实体", description = "")
    public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
        @ApiModelProperty("主键")
            @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 用户识别码
    */
        @ApiModelProperty("用户识别码")
            @TableId("user_code")
    private String userCode;

    /**
    * 用户名
    */
        @ApiModelProperty("用户名")
        @TableField("user_name")
    private String userName;

    /**
    * 密码
    */
        @ApiModelProperty("密码")
        @TableField("password")
    private String password;

    /**
    * 是否删除(0:未删除；1删除)
    */
        @ApiModelProperty("是否删除(0:未删除；1删除)")
        @TableField("deleted")
    private Boolean deleted;
}


