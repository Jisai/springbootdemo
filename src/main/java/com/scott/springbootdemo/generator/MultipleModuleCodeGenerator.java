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

import java.util.HashMap;

/**
 * 多模块生成配置
 * @author ：eidos.mephiste
 * @version : 1.0
 * @date ：Created in 2022/1/14
 * @description :
 * @modified By ：
 */
public class MultipleModuleCodeGenerator {

    // 基础信息配置
    // 数据库连接字符
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/organization?useUnicode=true&serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
    // 数据库用户名
    private static final String USERNAME = "root";
    // 数据库密码
    private static final String PASSWORD = "root";
    // 项目根路径。生成结果如：D:\MyProject\spring-boot
    private static final String projectRootPath = System.getProperty("user.dir");
    // 父包名。用于生成的java文件的import。
    //private static final String parentPackageName = "com.eidos.mephiste";

    public static void main(String[] args) {
        String database = "localhost:3306/organization";
        String username = "root";
        String password = "root";
        String[] tables = new String[]{"user"};
        //统一父包名
        String parentPackageName = "com.scott.springbootdemo";
        // 实体模块名称
        String entityModule = "entity";
        // mapper模块名称
        String mapperModule = "mapper";
        // service模块名称
        String serviceModule = "service";
        // controller模块名称
        String controllerModule = "controller";
        // 实体自定义包名
        String entityPackage = "entity";
        // manpper自定义包名
        String mapperPackage = "mapper";
        // service自定义包名
        String servicePackage = "service";
        // controller自定义包名
        String controllerPackage = "controller";
        multipleModuleCodeGenerator(database, username, password, tables, parentPackageName, entityModule, entityPackage, mapperModule, mapperPackage, serviceModule, servicePackage, controllerModule, controllerPackage);
    }

    /**
     * 多模块代码自动生成器
     *
     * @param database
     * @param username
     * @param password
     * @param tables
     * @param parentPackageName
     * @param entityModule
     * @param mapperModule
     * @param serviceModule
     * @param controllerModule
     */
    public static void multipleModuleCodeGenerator(String database, String username, String password, String[] tables, String parentPackageName,
                                                   String entityModule, String entityPackage, String mapperModule, String mapperPackage,
                                                   String serviceModule, String servicePackage, String controllerModule, String controllerPackage) {
        // 基础路径配置。多模块项目下，一般来说每个文件的路径都是不同的（根据项目实际，可能在不同的模块下）。
        String packagePath = StringUtils.replace(parentPackageName, ".", "/");
        String entityTemp = StringUtils.replace(entityPackage, ".", "/");
        String mapperTemp = StringUtils.replace(mapperPackage, ".", "/");
        String serviceTemp = StringUtils.replace(servicePackage, ".", "/");
        String serviceImplTemp = StringUtils.replace(servicePackage + ".impl", ".", "/");
        String controllerTemp = StringUtils.replace(controllerPackage, ".", "/");
        String entityPath = projectRootPath + "/" + entityModule + "/src/main/java/" + packagePath + "/" + entityTemp;
        String mapperPath = projectRootPath + "/" + mapperModule + "/src/main/java/" + packagePath + "/" + mapperTemp;
        String mapperXmlPath = projectRootPath + "/" + mapperModule + "/src/main/resources/mapper";
        String servicePath = projectRootPath + "/" + serviceModule + "/src/main/java/" + packagePath + "/" + serviceTemp;
        String serviceImplPath =
                projectRootPath + "/" + serviceModule + "/src/main/java/" + packagePath + "/" + serviceImplTemp;
        String controllerPath = projectRootPath + "/" + controllerModule + "/src/main/java/" + packagePath + "/" + controllerTemp;

        // 数据源配置
        StringBuffer url = new StringBuffer();
        url.append("jdbc:mysql://").append(database).append("?characterEncoding=UTF-8&useSSL=false&useUnicode=true&serverTimezone=UTC");

        FastAutoGenerator.create(url.toString(), username, password)
                .globalConfig(builder -> builder
                        // 作者名称
                        .author("Scott S")
                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。
                        // .fileOverride()
                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
                        .disableOpenDir()
                        // 指定输出目录。如果指定，Windows生成至D盘根目录下，Linux or MAC 生成至 /tmp 目录下。多模块不指定
                        //.outputDir("")
                        // 开启swagger2.注释掉则默认关闭。
                        .enableSwagger()
                        // 指定时间策略。
                        .dateType(DateType.ONLY_DATE)
                        // 注释时间策略。
                        .commentDate("yyyy-MM-dd HH:mm:ss")
                )
                .packageConfig(builder -> builder
                        // 设置父包名
                        .parent(StringUtils.replace(parentPackageName, "/", "."))
                        // 设置父包模块名
                        //.moduleName("web")
                        .pathInfo(
                                new HashMap<OutputFile, String>() {{
                                    // 实体类的保存路径
                                    put(OutputFile.entity, entityPath);
                                    // mapper接口的保存路径
                                    put(OutputFile.mapper, mapperPath);
                                    // mapper.xml文件的保存路径
                                    put(OutputFile.mapperXml, mapperXmlPath);
                                    // service层接口的保存路径
                                    put(OutputFile.service, servicePath);
                                    // service层接口实现类的保存路径
                                    put(OutputFile.serviceImpl, serviceImplPath);
                                    // 控制类的保存路径
                                    put(OutputFile.controller, controllerPath);
                                }}
                        )
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
                        // 会在实体类末尾生成一系列 [public static final String NICKNAME = "nickname";] 的语句。（一般在写wapper时，会用到）
                        //.enableColumnConstant()
                        // 开启链式模型。
                        // 会在实体类前添加 [@Accessors(chain = true)] 注解。用法如 [User user=new User().setAge(31).setName("snzl");]（这是Lombok的注解，需要添加Lombok依赖）
                        .enableChainModel()
                        // 开启 lombok 模型。
                        // 会在实体类前添加 [@Getter] 和 [@Setter] 注解。（这是Lombok的注解，需要添加Lombok依赖）
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

