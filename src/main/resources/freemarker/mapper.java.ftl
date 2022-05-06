package ${package.Mapper};

    import com.baomidou.mybatisplus.core.mapper.BaseMapper;
    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
    import ${package.Entity}.${entity};
    import org.apache.ibatis.annotations.Mapper;
    import org.apache.ibatis.annotations.Param;

    /**
    * @author ： ${author}
    * @date ：Created in ${date}
    * @description：${table.comment!} Mapper 接口
    * @modified By：
    * @version: 1.0
    */
    @Mapper
    public interface ${table.mapperName} extends BaseMapper<${entity}> {

    /**
    * 分页查询
    *
    * @param page
    * @param pageDTO
    * @return
    */
    Page<${entity}> getPage(@Param("page") Page<${entity}> page, @Param("dto") PageDTO pageDTO);

}
