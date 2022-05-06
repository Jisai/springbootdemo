package ${package.Entity};

<#list table.importPackages as pkg>
    import ${pkg};
</#list>
<#if swagger??>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel??>
    import lombok.*;
    <#if chainModel??>
        import lombok.experimental.Accessors;
    </#if>
</#if>

    /**
    * @author ： ${author}
    * @date ：Created in ${date}
    * @description：${table.comment!} 实体类
    * @modified By：
    * @version: 1.0
    */
<#if table.convert??>
    @TableName("${table.name}")
</#if>
<#if entityLombokModel>
    <#if superEntityClass??>
        @EqualsAndHashCode(callSuper = true)
    <#else>
        @EqualsAndHashCode(callSuper = false)
    </#if>
    <#if chainModel??>
        @Accessors(chain = true)
    </#if>
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
</#if>
<#--<#if swagger??>-->
    @ApiModel(value = "${entity} 实体", description = "${table.comment!}")
<#--</#if>-->
<#if superEntityClass??>
    public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> implements Serializable{
<#elseif activeRecord>
    public class ${entity} extends Model<${entity}> implements Serializable{
<#else>
    public class ${entity} <#if entitySerialVersionUID>implements Serializable</#if> {
</#if>

<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  -------- -->
<#list table.fields as field>
<#-- 排除公共字段 -->
<#--  <#if field.propertyName != 'id' && field.propertyName != 'delFlag' && field.propertyName != 'createdTime' && field.propertyName != 'createdBy' && field.propertyName != 'updatedTime' && field.propertyName != 'updatedBy' && field.propertyName != 'remark'>-->

    /**
    * ${field.comment}
    */
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if swagger??>
        @ApiModelProperty("${field.comment}")
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
        <#if field.keyIdentityFlag>
            @TableId(value = "${field.annotationColumnName}", type = IdType.AUTO)
        <#elseif idType??>
            @TableId(value = "${field.annotationColumnName}", type = IdType.${idType})
        <#elseif field.convert>
            @TableId("${field.annotationColumnName}")
        </#if>
    <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
        <#if field.convert>
            @TableField(value = "${field.annotationColumnName}", fill = FieldFill.${field.fill})
        <#else>
            @TableField(fill = FieldFill.${field.fill})
        </#if>
    <#elseif field.convert>
        @TableField("${field.annotationColumnName}")
    </#if>
<#-- 乐观锁注解 -->
    <#if field.versionField>
        @Version
    </#if>
<#-- 逻辑删除注解 -->
    <#if field.logicDeleteField>
        @TableLogic
    </#if>
    private ${field.propertyType} ${field.propertyName};
<#--  </#if>-->
</#list>
<#-- ----------  END 字段循环遍历  -------- -->
<#-- Lombok 模式 -->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
        public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
        }

        <#if chainModel>
            public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
            public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if chainModel>
            return this;
        </#if>
        }
    </#list>
</#if>
<#-- 列常量 -->
<#--<#if entityColumnConstant??>-->
<#--  <#list table.fields as field>-->
<#--  public static final String ${field.name?upper_case} = "${field.name}";-->
<#-- </#list>-->
<#--</#if>-->
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}


