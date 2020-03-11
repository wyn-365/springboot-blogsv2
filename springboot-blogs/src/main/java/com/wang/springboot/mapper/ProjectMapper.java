package com.wang.springboot.mapper;

import com.wang.springboot.pojo.Project;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 王一宁
 * @date 2020/3/9 8:34
 */
@Mapper
public interface ProjectMapper {
    @Select("select id,img,title from t_project")
    List<Project> getProject();

    @Select("select * from t_project where id = #{id}")
    Project getProjectById(Long id);

    @Insert("insert into t_project(title,content,img) values(#{title},#{content},#{img})")
    void save(Project project);

    @Delete("delete from t_project where id = #{id}")
    void delete(Long id);
}
