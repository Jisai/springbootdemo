package com.scott.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
* @author ： Scott S
* @date ：Created in 2022-05-08 20:48:33
* @description： 实体类
* @modified By：
* @version: 1.0
*/
@TableName("test")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Test 实体", description = "")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 编码
    */
    @ApiModelProperty("编码")
    @TableField("code")
    private String code;

    /**
    * 名称
    */
    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    /**
    * 年级
    */
    @ApiModelProperty("年级")
    @TableField("age")
    private Integer age;

    /**
    * 是否删除（0否；1是）
    */
    @ApiModelProperty("是否删除（0否；1是）")
    @TableField("deleted")
    private Integer deleted;

    /**
    * 创建人编码
    */
    @ApiModelProperty("创建人编码")
    @TableField("create_code")
    private String createCode;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
    * 更新人编码
    */
    @ApiModelProperty("更新人编码")
    @TableField("update_code")
    private String updateCode;

    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}


