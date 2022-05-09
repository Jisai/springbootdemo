package com.scott.springbootdemo.generator;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

/**
 * 单模块代码自动生成器
 * mybatis-plus-generator 3.5.1
 *
 * @author ：eidos.mephiste
 * @version : 1.0
 * @date ：Created in 2022/1/12
 * @description :
 * @modified By ：
 */
public class SimpleModuleCodeGenerator {

    public static void main(String[] args) {
        String database = "127.0.0.1:3306/test";
        String username = "root";
        String password = "root";
        String[] tables = new String[]{"test"};
        String parentPackageName = "com.scott.springbootdemo";
        //项目名称
        String moduleName = null;
        simpleModuleCodeGenerator(database, username, password, tables, parentPackageName, moduleName);
    }

    /**
     * 单模块代码自动生成器
     *
     * @param database          数据库连接： IP:PORT/实例名
     * @param username          数据库用户名
     * @param password          数据库密码
     * @param tables            表名 （字符串数组）
     * @param parentPackageName 父包名
     * @param moduleName        模块名称(如果没有则为空)
     */
    public static void simpleModuleCodeGenerator(String database, String username, String password, String[] tables, String parentPackageName, String moduleName) {
        StringBuffer outPutDir = new StringBuffer(System.getProperty("user.dir"));
        if (StringUtils.isNotBlank(moduleName)) {
            outPutDir.append("/").append(moduleName);
        }

        // 包路径
        String packagePath = outPutDir + "/src/main/java";
        // XML文件的路径
        String mapperXmlPath = outPutDir + "/src/main/resources/mapper";

        // 数据源配置
        StringBuffer url = new StringBuffer();
        url.append("jdbc:mysql://").append(database).append("?characterEncoding=UTF-8&useSSL=false&useUnicode=true&serverTimezone=UTC");

        FastAutoGenerator.create(url.toString(), username, password)
                .globalConfig(builder -> builder
                        // 作者名称
                        .author("Scott S")
                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。
                        //.fileOverride()
                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
                        .disableOpenDir()
                        // 指定输出目录。多模块不指定
                        .outputDir(packagePath)
                        // 开启swagger2.注释掉则默认关闭。
                        .enableSwagger()
                        // 指定时间策略。
                        .dateType(DateType.ONLY_DATE)
                        // 注释时间策略。
                        .commentDate("yyyy-MM-dd HH:mm:ss")
                )
                .packageConfig(builder -> builder
                        // 设置父包名
                        .parent(parentPackageName)
                        // 设置父包模块名
                        //.moduleName("system")
                        // mapper.xml 文件的路径。单模块下，其他文件路径默认即可。
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlPath))
                )
                .strategyConfig(builder -> builder
                        // 表名
                        .addInclude(tables)
                        // 阶段1：Entity实体类策略配置
                        .entityBuilder()
                        // 设置父类。会在生成的实体类名后：extends BaseEntity
                        // .superClass(BaseEntity.class)
                        // 生成 serialVersionUID。（不推荐禁用）
                        //.disableSerialVersionUID()
                        // 开启生成字段常量。
                        //.enableColumnConstant()
                        // 开启链式模型。
                        .enableChainModel()
                        // 开启 lombok 模型。
                        .enableLombok()
                        // 开启 Boolean 类型字段移除 is 前缀。
                        .enableRemoveIsPrefix()
                        // 开启生成实体时生成字段注解。
                        // 会在实体类的属性前，添加[@TableField("nickname")]
                        .enableTableFieldAnnotation()
                        // 逻辑删除字段名(数据库)。
                        //.logicDeleteColumnName("is_delete")
                        // 逻辑删除属性名(实体)。
                        // 会在实体类的该字段属性前加注解[@TableLogic]
                        //.logicDeletePropertyName("isDelete")
                        // 数据库表映射到实体的命名策略(默认下划线转驼峰)。一般不用设置
                        .naming(NamingStrategy.underline_to_camel)
                        // 数据库表字段映射到实体的命名策略(默认为 null，未指定按照 naming 执行)。一般不用设置
                        .columnNaming(NamingStrategy.underline_to_camel)
                        // 添加父类公共字段。
                        // 这些字段不会出现在新增的实体类中。
                        //.addSuperEntityColumns("id", "delete_time")
                        // 添加忽略字段。
                        // 这些字段不会出现在新增的实体类中。
                        // .addIgnoreColumns("password")
                        // 添加表字段填充
                        // 会在实体类的该字段上追加注解[@TableField(value = "create_time", fill = FieldFill.INSERT)]
                        .addTableFills(new Column("create_time", FieldFill.INSERT))
                        // 会在实体类的该字段上追加注解[@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)]
                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                        // 全局主键类型。如果MySQL主键设置为自增，则不需要设置此项。
                        //.idType(IdType.AUTO)
                        // 格式化文件名称。
                        // 如果不设置，如表[sys_user]的实体类名是[SysUser]。设置成下面这样，将是[SysUserEntity]
                        //.formatFileName("%sEntity")
                        // 阶段2：Mapper策略配置
                        .mapperBuilder()
                        // 设置父类
                        // 会在mapper接口方法集成[extends BaseMapper<XXXEntity>]
                        // .superClass(BaseMapper.class)
                        // 启用 BaseResultMap 生成。
                        // 会在mapper.xml文件生成[通用查询映射结果]配置。
                        .enableBaseResultMap()
                        // 启用 BaseColumnList。
                        // 会在mapper.xml文件生成[通用查询结果列 ]配置
                        .enableBaseColumnList()
                        // 设置缓存实现类
                        // .cache(MyMapperCache.class)
                        // 格式化 mapper 文件名称。
                        // 如果不设置，如表[sys_user]，默认的文件名是[SysUserMapper]。写成下面这种形式后，将变成[SysUserDao]。
                        // .formatMapperFileName("%sDao")
                        // 格式化 xml 实现类文件名称。
                        // 如果不设置，如表[sys_user]，默认的文件名是[SysUserMapper.xml]，写成下面这种形式后，将变成[SysUserXml.xml]。
                        // .formatXmlFileName("%sXml")
                        // 阶段3：Service策略配置
                        // .serviceBuilder()
                        // 设置 service 接口父类
                        // .superServiceClass(BaseService.class)
                        // 设置 service 实现类父类
                        // .superServiceImplClass(BaseServiceImpl.class)
                        // 格式化 service 接口文件名称
                        // 如果不设置，如表[sys_user]，默认的是[ISysUserService]。写成下面这种形式后，将变成[SysUserService]。
                        // .formatServiceFileName("%sService")
                        // 格式化 service 实现类文件名称
                        // 如果不设置，如表[sys_user]，默认的是[SysUserServiceImpl]。
                        // .formatServiceImplFileName("%sServiceImpl")

                        // 阶段4：Controller策略配置
                        .controllerBuilder()
                        // 设置父类。
                        // 会集成此父类。
                        // .superClass(BaseController.class)
                        // 开启生成 @RestController 控制器
                        // 会在控制类中加[@RestController]注解。
                        .enableRestStyle()
                        // 开启驼峰转连字符
                        .enableHyphenStyle()
                )
                .templateConfig(builder -> builder
                        // 自定义模板：https://github.com/baomidou/generator/tree/develop/mybatis-plus-generator/src/main/resources/templates
                        .entity("/freemarker/entity.java")
                        .mapper("/freemarker/mapper.java")
                        .mapperXml("/freemarker/mapper.xml")
                        .service("/freemarker/service.java")
                        .serviceImpl("/freemarker/serviceImpl.java")
                        .controller("/freemarker/controller.java")

                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


}

